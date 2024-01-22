package io.papermc.generator.types.registry;

import com.google.common.base.Suppliers;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeSpec;
import io.papermc.generator.Main;
import io.papermc.generator.types.SimpleGenerator;
import io.papermc.generator.utils.Annotations;
import io.papermc.generator.utils.Formatting;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import javax.lang.model.element.Modifier;
import io.papermc.generator.utils.RegistryUtils;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.flag.FeatureFlags;
import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.DefaultQualifier;

import static javax.lang.model.element.Modifier.FINAL;
import static javax.lang.model.element.Modifier.PRIVATE;

@DefaultQualifier(NonNull.class)
public abstract class EnumRegistryGenerator<T> extends SimpleGenerator {

    protected final ResourceKey<Registry<T>> registryKey;
    private final Supplier<List<ResourceKey<T>>> experimentalKeys;

    public EnumRegistryGenerator(final String className, final String pkg, ResourceKey<Registry<T>> registryKey) {
        super(className, pkg);
        this.registryKey = registryKey;
        this.experimentalKeys = Suppliers.memoize(() -> RegistryUtils.collectExperimentalKeys(Main.REGISTRY_ACCESS.registryOrThrow(this.registryKey)));
    }

    @Override
    protected TypeSpec getTypeSpec() {
        TypeSpec.Builder typeBuilder = TypeSpec.enumBuilder(this.className)
            .addSuperinterface(Keyed.class)
            .addModifiers(Modifier.PUBLIC)
            .addAnnotations(Annotations.CLASS_HEADER);

        Registry<T> registry = Main.REGISTRY_ACCESS.registryOrThrow(this.registryKey);
        List<Map.Entry<ResourceKey<T>, T>> paths = new ArrayList<>(registry.entrySet());
        paths.sort(Comparator.comparing(o -> o.getKey().location().getPath()));

        paths.forEach(entry -> {
            ResourceKey<T> resourceKey = entry.getKey();
            String pathKey = resourceKey.location().getPath();

            String fieldName = Formatting.formatPathAsField(pathKey);
            @Nullable String experimentalValue = this.getExperimentalValue(entry);
            TypeSpec.Builder builder = TypeSpec.anonymousClassBuilder("$S", pathKey);
            if (experimentalValue != null) {
                builder.addAnnotations(Annotations.experimentalAnnotations(experimentalValue));
            }

            typeBuilder.addEnumConstant(fieldName, builder.build());
        });

        FieldSpec keyField = FieldSpec.builder(NamespacedKey.class, "key", PRIVATE, FINAL).build();
        typeBuilder.addField(keyField);

        ParameterSpec keyParam = ParameterSpec.builder(String.class, "key").build();
        typeBuilder.addMethod(MethodSpec.constructorBuilder()
            .addParameter(keyParam).addCode("this.$N = $T.minecraft($N);", keyField, NamespacedKey.class, keyParam).build());

        typeBuilder.addMethod(MethodSpec.methodBuilder("getKey")
            .returns(NamespacedKey.class)
            .addModifiers(Modifier.PUBLIC)
            .addAnnotation(Annotations.NOT_NULL)
            .addAnnotation(Annotations.OVERRIDE)
            .addCode("return this.$N;", keyField).build());

        this.addExtras(typeBuilder, keyField);

        return typeBuilder.build();
    }

    public abstract void addExtras(TypeSpec.Builder builder, FieldSpec keyField);

    @Override
    protected JavaFile.Builder file(JavaFile.Builder builder) {
        return builder.skipJavaLangImports(true);
    }

    @Nullable
    public String getExperimentalValue(Map.Entry<ResourceKey<T>, T> entry) {
        if (this.experimentalKeys.get().contains(entry.getKey())) {
            return Formatting.formatFeatureFlag(FeatureFlags.UPDATE_1_21);
        }
        return null;
    }

}
