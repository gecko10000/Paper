package io.papermc.generator.types.registry;

import com.destroystokyo.paper.MaterialTags;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeVariableName;
import io.papermc.generator.Main;
import io.papermc.generator.types.SimpleGenerator;
import io.papermc.generator.utils.Annotations;
import io.papermc.generator.utils.Formatting;
import io.papermc.generator.utils.Javadocs;
import io.papermc.paper.tag.EntityTags;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import org.bukkit.Bukkit;
import org.bukkit.Fluid;
import org.bukkit.GameEvent;
import org.bukkit.Keyed;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Tag;
import org.bukkit.entity.EntityType;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;

import static com.squareup.javapoet.TypeSpec.interfaceBuilder;
import static io.papermc.generator.utils.Annotations.NOT_NULL;
import static io.papermc.generator.utils.Annotations.NULLABLE;
import static io.papermc.generator.utils.Annotations.experimentalAnnotations;
import static javax.lang.model.element.Modifier.ABSTRACT;
import static javax.lang.model.element.Modifier.FINAL;
import static javax.lang.model.element.Modifier.PUBLIC;
import static javax.lang.model.element.Modifier.STATIC;

@DefaultQualifier(NonNull.class)
public class TagGenerator extends SimpleGenerator {

    public static String CLASS_HEADER_JAVADOC = """
        Represents a tag that may be defined by the server or a resource pack to
        group like things together.
        <p>
        Note that whilst all tags defined within this interface must be present in
        Implementations, their existence is not guaranteed across future versions.
        <p>
        Custom tags defined by Paper are not present (as constants) in this class.
        To access them please refer to {@link $T}
        and {@link $T}.
    
        @param <T> the type of things grouped by this tag
        """;

    private static final String IS_TAGGED_JAVADOC = """
        Returns whether or not this tag has an entry for the specified item.
        
        @param item to check
        @return if it is tagged
        """;

    private static final String GET_VALUES_JAVADOC = """
        Gets an immutable set of all tagged items.
        
        @return set of tagged items
        """;

    private static final String REGISTRY_FIELD_JAVADOC = "Key for the built-in $L registry.";

    public record TagRegistry(String name, Class<?> apiType, ResourceKey<? extends Registry<?>> registryKey) {}

    private static TagRegistry registry(final String name, final Class<?> apiType, final ResourceKey<? extends Registry<?>> registryKey) {
        return new TagRegistry(name, apiType, registryKey);
    }

    private static final List<TagRegistry> TAG_REGISTRIES = List.of(
        registry("blocks", Material.class, Registries.BLOCK),
        registry("items", Material.class, Registries.ITEM),
        registry("fluids", Fluid.class, Registries.FLUID),
        registry("entity_types", EntityType.class, Registries.ENTITY_TYPE),
        registry("game_events", GameEvent.class, Registries.GAME_EVENT)
    );

    public TagGenerator(final String className, final String pkg) {
        super(className, pkg);
    }

    private TypeSpec.Builder tagHolderType() {
        return interfaceBuilder(this.className)
            .addModifiers(PUBLIC)
            .addSuperinterface(Keyed.class)
            .addJavadoc(CLASS_HEADER_JAVADOC, MaterialTags.class, EntityTags.class)
            .addAnnotations(Annotations.CLASS_HEADER);
    }

    @Override
    protected TypeSpec getTypeSpec() {
        final TypeVariableName typeVariable = TypeVariableName.get("T", Keyed.class);
        final TypeSpec.Builder typeBuilder = this.tagHolderType();
        typeBuilder.addTypeVariable(typeVariable);

        for (final TagRegistry tagRegistry : TAG_REGISTRIES) {
            final TypeName fieldType = ParameterizedTypeName.get(Tag.class, tagRegistry.apiType());

            final ResourceKey<? extends Registry<?>> registryKey = tagRegistry.registryKey();
            final Registry<?> registry = Main.REGISTRY_ACCESS.registryOrThrow(registryKey);
            final Collection<String> experimentalTags = Main.EXPERIMENTAL_TAGS.perRegistry().get(registryKey);

            final String registryFieldName = "REGISTRY_" + tagRegistry.name().toUpperCase(Locale.ENGLISH);
            final FieldSpec.Builder registryFieldBuilder = FieldSpec.builder(String.class, registryFieldName)
                .addModifiers(PUBLIC, STATIC, FINAL)
                .initializer("$S", tagRegistry.name())
                .addJavadoc(REGISTRY_FIELD_JAVADOC, registryKey.location().getPath());

            typeBuilder.addField(registryFieldBuilder.build());

            final String fieldPrefix = Formatting.formatTagFieldPrefix(tagRegistry.name(), registryKey);

            registry.getTagNames().sorted(Comparator.comparing(tagKey -> tagKey.location().getPath())).forEach(tagKey -> {
                final String keyPath = tagKey.location().getPath();

                final String fieldName = fieldPrefix + Formatting.formatPathAsField(keyPath);
                final FieldSpec.Builder fieldBuilder = FieldSpec.builder(fieldType, fieldName)
                    .addModifiers(PUBLIC, STATIC, FINAL)
                    .initializer("$T.getTag($L, $T.minecraft($S), $T.class)", Bukkit.class, registryFieldName, NamespacedKey.class, keyPath, tagRegistry.apiType())
                    .addJavadoc(Javadocs.getVersionDependentField("{@code $L}"), tagKey.location().toString());
                if (experimentalTags.contains(keyPath)) {
                    fieldBuilder.addAnnotations(experimentalAnnotations(Formatting.formatFeatureFlagName(Main.EXPERIMENTAL_TAGS.perFeatureFlag().get(tagKey))))
                                .addAnnotation(NULLABLE);
                }
                typeBuilder.addField(fieldBuilder.build());
            });

        }

        // deprecated tags
        typeBuilder.addField(
            FieldSpec.builder(ParameterizedTypeName.get(Tag.class, Material.class), "CARPETS")
                .addModifiers(PUBLIC, STATIC, FINAL)
                .addAnnotation(Deprecated.class)
                .initializer("WOOL_CARPETS")
                .addJavadoc(Javadocs.DEPRECATED_FOR, "WOOL_CARPETS").build()
        );

        // methods
        typeBuilder.addMethod(MethodSpec.methodBuilder("isTagged")
            .addModifiers(PUBLIC, ABSTRACT)
            .returns(boolean.class).addParameter(ParameterSpec.builder(typeVariable, "item")
                .addAnnotation(NOT_NULL).build())
            .addJavadoc(IS_TAGGED_JAVADOC)
            .build());

        typeBuilder.addMethod(MethodSpec.methodBuilder("getValues")
            .addModifiers(PUBLIC, ABSTRACT)
            .returns(ParameterizedTypeName.get(ClassName.get(Set.class), typeVariable))
            .addJavadoc(GET_VALUES_JAVADOC)
            .addAnnotation(NOT_NULL).build());

        return typeBuilder.build();
    }
}
