package org.bukkit.generator.structure;

import io.papermc.paper.generated.GeneratedFrom;
import org.bukkit.Keyed;
import org.bukkit.MinecraftExperimental;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * Vanilla keys for Structures.
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
public abstract class Structure implements Keyed {
    /**
     * {@code minecraft:ancient_city}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    public static final Structure ANCIENT_CITY = fetch("ancient_city");

    /**
     * {@code minecraft:bastion_remnant}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    public static final Structure BASTION_REMNANT = fetch("bastion_remnant");

    /**
     * {@code minecraft:buried_treasure}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    public static final Structure BURIED_TREASURE = fetch("buried_treasure");

    /**
     * {@code minecraft:desert_pyramid}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    public static final Structure DESERT_PYRAMID = fetch("desert_pyramid");

    /**
     * {@code minecraft:end_city}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    public static final Structure END_CITY = fetch("end_city");

    /**
     * {@code minecraft:fortress}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    public static final Structure FORTRESS = fetch("fortress");

    /**
     * {@code minecraft:igloo}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    public static final Structure IGLOO = fetch("igloo");

    /**
     * {@code minecraft:jungle_pyramid}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    public static final Structure JUNGLE_PYRAMID = fetch("jungle_pyramid");

    /**
     * {@code minecraft:mansion}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    public static final Structure MANSION = fetch("mansion");

    /**
     * {@code minecraft:mineshaft}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    public static final Structure MINESHAFT = fetch("mineshaft");

    /**
     * {@code minecraft:mineshaft_mesa}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    public static final Structure MINESHAFT_MESA = fetch("mineshaft_mesa");

    /**
     * {@code minecraft:monument}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    public static final Structure MONUMENT = fetch("monument");

    /**
     * {@code minecraft:nether_fossil}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    public static final Structure NETHER_FOSSIL = fetch("nether_fossil");

    /**
     * {@code minecraft:ocean_ruin_cold}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    public static final Structure OCEAN_RUIN_COLD = fetch("ocean_ruin_cold");

    /**
     * {@code minecraft:ocean_ruin_warm}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    public static final Structure OCEAN_RUIN_WARM = fetch("ocean_ruin_warm");

    /**
     * {@code minecraft:pillager_outpost}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    public static final Structure PILLAGER_OUTPOST = fetch("pillager_outpost");

    /**
     * {@code minecraft:ruined_portal}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    public static final Structure RUINED_PORTAL = fetch("ruined_portal");

    /**
     * {@code minecraft:ruined_portal_desert}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    public static final Structure RUINED_PORTAL_DESERT = fetch("ruined_portal_desert");

    /**
     * {@code minecraft:ruined_portal_jungle}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    public static final Structure RUINED_PORTAL_JUNGLE = fetch("ruined_portal_jungle");

    /**
     * {@code minecraft:ruined_portal_mountain}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    public static final Structure RUINED_PORTAL_MOUNTAIN = fetch("ruined_portal_mountain");

    /**
     * {@code minecraft:ruined_portal_nether}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    public static final Structure RUINED_PORTAL_NETHER = fetch("ruined_portal_nether");

    /**
     * {@code minecraft:ruined_portal_ocean}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    public static final Structure RUINED_PORTAL_OCEAN = fetch("ruined_portal_ocean");

    /**
     * {@code minecraft:ruined_portal_swamp}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    public static final Structure RUINED_PORTAL_SWAMP = fetch("ruined_portal_swamp");

    /**
     * {@code minecraft:shipwreck}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    public static final Structure SHIPWRECK = fetch("shipwreck");

    /**
     * {@code minecraft:shipwreck_beached}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    public static final Structure SHIPWRECK_BEACHED = fetch("shipwreck_beached");

    /**
     * {@code minecraft:stronghold}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    public static final Structure STRONGHOLD = fetch("stronghold");

    /**
     * {@code minecraft:swamp_hut}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    public static final Structure SWAMP_HUT = fetch("swamp_hut");

    /**
     * {@code minecraft:trail_ruins}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    public static final Structure TRAIL_RUINS = fetch("trail_ruins");

    /**
     * {@code minecraft:trial_chambers}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    @ApiStatus.Experimental
    @MinecraftExperimental("update 1.21")
    public static final Structure TRIAL_CHAMBERS = fetch("trial_chambers");

    /**
     * {@code minecraft:village_desert}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    public static final Structure VILLAGE_DESERT = fetch("village_desert");

    /**
     * {@code minecraft:village_plains}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    public static final Structure VILLAGE_PLAINS = fetch("village_plains");

    /**
     * {@code minecraft:village_savanna}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    public static final Structure VILLAGE_SAVANNA = fetch("village_savanna");

    /**
     * {@code minecraft:village_snowy}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    public static final Structure VILLAGE_SNOWY = fetch("village_snowy");

    /**
     * {@code minecraft:village_taiga}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    public static final Structure VILLAGE_TAIGA = fetch("village_taiga");

    private static @NotNull Structure fetch(final @NotNull String key) {
        return Registry.STRUCTURE.get(NamespacedKey.minecraft(key));
    }

    /**
     * Returns the type of the structure.
     *
     * @return the type of structure
     */
    @NotNull
    public abstract Structure getStructureType();
}
