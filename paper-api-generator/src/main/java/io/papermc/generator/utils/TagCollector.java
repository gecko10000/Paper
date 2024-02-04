package io.papermc.generator.utils;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.mojang.logging.LogUtils;
import io.papermc.generator.Main;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.BuiltInPackSource;
import net.minecraft.server.packs.resources.MultiPackResourceManager;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.TagManager;
import org.slf4j.Logger;

// collect all the tags by grabbing the json from the datapacks
// another (probably) way is to hook into the data generator like the key generator
public final class TagCollector {

    private static final Logger LOGGER = LogUtils.getLogger();

    private static ResourceLocation minecraft(String path) {
        return new ResourceLocation(ResourceLocation.DEFAULT_NAMESPACE, path);
    }

    public static TagResult grabExperimental(final MultiPackResourceManager resourceManager) {
        Map<TagKey<?>, String> perFeatureFlag = new HashMap<>();
        ImmutableMultimap.Builder<ResourceKey<? extends Registry<?>>, String> perRegistry = ImmutableMultimap.builder();

        // first collect all vanilla tags
        Multimap<ResourceKey<? extends Registry<?>>, String> vanillaTags = HashMultimap.create();
        resourceManager.listPacks().filter(packResources -> packResources.packId().equals(BuiltInPackSource.VANILLA_ID)).forEach(pack -> {
            collectFromPack(pack, vanillaTags::put);
        });

        // then distinct with other datapack tags to know for sure newly created tags and so experimental one
        resourceManager.listPacks().forEach(pack -> {
            String packId = pack.packId();
            if (packId.equals(BuiltInPackSource.VANILLA_ID)) return;
            collectFromPack(pack, (registryKey, path) -> {
                if (vanillaTags.get(registryKey).contains(path)) {
                    return;
                }

                perFeatureFlag.put(TagKey.create((ResourceKey<? extends Registry<Object>>) registryKey, minecraft(path)), packId);
                perRegistry.put(registryKey, path);
            });
        });
        return new TagResult(perFeatureFlag, perRegistry.build());
    }

    private static void collectFromPack(PackResources pack, BiConsumer<ResourceKey<? extends Registry<?>>, String> output) {
        Set<String> namespaces = pack.getNamespaces(PackType.SERVER_DATA);

        for (String namespace : namespaces) {
            Main.REGISTRY_ACCESS.registries().forEach(entry -> {
                // this is probably expensive but can't find another way around and datapack loader has similar logic
                // the issue is that registry key can have parent/key (and custom folder too) but tag key can also have parent/key so parsing become a mess
                // without having at least one of the two values
                String tagDir = TagManager.getTagDir(entry.key());
                pack.listResources(PackType.SERVER_DATA, namespace, tagDir, (id, supplier) -> {
                    Formatting.formatTagKey(tagDir, id.getPath()).ifPresentOrElse(path -> output.accept(entry.key(), path), () -> {
                        LOGGER.warn("Unable to parse the path: {}/{}/{}.json in the datapack {} into a tag key", namespace, tagDir, id.getPath(), pack.packId());
                    });
                });
            });
        }
    }

    private TagCollector() {

    }
}
