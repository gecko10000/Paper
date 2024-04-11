package io.papermc.generator.rewriter.types.simple;

import com.google.common.base.CaseFormat;
import com.google.common.collect.ImmutableMap;
import io.papermc.generator.rewriter.types.EnumRegistryRewriter;
import io.papermc.generator.types.goal.MobGoalNames;
import io.papermc.generator.utils.ClassHelper;
import io.papermc.generator.utils.Formatting;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import org.bukkit.entity.Villager;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.IdentityHashMap;
import java.util.Map;

import static io.papermc.generator.utils.Formatting.quoted;

public class EntityTypeRewriter extends EnumRegistryRewriter<EntityType<?>, org.bukkit.entity.EntityType> {

    private static final Map<EntityType<?>, Class<? extends Entity>> ENTITY_GENERIC_TYPES;
    static {
        final Map<EntityType<?>, Class<? extends Entity>> map = new IdentityHashMap<>();
        try {
            for (final Field field : EntityType.class.getDeclaredFields()) {
                if (!EntityType.class.isAssignableFrom(field.getType())) {
                    continue;
                }

                int mod = field.getModifiers();
                if (Modifier.isPublic(mod) & Modifier.isStatic(mod) & Modifier.isFinal(mod)) {
                    if (field.getGenericType() instanceof ParameterizedType complexType && complexType.getActualTypeArguments().length == 1) {
                        map.put((EntityType<?>) field.get(null), (Class<? extends Entity>) ClassHelper.eraseType(complexType.getActualTypeArguments()[0]));
                    }
                }
            }
        } catch (ReflectiveOperationException ex) {
            throw new RuntimeException(ex);
        }
        ENTITY_GENERIC_TYPES = Map.copyOf(map);
    }

    private static final Map<String, String> FIELD_RENAMES = ImmutableMap.<String, String>builder()
        .put("ITEM", "DROPPED_ITEM")
        .put("LEASH_KNOT", "LEASH_HITCH")
        .put("EYE_OF_ENDER", "ENDER_SIGNAL")
        .put("POTION", "SPLASH_POTION")
        .put("EXPERIENCE_BOTTLE", "THROWN_EXP_BOTTLE")
        .put("TNT", "PRIMED_TNT")
        .put("FIREWORK_ROCKET", "FIREWORK")
        .put("COMMAND_BLOCK_MINECART", "MINECART_COMMAND")
        .put("CHEST_MINECART", "MINECART_CHEST")
        .put("FURNACE_MINECART", "MINECART_FURNACE")
        .put("TNT_MINECART", "MINECART_TNT")
        .put("HOPPER_MINECART", "MINECART_HOPPER")
        .put("SPAWNER_MINECART", "MINECART_MOB_SPAWNER")
        .put("MOOSHROOM", "MUSHROOM_COW")
        .put("SNOW_GOLEM", "SNOWMAN")
        .put("END_CRYSTAL", "ENDER_CRYSTAL")
        .put("FISHING_BOBBER", "FISHING_HOOK")
        .put("LIGHTNING_BOLT", "LIGHTNING")
        .build();

    private static final Map<String, String> CLASS_RENAMES = ImmutableMap.<String, String>builder()
        .put("ExperienceBottle", "ThrownExpBottle")
        .put("EyeOfEnder", "EnderSignal")
        .put("EndCrystal", "EnderCrystal")
        .put("FireworkRocket", "Firework")
        .put("FishingBobber", "FishHook")
        .put("LeashKnot", "LeashHitch")
        .put("LightningBolt", "LightningStrike")
        .put("Potion", "ThrownPotion")
        .put("Tnt", "TNTPrimed")
        .put("ChestMinecart", "StorageMinecart")
        .put("CommandBlockMinecart", "CommandMinecart")
        .put("TntMinecart", "ExplosiveMinecart")
        .put("FurnaceMinecart", "PoweredMinecart")
        .build();

