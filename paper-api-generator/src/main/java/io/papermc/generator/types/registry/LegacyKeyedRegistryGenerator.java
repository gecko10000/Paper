package io.papermc.generator.types.registry;

import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import io.papermc.generator.utils.Annotations;
import io.papermc.generator.utils.Javadocs;
import io.papermc.generator.utils.RegistryUtils;
import io.papermc.paper.registry.RegistryKey;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;

import static io.papermc.generator.utils.Annotations.NOT_NULL;
import static io.papermc.generator.utils.Annotations.OVERRIDE;
import static java.util.Objects.requireNonNull;
import static javax.lang.model.element.Modifier.ABSTRACT;
import static javax.lang.model.element.Modifier.PUBLIC;

@DefaultQualifier(NonNull.class)
public class LegacyKeyedRegistryGenerator<T, A> extends RegistryGenerator<T, A> {

    private static final String GET_KEY_JAVADOC = """
        @deprecated use {@link $T#getKey($T)} and {@link $T#$L}. $L
        can exist without a key.
        """;

    public LegacyKeyedRegistryGenerator(final String className, final String pkg, final ResourceKey<? extends Registry<T>> registryKey, final RegistryKey<A> apiRegistryKey) {
        super(className, pkg, registryKey, apiRegistryKey, true);
    }

    @Override
    public void addExtras(final TypeSpec.Builder builder) {
        builder.addJavadoc(Javadocs.getVersionDependentClassHeader(this.className));

        builder.addMethod(MethodSpec.methodBuilder("getKey")
            .addModifiers(PUBLIC, ABSTRACT)
            .returns(NamespacedKey.class)
            .addJavadoc(GET_KEY_JAVADOC, org.bukkit.Registry.class, Keyed.class, org.bukkit.Registry.class, requireNonNull(RegistryUtils.REGISTRY_KEY_FIELD_NAMES.get(this.apiRegistryKey), "Missing field for " + this.apiRegistryKey), this.className)
            .addAnnotation(Annotations.deprecatedVersioned("1.20.4", true))
            .addAnnotation(OVERRIDE)
            .addAnnotation(NOT_NULL).build());
    }

}
