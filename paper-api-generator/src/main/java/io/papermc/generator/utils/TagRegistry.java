package io.papermc.generator.utils;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

public record TagRegistry(String name, Class<?> apiType, ResourceKey<? extends Registry<?>> registryKey) {

    public static TagRegistry registry(final String name, final Class<?> apiType, final ResourceKey<? extends Registry<?>> registryKey) {
        return new TagRegistry(name, apiType, registryKey);
    }
}
