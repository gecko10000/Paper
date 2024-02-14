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
import org.checkerframework.checker.nullness.qual.Nullable;
import java.util.List;
import java.util.function.Supplier;

public class EnumRegistryRewriter<T, A extends Enum<A>> extends SearchReplaceRewriter {

    private final net.minecraft.core.Registry<T> registry;
    private final Supplier<List<ResourceKey<T>>> experimentalKeys;
    private final boolean isFilteredRegistry;
    private final boolean keyedParam;

    public EnumRegistryRewriter(final Class<A> rewriteClass, final ResourceKey<? extends Registry<T>> registryKey, final String pattern, final boolean keyedParam) {
        super(rewriteClass, pattern, false);
        this.registry = Main.REGISTRY_ACCESS.registryOrThrow(registryKey);
        this.experimentalKeys = Suppliers.memoize(() -> RegistryUtils.collectExperimentalKeys(this.registry));
        this.isFilteredRegistry = FeatureElement.FILTERED_REGISTRIES.contains(registryKey);
        this.keyedParam = keyedParam;
    }

    @Override
    public void insert(final SearchMetadata metadata, final StringBuilder builder) {
        boolean reachEnd = metadata.replacedContent().stripTrailing().endsWith(";");

        List<Holder.Reference<T>> references = this.registry.holders().sorted(Formatting.alphabeticOrder(reference -> reference.key().location().getPath())).toList();
        for (int i = 0, size = references.size(); i < size; i++) {
            Holder.Reference<T> reference = references.get(i);
            ResourceKey<T> resourceKey = reference.key();
            String pathKey = resourceKey.location().getPath();

            String fieldName = this.rewriteFieldName(reference);
            String experimentalValue = this.getExperimentalValue(reference);
            if (experimentalValue != null) {
                Annotations.experimentalAnnotations(builder, metadata::indent, experimentalValue);
            }

            builder.append(metadata.indent()).append(fieldName);
            if (this.keyedParam) {
                builder.append("(\"%s\")".formatted(pathKey));
            }
            if (reachEnd && i == size - 1) {
                builder.append(';');
            } else {
                builder.append(',');
            }
            builder.append('\n');
        }
    }

    public String rewriteFieldName(Holder.Reference<T> reference) {
        return Formatting.formatPathAsField(reference.key().location().getPath());
    }

    @Nullable
    public String getExperimentalValue(Holder.Reference<T> reference) {
        if (this.isFilteredRegistry && reference.value() instanceof FeatureElement element && FeatureFlags.isExperimental(element.requiredFeatures())) {
            return Formatting.formatFeatureFlagSet(element.requiredFeatures());
        }
        if (this.experimentalKeys.get().contains(reference.key())) {
            return Formatting.formatFeatureFlag(FeatureFlags.UPDATE_1_21);
        }
        return null;
    }
}
