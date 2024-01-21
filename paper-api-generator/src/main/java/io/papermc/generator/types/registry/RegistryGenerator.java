package io.papermc.generator.types.registry;

import com.google.common.base.Suppliers;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import io.papermc.generator.types.SimpleGenerator;
import io.papermc.generator.utils.Annotations;
import io.papermc.generator.utils.Formatting;
import io.papermc.generator.utils.Javadocs;
import io.papermc.generator.utils.RegistryUtils;
import io.papermc.paper.registry.RegistryKey;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.DefaultQualifier;

import static com.squareup.javapoet.TypeSpec.classBuilder;
import static com.squareup.javapoet.TypeSpec.interfaceBuilder;
import static io.papermc.generator.utils.Annotations.NOT_NULL;
import static java.util.Objects.requireNonNull;
import static javax.lang.model.element.Modifier.ABSTRACT;
import static javax.lang.model.element.Modifier.FINAL;
import static javax.lang.model.element.Modifier.PRIVATE;
import static javax.lang.model.element.Modifier.PUBLIC;
import static javax.lang.model.element.Modifier.STATIC;

@DefaultQualifier(NonNull.class)
public abstract class RegistryGenerator<T, A> extends SimpleGenerator {

    protected final Class<A> apiType;
    protected final RegistryKey<A> apiRegistryKey;
    private final Supplier<Registry<T>> registry;
    private final boolean isInterface;
    private final Supplier<List<ResourceKey<T>>> experimentalKeys;

    public RegistryGenerator(final String keysClassName, final Class<A> apiType, final String pkg, final RegistryKey<A> apiRegistryKey, final boolean isInterface) {
        super(keysClassName, pkg);
        this.apiType = apiType;
        this.registry = Suppliers.memoize(this::getRegistry);
        this.apiRegistryKey = apiRegistryKey;
        this.isInterface = isInterface;
        this.experimentalKeys = Suppliers.memoize(() -> RegistryUtils.collectExperimentalKeys(this.registry.get(), this.registry.get().key())); // need to cleanup a bit here
    }

    private MethodSpec.@Nullable Builder fetchMethod(TypeName returnType) {
        if (this.isInterface) {
            return null;
        }

        final TypeName keyType = TypeName.get(String.class).annotated(NOT_NULL);

        final ParameterSpec keyParam = ParameterSpec.builder(keyType, "key", FINAL).build();
        final MethodSpec.Builder fetch = MethodSpec.methodBuilder("fetch")
            .addModifiers(PRIVATE, STATIC)
            .addParameter(keyParam)
            .addCode("return $T.$L.get($T.minecraft($N));", org.bukkit.Registry.class, requireNonNull(RegistryUtils.getRegistryKeyFieldNames().get(this.apiRegistryKey), "Missing field for " + this.apiRegistryKey), NamespacedKey.class, keyParam)
            .returns(returnType.annotated(NOT_NULL));
        return fetch;
    }

    private TypeSpec.Builder valueHolderType() {
        final TypeSpec.Builder typeBuilder;
        if (this.isInterface) {
            typeBuilder = interfaceBuilder(this.className);
        } else {
            typeBuilder = classBuilder(this.className).addModifiers(ABSTRACT); // legacy
        }

        return typeBuilder
            .addSuperinterface(Keyed.class)
            .addModifiers(PUBLIC)
            .addAnnotations(Annotations.CLASS_HEADER);
    }

    @Override
    protected TypeSpec getTypeSpec() {
        TypeSpec.Builder typeBuilder = this.valueHolderType();

        MethodSpec.@Nullable Builder fetchMethod = this.fetchMethod(TypeName.get(this.apiType));

        List<Map.Entry<ResourceKey<T>, T>> paths = new ArrayList<>(this.registry.get().entrySet());
        paths.sort(Comparator.comparing(o -> o.getKey().location().getPath()));

        String registryField = requireNonNull(RegistryUtils.getRegistryKeyFieldNames().get(this.apiRegistryKey)); // those will use the new RegistryAccess that use the registry key

        paths.forEach(entry -> {
            ResourceKey<T> resourceKey = entry.getKey();
            String pathKey = resourceKey.location().getPath();
            String fieldName = Formatting.formatPathAsField(pathKey);
            boolean isExperimental = this.isExperimental(entry);

            FieldSpec.Builder fieldBuilder = FieldSpec.builder(this.apiType, fieldName, PUBLIC, STATIC, FINAL)
                .addJavadoc(Javadocs.getVersionDependentField("{@code $L}"), resourceKey.location().toString());
            if (this.isInterface) {
                fieldBuilder.initializer("$T.$L.get($T.minecraft($S))", org.bukkit.Registry.class, registryField, NamespacedKey.class, pathKey);
            } else {
                fieldBuilder.initializer("$N($S)", fetchMethod.build(), pathKey);
            }
            if (isExperimental) {
                fieldBuilder.addAnnotations(Annotations.experimentalAnnotations("update 1.21"));
            }

            typeBuilder.addField(fieldBuilder.build());
        });

        if (fetchMethod != null) {
            typeBuilder.addMethod(fetchMethod.build());
        }

        this.addExtras(typeBuilder);

        return typeBuilder.build();
    }

    public abstract void addExtras(TypeSpec.Builder builder);

    @Override
    protected JavaFile.Builder file(JavaFile.Builder builder) {
        return builder.skipJavaLangImports(true);
    }

    public abstract Registry<T> getRegistry();

    public boolean isExperimental(Map.Entry<ResourceKey<T>, T> entry) {
        return this.experimentalKeys.get().contains(entry.getKey());
    }

}
