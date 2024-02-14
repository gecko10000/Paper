package org.bukkit.damage;

import com.google.common.base.Preconditions;
import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.Translatable;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * Represent a type of damage that an entity can receive.
 * <p>
 * Constants in this class include the base types provided by the vanilla
 * server. Data packs are capable of registering more types of damage which may
 * be obtained through the {@link Registry#DAMAGE_TYPE}.
 *
 * @see <a href="https://minecraft.wiki/w/Damage_type">Minecraft Wiki</a>
 */
@ApiStatus.Experimental
public interface DamageType extends Keyed, Translatable {

    // Paper start - Generated/DamageType
    // @GeneratedFrom 1.20.4
    DamageType ARROW = getDamageType("arrow");

    DamageType BAD_RESPAWN_POINT = getDamageType("bad_respawn_point");

    DamageType CACTUS = getDamageType("cactus");

    DamageType CRAMMING = getDamageType("cramming");

    DamageType DRAGON_BREATH = getDamageType("dragon_breath");

    DamageType DROWN = getDamageType("drown");

    DamageType DRY_OUT = getDamageType("dry_out");

    DamageType EXPLOSION = getDamageType("explosion");

    DamageType FALL = getDamageType("fall");

    DamageType FALLING_ANVIL = getDamageType("falling_anvil");

    DamageType FALLING_BLOCK = getDamageType("falling_block");

    DamageType FALLING_STALACTITE = getDamageType("falling_stalactite");

    DamageType FIREBALL = getDamageType("fireball");

    DamageType FIREWORKS = getDamageType("fireworks");

    DamageType FLY_INTO_WALL = getDamageType("fly_into_wall");

    DamageType FREEZE = getDamageType("freeze");

    DamageType GENERIC = getDamageType("generic");

    DamageType GENERIC_KILL = getDamageType("generic_kill");

    DamageType HOT_FLOOR = getDamageType("hot_floor");

    DamageType IN_FIRE = getDamageType("in_fire");

    DamageType IN_WALL = getDamageType("in_wall");

    DamageType INDIRECT_MAGIC = getDamageType("indirect_magic");

    DamageType LAVA = getDamageType("lava");

    DamageType LIGHTNING_BOLT = getDamageType("lightning_bolt");

    DamageType MAGIC = getDamageType("magic");

    DamageType MOB_ATTACK = getDamageType("mob_attack");

    DamageType MOB_ATTACK_NO_AGGRO = getDamageType("mob_attack_no_aggro");

    DamageType MOB_PROJECTILE = getDamageType("mob_projectile");

    DamageType ON_FIRE = getDamageType("on_fire");

    DamageType OUT_OF_WORLD = getDamageType("out_of_world");

    DamageType OUTSIDE_BORDER = getDamageType("outside_border");

    DamageType PLAYER_ATTACK = getDamageType("player_attack");

    DamageType PLAYER_EXPLOSION = getDamageType("player_explosion");

    DamageType SONIC_BOOM = getDamageType("sonic_boom");

    DamageType STALAGMITE = getDamageType("stalagmite");

    DamageType STARVE = getDamageType("starve");

    DamageType STING = getDamageType("sting");

    DamageType SWEET_BERRY_BUSH = getDamageType("sweet_berry_bush");

    DamageType THORNS = getDamageType("thorns");

    DamageType THROWN = getDamageType("thrown");

    DamageType TRIDENT = getDamageType("trident");

    DamageType UNATTRIBUTED_FIREBALL = getDamageType("unattributed_fireball");

    DamageType WITHER = getDamageType("wither");

    DamageType WITHER_SKULL = getDamageType("wither_skull");
    // Paper end - Generated/DamageType

    @NotNull
    private static DamageType getDamageType(@NotNull String key) {
        NamespacedKey namespacedKey = NamespacedKey.minecraft(key);
        return Preconditions.checkNotNull(Registry.DAMAGE_TYPE.get(namespacedKey), "No DamageType found for %s. This is a bug.", namespacedKey);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The returned key is that of the death message sent when this damage type
     * is responsible for the death of an entity.
     * <p>
     * <strong>Note</strong> This translation key is only used if
     * {@link #getDeathMessageType()} is {@link DeathMessageType#DEFAULT}
     */
    @NotNull
    @Override
    public String getTranslationKey();

    /**
     * Get the {@link DamageScaling} for this damage type.
     *
     * @return the damage scaling
     */
    @NotNull
    public DamageScaling getDamageScaling();

    /**
     * Get the {@link DamageEffect} for this damage type.
     *
     * @return the damage effect
     */
    @NotNull
    public DamageEffect getDamageEffect();

    /**
     * Get the {@link DeathMessageType} for this damage type.
     *
     * @return the death message type
     */
    @NotNull
    public DeathMessageType getDeathMessageType();

    /**
     * Get the amount of hunger exhaustion caused by this damage type.
     *
     * @return the exhaustion
     */
    public float getExhaustion();
}
