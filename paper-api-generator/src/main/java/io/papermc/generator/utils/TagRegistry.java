package io.papermc.generator.utils;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

public record TagRegistry(String name, Class<?> apiType, ResourceKey<? extends Registry<?>> registryKey) {}
