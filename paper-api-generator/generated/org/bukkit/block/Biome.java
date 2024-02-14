package org.bukkit.block;

import java.util.Locale;
import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

/**
 * Holds all accepted Biomes in the default server
 */
public enum Biome implements Keyed, net.kyori.adventure.translation.Translatable { // Paper
    // Paper start - Generated/Biome
    // @GeneratedFrom 1.20.4
    BADLANDS,
    BAMBOO_JUNGLE,
    BASALT_DELTAS,
    BEACH,
    BIRCH_FOREST,
    CHERRY_GROVE,
    COLD_OCEAN,
    CRIMSON_FOREST,
    DARK_FOREST,
    DEEP_COLD_OCEAN,
    DEEP_DARK,
    DEEP_FROZEN_OCEAN,
    DEEP_LUKEWARM_OCEAN,
    DEEP_OCEAN,
    DESERT,
    DRIPSTONE_CAVES,
    END_BARRENS,
    END_HIGHLANDS,
    END_MIDLANDS,
    ERODED_BADLANDS,
    FLOWER_FOREST,
    FOREST,
    FROZEN_OCEAN,
    FROZEN_PEAKS,
    FROZEN_RIVER,
    GROVE,
    ICE_SPIKES,
    JAGGED_PEAKS,
    JUNGLE,
    LUKEWARM_OCEAN,
    LUSH_CAVES,
    MANGROVE_SWAMP,
    MEADOW,
    MUSHROOM_FIELDS,
    NETHER_WASTES,
    OCEAN,
    OLD_GROWTH_BIRCH_FOREST,
    OLD_GROWTH_PINE_TAIGA,
    OLD_GROWTH_SPRUCE_TAIGA,
    PLAINS,
    RIVER,
    SAVANNA,
    SAVANNA_PLATEAU,
    SMALL_END_ISLANDS,
    SNOWY_BEACH,
    SNOWY_PLAINS,
    SNOWY_SLOPES,
    SNOWY_TAIGA,
    SOUL_SAND_VALLEY,
    SPARSE_JUNGLE,
    STONY_PEAKS,
    STONY_SHORE,
    SUNFLOWER_PLAINS,
    SWAMP,
    TAIGA,
    THE_END,
    THE_VOID,
    WARM_OCEAN,
    WARPED_FOREST,
    WINDSWEPT_FOREST,
    WINDSWEPT_GRAVELLY_HILLS,
    WINDSWEPT_HILLS,
    WINDSWEPT_SAVANNA,
    WOODED_BADLANDS,
    // Paper end - Generated/Biome
    /**
     * Represents a custom Biome
     */
    CUSTOM;

    private final NamespacedKey key;

    private Biome() {
        this.key = NamespacedKey.minecraft(name().toLowerCase(Locale.ROOT));
    }

    @NotNull
    @Override
    public NamespacedKey getKey() {
        return key;
    }

    // Paper start
    @Override
    public @NotNull String translationKey() {
        return "biome.minecraft." + this.key.getKey();
    }
    // Paper end
}
