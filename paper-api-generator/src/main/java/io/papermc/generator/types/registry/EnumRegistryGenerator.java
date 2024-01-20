package io.papermc.generator.types.registry;

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
import javax.lang.model.element.Modifier;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;

@DefaultQualifier(NonNull.class)
public abstract class EnumRegistryGenerator<T> extends SimpleGenerator {

    protected final ResourceKey<Registry<T>> registryResourceKey;

    public EnumRegistryGenerator(final String keysClassName, final String pkg, ResourceKey<Registry<T>> registryResourceKey) {
        super(keysClassName, pkg);
        this.registryResourceKey = registryResourceKey;
    }

    @Override
    protected TypeSpec getTypeSpec() {
        TypeSpec.Builder typeBuilder = TypeSpec.enumBuilder(this.className)
            .addSuperinterface(Keyed.class)
            .addModifiers(Modifier.PUBLIC)
            .addAnnotations(Annotations.CLASS_HEADER);

        Registry<T> event = Main.REGISTRY_ACCESS.registryOrThrow(this.registryResourceKey);
        List<Map.Entry<ResourceKey<T>, T>> paths = new ArrayList<>(event.entrySet());
        paths.sort(Comparator.comparing(o -> o.getKey().location().getPath()));

        paths.forEach(entry -> {
            ResourceKey<T> resourceKey = entry.getKey();
            String pathKey = resourceKey.location().getPath();

            String fieldName = Formatting.formatPathAsField(pathKey);
            boolean isExperimental = this.isExperimental(entry);
            TypeSpec.Builder builder = TypeSpec.anonymousClassBuilder("$S", pathKey);
            if (isExperimental) {
                builder.addAnnotations(Annotations.experimentalAnnotations(""));
            }

            typeBuilder.addEnumConstant(fieldName, builder.build());
        });

        FieldSpec keyField = FieldSpec.builder(NamespacedKey.class, "key", Modifier.PRIVATE).build();
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

        this.addExtras(typeBuilder);

        return typeBuilder.build();
    }

    public abstract void addExtras(TypeSpec.Builder builder);

    @Override
    protected JavaFile.Builder file(JavaFile.Builder builder) {
        return builder
            .skipJavaLangImports(true);
    }

    public abstract boolean isExperimental(Map.Entry<ResourceKey<T>, T> entry);

}
