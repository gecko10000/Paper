package io.papermc.generator.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.registries.UpdateOneTwentyOneRegistries;
import net.minecraft.resources.ResourceKey;
import org.checkerframework.checker.nullness.qual.Nullable;

public class RegistryUtils {

    private static final Map<ResourceKey<? extends Registry<?>>, RegistrySetBuilder.RegistryBootstrap<?>> EXPERIMENTAL_REGISTRY_ENTRIES = UpdateOneTwentyOneRegistries.BUILDER.entries.stream()
        .collect(Collectors.toMap(RegistrySetBuilder.RegistryStub::key, RegistrySetBuilder.RegistryStub::bootstrap));

    @SuppressWarnings("unchecked")
    public static <T> List<ResourceKey<T>> collectExperimentalKeys(final Registry<T> registry, ResourceKey<? extends Registry<T>> registryKey) {
        final RegistrySetBuilder.@Nullable RegistryBootstrap<T> registryBootstrap = (RegistrySetBuilder.RegistryBootstrap<T>) EXPERIMENTAL_REGISTRY_ENTRIES.get(registryKey);
        if (registryBootstrap == null) {
            return Collections.emptyList();
        }
        final List<ResourceKey<T>> experimental = new ArrayList<>();
        final CollectingContext<T> context = new CollectingContext<>(experimental, registry);
        registryBootstrap.run(context);
        return experimental;
    }
}
