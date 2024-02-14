package org.bukkit.attribute;

import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

/**
 * Types of attributes which may be present on an {@link Attributable}.
 */
public enum Attribute implements Keyed, net.kyori.adventure.translation.Translatable { // Paper - Adventure translations

    // Paper start - Generated/Attribute
    // @GeneratedFrom 1.20.4
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
    // Paper end - Generated/Attribute

    private final NamespacedKey key;

    private Attribute(String key) {
        this.key = NamespacedKey.minecraft(key);
    }

    @NotNull
    @Override
    public NamespacedKey getKey() {
        return key;
    }
    // Paper start
    @Override
    public @NotNull String translationKey() {
        return "attribute.name." + this.key.getKey();
    }
    // Paper end
}
