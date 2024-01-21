package org.bukkit.inventory.meta.trim;

import io.papermc.paper.generated.GeneratedFrom;
import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.jetbrains.annotations.NotNull;

/**
 * Vanilla keys for TrimMaterial.
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
public interface TrimMaterial extends Keyed {
    /**
     * {@code minecraft:amethyst}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    TrimMaterial AMETHYST = Registry.TRIM_MATERIAL.get(NamespacedKey.minecraft("amethyst"));

    /**
     * {@code minecraft:copper}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    TrimMaterial COPPER = Registry.TRIM_MATERIAL.get(NamespacedKey.minecraft("copper"));

    /**
     * {@code minecraft:diamond}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    TrimMaterial DIAMOND = Registry.TRIM_MATERIAL.get(NamespacedKey.minecraft("diamond"));

    /**
     * {@code minecraft:emerald}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    TrimMaterial EMERALD = Registry.TRIM_MATERIAL.get(NamespacedKey.minecraft("emerald"));

    /**
     * {@code minecraft:gold}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    TrimMaterial GOLD = Registry.TRIM_MATERIAL.get(NamespacedKey.minecraft("gold"));

    /**
     * {@code minecraft:iron}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    TrimMaterial IRON = Registry.TRIM_MATERIAL.get(NamespacedKey.minecraft("iron"));

    /**
     * {@code minecraft:lapis}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    TrimMaterial LAPIS = Registry.TRIM_MATERIAL.get(NamespacedKey.minecraft("lapis"));

    /**
     * {@code minecraft:netherite}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    TrimMaterial NETHERITE = Registry.TRIM_MATERIAL.get(NamespacedKey.minecraft("netherite"));

    /**
     * {@code minecraft:quartz}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    TrimMaterial QUARTZ = Registry.TRIM_MATERIAL.get(NamespacedKey.minecraft("quartz"));

    /**
     * {@code minecraft:redstone}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    TrimMaterial REDSTONE = Registry.TRIM_MATERIAL.get(NamespacedKey.minecraft("redstone"));

    /**
     * @deprecated use {@link Registry#getKey(Keyed)} and {@link Registry#TRIM_MATERIAL}. TrimMaterial
     * can exist without a key.
     */
    @Deprecated(
            forRemoval = true,
            since = "1.20.4"
    )
    @Override
    @NotNull
    TrimMaterial getKey();
}
