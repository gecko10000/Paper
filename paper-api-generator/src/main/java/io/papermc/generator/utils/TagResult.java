package io.papermc.generator.utils;

import com.google.common.collect.Multimap;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import java.util.Map;

public record TagResult(Map<TagKey<?>, String> perFeatureFlag, Multimap<ResourceKey<? extends Registry<?>>, String> perRegistry) {}
