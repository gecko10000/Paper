package org.bukkit.block;

import io.papermc.paper.generated.GeneratedFrom;
import net.kyori.adventure.translation.Translatable;
import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

/**
 * Vanilla keys for Biomes.
 *
 * @apiNote The fields provided here are a direct representation of
 * what is available from the vanilla game source. They may be
 * changed (including removals) on any Minecraft version
 * bump, so cross-version compatibility is not provided on the
 * same level as it is on most of the other API.
 */
@SuppressWarnings({
        "unused",
        "SpellCheckingInspection"
})
@GeneratedFrom("1.20.4")
public enum Biome implements Keyed, Translatable {
    BADLANDS("badlands"),

    BAMBOO_JUNGLE("bamboo_jungle"),

    BASALT_DELTAS("basalt_deltas"),

    BEACH("beach"),

    BIRCH_FOREST("birch_forest"),

    CHERRY_GROVE("cherry_grove"),

    COLD_OCEAN("cold_ocean"),

    CRIMSON_FOREST("crimson_forest"),

    DARK_FOREST("dark_forest"),

    DEEP_COLD_OCEAN("deep_cold_ocean"),

    DEEP_DARK("deep_dark"),

    DEEP_FROZEN_OCEAN("deep_frozen_ocean"),

    DEEP_LUKEWARM_OCEAN("deep_lukewarm_ocean"),

    DEEP_OCEAN("deep_ocean"),

    DESERT("desert"),

    DRIPSTONE_CAVES("dripstone_caves"),

    END_BARRENS("end_barrens"),

    END_HIGHLANDS("end_highlands"),

    END_MIDLANDS("end_midlands"),

    ERODED_BADLANDS("eroded_badlands"),

    FLOWER_FOREST("flower_forest"),

    FOREST("forest"),

    FROZEN_OCEAN("frozen_ocean"),

    FROZEN_PEAKS("frozen_peaks"),

    FROZEN_RIVER("frozen_river"),

    GROVE("grove"),

    ICE_SPIKES("ice_spikes"),

    JAGGED_PEAKS("jagged_peaks"),

    JUNGLE("jungle"),

    LUKEWARM_OCEAN("lukewarm_ocean"),

    LUSH_CAVES("lush_caves"),

    MANGROVE_SWAMP("mangrove_swamp"),

    MEADOW("meadow"),

    MUSHROOM_FIELDS("mushroom_fields"),

    NETHER_WASTES("nether_wastes"),

    OCEAN("ocean"),

    OLD_GROWTH_BIRCH_FOREST("old_growth_birch_forest"),

    OLD_GROWTH_PINE_TAIGA("old_growth_pine_taiga"),

    OLD_GROWTH_SPRUCE_TAIGA("old_growth_spruce_taiga"),

    PLAINS("plains"),

    RIVER("river"),

    SAVANNA("savanna"),

    SAVANNA_PLATEAU("savanna_plateau"),

    SMALL_END_ISLANDS("small_end_islands"),

    SNOWY_BEACH("snowy_beach"),

    SNOWY_PLAINS("snowy_plains"),

    SNOWY_SLOPES("snowy_slopes"),

    SNOWY_TAIGA("snowy_taiga"),

    SOUL_SAND_VALLEY("soul_sand_valley"),

    SPARSE_JUNGLE("sparse_jungle"),

    STONY_PEAKS("stony_peaks"),

    STONY_SHORE("stony_shore"),

    SUNFLOWER_PLAINS("sunflower_plains"),

    SWAMP("swamp"),

    TAIGA("taiga"),

    THE_END("the_end"),

    THE_VOID("the_void"),

    WARM_OCEAN("warm_ocean"),

    WARPED_FOREST("warped_forest"),

    WINDSWEPT_FOREST("windswept_forest"),

    WINDSWEPT_GRAVELLY_HILLS("windswept_gravelly_hills"),

    WINDSWEPT_HILLS("windswept_hills"),

    WINDSWEPT_SAVANNA("windswept_savanna"),

    WOODED_BADLANDS("wooded_badlands"),

    CUSTOM("custom");

    private final NamespacedKey key;

    Biome(String key) {
        this.key = NamespacedKey.minecraft(key);
    }

    @NotNull
    @Override
    public NamespacedKey getKey() {
        return this.key;
    }

    @NotNull
    @Override
    public String translationKey() {
        return "biome.minecraft." + this.key.getKey();
    }
}