    @Deprecated
    private static final Object2IntMap<EntityType<?>> LEGACY_ID = Util.make(new Object2IntOpenHashMap<>(), map -> {
        map.put(EntityType.ITEM, 1);
        map.put(EntityType.EXPERIENCE_ORB, 2);
        map.put(EntityType.AREA_EFFECT_CLOUD, 3);
        map.put(EntityType.ELDER_GUARDIAN, 4);
        map.put(EntityType.WITHER_SKELETON, 5);
        map.put(EntityType.STRAY, 6);
        map.put(EntityType.EGG, 7);
        map.put(EntityType.LEASH_KNOT, 8);
        map.put(EntityType.PAINTING, 9);
        map.put(EntityType.ARROW, 10);
        map.put(EntityType.SNOWBALL, 11);
        map.put(EntityType.FIREBALL, 12);
        map.put(EntityType.SMALL_FIREBALL, 13);
        map.put(EntityType.ENDER_PEARL, 14);
        map.put(EntityType.EYE_OF_ENDER, 15);
        map.put(EntityType.POTION, 16);
        map.put(EntityType.EXPERIENCE_BOTTLE, 17);
        map.put(EntityType.ITEM_FRAME, 18);
        map.put(EntityType.WITHER_SKULL, 19);
        map.put(EntityType.TNT, 20);
        map.put(EntityType.FALLING_BLOCK, 21);
        map.put(EntityType.FIREWORK_ROCKET, 22);
        map.put(EntityType.HUSK, 23);
        map.put(EntityType.SPECTRAL_ARROW, 24);
        map.put(EntityType.SHULKER_BULLET, 25);
        map.put(EntityType.DRAGON_FIREBALL, 26);
        map.put(EntityType.ZOMBIE_VILLAGER, 27);
        map.put(EntityType.SKELETON_HORSE, 28);
        map.put(EntityType.ZOMBIE_HORSE, 29);
        map.put(EntityType.ARMOR_STAND, 30);
        map.put(EntityType.DONKEY, 31);
        map.put(EntityType.MULE, 32);
        map.put(EntityType.EVOKER_FANGS, 33);
        map.put(EntityType.EVOKER, 34);
        map.put(EntityType.VEX, 35);
        map.put(EntityType.VINDICATOR, 36);
        map.put(EntityType.ILLUSIONER, 37);

        map.put(EntityType.COMMAND_BLOCK_MINECART, 40);
        map.put(EntityType.BOAT, 41);
        map.put(EntityType.MINECART, 42);
        map.put(EntityType.CHEST_MINECART, 43);
        map.put(EntityType.FURNACE_MINECART, 44);
        map.put(EntityType.TNT_MINECART, 45);
        map.put(EntityType.HOPPER_MINECART, 46);
        map.put(EntityType.SPAWNER_MINECART, 47);

        map.put(EntityType.CREEPER, 50);
        map.put(EntityType.SKELETON, 51);
        map.put(EntityType.SPIDER, 52);
        map.put(EntityType.GIANT, 53);
        map.put(EntityType.ZOMBIE, 54);
        map.put(EntityType.SLIME, 55);
        map.put(EntityType.GHAST, 56);
        map.put(EntityType.ZOMBIFIED_PIGLIN, 57);
        map.put(EntityType.ENDERMAN, 58);
        map.put(EntityType.CAVE_SPIDER, 59);
        map.put(EntityType.SILVERFISH, 60);
        map.put(EntityType.BLAZE, 61);
        map.put(EntityType.MAGMA_CUBE, 62);
        map.put(EntityType.ENDER_DRAGON, 63);
        map.put(EntityType.WITHER, 64);
        map.put(EntityType.BAT, 65);
        map.put(EntityType.WITCH, 66);
        map.put(EntityType.ENDERMITE, 67);
        map.put(EntityType.GUARDIAN, 68);
        map.put(EntityType.SHULKER, 69);
        map.put(EntityType.PIG, 90);
        map.put(EntityType.SHEEP, 91);
        map.put(EntityType.COW, 92);
        map.put(EntityType.CHICKEN, 93);
        map.put(EntityType.SQUID, 94);
        map.put(EntityType.WOLF, 95);
        map.put(EntityType.MOOSHROOM, 96);
        map.put(EntityType.SNOW_GOLEM, 97);
        map.put(EntityType.OCELOT, 98);
        map.put(EntityType.IRON_GOLEM, 99);
        map.put(EntityType.HORSE, 100);
        map.put(EntityType.RABBIT, 101);
        map.put(EntityType.POLAR_BEAR, 102);
        map.put(EntityType.LLAMA, 103);
        map.put(EntityType.LLAMA_SPIT, 104);
        map.put(EntityType.PARROT, 105);

        map.put(EntityType.VILLAGER, 120);

        map.put(EntityType.END_CRYSTAL, 200);
    });

    public EntityTypeRewriter(final String pattern) {
        super(org.bukkit.entity.EntityType.class, Registries.ENTITY_TYPE, pattern, true);
    }

    @Override
    protected String rewriteEnumName(Holder.Reference<EntityType<?>> reference) {
        String internalName = super.rewriteEnumName(reference);
        return FIELD_RENAMES.getOrDefault(internalName, internalName);
    }

    @Override
    protected String rewriteEnumValue(Holder.Reference<EntityType<?>> reference) {
        String path = reference.key().location().getPath();
        final String param;
        if (!reference.value().canSummon()) {
            param = "%s, %s.class, %d, false";
        } else {
            param = "%s, %s.class, %d";
        }

        return param.formatted(quoted(path), toBukkitClass(reference), LEGACY_ID.getOrDefault(reference.value(), -1));
    }

    private String toBukkitClass(Holder.Reference<EntityType<?>> reference) {
        Class<? extends Entity> internalClass = ENTITY_GENERIC_TYPES.get(reference.value());
        if (Mob.class.isAssignableFrom(internalClass)) {
            return MobGoalNames.toBukkitClass((Class<? extends Mob>) internalClass).getSimpleName();
        }

        String className = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, reference.key().location().getPath()); // use the key instead of the internal class name since name match a bit more
        return CLASS_RENAMES.getOrDefault(className, className);
    }
}
