package io.papermc.generator.types.registry;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import io.papermc.generator.types.SimpleGenerator;
import io.papermc.generator.utils.Annotations;
import io.papermc.generator.utils.Formatting;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import javax.lang.model.element.Modifier;
import io.papermc.generator.utils.Javadocs;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.generator.structure.StructureType;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;

import static com.squareup.javapoet.TypeSpec.classBuilder;
import static io.papermc.generator.utils.Annotations.NOT_NULL;
import static javax.lang.model.element.Modifier.FINAL;
import static javax.lang.model.element.Modifier.PRIVATE;
import static javax.lang.model.element.Modifier.PUBLIC;
import static javax.lang.model.element.Modifier.STATIC;

@DefaultQualifier(NonNull.class)
public class StructureTypeGenerator extends SimpleGenerator { // todo abstract this one for similar stuff like game event?

    private static final String CLASS_HEADER = Javadocs.getVersionDependentClassHeader("StructureTypes");

    public StructureTypeGenerator(final String keysClassName, final String pkg) {
        super(keysClassName, pkg);
    }

    private MethodSpec.Builder createMethod(TypeName returnType) {
        final TypeName keyType = TypeName.get(String.class).annotated(NOT_NULL);

        final ParameterSpec keyParam = ParameterSpec.builder(keyType, "key", FINAL).build();
        final MethodSpec.Builder create = MethodSpec.methodBuilder("create")
            .addModifiers(PRIVATE, STATIC)
            .addParameter(keyParam)
            .addCode("return $T.$L.get($T.minecraft($N));", Registry.class, "STRUCTURE_TYPE", NamespacedKey.class, keyParam)
            .returns(returnType.annotated(NOT_NULL));
        return create;
    }

    private TypeSpec.Builder keyHolderType() {
        return classBuilder(this.className)
            .addSuperinterface(Keyed.class)
            .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
            .addAnnotations(Annotations.CLASS_HEADER)
            .addJavadoc(CLASS_HEADER);
    }

    @Override
    protected TypeSpec getTypeSpec() {
        TypeSpec.Builder typeBuilder = this.keyHolderType();
        MethodSpec.Builder createMethod = this.createMethod(TypeName.get(StructureType.class));

        List<Map.Entry<ResourceKey<net.minecraft.world.level.levelgen.structure.StructureType<?>>, net.minecraft.world.level.levelgen.structure.StructureType<?>>> paths = new ArrayList<>(BuiltInRegistries.STRUCTURE_TYPE.entrySet());
        paths.sort(Comparator.comparing(o -> o.getKey().location().getPath()));

        paths.forEach(entry -> {
            ResourceKey<net.minecraft.world.level.levelgen.structure.StructureType<?>> resourceKey = entry.getKey();
            String pathKey = resourceKey.location().getPath();
            String fieldName = Formatting.formatPathAsField(pathKey);

            FieldSpec.Builder fieldBuilder = FieldSpec.builder(StructureType.class, fieldName, PUBLIC, STATIC, FINAL)
                .initializer("$N($S)", createMethod.build(), pathKey)
                .addJavadoc(Javadocs.getVersionDependentField("{@code $L}"), resourceKey.location().toString());

            typeBuilder.addField(fieldBuilder.build());
        });

        return typeBuilder.addMethod(createMethod.build()).build();
    }

    @Override
    protected JavaFile.Builder file(JavaFile.Builder builder) {
        return builder
            .skipJavaLangImports(true);
    }

}
