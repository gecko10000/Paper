package io.papermc.generator.rewriter.types;

import com.google.common.base.Suppliers;
import io.papermc.generator.Main;
import io.papermc.generator.rewriter.SearchMetadata;
import io.papermc.generator.rewriter.SearchReplaceRewriter;
import io.papermc.generator.rewriter.utils.Annotations;
import io.papermc.generator.utils.Formatting;
import io.papermc.generator.utils.RegistryUtils;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.flag.FeatureElement;
import net.minecraft.world.flag.FeatureFlags;
import org.bukkit.NamespacedKey;
import org.checkerframework.checker.nullness.qual.Nullable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class RegistryFieldRewriter<T, A> extends SearchReplaceRewriter {

    private static final Map<Class<?>, String> REGISTRY_FIELD_NAMES;
    static {
        final Map<Class<?>, String> map = new IdentityHashMap<>();
        for (final Field field : org.bukkit.Registry.class.getDeclaredFields()) {
            if (field.isAnnotationPresent(Deprecated.class) || field.getType() != org.bukkit.Registry.class) {
                continue;
            }

            int mod = field.getModifiers();
            if (Modifier.isPublic(mod) & Modifier.isStatic(mod) & Modifier.isFinal(mod)) {
                map.put((Class<?>) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0], field.getName());
            }
        }
        REGISTRY_FIELD_NAMES = Map.copyOf(map);
    }

    private final Registry<T> registry;
    private final Supplier<List<ResourceKey<T>>> experimentalKeys;
    private final boolean isFilteredRegistry;
    private final String fetchMethod;
    private final boolean isInterface;

    public RegistryFieldRewriter(final Class<A> rewriteClass, final ResourceKey<? extends Registry<T>> registryKey, final String pattern, final @Nullable String fetchMethod) {
        super(rewriteClass, pattern, false);
        this.registry = Main.REGISTRY_ACCESS.registryOrThrow(registryKey);
        this.experimentalKeys = Suppliers.memoize(() -> RegistryUtils.collectExperimentalKeys(this.registry));
        this.isFilteredRegistry = FeatureElement.FILTERED_REGISTRIES.contains(registryKey);
        this.isInterface = rewriteClass.isInterface();
        this.fetchMethod = fetchMethod;
    }

    @Override
    protected void insert(final SearchMetadata metadata, final StringBuilder builder) {
        List<Holder.Reference<T>> references = this.registry.holders().sorted(Formatting.alphabeticOrder(reference -> reference.key().location().getPath())).toList();
        Iterator<Holder.Reference<T>> referenceIterator = references.iterator();

        while (referenceIterator.hasNext()) {
            Holder.Reference<T> reference = referenceIterator.next();
            ResourceKey<T> resourceKey = reference.key();
            String pathKey = resourceKey.location().getPath();

            String fieldName = Formatting.formatPathAsField(pathKey);
            String experimentalValue = this.getExperimentalValue(reference);
            if (experimentalValue != null) {
                Annotations.experimentalAnnotations(builder, metadata, experimentalValue);
            }

            builder.append(metadata.indent());
            if (!this.isInterface) {
                builder.append("public static final ");
            }
            builder.append(this.rewriteClass.getSimpleName()).append(' ').append(fieldName);
            builder.append(" = ");
            if (this.fetchMethod == null) {
                builder.append("%s.%s.get(%s.minecraft(\"%s\"))".formatted(org.bukkit.Registry.class.getSimpleName(), REGISTRY_FIELD_NAMES.get(this.rewriteClass), NamespacedKey.class.getSimpleName(), pathKey));
            } else {
                builder.append("%s(\"%s\")".formatted(this.fetchMethod, pathKey)); // todo search with reflection or smth?
            }
            builder.append(';');

            builder.append('\n');
            if (referenceIterator.hasNext()) {
                builder.append('\n');
            }
        }
    }

    @Nullable
    protected String getExperimentalValue(Holder.Reference<T> reference) {
        if (this.isFilteredRegistry && reference.value() instanceof FeatureElement element && FeatureFlags.isExperimental(element.requiredFeatures())) {
            return Formatting.formatFeatureFlagSet(element.requiredFeatures());
        }
        if (this.experimentalKeys.get().contains(reference.key())) {
            return Formatting.formatFeatureFlag(FeatureFlags.UPDATE_1_21);
        }
        return null;
    }
}
