package org.bukkit.inventory.meta.trim;

import io.papermc.paper.generated.GeneratedFrom;
import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.jetbrains.annotations.NotNull;

/**
 * Vanilla keys for TrimPattern.
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
public interface TrimPattern extends Keyed {
    /**
     * {@code minecraft:coast}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    TrimPattern COAST = Registry.TRIM_PATTERN.get(NamespacedKey.minecraft("coast"));

    /**
     * {@code minecraft:dune}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    TrimPattern DUNE = Registry.TRIM_PATTERN.get(NamespacedKey.minecraft("dune"));

    /**
     * {@code minecraft:eye}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    TrimPattern EYE = Registry.TRIM_PATTERN.get(NamespacedKey.minecraft("eye"));

    /**
     * {@code minecraft:host}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    TrimPattern HOST = Registry.TRIM_PATTERN.get(NamespacedKey.minecraft("host"));

    /**
     * {@code minecraft:raiser}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    TrimPattern RAISER = Registry.TRIM_PATTERN.get(NamespacedKey.minecraft("raiser"));

    /**
     * {@code minecraft:rib}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    TrimPattern RIB = Registry.TRIM_PATTERN.get(NamespacedKey.minecraft("rib"));

    /**
     * {@code minecraft:sentry}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    TrimPattern SENTRY = Registry.TRIM_PATTERN.get(NamespacedKey.minecraft("sentry"));

    /**
     * {@code minecraft:shaper}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    TrimPattern SHAPER = Registry.TRIM_PATTERN.get(NamespacedKey.minecraft("shaper"));

    /**
     * {@code minecraft:silence}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    TrimPattern SILENCE = Registry.TRIM_PATTERN.get(NamespacedKey.minecraft("silence"));

    /**
     * {@code minecraft:snout}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    TrimPattern SNOUT = Registry.TRIM_PATTERN.get(NamespacedKey.minecraft("snout"));

    /**
     * {@code minecraft:spire}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    TrimPattern SPIRE = Registry.TRIM_PATTERN.get(NamespacedKey.minecraft("spire"));

    /**
     * {@code minecraft:tide}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    TrimPattern TIDE = Registry.TRIM_PATTERN.get(NamespacedKey.minecraft("tide"));

    /**
     * {@code minecraft:vex}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    TrimPattern VEX = Registry.TRIM_PATTERN.get(NamespacedKey.minecraft("vex"));

    /**
     * {@code minecraft:ward}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    TrimPattern WARD = Registry.TRIM_PATTERN.get(NamespacedKey.minecraft("ward"));

    /**
     * {@code minecraft:wayfinder}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    TrimPattern WAYFINDER = Registry.TRIM_PATTERN.get(NamespacedKey.minecraft("wayfinder"));

    /**
     * {@code minecraft:wild}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    TrimPattern WILD = Registry.TRIM_PATTERN.get(NamespacedKey.minecraft("wild"));

    /**
     * @deprecated use {@link Registry#getKey(Keyed)} and {@link Registry#TRIM_PATTERN}. TrimPattern
     * can exist without a key.
     */
    @Deprecated(
            forRemoval = true,
            since = "1.20.4"
    )
    @Override
    @NotNull
    NamespacedKey getKey();
}
