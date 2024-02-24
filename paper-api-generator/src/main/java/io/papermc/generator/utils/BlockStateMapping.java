package io.papermc.generator.utils;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class BlockStateMapping {

    public record BlockData(String impl, String api) {
    }

    public static final Map<Class<?>, BlockData> MAPPING;
    public static final Set<Class<?>> SPECIAL_BLOCKS;
    static {
        Map<Class<?>, BlockData> map = new HashMap<>();
        Set<Class<?>> specialBlocks = new HashSet<>();
        try {
            for (Block block : BuiltInRegistries.BLOCK) {
                if (!block.getStateDefinition().getProperties().isEmpty()) {
                    specialBlocks.add(block.getClass());
                }
            }
            SPECIAL_BLOCKS = Set.copyOf(specialBlocks);
            for (Class<?> specialBlock : specialBlocks) {
                /*
                String impl = String.format("Craft%s", specialBlock.getSimpleName().substring(0, specialBlock.getSimpleName().length() - "Block".length()));
                map.put(specialBlock, new BlockData(
                    impl,
                    Class.forName("org.bukkit.craftbukkit.block.impl.%s".formatted(impl)).getInterfaces()[0].getSimpleName()
                    // todo this won't work for server generation (CraftBlockData mapping)
                    // need to reorganize the module for server gen since this class ^ is not accessible
                ));*/
            }
            MAPPING = Map.copyOf(map);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
