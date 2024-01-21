package org.bukkit.attribute;

import io.papermc.paper.generated.GeneratedFrom;
import net.kyori.adventure.translation.Translatable;
import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

/**
 * Vanilla keys for Attributes.
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
public enum Attribute implements Keyed, Translatable {
    GENERIC_ARMOR("generic.armor"),

    GENERIC_ARMOR_TOUGHNESS("generic.armor_toughness"),

    GENERIC_ATTACK_DAMAGE("generic.attack_damage"),

    GENERIC_ATTACK_KNOCKBACK("generic.attack_knockback"),

    GENERIC_ATTACK_SPEED("generic.attack_speed"),

    GENERIC_FLYING_SPEED("generic.flying_speed"),

    GENERIC_FOLLOW_RANGE("generic.follow_range"),

    GENERIC_KNOCKBACK_RESISTANCE("generic.knockback_resistance"),

    GENERIC_LUCK("generic.luck"),

    GENERIC_MAX_ABSORPTION("generic.max_absorption"),

    GENERIC_MAX_HEALTH("generic.max_health"),

    GENERIC_MOVEMENT_SPEED("generic.movement_speed"),

    HORSE_JUMP_STRENGTH("horse.jump_strength"),

    ZOMBIE_SPAWN_REINFORCEMENTS("zombie.spawn_reinforcements");

    private final NamespacedKey key;

    Attribute(String key) {
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
        return "attribute.name." + this.key.getKey();
    }
}
