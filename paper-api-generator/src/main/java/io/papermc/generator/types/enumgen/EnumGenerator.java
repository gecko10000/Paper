package io.papermc.generator.types.enumgen;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import io.papermc.generator.Main;
import io.papermc.generator.types.SimpleGenerator;
import io.papermc.generator.utils.Annotations;
import io.papermc.generator.utils.Formatting;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import javax.lang.model.element.Modifier;
import net.kyori.adventure.key.Key;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;

@DefaultQualifier(NonNull.class)
public abstract class EnumGenerator<T> extends SimpleGenerator {

    protected final ResourceKey<Registry<T>> registryResourceKey;

    public EnumGenerator(final String keysClassName, final String pkg, ResourceKey<Registry<T>> registryResourceKey) {
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
            Key bukkitKey = Key.key(resourceKey.location().toString());

            String fieldName = Formatting.formatKeyAsField(bukkitKey);
            boolean isExperiemental = this.isExperiemental(entry);
            TypeSpec.Builder builder = TypeSpec.anonymousClassBuilder("$S", resourceKey.location().getPath().toString());
            if (isExperiemental) {
                builder.addAnnotations(Annotations.experimentalAnnotations(""));
            }

            typeBuilder.addEnumConstant(fieldName, builder.build());
        });

        typeBuilder.addField(FieldSpec.builder(NamespacedKey.class, "key", Modifier.PRIVATE).build());
        typeBuilder.addMethod(MethodSpec.constructorBuilder()
            .addParameter(String.class, "key").addCode("this.key = NamespacedKey.minecraft(key);").build());

        typeBuilder.addMethod(MethodSpec.methodBuilder("getKey")
            .returns(NamespacedKey.class)
            .addModifiers(Modifier.PUBLIC)
            .addAnnotation(Annotations.NOT_NULL)
            .addAnnotation(Annotations.OVERRIDE)
            .addCode("return this.key;").build());

        this.addExtras(typeBuilder);

        return typeBuilder.build();
    }

    public abstract void addExtras(TypeSpec.Builder builder);

    @Override
    protected JavaFile.Builder file(JavaFile.Builder builder) {
        return builder
            .skipJavaLangImports(true);
    }

    public abstract boolean isExperiemental(Map.Entry<ResourceKey<T>, T> entry);

    private static List<Pattern> of(String... strings) {
        List<Pattern> patterns = new ArrayList<>();
        for (String pattern : strings) {
            patterns.add(Pattern.compile(pattern));
        }

        return patterns;
    }
}
