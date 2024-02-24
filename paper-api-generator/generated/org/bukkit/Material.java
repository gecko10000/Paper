package org.bukkit;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import java.lang.reflect.Constructor;
import java.util.Locale;
import java.util.Map;
import java.util.function.Consumer;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.AnaloguePowerable;
import org.bukkit.block.data.Bisected;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Brushable;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.Hatchable;
import org.bukkit.block.data.Levelled;
import org.bukkit.block.data.Lightable;
import org.bukkit.block.data.MultipleFacing;
import org.bukkit.block.data.Orientable;
import org.bukkit.block.data.Powerable;
import org.bukkit.block.data.Rail;
import org.bukkit.block.data.Rotatable;
import org.bukkit.block.data.Snowable;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.block.data.type.AmethystCluster;
import org.bukkit.block.data.type.Bamboo;
import org.bukkit.block.data.type.Barrel;
import org.bukkit.block.data.type.Bed;
import org.bukkit.block.data.type.Beehive;
import org.bukkit.block.data.type.Bell;
import org.bukkit.block.data.type.BigDripleaf;
import org.bukkit.block.data.type.BrewingStand;
import org.bukkit.block.data.type.BubbleColumn;
import org.bukkit.block.data.type.Cake;
import org.bukkit.block.data.type.CalibratedSculkSensor;
import org.bukkit.block.data.type.Campfire;
import org.bukkit.block.data.type.Candle;
import org.bukkit.block.data.type.CaveVines;
import org.bukkit.block.data.type.CaveVinesPlant;
import org.bukkit.block.data.type.Chain;
import org.bukkit.block.data.type.Chest;
import org.bukkit.block.data.type.ChiseledBookshelf;
import org.bukkit.block.data.type.Cocoa;
import org.bukkit.block.data.type.CommandBlock;
import org.bukkit.block.data.type.Comparator;
import org.bukkit.block.data.type.CopperBulb;
import org.bukkit.block.data.type.CoralWallFan;
import org.bukkit.block.data.type.Crafter;
import org.bukkit.block.data.type.DaylightDetector;
import org.bukkit.block.data.type.DecoratedPot;
import org.bukkit.block.data.type.Dispenser;
import org.bukkit.block.data.type.Door;
import org.bukkit.block.data.type.Dripleaf;
import org.bukkit.block.data.type.EndPortalFrame;
import org.bukkit.block.data.type.EnderChest;
import org.bukkit.block.data.type.Farmland;
import org.bukkit.block.data.type.Fence;
import org.bukkit.block.data.type.Fire;
import org.bukkit.block.data.type.Furnace;
import org.bukkit.block.data.type.Gate;
import org.bukkit.block.data.type.GlassPane;
import org.bukkit.block.data.type.GlowLichen;
import org.bukkit.block.data.type.Grindstone;
import org.bukkit.block.data.type.HangingSign;
import org.bukkit.block.data.type.Hopper;
import org.bukkit.block.data.type.Jigsaw;
import org.bukkit.block.data.type.Jukebox;
import org.bukkit.block.data.type.Ladder;
import org.bukkit.block.data.type.Lantern;
import org.bukkit.block.data.type.Leaves;
import org.bukkit.block.data.type.Lectern;
import org.bukkit.block.data.type.Light;
import org.bukkit.block.data.type.LightningRod;
import org.bukkit.block.data.type.MangrovePropagule;
import org.bukkit.block.data.type.NoteBlock;
import org.bukkit.block.data.type.Observer;
import org.bukkit.block.data.type.PinkPetals;
import org.bukkit.block.data.type.Piston;
import org.bukkit.block.data.type.PistonHead;
import org.bukkit.block.data.type.PitcherCrop;
import org.bukkit.block.data.type.PointedDripstone;
import org.bukkit.block.data.type.RedstoneRail;
import org.bukkit.block.data.type.RedstoneWallTorch;
import org.bukkit.block.data.type.RedstoneWire;
import org.bukkit.block.data.type.Repeater;
import org.bukkit.block.data.type.RespawnAnchor;
import org.bukkit.block.data.type.Sapling;
import org.bukkit.block.data.type.Scaffolding;
import org.bukkit.block.data.type.SculkCatalyst;
import org.bukkit.block.data.type.SculkSensor;
import org.bukkit.block.data.type.SculkShrieker;
import org.bukkit.block.data.type.SculkVein;
import org.bukkit.block.data.type.SeaPickle;
import org.bukkit.block.data.type.Sign;
import org.bukkit.block.data.type.Slab;
import org.bukkit.block.data.type.SmallDripleaf;
import org.bukkit.block.data.type.Snow;
import org.bukkit.block.data.type.Stairs;
import org.bukkit.block.data.type.StructureBlock;
import org.bukkit.block.data.type.Switch;
import org.bukkit.block.data.type.TNT;
import org.bukkit.block.data.type.TechnicalPiston;
import org.bukkit.block.data.type.TrapDoor;
import org.bukkit.block.data.type.TrialSpawner;
import org.bukkit.block.data.type.Tripwire;
import org.bukkit.block.data.type.TripwireHook;
import org.bukkit.block.data.type.TurtleEgg;
import org.bukkit.block.data.type.Wall;
import org.bukkit.block.data.type.WallHangingSign;
import org.bukkit.block.data.type.WallSign;
import org.bukkit.inventory.CreativeCategory;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.material.MaterialData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * An enum of all material IDs accepted by the official server and client
 */
@SuppressWarnings({"DeprecatedIsStillUsed", "deprecation"}) // Paper
public enum Material implements Keyed, Translatable, net.kyori.adventure.translation.Translatable { // Paper
    //<editor-fold desc="Materials" defaultstate="collapsed">
    // Paper start - Generated/Items
    // @GeneratedFrom 1.20.4
    ACACIA_BOAT(-1, 1),
    ACACIA_CHEST_BOAT(-1, 1),
    AIR(-1, 0),
    ALLAY_SPAWN_EGG(-1),
    AMETHYST_SHARD(-1),
    ANGLER_POTTERY_SHERD(-1),
    APPLE(-1),
    ARCHER_POTTERY_SHERD(-1),
    ARMOR_STAND(-1, 16),
    ARMS_UP_POTTERY_SHERD(-1),
    ARROW(-1),
    AXOLOTL_BUCKET(-1, 1),
    AXOLOTL_SPAWN_EGG(-1),
    BAKED_POTATO(-1),
    BAMBOO_CHEST_RAFT(-1, 1),
    BAMBOO_RAFT(-1, 1),
    BAT_SPAWN_EGG(-1),
    BEE_SPAWN_EGG(-1),
    BEEF(-1),
    BEETROOT(-1),
    BEETROOT_SEEDS(-1),
    BEETROOT_SOUP(-1, 1),
    BIRCH_BOAT(-1, 1),
    BIRCH_CHEST_BOAT(-1, 1),
    BLACK_DYE(-1),
    BLADE_POTTERY_SHERD(-1),
    BLAZE_POWDER(-1),
    BLAZE_ROD(-1),
    BLAZE_SPAWN_EGG(-1),
    BLUE_DYE(-1),
    BONE(-1),
    BONE_MEAL(-1),
    BOOK(-1),
    BOW(-1, 1, 384),
    BOWL(-1),
    BREAD(-1),
    @MinecraftExperimental("update 1.21")
    @org.jetbrains.annotations.ApiStatus.Experimental
    BREEZE_SPAWN_EGG(-1),
    BREWER_POTTERY_SHERD(-1),
    BRICK(-1),
    BROWN_DYE(-1),
    BRUSH(-1, 1, 64),
    BUCKET(-1, 16),
    BUNDLE(-1, 1),
    BURN_POTTERY_SHERD(-1),
    CAMEL_SPAWN_EGG(-1),
    CARROT(-1),
    CARROT_ON_A_STICK(-1, 1, 25),
    CAT_SPAWN_EGG(-1),
    CAVE_SPIDER_SPAWN_EGG(-1),
    CHAINMAIL_BOOTS(-1, 1, 195),
    CHAINMAIL_CHESTPLATE(-1, 1, 240),
    CHAINMAIL_HELMET(-1, 1, 165),
    CHAINMAIL_LEGGINGS(-1, 1, 225),
    CHARCOAL(-1),
    CHERRY_BOAT(-1, 1),
    CHERRY_CHEST_BOAT(-1, 1),
    CHEST_MINECART(-1, 1),
    CHICKEN(-1),
    CHICKEN_SPAWN_EGG(-1),
    CHORUS_FRUIT(-1),
    CLAY_BALL(-1),
    CLOCK(-1),
    COAL(-1),
    COAST_ARMOR_TRIM_SMITHING_TEMPLATE(-1),
    COCOA_BEANS(-1),
    COD(-1),
    COD_BUCKET(-1, 1),
    COD_SPAWN_EGG(-1),
    COMMAND_BLOCK_MINECART(-1, 1),
    COMPASS(-1),
    COOKED_BEEF(-1),
    COOKED_CHICKEN(-1),
    COOKED_COD(-1),
    COOKED_MUTTON(-1),
    COOKED_PORKCHOP(-1),
    COOKED_RABBIT(-1),
    COOKED_SALMON(-1),
    COOKIE(-1),
    COPPER_INGOT(-1),
    COW_SPAWN_EGG(-1),
    CREEPER_BANNER_PATTERN(-1, 1),
    CREEPER_SPAWN_EGG(-1),
    CROSSBOW(-1, 1, 465),
    CYAN_DYE(-1),
    DANGER_POTTERY_SHERD(-1),
    DARK_OAK_BOAT(-1, 1),
    DARK_OAK_CHEST_BOAT(-1, 1),
    DEBUG_STICK(-1, 1),
    DIAMOND(-1),
    DIAMOND_AXE(-1, 1, 1561),
    DIAMOND_BOOTS(-1, 1, 429),
    DIAMOND_CHESTPLATE(-1, 1, 528),
    DIAMOND_HELMET(-1, 1, 363),
    DIAMOND_HOE(-1, 1, 1561),
    DIAMOND_HORSE_ARMOR(-1, 1),
    DIAMOND_LEGGINGS(-1, 1, 495),
    DIAMOND_PICKAXE(-1, 1, 1561),
    DIAMOND_SHOVEL(-1, 1, 1561),
    DIAMOND_SWORD(-1, 1, 1561),
    DOLPHIN_SPAWN_EGG(-1),
    DONKEY_SPAWN_EGG(-1),
    DROWNED_SPAWN_EGG(-1),
    DUNE_ARMOR_TRIM_SMITHING_TEMPLATE(-1),
    ECHO_SHARD(-1),
    ELDER_GUARDIAN_SPAWN_EGG(-1),
    ENDER_DRAGON_SPAWN_EGG(-1),
    ENDERMAN_SPAWN_EGG(-1),
    ENDERMITE_SPAWN_EGG(-1),
    EVOKER_SPAWN_EGG(-1),
    EXPERIENCE_BOTTLE(-1),
    FIRE_CHARGE(-1),
    FIREWORK_ROCKET(-1),
    FOX_SPAWN_EGG(-1),
    FROG_SPAWN_EGG(-1),
    GHAST_SPAWN_EGG(-1),
    GLOW_ITEM_FRAME(-1),
    GLOW_SQUID_SPAWN_EGG(-1),
    GOAT_SPAWN_EGG(-1),
    GOLDEN_CARROT(-1),
    GUARDIAN_SPAWN_EGG(-1),
    HOGLIN_SPAWN_EGG(-1),
    HORSE_SPAWN_EGG(-1),
    HUSK_SPAWN_EGG(-1),
    IRON_GOLEM_SPAWN_EGG(-1),
    ITEM_FRAME(-1),
    LLAMA_SPAWN_EGG(-1),
    MAGMA_CUBE_SPAWN_EGG(-1),
    MAP(-1),
    MOOSHROOM_SPAWN_EGG(-1),
    MULE_SPAWN_EGG(-1),
    MUSIC_DISC_5(-1, 1),
    DISC_FRAGMENT_5(-1),
    DRAGON_BREATH(-1),
    DRIED_KELP(-1),
    EGG(-1, 16),
    ELYTRA(-1, 1, 432),
    EMERALD(-1),
    ENCHANTED_BOOK(-1, 1),
    ENCHANTED_GOLDEN_APPLE(-1),
    END_CRYSTAL(-1),
    ENDER_EYE(-1),
    ENDER_PEARL(-1, 16),
    EXPLORER_POTTERY_SHERD(-1),
    EYE_ARMOR_TRIM_SMITHING_TEMPLATE(-1),
    FEATHER(-1),
    FERMENTED_SPIDER_EYE(-1),
    FILLED_MAP(-1),
    FIREWORK_STAR(-1),
    FISHING_ROD(-1, 1, 64),
    FLINT(-1),
    FLINT_AND_STEEL(-1, 1, 64),
    FLOWER_BANNER_PATTERN(-1, 1),
    FRIEND_POTTERY_SHERD(-1),
    FURNACE_MINECART(-1, 1),
    GHAST_TEAR(-1),
    GLASS_BOTTLE(-1),
    GLISTERING_MELON_SLICE(-1),
    GLOBE_BANNER_PATTERN(-1, 1),
    GLOW_BERRIES(-1),
    GLOW_INK_SAC(-1),
    GLOWSTONE_DUST(-1),
    GOAT_HORN(-1, 1),
    GOLD_INGOT(-1),
    GOLD_NUGGET(-1),
    GOLDEN_APPLE(-1),
    GOLDEN_AXE(-1, 1, 32),
    GOLDEN_BOOTS(-1, 1, 91),
    GOLDEN_CHESTPLATE(-1, 1, 112),
    GOLDEN_HELMET(-1, 1, 77),
    GOLDEN_HOE(-1, 1, 32),
    GOLDEN_HORSE_ARMOR(-1, 1),
    GOLDEN_LEGGINGS(-1, 1, 105),
    GOLDEN_PICKAXE(-1, 1, 32),
    GOLDEN_SHOVEL(-1, 1, 32),
    GOLDEN_SWORD(-1, 1, 32),
    GRAY_DYE(-1),
    GREEN_DYE(-1),
    GUNPOWDER(-1),
    HEART_OF_THE_SEA(-1),
    HEART_POTTERY_SHERD(-1),
    HEARTBREAK_POTTERY_SHERD(-1),
    HONEY_BOTTLE(-1, 16),
    HONEYCOMB(-1),
    HOPPER_MINECART(-1, 1),
    HOST_ARMOR_TRIM_SMITHING_TEMPLATE(-1),
    HOWL_POTTERY_SHERD(-1),
    INK_SAC(-1),
    IRON_AXE(-1, 1, 250),
    IRON_BOOTS(-1, 1, 195),
    IRON_CHESTPLATE(-1, 1, 240),
    IRON_HELMET(-1, 1, 165),
    IRON_HOE(-1, 1, 250),
    IRON_HORSE_ARMOR(-1, 1),
    IRON_INGOT(-1),
    IRON_LEGGINGS(-1, 1, 225),
    IRON_NUGGET(-1),
    IRON_PICKAXE(-1, 1, 250),
    IRON_SHOVEL(-1, 1, 250),
    IRON_SWORD(-1, 1, 250),
    JUNGLE_BOAT(-1, 1),
    JUNGLE_CHEST_BOAT(-1, 1),
    KNOWLEDGE_BOOK(-1, 1),
    LAPIS_LAZULI(-1),
    LAVA_BUCKET(-1, 1),
    LEAD(-1),
    LEATHER(-1),
    LEATHER_BOOTS(-1, 1, 65),
    LEATHER_CHESTPLATE(-1, 1, 80),
    LEATHER_HELMET(-1, 1, 55),
    LEATHER_HORSE_ARMOR(-1, 1),
    LEATHER_LEGGINGS(-1, 1, 75),
    LIGHT_BLUE_DYE(-1),
    LIGHT_GRAY_DYE(-1),
    LIME_DYE(-1),
    LINGERING_POTION(-1, 1),
    MAGENTA_DYE(-1),
    MAGMA_CREAM(-1),
    MANGROVE_BOAT(-1, 1),
    MANGROVE_CHEST_BOAT(-1, 1),
    MELON_SEEDS(-1),
    MELON_SLICE(-1),
    MILK_BUCKET(-1, 1),
    MINECART(-1, 1),
    MINER_POTTERY_SHERD(-1),
    MOJANG_BANNER_PATTERN(-1, 1),
    MOURNER_POTTERY_SHERD(-1),
    MUSHROOM_STEW(-1, 1),
    MUSIC_DISC_11(-1, 1),
    MUSIC_DISC_13(-1, 1),
    MUSIC_DISC_BLOCKS(-1, 1),
    MUSIC_DISC_CAT(-1, 1),
    MUSIC_DISC_CHIRP(-1, 1),
    MUSIC_DISC_FAR(-1, 1),
    MUSIC_DISC_MALL(-1, 1),
    MUSIC_DISC_MELLOHI(-1, 1),
    MUSIC_DISC_OTHERSIDE(-1, 1),
    MUSIC_DISC_PIGSTEP(-1, 1),
    MUSIC_DISC_RELIC(-1, 1),
    MUSIC_DISC_STAL(-1, 1),
    MUSIC_DISC_STRAD(-1, 1),
    MUSIC_DISC_WAIT(-1, 1),
    MUSIC_DISC_WARD(-1, 1),
    MUTTON(-1),
    NAME_TAG(-1),
    NAUTILUS_SHELL(-1),
    NETHER_BRICK(-1),
    NETHER_STAR(-1),
    NETHERITE_AXE(-1, 1, 2031),
    NETHERITE_BOOTS(-1, 1, 481),
    NETHERITE_CHESTPLATE(-1, 1, 592),
    NETHERITE_HELMET(-1, 1, 407),
    NETHERITE_HOE(-1, 1, 2031),
    NETHERITE_INGOT(-1),
    NETHERITE_LEGGINGS(-1, 1, 555),
    NETHERITE_PICKAXE(-1, 1, 2031),
    NETHERITE_SCRAP(-1),
    NETHERITE_SHOVEL(-1, 1, 2031),
    NETHERITE_SWORD(-1, 1, 2031),
    NETHERITE_UPGRADE_SMITHING_TEMPLATE(-1),
    OAK_BOAT(-1, 1),
    OAK_CHEST_BOAT(-1, 1),
    OCELOT_SPAWN_EGG(-1),
    ORANGE_DYE(-1),
    PAINTING(-1),
    PANDA_SPAWN_EGG(-1),
    PAPER(-1),
    PARROT_SPAWN_EGG(-1),
    PHANTOM_MEMBRANE(-1),
    PHANTOM_SPAWN_EGG(-1),
    PIG_SPAWN_EGG(-1),
    PIGLIN_BANNER_PATTERN(-1, 1),
    PIGLIN_BRUTE_SPAWN_EGG(-1),
    PIGLIN_SPAWN_EGG(-1),
    PILLAGER_SPAWN_EGG(-1),
    PINK_DYE(-1),
    PITCHER_POD(-1),
    PLENTY_POTTERY_SHERD(-1),
    POISONOUS_POTATO(-1),
    POLAR_BEAR_SPAWN_EGG(-1),
    POPPED_CHORUS_FRUIT(-1),
    PORKCHOP(-1),
    POTATO(-1),
    POTION(-1, 1),
    POWDER_SNOW_BUCKET(-1, 1),
    PRISMARINE_CRYSTALS(-1),
    PRISMARINE_SHARD(-1),
    PRIZE_POTTERY_SHERD(-1),
    PUFFERFISH(-1),
    PUFFERFISH_BUCKET(-1, 1),
    PUFFERFISH_SPAWN_EGG(-1),
    PUMPKIN_PIE(-1),
    PUMPKIN_SEEDS(-1),
    PURPLE_DYE(-1),
    QUARTZ(-1),
    RABBIT(-1),
    RABBIT_FOOT(-1),
    RABBIT_HIDE(-1),
    RABBIT_SPAWN_EGG(-1),
    RABBIT_STEW(-1, 1),
    RAISER_ARMOR_TRIM_SMITHING_TEMPLATE(-1),
    RAVAGER_SPAWN_EGG(-1),
    RAW_COPPER(-1),
    RAW_GOLD(-1),
    RAW_IRON(-1),
    RECOVERY_COMPASS(-1),
    RED_DYE(-1),
    REDSTONE(-1),
    RIB_ARMOR_TRIM_SMITHING_TEMPLATE(-1),
    ROTTEN_FLESH(-1),
    SADDLE(-1, 1),
    SALMON(-1),
    SALMON_BUCKET(-1, 1),
    SALMON_SPAWN_EGG(-1),
    SCUTE(-1),
    SENTRY_ARMOR_TRIM_SMITHING_TEMPLATE(-1),
    SHAPER_ARMOR_TRIM_SMITHING_TEMPLATE(-1),
    SHEAF_POTTERY_SHERD(-1),
    SHEARS(-1, 1, 238),
    SHEEP_SPAWN_EGG(-1),
    SHELTER_POTTERY_SHERD(-1),
    SHIELD(-1, 1, 336),
    SHULKER_SHELL(-1),
    SHULKER_SPAWN_EGG(-1),
    SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE(-1),
    SILVERFISH_SPAWN_EGG(-1),
    SKELETON_HORSE_SPAWN_EGG(-1),
    SKELETON_SPAWN_EGG(-1),
    SKULL_BANNER_PATTERN(-1, 1),
    SKULL_POTTERY_SHERD(-1),
    SLIME_BALL(-1),
    SLIME_SPAWN_EGG(-1),
    SNIFFER_SPAWN_EGG(-1),
    SNORT_POTTERY_SHERD(-1),
    SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE(-1),
    SNOW_GOLEM_SPAWN_EGG(-1),
    SNOWBALL(-1, 16),
    SPECTRAL_ARROW(-1),
    SPIDER_EYE(-1),
    SPIDER_SPAWN_EGG(-1),
    SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE(-1),
    SPLASH_POTION(-1, 1),
    SPRUCE_BOAT(-1, 1),
    SPRUCE_CHEST_BOAT(-1, 1),
    SPYGLASS(-1, 1),
    SQUID_SPAWN_EGG(-1),
    STICK(-1),
    STONE_AXE(-1, 1, 131),
    STONE_HOE(-1, 1, 131),
    STONE_PICKAXE(-1, 1, 131),
    STONE_SHOVEL(-1, 1, 131),
    STONE_SWORD(-1, 1, 131),
    STRAY_SPAWN_EGG(-1),
    STRIDER_SPAWN_EGG(-1),
    STRING(-1),
    SUGAR(-1),
    SUSPICIOUS_STEW(-1, 1),
    SWEET_BERRIES(-1),
    TADPOLE_BUCKET(-1, 1),
    TADPOLE_SPAWN_EGG(-1),
    TIDE_ARMOR_TRIM_SMITHING_TEMPLATE(-1),
    TIPPED_ARROW(-1),
    TNT_MINECART(-1, 1),
    TORCHFLOWER_SEEDS(-1),
    TOTEM_OF_UNDYING(-1, 1),
    TRADER_LLAMA_SPAWN_EGG(-1),
    @MinecraftExperimental("update 1.21")
    @org.jetbrains.annotations.ApiStatus.Experimental
    TRIAL_KEY(-1),
    TRIDENT(-1, 1, 250),
    TROPICAL_FISH(-1),
    TROPICAL_FISH_BUCKET(-1, 1),
    TROPICAL_FISH_SPAWN_EGG(-1),
    TURTLE_HELMET(-1, 1, 275),
    TURTLE_SPAWN_EGG(-1),
    VEX_ARMOR_TRIM_SMITHING_TEMPLATE(-1),
    VEX_SPAWN_EGG(-1),
    VILLAGER_SPAWN_EGG(-1),
    VINDICATOR_SPAWN_EGG(-1),
    WANDERING_TRADER_SPAWN_EGG(-1),
    WARD_ARMOR_TRIM_SMITHING_TEMPLATE(-1),
    WARDEN_SPAWN_EGG(-1),
    WARPED_FUNGUS_ON_A_STICK(-1, 1, 100),
    WATER_BUCKET(-1, 1),
    WAYFINDER_ARMOR_TRIM_SMITHING_TEMPLATE(-1),
    WHEAT_SEEDS(-1),
    WHITE_DYE(-1),
    WILD_ARMOR_TRIM_SMITHING_TEMPLATE(-1),
    WITCH_SPAWN_EGG(-1),
    WITHER_SKELETON_SPAWN_EGG(-1),
    WITHER_SPAWN_EGG(-1),
    WOLF_SPAWN_EGG(-1),
    WOODEN_AXE(-1, 1, 59),
    WOODEN_HOE(-1, 1, 59),
    WOODEN_PICKAXE(-1, 1, 59),
    WOODEN_SHOVEL(-1, 1, 59),
    WOODEN_SWORD(-1, 1, 59),
    WRITABLE_BOOK(-1, 1),
    WRITTEN_BOOK(-1, 16),
    YELLOW_DYE(-1),
    ZOGLIN_SPAWN_EGG(-1),
    ZOMBIE_HORSE_SPAWN_EGG(-1),
    ZOMBIE_SPAWN_EGG(-1),
    ZOMBIE_VILLAGER_SPAWN_EGG(-1),
    ZOMBIFIED_PIGLIN_SPAWN_EGG(-1),
    // Paper end - Generated/Items
    // Paper start - Generated/Blocks
    // @GeneratedFrom 1.20.4
    ACACIA_BUTTON(-1, BlockData.class),
    ACACIA_DOOR(-1, BlockData.class),
    ACACIA_FENCE(-1, BlockData.class),
    ACACIA_FENCE_GATE(-1, BlockData.class),
    ACACIA_HANGING_SIGN(-1, 16, BlockData.class),
    ACACIA_LEAVES(-1, BlockData.class),
    ACACIA_LOG(-1, BlockData.class),
    ACACIA_PLANKS(-1),
    ACACIA_PRESSURE_PLATE(-1, BlockData.class),
    ACACIA_SAPLING(-1, BlockData.class),
    ACACIA_SIGN(-1, 16, BlockData.class),
    ACACIA_SLAB(-1, BlockData.class),
    ACACIA_STAIRS(-1, BlockData.class),
    ACACIA_TRAPDOOR(-1, BlockData.class),
    ACACIA_WALL_HANGING_SIGN(-1, BlockData.class),
    ACACIA_WALL_SIGN(-1, 16, BlockData.class),
    ACACIA_WOOD(-1, BlockData.class),
    ACTIVATOR_RAIL(-1, BlockData.class),
    ALLIUM(-1),
    AMETHYST_BLOCK(-1),
    AMETHYST_CLUSTER(-1, BlockData.class),
    ANCIENT_DEBRIS(-1),
    ANDESITE(-1),
    ANDESITE_SLAB(-1, BlockData.class),
    ANDESITE_STAIRS(-1, BlockData.class),
    ANDESITE_WALL(-1, BlockData.class),
    ANVIL(-1, BlockData.class),
    ATTACHED_MELON_STEM(-1, BlockData.class),
    ATTACHED_PUMPKIN_STEM(-1, BlockData.class),
    AZALEA(-1),
    AZALEA_LEAVES(-1, BlockData.class),
    AZURE_BLUET(-1),
    BAMBOO(-1, BlockData.class),
    BAMBOO_BLOCK(-1, BlockData.class),
    BAMBOO_BUTTON(-1, BlockData.class),
    BAMBOO_DOOR(-1, BlockData.class),
    BAMBOO_FENCE(-1, BlockData.class),
    BAMBOO_FENCE_GATE(-1, BlockData.class),
    BAMBOO_HANGING_SIGN(-1, 16, BlockData.class),
    BAMBOO_MOSAIC(-1),
    BAMBOO_MOSAIC_SLAB(-1, BlockData.class),
    BAMBOO_MOSAIC_STAIRS(-1, BlockData.class),
    BAMBOO_PLANKS(-1),
    BAMBOO_PRESSURE_PLATE(-1, BlockData.class),
    BAMBOO_SAPLING(-1),
    BAMBOO_SIGN(-1, 16, BlockData.class),
    BAMBOO_SLAB(-1, BlockData.class),
    BAMBOO_STAIRS(-1, BlockData.class),
    BAMBOO_TRAPDOOR(-1, BlockData.class),
    BAMBOO_WALL_HANGING_SIGN(-1, BlockData.class),
    BAMBOO_WALL_SIGN(-1, 16, BlockData.class),
    BARREL(-1, BlockData.class),
    BARRIER(-1, BlockData.class),
    BASALT(-1, BlockData.class),
    BEACON(-1),
    BEDROCK(-1),
    BEE_NEST(-1, BlockData.class),
    BEEHIVE(-1, BlockData.class),
    BEETROOTS(-1, BlockData.class),
    BELL(-1, BlockData.class),
    BIG_DRIPLEAF(-1, BlockData.class),
    BIG_DRIPLEAF_STEM(-1, BlockData.class),
    BIRCH_BUTTON(-1, BlockData.class),
    BIRCH_DOOR(-1, BlockData.class),
    BIRCH_FENCE(-1, BlockData.class),
    BIRCH_FENCE_GATE(-1, BlockData.class),
    BIRCH_HANGING_SIGN(-1, 16, BlockData.class),
    BIRCH_LEAVES(-1, BlockData.class),
    BIRCH_LOG(-1, BlockData.class),
    BIRCH_PLANKS(-1),
    BIRCH_PRESSURE_PLATE(-1, BlockData.class),
    BIRCH_SAPLING(-1, BlockData.class),
    BIRCH_SIGN(-1, 16, BlockData.class),
    BIRCH_SLAB(-1, BlockData.class),
    BIRCH_STAIRS(-1, BlockData.class),
    BIRCH_TRAPDOOR(-1, BlockData.class),
    BIRCH_WALL_HANGING_SIGN(-1, BlockData.class),
    BIRCH_WALL_SIGN(-1, 16, BlockData.class),
    BIRCH_WOOD(-1, BlockData.class),
    BLACK_BANNER(-1, 16, BlockData.class),
    BLACK_BED(-1, 1, BlockData.class),
    BLACK_CANDLE(-1, BlockData.class),
    BLACK_CANDLE_CAKE(-1, BlockData.class),
    BLACK_CARPET(-1),
    BLACK_CONCRETE(-1),
    BLACK_CONCRETE_POWDER(-1),
    BLACK_GLAZED_TERRACOTTA(-1, BlockData.class),
    BLACK_SHULKER_BOX(-1, 1, BlockData.class),
    BLACK_STAINED_GLASS(-1),
    BLACK_STAINED_GLASS_PANE(-1, BlockData.class),
    BLACK_TERRACOTTA(-1),
    BLACK_WALL_BANNER(-1, BlockData.class),
    BLACK_WOOL(-1),
    BLACKSTONE(-1),
    BLACKSTONE_SLAB(-1, BlockData.class),
    BLACKSTONE_STAIRS(-1, BlockData.class),
    BLACKSTONE_WALL(-1, BlockData.class),
    BLAST_FURNACE(-1, BlockData.class),
    BLUE_BANNER(-1, 16, BlockData.class),
    BLUE_BED(-1, 1, BlockData.class),
    BLUE_CANDLE(-1, BlockData.class),
    BLUE_CANDLE_CAKE(-1, BlockData.class),
    BLUE_CARPET(-1),
    BLUE_CONCRETE(-1),
    BLUE_CONCRETE_POWDER(-1),
    BLUE_GLAZED_TERRACOTTA(-1, BlockData.class),
    BLUE_ICE(-1),
    BLUE_ORCHID(-1),
    BLUE_SHULKER_BOX(-1, 1, BlockData.class),
    BLUE_STAINED_GLASS(-1),
    BLUE_STAINED_GLASS_PANE(-1, BlockData.class),
    BLUE_TERRACOTTA(-1),
    BLUE_WALL_BANNER(-1, BlockData.class),
    BLUE_WOOL(-1),
    BONE_BLOCK(-1, BlockData.class),
    BOOKSHELF(-1),
    BRAIN_CORAL(-1, BlockData.class),
    BRAIN_CORAL_BLOCK(-1),
    BRAIN_CORAL_FAN(-1, BlockData.class),
    BRAIN_CORAL_WALL_FAN(-1, BlockData.class),
    BREWING_STAND(-1, BlockData.class),
    BRICK_SLAB(-1, BlockData.class),
    BRICK_STAIRS(-1, BlockData.class),
    BRICK_WALL(-1, BlockData.class),
    BRICKS(-1),
    BROWN_BANNER(-1, 16, BlockData.class),
    BROWN_BED(-1, 1, BlockData.class),
    BROWN_CANDLE(-1, BlockData.class),
    BROWN_CANDLE_CAKE(-1, BlockData.class),
    BROWN_CARPET(-1),
    BROWN_CONCRETE(-1),
    BROWN_CONCRETE_POWDER(-1),
    BROWN_GLAZED_TERRACOTTA(-1, BlockData.class),
    BROWN_MUSHROOM(-1),
    BROWN_MUSHROOM_BLOCK(-1, BlockData.class),
    BROWN_SHULKER_BOX(-1, 1, BlockData.class),
    BROWN_STAINED_GLASS(-1),
    BROWN_STAINED_GLASS_PANE(-1, BlockData.class),
    BROWN_TERRACOTTA(-1),
    BROWN_WALL_BANNER(-1, BlockData.class),
    BROWN_WOOL(-1),
    BUBBLE_COLUMN(-1, BlockData.class),
    BUBBLE_CORAL(-1, BlockData.class),
    BUBBLE_CORAL_BLOCK(-1),
    BUBBLE_CORAL_FAN(-1, BlockData.class),
    BUBBLE_CORAL_WALL_FAN(-1, BlockData.class),
    BUDDING_AMETHYST(-1),
    CACTUS(-1, BlockData.class),
    CAKE(-1, 1, BlockData.class),
    CALCITE(-1),
    CALIBRATED_SCULK_SENSOR(-1, BlockData.class),
    CAMPFIRE(-1, BlockData.class),
    CANDLE(-1, BlockData.class),
    CANDLE_CAKE(-1, BlockData.class),
    CARROTS(-1, BlockData.class),
    CARTOGRAPHY_TABLE(-1),
    CARVED_PUMPKIN(-1, BlockData.class),
    CAULDRON(-1),
    CAVE_AIR(-1),
    CAVE_VINES(-1, BlockData.class),
    CAVE_VINES_PLANT(-1, BlockData.class),
    CHAIN(-1, BlockData.class),
    CHAIN_COMMAND_BLOCK(-1, BlockData.class),
    CHERRY_BUTTON(-1, BlockData.class),
    CHERRY_DOOR(-1, BlockData.class),
    CHERRY_FENCE(-1, BlockData.class),
    CHERRY_FENCE_GATE(-1, BlockData.class),
    CHERRY_HANGING_SIGN(-1, 16, BlockData.class),
    CHERRY_LEAVES(-1, BlockData.class),
    CHERRY_LOG(-1, BlockData.class),
    CHERRY_PLANKS(-1),
    CHERRY_PRESSURE_PLATE(-1, BlockData.class),
    CHERRY_SAPLING(-1, BlockData.class),
    CHERRY_SIGN(-1, 16, BlockData.class),
    CHERRY_SLAB(-1, BlockData.class),
    CHERRY_STAIRS(-1, BlockData.class),
    CHERRY_TRAPDOOR(-1, BlockData.class),
    CHERRY_WALL_HANGING_SIGN(-1, BlockData.class),
    CHERRY_WALL_SIGN(-1, 16, BlockData.class),
    CHERRY_WOOD(-1, BlockData.class),
    CHEST(-1, BlockData.class),
    CHIPPED_ANVIL(-1, BlockData.class),
    CHISELED_BOOKSHELF(-1, BlockData.class),
    @MinecraftExperimental("update 1.21")
    @org.jetbrains.annotations.ApiStatus.Experimental
    CHISELED_COPPER(-1),
    CHISELED_DEEPSLATE(-1),
    CHISELED_NETHER_BRICKS(-1),
    CHISELED_POLISHED_BLACKSTONE(-1),
    CHISELED_QUARTZ_BLOCK(-1),
    CHISELED_RED_SANDSTONE(-1),
    CHISELED_SANDSTONE(-1),
    CHISELED_STONE_BRICKS(-1),
    @MinecraftExperimental("update 1.21")
    @org.jetbrains.annotations.ApiStatus.Experimental
    CHISELED_TUFF(-1),
    @MinecraftExperimental("update 1.21")
    @org.jetbrains.annotations.ApiStatus.Experimental
    CHISELED_TUFF_BRICKS(-1),
    CHORUS_FLOWER(-1, BlockData.class),
    CHORUS_PLANT(-1, BlockData.class),
    CLAY(-1),
    COAL_BLOCK(-1),
    COAL_ORE(-1),
    COARSE_DIRT(-1),
    COBBLED_DEEPSLATE(-1),
    COBBLED_DEEPSLATE_SLAB(-1, BlockData.class),
    COBBLED_DEEPSLATE_STAIRS(-1, BlockData.class),
    COBBLED_DEEPSLATE_WALL(-1, BlockData.class),
    COBBLESTONE(-1),
    COBBLESTONE_SLAB(-1, BlockData.class),
    COBBLESTONE_STAIRS(-1, BlockData.class),
    COBBLESTONE_WALL(-1, BlockData.class),
    COBWEB(-1),
    COCOA(-1, BlockData.class),
    COMMAND_BLOCK(-1, BlockData.class),
    COMPARATOR(-1, BlockData.class),
    COMPOSTER(-1, BlockData.class),
    CONDUIT(-1, BlockData.class),
    COPPER_BLOCK(-1),
    @MinecraftExperimental("update 1.21")
    @org.jetbrains.annotations.ApiStatus.Experimental
    COPPER_BULB(-1, BlockData.class),
    @MinecraftExperimental("update 1.21")
    @org.jetbrains.annotations.ApiStatus.Experimental
    COPPER_DOOR(-1, BlockData.class),
    @MinecraftExperimental("update 1.21")
    @org.jetbrains.annotations.ApiStatus.Experimental
    COPPER_GRATE(-1, BlockData.class),
    COPPER_ORE(-1),
    @MinecraftExperimental("update 1.21")
    @org.jetbrains.annotations.ApiStatus.Experimental
    COPPER_TRAPDOOR(-1, BlockData.class),
    CORNFLOWER(-1),
    CRACKED_DEEPSLATE_BRICKS(-1),
    CRACKED_DEEPSLATE_TILES(-1),
    CRACKED_NETHER_BRICKS(-1),
    CRACKED_POLISHED_BLACKSTONE_BRICKS(-1),
    CRACKED_STONE_BRICKS(-1),
    @MinecraftExperimental("update 1.21")
    @org.jetbrains.annotations.ApiStatus.Experimental
    CRAFTER(-1, BlockData.class),
    CRAFTING_TABLE(-1),
    CREEPER_HEAD(-1, BlockData.class),
    CREEPER_WALL_HEAD(-1, BlockData.class),
    CRIMSON_BUTTON(-1, BlockData.class),
    CRIMSON_DOOR(-1, BlockData.class),
    CRIMSON_FENCE(-1, BlockData.class),
    CRIMSON_FENCE_GATE(-1, BlockData.class),
    CRIMSON_FUNGUS(-1),
    CRIMSON_HANGING_SIGN(-1, 16, BlockData.class),
    CRIMSON_HYPHAE(-1, BlockData.class),
    CRIMSON_NYLIUM(-1),
    CRIMSON_PLANKS(-1),
    CRIMSON_PRESSURE_PLATE(-1, BlockData.class),
    CRIMSON_ROOTS(-1),
    CRIMSON_SIGN(-1, 16, BlockData.class),
    CRIMSON_SLAB(-1, BlockData.class),
    CRIMSON_STAIRS(-1, BlockData.class),
    CRIMSON_STEM(-1, BlockData.class),
    CRIMSON_TRAPDOOR(-1, BlockData.class),
    CRIMSON_WALL_HANGING_SIGN(-1, BlockData.class),
    CRIMSON_WALL_SIGN(-1, 16, BlockData.class),
    CRYING_OBSIDIAN(-1),
    CUT_COPPER(-1),
    CUT_COPPER_SLAB(-1, BlockData.class),
    CUT_COPPER_STAIRS(-1, BlockData.class),
    CUT_RED_SANDSTONE(-1),
    CUT_RED_SANDSTONE_SLAB(-1, BlockData.class),
    CUT_SANDSTONE(-1),
    CUT_SANDSTONE_SLAB(-1, BlockData.class),
    CYAN_BANNER(-1, 16, BlockData.class),
    CYAN_BED(-1, 1, BlockData.class),
    CYAN_CANDLE(-1, BlockData.class),
    CYAN_CANDLE_CAKE(-1, BlockData.class),
    CYAN_CARPET(-1),
    CYAN_CONCRETE(-1),
    CYAN_CONCRETE_POWDER(-1),
    CYAN_GLAZED_TERRACOTTA(-1, BlockData.class),
    CYAN_SHULKER_BOX(-1, 1, BlockData.class),
    CYAN_STAINED_GLASS(-1),
    CYAN_STAINED_GLASS_PANE(-1, BlockData.class),
    CYAN_TERRACOTTA(-1),
    CYAN_WALL_BANNER(-1, BlockData.class),
    CYAN_WOOL(-1),
    DAMAGED_ANVIL(-1, BlockData.class),
    DANDELION(-1),
    DARK_OAK_BUTTON(-1, BlockData.class),
    DARK_OAK_DOOR(-1, BlockData.class),
    DARK_OAK_FENCE(-1, BlockData.class),
    DARK_OAK_FENCE_GATE(-1, BlockData.class),
    DARK_OAK_HANGING_SIGN(-1, 16, BlockData.class),
    DARK_OAK_LEAVES(-1, BlockData.class),
    DARK_OAK_LOG(-1, BlockData.class),
    DARK_OAK_PLANKS(-1),
    DARK_OAK_PRESSURE_PLATE(-1, BlockData.class),
    DARK_OAK_SAPLING(-1, BlockData.class),
    DARK_OAK_SIGN(-1, 16, BlockData.class),
    DARK_OAK_SLAB(-1, BlockData.class),
    DARK_OAK_STAIRS(-1, BlockData.class),
    DARK_OAK_TRAPDOOR(-1, BlockData.class),
    DARK_OAK_WALL_HANGING_SIGN(-1, BlockData.class),
    DARK_OAK_WALL_SIGN(-1, 16, BlockData.class),
    DARK_OAK_WOOD(-1, BlockData.class),
    DARK_PRISMARINE(-1),
    DARK_PRISMARINE_SLAB(-1, BlockData.class),
    DARK_PRISMARINE_STAIRS(-1, BlockData.class),
    DAYLIGHT_DETECTOR(-1, BlockData.class),
    DEAD_BRAIN_CORAL(-1, BlockData.class),
    DEAD_BRAIN_CORAL_BLOCK(-1),
    DEAD_BRAIN_CORAL_FAN(-1, BlockData.class),
    DEAD_BRAIN_CORAL_WALL_FAN(-1, BlockData.class),
    DEAD_BUBBLE_CORAL(-1, BlockData.class),
    DEAD_BUBBLE_CORAL_BLOCK(-1),
    DEAD_BUBBLE_CORAL_FAN(-1, BlockData.class),
    DEAD_BUBBLE_CORAL_WALL_FAN(-1, BlockData.class),
    DEAD_BUSH(-1),
    DEAD_FIRE_CORAL(-1, BlockData.class),
    DEAD_FIRE_CORAL_BLOCK(-1),
    DEAD_FIRE_CORAL_FAN(-1, BlockData.class),
    DEAD_FIRE_CORAL_WALL_FAN(-1, BlockData.class),
    DEAD_HORN_CORAL(-1, BlockData.class),
    DEAD_HORN_CORAL_BLOCK(-1),
    DEAD_HORN_CORAL_FAN(-1, BlockData.class),
    DEAD_HORN_CORAL_WALL_FAN(-1, BlockData.class),
    DEAD_TUBE_CORAL(-1, BlockData.class),
    DEAD_TUBE_CORAL_BLOCK(-1),
    DEAD_TUBE_CORAL_FAN(-1, BlockData.class),
    DEAD_TUBE_CORAL_WALL_FAN(-1, BlockData.class),
    DECORATED_POT(-1, BlockData.class),
    DEEPSLATE(-1, BlockData.class),
    DEEPSLATE_BRICK_SLAB(-1, BlockData.class),
    DEEPSLATE_BRICK_STAIRS(-1, BlockData.class),
    DEEPSLATE_BRICK_WALL(-1, BlockData.class),
    DEEPSLATE_BRICKS(-1),
    DEEPSLATE_COAL_ORE(-1),
    DEEPSLATE_COPPER_ORE(-1),
    DEEPSLATE_DIAMOND_ORE(-1),
    DEEPSLATE_EMERALD_ORE(-1),
    DEEPSLATE_GOLD_ORE(-1),
    DEEPSLATE_IRON_ORE(-1),
    DEEPSLATE_LAPIS_ORE(-1),
    DEEPSLATE_REDSTONE_ORE(-1, BlockData.class),
    DEEPSLATE_TILE_SLAB(-1, BlockData.class),
    DEEPSLATE_TILE_STAIRS(-1, BlockData.class),
    DEEPSLATE_TILE_WALL(-1, BlockData.class),
    DEEPSLATE_TILES(-1),
    DETECTOR_RAIL(-1, BlockData.class),
    DIAMOND_BLOCK(-1),
    DIAMOND_ORE(-1),
    DIORITE(-1),
    DIORITE_SLAB(-1, BlockData.class),
    DIORITE_STAIRS(-1, BlockData.class),
    DIORITE_WALL(-1, BlockData.class),
    DIRT(-1),
    DIRT_PATH(-1),
    DISPENSER(-1, BlockData.class),
    DRAGON_EGG(-1),
    DRAGON_HEAD(-1, BlockData.class),
    DRAGON_WALL_HEAD(-1, BlockData.class),
    DRIED_KELP_BLOCK(-1),
    DRIPSTONE_BLOCK(-1),
    DROPPER(-1, BlockData.class),
    EMERALD_BLOCK(-1),
    EMERALD_ORE(-1),
    ENCHANTING_TABLE(-1),
    END_GATEWAY(-1),
    END_PORTAL(-1),
    END_PORTAL_FRAME(-1, BlockData.class),
    END_ROD(-1, BlockData.class),
    END_STONE(-1),
    END_STONE_BRICK_SLAB(-1, BlockData.class),
    END_STONE_BRICK_STAIRS(-1, BlockData.class),
    END_STONE_BRICK_WALL(-1, BlockData.class),
    END_STONE_BRICKS(-1),
    ENDER_CHEST(-1, BlockData.class),
    @MinecraftExperimental("update 1.21")
    @org.jetbrains.annotations.ApiStatus.Experimental
    EXPOSED_CHISELED_COPPER(-1),
    EXPOSED_COPPER(-1),
    @MinecraftExperimental("update 1.21")
    @org.jetbrains.annotations.ApiStatus.Experimental
    EXPOSED_COPPER_BULB(-1, BlockData.class),
    @MinecraftExperimental("update 1.21")
    @org.jetbrains.annotations.ApiStatus.Experimental
    EXPOSED_COPPER_DOOR(-1, BlockData.class),
    @MinecraftExperimental("update 1.21")
    @org.jetbrains.annotations.ApiStatus.Experimental
    EXPOSED_COPPER_GRATE(-1, BlockData.class),
    @MinecraftExperimental("update 1.21")
    @org.jetbrains.annotations.ApiStatus.Experimental
    EXPOSED_COPPER_TRAPDOOR(-1, BlockData.class),
    EXPOSED_CUT_COPPER(-1),
    EXPOSED_CUT_COPPER_SLAB(-1, BlockData.class),
    EXPOSED_CUT_COPPER_STAIRS(-1, BlockData.class),
    FARMLAND(-1, BlockData.class),
    FERN(-1),
    FIRE(-1, BlockData.class),
    FIRE_CORAL(-1, BlockData.class),
    FIRE_CORAL_BLOCK(-1),
    FIRE_CORAL_FAN(-1, BlockData.class),
    FIRE_CORAL_WALL_FAN(-1, BlockData.class),
    FLETCHING_TABLE(-1),
    FLOWER_POT(-1),
    FLOWERING_AZALEA(-1),
    FLOWERING_AZALEA_LEAVES(-1, BlockData.class),
    FROGSPAWN(-1),
    FROSTED_ICE(-1, BlockData.class),
    FURNACE(-1, BlockData.class),
    GILDED_BLACKSTONE(-1),
    GLASS(-1),
    GLASS_PANE(-1, BlockData.class),
    GLOW_LICHEN(-1, BlockData.class),
    GLOWSTONE(-1),
    GOLD_BLOCK(-1),
    GOLD_ORE(-1),
    GRANITE(-1),
    GRANITE_SLAB(-1, BlockData.class),
    GRANITE_STAIRS(-1, BlockData.class),
    GRANITE_WALL(-1, BlockData.class),
    GRASS_BLOCK(-1, BlockData.class),
    GRAVEL(-1),
    GRAY_BANNER(-1, 16, BlockData.class),
    GRAY_BED(-1, 1, BlockData.class),
    GRAY_CANDLE(-1, BlockData.class),
    GRAY_CANDLE_CAKE(-1, BlockData.class),
    GRAY_CARPET(-1),
    GRAY_CONCRETE(-1),
    GRAY_CONCRETE_POWDER(-1),
    GRAY_GLAZED_TERRACOTTA(-1, BlockData.class),
    GRAY_SHULKER_BOX(-1, 1, BlockData.class),
    GRAY_STAINED_GLASS(-1),
    GRAY_STAINED_GLASS_PANE(-1, BlockData.class),
    GRAY_TERRACOTTA(-1),
    GRAY_WALL_BANNER(-1, BlockData.class),
    GRAY_WOOL(-1),
    GREEN_BANNER(-1, 16, BlockData.class),
    GREEN_BED(-1, 1, BlockData.class),
    GREEN_CANDLE(-1, BlockData.class),
    GREEN_CANDLE_CAKE(-1, BlockData.class),
    GREEN_CARPET(-1),
    GREEN_CONCRETE(-1),
    GREEN_CONCRETE_POWDER(-1),
    GREEN_GLAZED_TERRACOTTA(-1, BlockData.class),
    GREEN_SHULKER_BOX(-1, 1, BlockData.class),
    GREEN_STAINED_GLASS(-1),
    GREEN_STAINED_GLASS_PANE(-1, BlockData.class),
    GREEN_TERRACOTTA(-1),
    GREEN_WALL_BANNER(-1, BlockData.class),
    GREEN_WOOL(-1),
    GRINDSTONE(-1, BlockData.class),
    HANGING_ROOTS(-1, BlockData.class),
    HAY_BLOCK(-1, BlockData.class),
    HEAVY_WEIGHTED_PRESSURE_PLATE(-1, BlockData.class),
    HONEY_BLOCK(-1),
    HONEYCOMB_BLOCK(-1),
    HOPPER(-1, BlockData.class),
    HORN_CORAL(-1, BlockData.class),
    HORN_CORAL_BLOCK(-1),
    HORN_CORAL_FAN(-1, BlockData.class),
    HORN_CORAL_WALL_FAN(-1, BlockData.class),
    ICE(-1),
    INFESTED_CHISELED_STONE_BRICKS(-1),
    INFESTED_COBBLESTONE(-1),
    INFESTED_CRACKED_STONE_BRICKS(-1),
    INFESTED_DEEPSLATE(-1, BlockData.class),
    INFESTED_MOSSY_STONE_BRICKS(-1),
    INFESTED_STONE(-1),
    INFESTED_STONE_BRICKS(-1),
    IRON_BARS(-1, BlockData.class),
    IRON_BLOCK(-1),
    IRON_DOOR(-1, BlockData.class),
    IRON_ORE(-1),
    IRON_TRAPDOOR(-1, BlockData.class),
    JACK_O_LANTERN(-1, BlockData.class),
    JIGSAW(-1, BlockData.class),
    JUKEBOX(-1, BlockData.class),
    JUNGLE_BUTTON(-1, BlockData.class),
    JUNGLE_DOOR(-1, BlockData.class),
    JUNGLE_FENCE(-1, BlockData.class),
    JUNGLE_FENCE_GATE(-1, BlockData.class),
    JUNGLE_HANGING_SIGN(-1, 16, BlockData.class),
    JUNGLE_LEAVES(-1, BlockData.class),
    JUNGLE_LOG(-1, BlockData.class),
    JUNGLE_PLANKS(-1),
    JUNGLE_PRESSURE_PLATE(-1, BlockData.class),
    JUNGLE_SAPLING(-1, BlockData.class),
    JUNGLE_SIGN(-1, 16, BlockData.class),
    JUNGLE_SLAB(-1, BlockData.class),
    JUNGLE_STAIRS(-1, BlockData.class),
    JUNGLE_TRAPDOOR(-1, BlockData.class),
    JUNGLE_WALL_HANGING_SIGN(-1, BlockData.class),
    JUNGLE_WALL_SIGN(-1, 16, BlockData.class),
    JUNGLE_WOOD(-1, BlockData.class),
    KELP(-1, BlockData.class),
    KELP_PLANT(-1),
    LADDER(-1, BlockData.class),
    LANTERN(-1, BlockData.class),
    LAPIS_BLOCK(-1),
    LAPIS_ORE(-1),
    LARGE_AMETHYST_BUD(-1, BlockData.class),
    LARGE_FERN(-1, BlockData.class),
    LAVA(-1, BlockData.class),
    LAVA_CAULDRON(-1),
    LECTERN(-1, BlockData.class),
    LEVER(-1, BlockData.class),
    LIGHT(-1, BlockData.class),
    LIGHT_BLUE_BANNER(-1, 16, BlockData.class),
    LIGHT_BLUE_BED(-1, 1, BlockData.class),
    LIGHT_BLUE_CANDLE(-1, BlockData.class),
    LIGHT_BLUE_CANDLE_CAKE(-1, BlockData.class),
    LIGHT_BLUE_CARPET(-1),
    LIGHT_BLUE_CONCRETE(-1),
    LIGHT_BLUE_CONCRETE_POWDER(-1),
    LIGHT_BLUE_GLAZED_TERRACOTTA(-1, BlockData.class),
    LIGHT_BLUE_SHULKER_BOX(-1, 1, BlockData.class),
    LIGHT_BLUE_STAINED_GLASS(-1),
    LIGHT_BLUE_STAINED_GLASS_PANE(-1, BlockData.class),
    LIGHT_BLUE_TERRACOTTA(-1),
    LIGHT_BLUE_WALL_BANNER(-1, BlockData.class),
    LIGHT_BLUE_WOOL(-1),
    LIGHT_GRAY_BANNER(-1, 16, BlockData.class),
    LIGHT_GRAY_BED(-1, 1, BlockData.class),
    LIGHT_GRAY_CANDLE(-1, BlockData.class),
    LIGHT_GRAY_CANDLE_CAKE(-1, BlockData.class),
    LIGHT_GRAY_CARPET(-1),
    LIGHT_GRAY_CONCRETE(-1),
    LIGHT_GRAY_CONCRETE_POWDER(-1),
    LIGHT_GRAY_GLAZED_TERRACOTTA(-1, BlockData.class),
    LIGHT_GRAY_SHULKER_BOX(-1, 1, BlockData.class),
    LIGHT_GRAY_STAINED_GLASS(-1),
    LIGHT_GRAY_STAINED_GLASS_PANE(-1, BlockData.class),
    LIGHT_GRAY_TERRACOTTA(-1),
    LIGHT_GRAY_WALL_BANNER(-1, BlockData.class),
    LIGHT_GRAY_WOOL(-1),
    LIGHT_WEIGHTED_PRESSURE_PLATE(-1, BlockData.class),
    LIGHTNING_ROD(-1, BlockData.class),
    LILAC(-1, BlockData.class),
    LILY_OF_THE_VALLEY(-1),
    LILY_PAD(-1),
    LIME_BANNER(-1, 16, BlockData.class),
    LIME_BED(-1, 1, BlockData.class),
    LIME_CANDLE(-1, BlockData.class),
    LIME_CANDLE_CAKE(-1, BlockData.class),
    LIME_CARPET(-1),
    LIME_CONCRETE(-1),
    LIME_CONCRETE_POWDER(-1),
    LIME_GLAZED_TERRACOTTA(-1, BlockData.class),
    LIME_SHULKER_BOX(-1, 1, BlockData.class),
    LIME_STAINED_GLASS(-1),
    LIME_STAINED_GLASS_PANE(-1, BlockData.class),
    LIME_TERRACOTTA(-1),
    LIME_WALL_BANNER(-1, BlockData.class),
    LIME_WOOL(-1),
    LODESTONE(-1),
    LOOM(-1, BlockData.class),
    MAGENTA_BANNER(-1, 16, BlockData.class),
    MAGENTA_BED(-1, 1, BlockData.class),
    MAGENTA_CANDLE(-1, BlockData.class),
    MAGENTA_CANDLE_CAKE(-1, BlockData.class),
    MAGENTA_CARPET(-1),
    MAGENTA_CONCRETE(-1),
    MAGENTA_CONCRETE_POWDER(-1),
    MAGENTA_GLAZED_TERRACOTTA(-1, BlockData.class),
    MAGENTA_SHULKER_BOX(-1, 1, BlockData.class),
    MAGENTA_STAINED_GLASS(-1),
    MAGENTA_STAINED_GLASS_PANE(-1, BlockData.class),
    MAGENTA_TERRACOTTA(-1),
    MAGENTA_WALL_BANNER(-1, BlockData.class),
    MAGENTA_WOOL(-1),
    MAGMA_BLOCK(-1),
    MANGROVE_BUTTON(-1, BlockData.class),
    MANGROVE_DOOR(-1, BlockData.class),
    MANGROVE_FENCE(-1, BlockData.class),
    MANGROVE_FENCE_GATE(-1, BlockData.class),
    MANGROVE_HANGING_SIGN(-1, 16, BlockData.class),
    MANGROVE_LEAVES(-1, BlockData.class),
    MANGROVE_LOG(-1, BlockData.class),
    MANGROVE_PLANKS(-1),
    MANGROVE_PRESSURE_PLATE(-1, BlockData.class),
    MANGROVE_PROPAGULE(-1, BlockData.class),
    MANGROVE_ROOTS(-1, BlockData.class),
    MANGROVE_SIGN(-1, 16, BlockData.class),
    MANGROVE_SLAB(-1, BlockData.class),
    MANGROVE_STAIRS(-1, BlockData.class),
    MANGROVE_TRAPDOOR(-1, BlockData.class),
    MANGROVE_WALL_HANGING_SIGN(-1, BlockData.class),
    MANGROVE_WALL_SIGN(-1, 16, BlockData.class),
    MANGROVE_WOOD(-1, BlockData.class),
    MEDIUM_AMETHYST_BUD(-1, BlockData.class),
    MELON(-1),
    MELON_STEM(-1, BlockData.class),
    MOSS_BLOCK(-1),
    MOSS_CARPET(-1),
    MOSSY_COBBLESTONE(-1),
    MOSSY_COBBLESTONE_SLAB(-1, BlockData.class),
    MOSSY_COBBLESTONE_STAIRS(-1, BlockData.class),
    MOSSY_COBBLESTONE_WALL(-1, BlockData.class),
    MOSSY_STONE_BRICK_SLAB(-1, BlockData.class),
    MOSSY_STONE_BRICK_STAIRS(-1, BlockData.class),
    MOSSY_STONE_BRICK_WALL(-1, BlockData.class),
    MOSSY_STONE_BRICKS(-1),
    MOVING_PISTON(-1, BlockData.class),
    MUD(-1),
    MUD_BRICK_SLAB(-1, BlockData.class),
    MUD_BRICK_STAIRS(-1, BlockData.class),
    MUD_BRICK_WALL(-1, BlockData.class),
    MUD_BRICKS(-1),
    MUDDY_MANGROVE_ROOTS(-1, BlockData.class),
    MUSHROOM_STEM(-1, BlockData.class),
    MYCELIUM(-1, BlockData.class),
    NETHER_BRICK_FENCE(-1, BlockData.class),
    NETHER_BRICK_SLAB(-1, BlockData.class),
    NETHER_BRICK_STAIRS(-1, BlockData.class),
    NETHER_BRICK_WALL(-1, BlockData.class),
    NETHER_BRICKS(-1),
    NETHER_GOLD_ORE(-1),
    NETHER_PORTAL(-1, BlockData.class),
    NETHER_QUARTZ_ORE(-1),
    NETHER_SPROUTS(-1),
    NETHER_WART(-1, BlockData.class),
    NETHER_WART_BLOCK(-1),
    NETHERITE_BLOCK(-1),
    NETHERRACK(-1),
    NOTE_BLOCK(-1, BlockData.class),
    OAK_BUTTON(-1, BlockData.class),
    OAK_DOOR(-1, BlockData.class),
    OAK_FENCE(-1, BlockData.class),
    OAK_FENCE_GATE(-1, BlockData.class),
    OAK_HANGING_SIGN(-1, 16, BlockData.class),
    OAK_LEAVES(-1, BlockData.class),
    OAK_LOG(-1, BlockData.class),
    OAK_PLANKS(-1),
    OAK_PRESSURE_PLATE(-1, BlockData.class),
    OAK_SAPLING(-1, BlockData.class),
    OAK_SIGN(-1, 16, BlockData.class),
    OAK_SLAB(-1, BlockData.class),
    OAK_STAIRS(-1, BlockData.class),
    OAK_TRAPDOOR(-1, BlockData.class),
    OAK_WALL_HANGING_SIGN(-1, BlockData.class),
    OAK_WALL_SIGN(-1, 16, BlockData.class),
    OAK_WOOD(-1, BlockData.class),
    OBSERVER(-1, BlockData.class),
    OBSIDIAN(-1),
    OCHRE_FROGLIGHT(-1, BlockData.class),
    ORANGE_BANNER(-1, 16, BlockData.class),
    ORANGE_BED(-1, 1, BlockData.class),
    ORANGE_CANDLE(-1, BlockData.class),
    ORANGE_CANDLE_CAKE(-1, BlockData.class),
    ORANGE_CARPET(-1),
    ORANGE_CONCRETE(-1),
    ORANGE_CONCRETE_POWDER(-1),
    ORANGE_GLAZED_TERRACOTTA(-1, BlockData.class),
    ORANGE_SHULKER_BOX(-1, 1, BlockData.class),
    ORANGE_STAINED_GLASS(-1),
    ORANGE_STAINED_GLASS_PANE(-1, BlockData.class),
    ORANGE_TERRACOTTA(-1),
    ORANGE_TULIP(-1),
    ORANGE_WALL_BANNER(-1, BlockData.class),
    ORANGE_WOOL(-1),
    OXEYE_DAISY(-1),
    @MinecraftExperimental("update 1.21")
    @org.jetbrains.annotations.ApiStatus.Experimental
    OXIDIZED_CHISELED_COPPER(-1),
    OXIDIZED_COPPER(-1),
    @MinecraftExperimental("update 1.21")
    @org.jetbrains.annotations.ApiStatus.Experimental
    OXIDIZED_COPPER_BULB(-1, BlockData.class),
    @MinecraftExperimental("update 1.21")
    @org.jetbrains.annotations.ApiStatus.Experimental
    OXIDIZED_COPPER_DOOR(-1, BlockData.class),
    @MinecraftExperimental("update 1.21")
    @org.jetbrains.annotations.ApiStatus.Experimental
    OXIDIZED_COPPER_GRATE(-1, BlockData.class),
    @MinecraftExperimental("update 1.21")
    @org.jetbrains.annotations.ApiStatus.Experimental
    OXIDIZED_COPPER_TRAPDOOR(-1, BlockData.class),
    OXIDIZED_CUT_COPPER(-1),
    OXIDIZED_CUT_COPPER_SLAB(-1, BlockData.class),
    OXIDIZED_CUT_COPPER_STAIRS(-1, BlockData.class),
    PACKED_ICE(-1),
    PACKED_MUD(-1),
    PEARLESCENT_FROGLIGHT(-1, BlockData.class),
    PEONY(-1, BlockData.class),
    PETRIFIED_OAK_SLAB(-1, BlockData.class),
    PIGLIN_HEAD(-1, BlockData.class),
    PIGLIN_WALL_HEAD(-1, BlockData.class),
    PINK_BANNER(-1, 16, BlockData.class),
    PINK_BED(-1, 1, BlockData.class),
    PINK_CANDLE(-1, BlockData.class),
    PINK_CANDLE_CAKE(-1, BlockData.class),
    PINK_CARPET(-1),
    PINK_CONCRETE(-1),
    PINK_CONCRETE_POWDER(-1),
    PINK_GLAZED_TERRACOTTA(-1, BlockData.class),
    PINK_PETALS(-1, BlockData.class),
    PINK_SHULKER_BOX(-1, 1, BlockData.class),
    PINK_STAINED_GLASS(-1),
    PINK_STAINED_GLASS_PANE(-1, BlockData.class),
    PINK_TERRACOTTA(-1),
    PINK_TULIP(-1),
    PINK_WALL_BANNER(-1, BlockData.class),
    PINK_WOOL(-1),
    PISTON(-1, BlockData.class),
    PISTON_HEAD(-1, BlockData.class),
    PITCHER_CROP(-1, BlockData.class),
    PITCHER_PLANT(-1, BlockData.class),
    PLAYER_HEAD(-1, BlockData.class),
    PLAYER_WALL_HEAD(-1, BlockData.class),
    PODZOL(-1, BlockData.class),
    POINTED_DRIPSTONE(-1, BlockData.class),
    POLISHED_ANDESITE(-1),
    POLISHED_ANDESITE_SLAB(-1, BlockData.class),
    POLISHED_ANDESITE_STAIRS(-1, BlockData.class),
    POLISHED_BASALT(-1, BlockData.class),
    POLISHED_BLACKSTONE(-1),
    POLISHED_BLACKSTONE_BRICK_SLAB(-1, BlockData.class),
    POLISHED_BLACKSTONE_BRICK_STAIRS(-1, BlockData.class),
    POLISHED_BLACKSTONE_BRICK_WALL(-1, BlockData.class),
    POLISHED_BLACKSTONE_BRICKS(-1),
    POLISHED_BLACKSTONE_BUTTON(-1, BlockData.class),
    POLISHED_BLACKSTONE_PRESSURE_PLATE(-1, BlockData.class),
    POLISHED_BLACKSTONE_SLAB(-1, BlockData.class),
    POLISHED_BLACKSTONE_STAIRS(-1, BlockData.class),
    POLISHED_BLACKSTONE_WALL(-1, BlockData.class),
    POLISHED_DEEPSLATE(-1),
    POLISHED_DEEPSLATE_SLAB(-1, BlockData.class),
    POLISHED_DEEPSLATE_STAIRS(-1, BlockData.class),
    POLISHED_DEEPSLATE_WALL(-1, BlockData.class),
    POLISHED_DIORITE(-1),
    POLISHED_DIORITE_SLAB(-1, BlockData.class),
    POLISHED_DIORITE_STAIRS(-1, BlockData.class),
    POLISHED_GRANITE(-1),
    POLISHED_GRANITE_SLAB(-1, BlockData.class),
    POLISHED_GRANITE_STAIRS(-1, BlockData.class),
    @MinecraftExperimental("update 1.21")
    @org.jetbrains.annotations.ApiStatus.Experimental
    POLISHED_TUFF(-1),
    @MinecraftExperimental("update 1.21")
    @org.jetbrains.annotations.ApiStatus.Experimental
    POLISHED_TUFF_SLAB(-1, BlockData.class),
    @MinecraftExperimental("update 1.21")
    @org.jetbrains.annotations.ApiStatus.Experimental
    POLISHED_TUFF_STAIRS(-1, BlockData.class),
    @MinecraftExperimental("update 1.21")
    @org.jetbrains.annotations.ApiStatus.Experimental
    POLISHED_TUFF_WALL(-1, BlockData.class),
    POPPY(-1),
    POTATOES(-1, BlockData.class),
    POTTED_ACACIA_SAPLING(-1),
    POTTED_ALLIUM(-1),
    POTTED_AZALEA_BUSH(-1),
    POTTED_AZURE_BLUET(-1),
    POTTED_BAMBOO(-1),
    POTTED_BIRCH_SAPLING(-1),
    POTTED_BLUE_ORCHID(-1),
    POTTED_BROWN_MUSHROOM(-1),
    POTTED_CACTUS(-1),
    POTTED_CHERRY_SAPLING(-1),
    POTTED_CORNFLOWER(-1),
    POTTED_CRIMSON_FUNGUS(-1),
    POTTED_CRIMSON_ROOTS(-1),
    POTTED_DANDELION(-1),
    POTTED_DARK_OAK_SAPLING(-1),
    POTTED_DEAD_BUSH(-1),
    POTTED_FERN(-1),
    POTTED_FLOWERING_AZALEA_BUSH(-1),
    POTTED_JUNGLE_SAPLING(-1),
    POTTED_LILY_OF_THE_VALLEY(-1),
    POTTED_MANGROVE_PROPAGULE(-1),
    POTTED_OAK_SAPLING(-1),
    POTTED_ORANGE_TULIP(-1),
    POTTED_OXEYE_DAISY(-1),
    POTTED_PINK_TULIP(-1),
    POTTED_POPPY(-1),
    POTTED_RED_MUSHROOM(-1),
    POTTED_RED_TULIP(-1),
    POTTED_SPRUCE_SAPLING(-1),
    POTTED_TORCHFLOWER(-1),
    POTTED_WARPED_FUNGUS(-1),
    POTTED_WARPED_ROOTS(-1),
    POTTED_WHITE_TULIP(-1),
    POTTED_WITHER_ROSE(-1),
    POWDER_SNOW(-1),
    POWDER_SNOW_CAULDRON(-1, BlockData.class),
    POWERED_RAIL(-1, BlockData.class),
    PRISMARINE(-1),
    PRISMARINE_BRICK_SLAB(-1, BlockData.class),
    PRISMARINE_BRICK_STAIRS(-1, BlockData.class),
    PRISMARINE_BRICKS(-1),
    PRISMARINE_SLAB(-1, BlockData.class),
    PRISMARINE_STAIRS(-1, BlockData.class),
    PRISMARINE_WALL(-1, BlockData.class),
    PUMPKIN(-1),
    PUMPKIN_STEM(-1, BlockData.class),
    PURPLE_BANNER(-1, 16, BlockData.class),
    PURPLE_BED(-1, 1, BlockData.class),
    PURPLE_CANDLE(-1, BlockData.class),
    PURPLE_CANDLE_CAKE(-1, BlockData.class),
    PURPLE_CARPET(-1),
    PURPLE_CONCRETE(-1),
    PURPLE_CONCRETE_POWDER(-1),
    PURPLE_GLAZED_TERRACOTTA(-1, BlockData.class),
    PURPLE_SHULKER_BOX(-1, 1, BlockData.class),
    PURPLE_STAINED_GLASS(-1),
    PURPLE_STAINED_GLASS_PANE(-1, BlockData.class),
    PURPLE_TERRACOTTA(-1),
    PURPLE_WALL_BANNER(-1, BlockData.class),
    PURPLE_WOOL(-1),
    PURPUR_BLOCK(-1),
    PURPUR_PILLAR(-1, BlockData.class),
    PURPUR_SLAB(-1, BlockData.class),
    PURPUR_STAIRS(-1, BlockData.class),
    QUARTZ_BLOCK(-1),
    QUARTZ_BRICKS(-1),
    QUARTZ_PILLAR(-1, BlockData.class),
    QUARTZ_SLAB(-1, BlockData.class),
    QUARTZ_STAIRS(-1, BlockData.class),
    RAIL(-1, BlockData.class),
    RAW_COPPER_BLOCK(-1),
    RAW_GOLD_BLOCK(-1),
    RAW_IRON_BLOCK(-1),
    RED_BANNER(-1, 16, BlockData.class),
    RED_BED(-1, 1, BlockData.class),
    RED_CANDLE(-1, BlockData.class),
    RED_CANDLE_CAKE(-1, BlockData.class),
    RED_CARPET(-1),
    RED_CONCRETE(-1),
    RED_CONCRETE_POWDER(-1),
    RED_GLAZED_TERRACOTTA(-1, BlockData.class),
    RED_MUSHROOM(-1),
    RED_MUSHROOM_BLOCK(-1, BlockData.class),
    RED_NETHER_BRICK_SLAB(-1, BlockData.class),
    RED_NETHER_BRICK_STAIRS(-1, BlockData.class),
    RED_NETHER_BRICK_WALL(-1, BlockData.class),
    RED_NETHER_BRICKS(-1),
    RED_SAND(-1),
    RED_SANDSTONE(-1),
    RED_SANDSTONE_SLAB(-1, BlockData.class),
    RED_SANDSTONE_STAIRS(-1, BlockData.class),
    RED_SANDSTONE_WALL(-1, BlockData.class),
    RED_SHULKER_BOX(-1, 1, BlockData.class),
    RED_STAINED_GLASS(-1),
    RED_STAINED_GLASS_PANE(-1, BlockData.class),
    RED_TERRACOTTA(-1),
    RED_TULIP(-1),
    RED_WALL_BANNER(-1, BlockData.class),
    RED_WOOL(-1),
    REDSTONE_BLOCK(-1),
    REDSTONE_LAMP(-1, BlockData.class),
    REDSTONE_ORE(-1, BlockData.class),
    REDSTONE_TORCH(-1, BlockData.class),
    REDSTONE_WALL_TORCH(-1, BlockData.class),
    REDSTONE_WIRE(-1, BlockData.class),
    REINFORCED_DEEPSLATE(-1),
    REPEATER(-1, BlockData.class),
    REPEATING_COMMAND_BLOCK(-1, BlockData.class),
    RESPAWN_ANCHOR(-1, BlockData.class),
    ROOTED_DIRT(-1),
    ROSE_BUSH(-1, BlockData.class),
    SAND(-1),
    SANDSTONE(-1),
    SANDSTONE_SLAB(-1, BlockData.class),
    SANDSTONE_STAIRS(-1, BlockData.class),
    SANDSTONE_WALL(-1, BlockData.class),
    SCAFFOLDING(-1, BlockData.class),
    SCULK(-1),
    SCULK_CATALYST(-1, BlockData.class),
    SCULK_SENSOR(-1, BlockData.class),
    SCULK_SHRIEKER(-1, BlockData.class),
    SCULK_VEIN(-1, BlockData.class),
    SEA_LANTERN(-1),
    SEA_PICKLE(-1, BlockData.class),
    SEAGRASS(-1),
    SHORT_GRASS(-1),
    SHROOMLIGHT(-1),
    SHULKER_BOX(-1, 1, BlockData.class),
    SKELETON_SKULL(-1, BlockData.class),
    SKELETON_WALL_SKULL(-1, BlockData.class),
    SLIME_BLOCK(-1),
    SMALL_AMETHYST_BUD(-1, BlockData.class),
    SMALL_DRIPLEAF(-1, BlockData.class),
    SMITHING_TABLE(-1),
    SMOKER(-1, BlockData.class),
    SMOOTH_BASALT(-1),
    SMOOTH_QUARTZ(-1),
    SMOOTH_QUARTZ_SLAB(-1, BlockData.class),
    SMOOTH_QUARTZ_STAIRS(-1, BlockData.class),
    SMOOTH_RED_SANDSTONE(-1),
    SMOOTH_RED_SANDSTONE_SLAB(-1, BlockData.class),
    SMOOTH_RED_SANDSTONE_STAIRS(-1, BlockData.class),
    SMOOTH_SANDSTONE(-1),
    SMOOTH_SANDSTONE_SLAB(-1, BlockData.class),
    SMOOTH_SANDSTONE_STAIRS(-1, BlockData.class),
    SMOOTH_STONE(-1),
    SMOOTH_STONE_SLAB(-1, BlockData.class),
    SNIFFER_EGG(-1, BlockData.class),
    SNOW(-1, BlockData.class),
    SNOW_BLOCK(-1),
    SOUL_CAMPFIRE(-1, BlockData.class),
    SOUL_FIRE(-1),
    SOUL_LANTERN(-1, BlockData.class),
    SOUL_SAND(-1),
    SOUL_SOIL(-1),
    SOUL_TORCH(-1),
    SOUL_WALL_TORCH(-1, BlockData.class),
    SPAWNER(-1),
    SPONGE(-1),
    SPORE_BLOSSOM(-1),
    SPRUCE_BUTTON(-1, BlockData.class),
    SPRUCE_DOOR(-1, BlockData.class),
    SPRUCE_FENCE(-1, BlockData.class),
    SPRUCE_FENCE_GATE(-1, BlockData.class),
    SPRUCE_HANGING_SIGN(-1, 16, BlockData.class),
    SPRUCE_LEAVES(-1, BlockData.class),
    SPRUCE_LOG(-1, BlockData.class),
    SPRUCE_PLANKS(-1),
    SPRUCE_PRESSURE_PLATE(-1, BlockData.class),
    SPRUCE_SAPLING(-1, BlockData.class),
    SPRUCE_SIGN(-1, 16, BlockData.class),
    SPRUCE_SLAB(-1, BlockData.class),
    SPRUCE_STAIRS(-1, BlockData.class),
    SPRUCE_TRAPDOOR(-1, BlockData.class),
    SPRUCE_WALL_HANGING_SIGN(-1, BlockData.class),
    SPRUCE_WALL_SIGN(-1, 16, BlockData.class),
    SPRUCE_WOOD(-1, BlockData.class),
    STICKY_PISTON(-1, BlockData.class),
    STONE(-1),
    STONE_BRICK_SLAB(-1, BlockData.class),
    STONE_BRICK_STAIRS(-1, BlockData.class),
    STONE_BRICK_WALL(-1, BlockData.class),
    STONE_BRICKS(-1),
    STONE_BUTTON(-1, BlockData.class),
    STONE_PRESSURE_PLATE(-1, BlockData.class),
    STONE_SLAB(-1, BlockData.class),
    STONE_STAIRS(-1, BlockData.class),
    STONECUTTER(-1, BlockData.class),
    STRIPPED_ACACIA_LOG(-1, BlockData.class),
    STRIPPED_ACACIA_WOOD(-1, BlockData.class),
    STRIPPED_BAMBOO_BLOCK(-1, BlockData.class),
    STRIPPED_BIRCH_LOG(-1, BlockData.class),
    STRIPPED_BIRCH_WOOD(-1, BlockData.class),
    STRIPPED_CHERRY_LOG(-1, BlockData.class),
    STRIPPED_CHERRY_WOOD(-1, BlockData.class),
    STRIPPED_CRIMSON_HYPHAE(-1, BlockData.class),
    STRIPPED_CRIMSON_STEM(-1, BlockData.class),
    STRIPPED_DARK_OAK_LOG(-1, BlockData.class),
    STRIPPED_DARK_OAK_WOOD(-1, BlockData.class),
    STRIPPED_JUNGLE_LOG(-1, BlockData.class),
    STRIPPED_JUNGLE_WOOD(-1, BlockData.class),
    STRIPPED_MANGROVE_LOG(-1, BlockData.class),
    STRIPPED_MANGROVE_WOOD(-1, BlockData.class),
    STRIPPED_OAK_LOG(-1, BlockData.class),
    STRIPPED_OAK_WOOD(-1, BlockData.class),
    STRIPPED_SPRUCE_LOG(-1, BlockData.class),
    STRIPPED_SPRUCE_WOOD(-1, BlockData.class),
    STRIPPED_WARPED_HYPHAE(-1, BlockData.class),
    STRIPPED_WARPED_STEM(-1, BlockData.class),
    STRUCTURE_BLOCK(-1, BlockData.class),
    STRUCTURE_VOID(-1),
    SUGAR_CANE(-1, BlockData.class),
    SUNFLOWER(-1, BlockData.class),
    SUSPICIOUS_GRAVEL(-1, BlockData.class),
    SUSPICIOUS_SAND(-1, BlockData.class),
    SWEET_BERRY_BUSH(-1, BlockData.class),
    TALL_GRASS(-1, BlockData.class),
    TALL_SEAGRASS(-1, BlockData.class),
    TARGET(-1, BlockData.class),
    TERRACOTTA(-1),
    TINTED_GLASS(-1),
    TNT(-1, BlockData.class),
    TORCH(-1),
    TORCHFLOWER(-1),
    TORCHFLOWER_CROP(-1, BlockData.class),
    TRAPPED_CHEST(-1, BlockData.class),
    @MinecraftExperimental("update 1.21")
    @org.jetbrains.annotations.ApiStatus.Experimental
    TRIAL_SPAWNER(-1, BlockData.class),
    TRIPWIRE(-1, BlockData.class),
    TRIPWIRE_HOOK(-1, BlockData.class),
    TUBE_CORAL(-1, BlockData.class),
    TUBE_CORAL_BLOCK(-1),
    TUBE_CORAL_FAN(-1, BlockData.class),
    TUBE_CORAL_WALL_FAN(-1, BlockData.class),
    TUFF(-1),
    @MinecraftExperimental("update 1.21")
    @org.jetbrains.annotations.ApiStatus.Experimental
    TUFF_BRICK_SLAB(-1, BlockData.class),
    @MinecraftExperimental("update 1.21")
    @org.jetbrains.annotations.ApiStatus.Experimental
    TUFF_BRICK_STAIRS(-1, BlockData.class),
    @MinecraftExperimental("update 1.21")
    @org.jetbrains.annotations.ApiStatus.Experimental
    TUFF_BRICK_WALL(-1, BlockData.class),
    @MinecraftExperimental("update 1.21")
    @org.jetbrains.annotations.ApiStatus.Experimental
    TUFF_BRICKS(-1),
    @MinecraftExperimental("update 1.21")
    @org.jetbrains.annotations.ApiStatus.Experimental
    TUFF_SLAB(-1, BlockData.class),
    @MinecraftExperimental("update 1.21")
    @org.jetbrains.annotations.ApiStatus.Experimental
    TUFF_STAIRS(-1, BlockData.class),
    @MinecraftExperimental("update 1.21")
    @org.jetbrains.annotations.ApiStatus.Experimental
    TUFF_WALL(-1, BlockData.class),
    TURTLE_EGG(-1, BlockData.class),
    TWISTING_VINES(-1, BlockData.class),
    TWISTING_VINES_PLANT(-1),
    VERDANT_FROGLIGHT(-1, BlockData.class),
    VINE(-1, BlockData.class),
    VOID_AIR(-1),
    WALL_TORCH(-1, BlockData.class),
    WARPED_BUTTON(-1, BlockData.class),
    WARPED_DOOR(-1, BlockData.class),
    WARPED_FENCE(-1, BlockData.class),
    WARPED_FENCE_GATE(-1, BlockData.class),
    WARPED_FUNGUS(-1),
    WARPED_HANGING_SIGN(-1, 16, BlockData.class),
    WARPED_HYPHAE(-1, BlockData.class),
    WARPED_NYLIUM(-1),
    WARPED_PLANKS(-1),
    WARPED_PRESSURE_PLATE(-1, BlockData.class),
    WARPED_ROOTS(-1),
    WARPED_SIGN(-1, 16, BlockData.class),
    WARPED_SLAB(-1, BlockData.class),
    WARPED_STAIRS(-1, BlockData.class),
    WARPED_STEM(-1, BlockData.class),
    WARPED_TRAPDOOR(-1, BlockData.class),
    WARPED_WALL_HANGING_SIGN(-1, BlockData.class),
    WARPED_WALL_SIGN(-1, 16, BlockData.class),
    WARPED_WART_BLOCK(-1),
    WATER(-1, BlockData.class),
    WATER_CAULDRON(-1, BlockData.class),
    @MinecraftExperimental("update 1.21")
    @org.jetbrains.annotations.ApiStatus.Experimental
    WAXED_CHISELED_COPPER(-1),
    WAXED_COPPER_BLOCK(-1),
    @MinecraftExperimental("update 1.21")
    @org.jetbrains.annotations.ApiStatus.Experimental
    WAXED_COPPER_BULB(-1, BlockData.class),
    @MinecraftExperimental("update 1.21")
    @org.jetbrains.annotations.ApiStatus.Experimental
    WAXED_COPPER_DOOR(-1, BlockData.class),
    @MinecraftExperimental("update 1.21")
    @org.jetbrains.annotations.ApiStatus.Experimental
    WAXED_COPPER_GRATE(-1, BlockData.class),
    @MinecraftExperimental("update 1.21")
    @org.jetbrains.annotations.ApiStatus.Experimental
    WAXED_COPPER_TRAPDOOR(-1, BlockData.class),
    WAXED_CUT_COPPER(-1),
    WAXED_CUT_COPPER_SLAB(-1, BlockData.class),
    WAXED_CUT_COPPER_STAIRS(-1, BlockData.class),
    @MinecraftExperimental("update 1.21")
    @org.jetbrains.annotations.ApiStatus.Experimental
    WAXED_EXPOSED_CHISELED_COPPER(-1),
    WAXED_EXPOSED_COPPER(-1),
    @MinecraftExperimental("update 1.21")
    @org.jetbrains.annotations.ApiStatus.Experimental
    WAXED_EXPOSED_COPPER_BULB(-1, BlockData.class),
    @MinecraftExperimental("update 1.21")
    @org.jetbrains.annotations.ApiStatus.Experimental
    WAXED_EXPOSED_COPPER_DOOR(-1, BlockData.class),
    @MinecraftExperimental("update 1.21")
    @org.jetbrains.annotations.ApiStatus.Experimental
    WAXED_EXPOSED_COPPER_GRATE(-1, BlockData.class),
    @MinecraftExperimental("update 1.21")
    @org.jetbrains.annotations.ApiStatus.Experimental
    WAXED_EXPOSED_COPPER_TRAPDOOR(-1, BlockData.class),
    WAXED_EXPOSED_CUT_COPPER(-1),
    WAXED_EXPOSED_CUT_COPPER_SLAB(-1, BlockData.class),
    WAXED_EXPOSED_CUT_COPPER_STAIRS(-1, BlockData.class),
    @MinecraftExperimental("update 1.21")
    @org.jetbrains.annotations.ApiStatus.Experimental
    WAXED_OXIDIZED_CHISELED_COPPER(-1),
    WAXED_OXIDIZED_COPPER(-1),
    @MinecraftExperimental("update 1.21")
    @org.jetbrains.annotations.ApiStatus.Experimental
    WAXED_OXIDIZED_COPPER_BULB(-1, BlockData.class),
    @MinecraftExperimental("update 1.21")
    @org.jetbrains.annotations.ApiStatus.Experimental
    WAXED_OXIDIZED_COPPER_DOOR(-1, BlockData.class),
    @MinecraftExperimental("update 1.21")
    @org.jetbrains.annotations.ApiStatus.Experimental
    WAXED_OXIDIZED_COPPER_GRATE(-1, BlockData.class),
    @MinecraftExperimental("update 1.21")
    @org.jetbrains.annotations.ApiStatus.Experimental
    WAXED_OXIDIZED_COPPER_TRAPDOOR(-1, BlockData.class),
    WAXED_OXIDIZED_CUT_COPPER(-1),
    WAXED_OXIDIZED_CUT_COPPER_SLAB(-1, BlockData.class),
    WAXED_OXIDIZED_CUT_COPPER_STAIRS(-1, BlockData.class),
    @MinecraftExperimental("update 1.21")
    @org.jetbrains.annotations.ApiStatus.Experimental
    WAXED_WEATHERED_CHISELED_COPPER(-1),
    WAXED_WEATHERED_COPPER(-1),
    @MinecraftExperimental("update 1.21")
    @org.jetbrains.annotations.ApiStatus.Experimental
    WAXED_WEATHERED_COPPER_BULB(-1, BlockData.class),
    @MinecraftExperimental("update 1.21")
    @org.jetbrains.annotations.ApiStatus.Experimental
    WAXED_WEATHERED_COPPER_DOOR(-1, BlockData.class),
    @MinecraftExperimental("update 1.21")
    @org.jetbrains.annotations.ApiStatus.Experimental
    WAXED_WEATHERED_COPPER_GRATE(-1, BlockData.class),
    @MinecraftExperimental("update 1.21")
    @org.jetbrains.annotations.ApiStatus.Experimental
    WAXED_WEATHERED_COPPER_TRAPDOOR(-1, BlockData.class),
    WAXED_WEATHERED_CUT_COPPER(-1),
    WAXED_WEATHERED_CUT_COPPER_SLAB(-1, BlockData.class),
    WAXED_WEATHERED_CUT_COPPER_STAIRS(-1, BlockData.class),
    @MinecraftExperimental("update 1.21")
    @org.jetbrains.annotations.ApiStatus.Experimental
    WEATHERED_CHISELED_COPPER(-1),
    WEATHERED_COPPER(-1),
    @MinecraftExperimental("update 1.21")
    @org.jetbrains.annotations.ApiStatus.Experimental
    WEATHERED_COPPER_BULB(-1, BlockData.class),
    @MinecraftExperimental("update 1.21")
    @org.jetbrains.annotations.ApiStatus.Experimental
    WEATHERED_COPPER_DOOR(-1, BlockData.class),
    @MinecraftExperimental("update 1.21")
    @org.jetbrains.annotations.ApiStatus.Experimental
    WEATHERED_COPPER_GRATE(-1, BlockData.class),
    @MinecraftExperimental("update 1.21")
    @org.jetbrains.annotations.ApiStatus.Experimental
    WEATHERED_COPPER_TRAPDOOR(-1, BlockData.class),
    WEATHERED_CUT_COPPER(-1),
    WEATHERED_CUT_COPPER_SLAB(-1, BlockData.class),
    WEATHERED_CUT_COPPER_STAIRS(-1, BlockData.class),
    WEEPING_VINES(-1, BlockData.class),
    WEEPING_VINES_PLANT(-1),
    WET_SPONGE(-1),
    WHEAT(-1, BlockData.class),
    WHITE_BANNER(-1, 16, BlockData.class),
    WHITE_BED(-1, 1, BlockData.class),
    WHITE_CANDLE(-1, BlockData.class),
    WHITE_CANDLE_CAKE(-1, BlockData.class),
    WHITE_CARPET(-1),
    WHITE_CONCRETE(-1),
    WHITE_CONCRETE_POWDER(-1),
    WHITE_GLAZED_TERRACOTTA(-1, BlockData.class),
    WHITE_SHULKER_BOX(-1, 1, BlockData.class),
    WHITE_STAINED_GLASS(-1),
    WHITE_STAINED_GLASS_PANE(-1, BlockData.class),
    WHITE_TERRACOTTA(-1),
    WHITE_TULIP(-1),
    WHITE_WALL_BANNER(-1, BlockData.class),
    WHITE_WOOL(-1),
    WITHER_ROSE(-1),
    WITHER_SKELETON_SKULL(-1, BlockData.class),
    WITHER_SKELETON_WALL_SKULL(-1, BlockData.class),
    YELLOW_BANNER(-1, 16, BlockData.class),
    YELLOW_BED(-1, 1, BlockData.class),
    YELLOW_CANDLE(-1, BlockData.class),
    YELLOW_CANDLE_CAKE(-1, BlockData.class),
    YELLOW_CARPET(-1),
    YELLOW_CONCRETE(-1),
    YELLOW_CONCRETE_POWDER(-1),
    YELLOW_GLAZED_TERRACOTTA(-1, BlockData.class),
    YELLOW_SHULKER_BOX(-1, 1, BlockData.class),
    YELLOW_STAINED_GLASS(-1),
    YELLOW_STAINED_GLASS_PANE(-1, BlockData.class),
    YELLOW_TERRACOTTA(-1),
    YELLOW_WALL_BANNER(-1, BlockData.class),
    YELLOW_WOOL(-1),
    ZOMBIE_HEAD(-1, BlockData.class),
    ZOMBIE_WALL_HEAD(-1, BlockData.class),
    // Paper end - Generated/Blocks
    // ----- Legacy Separator -----
    @Deprecated
    LEGACY_AIR(0, 0),
    @Deprecated
    LEGACY_STONE(1),
    @Deprecated
    LEGACY_GRASS(2),
    @Deprecated
    LEGACY_DIRT(3),
    @Deprecated
    LEGACY_COBBLESTONE(4),
    @Deprecated
    LEGACY_WOOD(5, org.bukkit.material.Wood.class),
    @Deprecated
    LEGACY_SAPLING(6, org.bukkit.material.Sapling.class),
    @Deprecated
    LEGACY_BEDROCK(7),
    @Deprecated
    LEGACY_WATER(8, org.bukkit.material.MaterialData.class),
    @Deprecated
    LEGACY_STATIONARY_WATER(9, org.bukkit.material.MaterialData.class),
    @Deprecated
    LEGACY_LAVA(10, org.bukkit.material.MaterialData.class),
    @Deprecated
    LEGACY_STATIONARY_LAVA(11, org.bukkit.material.MaterialData.class),
    @Deprecated
    LEGACY_SAND(12),
    @Deprecated
    LEGACY_GRAVEL(13),
    @Deprecated
    LEGACY_GOLD_ORE(14),
    @Deprecated
    LEGACY_IRON_ORE(15),
    @Deprecated
    LEGACY_COAL_ORE(16),
    @Deprecated
    LEGACY_LOG(17, org.bukkit.material.Tree.class),
    @Deprecated
    LEGACY_LEAVES(18, org.bukkit.material.Leaves.class),
    @Deprecated
    LEGACY_SPONGE(19),
    @Deprecated
    LEGACY_GLASS(20),
    @Deprecated
    LEGACY_LAPIS_ORE(21),
    @Deprecated
    LEGACY_LAPIS_BLOCK(22),
    @Deprecated
    LEGACY_DISPENSER(23, org.bukkit.material.Dispenser.class),
    @Deprecated
    LEGACY_SANDSTONE(24, org.bukkit.material.Sandstone.class),
    @Deprecated
    LEGACY_NOTE_BLOCK(25),
    @Deprecated
    LEGACY_BED_BLOCK(26, org.bukkit.material.Bed.class),
    @Deprecated
    LEGACY_POWERED_RAIL(27, org.bukkit.material.PoweredRail.class),
    @Deprecated
    LEGACY_DETECTOR_RAIL(28, org.bukkit.material.DetectorRail.class),
    @Deprecated
    LEGACY_PISTON_STICKY_BASE(29, org.bukkit.material.PistonBaseMaterial.class),
    @Deprecated
    LEGACY_WEB(30),
    @Deprecated
    LEGACY_LONG_GRASS(31, org.bukkit.material.LongGrass.class),
    @Deprecated
    LEGACY_DEAD_BUSH(32),
    @Deprecated
    LEGACY_PISTON_BASE(33, org.bukkit.material.PistonBaseMaterial.class),
    @Deprecated
    LEGACY_PISTON_EXTENSION(34, org.bukkit.material.PistonExtensionMaterial.class),
    @Deprecated
    LEGACY_WOOL(35, org.bukkit.material.Wool.class),
    @Deprecated
    LEGACY_PISTON_MOVING_PIECE(36),
    @Deprecated
    LEGACY_YELLOW_FLOWER(37),
    @Deprecated
    LEGACY_RED_ROSE(38),
    @Deprecated
    LEGACY_BROWN_MUSHROOM(39),
    @Deprecated
    LEGACY_RED_MUSHROOM(40),
    @Deprecated
    LEGACY_GOLD_BLOCK(41),
    @Deprecated
    LEGACY_IRON_BLOCK(42),
    @Deprecated
    LEGACY_DOUBLE_STEP(43, org.bukkit.material.Step.class),
    @Deprecated
    LEGACY_STEP(44, org.bukkit.material.Step.class),
    @Deprecated
    LEGACY_BRICK(45),
    @Deprecated
    LEGACY_TNT(46),
    @Deprecated
    LEGACY_BOOKSHELF(47),
    @Deprecated
    LEGACY_MOSSY_COBBLESTONE(48),
    @Deprecated
    LEGACY_OBSIDIAN(49),
    @Deprecated
    LEGACY_TORCH(50, org.bukkit.material.Torch.class),
    @Deprecated
    LEGACY_FIRE(51),
    @Deprecated
    LEGACY_MOB_SPAWNER(52),
    @Deprecated
    LEGACY_WOOD_STAIRS(53, org.bukkit.material.Stairs.class),
    @Deprecated
    LEGACY_CHEST(54, org.bukkit.material.Chest.class),
    @Deprecated
    LEGACY_REDSTONE_WIRE(55, org.bukkit.material.RedstoneWire.class),
    @Deprecated
    LEGACY_DIAMOND_ORE(56),
    @Deprecated
    LEGACY_DIAMOND_BLOCK(57),
    @Deprecated
    LEGACY_WORKBENCH(58),
    @Deprecated
    LEGACY_CROPS(59, org.bukkit.material.Crops.class),
    @Deprecated
    LEGACY_SOIL(60, org.bukkit.material.MaterialData.class),
    @Deprecated
    LEGACY_FURNACE(61, org.bukkit.material.Furnace.class),
    @Deprecated
    LEGACY_BURNING_FURNACE(62, org.bukkit.material.Furnace.class),
    @Deprecated
    LEGACY_SIGN_POST(63, 64, org.bukkit.material.Sign.class),
    @Deprecated
    LEGACY_WOODEN_DOOR(64, org.bukkit.material.Door.class),
    @Deprecated
    LEGACY_LADDER(65, org.bukkit.material.Ladder.class),
    @Deprecated
    LEGACY_RAILS(66, org.bukkit.material.Rails.class),
    @Deprecated
    LEGACY_COBBLESTONE_STAIRS(67, org.bukkit.material.Stairs.class),
    @Deprecated
    LEGACY_WALL_SIGN(68, 64, org.bukkit.material.Sign.class),
    @Deprecated
    LEGACY_LEVER(69, org.bukkit.material.Lever.class),
    @Deprecated
    LEGACY_STONE_PLATE(70, org.bukkit.material.PressurePlate.class),
    @Deprecated
    LEGACY_IRON_DOOR_BLOCK(71, org.bukkit.material.Door.class),
    @Deprecated
    LEGACY_WOOD_PLATE(72, org.bukkit.material.PressurePlate.class),
    @Deprecated
    LEGACY_REDSTONE_ORE(73),
    @Deprecated
    LEGACY_GLOWING_REDSTONE_ORE(74),
    @Deprecated
    LEGACY_REDSTONE_TORCH_OFF(75, org.bukkit.material.RedstoneTorch.class),
    @Deprecated
    LEGACY_REDSTONE_TORCH_ON(76, org.bukkit.material.RedstoneTorch.class),
    @Deprecated
    LEGACY_STONE_BUTTON(77, org.bukkit.material.Button.class),
    @Deprecated
    LEGACY_SNOW(78),
    @Deprecated
    LEGACY_ICE(79),
    @Deprecated
    LEGACY_SNOW_BLOCK(80),
    @Deprecated
    LEGACY_CACTUS(81, org.bukkit.material.MaterialData.class),
    @Deprecated
    LEGACY_CLAY(82),
    @Deprecated
    LEGACY_SUGAR_CANE_BLOCK(83, org.bukkit.material.MaterialData.class),
    @Deprecated
    LEGACY_JUKEBOX(84),
    @Deprecated
    LEGACY_FENCE(85),
    @Deprecated
    LEGACY_PUMPKIN(86, org.bukkit.material.Pumpkin.class),
    @Deprecated
    LEGACY_NETHERRACK(87),
    @Deprecated
    LEGACY_SOUL_SAND(88),
    @Deprecated
    LEGACY_GLOWSTONE(89),
    @Deprecated
    LEGACY_PORTAL(90),
    @Deprecated
    LEGACY_JACK_O_LANTERN(91, org.bukkit.material.Pumpkin.class),
    @Deprecated
    LEGACY_CAKE_BLOCK(92, 64, org.bukkit.material.Cake.class),
    @Deprecated
    LEGACY_DIODE_BLOCK_OFF(93, org.bukkit.material.Diode.class),
    @Deprecated
    LEGACY_DIODE_BLOCK_ON(94, org.bukkit.material.Diode.class),
    @Deprecated
    LEGACY_STAINED_GLASS(95),
    @Deprecated
    LEGACY_TRAP_DOOR(96, org.bukkit.material.TrapDoor.class),
    @Deprecated
    LEGACY_MONSTER_EGGS(97, org.bukkit.material.MonsterEggs.class),
    @Deprecated
    LEGACY_SMOOTH_BRICK(98, org.bukkit.material.SmoothBrick.class),
    @Deprecated
    LEGACY_HUGE_MUSHROOM_1(99, org.bukkit.material.Mushroom.class),
    @Deprecated
    LEGACY_HUGE_MUSHROOM_2(100, org.bukkit.material.Mushroom.class),
    @Deprecated
    LEGACY_IRON_FENCE(101),
    @Deprecated
    LEGACY_THIN_GLASS(102),
    @Deprecated
    LEGACY_MELON_BLOCK(103),
    @Deprecated
    LEGACY_PUMPKIN_STEM(104, org.bukkit.material.MaterialData.class),
    @Deprecated
    LEGACY_MELON_STEM(105, org.bukkit.material.MaterialData.class),
    @Deprecated
    LEGACY_VINE(106, org.bukkit.material.Vine.class),
    @Deprecated
    LEGACY_FENCE_GATE(107, org.bukkit.material.Gate.class),
    @Deprecated
    LEGACY_BRICK_STAIRS(108, org.bukkit.material.Stairs.class),
    @Deprecated
    LEGACY_SMOOTH_STAIRS(109, org.bukkit.material.Stairs.class),
    @Deprecated
    LEGACY_MYCEL(110),
    @Deprecated
    LEGACY_WATER_LILY(111),
    @Deprecated
    LEGACY_NETHER_BRICK(112),
    @Deprecated
    LEGACY_NETHER_FENCE(113),
    @Deprecated
    LEGACY_NETHER_BRICK_STAIRS(114, org.bukkit.material.Stairs.class),
    @Deprecated
    LEGACY_NETHER_WARTS(115, org.bukkit.material.NetherWarts.class),
    @Deprecated
    LEGACY_ENCHANTMENT_TABLE(116),
    @Deprecated
    LEGACY_BREWING_STAND(117, org.bukkit.material.MaterialData.class),
    @Deprecated
    LEGACY_CAULDRON(118, org.bukkit.material.Cauldron.class),
    @Deprecated
    LEGACY_ENDER_PORTAL(119),
    @Deprecated
    LEGACY_ENDER_PORTAL_FRAME(120),
    @Deprecated
    LEGACY_ENDER_STONE(121),
    @Deprecated
    LEGACY_DRAGON_EGG(122),
    @Deprecated
    LEGACY_REDSTONE_LAMP_OFF(123),
    @Deprecated
    LEGACY_REDSTONE_LAMP_ON(124),
    @Deprecated
    LEGACY_WOOD_DOUBLE_STEP(125, org.bukkit.material.Wood.class),
    @Deprecated
    LEGACY_WOOD_STEP(126, org.bukkit.material.WoodenStep.class),
    @Deprecated
    LEGACY_COCOA(127, org.bukkit.material.CocoaPlant.class),
    @Deprecated
    LEGACY_SANDSTONE_STAIRS(128, org.bukkit.material.Stairs.class),
    @Deprecated
    LEGACY_EMERALD_ORE(129),
    @Deprecated
    LEGACY_ENDER_CHEST(130, org.bukkit.material.EnderChest.class),
    @Deprecated
    LEGACY_TRIPWIRE_HOOK(131, org.bukkit.material.TripwireHook.class),
    @Deprecated
    LEGACY_TRIPWIRE(132, org.bukkit.material.Tripwire.class),
    @Deprecated
    LEGACY_EMERALD_BLOCK(133),
    @Deprecated
    LEGACY_SPRUCE_WOOD_STAIRS(134, org.bukkit.material.Stairs.class),
    @Deprecated
    LEGACY_BIRCH_WOOD_STAIRS(135, org.bukkit.material.Stairs.class),
    @Deprecated
    LEGACY_JUNGLE_WOOD_STAIRS(136, org.bukkit.material.Stairs.class),
    @Deprecated
    LEGACY_COMMAND(137, org.bukkit.material.Command.class),
    @Deprecated
    LEGACY_BEACON(138),
    @Deprecated
    LEGACY_COBBLE_WALL(139),
    @Deprecated
    LEGACY_FLOWER_POT(140, org.bukkit.material.FlowerPot.class),
    @Deprecated
    LEGACY_CARROT(141, org.bukkit.material.Crops.class),
    @Deprecated
    LEGACY_POTATO(142, org.bukkit.material.Crops.class),
    @Deprecated
    LEGACY_WOOD_BUTTON(143, org.bukkit.material.Button.class),
    @Deprecated
    LEGACY_SKULL(144, org.bukkit.material.Skull.class),
    @Deprecated
    LEGACY_ANVIL(145),
    @Deprecated
    LEGACY_TRAPPED_CHEST(146, org.bukkit.material.Chest.class),
    @Deprecated
    LEGACY_GOLD_PLATE(147),
    @Deprecated
    LEGACY_IRON_PLATE(148),
    @Deprecated
    LEGACY_REDSTONE_COMPARATOR_OFF(149, org.bukkit.material.Comparator.class),
    @Deprecated
    LEGACY_REDSTONE_COMPARATOR_ON(150, org.bukkit.material.Comparator.class),
    @Deprecated
    LEGACY_DAYLIGHT_DETECTOR(151),
    @Deprecated
    LEGACY_REDSTONE_BLOCK(152),
    @Deprecated
    LEGACY_QUARTZ_ORE(153),
    @Deprecated
    LEGACY_HOPPER(154, org.bukkit.material.Hopper.class),
    @Deprecated
    LEGACY_QUARTZ_BLOCK(155),
    @Deprecated
    LEGACY_QUARTZ_STAIRS(156, org.bukkit.material.Stairs.class),
    @Deprecated
    LEGACY_ACTIVATOR_RAIL(157, org.bukkit.material.PoweredRail.class),
    @Deprecated
    LEGACY_DROPPER(158, org.bukkit.material.Dispenser.class),
    @Deprecated
    LEGACY_STAINED_CLAY(159),
    @Deprecated
    LEGACY_STAINED_GLASS_PANE(160),
    @Deprecated
    LEGACY_LEAVES_2(161, org.bukkit.material.Leaves.class),
    @Deprecated
    LEGACY_LOG_2(162, org.bukkit.material.Tree.class),
    @Deprecated
    LEGACY_ACACIA_STAIRS(163, org.bukkit.material.Stairs.class),
    @Deprecated
    LEGACY_DARK_OAK_STAIRS(164, org.bukkit.material.Stairs.class),
    @Deprecated
    LEGACY_SLIME_BLOCK(165),
    @Deprecated
    LEGACY_BARRIER(166),
    @Deprecated
    LEGACY_IRON_TRAPDOOR(167, org.bukkit.material.TrapDoor.class),
    @Deprecated
    LEGACY_PRISMARINE(168),
    @Deprecated
    LEGACY_SEA_LANTERN(169),
    @Deprecated
    LEGACY_HAY_BLOCK(170),
    @Deprecated
    LEGACY_CARPET(171),
    @Deprecated
    LEGACY_HARD_CLAY(172),
    @Deprecated
    LEGACY_COAL_BLOCK(173),
    @Deprecated
    LEGACY_PACKED_ICE(174),
    @Deprecated
    LEGACY_DOUBLE_PLANT(175),
    @Deprecated
    LEGACY_STANDING_BANNER(176, org.bukkit.material.Banner.class),
    @Deprecated
    LEGACY_WALL_BANNER(177, org.bukkit.material.Banner.class),
    @Deprecated
    LEGACY_DAYLIGHT_DETECTOR_INVERTED(178),
    @Deprecated
    LEGACY_RED_SANDSTONE(179),
    @Deprecated
    LEGACY_RED_SANDSTONE_STAIRS(180, org.bukkit.material.Stairs.class),
    @Deprecated
    LEGACY_DOUBLE_STONE_SLAB2(181),
    @Deprecated
    LEGACY_STONE_SLAB2(182),
    @Deprecated
    LEGACY_SPRUCE_FENCE_GATE(183, org.bukkit.material.Gate.class),
    @Deprecated
    LEGACY_BIRCH_FENCE_GATE(184, org.bukkit.material.Gate.class),
    @Deprecated
    LEGACY_JUNGLE_FENCE_GATE(185, org.bukkit.material.Gate.class),
    @Deprecated
    LEGACY_DARK_OAK_FENCE_GATE(186, org.bukkit.material.Gate.class),
    @Deprecated
    LEGACY_ACACIA_FENCE_GATE(187, org.bukkit.material.Gate.class),
    @Deprecated
    LEGACY_SPRUCE_FENCE(188),
    @Deprecated
    LEGACY_BIRCH_FENCE(189),
    @Deprecated
    LEGACY_JUNGLE_FENCE(190),
    @Deprecated
    LEGACY_DARK_OAK_FENCE(191),
    @Deprecated
    LEGACY_ACACIA_FENCE(192),
    @Deprecated
    LEGACY_SPRUCE_DOOR(193, org.bukkit.material.Door.class),
    @Deprecated
    LEGACY_BIRCH_DOOR(194, org.bukkit.material.Door.class),
    @Deprecated
    LEGACY_JUNGLE_DOOR(195, org.bukkit.material.Door.class),
    @Deprecated
    LEGACY_ACACIA_DOOR(196, org.bukkit.material.Door.class),
    @Deprecated
    LEGACY_DARK_OAK_DOOR(197, org.bukkit.material.Door.class),
    @Deprecated
    LEGACY_END_ROD(198),
    @Deprecated
    LEGACY_CHORUS_PLANT(199),
    @Deprecated
    LEGACY_CHORUS_FLOWER(200),
    @Deprecated
    LEGACY_PURPUR_BLOCK(201),
    @Deprecated
    LEGACY_PURPUR_PILLAR(202),
    @Deprecated
    LEGACY_PURPUR_STAIRS(203, org.bukkit.material.Stairs.class),
    @Deprecated
    LEGACY_PURPUR_DOUBLE_SLAB(204),
    @Deprecated
    LEGACY_PURPUR_SLAB(205),
    @Deprecated
    LEGACY_END_BRICKS(206),
    @Deprecated
    LEGACY_BEETROOT_BLOCK(207, org.bukkit.material.Crops.class),
    @Deprecated
    LEGACY_GRASS_PATH(208),
    @Deprecated
    LEGACY_END_GATEWAY(209),
    @Deprecated
    LEGACY_COMMAND_REPEATING(210, org.bukkit.material.Command.class),
    @Deprecated
    LEGACY_COMMAND_CHAIN(211, org.bukkit.material.Command.class),
    @Deprecated
    LEGACY_FROSTED_ICE(212),
    @Deprecated
    LEGACY_MAGMA(213),
    @Deprecated
    LEGACY_NETHER_WART_BLOCK(214),
    @Deprecated
    LEGACY_RED_NETHER_BRICK(215),
    @Deprecated
    LEGACY_BONE_BLOCK(216),
    @Deprecated
    LEGACY_STRUCTURE_VOID(217),
    @Deprecated
    LEGACY_OBSERVER(218, org.bukkit.material.Observer.class),
    @Deprecated
    LEGACY_WHITE_SHULKER_BOX(219, 1),
    @Deprecated
    LEGACY_ORANGE_SHULKER_BOX(220, 1),
    @Deprecated
    LEGACY_MAGENTA_SHULKER_BOX(221, 1),
    @Deprecated
    LEGACY_LIGHT_BLUE_SHULKER_BOX(222, 1),
    @Deprecated
    LEGACY_YELLOW_SHULKER_BOX(223, 1),
    @Deprecated
    LEGACY_LIME_SHULKER_BOX(224, 1),
    @Deprecated
    LEGACY_PINK_SHULKER_BOX(225, 1),
    @Deprecated
    LEGACY_GRAY_SHULKER_BOX(226, 1),
    @Deprecated
    LEGACY_SILVER_SHULKER_BOX(227, 1),
    @Deprecated
    LEGACY_CYAN_SHULKER_BOX(228, 1),
    @Deprecated
    LEGACY_PURPLE_SHULKER_BOX(229, 1),
    @Deprecated
    LEGACY_BLUE_SHULKER_BOX(230, 1),
    @Deprecated
    LEGACY_BROWN_SHULKER_BOX(231, 1),
    @Deprecated
    LEGACY_GREEN_SHULKER_BOX(232, 1),
    @Deprecated
    LEGACY_RED_SHULKER_BOX(233, 1),
    @Deprecated
    LEGACY_BLACK_SHULKER_BOX(234, 1),
    @Deprecated
    LEGACY_WHITE_GLAZED_TERRACOTTA(235),
    @Deprecated
    LEGACY_ORANGE_GLAZED_TERRACOTTA(236),
    @Deprecated
    LEGACY_MAGENTA_GLAZED_TERRACOTTA(237),
    @Deprecated
    LEGACY_LIGHT_BLUE_GLAZED_TERRACOTTA(238),
    @Deprecated
    LEGACY_YELLOW_GLAZED_TERRACOTTA(239),
    @Deprecated
    LEGACY_LIME_GLAZED_TERRACOTTA(240),
    @Deprecated
    LEGACY_PINK_GLAZED_TERRACOTTA(241),
    @Deprecated
    LEGACY_GRAY_GLAZED_TERRACOTTA(242),
    @Deprecated
    LEGACY_SILVER_GLAZED_TERRACOTTA(243),
    @Deprecated
    LEGACY_CYAN_GLAZED_TERRACOTTA(244),
    @Deprecated
    LEGACY_PURPLE_GLAZED_TERRACOTTA(245),
    @Deprecated
    LEGACY_BLUE_GLAZED_TERRACOTTA(246),
    @Deprecated
    LEGACY_BROWN_GLAZED_TERRACOTTA(247),
    @Deprecated
    LEGACY_GREEN_GLAZED_TERRACOTTA(248),
    @Deprecated
    LEGACY_RED_GLAZED_TERRACOTTA(249),
    @Deprecated
    LEGACY_BLACK_GLAZED_TERRACOTTA(250),
    @Deprecated
    LEGACY_CONCRETE(251),
    @Deprecated
    LEGACY_CONCRETE_POWDER(252),
    @Deprecated
    LEGACY_STRUCTURE_BLOCK(255),
    // ----- Item Separator -----
    @Deprecated
    LEGACY_IRON_SPADE(256, 1, 250),
    @Deprecated
    LEGACY_IRON_PICKAXE(257, 1, 250),
    @Deprecated
    LEGACY_IRON_AXE(258, 1, 250),
    @Deprecated
    LEGACY_FLINT_AND_STEEL(259, 1, 64),
    @Deprecated
    LEGACY_APPLE(260),
    @Deprecated
    LEGACY_BOW(261, 1, 384),
    @Deprecated
    LEGACY_ARROW(262),
    @Deprecated
    LEGACY_COAL(263, org.bukkit.material.Coal.class),
    @Deprecated
    LEGACY_DIAMOND(264),
    @Deprecated
    LEGACY_IRON_INGOT(265),
    @Deprecated
    LEGACY_GOLD_INGOT(266),
    @Deprecated
    LEGACY_IRON_SWORD(267, 1, 250),
    @Deprecated
    LEGACY_WOOD_SWORD(268, 1, 59),
    @Deprecated
    LEGACY_WOOD_SPADE(269, 1, 59),
    @Deprecated
    LEGACY_WOOD_PICKAXE(270, 1, 59),
    @Deprecated
    LEGACY_WOOD_AXE(271, 1, 59),
    @Deprecated
    LEGACY_STONE_SWORD(272, 1, 131),
    @Deprecated
    LEGACY_STONE_SPADE(273, 1, 131),
    @Deprecated
    LEGACY_STONE_PICKAXE(274, 1, 131),
    @Deprecated
    LEGACY_STONE_AXE(275, 1, 131),
    @Deprecated
    LEGACY_DIAMOND_SWORD(276, 1, 1561),
    @Deprecated
    LEGACY_DIAMOND_SPADE(277, 1, 1561),
    @Deprecated
    LEGACY_DIAMOND_PICKAXE(278, 1, 1561),
    @Deprecated
    LEGACY_DIAMOND_AXE(279, 1, 1561),
    @Deprecated
    LEGACY_STICK(280),
    @Deprecated
    LEGACY_BOWL(281),
    @Deprecated
    LEGACY_MUSHROOM_SOUP(282, 1),
    @Deprecated
    LEGACY_GOLD_SWORD(283, 1, 32),
    @Deprecated
    LEGACY_GOLD_SPADE(284, 1, 32),
    @Deprecated
    LEGACY_GOLD_PICKAXE(285, 1, 32),
    @Deprecated
    LEGACY_GOLD_AXE(286, 1, 32),
    @Deprecated
    LEGACY_STRING(287),
    @Deprecated
    LEGACY_FEATHER(288),
    @Deprecated
    LEGACY_SULPHUR(289),
    @Deprecated
    LEGACY_WOOD_HOE(290, 1, 59),
    @Deprecated
    LEGACY_STONE_HOE(291, 1, 131),
    @Deprecated
    LEGACY_IRON_HOE(292, 1, 250),
    @Deprecated
    LEGACY_DIAMOND_HOE(293, 1, 1561),
    @Deprecated
    LEGACY_GOLD_HOE(294, 1, 32),
    @Deprecated
    LEGACY_SEEDS(295),
    @Deprecated
    LEGACY_WHEAT(296),
    @Deprecated
    LEGACY_BREAD(297),
    @Deprecated
    LEGACY_LEATHER_HELMET(298, 1, 55),
    @Deprecated
    LEGACY_LEATHER_CHESTPLATE(299, 1, 80),
    @Deprecated
    LEGACY_LEATHER_LEGGINGS(300, 1, 75),
    @Deprecated
    LEGACY_LEATHER_BOOTS(301, 1, 65),
    @Deprecated
    LEGACY_CHAINMAIL_HELMET(302, 1, 165),
    @Deprecated
    LEGACY_CHAINMAIL_CHESTPLATE(303, 1, 240),
    @Deprecated
    LEGACY_CHAINMAIL_LEGGINGS(304, 1, 225),
    @Deprecated
    LEGACY_CHAINMAIL_BOOTS(305, 1, 195),
    @Deprecated
    LEGACY_IRON_HELMET(306, 1, 165),
    @Deprecated
    LEGACY_IRON_CHESTPLATE(307, 1, 240),
    @Deprecated
    LEGACY_IRON_LEGGINGS(308, 1, 225),
    @Deprecated
    LEGACY_IRON_BOOTS(309, 1, 195),
    @Deprecated
    LEGACY_DIAMOND_HELMET(310, 1, 363),
    @Deprecated
    LEGACY_DIAMOND_CHESTPLATE(311, 1, 528),
    @Deprecated
    LEGACY_DIAMOND_LEGGINGS(312, 1, 495),
    @Deprecated
    LEGACY_DIAMOND_BOOTS(313, 1, 429),
    @Deprecated
    LEGACY_GOLD_HELMET(314, 1, 77),
    @Deprecated
    LEGACY_GOLD_CHESTPLATE(315, 1, 112),
    @Deprecated
    LEGACY_GOLD_LEGGINGS(316, 1, 105),
    @Deprecated
    LEGACY_GOLD_BOOTS(317, 1, 91),
    @Deprecated
    LEGACY_FLINT(318),
    @Deprecated
    LEGACY_PORK(319),
    @Deprecated
    LEGACY_GRILLED_PORK(320),
    @Deprecated
    LEGACY_PAINTING(321),
    @Deprecated
    LEGACY_GOLDEN_APPLE(322),
    @Deprecated
    LEGACY_SIGN(323, 16),
    @Deprecated
    LEGACY_WOOD_DOOR(324, 64),
    @Deprecated
    LEGACY_BUCKET(325, 16),
    @Deprecated
    LEGACY_WATER_BUCKET(326, 1),
    @Deprecated
    LEGACY_LAVA_BUCKET(327, 1),
    @Deprecated
    LEGACY_MINECART(328, 1),
    @Deprecated
    LEGACY_SADDLE(329, 1),
    @Deprecated
    LEGACY_IRON_DOOR(330, 64),
    @Deprecated
    LEGACY_REDSTONE(331),
    @Deprecated
    LEGACY_SNOW_BALL(332, 16),
    @Deprecated
    LEGACY_BOAT(333, 1),
    @Deprecated
    LEGACY_LEATHER(334),
    @Deprecated
    LEGACY_MILK_BUCKET(335, 1),
    @Deprecated
    LEGACY_CLAY_BRICK(336),
    @Deprecated
    LEGACY_CLAY_BALL(337),
    @Deprecated
    LEGACY_SUGAR_CANE(338),
    @Deprecated
    LEGACY_PAPER(339),
    @Deprecated
    LEGACY_BOOK(340),
    @Deprecated
    LEGACY_SLIME_BALL(341),
    @Deprecated
    LEGACY_STORAGE_MINECART(342, 1),
    @Deprecated
    LEGACY_POWERED_MINECART(343, 1),
    @Deprecated
    LEGACY_EGG(344, 16),
    @Deprecated
    LEGACY_COMPASS(345),
    @Deprecated
    LEGACY_FISHING_ROD(346, 1, 64),
    @Deprecated
    LEGACY_WATCH(347),
    @Deprecated
    LEGACY_GLOWSTONE_DUST(348),
    @Deprecated
    LEGACY_RAW_FISH(349),
    @Deprecated
    LEGACY_COOKED_FISH(350),
    @Deprecated
    LEGACY_INK_SACK(351, org.bukkit.material.Dye.class),
    @Deprecated
    LEGACY_BONE(352),
    @Deprecated
    LEGACY_SUGAR(353),
    @Deprecated
    LEGACY_CAKE(354, 1),
    @Deprecated
    LEGACY_BED(355, 1),
    @Deprecated
    LEGACY_DIODE(356),
    @Deprecated
    LEGACY_COOKIE(357),
    /**
     * @see org.bukkit.map.MapView
     */
    @Deprecated
    LEGACY_MAP(358, org.bukkit.material.MaterialData.class),
    @Deprecated
    LEGACY_SHEARS(359, 1, 238),
    @Deprecated
    LEGACY_MELON(360),
    @Deprecated
    LEGACY_PUMPKIN_SEEDS(361),
    @Deprecated
    LEGACY_MELON_SEEDS(362),
    @Deprecated
    LEGACY_RAW_BEEF(363),
    @Deprecated
    LEGACY_COOKED_BEEF(364),
    @Deprecated
    LEGACY_RAW_CHICKEN(365),
    @Deprecated
    LEGACY_COOKED_CHICKEN(366),
    @Deprecated
    LEGACY_ROTTEN_FLESH(367),
    @Deprecated
    LEGACY_ENDER_PEARL(368, 16),
    @Deprecated
    LEGACY_BLAZE_ROD(369),
    @Deprecated
    LEGACY_GHAST_TEAR(370),
    @Deprecated
    LEGACY_GOLD_NUGGET(371),
    @Deprecated
    LEGACY_NETHER_STALK(372),
    @Deprecated
    LEGACY_POTION(373, 1, org.bukkit.material.MaterialData.class),
    @Deprecated
    LEGACY_GLASS_BOTTLE(374),
    @Deprecated
    LEGACY_SPIDER_EYE(375),
    @Deprecated
    LEGACY_FERMENTED_SPIDER_EYE(376),
    @Deprecated
    LEGACY_BLAZE_POWDER(377),
    @Deprecated
    LEGACY_MAGMA_CREAM(378),
    @Deprecated
    LEGACY_BREWING_STAND_ITEM(379),
    @Deprecated
    LEGACY_CAULDRON_ITEM(380),
    @Deprecated
    LEGACY_EYE_OF_ENDER(381),
    @Deprecated
    LEGACY_SPECKLED_MELON(382),
    @Deprecated
    LEGACY_MONSTER_EGG(383, 64, org.bukkit.material.SpawnEgg.class),
    @Deprecated
    LEGACY_EXP_BOTTLE(384, 64),
    @Deprecated
    LEGACY_FIREBALL(385, 64),
    @Deprecated
    LEGACY_BOOK_AND_QUILL(386, 1),
    @Deprecated
    LEGACY_WRITTEN_BOOK(387, 16),
    @Deprecated
    LEGACY_EMERALD(388, 64),
    @Deprecated
    LEGACY_ITEM_FRAME(389),
    @Deprecated
    LEGACY_FLOWER_POT_ITEM(390),
    @Deprecated
    LEGACY_CARROT_ITEM(391),
    @Deprecated
    LEGACY_POTATO_ITEM(392),
    @Deprecated
    LEGACY_BAKED_POTATO(393),
    @Deprecated
    LEGACY_POISONOUS_POTATO(394),
    @Deprecated
    LEGACY_EMPTY_MAP(395),
    @Deprecated
    LEGACY_GOLDEN_CARROT(396),
    @Deprecated
    LEGACY_SKULL_ITEM(397),
    @Deprecated
    LEGACY_CARROT_STICK(398, 1, 25),
    @Deprecated
    LEGACY_NETHER_STAR(399),
    @Deprecated
    LEGACY_PUMPKIN_PIE(400),
    @Deprecated
    LEGACY_FIREWORK(401),
    @Deprecated
    LEGACY_FIREWORK_CHARGE(402),
    @Deprecated
    LEGACY_ENCHANTED_BOOK(403, 1),
    @Deprecated
    LEGACY_REDSTONE_COMPARATOR(404),
    @Deprecated
    LEGACY_NETHER_BRICK_ITEM(405),
    @Deprecated
    LEGACY_QUARTZ(406),
    @Deprecated
    LEGACY_EXPLOSIVE_MINECART(407, 1),
    @Deprecated
    LEGACY_HOPPER_MINECART(408, 1),
    @Deprecated
    LEGACY_PRISMARINE_SHARD(409),
    @Deprecated
    LEGACY_PRISMARINE_CRYSTALS(410),
    @Deprecated
    LEGACY_RABBIT(411),
    @Deprecated
    LEGACY_COOKED_RABBIT(412),
    @Deprecated
    LEGACY_RABBIT_STEW(413, 1),
    @Deprecated
    LEGACY_RABBIT_FOOT(414),
    @Deprecated
    LEGACY_RABBIT_HIDE(415),
    @Deprecated
    LEGACY_ARMOR_STAND(416, 16),
    @Deprecated
    LEGACY_IRON_BARDING(417, 1),
    @Deprecated
    LEGACY_GOLD_BARDING(418, 1),
    @Deprecated
    LEGACY_DIAMOND_BARDING(419, 1),
    @Deprecated
    LEGACY_LEASH(420),
    @Deprecated
    LEGACY_NAME_TAG(421),
    @Deprecated
    LEGACY_COMMAND_MINECART(422, 1),
    @Deprecated
    LEGACY_MUTTON(423),
    @Deprecated
    LEGACY_COOKED_MUTTON(424),
    @Deprecated
    LEGACY_BANNER(425, 16),
    @Deprecated
    LEGACY_END_CRYSTAL(426),
    @Deprecated
    LEGACY_SPRUCE_DOOR_ITEM(427),
    @Deprecated
    LEGACY_BIRCH_DOOR_ITEM(428),
    @Deprecated
    LEGACY_JUNGLE_DOOR_ITEM(429),
    @Deprecated
    LEGACY_ACACIA_DOOR_ITEM(430),
    @Deprecated
    LEGACY_DARK_OAK_DOOR_ITEM(431),
    @Deprecated
    LEGACY_CHORUS_FRUIT(432),
    @Deprecated
    LEGACY_CHORUS_FRUIT_POPPED(433),
    @Deprecated
    LEGACY_BEETROOT(434),
    @Deprecated
    LEGACY_BEETROOT_SEEDS(435),
    @Deprecated
    LEGACY_BEETROOT_SOUP(436, 1),
    @Deprecated
    LEGACY_DRAGONS_BREATH(437),
    @Deprecated
    LEGACY_SPLASH_POTION(438, 1),
    @Deprecated
    LEGACY_SPECTRAL_ARROW(439),
    @Deprecated
    LEGACY_TIPPED_ARROW(440),
    @Deprecated
    LEGACY_LINGERING_POTION(441, 1),
    @Deprecated
    LEGACY_SHIELD(442, 1, 336),
    @Deprecated
    LEGACY_ELYTRA(443, 1, 431),
    @Deprecated
    LEGACY_BOAT_SPRUCE(444, 1),
    @Deprecated
    LEGACY_BOAT_BIRCH(445, 1),
    @Deprecated
    LEGACY_BOAT_JUNGLE(446, 1),
    @Deprecated
    LEGACY_BOAT_ACACIA(447, 1),
    @Deprecated
    LEGACY_BOAT_DARK_OAK(448, 1),
    @Deprecated
    LEGACY_TOTEM(449, 1),
    @Deprecated
    LEGACY_SHULKER_SHELL(450),
    @Deprecated
    LEGACY_IRON_NUGGET(452),
    @Deprecated
    LEGACY_KNOWLEDGE_BOOK(453, 1),
    @Deprecated
    LEGACY_GOLD_RECORD(2256, 1),
    @Deprecated
    LEGACY_GREEN_RECORD(2257, 1),
    @Deprecated
    LEGACY_RECORD_3(2258, 1),
    @Deprecated
    LEGACY_RECORD_4(2259, 1),
    @Deprecated
    LEGACY_RECORD_5(2260, 1),
    @Deprecated
    LEGACY_RECORD_6(2261, 1),
    @Deprecated
    LEGACY_RECORD_7(2262, 1),
    @Deprecated
    LEGACY_RECORD_8(2263, 1),
    @Deprecated
    LEGACY_RECORD_9(2264, 1),
    @Deprecated
    LEGACY_RECORD_10(2265, 1),
    @Deprecated
    LEGACY_RECORD_11(2266, 1),
    @Deprecated
    LEGACY_RECORD_12(2267, 1),
    ;
    //</editor-fold>

    @Deprecated
    public static final String LEGACY_PREFIX = "LEGACY_";

    private final int id;
    private final Constructor<? extends MaterialData> ctor;
    private static final Map<String, Material> BY_NAME = Maps.newHashMap();
    private final int maxStack;
    private final short durability;
    public final Class<?> data;
    private final boolean legacy;
    private final NamespacedKey key;
    private boolean isBlock; // Paper

    private Material(final int id) {
        this(id, 64);
    }

    private Material(final int id, final int stack) {
        this(id, stack, MaterialData.class);
    }

    private Material(final int id, final int stack, final int durability) {
        this(id, stack, durability, MaterialData.class);
    }

    private Material(final int id, /*@NotNull*/ final Class<?> data) {
        this(id, 64, data);
    }

    private Material(final int id, final int stack, /*@NotNull*/ final Class<?> data) {
        this(id, stack, 0, data);
    }

    private Material(final int id, final int stack, final int durability, /*@NotNull*/ final Class<?> data) {
        this.id = id;
        this.durability = (short) durability;
        this.maxStack = stack;
        this.data = data;
        this.legacy = this.name().startsWith(LEGACY_PREFIX);
        this.key = NamespacedKey.minecraft(this.name().toLowerCase(Locale.ROOT));
        // try to cache the constructor for this material
        try {
            if (MaterialData.class.isAssignableFrom(data)) {
                this.ctor = (Constructor<? extends MaterialData>) data.getConstructor(Material.class, byte.class);
            } else {
                this.ctor = null;
            }
        } catch (NoSuchMethodException ex) {
            throw new AssertionError(ex);
        } catch (SecurityException ex) {
            throw new AssertionError(ex);
        }
    }

    // Paper start

    /**
     * @return If the type is either AIR, CAVE_AIR or VOID_AIR
     */
    public boolean isEmpty() {
        switch (this) {
            case AIR:
            case CAVE_AIR:
            case VOID_AIR:
                return true;
        }
        return false;
    }

    @Override
    public @NotNull String translationKey() {
        if (this.isItem()) {
            return Bukkit.getUnsafe().getItemTranslationKey(this);
        } else {
            return Bukkit.getUnsafe().getBlockTranslationKey(this);
        }
    }

    /**
     * Returns the item rarity for the item. The Material <b>MUST</b> be an Item not a block.
     * Use {@link #isItem()} before this.
     *
     * @return the item rarity
     */
    @NotNull
    public io.papermc.paper.inventory.ItemRarity getItemRarity() {
        return Bukkit.getUnsafe().getItemRarity(this);
    }

    /**
     * Returns an immutable multimap of attributes for the slot.
     * {@link #isItem()} must be true for this material.
     *
     * @param equipmentSlot the slot to get the attributes for
     * @throws IllegalArgumentException if {@link #isItem()} is false
     * @return an immutable multimap of attributes
     * @deprecated use {@link #getDefaultAttributeModifiers(EquipmentSlot)}
     */
    @NotNull
    @Deprecated
    public Multimap<Attribute, AttributeModifier> getItemAttributes(@NotNull EquipmentSlot equipmentSlot) {
        return Bukkit.getUnsafe().getItemAttributes(this, equipmentSlot);
    }

    /**
     * Checks if this material is collidable.
     *
     * @return true if collidable
     * @throws IllegalArgumentException if {@link #isBlock()} is false
     */
    public boolean isCollidable() {
        return Bukkit.getUnsafe().isCollidable(this);
    }
    // Paper end

    /**
     * Do not use for any reason.
     *
     * @return ID of this material
     * @apiNote Internal Use Only
     */
    @org.jetbrains.annotations.ApiStatus.Internal // Paper
    public int getId() {
        Preconditions.checkArgument(legacy, "Cannot get ID of Modern Material");
        return id;
    }

    /**
     * Checks if this constant is a legacy material.
     *
     * @return legacy status
     */
    // @Deprecated // Paper - this is useful, don't deprecate
    public boolean isLegacy() {
        return legacy;
    }

    @NotNull
    @Override
    public NamespacedKey getKey() {
        Preconditions.checkArgument(!legacy, "Cannot get key of Legacy Material");
        return key;
    }

    /**
     * Gets the maximum amount of this material that can be held in a stack
     *
     * @return Maximum stack size for this material
     */
    public int getMaxStackSize() {
        return maxStack;
    }

    /**
     * Gets the maximum durability of this material
     *
     * @return Maximum durability for this material
     */
    public short getMaxDurability() {
        return durability;
    }

    /**
     * Creates a new {@link BlockData} instance for this Material, with all
     * properties initialized to unspecified defaults.
     *
     * @return new data instance
     */
    @NotNull
    public BlockData createBlockData() {
        return Bukkit.createBlockData(this);
    }

    /**
     * Creates a new {@link BlockData} instance for this Material, with
     * all properties initialized to unspecified defaults.
     *
     * @param consumer consumer to run on new instance before returning
     * @return new data instance
     */
    @NotNull
    public BlockData createBlockData(@Nullable Consumer<? super BlockData> consumer) {
        return Bukkit.createBlockData(this, consumer);
    }

    /**
     * Creates a new {@link BlockData} instance for this Material, with all
     * properties initialized to unspecified defaults, except for those provided
     * in data.
     *
     * @param data data string
     * @return new data instance
     * @throws IllegalArgumentException if the specified data is not valid
     */
    @NotNull
    public BlockData createBlockData(@Nullable String data) throws IllegalArgumentException {
        return Bukkit.createBlockData(this, data);
    }

    /**
     * Gets the MaterialData class associated with this Material
     *
     * @return MaterialData associated with this Material
     * @deprecated use {@link #createBlockData()}
     */
    @NotNull
    @Deprecated // Paper
    public Class<? extends MaterialData> getData() {
        Preconditions.checkArgument(legacy, "Cannot get data class of Modern Material");
        return ctor.getDeclaringClass();
    }

    /**
     * Constructs a new MaterialData relevant for this Material, with the
     * given initial data
     *
     * @param raw Initial data to construct the MaterialData with
     * @return New MaterialData with the given data
     * @deprecated Magic value
     */
    @Deprecated
    @NotNull
    public MaterialData getNewData(final byte raw) {
        Preconditions.checkArgument(legacy, "Cannot get new data of Modern Material");
        try {
            return ctor.newInstance(this, raw);
        } catch (InstantiationException ex) {
            final Throwable t = ex.getCause();
            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            }
            if (t instanceof Error) {
                throw (Error) t;
            }
            throw new AssertionError(t);
        } catch (Throwable t) {
            throw new AssertionError(t);
        }
    }

    /**
     * Checks if this Material is a placable block
     *
     * @return true if this material is a block
     */
    public boolean isBlock() {
    // Paper start - cache isBlock
        return this.isBlock;
    }
    private boolean isBlock0() {
    // Paper end
        switch (this) {
            //<editor-fold defaultstate="collapsed" desc="isBlock">
            // Paper start - Generated/Material#isBlock
            // @GeneratedFrom 1.20.4
            case ACACIA_BUTTON:
            case ACACIA_DOOR:
            case ACACIA_FENCE:
            case ACACIA_FENCE_GATE:
            case ACACIA_HANGING_SIGN:
            case ACACIA_LEAVES:
            case ACACIA_LOG:
            case ACACIA_PLANKS:
            case ACACIA_PRESSURE_PLATE:
            case ACACIA_SAPLING:
            case ACACIA_SIGN:
            case ACACIA_SLAB:
            case ACACIA_STAIRS:
            case ACACIA_TRAPDOOR:
            case ACACIA_WALL_HANGING_SIGN:
            case ACACIA_WALL_SIGN:
            case ACACIA_WOOD:
            case ACTIVATOR_RAIL:
            case AIR:
            case ALLIUM:
            case AMETHYST_BLOCK:
            case AMETHYST_CLUSTER:
            case ANCIENT_DEBRIS:
            case ANDESITE:
            case ANDESITE_SLAB:
            case ANDESITE_STAIRS:
            case ANDESITE_WALL:
            case ANVIL:
            case ATTACHED_MELON_STEM:
            case ATTACHED_PUMPKIN_STEM:
            case AZALEA:
            case AZALEA_LEAVES:
            case AZURE_BLUET:
            case BAMBOO:
            case BAMBOO_BLOCK:
            case BAMBOO_BUTTON:
            case BAMBOO_DOOR:
            case BAMBOO_FENCE:
            case BAMBOO_FENCE_GATE:
            case BAMBOO_HANGING_SIGN:
            case BAMBOO_MOSAIC:
            case BAMBOO_MOSAIC_SLAB:
            case BAMBOO_MOSAIC_STAIRS:
            case BAMBOO_PLANKS:
            case BAMBOO_PRESSURE_PLATE:
            case BAMBOO_SAPLING:
            case BAMBOO_SIGN:
            case BAMBOO_SLAB:
            case BAMBOO_STAIRS:
            case BAMBOO_TRAPDOOR:
            case BAMBOO_WALL_HANGING_SIGN:
            case BAMBOO_WALL_SIGN:
            case BARREL:
            case BARRIER:
            case BASALT:
            case BEACON:
            case BEDROCK:
            case BEEHIVE:
            case BEETROOTS:
            case BEE_NEST:
            case BELL:
            case BIG_DRIPLEAF:
            case BIG_DRIPLEAF_STEM:
            case BIRCH_BUTTON:
            case BIRCH_DOOR:
            case BIRCH_FENCE:
            case BIRCH_FENCE_GATE:
            case BIRCH_HANGING_SIGN:
            case BIRCH_LEAVES:
            case BIRCH_LOG:
            case BIRCH_PLANKS:
            case BIRCH_PRESSURE_PLATE:
            case BIRCH_SAPLING:
            case BIRCH_SIGN:
            case BIRCH_SLAB:
            case BIRCH_STAIRS:
            case BIRCH_TRAPDOOR:
            case BIRCH_WALL_HANGING_SIGN:
            case BIRCH_WALL_SIGN:
            case BIRCH_WOOD:
            case BLACKSTONE:
            case BLACKSTONE_SLAB:
            case BLACKSTONE_STAIRS:
            case BLACKSTONE_WALL:
            case BLACK_BANNER:
            case BLACK_BED:
            case BLACK_CANDLE:
            case BLACK_CANDLE_CAKE:
            case BLACK_CARPET:
            case BLACK_CONCRETE:
            case BLACK_CONCRETE_POWDER:
            case BLACK_GLAZED_TERRACOTTA:
            case BLACK_SHULKER_BOX:
            case BLACK_STAINED_GLASS:
            case BLACK_STAINED_GLASS_PANE:
            case BLACK_TERRACOTTA:
            case BLACK_WALL_BANNER:
            case BLACK_WOOL:
            case BLAST_FURNACE:
            case BLUE_BANNER:
            case BLUE_BED:
            case BLUE_CANDLE:
            case BLUE_CANDLE_CAKE:
            case BLUE_CARPET:
            case BLUE_CONCRETE:
            case BLUE_CONCRETE_POWDER:
            case BLUE_GLAZED_TERRACOTTA:
            case BLUE_ICE:
            case BLUE_ORCHID:
            case BLUE_SHULKER_BOX:
            case BLUE_STAINED_GLASS:
            case BLUE_STAINED_GLASS_PANE:
            case BLUE_TERRACOTTA:
            case BLUE_WALL_BANNER:
            case BLUE_WOOL:
            case BONE_BLOCK:
            case BOOKSHELF:
            case BRAIN_CORAL:
            case BRAIN_CORAL_BLOCK:
            case BRAIN_CORAL_FAN:
            case BRAIN_CORAL_WALL_FAN:
            case BREWING_STAND:
            case BRICKS:
            case BRICK_SLAB:
            case BRICK_STAIRS:
            case BRICK_WALL:
            case BROWN_BANNER:
            case BROWN_BED:
            case BROWN_CANDLE:
            case BROWN_CANDLE_CAKE:
            case BROWN_CARPET:
            case BROWN_CONCRETE:
            case BROWN_CONCRETE_POWDER:
            case BROWN_GLAZED_TERRACOTTA:
            case BROWN_MUSHROOM:
            case BROWN_MUSHROOM_BLOCK:
            case BROWN_SHULKER_BOX:
            case BROWN_STAINED_GLASS:
            case BROWN_STAINED_GLASS_PANE:
            case BROWN_TERRACOTTA:
            case BROWN_WALL_BANNER:
            case BROWN_WOOL:
            case BUBBLE_COLUMN:
            case BUBBLE_CORAL:
            case BUBBLE_CORAL_BLOCK:
            case BUBBLE_CORAL_FAN:
            case BUBBLE_CORAL_WALL_FAN:
            case BUDDING_AMETHYST:
            case CACTUS:
            case CAKE:
            case CALCITE:
            case CALIBRATED_SCULK_SENSOR:
            case CAMPFIRE:
            case CANDLE:
            case CANDLE_CAKE:
            case CARROTS:
            case CARTOGRAPHY_TABLE:
            case CARVED_PUMPKIN:
            case CAULDRON:
            case CAVE_AIR:
            case CAVE_VINES:
            case CAVE_VINES_PLANT:
            case CHAIN:
            case CHAIN_COMMAND_BLOCK:
            case CHERRY_BUTTON:
            case CHERRY_DOOR:
            case CHERRY_FENCE:
            case CHERRY_FENCE_GATE:
            case CHERRY_HANGING_SIGN:
            case CHERRY_LEAVES:
            case CHERRY_LOG:
            case CHERRY_PLANKS:
            case CHERRY_PRESSURE_PLATE:
            case CHERRY_SAPLING:
            case CHERRY_SIGN:
            case CHERRY_SLAB:
            case CHERRY_STAIRS:
            case CHERRY_TRAPDOOR:
            case CHERRY_WALL_HANGING_SIGN:
            case CHERRY_WALL_SIGN:
            case CHERRY_WOOD:
            case CHEST:
            case CHIPPED_ANVIL:
            case CHISELED_BOOKSHELF:
            case CHISELED_COPPER:
            case CHISELED_DEEPSLATE:
            case CHISELED_NETHER_BRICKS:
            case CHISELED_POLISHED_BLACKSTONE:
            case CHISELED_QUARTZ_BLOCK:
            case CHISELED_RED_SANDSTONE:
            case CHISELED_SANDSTONE:
            case CHISELED_STONE_BRICKS:
            case CHISELED_TUFF:
            case CHISELED_TUFF_BRICKS:
            case CHORUS_FLOWER:
            case CHORUS_PLANT:
            case CLAY:
            case COAL_BLOCK:
            case COAL_ORE:
            case COARSE_DIRT:
            case COBBLED_DEEPSLATE:
            case COBBLED_DEEPSLATE_SLAB:
            case COBBLED_DEEPSLATE_STAIRS:
            case COBBLED_DEEPSLATE_WALL:
            case COBBLESTONE:
            case COBBLESTONE_SLAB:
            case COBBLESTONE_STAIRS:
            case COBBLESTONE_WALL:
            case COBWEB:
            case COCOA:
            case COMMAND_BLOCK:
            case COMPARATOR:
            case COMPOSTER:
            case CONDUIT:
            case COPPER_BLOCK:
            case COPPER_BULB:
            case COPPER_DOOR:
            case COPPER_GRATE:
            case COPPER_ORE:
            case COPPER_TRAPDOOR:
            case CORNFLOWER:
            case CRACKED_DEEPSLATE_BRICKS:
            case CRACKED_DEEPSLATE_TILES:
            case CRACKED_NETHER_BRICKS:
            case CRACKED_POLISHED_BLACKSTONE_BRICKS:
            case CRACKED_STONE_BRICKS:
            case CRAFTER:
            case CRAFTING_TABLE:
            case CREEPER_HEAD:
            case CREEPER_WALL_HEAD:
            case CRIMSON_BUTTON:
            case CRIMSON_DOOR:
            case CRIMSON_FENCE:
            case CRIMSON_FENCE_GATE:
            case CRIMSON_FUNGUS:
            case CRIMSON_HANGING_SIGN:
            case CRIMSON_HYPHAE:
            case CRIMSON_NYLIUM:
            case CRIMSON_PLANKS:
            case CRIMSON_PRESSURE_PLATE:
            case CRIMSON_ROOTS:
            case CRIMSON_SIGN:
            case CRIMSON_SLAB:
            case CRIMSON_STAIRS:
            case CRIMSON_STEM:
            case CRIMSON_TRAPDOOR:
            case CRIMSON_WALL_HANGING_SIGN:
            case CRIMSON_WALL_SIGN:
            case CRYING_OBSIDIAN:
            case CUT_COPPER:
            case CUT_COPPER_SLAB:
            case CUT_COPPER_STAIRS:
            case CUT_RED_SANDSTONE:
            case CUT_RED_SANDSTONE_SLAB:
            case CUT_SANDSTONE:
            case CUT_SANDSTONE_SLAB:
            case CYAN_BANNER:
            case CYAN_BED:
            case CYAN_CANDLE:
            case CYAN_CANDLE_CAKE:
            case CYAN_CARPET:
            case CYAN_CONCRETE:
            case CYAN_CONCRETE_POWDER:
            case CYAN_GLAZED_TERRACOTTA:
            case CYAN_SHULKER_BOX:
            case CYAN_STAINED_GLASS:
            case CYAN_STAINED_GLASS_PANE:
            case CYAN_TERRACOTTA:
            case CYAN_WALL_BANNER:
            case CYAN_WOOL:
            case DAMAGED_ANVIL:
            case DANDELION:
            case DARK_OAK_BUTTON:
            case DARK_OAK_DOOR:
            case DARK_OAK_FENCE:
            case DARK_OAK_FENCE_GATE:
            case DARK_OAK_HANGING_SIGN:
            case DARK_OAK_LEAVES:
            case DARK_OAK_LOG:
            case DARK_OAK_PLANKS:
            case DARK_OAK_PRESSURE_PLATE:
            case DARK_OAK_SAPLING:
            case DARK_OAK_SIGN:
            case DARK_OAK_SLAB:
            case DARK_OAK_STAIRS:
            case DARK_OAK_TRAPDOOR:
            case DARK_OAK_WALL_HANGING_SIGN:
            case DARK_OAK_WALL_SIGN:
            case DARK_OAK_WOOD:
            case DARK_PRISMARINE:
            case DARK_PRISMARINE_SLAB:
            case DARK_PRISMARINE_STAIRS:
            case DAYLIGHT_DETECTOR:
            case DEAD_BRAIN_CORAL:
            case DEAD_BRAIN_CORAL_BLOCK:
            case DEAD_BRAIN_CORAL_FAN:
            case DEAD_BRAIN_CORAL_WALL_FAN:
            case DEAD_BUBBLE_CORAL:
            case DEAD_BUBBLE_CORAL_BLOCK:
            case DEAD_BUBBLE_CORAL_FAN:
            case DEAD_BUBBLE_CORAL_WALL_FAN:
            case DEAD_BUSH:
            case DEAD_FIRE_CORAL:
            case DEAD_FIRE_CORAL_BLOCK:
            case DEAD_FIRE_CORAL_FAN:
            case DEAD_FIRE_CORAL_WALL_FAN:
            case DEAD_HORN_CORAL:
            case DEAD_HORN_CORAL_BLOCK:
            case DEAD_HORN_CORAL_FAN:
            case DEAD_HORN_CORAL_WALL_FAN:
            case DEAD_TUBE_CORAL:
            case DEAD_TUBE_CORAL_BLOCK:
            case DEAD_TUBE_CORAL_FAN:
            case DEAD_TUBE_CORAL_WALL_FAN:
            case DECORATED_POT:
            case DEEPSLATE:
            case DEEPSLATE_BRICKS:
            case DEEPSLATE_BRICK_SLAB:
            case DEEPSLATE_BRICK_STAIRS:
            case DEEPSLATE_BRICK_WALL:
            case DEEPSLATE_COAL_ORE:
            case DEEPSLATE_COPPER_ORE:
            case DEEPSLATE_DIAMOND_ORE:
            case DEEPSLATE_EMERALD_ORE:
            case DEEPSLATE_GOLD_ORE:
            case DEEPSLATE_IRON_ORE:
            case DEEPSLATE_LAPIS_ORE:
            case DEEPSLATE_REDSTONE_ORE:
            case DEEPSLATE_TILES:
            case DEEPSLATE_TILE_SLAB:
            case DEEPSLATE_TILE_STAIRS:
            case DEEPSLATE_TILE_WALL:
            case DETECTOR_RAIL:
            case DIAMOND_BLOCK:
            case DIAMOND_ORE:
            case DIORITE:
            case DIORITE_SLAB:
            case DIORITE_STAIRS:
            case DIORITE_WALL:
            case DIRT:
            case DIRT_PATH:
            case DISPENSER:
            case DRAGON_EGG:
            case DRAGON_HEAD:
            case DRAGON_WALL_HEAD:
            case DRIED_KELP_BLOCK:
            case DRIPSTONE_BLOCK:
            case DROPPER:
            case EMERALD_BLOCK:
            case EMERALD_ORE:
            case ENCHANTING_TABLE:
            case ENDER_CHEST:
            case END_GATEWAY:
            case END_PORTAL:
            case END_PORTAL_FRAME:
            case END_ROD:
            case END_STONE:
            case END_STONE_BRICKS:
            case END_STONE_BRICK_SLAB:
            case END_STONE_BRICK_STAIRS:
            case END_STONE_BRICK_WALL:
            case EXPOSED_CHISELED_COPPER:
            case EXPOSED_COPPER:
            case EXPOSED_COPPER_BULB:
            case EXPOSED_COPPER_DOOR:
            case EXPOSED_COPPER_GRATE:
            case EXPOSED_COPPER_TRAPDOOR:
            case EXPOSED_CUT_COPPER:
            case EXPOSED_CUT_COPPER_SLAB:
            case EXPOSED_CUT_COPPER_STAIRS:
            case FARMLAND:
            case FERN:
            case FIRE:
            case FIRE_CORAL:
            case FIRE_CORAL_BLOCK:
            case FIRE_CORAL_FAN:
            case FIRE_CORAL_WALL_FAN:
            case FLETCHING_TABLE:
            case FLOWERING_AZALEA:
            case FLOWERING_AZALEA_LEAVES:
            case FLOWER_POT:
            case FROGSPAWN:
            case FROSTED_ICE:
            case FURNACE:
            case GILDED_BLACKSTONE:
            case GLASS:
            case GLASS_PANE:
            case GLOWSTONE:
            case GLOW_LICHEN:
            case GOLD_BLOCK:
            case GOLD_ORE:
            case GRANITE:
            case GRANITE_SLAB:
            case GRANITE_STAIRS:
            case GRANITE_WALL:
            case GRASS_BLOCK:
            case GRAVEL:
            case GRAY_BANNER:
            case GRAY_BED:
            case GRAY_CANDLE:
            case GRAY_CANDLE_CAKE:
            case GRAY_CARPET:
            case GRAY_CONCRETE:
            case GRAY_CONCRETE_POWDER:
            case GRAY_GLAZED_TERRACOTTA:
            case GRAY_SHULKER_BOX:
            case GRAY_STAINED_GLASS:
            case GRAY_STAINED_GLASS_PANE:
            case GRAY_TERRACOTTA:
            case GRAY_WALL_BANNER:
            case GRAY_WOOL:
            case GREEN_BANNER:
            case GREEN_BED:
            case GREEN_CANDLE:
            case GREEN_CANDLE_CAKE:
            case GREEN_CARPET:
            case GREEN_CONCRETE:
            case GREEN_CONCRETE_POWDER:
            case GREEN_GLAZED_TERRACOTTA:
            case GREEN_SHULKER_BOX:
            case GREEN_STAINED_GLASS:
            case GREEN_STAINED_GLASS_PANE:
            case GREEN_TERRACOTTA:
            case GREEN_WALL_BANNER:
            case GREEN_WOOL:
            case GRINDSTONE:
            case HANGING_ROOTS:
            case HAY_BLOCK:
            case HEAVY_WEIGHTED_PRESSURE_PLATE:
            case HONEYCOMB_BLOCK:
            case HONEY_BLOCK:
            case HOPPER:
            case HORN_CORAL:
            case HORN_CORAL_BLOCK:
            case HORN_CORAL_FAN:
            case HORN_CORAL_WALL_FAN:
            case ICE:
            case INFESTED_CHISELED_STONE_BRICKS:
            case INFESTED_COBBLESTONE:
            case INFESTED_CRACKED_STONE_BRICKS:
            case INFESTED_DEEPSLATE:
            case INFESTED_MOSSY_STONE_BRICKS:
            case INFESTED_STONE:
            case INFESTED_STONE_BRICKS:
            case IRON_BARS:
            case IRON_BLOCK:
            case IRON_DOOR:
            case IRON_ORE:
            case IRON_TRAPDOOR:
            case JACK_O_LANTERN:
            case JIGSAW:
            case JUKEBOX:
            case JUNGLE_BUTTON:
            case JUNGLE_DOOR:
            case JUNGLE_FENCE:
            case JUNGLE_FENCE_GATE:
            case JUNGLE_HANGING_SIGN:
            case JUNGLE_LEAVES:
            case JUNGLE_LOG:
            case JUNGLE_PLANKS:
            case JUNGLE_PRESSURE_PLATE:
            case JUNGLE_SAPLING:
            case JUNGLE_SIGN:
            case JUNGLE_SLAB:
            case JUNGLE_STAIRS:
            case JUNGLE_TRAPDOOR:
            case JUNGLE_WALL_HANGING_SIGN:
            case JUNGLE_WALL_SIGN:
            case JUNGLE_WOOD:
            case KELP:
            case KELP_PLANT:
            case LADDER:
            case LANTERN:
            case LAPIS_BLOCK:
            case LAPIS_ORE:
            case LARGE_AMETHYST_BUD:
            case LARGE_FERN:
            case LAVA:
            case LAVA_CAULDRON:
            case LECTERN:
            case LEVER:
            case LIGHT:
            case LIGHTNING_ROD:
            case LIGHT_BLUE_BANNER:
            case LIGHT_BLUE_BED:
            case LIGHT_BLUE_CANDLE:
            case LIGHT_BLUE_CANDLE_CAKE:
            case LIGHT_BLUE_CARPET:
            case LIGHT_BLUE_CONCRETE:
            case LIGHT_BLUE_CONCRETE_POWDER:
            case LIGHT_BLUE_GLAZED_TERRACOTTA:
            case LIGHT_BLUE_SHULKER_BOX:
            case LIGHT_BLUE_STAINED_GLASS:
            case LIGHT_BLUE_STAINED_GLASS_PANE:
            case LIGHT_BLUE_TERRACOTTA:
            case LIGHT_BLUE_WALL_BANNER:
            case LIGHT_BLUE_WOOL:
            case LIGHT_GRAY_BANNER:
            case LIGHT_GRAY_BED:
            case LIGHT_GRAY_CANDLE:
            case LIGHT_GRAY_CANDLE_CAKE:
            case LIGHT_GRAY_CARPET:
            case LIGHT_GRAY_CONCRETE:
            case LIGHT_GRAY_CONCRETE_POWDER:
            case LIGHT_GRAY_GLAZED_TERRACOTTA:
            case LIGHT_GRAY_SHULKER_BOX:
            case LIGHT_GRAY_STAINED_GLASS:
            case LIGHT_GRAY_STAINED_GLASS_PANE:
            case LIGHT_GRAY_TERRACOTTA:
            case LIGHT_GRAY_WALL_BANNER:
            case LIGHT_GRAY_WOOL:
            case LIGHT_WEIGHTED_PRESSURE_PLATE:
            case LILAC:
            case LILY_OF_THE_VALLEY:
            case LILY_PAD:
            case LIME_BANNER:
            case LIME_BED:
            case LIME_CANDLE:
            case LIME_CANDLE_CAKE:
            case LIME_CARPET:
            case LIME_CONCRETE:
            case LIME_CONCRETE_POWDER:
            case LIME_GLAZED_TERRACOTTA:
            case LIME_SHULKER_BOX:
            case LIME_STAINED_GLASS:
            case LIME_STAINED_GLASS_PANE:
            case LIME_TERRACOTTA:
            case LIME_WALL_BANNER:
            case LIME_WOOL:
            case LODESTONE:
            case LOOM:
            case MAGENTA_BANNER:
            case MAGENTA_BED:
            case MAGENTA_CANDLE:
            case MAGENTA_CANDLE_CAKE:
            case MAGENTA_CARPET:
            case MAGENTA_CONCRETE:
            case MAGENTA_CONCRETE_POWDER:
            case MAGENTA_GLAZED_TERRACOTTA:
            case MAGENTA_SHULKER_BOX:
            case MAGENTA_STAINED_GLASS:
            case MAGENTA_STAINED_GLASS_PANE:
            case MAGENTA_TERRACOTTA:
            case MAGENTA_WALL_BANNER:
            case MAGENTA_WOOL:
            case MAGMA_BLOCK:
            case MANGROVE_BUTTON:
            case MANGROVE_DOOR:
            case MANGROVE_FENCE:
            case MANGROVE_FENCE_GATE:
            case MANGROVE_HANGING_SIGN:
            case MANGROVE_LEAVES:
            case MANGROVE_LOG:
            case MANGROVE_PLANKS:
            case MANGROVE_PRESSURE_PLATE:
            case MANGROVE_PROPAGULE:
            case MANGROVE_ROOTS:
            case MANGROVE_SIGN:
            case MANGROVE_SLAB:
            case MANGROVE_STAIRS:
            case MANGROVE_TRAPDOOR:
            case MANGROVE_WALL_HANGING_SIGN:
            case MANGROVE_WALL_SIGN:
            case MANGROVE_WOOD:
            case MEDIUM_AMETHYST_BUD:
            case MELON:
            case MELON_STEM:
            case MOSSY_COBBLESTONE:
            case MOSSY_COBBLESTONE_SLAB:
            case MOSSY_COBBLESTONE_STAIRS:
            case MOSSY_COBBLESTONE_WALL:
            case MOSSY_STONE_BRICKS:
            case MOSSY_STONE_BRICK_SLAB:
            case MOSSY_STONE_BRICK_STAIRS:
            case MOSSY_STONE_BRICK_WALL:
            case MOSS_BLOCK:
            case MOSS_CARPET:
            case MOVING_PISTON:
            case MUD:
            case MUDDY_MANGROVE_ROOTS:
            case MUD_BRICKS:
            case MUD_BRICK_SLAB:
            case MUD_BRICK_STAIRS:
            case MUD_BRICK_WALL:
            case MUSHROOM_STEM:
            case MYCELIUM:
            case NETHERITE_BLOCK:
            case NETHERRACK:
            case NETHER_BRICKS:
            case NETHER_BRICK_FENCE:
            case NETHER_BRICK_SLAB:
            case NETHER_BRICK_STAIRS:
            case NETHER_BRICK_WALL:
            case NETHER_GOLD_ORE:
            case NETHER_PORTAL:
            case NETHER_QUARTZ_ORE:
            case NETHER_SPROUTS:
            case NETHER_WART:
            case NETHER_WART_BLOCK:
            case NOTE_BLOCK:
            case OAK_BUTTON:
            case OAK_DOOR:
            case OAK_FENCE:
            case OAK_FENCE_GATE:
            case OAK_HANGING_SIGN:
            case OAK_LEAVES:
            case OAK_LOG:
            case OAK_PLANKS:
            case OAK_PRESSURE_PLATE:
            case OAK_SAPLING:
            case OAK_SIGN:
            case OAK_SLAB:
            case OAK_STAIRS:
            case OAK_TRAPDOOR:
            case OAK_WALL_HANGING_SIGN:
            case OAK_WALL_SIGN:
            case OAK_WOOD:
            case OBSERVER:
            case OBSIDIAN:
            case OCHRE_FROGLIGHT:
            case ORANGE_BANNER:
            case ORANGE_BED:
            case ORANGE_CANDLE:
            case ORANGE_CANDLE_CAKE:
            case ORANGE_CARPET:
            case ORANGE_CONCRETE:
            case ORANGE_CONCRETE_POWDER:
            case ORANGE_GLAZED_TERRACOTTA:
            case ORANGE_SHULKER_BOX:
            case ORANGE_STAINED_GLASS:
            case ORANGE_STAINED_GLASS_PANE:
            case ORANGE_TERRACOTTA:
            case ORANGE_TULIP:
            case ORANGE_WALL_BANNER:
            case ORANGE_WOOL:
            case OXEYE_DAISY:
            case OXIDIZED_CHISELED_COPPER:
            case OXIDIZED_COPPER:
            case OXIDIZED_COPPER_BULB:
            case OXIDIZED_COPPER_DOOR:
            case OXIDIZED_COPPER_GRATE:
            case OXIDIZED_COPPER_TRAPDOOR:
            case OXIDIZED_CUT_COPPER:
            case OXIDIZED_CUT_COPPER_SLAB:
            case OXIDIZED_CUT_COPPER_STAIRS:
            case PACKED_ICE:
            case PACKED_MUD:
            case PEARLESCENT_FROGLIGHT:
            case PEONY:
            case PETRIFIED_OAK_SLAB:
            case PIGLIN_HEAD:
            case PIGLIN_WALL_HEAD:
            case PINK_BANNER:
            case PINK_BED:
            case PINK_CANDLE:
            case PINK_CANDLE_CAKE:
            case PINK_CARPET:
            case PINK_CONCRETE:
            case PINK_CONCRETE_POWDER:
            case PINK_GLAZED_TERRACOTTA:
            case PINK_PETALS:
            case PINK_SHULKER_BOX:
            case PINK_STAINED_GLASS:
            case PINK_STAINED_GLASS_PANE:
            case PINK_TERRACOTTA:
            case PINK_TULIP:
            case PINK_WALL_BANNER:
            case PINK_WOOL:
            case PISTON:
            case PISTON_HEAD:
            case PITCHER_CROP:
            case PITCHER_PLANT:
            case PLAYER_HEAD:
            case PLAYER_WALL_HEAD:
            case PODZOL:
            case POINTED_DRIPSTONE:
            case POLISHED_ANDESITE:
            case POLISHED_ANDESITE_SLAB:
            case POLISHED_ANDESITE_STAIRS:
            case POLISHED_BASALT:
            case POLISHED_BLACKSTONE:
            case POLISHED_BLACKSTONE_BRICKS:
            case POLISHED_BLACKSTONE_BRICK_SLAB:
            case POLISHED_BLACKSTONE_BRICK_STAIRS:
            case POLISHED_BLACKSTONE_BRICK_WALL:
            case POLISHED_BLACKSTONE_BUTTON:
            case POLISHED_BLACKSTONE_PRESSURE_PLATE:
            case POLISHED_BLACKSTONE_SLAB:
            case POLISHED_BLACKSTONE_STAIRS:
            case POLISHED_BLACKSTONE_WALL:
            case POLISHED_DEEPSLATE:
            case POLISHED_DEEPSLATE_SLAB:
            case POLISHED_DEEPSLATE_STAIRS:
            case POLISHED_DEEPSLATE_WALL:
            case POLISHED_DIORITE:
            case POLISHED_DIORITE_SLAB:
            case POLISHED_DIORITE_STAIRS:
            case POLISHED_GRANITE:
            case POLISHED_GRANITE_SLAB:
            case POLISHED_GRANITE_STAIRS:
            case POLISHED_TUFF:
            case POLISHED_TUFF_SLAB:
            case POLISHED_TUFF_STAIRS:
            case POLISHED_TUFF_WALL:
            case POPPY:
            case POTATOES:
            case POTTED_ACACIA_SAPLING:
            case POTTED_ALLIUM:
            case POTTED_AZALEA_BUSH:
            case POTTED_AZURE_BLUET:
            case POTTED_BAMBOO:
            case POTTED_BIRCH_SAPLING:
            case POTTED_BLUE_ORCHID:
            case POTTED_BROWN_MUSHROOM:
            case POTTED_CACTUS:
            case POTTED_CHERRY_SAPLING:
            case POTTED_CORNFLOWER:
            case POTTED_CRIMSON_FUNGUS:
            case POTTED_CRIMSON_ROOTS:
            case POTTED_DANDELION:
            case POTTED_DARK_OAK_SAPLING:
            case POTTED_DEAD_BUSH:
            case POTTED_FERN:
            case POTTED_FLOWERING_AZALEA_BUSH:
            case POTTED_JUNGLE_SAPLING:
            case POTTED_LILY_OF_THE_VALLEY:
            case POTTED_MANGROVE_PROPAGULE:
            case POTTED_OAK_SAPLING:
            case POTTED_ORANGE_TULIP:
            case POTTED_OXEYE_DAISY:
            case POTTED_PINK_TULIP:
            case POTTED_POPPY:
            case POTTED_RED_MUSHROOM:
            case POTTED_RED_TULIP:
            case POTTED_SPRUCE_SAPLING:
            case POTTED_TORCHFLOWER:
            case POTTED_WARPED_FUNGUS:
            case POTTED_WARPED_ROOTS:
            case POTTED_WHITE_TULIP:
            case POTTED_WITHER_ROSE:
            case POWDER_SNOW:
            case POWDER_SNOW_CAULDRON:
            case POWERED_RAIL:
            case PRISMARINE:
            case PRISMARINE_BRICKS:
            case PRISMARINE_BRICK_SLAB:
            case PRISMARINE_BRICK_STAIRS:
            case PRISMARINE_SLAB:
            case PRISMARINE_STAIRS:
            case PRISMARINE_WALL:
            case PUMPKIN:
            case PUMPKIN_STEM:
            case PURPLE_BANNER:
            case PURPLE_BED:
            case PURPLE_CANDLE:
            case PURPLE_CANDLE_CAKE:
            case PURPLE_CARPET:
            case PURPLE_CONCRETE:
            case PURPLE_CONCRETE_POWDER:
            case PURPLE_GLAZED_TERRACOTTA:
            case PURPLE_SHULKER_BOX:
            case PURPLE_STAINED_GLASS:
            case PURPLE_STAINED_GLASS_PANE:
            case PURPLE_TERRACOTTA:
            case PURPLE_WALL_BANNER:
            case PURPLE_WOOL:
            case PURPUR_BLOCK:
            case PURPUR_PILLAR:
            case PURPUR_SLAB:
            case PURPUR_STAIRS:
            case QUARTZ_BLOCK:
            case QUARTZ_BRICKS:
            case QUARTZ_PILLAR:
            case QUARTZ_SLAB:
            case QUARTZ_STAIRS:
            case RAIL:
            case RAW_COPPER_BLOCK:
            case RAW_GOLD_BLOCK:
            case RAW_IRON_BLOCK:
            case REDSTONE_BLOCK:
            case REDSTONE_LAMP:
            case REDSTONE_ORE:
            case REDSTONE_TORCH:
            case REDSTONE_WALL_TORCH:
            case REDSTONE_WIRE:
            case RED_BANNER:
            case RED_BED:
            case RED_CANDLE:
            case RED_CANDLE_CAKE:
            case RED_CARPET:
            case RED_CONCRETE:
            case RED_CONCRETE_POWDER:
            case RED_GLAZED_TERRACOTTA:
            case RED_MUSHROOM:
            case RED_MUSHROOM_BLOCK:
            case RED_NETHER_BRICKS:
            case RED_NETHER_BRICK_SLAB:
            case RED_NETHER_BRICK_STAIRS:
            case RED_NETHER_BRICK_WALL:
            case RED_SAND:
            case RED_SANDSTONE:
            case RED_SANDSTONE_SLAB:
            case RED_SANDSTONE_STAIRS:
            case RED_SANDSTONE_WALL:
            case RED_SHULKER_BOX:
            case RED_STAINED_GLASS:
            case RED_STAINED_GLASS_PANE:
            case RED_TERRACOTTA:
            case RED_TULIP:
            case RED_WALL_BANNER:
            case RED_WOOL:
            case REINFORCED_DEEPSLATE:
            case REPEATER:
            case REPEATING_COMMAND_BLOCK:
            case RESPAWN_ANCHOR:
            case ROOTED_DIRT:
            case ROSE_BUSH:
            case SAND:
            case SANDSTONE:
            case SANDSTONE_SLAB:
            case SANDSTONE_STAIRS:
            case SANDSTONE_WALL:
            case SCAFFOLDING:
            case SCULK:
            case SCULK_CATALYST:
            case SCULK_SENSOR:
            case SCULK_SHRIEKER:
            case SCULK_VEIN:
            case SEAGRASS:
            case SEA_LANTERN:
            case SEA_PICKLE:
            case SHORT_GRASS:
            case SHROOMLIGHT:
            case SHULKER_BOX:
            case SKELETON_SKULL:
            case SKELETON_WALL_SKULL:
            case SLIME_BLOCK:
            case SMALL_AMETHYST_BUD:
            case SMALL_DRIPLEAF:
            case SMITHING_TABLE:
            case SMOKER:
            case SMOOTH_BASALT:
            case SMOOTH_QUARTZ:
            case SMOOTH_QUARTZ_SLAB:
            case SMOOTH_QUARTZ_STAIRS:
            case SMOOTH_RED_SANDSTONE:
            case SMOOTH_RED_SANDSTONE_SLAB:
            case SMOOTH_RED_SANDSTONE_STAIRS:
            case SMOOTH_SANDSTONE:
            case SMOOTH_SANDSTONE_SLAB:
            case SMOOTH_SANDSTONE_STAIRS:
            case SMOOTH_STONE:
            case SMOOTH_STONE_SLAB:
            case SNIFFER_EGG:
            case SNOW:
            case SNOW_BLOCK:
            case SOUL_CAMPFIRE:
            case SOUL_FIRE:
            case SOUL_LANTERN:
            case SOUL_SAND:
            case SOUL_SOIL:
            case SOUL_TORCH:
            case SOUL_WALL_TORCH:
            case SPAWNER:
            case SPONGE:
            case SPORE_BLOSSOM:
            case SPRUCE_BUTTON:
            case SPRUCE_DOOR:
            case SPRUCE_FENCE:
            case SPRUCE_FENCE_GATE:
            case SPRUCE_HANGING_SIGN:
            case SPRUCE_LEAVES:
            case SPRUCE_LOG:
            case SPRUCE_PLANKS:
            case SPRUCE_PRESSURE_PLATE:
            case SPRUCE_SAPLING:
            case SPRUCE_SIGN:
            case SPRUCE_SLAB:
            case SPRUCE_STAIRS:
            case SPRUCE_TRAPDOOR:
            case SPRUCE_WALL_HANGING_SIGN:
            case SPRUCE_WALL_SIGN:
            case SPRUCE_WOOD:
            case STICKY_PISTON:
            case STONE:
            case STONECUTTER:
            case STONE_BRICKS:
            case STONE_BRICK_SLAB:
            case STONE_BRICK_STAIRS:
            case STONE_BRICK_WALL:
            case STONE_BUTTON:
            case STONE_PRESSURE_PLATE:
            case STONE_SLAB:
            case STONE_STAIRS:
            case STRIPPED_ACACIA_LOG:
            case STRIPPED_ACACIA_WOOD:
            case STRIPPED_BAMBOO_BLOCK:
            case STRIPPED_BIRCH_LOG:
            case STRIPPED_BIRCH_WOOD:
            case STRIPPED_CHERRY_LOG:
            case STRIPPED_CHERRY_WOOD:
            case STRIPPED_CRIMSON_HYPHAE:
            case STRIPPED_CRIMSON_STEM:
            case STRIPPED_DARK_OAK_LOG:
            case STRIPPED_DARK_OAK_WOOD:
            case STRIPPED_JUNGLE_LOG:
            case STRIPPED_JUNGLE_WOOD:
            case STRIPPED_MANGROVE_LOG:
            case STRIPPED_MANGROVE_WOOD:
            case STRIPPED_OAK_LOG:
            case STRIPPED_OAK_WOOD:
            case STRIPPED_SPRUCE_LOG:
            case STRIPPED_SPRUCE_WOOD:
            case STRIPPED_WARPED_HYPHAE:
            case STRIPPED_WARPED_STEM:
            case STRUCTURE_BLOCK:
            case STRUCTURE_VOID:
            case SUGAR_CANE:
            case SUNFLOWER:
            case SUSPICIOUS_GRAVEL:
            case SUSPICIOUS_SAND:
            case SWEET_BERRY_BUSH:
            case TALL_GRASS:
            case TALL_SEAGRASS:
            case TARGET:
            case TERRACOTTA:
            case TINTED_GLASS:
            case TNT:
            case TORCH:
            case TORCHFLOWER:
            case TORCHFLOWER_CROP:
            case TRAPPED_CHEST:
            case TRIAL_SPAWNER:
            case TRIPWIRE:
            case TRIPWIRE_HOOK:
            case TUBE_CORAL:
            case TUBE_CORAL_BLOCK:
            case TUBE_CORAL_FAN:
            case TUBE_CORAL_WALL_FAN:
            case TUFF:
            case TUFF_BRICKS:
            case TUFF_BRICK_SLAB:
            case TUFF_BRICK_STAIRS:
            case TUFF_BRICK_WALL:
            case TUFF_SLAB:
            case TUFF_STAIRS:
            case TUFF_WALL:
            case TURTLE_EGG:
            case TWISTING_VINES:
            case TWISTING_VINES_PLANT:
            case VERDANT_FROGLIGHT:
            case VINE:
            case VOID_AIR:
            case WALL_TORCH:
            case WARPED_BUTTON:
            case WARPED_DOOR:
            case WARPED_FENCE:
            case WARPED_FENCE_GATE:
            case WARPED_FUNGUS:
            case WARPED_HANGING_SIGN:
            case WARPED_HYPHAE:
            case WARPED_NYLIUM:
            case WARPED_PLANKS:
            case WARPED_PRESSURE_PLATE:
            case WARPED_ROOTS:
            case WARPED_SIGN:
            case WARPED_SLAB:
            case WARPED_STAIRS:
            case WARPED_STEM:
            case WARPED_TRAPDOOR:
            case WARPED_WALL_HANGING_SIGN:
            case WARPED_WALL_SIGN:
            case WARPED_WART_BLOCK:
            case WATER:
            case WATER_CAULDRON:
            case WAXED_CHISELED_COPPER:
            case WAXED_COPPER_BLOCK:
            case WAXED_COPPER_BULB:
            case WAXED_COPPER_DOOR:
            case WAXED_COPPER_GRATE:
            case WAXED_COPPER_TRAPDOOR:
            case WAXED_CUT_COPPER:
            case WAXED_CUT_COPPER_SLAB:
            case WAXED_CUT_COPPER_STAIRS:
            case WAXED_EXPOSED_CHISELED_COPPER:
            case WAXED_EXPOSED_COPPER:
            case WAXED_EXPOSED_COPPER_BULB:
            case WAXED_EXPOSED_COPPER_DOOR:
            case WAXED_EXPOSED_COPPER_GRATE:
            case WAXED_EXPOSED_COPPER_TRAPDOOR:
            case WAXED_EXPOSED_CUT_COPPER:
            case WAXED_EXPOSED_CUT_COPPER_SLAB:
            case WAXED_EXPOSED_CUT_COPPER_STAIRS:
            case WAXED_OXIDIZED_CHISELED_COPPER:
            case WAXED_OXIDIZED_COPPER:
            case WAXED_OXIDIZED_COPPER_BULB:
            case WAXED_OXIDIZED_COPPER_DOOR:
            case WAXED_OXIDIZED_COPPER_GRATE:
            case WAXED_OXIDIZED_COPPER_TRAPDOOR:
            case WAXED_OXIDIZED_CUT_COPPER:
            case WAXED_OXIDIZED_CUT_COPPER_SLAB:
            case WAXED_OXIDIZED_CUT_COPPER_STAIRS:
            case WAXED_WEATHERED_CHISELED_COPPER:
            case WAXED_WEATHERED_COPPER:
            case WAXED_WEATHERED_COPPER_BULB:
            case WAXED_WEATHERED_COPPER_DOOR:
            case WAXED_WEATHERED_COPPER_GRATE:
            case WAXED_WEATHERED_COPPER_TRAPDOOR:
            case WAXED_WEATHERED_CUT_COPPER:
            case WAXED_WEATHERED_CUT_COPPER_SLAB:
            case WAXED_WEATHERED_CUT_COPPER_STAIRS:
            case WEATHERED_CHISELED_COPPER:
            case WEATHERED_COPPER:
            case WEATHERED_COPPER_BULB:
            case WEATHERED_COPPER_DOOR:
            case WEATHERED_COPPER_GRATE:
            case WEATHERED_COPPER_TRAPDOOR:
            case WEATHERED_CUT_COPPER:
            case WEATHERED_CUT_COPPER_SLAB:
            case WEATHERED_CUT_COPPER_STAIRS:
            case WEEPING_VINES:
            case WEEPING_VINES_PLANT:
            case WET_SPONGE:
            case WHEAT:
            case WHITE_BANNER:
            case WHITE_BED:
            case WHITE_CANDLE:
            case WHITE_CANDLE_CAKE:
            case WHITE_CARPET:
            case WHITE_CONCRETE:
            case WHITE_CONCRETE_POWDER:
            case WHITE_GLAZED_TERRACOTTA:
            case WHITE_SHULKER_BOX:
            case WHITE_STAINED_GLASS:
            case WHITE_STAINED_GLASS_PANE:
            case WHITE_TERRACOTTA:
            case WHITE_TULIP:
            case WHITE_WALL_BANNER:
            case WHITE_WOOL:
            case WITHER_ROSE:
            case WITHER_SKELETON_SKULL:
            case WITHER_SKELETON_WALL_SKULL:
            case YELLOW_BANNER:
            case YELLOW_BED:
            case YELLOW_CANDLE:
            case YELLOW_CANDLE_CAKE:
            case YELLOW_CARPET:
            case YELLOW_CONCRETE:
            case YELLOW_CONCRETE_POWDER:
            case YELLOW_GLAZED_TERRACOTTA:
            case YELLOW_SHULKER_BOX:
            case YELLOW_STAINED_GLASS:
            case YELLOW_STAINED_GLASS_PANE:
            case YELLOW_TERRACOTTA:
            case YELLOW_WALL_BANNER:
            case YELLOW_WOOL:
            case ZOMBIE_HEAD:
            case ZOMBIE_WALL_HEAD:
            // Paper end - Generated/Material#isBlock
            //</editor-fold>
                return true;
            default:
                return 0 <= id && id < 256;
        }
    }

    /**
     * Checks if this Material is edible.
     *
     * @return true if this Material is edible.
     */
    public boolean isEdible() {
        switch (this) {
            //<editor-fold defaultstate="collapsed" desc="isEdible">
            // Paper start - Generated/Material#isEdible
            // @GeneratedFrom 1.20.4
            case APPLE:
            case BAKED_POTATO:
            case BEEF:
            case BEETROOT:
            case BEETROOT_SOUP:
            case BREAD:
            case CARROT:
            case CHICKEN:
            case CHORUS_FRUIT:
            case COD:
            case COOKED_BEEF:
            case COOKED_CHICKEN:
            case COOKED_COD:
            case COOKED_MUTTON:
            case COOKED_PORKCHOP:
            case COOKED_RABBIT:
            case COOKED_SALMON:
            case COOKIE:
            case DRIED_KELP:
            case ENCHANTED_GOLDEN_APPLE:
            case GLOW_BERRIES:
            case GOLDEN_APPLE:
            case GOLDEN_CARROT:
            case HONEY_BOTTLE:
            case MELON_SLICE:
            case MUSHROOM_STEW:
            case MUTTON:
            case POISONOUS_POTATO:
            case PORKCHOP:
            case POTATO:
            case PUFFERFISH:
            case PUMPKIN_PIE:
            case RABBIT:
            case RABBIT_STEW:
            case ROTTEN_FLESH:
            case SALMON:
            case SPIDER_EYE:
            case SUSPICIOUS_STEW:
            case SWEET_BERRIES:
            case TROPICAL_FISH:
            // Paper end - Generated/Material#isEdible
            // ----- Legacy Separator -----
            case LEGACY_BREAD:
            case LEGACY_CARROT_ITEM:
            case LEGACY_BAKED_POTATO:
            case LEGACY_POTATO_ITEM:
            case LEGACY_POISONOUS_POTATO:
            case LEGACY_GOLDEN_CARROT:
            case LEGACY_PUMPKIN_PIE:
            case LEGACY_COOKIE:
            case LEGACY_MELON:
            case LEGACY_MUSHROOM_SOUP:
            case LEGACY_RAW_CHICKEN:
            case LEGACY_COOKED_CHICKEN:
            case LEGACY_RAW_BEEF:
            case LEGACY_COOKED_BEEF:
            case LEGACY_RAW_FISH:
            case LEGACY_COOKED_FISH:
            case LEGACY_PORK:
            case LEGACY_GRILLED_PORK:
            case LEGACY_APPLE:
            case LEGACY_GOLDEN_APPLE:
            case LEGACY_ROTTEN_FLESH:
            case LEGACY_SPIDER_EYE:
            case LEGACY_RABBIT:
            case LEGACY_COOKED_RABBIT:
            case LEGACY_RABBIT_STEW:
            case LEGACY_MUTTON:
            case LEGACY_COOKED_MUTTON:
            case LEGACY_BEETROOT:
            case LEGACY_CHORUS_FRUIT:
            case LEGACY_BEETROOT_SOUP:
            //</editor-fold>
                return true;
            default:
                return false;
        }
    }

    /**
     * Attempts to get the Material with the given name.
     * <p>
     * This is a normal lookup, names must be the precise name they are given
     * in the enum.
     *
     * @param name Name of the material to get
     * @return Material if found, or null
     */
    @Nullable
    public static Material getMaterial(@NotNull final String name) {
        return getMaterial(name, false);
    }

    /**
     * Attempts to get the Material with the given name.
     * <p>
     * This is a normal lookup, names must be the precise name they are given in
     * the enum (but optionally including the LEGACY_PREFIX if legacyName is
     * true).
     * <p>
     * If legacyName is true, then the lookup will be against legacy materials,
     * but the returned Material will be a modern material (ie this method is
     * useful for updating stored data).
     *
     * @param name Name of the material to get
     * @param legacyName whether this is a legacy name lookup
     * @return Material if found, or null
     */
    @Nullable
    public static Material getMaterial(@NotNull String name, boolean legacyName) {
        if (legacyName) {
            if (!name.startsWith(LEGACY_PREFIX)) {
                name = LEGACY_PREFIX + name;
            }

            Material match = BY_NAME.get(name);
            return Bukkit.getUnsafe().fromLegacy(match);
        }

        return BY_NAME.get(name);
    }

    /**
     * Attempts to match the Material with the given name.
     * <p>
     * This is a match lookup; names will be stripped of the "minecraft:"
     * namespace, converted to uppercase, then stripped of special characters in
     * an attempt to format it like the enum.
     *
     * @param name Name of the material to get
     * @return Material if found, or null
     */
    @Nullable
    public static Material matchMaterial(@NotNull final String name) {
        return matchMaterial(name, false);
    }

    /**
     * Attempts to match the Material with the given name.
     * <p>
     * This is a match lookup; names will be stripped of the "minecraft:"
     * namespace, converted to uppercase, then stripped of special characters in
     * an attempt to format it like the enum.
     *
     * @param name Name of the material to get
     * @param legacyName whether this is a legacy name (see
     * {@link #getMaterial(java.lang.String, boolean)}
     * @return Material if found, or null
     */
    @Nullable
    public static Material matchMaterial(@NotNull final String name, boolean legacyName) {
        Preconditions.checkArgument(name != null, "Name cannot be null");

        String filtered = name;
        if (filtered.startsWith(NamespacedKey.MINECRAFT + ":")) {
            filtered = filtered.substring((NamespacedKey.MINECRAFT + ":").length());
        }

        filtered = filtered.toUpperCase(java.util.Locale.ENGLISH);

        filtered = filtered.replaceAll("\\s+", "_").replaceAll("\\W", "");
        return getMaterial(filtered, legacyName);
    }

    static {
        for (Material material : values()) {
            BY_NAME.put(material.name(), material);
            material.isBlock = material.isBlock0(); // Paper
        }
    }

    /**
     * @return True if this material represents a playable music disk.
     */
    public boolean isRecord() {
        switch (this) {
            //<editor-fold defaultstate="collapsed" desc="isRecord">
            // Paper start - Generated/Material#isRecord
            // @GeneratedFrom 1.20.4
            case MUSIC_DISC_5:
            case MUSIC_DISC_11:
            case MUSIC_DISC_13:
            case MUSIC_DISC_BLOCKS:
            case MUSIC_DISC_CAT:
            case MUSIC_DISC_CHIRP:
            case MUSIC_DISC_FAR:
            case MUSIC_DISC_MALL:
            case MUSIC_DISC_MELLOHI:
            case MUSIC_DISC_OTHERSIDE:
            case MUSIC_DISC_PIGSTEP:
            case MUSIC_DISC_RELIC:
            case MUSIC_DISC_STAL:
            case MUSIC_DISC_STRAD:
            case MUSIC_DISC_WAIT:
            case MUSIC_DISC_WARD:
            // Paper end - Generated/Material#isRecord
            //</editor-fold>
                return true;
            default:
                return id >= LEGACY_GOLD_RECORD.id && id <= LEGACY_RECORD_12.id;
        }
    }

    /**
     * Check if the material is a block and solid (can be built upon)
     *
     * @return True if this material is a block and solid
     */
    public boolean isSolid() {
        if (!isBlock() || id == 0) {
            return false;
        }
        switch (this) {
            //<editor-fold defaultstate="collapsed" desc="isSolid">
            // Paper start - Generated/Material#isSolid
            // @GeneratedFrom 1.20.4
            case ACACIA_DOOR:
            case ACACIA_FENCE:
            case ACACIA_FENCE_GATE:
            case ACACIA_HANGING_SIGN:
            case ACACIA_LEAVES:
            case ACACIA_LOG:
            case ACACIA_PLANKS:
            case ACACIA_PRESSURE_PLATE:
            case ACACIA_SIGN:
            case ACACIA_SLAB:
            case ACACIA_STAIRS:
            case ACACIA_TRAPDOOR:
            case ACACIA_WALL_HANGING_SIGN:
            case ACACIA_WALL_SIGN:
            case ACACIA_WOOD:
            case AMETHYST_BLOCK:
            case AMETHYST_CLUSTER:
            case ANCIENT_DEBRIS:
            case ANDESITE:
            case ANDESITE_SLAB:
            case ANDESITE_STAIRS:
            case ANDESITE_WALL:
            case ANVIL:
            case AZALEA_LEAVES:
            case BAMBOO:
            case BAMBOO_BLOCK:
            case BAMBOO_DOOR:
            case BAMBOO_FENCE:
            case BAMBOO_FENCE_GATE:
            case BAMBOO_HANGING_SIGN:
            case BAMBOO_MOSAIC:
            case BAMBOO_MOSAIC_SLAB:
            case BAMBOO_MOSAIC_STAIRS:
            case BAMBOO_PLANKS:
            case BAMBOO_PRESSURE_PLATE:
            case BAMBOO_SIGN:
            case BAMBOO_SLAB:
            case BAMBOO_STAIRS:
            case BAMBOO_TRAPDOOR:
            case BAMBOO_WALL_HANGING_SIGN:
            case BAMBOO_WALL_SIGN:
            case BARREL:
            case BARRIER:
            case BASALT:
            case BEACON:
            case BEDROCK:
            case BEEHIVE:
            case BEE_NEST:
            case BELL:
            case BIRCH_DOOR:
            case BIRCH_FENCE:
            case BIRCH_FENCE_GATE:
            case BIRCH_HANGING_SIGN:
            case BIRCH_LEAVES:
            case BIRCH_LOG:
            case BIRCH_PLANKS:
            case BIRCH_PRESSURE_PLATE:
            case BIRCH_SIGN:
            case BIRCH_SLAB:
            case BIRCH_STAIRS:
            case BIRCH_TRAPDOOR:
            case BIRCH_WALL_HANGING_SIGN:
            case BIRCH_WALL_SIGN:
            case BIRCH_WOOD:
            case BLACKSTONE:
            case BLACKSTONE_SLAB:
            case BLACKSTONE_STAIRS:
            case BLACKSTONE_WALL:
            case BLACK_BANNER:
            case BLACK_BED:
            case BLACK_CANDLE_CAKE:
            case BLACK_CONCRETE:
            case BLACK_CONCRETE_POWDER:
            case BLACK_GLAZED_TERRACOTTA:
            case BLACK_SHULKER_BOX:
            case BLACK_STAINED_GLASS:
            case BLACK_STAINED_GLASS_PANE:
            case BLACK_TERRACOTTA:
            case BLACK_WALL_BANNER:
            case BLACK_WOOL:
            case BLAST_FURNACE:
            case BLUE_BANNER:
            case BLUE_BED:
            case BLUE_CANDLE_CAKE:
            case BLUE_CONCRETE:
            case BLUE_CONCRETE_POWDER:
            case BLUE_GLAZED_TERRACOTTA:
            case BLUE_ICE:
            case BLUE_SHULKER_BOX:
            case BLUE_STAINED_GLASS:
            case BLUE_STAINED_GLASS_PANE:
            case BLUE_TERRACOTTA:
            case BLUE_WALL_BANNER:
            case BLUE_WOOL:
            case BONE_BLOCK:
            case BOOKSHELF:
            case BRAIN_CORAL_BLOCK:
            case BREWING_STAND:
            case BRICKS:
            case BRICK_SLAB:
            case BRICK_STAIRS:
            case BRICK_WALL:
            case BROWN_BANNER:
            case BROWN_BED:
            case BROWN_CANDLE_CAKE:
            case BROWN_CONCRETE:
            case BROWN_CONCRETE_POWDER:
            case BROWN_GLAZED_TERRACOTTA:
            case BROWN_MUSHROOM_BLOCK:
            case BROWN_SHULKER_BOX:
            case BROWN_STAINED_GLASS:
            case BROWN_STAINED_GLASS_PANE:
            case BROWN_TERRACOTTA:
            case BROWN_WALL_BANNER:
            case BROWN_WOOL:
            case BUBBLE_CORAL_BLOCK:
            case BUDDING_AMETHYST:
            case CACTUS:
            case CAKE:
            case CALCITE:
            case CALIBRATED_SCULK_SENSOR:
            case CAMPFIRE:
            case CANDLE_CAKE:
            case CARTOGRAPHY_TABLE:
            case CARVED_PUMPKIN:
            case CAULDRON:
            case CHAIN:
            case CHAIN_COMMAND_BLOCK:
            case CHERRY_DOOR:
            case CHERRY_FENCE:
            case CHERRY_FENCE_GATE:
            case CHERRY_HANGING_SIGN:
            case CHERRY_LEAVES:
            case CHERRY_LOG:
            case CHERRY_PLANKS:
            case CHERRY_PRESSURE_PLATE:
            case CHERRY_SIGN:
            case CHERRY_SLAB:
            case CHERRY_STAIRS:
            case CHERRY_TRAPDOOR:
            case CHERRY_WALL_HANGING_SIGN:
            case CHERRY_WALL_SIGN:
            case CHERRY_WOOD:
            case CHEST:
            case CHIPPED_ANVIL:
            case CHISELED_BOOKSHELF:
            case CHISELED_COPPER:
            case CHISELED_DEEPSLATE:
            case CHISELED_NETHER_BRICKS:
            case CHISELED_POLISHED_BLACKSTONE:
            case CHISELED_QUARTZ_BLOCK:
            case CHISELED_RED_SANDSTONE:
            case CHISELED_SANDSTONE:
            case CHISELED_STONE_BRICKS:
            case CHISELED_TUFF:
            case CHISELED_TUFF_BRICKS:
            case CLAY:
            case COAL_BLOCK:
            case COAL_ORE:
            case COARSE_DIRT:
            case COBBLED_DEEPSLATE:
            case COBBLED_DEEPSLATE_SLAB:
            case COBBLED_DEEPSLATE_STAIRS:
            case COBBLED_DEEPSLATE_WALL:
            case COBBLESTONE:
            case COBBLESTONE_SLAB:
            case COBBLESTONE_STAIRS:
            case COBBLESTONE_WALL:
            case COMMAND_BLOCK:
            case COMPOSTER:
            case CONDUIT:
            case COPPER_BLOCK:
            case COPPER_BULB:
            case COPPER_DOOR:
            case COPPER_GRATE:
            case COPPER_ORE:
            case COPPER_TRAPDOOR:
            case CRACKED_DEEPSLATE_BRICKS:
            case CRACKED_DEEPSLATE_TILES:
            case CRACKED_NETHER_BRICKS:
            case CRACKED_POLISHED_BLACKSTONE_BRICKS:
            case CRACKED_STONE_BRICKS:
            case CRAFTER:
            case CRAFTING_TABLE:
            case CRIMSON_DOOR:
            case CRIMSON_FENCE:
            case CRIMSON_FENCE_GATE:
            case CRIMSON_HANGING_SIGN:
            case CRIMSON_HYPHAE:
            case CRIMSON_NYLIUM:
            case CRIMSON_PLANKS:
            case CRIMSON_PRESSURE_PLATE:
            case CRIMSON_SIGN:
            case CRIMSON_SLAB:
            case CRIMSON_STAIRS:
            case CRIMSON_STEM:
            case CRIMSON_TRAPDOOR:
            case CRIMSON_WALL_HANGING_SIGN:
            case CRIMSON_WALL_SIGN:
            case CRYING_OBSIDIAN:
            case CUT_COPPER:
            case CUT_COPPER_SLAB:
            case CUT_COPPER_STAIRS:
            case CUT_RED_SANDSTONE:
            case CUT_RED_SANDSTONE_SLAB:
            case CUT_SANDSTONE:
            case CUT_SANDSTONE_SLAB:
            case CYAN_BANNER:
            case CYAN_BED:
            case CYAN_CANDLE_CAKE:
            case CYAN_CONCRETE:
            case CYAN_CONCRETE_POWDER:
            case CYAN_GLAZED_TERRACOTTA:
            case CYAN_SHULKER_BOX:
            case CYAN_STAINED_GLASS:
            case CYAN_STAINED_GLASS_PANE:
            case CYAN_TERRACOTTA:
            case CYAN_WALL_BANNER:
            case CYAN_WOOL:
            case DAMAGED_ANVIL:
            case DARK_OAK_DOOR:
            case DARK_OAK_FENCE:
            case DARK_OAK_FENCE_GATE:
            case DARK_OAK_HANGING_SIGN:
            case DARK_OAK_LEAVES:
            case DARK_OAK_LOG:
            case DARK_OAK_PLANKS:
            case DARK_OAK_PRESSURE_PLATE:
            case DARK_OAK_SIGN:
            case DARK_OAK_SLAB:
            case DARK_OAK_STAIRS:
            case DARK_OAK_TRAPDOOR:
            case DARK_OAK_WALL_HANGING_SIGN:
            case DARK_OAK_WALL_SIGN:
            case DARK_OAK_WOOD:
            case DARK_PRISMARINE:
            case DARK_PRISMARINE_SLAB:
            case DARK_PRISMARINE_STAIRS:
            case DAYLIGHT_DETECTOR:
            case DEAD_BRAIN_CORAL:
            case DEAD_BRAIN_CORAL_BLOCK:
            case DEAD_BRAIN_CORAL_FAN:
            case DEAD_BRAIN_CORAL_WALL_FAN:
            case DEAD_BUBBLE_CORAL:
            case DEAD_BUBBLE_CORAL_BLOCK:
            case DEAD_BUBBLE_CORAL_FAN:
            case DEAD_BUBBLE_CORAL_WALL_FAN:
            case DEAD_FIRE_CORAL:
            case DEAD_FIRE_CORAL_BLOCK:
            case DEAD_FIRE_CORAL_FAN:
            case DEAD_FIRE_CORAL_WALL_FAN:
            case DEAD_HORN_CORAL:
            case DEAD_HORN_CORAL_BLOCK:
            case DEAD_HORN_CORAL_FAN:
            case DEAD_HORN_CORAL_WALL_FAN:
            case DEAD_TUBE_CORAL:
            case DEAD_TUBE_CORAL_BLOCK:
            case DEAD_TUBE_CORAL_FAN:
            case DEAD_TUBE_CORAL_WALL_FAN:
            case DECORATED_POT:
            case DEEPSLATE:
            case DEEPSLATE_BRICKS:
            case DEEPSLATE_BRICK_SLAB:
            case DEEPSLATE_BRICK_STAIRS:
            case DEEPSLATE_BRICK_WALL:
            case DEEPSLATE_COAL_ORE:
            case DEEPSLATE_COPPER_ORE:
            case DEEPSLATE_DIAMOND_ORE:
            case DEEPSLATE_EMERALD_ORE:
            case DEEPSLATE_GOLD_ORE:
            case DEEPSLATE_IRON_ORE:
            case DEEPSLATE_LAPIS_ORE:
            case DEEPSLATE_REDSTONE_ORE:
            case DEEPSLATE_TILES:
            case DEEPSLATE_TILE_SLAB:
            case DEEPSLATE_TILE_STAIRS:
            case DEEPSLATE_TILE_WALL:
            case DIAMOND_BLOCK:
            case DIAMOND_ORE:
            case DIORITE:
            case DIORITE_SLAB:
            case DIORITE_STAIRS:
            case DIORITE_WALL:
            case DIRT:
            case DIRT_PATH:
            case DISPENSER:
            case DRAGON_EGG:
            case DRIED_KELP_BLOCK:
            case DRIPSTONE_BLOCK:
            case DROPPER:
            case EMERALD_BLOCK:
            case EMERALD_ORE:
            case ENCHANTING_TABLE:
            case ENDER_CHEST:
            case END_PORTAL_FRAME:
            case END_STONE:
            case END_STONE_BRICKS:
            case END_STONE_BRICK_SLAB:
            case END_STONE_BRICK_STAIRS:
            case END_STONE_BRICK_WALL:
            case EXPOSED_CHISELED_COPPER:
            case EXPOSED_COPPER:
            case EXPOSED_COPPER_BULB:
            case EXPOSED_COPPER_DOOR:
            case EXPOSED_COPPER_GRATE:
            case EXPOSED_COPPER_TRAPDOOR:
            case EXPOSED_CUT_COPPER:
            case EXPOSED_CUT_COPPER_SLAB:
            case EXPOSED_CUT_COPPER_STAIRS:
            case FARMLAND:
            case FIRE_CORAL_BLOCK:
            case FLETCHING_TABLE:
            case FLOWERING_AZALEA_LEAVES:
            case FROSTED_ICE:
            case FURNACE:
            case GILDED_BLACKSTONE:
            case GLASS:
            case GLASS_PANE:
            case GLOWSTONE:
            case GOLD_BLOCK:
            case GOLD_ORE:
            case GRANITE:
            case GRANITE_SLAB:
            case GRANITE_STAIRS:
            case GRANITE_WALL:
            case GRASS_BLOCK:
            case GRAVEL:
            case GRAY_BANNER:
            case GRAY_BED:
            case GRAY_CANDLE_CAKE:
            case GRAY_CONCRETE:
            case GRAY_CONCRETE_POWDER:
            case GRAY_GLAZED_TERRACOTTA:
            case GRAY_SHULKER_BOX:
            case GRAY_STAINED_GLASS:
            case GRAY_STAINED_GLASS_PANE:
            case GRAY_TERRACOTTA:
            case GRAY_WALL_BANNER:
            case GRAY_WOOL:
            case GREEN_BANNER:
            case GREEN_BED:
            case GREEN_CANDLE_CAKE:
            case GREEN_CONCRETE:
            case GREEN_CONCRETE_POWDER:
            case GREEN_GLAZED_TERRACOTTA:
            case GREEN_SHULKER_BOX:
            case GREEN_STAINED_GLASS:
            case GREEN_STAINED_GLASS_PANE:
            case GREEN_TERRACOTTA:
            case GREEN_WALL_BANNER:
            case GREEN_WOOL:
            case GRINDSTONE:
            case HAY_BLOCK:
            case HEAVY_WEIGHTED_PRESSURE_PLATE:
            case HONEYCOMB_BLOCK:
            case HONEY_BLOCK:
            case HOPPER:
            case HORN_CORAL_BLOCK:
            case ICE:
            case INFESTED_CHISELED_STONE_BRICKS:
            case INFESTED_COBBLESTONE:
            case INFESTED_CRACKED_STONE_BRICKS:
            case INFESTED_DEEPSLATE:
            case INFESTED_MOSSY_STONE_BRICKS:
            case INFESTED_STONE:
            case INFESTED_STONE_BRICKS:
            case IRON_BARS:
            case IRON_BLOCK:
            case IRON_DOOR:
            case IRON_ORE:
            case IRON_TRAPDOOR:
            case JACK_O_LANTERN:
            case JIGSAW:
            case JUKEBOX:
            case JUNGLE_DOOR:
            case JUNGLE_FENCE:
            case JUNGLE_FENCE_GATE:
            case JUNGLE_HANGING_SIGN:
            case JUNGLE_LEAVES:
            case JUNGLE_LOG:
            case JUNGLE_PLANKS:
            case JUNGLE_PRESSURE_PLATE:
            case JUNGLE_SIGN:
            case JUNGLE_SLAB:
            case JUNGLE_STAIRS:
            case JUNGLE_TRAPDOOR:
            case JUNGLE_WALL_HANGING_SIGN:
            case JUNGLE_WALL_SIGN:
            case JUNGLE_WOOD:
            case LANTERN:
            case LAPIS_BLOCK:
            case LAPIS_ORE:
            case LARGE_AMETHYST_BUD:
            case LAVA_CAULDRON:
            case LECTERN:
            case LIGHTNING_ROD:
            case LIGHT_BLUE_BANNER:
            case LIGHT_BLUE_BED:
            case LIGHT_BLUE_CANDLE_CAKE:
            case LIGHT_BLUE_CONCRETE:
            case LIGHT_BLUE_CONCRETE_POWDER:
            case LIGHT_BLUE_GLAZED_TERRACOTTA:
            case LIGHT_BLUE_SHULKER_BOX:
            case LIGHT_BLUE_STAINED_GLASS:
            case LIGHT_BLUE_STAINED_GLASS_PANE:
            case LIGHT_BLUE_TERRACOTTA:
            case LIGHT_BLUE_WALL_BANNER:
            case LIGHT_BLUE_WOOL:
            case LIGHT_GRAY_BANNER:
            case LIGHT_GRAY_BED:
            case LIGHT_GRAY_CANDLE_CAKE:
            case LIGHT_GRAY_CONCRETE:
            case LIGHT_GRAY_CONCRETE_POWDER:
            case LIGHT_GRAY_GLAZED_TERRACOTTA:
            case LIGHT_GRAY_SHULKER_BOX:
            case LIGHT_GRAY_STAINED_GLASS:
            case LIGHT_GRAY_STAINED_GLASS_PANE:
            case LIGHT_GRAY_TERRACOTTA:
            case LIGHT_GRAY_WALL_BANNER:
            case LIGHT_GRAY_WOOL:
            case LIGHT_WEIGHTED_PRESSURE_PLATE:
            case LIME_BANNER:
            case LIME_BED:
            case LIME_CANDLE_CAKE:
            case LIME_CONCRETE:
            case LIME_CONCRETE_POWDER:
            case LIME_GLAZED_TERRACOTTA:
            case LIME_SHULKER_BOX:
            case LIME_STAINED_GLASS:
            case LIME_STAINED_GLASS_PANE:
            case LIME_TERRACOTTA:
            case LIME_WALL_BANNER:
            case LIME_WOOL:
            case LODESTONE:
            case LOOM:
            case MAGENTA_BANNER:
            case MAGENTA_BED:
            case MAGENTA_CANDLE_CAKE:
            case MAGENTA_CONCRETE:
            case MAGENTA_CONCRETE_POWDER:
            case MAGENTA_GLAZED_TERRACOTTA:
            case MAGENTA_SHULKER_BOX:
            case MAGENTA_STAINED_GLASS:
            case MAGENTA_STAINED_GLASS_PANE:
            case MAGENTA_TERRACOTTA:
            case MAGENTA_WALL_BANNER:
            case MAGENTA_WOOL:
            case MAGMA_BLOCK:
            case MANGROVE_DOOR:
            case MANGROVE_FENCE:
            case MANGROVE_FENCE_GATE:
            case MANGROVE_HANGING_SIGN:
            case MANGROVE_LEAVES:
            case MANGROVE_LOG:
            case MANGROVE_PLANKS:
            case MANGROVE_PRESSURE_PLATE:
            case MANGROVE_ROOTS:
            case MANGROVE_SIGN:
            case MANGROVE_SLAB:
            case MANGROVE_STAIRS:
            case MANGROVE_TRAPDOOR:
            case MANGROVE_WALL_HANGING_SIGN:
            case MANGROVE_WALL_SIGN:
            case MANGROVE_WOOD:
            case MEDIUM_AMETHYST_BUD:
            case MELON:
            case MOSSY_COBBLESTONE:
            case MOSSY_COBBLESTONE_SLAB:
            case MOSSY_COBBLESTONE_STAIRS:
            case MOSSY_COBBLESTONE_WALL:
            case MOSSY_STONE_BRICKS:
            case MOSSY_STONE_BRICK_SLAB:
            case MOSSY_STONE_BRICK_STAIRS:
            case MOSSY_STONE_BRICK_WALL:
            case MOSS_BLOCK:
            case MOVING_PISTON:
            case MUD:
            case MUDDY_MANGROVE_ROOTS:
            case MUD_BRICKS:
            case MUD_BRICK_SLAB:
            case MUD_BRICK_STAIRS:
            case MUD_BRICK_WALL:
            case MUSHROOM_STEM:
            case MYCELIUM:
            case NETHERITE_BLOCK:
            case NETHERRACK:
            case NETHER_BRICKS:
            case NETHER_BRICK_FENCE:
            case NETHER_BRICK_SLAB:
            case NETHER_BRICK_STAIRS:
            case NETHER_BRICK_WALL:
            case NETHER_GOLD_ORE:
            case NETHER_QUARTZ_ORE:
            case NETHER_WART_BLOCK:
            case NOTE_BLOCK:
            case OAK_DOOR:
            case OAK_FENCE:
            case OAK_FENCE_GATE:
            case OAK_HANGING_SIGN:
            case OAK_LEAVES:
            case OAK_LOG:
            case OAK_PLANKS:
            case OAK_PRESSURE_PLATE:
            case OAK_SIGN:
            case OAK_SLAB:
            case OAK_STAIRS:
            case OAK_TRAPDOOR:
            case OAK_WALL_HANGING_SIGN:
            case OAK_WALL_SIGN:
            case OAK_WOOD:
            case OBSERVER:
            case OBSIDIAN:
            case OCHRE_FROGLIGHT:
            case ORANGE_BANNER:
            case ORANGE_BED:
            case ORANGE_CANDLE_CAKE:
            case ORANGE_CONCRETE:
            case ORANGE_CONCRETE_POWDER:
            case ORANGE_GLAZED_TERRACOTTA:
            case ORANGE_SHULKER_BOX:
            case ORANGE_STAINED_GLASS:
            case ORANGE_STAINED_GLASS_PANE:
            case ORANGE_TERRACOTTA:
            case ORANGE_WALL_BANNER:
            case ORANGE_WOOL:
            case OXIDIZED_CHISELED_COPPER:
            case OXIDIZED_COPPER:
            case OXIDIZED_COPPER_BULB:
            case OXIDIZED_COPPER_DOOR:
            case OXIDIZED_COPPER_GRATE:
            case OXIDIZED_COPPER_TRAPDOOR:
            case OXIDIZED_CUT_COPPER:
            case OXIDIZED_CUT_COPPER_SLAB:
            case OXIDIZED_CUT_COPPER_STAIRS:
            case PACKED_ICE:
            case PACKED_MUD:
            case PEARLESCENT_FROGLIGHT:
            case PETRIFIED_OAK_SLAB:
            case PINK_BANNER:
            case PINK_BED:
            case PINK_CANDLE_CAKE:
            case PINK_CONCRETE:
            case PINK_CONCRETE_POWDER:
            case PINK_GLAZED_TERRACOTTA:
            case PINK_SHULKER_BOX:
            case PINK_STAINED_GLASS:
            case PINK_STAINED_GLASS_PANE:
            case PINK_TERRACOTTA:
            case PINK_WALL_BANNER:
            case PINK_WOOL:
            case PISTON:
            case PISTON_HEAD:
            case PODZOL:
            case POINTED_DRIPSTONE:
            case POLISHED_ANDESITE:
            case POLISHED_ANDESITE_SLAB:
            case POLISHED_ANDESITE_STAIRS:
            case POLISHED_BASALT:
            case POLISHED_BLACKSTONE:
            case POLISHED_BLACKSTONE_BRICKS:
            case POLISHED_BLACKSTONE_BRICK_SLAB:
            case POLISHED_BLACKSTONE_BRICK_STAIRS:
            case POLISHED_BLACKSTONE_BRICK_WALL:
            case POLISHED_BLACKSTONE_PRESSURE_PLATE:
            case POLISHED_BLACKSTONE_SLAB:
            case POLISHED_BLACKSTONE_STAIRS:
            case POLISHED_BLACKSTONE_WALL:
            case POLISHED_DEEPSLATE:
            case POLISHED_DEEPSLATE_SLAB:
            case POLISHED_DEEPSLATE_STAIRS:
            case POLISHED_DEEPSLATE_WALL:
            case POLISHED_DIORITE:
            case POLISHED_DIORITE_SLAB:
            case POLISHED_DIORITE_STAIRS:
            case POLISHED_GRANITE:
            case POLISHED_GRANITE_SLAB:
            case POLISHED_GRANITE_STAIRS:
            case POLISHED_TUFF:
            case POLISHED_TUFF_SLAB:
            case POLISHED_TUFF_STAIRS:
            case POLISHED_TUFF_WALL:
            case POWDER_SNOW_CAULDRON:
            case PRISMARINE:
            case PRISMARINE_BRICKS:
            case PRISMARINE_BRICK_SLAB:
            case PRISMARINE_BRICK_STAIRS:
            case PRISMARINE_SLAB:
            case PRISMARINE_STAIRS:
            case PRISMARINE_WALL:
            case PUMPKIN:
            case PURPLE_BANNER:
            case PURPLE_BED:
            case PURPLE_CANDLE_CAKE:
            case PURPLE_CONCRETE:
            case PURPLE_CONCRETE_POWDER:
            case PURPLE_GLAZED_TERRACOTTA:
            case PURPLE_SHULKER_BOX:
            case PURPLE_STAINED_GLASS:
            case PURPLE_STAINED_GLASS_PANE:
            case PURPLE_TERRACOTTA:
            case PURPLE_WALL_BANNER:
            case PURPLE_WOOL:
            case PURPUR_BLOCK:
            case PURPUR_PILLAR:
            case PURPUR_SLAB:
            case PURPUR_STAIRS:
            case QUARTZ_BLOCK:
            case QUARTZ_BRICKS:
            case QUARTZ_PILLAR:
            case QUARTZ_SLAB:
            case QUARTZ_STAIRS:
            case RAW_COPPER_BLOCK:
            case RAW_GOLD_BLOCK:
            case RAW_IRON_BLOCK:
            case REDSTONE_BLOCK:
            case REDSTONE_LAMP:
            case REDSTONE_ORE:
            case RED_BANNER:
            case RED_BED:
            case RED_CANDLE_CAKE:
            case RED_CONCRETE:
            case RED_CONCRETE_POWDER:
            case RED_GLAZED_TERRACOTTA:
            case RED_MUSHROOM_BLOCK:
            case RED_NETHER_BRICKS:
            case RED_NETHER_BRICK_SLAB:
            case RED_NETHER_BRICK_STAIRS:
            case RED_NETHER_BRICK_WALL:
            case RED_SAND:
            case RED_SANDSTONE:
            case RED_SANDSTONE_SLAB:
            case RED_SANDSTONE_STAIRS:
            case RED_SANDSTONE_WALL:
            case RED_SHULKER_BOX:
            case RED_STAINED_GLASS:
            case RED_STAINED_GLASS_PANE:
            case RED_TERRACOTTA:
            case RED_WALL_BANNER:
            case RED_WOOL:
            case REINFORCED_DEEPSLATE:
            case REPEATING_COMMAND_BLOCK:
            case RESPAWN_ANCHOR:
            case ROOTED_DIRT:
            case SAND:
            case SANDSTONE:
            case SANDSTONE_SLAB:
            case SANDSTONE_STAIRS:
            case SANDSTONE_WALL:
            case SCULK:
            case SCULK_CATALYST:
            case SCULK_SENSOR:
            case SCULK_SHRIEKER:
            case SCULK_VEIN:
            case SEA_LANTERN:
            case SHROOMLIGHT:
            case SHULKER_BOX:
            case SLIME_BLOCK:
            case SMALL_AMETHYST_BUD:
            case SMITHING_TABLE:
            case SMOKER:
            case SMOOTH_BASALT:
            case SMOOTH_QUARTZ:
            case SMOOTH_QUARTZ_SLAB:
            case SMOOTH_QUARTZ_STAIRS:
            case SMOOTH_RED_SANDSTONE:
            case SMOOTH_RED_SANDSTONE_SLAB:
            case SMOOTH_RED_SANDSTONE_STAIRS:
            case SMOOTH_SANDSTONE:
            case SMOOTH_SANDSTONE_SLAB:
            case SMOOTH_SANDSTONE_STAIRS:
            case SMOOTH_STONE:
            case SMOOTH_STONE_SLAB:
            case SNIFFER_EGG:
            case SNOW_BLOCK:
            case SOUL_CAMPFIRE:
            case SOUL_LANTERN:
            case SOUL_SAND:
            case SOUL_SOIL:
            case SPAWNER:
            case SPONGE:
            case SPRUCE_DOOR:
            case SPRUCE_FENCE:
            case SPRUCE_FENCE_GATE:
            case SPRUCE_HANGING_SIGN:
            case SPRUCE_LEAVES:
            case SPRUCE_LOG:
            case SPRUCE_PLANKS:
            case SPRUCE_PRESSURE_PLATE:
            case SPRUCE_SIGN:
            case SPRUCE_SLAB:
            case SPRUCE_STAIRS:
            case SPRUCE_TRAPDOOR:
            case SPRUCE_WALL_HANGING_SIGN:
            case SPRUCE_WALL_SIGN:
            case SPRUCE_WOOD:
            case STICKY_PISTON:
            case STONE:
            case STONECUTTER:
            case STONE_BRICKS:
            case STONE_BRICK_SLAB:
            case STONE_BRICK_STAIRS:
            case STONE_BRICK_WALL:
            case STONE_PRESSURE_PLATE:
            case STONE_SLAB:
            case STONE_STAIRS:
            case STRIPPED_ACACIA_LOG:
            case STRIPPED_ACACIA_WOOD:
            case STRIPPED_BAMBOO_BLOCK:
            case STRIPPED_BIRCH_LOG:
            case STRIPPED_BIRCH_WOOD:
            case STRIPPED_CHERRY_LOG:
            case STRIPPED_CHERRY_WOOD:
            case STRIPPED_CRIMSON_HYPHAE:
            case STRIPPED_CRIMSON_STEM:
            case STRIPPED_DARK_OAK_LOG:
            case STRIPPED_DARK_OAK_WOOD:
            case STRIPPED_JUNGLE_LOG:
            case STRIPPED_JUNGLE_WOOD:
            case STRIPPED_MANGROVE_LOG:
            case STRIPPED_MANGROVE_WOOD:
            case STRIPPED_OAK_LOG:
            case STRIPPED_OAK_WOOD:
            case STRIPPED_SPRUCE_LOG:
            case STRIPPED_SPRUCE_WOOD:
            case STRIPPED_WARPED_HYPHAE:
            case STRIPPED_WARPED_STEM:
            case STRUCTURE_BLOCK:
            case SUSPICIOUS_GRAVEL:
            case SUSPICIOUS_SAND:
            case TARGET:
            case TERRACOTTA:
            case TINTED_GLASS:
            case TNT:
            case TRAPPED_CHEST:
            case TRIAL_SPAWNER:
            case TUBE_CORAL_BLOCK:
            case TUFF:
            case TUFF_BRICKS:
            case TUFF_BRICK_SLAB:
            case TUFF_BRICK_STAIRS:
            case TUFF_BRICK_WALL:
            case TUFF_SLAB:
            case TUFF_STAIRS:
            case TUFF_WALL:
            case TURTLE_EGG:
            case VERDANT_FROGLIGHT:
            case WARPED_DOOR:
            case WARPED_FENCE:
            case WARPED_FENCE_GATE:
            case WARPED_HANGING_SIGN:
            case WARPED_HYPHAE:
            case WARPED_NYLIUM:
            case WARPED_PLANKS:
            case WARPED_PRESSURE_PLATE:
            case WARPED_SIGN:
            case WARPED_SLAB:
            case WARPED_STAIRS:
            case WARPED_STEM:
            case WARPED_TRAPDOOR:
            case WARPED_WALL_HANGING_SIGN:
            case WARPED_WALL_SIGN:
            case WARPED_WART_BLOCK:
            case WATER_CAULDRON:
            case WAXED_CHISELED_COPPER:
            case WAXED_COPPER_BLOCK:
            case WAXED_COPPER_BULB:
            case WAXED_COPPER_DOOR:
            case WAXED_COPPER_GRATE:
            case WAXED_COPPER_TRAPDOOR:
            case WAXED_CUT_COPPER:
            case WAXED_CUT_COPPER_SLAB:
            case WAXED_CUT_COPPER_STAIRS:
            case WAXED_EXPOSED_CHISELED_COPPER:
            case WAXED_EXPOSED_COPPER:
            case WAXED_EXPOSED_COPPER_BULB:
            case WAXED_EXPOSED_COPPER_DOOR:
            case WAXED_EXPOSED_COPPER_GRATE:
            case WAXED_EXPOSED_COPPER_TRAPDOOR:
            case WAXED_EXPOSED_CUT_COPPER:
            case WAXED_EXPOSED_CUT_COPPER_SLAB:
            case WAXED_EXPOSED_CUT_COPPER_STAIRS:
            case WAXED_OXIDIZED_CHISELED_COPPER:
            case WAXED_OXIDIZED_COPPER:
            case WAXED_OXIDIZED_COPPER_BULB:
            case WAXED_OXIDIZED_COPPER_DOOR:
            case WAXED_OXIDIZED_COPPER_GRATE:
            case WAXED_OXIDIZED_COPPER_TRAPDOOR:
            case WAXED_OXIDIZED_CUT_COPPER:
            case WAXED_OXIDIZED_CUT_COPPER_SLAB:
            case WAXED_OXIDIZED_CUT_COPPER_STAIRS:
            case WAXED_WEATHERED_CHISELED_COPPER:
            case WAXED_WEATHERED_COPPER:
            case WAXED_WEATHERED_COPPER_BULB:
            case WAXED_WEATHERED_COPPER_DOOR:
            case WAXED_WEATHERED_COPPER_GRATE:
            case WAXED_WEATHERED_COPPER_TRAPDOOR:
            case WAXED_WEATHERED_CUT_COPPER:
            case WAXED_WEATHERED_CUT_COPPER_SLAB:
            case WAXED_WEATHERED_CUT_COPPER_STAIRS:
            case WEATHERED_CHISELED_COPPER:
            case WEATHERED_COPPER:
            case WEATHERED_COPPER_BULB:
            case WEATHERED_COPPER_DOOR:
            case WEATHERED_COPPER_GRATE:
            case WEATHERED_COPPER_TRAPDOOR:
            case WEATHERED_CUT_COPPER:
            case WEATHERED_CUT_COPPER_SLAB:
            case WEATHERED_CUT_COPPER_STAIRS:
            case WET_SPONGE:
            case WHITE_BANNER:
            case WHITE_BED:
            case WHITE_CANDLE_CAKE:
            case WHITE_CONCRETE:
            case WHITE_CONCRETE_POWDER:
            case WHITE_GLAZED_TERRACOTTA:
            case WHITE_SHULKER_BOX:
            case WHITE_STAINED_GLASS:
            case WHITE_STAINED_GLASS_PANE:
            case WHITE_TERRACOTTA:
            case WHITE_WALL_BANNER:
            case WHITE_WOOL:
            case YELLOW_BANNER:
            case YELLOW_BED:
            case YELLOW_CANDLE_CAKE:
            case YELLOW_CONCRETE:
            case YELLOW_CONCRETE_POWDER:
            case YELLOW_GLAZED_TERRACOTTA:
            case YELLOW_SHULKER_BOX:
            case YELLOW_STAINED_GLASS:
            case YELLOW_STAINED_GLASS_PANE:
            case YELLOW_TERRACOTTA:
            case YELLOW_WALL_BANNER:
            case YELLOW_WOOL:
            // Paper end - Generated/Material#isSolid
            // ----- Legacy Separator -----
            case LEGACY_STONE:
            case LEGACY_GRASS:
            case LEGACY_DIRT:
            case LEGACY_COBBLESTONE:
            case LEGACY_WOOD:
            case LEGACY_BEDROCK:
            case LEGACY_SAND:
            case LEGACY_GRAVEL:
            case LEGACY_GOLD_ORE:
            case LEGACY_IRON_ORE:
            case LEGACY_COAL_ORE:
            case LEGACY_LOG:
            case LEGACY_LEAVES:
            case LEGACY_SPONGE:
            case LEGACY_GLASS:
            case LEGACY_LAPIS_ORE:
            case LEGACY_LAPIS_BLOCK:
            case LEGACY_DISPENSER:
            case LEGACY_SANDSTONE:
            case LEGACY_NOTE_BLOCK:
            case LEGACY_BED_BLOCK:
            case LEGACY_PISTON_STICKY_BASE:
            case LEGACY_PISTON_BASE:
            case LEGACY_PISTON_EXTENSION:
            case LEGACY_WOOL:
            case LEGACY_PISTON_MOVING_PIECE:
            case LEGACY_GOLD_BLOCK:
            case LEGACY_IRON_BLOCK:
            case LEGACY_DOUBLE_STEP:
            case LEGACY_STEP:
            case LEGACY_BRICK:
            case LEGACY_TNT:
            case LEGACY_BOOKSHELF:
            case LEGACY_MOSSY_COBBLESTONE:
            case LEGACY_OBSIDIAN:
            case LEGACY_MOB_SPAWNER:
            case LEGACY_WOOD_STAIRS:
            case LEGACY_CHEST:
            case LEGACY_DIAMOND_ORE:
            case LEGACY_DIAMOND_BLOCK:
            case LEGACY_WORKBENCH:
            case LEGACY_SOIL:
            case LEGACY_FURNACE:
            case LEGACY_BURNING_FURNACE:
            case LEGACY_SIGN_POST:
            case LEGACY_WOODEN_DOOR:
            case LEGACY_COBBLESTONE_STAIRS:
            case LEGACY_WALL_SIGN:
            case LEGACY_STONE_PLATE:
            case LEGACY_IRON_DOOR_BLOCK:
            case LEGACY_WOOD_PLATE:
            case LEGACY_REDSTONE_ORE:
            case LEGACY_GLOWING_REDSTONE_ORE:
            case LEGACY_ICE:
            case LEGACY_SNOW_BLOCK:
            case LEGACY_CACTUS:
            case LEGACY_CLAY:
            case LEGACY_JUKEBOX:
            case LEGACY_FENCE:
            case LEGACY_PUMPKIN:
            case LEGACY_NETHERRACK:
            case LEGACY_SOUL_SAND:
            case LEGACY_GLOWSTONE:
            case LEGACY_JACK_O_LANTERN:
            case LEGACY_CAKE_BLOCK:
            case LEGACY_STAINED_GLASS:
            case LEGACY_TRAP_DOOR:
            case LEGACY_MONSTER_EGGS:
            case LEGACY_SMOOTH_BRICK:
            case LEGACY_HUGE_MUSHROOM_1:
            case LEGACY_HUGE_MUSHROOM_2:
            case LEGACY_IRON_FENCE:
            case LEGACY_THIN_GLASS:
            case LEGACY_MELON_BLOCK:
            case LEGACY_FENCE_GATE:
            case LEGACY_BRICK_STAIRS:
            case LEGACY_SMOOTH_STAIRS:
            case LEGACY_MYCEL:
            case LEGACY_NETHER_BRICK:
            case LEGACY_NETHER_FENCE:
            case LEGACY_NETHER_BRICK_STAIRS:
            case LEGACY_ENCHANTMENT_TABLE:
            case LEGACY_BREWING_STAND:
            case LEGACY_CAULDRON:
            case LEGACY_ENDER_PORTAL_FRAME:
            case LEGACY_ENDER_STONE:
            case LEGACY_DRAGON_EGG:
            case LEGACY_REDSTONE_LAMP_OFF:
            case LEGACY_REDSTONE_LAMP_ON:
            case LEGACY_WOOD_DOUBLE_STEP:
            case LEGACY_WOOD_STEP:
            case LEGACY_SANDSTONE_STAIRS:
            case LEGACY_EMERALD_ORE:
            case LEGACY_ENDER_CHEST:
            case LEGACY_EMERALD_BLOCK:
            case LEGACY_SPRUCE_WOOD_STAIRS:
            case LEGACY_BIRCH_WOOD_STAIRS:
            case LEGACY_JUNGLE_WOOD_STAIRS:
            case LEGACY_COMMAND:
            case LEGACY_BEACON:
            case LEGACY_COBBLE_WALL:
            case LEGACY_ANVIL:
            case LEGACY_TRAPPED_CHEST:
            case LEGACY_GOLD_PLATE:
            case LEGACY_IRON_PLATE:
            case LEGACY_DAYLIGHT_DETECTOR:
            case LEGACY_REDSTONE_BLOCK:
            case LEGACY_QUARTZ_ORE:
            case LEGACY_HOPPER:
            case LEGACY_QUARTZ_BLOCK:
            case LEGACY_QUARTZ_STAIRS:
            case LEGACY_DROPPER:
            case LEGACY_STAINED_CLAY:
            case LEGACY_HAY_BLOCK:
            case LEGACY_HARD_CLAY:
            case LEGACY_COAL_BLOCK:
            case LEGACY_STAINED_GLASS_PANE:
            case LEGACY_LEAVES_2:
            case LEGACY_LOG_2:
            case LEGACY_ACACIA_STAIRS:
            case LEGACY_DARK_OAK_STAIRS:
            case LEGACY_PACKED_ICE:
            case LEGACY_RED_SANDSTONE:
            case LEGACY_SLIME_BLOCK:
            case LEGACY_BARRIER:
            case LEGACY_IRON_TRAPDOOR:
            case LEGACY_PRISMARINE:
            case LEGACY_SEA_LANTERN:
            case LEGACY_DOUBLE_STONE_SLAB2:
            case LEGACY_RED_SANDSTONE_STAIRS:
            case LEGACY_STONE_SLAB2:
            case LEGACY_SPRUCE_FENCE_GATE:
            case LEGACY_BIRCH_FENCE_GATE:
            case LEGACY_JUNGLE_FENCE_GATE:
            case LEGACY_DARK_OAK_FENCE_GATE:
            case LEGACY_ACACIA_FENCE_GATE:
            case LEGACY_SPRUCE_FENCE:
            case LEGACY_BIRCH_FENCE:
            case LEGACY_JUNGLE_FENCE:
            case LEGACY_DARK_OAK_FENCE:
            case LEGACY_ACACIA_FENCE:
            case LEGACY_STANDING_BANNER:
            case LEGACY_WALL_BANNER:
            case LEGACY_DAYLIGHT_DETECTOR_INVERTED:
            case LEGACY_SPRUCE_DOOR:
            case LEGACY_BIRCH_DOOR:
            case LEGACY_JUNGLE_DOOR:
            case LEGACY_ACACIA_DOOR:
            case LEGACY_DARK_OAK_DOOR:
            case LEGACY_PURPUR_BLOCK:
            case LEGACY_PURPUR_PILLAR:
            case LEGACY_PURPUR_STAIRS:
            case LEGACY_PURPUR_DOUBLE_SLAB:
            case LEGACY_PURPUR_SLAB:
            case LEGACY_END_BRICKS:
            case LEGACY_GRASS_PATH:
            case LEGACY_STRUCTURE_BLOCK:
            case LEGACY_COMMAND_REPEATING:
            case LEGACY_COMMAND_CHAIN:
            case LEGACY_FROSTED_ICE:
            case LEGACY_MAGMA:
            case LEGACY_NETHER_WART_BLOCK:
            case LEGACY_RED_NETHER_BRICK:
            case LEGACY_BONE_BLOCK:
            case LEGACY_OBSERVER:
            case LEGACY_WHITE_SHULKER_BOX:
            case LEGACY_ORANGE_SHULKER_BOX:
            case LEGACY_MAGENTA_SHULKER_BOX:
            case LEGACY_LIGHT_BLUE_SHULKER_BOX:
            case LEGACY_YELLOW_SHULKER_BOX:
            case LEGACY_LIME_SHULKER_BOX:
            case LEGACY_PINK_SHULKER_BOX:
            case LEGACY_GRAY_SHULKER_BOX:
            case LEGACY_SILVER_SHULKER_BOX:
            case LEGACY_CYAN_SHULKER_BOX:
            case LEGACY_PURPLE_SHULKER_BOX:
            case LEGACY_BLUE_SHULKER_BOX:
            case LEGACY_BROWN_SHULKER_BOX:
            case LEGACY_GREEN_SHULKER_BOX:
            case LEGACY_RED_SHULKER_BOX:
            case LEGACY_BLACK_SHULKER_BOX:
            case LEGACY_WHITE_GLAZED_TERRACOTTA:
            case LEGACY_ORANGE_GLAZED_TERRACOTTA:
            case LEGACY_MAGENTA_GLAZED_TERRACOTTA:
            case LEGACY_LIGHT_BLUE_GLAZED_TERRACOTTA:
            case LEGACY_YELLOW_GLAZED_TERRACOTTA:
            case LEGACY_LIME_GLAZED_TERRACOTTA:
            case LEGACY_PINK_GLAZED_TERRACOTTA:
            case LEGACY_GRAY_GLAZED_TERRACOTTA:
            case LEGACY_SILVER_GLAZED_TERRACOTTA:
            case LEGACY_CYAN_GLAZED_TERRACOTTA:
            case LEGACY_PURPLE_GLAZED_TERRACOTTA:
            case LEGACY_BLUE_GLAZED_TERRACOTTA:
            case LEGACY_BROWN_GLAZED_TERRACOTTA:
            case LEGACY_GREEN_GLAZED_TERRACOTTA:
            case LEGACY_RED_GLAZED_TERRACOTTA:
            case LEGACY_BLACK_GLAZED_TERRACOTTA:
            case LEGACY_CONCRETE:
            case LEGACY_CONCRETE_POWDER:
            //</editor-fold>
                return true;
            default:
                return false;
        }
    }

    /**
     * Check if the material is an air block.
     *
     * @return True if this material is an air block.
     */
    public boolean isAir() {
        switch (this) {
            //<editor-fold defaultstate="collapsed" desc="isAir">
            // Paper start - Generated/Material#isAir
            // @GeneratedFrom 1.20.4
            case AIR:
            case CAVE_AIR:
            case VOID_AIR:
            // Paper end - Generated/Material#isAir
            // ----- Legacy Separator -----
            case LEGACY_AIR:
                //</editor-fold>
                return true;
            default:
                return false;
        }
    }

    /**
     * Check if the material is a block and does not block any light
     *
     * @return True if this material is a block and does not block any light
     * @deprecated currently does not have an implementation which is well
     * linked to the underlying server. Contributions welcome.
     */
    @Deprecated
    public boolean isTransparent() {
        if (!isBlock()) {
            return false;
        }
        switch (this) {
            //<editor-fold defaultstate="collapsed" desc="isTransparent">
            // Paper start - Generated/Material#isTransparent
            case ACACIA_BUTTON:
            case ACACIA_SAPLING:
            case ACTIVATOR_RAIL:
            case AIR:
            case ALLIUM:
            case ATTACHED_MELON_STEM:
            case ATTACHED_PUMPKIN_STEM:
            case AZURE_BLUET:
            case BARRIER:
            case BEETROOTS:
            case BIRCH_BUTTON:
            case BIRCH_SAPLING:
            case BLACK_CARPET:
            case BLUE_CARPET:
            case BLUE_ORCHID:
            case BROWN_CARPET:
            case BROWN_MUSHROOM:
            case CARROTS:
            case CAVE_AIR:
            case CHORUS_FLOWER:
            case CHORUS_PLANT:
            case COCOA:
            case COMPARATOR:
            case CREEPER_HEAD:
            case CREEPER_WALL_HEAD:
            case CYAN_CARPET:
            case DANDELION:
            case DARK_OAK_BUTTON:
            case DARK_OAK_SAPLING:
            case DEAD_BUSH:
            case DETECTOR_RAIL:
            case DRAGON_HEAD:
            case DRAGON_WALL_HEAD:
            case END_GATEWAY:
            case END_PORTAL:
            case END_ROD:
            case FERN:
            case FIRE:
            case FLOWER_POT:
            case GRAY_CARPET:
            case GREEN_CARPET:
            case JUNGLE_BUTTON:
            case JUNGLE_SAPLING:
            case LADDER:
            case LARGE_FERN:
            case LEVER:
            case LIGHT_BLUE_CARPET:
            case LIGHT_GRAY_CARPET:
            case LILAC:
            case LILY_PAD:
            case LIME_CARPET:
            case MAGENTA_CARPET:
            case MELON_STEM:
            case NETHER_PORTAL:
            case NETHER_WART:
            case OAK_BUTTON:
            case OAK_SAPLING:
            case ORANGE_CARPET:
            case ORANGE_TULIP:
            case OXEYE_DAISY:
            case PEONY:
            case PINK_CARPET:
            case PINK_TULIP:
            case PLAYER_HEAD:
            case PLAYER_WALL_HEAD:
            case POPPY:
            case POTATOES:
            case POTTED_ACACIA_SAPLING:
            case POTTED_ALLIUM:
            case POTTED_AZALEA_BUSH:
            case POTTED_AZURE_BLUET:
            case POTTED_BIRCH_SAPLING:
            case POTTED_BLUE_ORCHID:
            case POTTED_BROWN_MUSHROOM:
            case POTTED_CACTUS:
            case POTTED_DANDELION:
            case POTTED_DARK_OAK_SAPLING:
            case POTTED_DEAD_BUSH:
            case POTTED_FERN:
            case POTTED_FLOWERING_AZALEA_BUSH:
            case POTTED_JUNGLE_SAPLING:
            case POTTED_OAK_SAPLING:
            case POTTED_ORANGE_TULIP:
            case POTTED_OXEYE_DAISY:
            case POTTED_PINK_TULIP:
            case POTTED_POPPY:
            case POTTED_RED_MUSHROOM:
            case POTTED_RED_TULIP:
            case POTTED_SPRUCE_SAPLING:
            case POTTED_WHITE_TULIP:
            case POWERED_RAIL:
            case PUMPKIN_STEM:
            case PURPLE_CARPET:
            case RAIL:
            case REDSTONE_TORCH:
            case REDSTONE_WALL_TORCH:
            case REDSTONE_WIRE:
            case RED_CARPET:
            case RED_MUSHROOM:
            case RED_TULIP:
            case REPEATER:
            case ROSE_BUSH:
            case SHORT_GRASS:
            case SKELETON_SKULL:
            case SKELETON_WALL_SKULL:
            case SNOW:
            case SPRUCE_BUTTON:
            case SPRUCE_SAPLING:
            case STONE_BUTTON:
            case STRUCTURE_VOID:
            case SUGAR_CANE:
            case SUNFLOWER:
            case TALL_GRASS:
            case TORCH:
            case TRIPWIRE:
            case TRIPWIRE_HOOK:
            case VINE:
            case VOID_AIR:
            case WALL_TORCH:
            case WHEAT:
            case WHITE_CARPET:
            case WHITE_TULIP:
            case WITHER_SKELETON_SKULL:
            case WITHER_SKELETON_WALL_SKULL:
            case YELLOW_CARPET:
            case ZOMBIE_HEAD:
            case ZOMBIE_WALL_HEAD:
            // Paper end - Generated/Material#isTransparent
            // ----- Legacy Separator -----
            case LEGACY_AIR:
            case LEGACY_SAPLING:
            case LEGACY_POWERED_RAIL:
            case LEGACY_DETECTOR_RAIL:
            case LEGACY_LONG_GRASS:
            case LEGACY_DEAD_BUSH:
            case LEGACY_YELLOW_FLOWER:
            case LEGACY_RED_ROSE:
            case LEGACY_BROWN_MUSHROOM:
            case LEGACY_RED_MUSHROOM:
            case LEGACY_TORCH:
            case LEGACY_FIRE:
            case LEGACY_REDSTONE_WIRE:
            case LEGACY_CROPS:
            case LEGACY_LADDER:
            case LEGACY_RAILS:
            case LEGACY_LEVER:
            case LEGACY_REDSTONE_TORCH_OFF:
            case LEGACY_REDSTONE_TORCH_ON:
            case LEGACY_STONE_BUTTON:
            case LEGACY_SNOW:
            case LEGACY_SUGAR_CANE_BLOCK:
            case LEGACY_PORTAL:
            case LEGACY_DIODE_BLOCK_OFF:
            case LEGACY_DIODE_BLOCK_ON:
            case LEGACY_PUMPKIN_STEM:
            case LEGACY_MELON_STEM:
            case LEGACY_VINE:
            case LEGACY_WATER_LILY:
            case LEGACY_NETHER_WARTS:
            case LEGACY_ENDER_PORTAL:
            case LEGACY_COCOA:
            case LEGACY_TRIPWIRE_HOOK:
            case LEGACY_TRIPWIRE:
            case LEGACY_FLOWER_POT:
            case LEGACY_CARROT:
            case LEGACY_POTATO:
            case LEGACY_WOOD_BUTTON:
            case LEGACY_SKULL:
            case LEGACY_REDSTONE_COMPARATOR_OFF:
            case LEGACY_REDSTONE_COMPARATOR_ON:
            case LEGACY_ACTIVATOR_RAIL:
            case LEGACY_CARPET:
            case LEGACY_DOUBLE_PLANT:
            case LEGACY_END_ROD:
            case LEGACY_CHORUS_PLANT:
            case LEGACY_CHORUS_FLOWER:
            case LEGACY_BEETROOT_BLOCK:
            case LEGACY_END_GATEWAY:
            case LEGACY_STRUCTURE_VOID:
            //</editor-fold>
                return true;
            default:
                return false;
        }
    }

    /**
     * Check if the material is a block and can catch fire
     *
     * @return True if this material is a block and can catch fire
     */
    public boolean isFlammable() {
        if (!isBlock()) {
            return false;
        }
        switch (this) {
            //<editor-fold defaultstate="collapsed" desc="isFlammable">
            // Paper start - Generated/Material#isFlammable
            // @GeneratedFrom 1.20.4
            case ACACIA_DOOR:
            case ACACIA_FENCE:
            case ACACIA_FENCE_GATE:
            case ACACIA_HANGING_SIGN:
            case ACACIA_LEAVES:
            case ACACIA_LOG:
            case ACACIA_PLANKS:
            case ACACIA_PRESSURE_PLATE:
            case ACACIA_SIGN:
            case ACACIA_SLAB:
            case ACACIA_STAIRS:
            case ACACIA_TRAPDOOR:
            case ACACIA_WALL_HANGING_SIGN:
            case ACACIA_WALL_SIGN:
            case ACACIA_WOOD:
            case AZALEA_LEAVES:
            case BAMBOO:
            case BAMBOO_BLOCK:
            case BAMBOO_DOOR:
            case BAMBOO_FENCE:
            case BAMBOO_FENCE_GATE:
            case BAMBOO_HANGING_SIGN:
            case BAMBOO_MOSAIC:
            case BAMBOO_MOSAIC_SLAB:
            case BAMBOO_MOSAIC_STAIRS:
            case BAMBOO_PLANKS:
            case BAMBOO_PRESSURE_PLATE:
            case BAMBOO_SAPLING:
            case BAMBOO_SIGN:
            case BAMBOO_SLAB:
            case BAMBOO_STAIRS:
            case BAMBOO_TRAPDOOR:
            case BAMBOO_WALL_HANGING_SIGN:
            case BAMBOO_WALL_SIGN:
            case BARREL:
            case BEEHIVE:
            case BEE_NEST:
            case BIRCH_DOOR:
            case BIRCH_FENCE:
            case BIRCH_FENCE_GATE:
            case BIRCH_HANGING_SIGN:
            case BIRCH_LEAVES:
            case BIRCH_LOG:
            case BIRCH_PLANKS:
            case BIRCH_PRESSURE_PLATE:
            case BIRCH_SIGN:
            case BIRCH_SLAB:
            case BIRCH_STAIRS:
            case BIRCH_TRAPDOOR:
            case BIRCH_WALL_HANGING_SIGN:
            case BIRCH_WALL_SIGN:
            case BIRCH_WOOD:
            case BLACK_BANNER:
            case BLACK_BED:
            case BLACK_CARPET:
            case BLACK_WALL_BANNER:
            case BLACK_WOOL:
            case BLUE_BANNER:
            case BLUE_BED:
            case BLUE_CARPET:
            case BLUE_WALL_BANNER:
            case BLUE_WOOL:
            case BOOKSHELF:
            case BROWN_BANNER:
            case BROWN_BED:
            case BROWN_CARPET:
            case BROWN_MUSHROOM_BLOCK:
            case BROWN_WALL_BANNER:
            case BROWN_WOOL:
            case CAMPFIRE:
            case CARTOGRAPHY_TABLE:
            case CHERRY_DOOR:
            case CHERRY_FENCE:
            case CHERRY_FENCE_GATE:
            case CHERRY_HANGING_SIGN:
            case CHERRY_LEAVES:
            case CHERRY_LOG:
            case CHERRY_PLANKS:
            case CHERRY_PRESSURE_PLATE:
            case CHERRY_SIGN:
            case CHERRY_SLAB:
            case CHERRY_STAIRS:
            case CHERRY_TRAPDOOR:
            case CHERRY_WALL_HANGING_SIGN:
            case CHERRY_WALL_SIGN:
            case CHERRY_WOOD:
            case CHEST:
            case CHISELED_BOOKSHELF:
            case COMPOSTER:
            case CRAFTING_TABLE:
            case CYAN_BANNER:
            case CYAN_BED:
            case CYAN_CARPET:
            case CYAN_WALL_BANNER:
            case CYAN_WOOL:
            case DARK_OAK_DOOR:
            case DARK_OAK_FENCE:
            case DARK_OAK_FENCE_GATE:
            case DARK_OAK_HANGING_SIGN:
            case DARK_OAK_LEAVES:
            case DARK_OAK_LOG:
            case DARK_OAK_PLANKS:
            case DARK_OAK_PRESSURE_PLATE:
            case DARK_OAK_SIGN:
            case DARK_OAK_SLAB:
            case DARK_OAK_STAIRS:
            case DARK_OAK_TRAPDOOR:
            case DARK_OAK_WALL_HANGING_SIGN:
            case DARK_OAK_WALL_SIGN:
            case DARK_OAK_WOOD:
            case DAYLIGHT_DETECTOR:
            case DEAD_BUSH:
            case FERN:
            case FLETCHING_TABLE:
            case FLOWERING_AZALEA_LEAVES:
            case GLOW_LICHEN:
            case GRAY_BANNER:
            case GRAY_BED:
            case GRAY_CARPET:
            case GRAY_WALL_BANNER:
            case GRAY_WOOL:
            case GREEN_BANNER:
            case GREEN_BED:
            case GREEN_CARPET:
            case GREEN_WALL_BANNER:
            case GREEN_WOOL:
            case HANGING_ROOTS:
            case JUKEBOX:
            case JUNGLE_DOOR:
            case JUNGLE_FENCE:
            case JUNGLE_FENCE_GATE:
            case JUNGLE_HANGING_SIGN:
            case JUNGLE_LEAVES:
            case JUNGLE_LOG:
            case JUNGLE_PLANKS:
            case JUNGLE_PRESSURE_PLATE:
            case JUNGLE_SIGN:
            case JUNGLE_SLAB:
            case JUNGLE_STAIRS:
            case JUNGLE_TRAPDOOR:
            case JUNGLE_WALL_HANGING_SIGN:
            case JUNGLE_WALL_SIGN:
            case JUNGLE_WOOD:
            case LARGE_FERN:
            case LECTERN:
            case LIGHT_BLUE_BANNER:
            case LIGHT_BLUE_BED:
            case LIGHT_BLUE_CARPET:
            case LIGHT_BLUE_WALL_BANNER:
            case LIGHT_BLUE_WOOL:
            case LIGHT_GRAY_BANNER:
            case LIGHT_GRAY_BED:
            case LIGHT_GRAY_CARPET:
            case LIGHT_GRAY_WALL_BANNER:
            case LIGHT_GRAY_WOOL:
            case LILAC:
            case LIME_BANNER:
            case LIME_BED:
            case LIME_CARPET:
            case LIME_WALL_BANNER:
            case LIME_WOOL:
            case LOOM:
            case MAGENTA_BANNER:
            case MAGENTA_BED:
            case MAGENTA_CARPET:
            case MAGENTA_WALL_BANNER:
            case MAGENTA_WOOL:
            case MANGROVE_DOOR:
            case MANGROVE_FENCE:
            case MANGROVE_FENCE_GATE:
            case MANGROVE_HANGING_SIGN:
            case MANGROVE_LEAVES:
            case MANGROVE_LOG:
            case MANGROVE_PLANKS:
            case MANGROVE_PRESSURE_PLATE:
            case MANGROVE_ROOTS:
            case MANGROVE_SIGN:
            case MANGROVE_SLAB:
            case MANGROVE_STAIRS:
            case MANGROVE_TRAPDOOR:
            case MANGROVE_WALL_HANGING_SIGN:
            case MANGROVE_WALL_SIGN:
            case MANGROVE_WOOD:
            case MUSHROOM_STEM:
            case NOTE_BLOCK:
            case OAK_DOOR:
            case OAK_FENCE:
            case OAK_FENCE_GATE:
            case OAK_HANGING_SIGN:
            case OAK_LEAVES:
            case OAK_LOG:
            case OAK_PLANKS:
            case OAK_PRESSURE_PLATE:
            case OAK_SIGN:
            case OAK_SLAB:
            case OAK_STAIRS:
            case OAK_TRAPDOOR:
            case OAK_WALL_HANGING_SIGN:
            case OAK_WALL_SIGN:
            case OAK_WOOD:
            case ORANGE_BANNER:
            case ORANGE_BED:
            case ORANGE_CARPET:
            case ORANGE_WALL_BANNER:
            case ORANGE_WOOL:
            case PEONY:
            case PINK_BANNER:
            case PINK_BED:
            case PINK_CARPET:
            case PINK_WALL_BANNER:
            case PINK_WOOL:
            case PITCHER_PLANT:
            case PURPLE_BANNER:
            case PURPLE_BED:
            case PURPLE_CARPET:
            case PURPLE_WALL_BANNER:
            case PURPLE_WOOL:
            case RED_BANNER:
            case RED_BED:
            case RED_CARPET:
            case RED_MUSHROOM_BLOCK:
            case RED_WALL_BANNER:
            case RED_WOOL:
            case ROSE_BUSH:
            case SHORT_GRASS:
            case SMITHING_TABLE:
            case SOUL_CAMPFIRE:
            case SPRUCE_DOOR:
            case SPRUCE_FENCE:
            case SPRUCE_FENCE_GATE:
            case SPRUCE_HANGING_SIGN:
            case SPRUCE_LEAVES:
            case SPRUCE_LOG:
            case SPRUCE_PLANKS:
            case SPRUCE_PRESSURE_PLATE:
            case SPRUCE_SIGN:
            case SPRUCE_SLAB:
            case SPRUCE_STAIRS:
            case SPRUCE_TRAPDOOR:
            case SPRUCE_WALL_HANGING_SIGN:
            case SPRUCE_WALL_SIGN:
            case SPRUCE_WOOD:
            case STRIPPED_ACACIA_LOG:
            case STRIPPED_ACACIA_WOOD:
            case STRIPPED_BAMBOO_BLOCK:
            case STRIPPED_BIRCH_LOG:
            case STRIPPED_BIRCH_WOOD:
            case STRIPPED_CHERRY_LOG:
            case STRIPPED_CHERRY_WOOD:
            case STRIPPED_DARK_OAK_LOG:
            case STRIPPED_DARK_OAK_WOOD:
            case STRIPPED_JUNGLE_LOG:
            case STRIPPED_JUNGLE_WOOD:
            case STRIPPED_MANGROVE_LOG:
            case STRIPPED_MANGROVE_WOOD:
            case STRIPPED_OAK_LOG:
            case STRIPPED_OAK_WOOD:
            case STRIPPED_SPRUCE_LOG:
            case STRIPPED_SPRUCE_WOOD:
            case SUNFLOWER:
            case TALL_GRASS:
            case TNT:
            case TRAPPED_CHEST:
            case VINE:
            case WHITE_BANNER:
            case WHITE_BED:
            case WHITE_CARPET:
            case WHITE_WALL_BANNER:
            case WHITE_WOOL:
            case YELLOW_BANNER:
            case YELLOW_BED:
            case YELLOW_CARPET:
            case YELLOW_WALL_BANNER:
            case YELLOW_WOOL:
            // Paper end - Generated/Material#isFlammable
            // ----- Legacy Separator -----
            case LEGACY_WOOD:
            case LEGACY_LOG:
            case LEGACY_LEAVES:
            case LEGACY_NOTE_BLOCK:
            case LEGACY_BED_BLOCK:
            case LEGACY_LONG_GRASS:
            case LEGACY_DEAD_BUSH:
            case LEGACY_WOOL:
            case LEGACY_TNT:
            case LEGACY_BOOKSHELF:
            case LEGACY_WOOD_STAIRS:
            case LEGACY_CHEST:
            case LEGACY_WORKBENCH:
            case LEGACY_SIGN_POST:
            case LEGACY_WOODEN_DOOR:
            case LEGACY_WALL_SIGN:
            case LEGACY_WOOD_PLATE:
            case LEGACY_JUKEBOX:
            case LEGACY_FENCE:
            case LEGACY_TRAP_DOOR:
            case LEGACY_HUGE_MUSHROOM_1:
            case LEGACY_HUGE_MUSHROOM_2:
            case LEGACY_VINE:
            case LEGACY_FENCE_GATE:
            case LEGACY_WOOD_DOUBLE_STEP:
            case LEGACY_WOOD_STEP:
            case LEGACY_SPRUCE_WOOD_STAIRS:
            case LEGACY_BIRCH_WOOD_STAIRS:
            case LEGACY_JUNGLE_WOOD_STAIRS:
            case LEGACY_TRAPPED_CHEST:
            case LEGACY_DAYLIGHT_DETECTOR:
            case LEGACY_CARPET:
            case LEGACY_LEAVES_2:
            case LEGACY_LOG_2:
            case LEGACY_ACACIA_STAIRS:
            case LEGACY_DARK_OAK_STAIRS:
            case LEGACY_DOUBLE_PLANT:
            case LEGACY_SPRUCE_FENCE_GATE:
            case LEGACY_BIRCH_FENCE_GATE:
            case LEGACY_JUNGLE_FENCE_GATE:
            case LEGACY_DARK_OAK_FENCE_GATE:
            case LEGACY_ACACIA_FENCE_GATE:
            case LEGACY_SPRUCE_FENCE:
            case LEGACY_BIRCH_FENCE:
            case LEGACY_JUNGLE_FENCE:
            case LEGACY_DARK_OAK_FENCE:
            case LEGACY_ACACIA_FENCE:
            case LEGACY_STANDING_BANNER:
            case LEGACY_WALL_BANNER:
            case LEGACY_DAYLIGHT_DETECTOR_INVERTED:
            case LEGACY_SPRUCE_DOOR:
            case LEGACY_BIRCH_DOOR:
            case LEGACY_JUNGLE_DOOR:
            case LEGACY_ACACIA_DOOR:
            case LEGACY_DARK_OAK_DOOR:
            //</editor-fold>
                return true;
            default:
                return false;
        }
    }

    /**
     * Check if the material is a block and can burn away
     *
     * @return True if this material is a block and can burn away
     */
    public boolean isBurnable() {
        if (!isBlock()) {
            return false;
        }
        switch (this) {
            //<editor-fold defaultstate="collapsed" desc="isBurnable">
            // Paper start - Generated/Material#isBurnable
            // @GeneratedFrom 1.20.4
            case ACACIA_FENCE:
            case ACACIA_FENCE_GATE:
            case ACACIA_LEAVES:
            case ACACIA_LOG:
            case ACACIA_PLANKS:
            case ACACIA_SLAB:
            case ACACIA_STAIRS:
            case ACACIA_WOOD:
            case ALLIUM:
            case AZALEA:
            case AZALEA_LEAVES:
            case AZURE_BLUET:
            case BAMBOO:
            case BAMBOO_BLOCK:
            case BAMBOO_FENCE:
            case BAMBOO_FENCE_GATE:
            case BAMBOO_MOSAIC:
            case BAMBOO_MOSAIC_SLAB:
            case BAMBOO_MOSAIC_STAIRS:
            case BAMBOO_PLANKS:
            case BAMBOO_SLAB:
            case BAMBOO_STAIRS:
            case BEEHIVE:
            case BEE_NEST:
            case BIG_DRIPLEAF:
            case BIG_DRIPLEAF_STEM:
            case BIRCH_FENCE:
            case BIRCH_FENCE_GATE:
            case BIRCH_LEAVES:
            case BIRCH_LOG:
            case BIRCH_PLANKS:
            case BIRCH_SLAB:
            case BIRCH_STAIRS:
            case BIRCH_WOOD:
            case BLACK_CARPET:
            case BLACK_WOOL:
            case BLUE_CARPET:
            case BLUE_ORCHID:
            case BLUE_WOOL:
            case BOOKSHELF:
            case BROWN_CARPET:
            case BROWN_WOOL:
            case CAVE_VINES:
            case CAVE_VINES_PLANT:
            case CHERRY_FENCE:
            case CHERRY_FENCE_GATE:
            case CHERRY_LEAVES:
            case CHERRY_LOG:
            case CHERRY_PLANKS:
            case CHERRY_SLAB:
            case CHERRY_STAIRS:
            case CHERRY_WOOD:
            case COAL_BLOCK:
            case COMPOSTER:
            case CORNFLOWER:
            case CYAN_CARPET:
            case CYAN_WOOL:
            case DANDELION:
            case DARK_OAK_FENCE:
            case DARK_OAK_FENCE_GATE:
            case DARK_OAK_LEAVES:
            case DARK_OAK_LOG:
            case DARK_OAK_PLANKS:
            case DARK_OAK_SLAB:
            case DARK_OAK_STAIRS:
            case DARK_OAK_WOOD:
            case DEAD_BUSH:
            case DRIED_KELP_BLOCK:
            case FERN:
            case FLOWERING_AZALEA:
            case FLOWERING_AZALEA_LEAVES:
            case GLOW_LICHEN:
            case GRAY_CARPET:
            case GRAY_WOOL:
            case GREEN_CARPET:
            case GREEN_WOOL:
            case HANGING_ROOTS:
            case HAY_BLOCK:
            case JUNGLE_FENCE:
            case JUNGLE_FENCE_GATE:
            case JUNGLE_LEAVES:
            case JUNGLE_LOG:
            case JUNGLE_PLANKS:
            case JUNGLE_SLAB:
            case JUNGLE_STAIRS:
            case JUNGLE_WOOD:
            case LARGE_FERN:
            case LECTERN:
            case LIGHT_BLUE_CARPET:
            case LIGHT_BLUE_WOOL:
            case LIGHT_GRAY_CARPET:
            case LIGHT_GRAY_WOOL:
            case LILAC:
            case LILY_OF_THE_VALLEY:
            case LIME_CARPET:
            case LIME_WOOL:
            case MAGENTA_CARPET:
            case MAGENTA_WOOL:
            case MANGROVE_FENCE:
            case MANGROVE_FENCE_GATE:
            case MANGROVE_LEAVES:
            case MANGROVE_LOG:
            case MANGROVE_PLANKS:
            case MANGROVE_ROOTS:
            case MANGROVE_SLAB:
            case MANGROVE_STAIRS:
            case MANGROVE_WOOD:
            case OAK_FENCE:
            case OAK_FENCE_GATE:
            case OAK_LEAVES:
            case OAK_LOG:
            case OAK_PLANKS:
            case OAK_SLAB:
            case OAK_STAIRS:
            case OAK_WOOD:
            case ORANGE_CARPET:
            case ORANGE_TULIP:
            case ORANGE_WOOL:
            case OXEYE_DAISY:
            case PEONY:
            case PINK_CARPET:
            case PINK_PETALS:
            case PINK_TULIP:
            case PINK_WOOL:
            case PITCHER_PLANT:
            case POPPY:
            case PURPLE_CARPET:
            case PURPLE_WOOL:
            case RED_CARPET:
            case RED_TULIP:
            case RED_WOOL:
            case ROSE_BUSH:
            case SCAFFOLDING:
            case SHORT_GRASS:
            case SMALL_DRIPLEAF:
            case SPORE_BLOSSOM:
            case SPRUCE_FENCE:
            case SPRUCE_FENCE_GATE:
            case SPRUCE_LEAVES:
            case SPRUCE_LOG:
            case SPRUCE_PLANKS:
            case SPRUCE_SLAB:
            case SPRUCE_STAIRS:
            case SPRUCE_WOOD:
            case STRIPPED_ACACIA_LOG:
            case STRIPPED_ACACIA_WOOD:
            case STRIPPED_BAMBOO_BLOCK:
            case STRIPPED_BIRCH_LOG:
            case STRIPPED_BIRCH_WOOD:
            case STRIPPED_CHERRY_LOG:
            case STRIPPED_CHERRY_WOOD:
            case STRIPPED_DARK_OAK_LOG:
            case STRIPPED_DARK_OAK_WOOD:
            case STRIPPED_JUNGLE_LOG:
            case STRIPPED_JUNGLE_WOOD:
            case STRIPPED_MANGROVE_LOG:
            case STRIPPED_MANGROVE_WOOD:
            case STRIPPED_OAK_LOG:
            case STRIPPED_OAK_WOOD:
            case STRIPPED_SPRUCE_LOG:
            case STRIPPED_SPRUCE_WOOD:
            case SUNFLOWER:
            case SWEET_BERRY_BUSH:
            case TALL_GRASS:
            case TARGET:
            case TNT:
            case TORCHFLOWER:
            case VINE:
            case WHITE_CARPET:
            case WHITE_TULIP:
            case WHITE_WOOL:
            case WITHER_ROSE:
            case YELLOW_CARPET:
            case YELLOW_WOOL:
            // Paper end - Generated/Material#isBurnable
            // ----- Legacy Separator -----
            case LEGACY_WOOD:
            case LEGACY_LOG:
            case LEGACY_LEAVES:
            case LEGACY_LONG_GRASS:
            case LEGACY_WOOL:
            case LEGACY_YELLOW_FLOWER:
            case LEGACY_RED_ROSE:
            case LEGACY_TNT:
            case LEGACY_BOOKSHELF:
            case LEGACY_WOOD_STAIRS:
            case LEGACY_FENCE:
            case LEGACY_VINE:
            case LEGACY_WOOD_DOUBLE_STEP:
            case LEGACY_WOOD_STEP:
            case LEGACY_SPRUCE_WOOD_STAIRS:
            case LEGACY_BIRCH_WOOD_STAIRS:
            case LEGACY_JUNGLE_WOOD_STAIRS:
            case LEGACY_HAY_BLOCK:
            case LEGACY_COAL_BLOCK:
            case LEGACY_LEAVES_2:
            case LEGACY_LOG_2:
            case LEGACY_CARPET:
            case LEGACY_DOUBLE_PLANT:
            case LEGACY_DEAD_BUSH:
            case LEGACY_FENCE_GATE:
            case LEGACY_SPRUCE_FENCE_GATE:
            case LEGACY_BIRCH_FENCE_GATE:
            case LEGACY_JUNGLE_FENCE_GATE:
            case LEGACY_DARK_OAK_FENCE_GATE:
            case LEGACY_ACACIA_FENCE_GATE:
            case LEGACY_SPRUCE_FENCE:
            case LEGACY_BIRCH_FENCE:
            case LEGACY_JUNGLE_FENCE:
            case LEGACY_DARK_OAK_FENCE:
            case LEGACY_ACACIA_FENCE:
            case LEGACY_ACACIA_STAIRS:
            case LEGACY_DARK_OAK_STAIRS:
            //</editor-fold>
                return true;
            default:
                return false;
        }
    }

    /**
     * Checks if this Material can be used as fuel in a Furnace
     *
     * @return true if this Material can be used as fuel.
     */
    public boolean isFuel() {
        switch (this) {
            //<editor-fold defaultstate="collapsed" desc="isFuel">
            // Paper start - Generated/Material#isFuel
            // @GeneratedFrom 1.20.4
            case ACACIA_BOAT:
            case ACACIA_BUTTON:
            case ACACIA_CHEST_BOAT:
            case ACACIA_DOOR:
            case ACACIA_FENCE:
            case ACACIA_FENCE_GATE:
            case ACACIA_HANGING_SIGN:
            case ACACIA_LOG:
            case ACACIA_PLANKS:
            case ACACIA_PRESSURE_PLATE:
            case ACACIA_SAPLING:
            case ACACIA_SIGN:
            case ACACIA_SLAB:
            case ACACIA_STAIRS:
            case ACACIA_TRAPDOOR:
            case ACACIA_WOOD:
            case AZALEA:
            case BAMBOO:
            case BAMBOO_BLOCK:
            case BAMBOO_BUTTON:
            case BAMBOO_CHEST_RAFT:
            case BAMBOO_DOOR:
            case BAMBOO_FENCE:
            case BAMBOO_FENCE_GATE:
            case BAMBOO_HANGING_SIGN:
            case BAMBOO_MOSAIC:
            case BAMBOO_MOSAIC_SLAB:
            case BAMBOO_MOSAIC_STAIRS:
            case BAMBOO_PLANKS:
            case BAMBOO_PRESSURE_PLATE:
            case BAMBOO_RAFT:
            case BAMBOO_SIGN:
            case BAMBOO_SLAB:
            case BAMBOO_STAIRS:
            case BAMBOO_TRAPDOOR:
            case BARREL:
            case BIRCH_BOAT:
            case BIRCH_BUTTON:
            case BIRCH_CHEST_BOAT:
            case BIRCH_DOOR:
            case BIRCH_FENCE:
            case BIRCH_FENCE_GATE:
            case BIRCH_HANGING_SIGN:
            case BIRCH_LOG:
            case BIRCH_PLANKS:
            case BIRCH_PRESSURE_PLATE:
            case BIRCH_SAPLING:
            case BIRCH_SIGN:
            case BIRCH_SLAB:
            case BIRCH_STAIRS:
            case BIRCH_TRAPDOOR:
            case BIRCH_WOOD:
            case BLACK_BANNER:
            case BLACK_CARPET:
            case BLACK_WOOL:
            case BLAZE_ROD:
            case BLUE_BANNER:
            case BLUE_CARPET:
            case BLUE_WOOL:
            case BOOKSHELF:
            case BOW:
            case BOWL:
            case BROWN_BANNER:
            case BROWN_CARPET:
            case BROWN_WOOL:
            case CARTOGRAPHY_TABLE:
            case CHARCOAL:
            case CHERRY_BOAT:
            case CHERRY_BUTTON:
            case CHERRY_CHEST_BOAT:
            case CHERRY_DOOR:
            case CHERRY_FENCE:
            case CHERRY_FENCE_GATE:
            case CHERRY_HANGING_SIGN:
            case CHERRY_LOG:
            case CHERRY_PLANKS:
            case CHERRY_PRESSURE_PLATE:
            case CHERRY_SAPLING:
            case CHERRY_SIGN:
            case CHERRY_SLAB:
            case CHERRY_STAIRS:
            case CHERRY_TRAPDOOR:
            case CHERRY_WOOD:
            case CHEST:
            case CHISELED_BOOKSHELF:
            case COAL:
            case COAL_BLOCK:
            case COMPOSTER:
            case CRAFTING_TABLE:
            case CROSSBOW:
            case CYAN_BANNER:
            case CYAN_CARPET:
            case CYAN_WOOL:
            case DARK_OAK_BOAT:
            case DARK_OAK_BUTTON:
            case DARK_OAK_CHEST_BOAT:
            case DARK_OAK_DOOR:
            case DARK_OAK_FENCE:
            case DARK_OAK_FENCE_GATE:
            case DARK_OAK_HANGING_SIGN:
            case DARK_OAK_LOG:
            case DARK_OAK_PLANKS:
            case DARK_OAK_PRESSURE_PLATE:
            case DARK_OAK_SAPLING:
            case DARK_OAK_SIGN:
            case DARK_OAK_SLAB:
            case DARK_OAK_STAIRS:
            case DARK_OAK_TRAPDOOR:
            case DARK_OAK_WOOD:
            case DAYLIGHT_DETECTOR:
            case DEAD_BUSH:
            case DRIED_KELP_BLOCK:
            case FISHING_ROD:
            case FLETCHING_TABLE:
            case FLOWERING_AZALEA:
            case GRAY_BANNER:
            case GRAY_CARPET:
            case GRAY_WOOL:
            case GREEN_BANNER:
            case GREEN_CARPET:
            case GREEN_WOOL:
            case JUKEBOX:
            case JUNGLE_BOAT:
            case JUNGLE_BUTTON:
            case JUNGLE_CHEST_BOAT:
            case JUNGLE_DOOR:
            case JUNGLE_FENCE:
            case JUNGLE_FENCE_GATE:
            case JUNGLE_HANGING_SIGN:
            case JUNGLE_LOG:
            case JUNGLE_PLANKS:
            case JUNGLE_PRESSURE_PLATE:
            case JUNGLE_SAPLING:
            case JUNGLE_SIGN:
            case JUNGLE_SLAB:
            case JUNGLE_STAIRS:
            case JUNGLE_TRAPDOOR:
            case JUNGLE_WOOD:
            case LADDER:
            case LAVA_BUCKET:
            case LECTERN:
            case LIGHT_BLUE_BANNER:
            case LIGHT_BLUE_CARPET:
            case LIGHT_BLUE_WOOL:
            case LIGHT_GRAY_BANNER:
            case LIGHT_GRAY_CARPET:
            case LIGHT_GRAY_WOOL:
            case LIME_BANNER:
            case LIME_CARPET:
            case LIME_WOOL:
            case LOOM:
            case MAGENTA_BANNER:
            case MAGENTA_CARPET:
            case MAGENTA_WOOL:
            case MANGROVE_BOAT:
            case MANGROVE_BUTTON:
            case MANGROVE_CHEST_BOAT:
            case MANGROVE_DOOR:
            case MANGROVE_FENCE:
            case MANGROVE_FENCE_GATE:
            case MANGROVE_HANGING_SIGN:
            case MANGROVE_LOG:
            case MANGROVE_PLANKS:
            case MANGROVE_PRESSURE_PLATE:
            case MANGROVE_PROPAGULE:
            case MANGROVE_ROOTS:
            case MANGROVE_SIGN:
            case MANGROVE_SLAB:
            case MANGROVE_STAIRS:
            case MANGROVE_TRAPDOOR:
            case MANGROVE_WOOD:
            case NOTE_BLOCK:
            case OAK_BOAT:
            case OAK_BUTTON:
            case OAK_CHEST_BOAT:
            case OAK_DOOR:
            case OAK_FENCE:
            case OAK_FENCE_GATE:
            case OAK_HANGING_SIGN:
            case OAK_LOG:
            case OAK_PLANKS:
            case OAK_PRESSURE_PLATE:
            case OAK_SAPLING:
            case OAK_SIGN:
            case OAK_SLAB:
            case OAK_STAIRS:
            case OAK_TRAPDOOR:
            case OAK_WOOD:
            case ORANGE_BANNER:
            case ORANGE_CARPET:
            case ORANGE_WOOL:
            case PINK_BANNER:
            case PINK_CARPET:
            case PINK_WOOL:
            case PURPLE_BANNER:
            case PURPLE_CARPET:
            case PURPLE_WOOL:
            case RED_BANNER:
            case RED_CARPET:
            case RED_WOOL:
            case SCAFFOLDING:
            case SMITHING_TABLE:
            case SPRUCE_BOAT:
            case SPRUCE_BUTTON:
            case SPRUCE_CHEST_BOAT:
            case SPRUCE_DOOR:
            case SPRUCE_FENCE:
            case SPRUCE_FENCE_GATE:
            case SPRUCE_HANGING_SIGN:
            case SPRUCE_LOG:
            case SPRUCE_PLANKS:
            case SPRUCE_PRESSURE_PLATE:
            case SPRUCE_SAPLING:
            case SPRUCE_SIGN:
            case SPRUCE_SLAB:
            case SPRUCE_STAIRS:
            case SPRUCE_TRAPDOOR:
            case SPRUCE_WOOD:
            case STICK:
            case STRIPPED_ACACIA_LOG:
            case STRIPPED_ACACIA_WOOD:
            case STRIPPED_BAMBOO_BLOCK:
            case STRIPPED_BIRCH_LOG:
            case STRIPPED_BIRCH_WOOD:
            case STRIPPED_CHERRY_LOG:
            case STRIPPED_CHERRY_WOOD:
            case STRIPPED_DARK_OAK_LOG:
            case STRIPPED_DARK_OAK_WOOD:
            case STRIPPED_JUNGLE_LOG:
            case STRIPPED_JUNGLE_WOOD:
            case STRIPPED_MANGROVE_LOG:
            case STRIPPED_MANGROVE_WOOD:
            case STRIPPED_OAK_LOG:
            case STRIPPED_OAK_WOOD:
            case STRIPPED_SPRUCE_LOG:
            case STRIPPED_SPRUCE_WOOD:
            case TRAPPED_CHEST:
            case WHITE_BANNER:
            case WHITE_CARPET:
            case WHITE_WOOL:
            case WOODEN_AXE:
            case WOODEN_HOE:
            case WOODEN_PICKAXE:
            case WOODEN_SHOVEL:
            case WOODEN_SWORD:
            case YELLOW_BANNER:
            case YELLOW_CARPET:
            case YELLOW_WOOL:
            // Paper end - Generated/Material#isFuel
            // ----- Legacy Separator -----
            case LEGACY_LAVA_BUCKET:
            case LEGACY_COAL_BLOCK:
            case LEGACY_BLAZE_ROD:
            case LEGACY_COAL:
            case LEGACY_BOAT:
            case LEGACY_BOAT_ACACIA:
            case LEGACY_BOAT_BIRCH:
            case LEGACY_BOAT_DARK_OAK:
            case LEGACY_BOAT_JUNGLE:
            case LEGACY_BOAT_SPRUCE:
            case LEGACY_LOG:
            case LEGACY_LOG_2:
            case LEGACY_WOOD:
            case LEGACY_WOOD_PLATE:
            case LEGACY_FENCE:
            case LEGACY_ACACIA_FENCE:
            case LEGACY_BIRCH_FENCE:
            case LEGACY_DARK_OAK_FENCE:
            case LEGACY_JUNGLE_FENCE:
            case LEGACY_SPRUCE_FENCE:
            case LEGACY_FENCE_GATE:
            case LEGACY_ACACIA_FENCE_GATE:
            case LEGACY_BIRCH_FENCE_GATE:
            case LEGACY_DARK_OAK_FENCE_GATE:
            case LEGACY_JUNGLE_FENCE_GATE:
            case LEGACY_SPRUCE_FENCE_GATE:
            case LEGACY_WOOD_STAIRS:
            case LEGACY_ACACIA_STAIRS:
            case LEGACY_BIRCH_WOOD_STAIRS:
            case LEGACY_DARK_OAK_STAIRS:
            case LEGACY_JUNGLE_WOOD_STAIRS:
            case LEGACY_SPRUCE_WOOD_STAIRS:
            case LEGACY_TRAP_DOOR:
            case LEGACY_WORKBENCH:
            case LEGACY_BOOKSHELF:
            case LEGACY_CHEST:
            case LEGACY_TRAPPED_CHEST:
            case LEGACY_DAYLIGHT_DETECTOR:
            case LEGACY_JUKEBOX:
            case LEGACY_NOTE_BLOCK:
            case LEGACY_BANNER:
            case LEGACY_FISHING_ROD:
            case LEGACY_LADDER:
            case LEGACY_WOOD_SWORD:
            case LEGACY_WOOD_PICKAXE:
            case LEGACY_WOOD_AXE:
            case LEGACY_WOOD_SPADE:
            case LEGACY_WOOD_HOE:
            case LEGACY_BOW:
            case LEGACY_SIGN:
            case LEGACY_WOOD_DOOR:
            case LEGACY_ACACIA_DOOR_ITEM:
            case LEGACY_BIRCH_DOOR_ITEM:
            case LEGACY_DARK_OAK_DOOR_ITEM:
            case LEGACY_JUNGLE_DOOR_ITEM:
            case LEGACY_SPRUCE_DOOR_ITEM:
            case LEGACY_WOOD_STEP:
            case LEGACY_SAPLING:
            case LEGACY_STICK:
            case LEGACY_WOOD_BUTTON:
            case LEGACY_WOOL:
            case LEGACY_CARPET:
            case LEGACY_BOWL:
            //</editor-fold>
                return true;
            default:
                return false;
        }
    }

    /**
     * Check if the material is a block and occludes light in the lighting engine.
     * <p>
     * Generally speaking, most full blocks will occlude light. Non-full blocks are
     * not occluding (e.g. anvils, chests, tall grass, stairs, etc.), nor are specific
     * full blocks such as barriers or spawners which block light despite their texture.
     * <p>
     * An occluding block will have the following effects:
     * <ul>
     *   <li>Chests cannot be opened if an occluding block is above it.
     *   <li>Mobs cannot spawn inside of occluding blocks.
     *   <li>Only occluding blocks can be "powered" ({@link Block#isBlockPowered()}).
     * </ul>
     * This list may be inconclusive. For a full list of the side effects of an occluding
     * block, see the <a href="https://minecraft.wiki/w/Opacity">Minecraft Wiki</a>.
     *
     * @return True if this material is a block and occludes light
     */
    public boolean isOccluding() {
        if (!isBlock()) {
            return false;
        }
        switch (this) {
            //<editor-fold defaultstate="collapsed" desc="isOccluding">
            // Paper start - Generated/Material#isOccluding
            // @GeneratedFrom 1.20.4
            case ACACIA_LOG:
            case ACACIA_PLANKS:
            case ACACIA_WOOD:
            case AMETHYST_BLOCK:
            case ANCIENT_DEBRIS:
            case ANDESITE:
            case BAMBOO_BLOCK:
            case BAMBOO_MOSAIC:
            case BAMBOO_PLANKS:
            case BARREL:
            case BARRIER:
            case BASALT:
            case BEDROCK:
            case BEEHIVE:
            case BEE_NEST:
            case BIRCH_LOG:
            case BIRCH_PLANKS:
            case BIRCH_WOOD:
            case BLACKSTONE:
            case BLACK_CONCRETE:
            case BLACK_CONCRETE_POWDER:
            case BLACK_GLAZED_TERRACOTTA:
            case BLACK_SHULKER_BOX:
            case BLACK_TERRACOTTA:
            case BLACK_WOOL:
            case BLAST_FURNACE:
            case BLUE_CONCRETE:
            case BLUE_CONCRETE_POWDER:
            case BLUE_GLAZED_TERRACOTTA:
            case BLUE_ICE:
            case BLUE_SHULKER_BOX:
            case BLUE_TERRACOTTA:
            case BLUE_WOOL:
            case BONE_BLOCK:
            case BOOKSHELF:
            case BRAIN_CORAL_BLOCK:
            case BRICKS:
            case BROWN_CONCRETE:
            case BROWN_CONCRETE_POWDER:
            case BROWN_GLAZED_TERRACOTTA:
            case BROWN_MUSHROOM_BLOCK:
            case BROWN_SHULKER_BOX:
            case BROWN_TERRACOTTA:
            case BROWN_WOOL:
            case BUBBLE_CORAL_BLOCK:
            case BUDDING_AMETHYST:
            case CALCITE:
            case CARTOGRAPHY_TABLE:
            case CARVED_PUMPKIN:
            case CHAIN_COMMAND_BLOCK:
            case CHERRY_LOG:
            case CHERRY_PLANKS:
            case CHERRY_WOOD:
            case CHISELED_BOOKSHELF:
            case CHISELED_COPPER:
            case CHISELED_DEEPSLATE:
            case CHISELED_NETHER_BRICKS:
            case CHISELED_POLISHED_BLACKSTONE:
            case CHISELED_QUARTZ_BLOCK:
            case CHISELED_RED_SANDSTONE:
            case CHISELED_SANDSTONE:
            case CHISELED_STONE_BRICKS:
            case CHISELED_TUFF:
            case CHISELED_TUFF_BRICKS:
            case CLAY:
            case COAL_BLOCK:
            case COAL_ORE:
            case COARSE_DIRT:
            case COBBLED_DEEPSLATE:
            case COBBLESTONE:
            case COMMAND_BLOCK:
            case COPPER_BLOCK:
            case COPPER_ORE:
            case CRACKED_DEEPSLATE_BRICKS:
            case CRACKED_DEEPSLATE_TILES:
            case CRACKED_NETHER_BRICKS:
            case CRACKED_POLISHED_BLACKSTONE_BRICKS:
            case CRACKED_STONE_BRICKS:
            case CRAFTER:
            case CRAFTING_TABLE:
            case CRIMSON_HYPHAE:
            case CRIMSON_NYLIUM:
            case CRIMSON_PLANKS:
            case CRIMSON_STEM:
            case CRYING_OBSIDIAN:
            case CUT_COPPER:
            case CUT_RED_SANDSTONE:
            case CUT_SANDSTONE:
            case CYAN_CONCRETE:
            case CYAN_CONCRETE_POWDER:
            case CYAN_GLAZED_TERRACOTTA:
            case CYAN_SHULKER_BOX:
            case CYAN_TERRACOTTA:
            case CYAN_WOOL:
            case DARK_OAK_LOG:
            case DARK_OAK_PLANKS:
            case DARK_OAK_WOOD:
            case DARK_PRISMARINE:
            case DEAD_BRAIN_CORAL_BLOCK:
            case DEAD_BUBBLE_CORAL_BLOCK:
            case DEAD_FIRE_CORAL_BLOCK:
            case DEAD_HORN_CORAL_BLOCK:
            case DEAD_TUBE_CORAL_BLOCK:
            case DEEPSLATE:
            case DEEPSLATE_BRICKS:
            case DEEPSLATE_COAL_ORE:
            case DEEPSLATE_COPPER_ORE:
            case DEEPSLATE_DIAMOND_ORE:
            case DEEPSLATE_EMERALD_ORE:
            case DEEPSLATE_GOLD_ORE:
            case DEEPSLATE_IRON_ORE:
            case DEEPSLATE_LAPIS_ORE:
            case DEEPSLATE_REDSTONE_ORE:
            case DEEPSLATE_TILES:
            case DIAMOND_BLOCK:
            case DIAMOND_ORE:
            case DIORITE:
            case DIRT:
            case DISPENSER:
            case DRIED_KELP_BLOCK:
            case DRIPSTONE_BLOCK:
            case DROPPER:
            case EMERALD_BLOCK:
            case EMERALD_ORE:
            case END_STONE:
            case END_STONE_BRICKS:
            case EXPOSED_CHISELED_COPPER:
            case EXPOSED_COPPER:
            case EXPOSED_CUT_COPPER:
            case FIRE_CORAL_BLOCK:
            case FLETCHING_TABLE:
            case FURNACE:
            case GILDED_BLACKSTONE:
            case GOLD_BLOCK:
            case GOLD_ORE:
            case GRANITE:
            case GRASS_BLOCK:
            case GRAVEL:
            case GRAY_CONCRETE:
            case GRAY_CONCRETE_POWDER:
            case GRAY_GLAZED_TERRACOTTA:
            case GRAY_SHULKER_BOX:
            case GRAY_TERRACOTTA:
            case GRAY_WOOL:
            case GREEN_CONCRETE:
            case GREEN_CONCRETE_POWDER:
            case GREEN_GLAZED_TERRACOTTA:
            case GREEN_SHULKER_BOX:
            case GREEN_TERRACOTTA:
            case GREEN_WOOL:
            case HAY_BLOCK:
            case HONEYCOMB_BLOCK:
            case HORN_CORAL_BLOCK:
            case INFESTED_CHISELED_STONE_BRICKS:
            case INFESTED_COBBLESTONE:
            case INFESTED_CRACKED_STONE_BRICKS:
            case INFESTED_DEEPSLATE:
            case INFESTED_MOSSY_STONE_BRICKS:
            case INFESTED_STONE:
            case INFESTED_STONE_BRICKS:
            case IRON_BLOCK:
            case IRON_ORE:
            case JACK_O_LANTERN:
            case JIGSAW:
            case JUKEBOX:
            case JUNGLE_LOG:
            case JUNGLE_PLANKS:
            case JUNGLE_WOOD:
            case LAPIS_BLOCK:
            case LAPIS_ORE:
            case LIGHT_BLUE_CONCRETE:
            case LIGHT_BLUE_CONCRETE_POWDER:
            case LIGHT_BLUE_GLAZED_TERRACOTTA:
            case LIGHT_BLUE_SHULKER_BOX:
            case LIGHT_BLUE_TERRACOTTA:
            case LIGHT_BLUE_WOOL:
            case LIGHT_GRAY_CONCRETE:
            case LIGHT_GRAY_CONCRETE_POWDER:
            case LIGHT_GRAY_GLAZED_TERRACOTTA:
            case LIGHT_GRAY_SHULKER_BOX:
            case LIGHT_GRAY_TERRACOTTA:
            case LIGHT_GRAY_WOOL:
            case LIME_CONCRETE:
            case LIME_CONCRETE_POWDER:
            case LIME_GLAZED_TERRACOTTA:
            case LIME_SHULKER_BOX:
            case LIME_TERRACOTTA:
            case LIME_WOOL:
            case LODESTONE:
            case LOOM:
            case MAGENTA_CONCRETE:
            case MAGENTA_CONCRETE_POWDER:
            case MAGENTA_GLAZED_TERRACOTTA:
            case MAGENTA_SHULKER_BOX:
            case MAGENTA_TERRACOTTA:
            case MAGENTA_WOOL:
            case MAGMA_BLOCK:
            case MANGROVE_LOG:
            case MANGROVE_PLANKS:
            case MANGROVE_ROOTS:
            case MANGROVE_WOOD:
            case MELON:
            case MOSSY_COBBLESTONE:
            case MOSSY_STONE_BRICKS:
            case MOSS_BLOCK:
            case MUD:
            case MUDDY_MANGROVE_ROOTS:
            case MUD_BRICKS:
            case MUSHROOM_STEM:
            case MYCELIUM:
            case NETHERITE_BLOCK:
            case NETHERRACK:
            case NETHER_BRICKS:
            case NETHER_GOLD_ORE:
            case NETHER_QUARTZ_ORE:
            case NETHER_WART_BLOCK:
            case NOTE_BLOCK:
            case OAK_LOG:
            case OAK_PLANKS:
            case OAK_WOOD:
            case OBSIDIAN:
            case OCHRE_FROGLIGHT:
            case ORANGE_CONCRETE:
            case ORANGE_CONCRETE_POWDER:
            case ORANGE_GLAZED_TERRACOTTA:
            case ORANGE_SHULKER_BOX:
            case ORANGE_TERRACOTTA:
            case ORANGE_WOOL:
            case OXIDIZED_CHISELED_COPPER:
            case OXIDIZED_COPPER:
            case OXIDIZED_CUT_COPPER:
            case PACKED_ICE:
            case PACKED_MUD:
            case PEARLESCENT_FROGLIGHT:
            case PINK_CONCRETE:
            case PINK_CONCRETE_POWDER:
            case PINK_GLAZED_TERRACOTTA:
            case PINK_SHULKER_BOX:
            case PINK_TERRACOTTA:
            case PINK_WOOL:
            case PODZOL:
            case POLISHED_ANDESITE:
            case POLISHED_BASALT:
            case POLISHED_BLACKSTONE:
            case POLISHED_BLACKSTONE_BRICKS:
            case POLISHED_DEEPSLATE:
            case POLISHED_DIORITE:
            case POLISHED_GRANITE:
            case POLISHED_TUFF:
            case PRISMARINE:
            case PRISMARINE_BRICKS:
            case PUMPKIN:
            case PURPLE_CONCRETE:
            case PURPLE_CONCRETE_POWDER:
            case PURPLE_GLAZED_TERRACOTTA:
            case PURPLE_SHULKER_BOX:
            case PURPLE_TERRACOTTA:
            case PURPLE_WOOL:
            case PURPUR_BLOCK:
            case PURPUR_PILLAR:
            case QUARTZ_BLOCK:
            case QUARTZ_BRICKS:
            case QUARTZ_PILLAR:
            case RAW_COPPER_BLOCK:
            case RAW_GOLD_BLOCK:
            case RAW_IRON_BLOCK:
            case REDSTONE_LAMP:
            case REDSTONE_ORE:
            case RED_CONCRETE:
            case RED_CONCRETE_POWDER:
            case RED_GLAZED_TERRACOTTA:
            case RED_MUSHROOM_BLOCK:
            case RED_NETHER_BRICKS:
            case RED_SAND:
            case RED_SANDSTONE:
            case RED_SHULKER_BOX:
            case RED_TERRACOTTA:
            case RED_WOOL:
            case REINFORCED_DEEPSLATE:
            case REPEATING_COMMAND_BLOCK:
            case RESPAWN_ANCHOR:
            case ROOTED_DIRT:
            case SAND:
            case SANDSTONE:
            case SCULK:
            case SCULK_CATALYST:
            case SHROOMLIGHT:
            case SHULKER_BOX:
            case SLIME_BLOCK:
            case SMITHING_TABLE:
            case SMOKER:
            case SMOOTH_BASALT:
            case SMOOTH_QUARTZ:
            case SMOOTH_RED_SANDSTONE:
            case SMOOTH_SANDSTONE:
            case SMOOTH_STONE:
            case SNOW_BLOCK:
            case SOUL_SAND:
            case SOUL_SOIL:
            case SPAWNER:
            case SPONGE:
            case SPRUCE_LOG:
            case SPRUCE_PLANKS:
            case SPRUCE_WOOD:
            case STONE:
            case STONE_BRICKS:
            case STRIPPED_ACACIA_LOG:
            case STRIPPED_ACACIA_WOOD:
            case STRIPPED_BAMBOO_BLOCK:
            case STRIPPED_BIRCH_LOG:
            case STRIPPED_BIRCH_WOOD:
            case STRIPPED_CHERRY_LOG:
            case STRIPPED_CHERRY_WOOD:
            case STRIPPED_CRIMSON_HYPHAE:
            case STRIPPED_CRIMSON_STEM:
            case STRIPPED_DARK_OAK_LOG:
            case STRIPPED_DARK_OAK_WOOD:
            case STRIPPED_JUNGLE_LOG:
            case STRIPPED_JUNGLE_WOOD:
            case STRIPPED_MANGROVE_LOG:
            case STRIPPED_MANGROVE_WOOD:
            case STRIPPED_OAK_LOG:
            case STRIPPED_OAK_WOOD:
            case STRIPPED_SPRUCE_LOG:
            case STRIPPED_SPRUCE_WOOD:
            case STRIPPED_WARPED_HYPHAE:
            case STRIPPED_WARPED_STEM:
            case STRUCTURE_BLOCK:
            case SUSPICIOUS_GRAVEL:
            case SUSPICIOUS_SAND:
            case TARGET:
            case TERRACOTTA:
            case TRIAL_SPAWNER:
            case TUBE_CORAL_BLOCK:
            case TUFF:
            case TUFF_BRICKS:
            case VERDANT_FROGLIGHT:
            case WARPED_HYPHAE:
            case WARPED_NYLIUM:
            case WARPED_PLANKS:
            case WARPED_STEM:
            case WARPED_WART_BLOCK:
            case WAXED_CHISELED_COPPER:
            case WAXED_COPPER_BLOCK:
            case WAXED_CUT_COPPER:
            case WAXED_EXPOSED_CHISELED_COPPER:
            case WAXED_EXPOSED_COPPER:
            case WAXED_EXPOSED_CUT_COPPER:
            case WAXED_OXIDIZED_CHISELED_COPPER:
            case WAXED_OXIDIZED_COPPER:
            case WAXED_OXIDIZED_CUT_COPPER:
            case WAXED_WEATHERED_CHISELED_COPPER:
            case WAXED_WEATHERED_COPPER:
            case WAXED_WEATHERED_CUT_COPPER:
            case WEATHERED_CHISELED_COPPER:
            case WEATHERED_COPPER:
            case WEATHERED_CUT_COPPER:
            case WET_SPONGE:
            case WHITE_CONCRETE:
            case WHITE_CONCRETE_POWDER:
            case WHITE_GLAZED_TERRACOTTA:
            case WHITE_SHULKER_BOX:
            case WHITE_TERRACOTTA:
            case WHITE_WOOL:
            case YELLOW_CONCRETE:
            case YELLOW_CONCRETE_POWDER:
            case YELLOW_GLAZED_TERRACOTTA:
            case YELLOW_SHULKER_BOX:
            case YELLOW_TERRACOTTA:
            case YELLOW_WOOL:
            // Paper end - Generated/Material#isOccluding
            // ----- Legacy Separator -----
            case LEGACY_STONE:
            case LEGACY_GRASS:
            case LEGACY_DIRT:
            case LEGACY_COBBLESTONE:
            case LEGACY_WOOD:
            case LEGACY_BEDROCK:
            case LEGACY_SAND:
            case LEGACY_GRAVEL:
            case LEGACY_GOLD_ORE:
            case LEGACY_IRON_ORE:
            case LEGACY_COAL_ORE:
            case LEGACY_LOG:
            case LEGACY_SPONGE:
            case LEGACY_LAPIS_ORE:
            case LEGACY_LAPIS_BLOCK:
            case LEGACY_DISPENSER:
            case LEGACY_SANDSTONE:
            case LEGACY_NOTE_BLOCK:
            case LEGACY_WOOL:
            case LEGACY_GOLD_BLOCK:
            case LEGACY_IRON_BLOCK:
            case LEGACY_DOUBLE_STEP:
            case LEGACY_BRICK:
            case LEGACY_BOOKSHELF:
            case LEGACY_MOSSY_COBBLESTONE:
            case LEGACY_OBSIDIAN:
            case LEGACY_MOB_SPAWNER:
            case LEGACY_DIAMOND_ORE:
            case LEGACY_DIAMOND_BLOCK:
            case LEGACY_WORKBENCH:
            case LEGACY_FURNACE:
            case LEGACY_BURNING_FURNACE:
            case LEGACY_REDSTONE_ORE:
            case LEGACY_GLOWING_REDSTONE_ORE:
            case LEGACY_SNOW_BLOCK:
            case LEGACY_CLAY:
            case LEGACY_JUKEBOX:
            case LEGACY_PUMPKIN:
            case LEGACY_NETHERRACK:
            case LEGACY_SOUL_SAND:
            case LEGACY_JACK_O_LANTERN:
            case LEGACY_MONSTER_EGGS:
            case LEGACY_SMOOTH_BRICK:
            case LEGACY_HUGE_MUSHROOM_1:
            case LEGACY_HUGE_MUSHROOM_2:
            case LEGACY_MELON_BLOCK:
            case LEGACY_MYCEL:
            case LEGACY_NETHER_BRICK:
            case LEGACY_ENDER_STONE:
            case LEGACY_REDSTONE_LAMP_OFF:
            case LEGACY_REDSTONE_LAMP_ON:
            case LEGACY_WOOD_DOUBLE_STEP:
            case LEGACY_EMERALD_ORE:
            case LEGACY_EMERALD_BLOCK:
            case LEGACY_COMMAND:
            case LEGACY_QUARTZ_ORE:
            case LEGACY_QUARTZ_BLOCK:
            case LEGACY_DROPPER:
            case LEGACY_STAINED_CLAY:
            case LEGACY_HAY_BLOCK:
            case LEGACY_HARD_CLAY:
            case LEGACY_COAL_BLOCK:
            case LEGACY_LOG_2:
            case LEGACY_PACKED_ICE:
            case LEGACY_SLIME_BLOCK:
            case LEGACY_BARRIER:
            case LEGACY_PRISMARINE:
            case LEGACY_RED_SANDSTONE:
            case LEGACY_DOUBLE_STONE_SLAB2:
            case LEGACY_PURPUR_BLOCK:
            case LEGACY_PURPUR_PILLAR:
            case LEGACY_PURPUR_DOUBLE_SLAB:
            case LEGACY_END_BRICKS:
            case LEGACY_STRUCTURE_BLOCK:
            case LEGACY_COMMAND_REPEATING:
            case LEGACY_COMMAND_CHAIN:
            case LEGACY_MAGMA:
            case LEGACY_NETHER_WART_BLOCK:
            case LEGACY_RED_NETHER_BRICK:
            case LEGACY_BONE_BLOCK:
            case LEGACY_WHITE_GLAZED_TERRACOTTA:
            case LEGACY_ORANGE_GLAZED_TERRACOTTA:
            case LEGACY_MAGENTA_GLAZED_TERRACOTTA:
            case LEGACY_LIGHT_BLUE_GLAZED_TERRACOTTA:
            case LEGACY_YELLOW_GLAZED_TERRACOTTA:
            case LEGACY_LIME_GLAZED_TERRACOTTA:
            case LEGACY_PINK_GLAZED_TERRACOTTA:
            case LEGACY_GRAY_GLAZED_TERRACOTTA:
            case LEGACY_SILVER_GLAZED_TERRACOTTA:
            case LEGACY_CYAN_GLAZED_TERRACOTTA:
            case LEGACY_PURPLE_GLAZED_TERRACOTTA:
            case LEGACY_BLUE_GLAZED_TERRACOTTA:
            case LEGACY_BROWN_GLAZED_TERRACOTTA:
            case LEGACY_GREEN_GLAZED_TERRACOTTA:
            case LEGACY_RED_GLAZED_TERRACOTTA:
            case LEGACY_BLACK_GLAZED_TERRACOTTA:
            case LEGACY_CONCRETE:
            case LEGACY_CONCRETE_POWDER:
            //</editor-fold>
                return true;
            default:
                return false;
        }
    }

    /**
     * @return True if this material is affected by gravity.
     */
    public boolean hasGravity() {
        if (!isBlock()) {
            return false;
        }
        switch (this) {
            //<editor-fold defaultstate="collapsed" desc="hasGravity">
            // Paper start - Generated/Material#hasGravity
            // @GeneratedFrom 1.20.4
            case ANVIL:
            case BLACK_CONCRETE_POWDER:
            case BLUE_CONCRETE_POWDER:
            case BROWN_CONCRETE_POWDER:
            case CHIPPED_ANVIL:
            case CYAN_CONCRETE_POWDER:
            case DAMAGED_ANVIL:
            case DRAGON_EGG:
            case GRAVEL:
            case GRAY_CONCRETE_POWDER:
            case GREEN_CONCRETE_POWDER:
            case LIGHT_BLUE_CONCRETE_POWDER:
            case LIGHT_GRAY_CONCRETE_POWDER:
            case LIME_CONCRETE_POWDER:
            case MAGENTA_CONCRETE_POWDER:
            case ORANGE_CONCRETE_POWDER:
            case PINK_CONCRETE_POWDER:
            case PURPLE_CONCRETE_POWDER:
            case RED_CONCRETE_POWDER:
            case RED_SAND:
            case SAND:
            case WHITE_CONCRETE_POWDER:
            case YELLOW_CONCRETE_POWDER:
            // Paper end - Generated/Material#hasGravity
            // ----- Legacy Separator -----
            case LEGACY_SAND:
            case LEGACY_GRAVEL:
            case LEGACY_ANVIL:
            case LEGACY_CONCRETE_POWDER:
            //</editor-fold>
                return true;
            default:
                return false;
        }
    }

    /**
     * Checks if this Material is an obtainable item.
     *
     * @return true if this material is an item
     */
    public boolean isItem() {
        switch (this) {
            //<editor-fold defaultstate="collapsed" desc="isItem">
            // Paper start - Generated/Material#isItem
            // @GeneratedFrom 1.20.4
            case ACACIA_WALL_HANGING_SIGN:
            case ACACIA_WALL_SIGN:
            case ATTACHED_MELON_STEM:
            case ATTACHED_PUMPKIN_STEM:
            case BAMBOO_SAPLING:
            case BAMBOO_WALL_HANGING_SIGN:
            case BAMBOO_WALL_SIGN:
            case BEETROOTS:
            case BIG_DRIPLEAF_STEM:
            case BIRCH_WALL_HANGING_SIGN:
            case BIRCH_WALL_SIGN:
            case BLACK_CANDLE_CAKE:
            case BLACK_WALL_BANNER:
            case BLUE_CANDLE_CAKE:
            case BLUE_WALL_BANNER:
            case BRAIN_CORAL_WALL_FAN:
            case BROWN_CANDLE_CAKE:
            case BROWN_WALL_BANNER:
            case BUBBLE_COLUMN:
            case BUBBLE_CORAL_WALL_FAN:
            case CANDLE_CAKE:
            case CARROTS:
            case CAVE_AIR:
            case CAVE_VINES:
            case CAVE_VINES_PLANT:
            case CHERRY_WALL_HANGING_SIGN:
            case CHERRY_WALL_SIGN:
            case COCOA:
            case CREEPER_WALL_HEAD:
            case CRIMSON_WALL_HANGING_SIGN:
            case CRIMSON_WALL_SIGN:
            case CYAN_CANDLE_CAKE:
            case CYAN_WALL_BANNER:
            case DARK_OAK_WALL_HANGING_SIGN:
            case DARK_OAK_WALL_SIGN:
            case DEAD_BRAIN_CORAL_WALL_FAN:
            case DEAD_BUBBLE_CORAL_WALL_FAN:
            case DEAD_FIRE_CORAL_WALL_FAN:
            case DEAD_HORN_CORAL_WALL_FAN:
            case DEAD_TUBE_CORAL_WALL_FAN:
            case DRAGON_WALL_HEAD:
            case END_GATEWAY:
            case END_PORTAL:
            case FIRE:
            case FIRE_CORAL_WALL_FAN:
            case FROSTED_ICE:
            case GRAY_CANDLE_CAKE:
            case GRAY_WALL_BANNER:
            case GREEN_CANDLE_CAKE:
            case GREEN_WALL_BANNER:
            case HORN_CORAL_WALL_FAN:
            case JUNGLE_WALL_HANGING_SIGN:
            case JUNGLE_WALL_SIGN:
            case KELP_PLANT:
            case LAVA:
            case LAVA_CAULDRON:
            case LIGHT_BLUE_CANDLE_CAKE:
            case LIGHT_BLUE_WALL_BANNER:
            case LIGHT_GRAY_CANDLE_CAKE:
            case LIGHT_GRAY_WALL_BANNER:
            case LIME_CANDLE_CAKE:
            case LIME_WALL_BANNER:
            case MAGENTA_CANDLE_CAKE:
            case MAGENTA_WALL_BANNER:
            case MANGROVE_WALL_HANGING_SIGN:
            case MANGROVE_WALL_SIGN:
            case MELON_STEM:
            case MOVING_PISTON:
            case NETHER_PORTAL:
            case OAK_WALL_HANGING_SIGN:
            case OAK_WALL_SIGN:
            case ORANGE_CANDLE_CAKE:
            case ORANGE_WALL_BANNER:
            case PIGLIN_WALL_HEAD:
            case PINK_CANDLE_CAKE:
            case PINK_WALL_BANNER:
            case PISTON_HEAD:
            case PITCHER_CROP:
            case PLAYER_WALL_HEAD:
            case POTATOES:
            case POTTED_ACACIA_SAPLING:
            case POTTED_ALLIUM:
            case POTTED_AZALEA_BUSH:
            case POTTED_AZURE_BLUET:
            case POTTED_BAMBOO:
            case POTTED_BIRCH_SAPLING:
            case POTTED_BLUE_ORCHID:
            case POTTED_BROWN_MUSHROOM:
            case POTTED_CACTUS:
            case POTTED_CHERRY_SAPLING:
            case POTTED_CORNFLOWER:
            case POTTED_CRIMSON_FUNGUS:
            case POTTED_CRIMSON_ROOTS:
            case POTTED_DANDELION:
            case POTTED_DARK_OAK_SAPLING:
            case POTTED_DEAD_BUSH:
            case POTTED_FERN:
            case POTTED_FLOWERING_AZALEA_BUSH:
            case POTTED_JUNGLE_SAPLING:
            case POTTED_LILY_OF_THE_VALLEY:
            case POTTED_MANGROVE_PROPAGULE:
            case POTTED_OAK_SAPLING:
            case POTTED_ORANGE_TULIP:
            case POTTED_OXEYE_DAISY:
            case POTTED_PINK_TULIP:
            case POTTED_POPPY:
            case POTTED_RED_MUSHROOM:
            case POTTED_RED_TULIP:
            case POTTED_SPRUCE_SAPLING:
            case POTTED_TORCHFLOWER:
            case POTTED_WARPED_FUNGUS:
            case POTTED_WARPED_ROOTS:
            case POTTED_WHITE_TULIP:
            case POTTED_WITHER_ROSE:
            case POWDER_SNOW:
            case POWDER_SNOW_CAULDRON:
            case PUMPKIN_STEM:
            case PURPLE_CANDLE_CAKE:
            case PURPLE_WALL_BANNER:
            case REDSTONE_WALL_TORCH:
            case REDSTONE_WIRE:
            case RED_CANDLE_CAKE:
            case RED_WALL_BANNER:
            case SKELETON_WALL_SKULL:
            case SOUL_FIRE:
            case SOUL_WALL_TORCH:
            case SPRUCE_WALL_HANGING_SIGN:
            case SPRUCE_WALL_SIGN:
            case SWEET_BERRY_BUSH:
            case TALL_SEAGRASS:
            case TORCHFLOWER_CROP:
            case TRIPWIRE:
            case TUBE_CORAL_WALL_FAN:
            case TWISTING_VINES_PLANT:
            case VOID_AIR:
            case WALL_TORCH:
            case WARPED_WALL_HANGING_SIGN:
            case WARPED_WALL_SIGN:
            case WATER:
            case WATER_CAULDRON:
            case WEEPING_VINES_PLANT:
            case WHITE_CANDLE_CAKE:
            case WHITE_WALL_BANNER:
            case WITHER_SKELETON_WALL_SKULL:
            case YELLOW_CANDLE_CAKE:
            case YELLOW_WALL_BANNER:
            case ZOMBIE_WALL_HEAD:
            // Paper end - Generated/Material#isItem
            // ----- Legacy Separator -----
            case LEGACY_ACACIA_DOOR:
            case LEGACY_BED_BLOCK:
            case LEGACY_BEETROOT_BLOCK:
            case LEGACY_BIRCH_DOOR:
            case LEGACY_BREWING_STAND:
            case LEGACY_BURNING_FURNACE:
            case LEGACY_CAKE_BLOCK:
            case LEGACY_CARROT:
            case LEGACY_CAULDRON:
            case LEGACY_COCOA:
            case LEGACY_CROPS:
            case LEGACY_DARK_OAK_DOOR:
            case LEGACY_DAYLIGHT_DETECTOR_INVERTED:
            case LEGACY_DIODE_BLOCK_OFF:
            case LEGACY_DIODE_BLOCK_ON:
            case LEGACY_DOUBLE_STEP:
            case LEGACY_DOUBLE_STONE_SLAB2:
            case LEGACY_ENDER_PORTAL:
            case LEGACY_END_GATEWAY:
            case LEGACY_FIRE:
            case LEGACY_FLOWER_POT:
            case LEGACY_FROSTED_ICE:
            case LEGACY_GLOWING_REDSTONE_ORE:
            case LEGACY_IRON_DOOR_BLOCK:
            case LEGACY_JUNGLE_DOOR:
            case LEGACY_LAVA:
            case LEGACY_MELON_STEM:
            case LEGACY_NETHER_WARTS:
            case LEGACY_PISTON_EXTENSION:
            case LEGACY_PISTON_MOVING_PIECE:
            case LEGACY_PORTAL:
            case LEGACY_POTATO:
            case LEGACY_PUMPKIN_STEM:
            case LEGACY_PURPUR_DOUBLE_SLAB:
            case LEGACY_REDSTONE_COMPARATOR_OFF:
            case LEGACY_REDSTONE_COMPARATOR_ON:
            case LEGACY_REDSTONE_LAMP_ON:
            case LEGACY_REDSTONE_TORCH_OFF:
            case LEGACY_REDSTONE_WIRE:
            case LEGACY_SIGN_POST:
            case LEGACY_SKULL:
            case LEGACY_SPRUCE_DOOR:
            case LEGACY_STANDING_BANNER:
            case LEGACY_STATIONARY_LAVA:
            case LEGACY_STATIONARY_WATER:
            case LEGACY_SUGAR_CANE_BLOCK:
            case LEGACY_TRIPWIRE:
            case LEGACY_WALL_BANNER:
            case LEGACY_WALL_SIGN:
            case LEGACY_WATER:
            case LEGACY_WOODEN_DOOR:
            case LEGACY_WOOD_DOUBLE_STEP:
            //</editor-fold>
                return false;
            default:
                return true;
        }
    }

    /**
     * Checks if this Material can be interacted with.
     *
     * Interactable materials include those with functionality when they are
     * interacted with by a player such as chests, furnaces, etc.
     *
     * Some blocks such as piston heads and stairs are considered interactable
     * though may not perform any additional functionality.
     *
     * Note that the interactability of some materials may be dependant on their
     * state as well. This method will return true if there is at least one
     * state in which additional interact handling is performed for the
     * material.
     *
     * @return true if this material can be interacted with.
     * @deprecated This method is not comprehensive and does not accurately reflect what block types are
     * interactable. Many "interactions" are defined on the item not block, and many are conditional on some other world state
     * checks being true.
     */
    @Deprecated // Paper
    public boolean isInteractable() {
        switch (this) {
            // <editor-fold defaultstate="collapsed" desc="isInteractable">
            // Paper start - Generated/Material#isInteractable
            // @GeneratedFrom 1.20.4
            case ACACIA_BUTTON:
            case ACACIA_DOOR:
            case ACACIA_FENCE:
            case ACACIA_FENCE_GATE:
            case ACACIA_HANGING_SIGN:
            case ACACIA_SIGN:
            case ACACIA_TRAPDOOR:
            case ACACIA_WALL_HANGING_SIGN:
            case ACACIA_WALL_SIGN:
            case ANVIL:
            case BAMBOO_BUTTON:
            case BAMBOO_DOOR:
            case BAMBOO_FENCE:
            case BAMBOO_FENCE_GATE:
            case BAMBOO_HANGING_SIGN:
            case BAMBOO_SIGN:
            case BAMBOO_TRAPDOOR:
            case BAMBOO_WALL_HANGING_SIGN:
            case BAMBOO_WALL_SIGN:
            case BARREL:
            case BEACON:
            case BEEHIVE:
            case BEE_NEST:
            case BELL:
            case BIRCH_BUTTON:
            case BIRCH_DOOR:
            case BIRCH_FENCE:
            case BIRCH_FENCE_GATE:
            case BIRCH_HANGING_SIGN:
            case BIRCH_SIGN:
            case BIRCH_TRAPDOOR:
            case BIRCH_WALL_HANGING_SIGN:
            case BIRCH_WALL_SIGN:
            case BLACK_BED:
            case BLACK_CANDLE:
            case BLACK_CANDLE_CAKE:
            case BLACK_SHULKER_BOX:
            case BLAST_FURNACE:
            case BLUE_BED:
            case BLUE_CANDLE:
            case BLUE_CANDLE_CAKE:
            case BLUE_SHULKER_BOX:
            case BREWING_STAND:
            case BROWN_BED:
            case BROWN_CANDLE:
            case BROWN_CANDLE_CAKE:
            case BROWN_SHULKER_BOX:
            case CAKE:
            case CAMPFIRE:
            case CANDLE:
            case CANDLE_CAKE:
            case CARTOGRAPHY_TABLE:
            case CAULDRON:
            case CAVE_VINES:
            case CAVE_VINES_PLANT:
            case CHAIN_COMMAND_BLOCK:
            case CHERRY_BUTTON:
            case CHERRY_DOOR:
            case CHERRY_FENCE:
            case CHERRY_FENCE_GATE:
            case CHERRY_HANGING_SIGN:
            case CHERRY_SIGN:
            case CHERRY_TRAPDOOR:
            case CHERRY_WALL_HANGING_SIGN:
            case CHERRY_WALL_SIGN:
            case CHEST:
            case CHIPPED_ANVIL:
            case CHISELED_BOOKSHELF:
            case COMMAND_BLOCK:
            case COMPARATOR:
            case COMPOSTER:
            case COPPER_DOOR:
            case COPPER_TRAPDOOR:
            case CRAFTER:
            case CRAFTING_TABLE:
            case CRIMSON_BUTTON:
            case CRIMSON_DOOR:
            case CRIMSON_FENCE:
            case CRIMSON_FENCE_GATE:
            case CRIMSON_HANGING_SIGN:
            case CRIMSON_SIGN:
            case CRIMSON_TRAPDOOR:
            case CRIMSON_WALL_HANGING_SIGN:
            case CRIMSON_WALL_SIGN:
            case CYAN_BED:
            case CYAN_CANDLE:
            case CYAN_CANDLE_CAKE:
            case CYAN_SHULKER_BOX:
            case DAMAGED_ANVIL:
            case DARK_OAK_BUTTON:
            case DARK_OAK_DOOR:
            case DARK_OAK_FENCE:
            case DARK_OAK_FENCE_GATE:
            case DARK_OAK_HANGING_SIGN:
            case DARK_OAK_SIGN:
            case DARK_OAK_TRAPDOOR:
            case DARK_OAK_WALL_HANGING_SIGN:
            case DARK_OAK_WALL_SIGN:
            case DAYLIGHT_DETECTOR:
            case DECORATED_POT:
            case DEEPSLATE_REDSTONE_ORE:
            case DISPENSER:
            case DRAGON_EGG:
            case DROPPER:
            case ENCHANTING_TABLE:
            case ENDER_CHEST:
            case EXPOSED_COPPER_DOOR:
            case EXPOSED_COPPER_TRAPDOOR:
            case FLETCHING_TABLE:
            case FLOWER_POT:
            case FURNACE:
            case GRAY_BED:
            case GRAY_CANDLE:
            case GRAY_CANDLE_CAKE:
            case GRAY_SHULKER_BOX:
            case GREEN_BED:
            case GREEN_CANDLE:
            case GREEN_CANDLE_CAKE:
            case GREEN_SHULKER_BOX:
            case GRINDSTONE:
            case HOPPER:
            case IRON_DOOR:
            case IRON_TRAPDOOR:
            case JIGSAW:
            case JUKEBOX:
            case JUNGLE_BUTTON:
            case JUNGLE_DOOR:
            case JUNGLE_FENCE:
            case JUNGLE_FENCE_GATE:
            case JUNGLE_HANGING_SIGN:
            case JUNGLE_SIGN:
            case JUNGLE_TRAPDOOR:
            case JUNGLE_WALL_HANGING_SIGN:
            case JUNGLE_WALL_SIGN:
            case LAVA_CAULDRON:
            case LECTERN:
            case LEVER:
            case LIGHT:
            case LIGHT_BLUE_BED:
            case LIGHT_BLUE_CANDLE:
            case LIGHT_BLUE_CANDLE_CAKE:
            case LIGHT_BLUE_SHULKER_BOX:
            case LIGHT_GRAY_BED:
            case LIGHT_GRAY_CANDLE:
            case LIGHT_GRAY_CANDLE_CAKE:
            case LIGHT_GRAY_SHULKER_BOX:
            case LIME_BED:
            case LIME_CANDLE:
            case LIME_CANDLE_CAKE:
            case LIME_SHULKER_BOX:
            case LOOM:
            case MAGENTA_BED:
            case MAGENTA_CANDLE:
            case MAGENTA_CANDLE_CAKE:
            case MAGENTA_SHULKER_BOX:
            case MANGROVE_BUTTON:
            case MANGROVE_DOOR:
            case MANGROVE_FENCE:
            case MANGROVE_FENCE_GATE:
            case MANGROVE_HANGING_SIGN:
            case MANGROVE_SIGN:
            case MANGROVE_TRAPDOOR:
            case MANGROVE_WALL_HANGING_SIGN:
            case MANGROVE_WALL_SIGN:
            case MOVING_PISTON:
            case NETHER_BRICK_FENCE:
            case NOTE_BLOCK:
            case OAK_BUTTON:
            case OAK_DOOR:
            case OAK_FENCE:
            case OAK_FENCE_GATE:
            case OAK_HANGING_SIGN:
            case OAK_SIGN:
            case OAK_TRAPDOOR:
            case OAK_WALL_HANGING_SIGN:
            case OAK_WALL_SIGN:
            case ORANGE_BED:
            case ORANGE_CANDLE:
            case ORANGE_CANDLE_CAKE:
            case ORANGE_SHULKER_BOX:
            case OXIDIZED_COPPER_DOOR:
            case OXIDIZED_COPPER_TRAPDOOR:
            case PINK_BED:
            case PINK_CANDLE:
            case PINK_CANDLE_CAKE:
            case PINK_SHULKER_BOX:
            case POLISHED_BLACKSTONE_BUTTON:
            case POTTED_ACACIA_SAPLING:
            case POTTED_ALLIUM:
            case POTTED_AZALEA_BUSH:
            case POTTED_AZURE_BLUET:
            case POTTED_BAMBOO:
            case POTTED_BIRCH_SAPLING:
            case POTTED_BLUE_ORCHID:
            case POTTED_BROWN_MUSHROOM:
            case POTTED_CACTUS:
            case POTTED_CHERRY_SAPLING:
            case POTTED_CORNFLOWER:
            case POTTED_CRIMSON_FUNGUS:
            case POTTED_CRIMSON_ROOTS:
            case POTTED_DANDELION:
            case POTTED_DARK_OAK_SAPLING:
            case POTTED_DEAD_BUSH:
            case POTTED_FERN:
            case POTTED_FLOWERING_AZALEA_BUSH:
            case POTTED_JUNGLE_SAPLING:
            case POTTED_LILY_OF_THE_VALLEY:
            case POTTED_MANGROVE_PROPAGULE:
            case POTTED_OAK_SAPLING:
            case POTTED_ORANGE_TULIP:
            case POTTED_OXEYE_DAISY:
            case POTTED_PINK_TULIP:
            case POTTED_POPPY:
            case POTTED_RED_MUSHROOM:
            case POTTED_RED_TULIP:
            case POTTED_SPRUCE_SAPLING:
            case POTTED_TORCHFLOWER:
            case POTTED_WARPED_FUNGUS:
            case POTTED_WARPED_ROOTS:
            case POTTED_WHITE_TULIP:
            case POTTED_WITHER_ROSE:
            case POWDER_SNOW_CAULDRON:
            case PUMPKIN:
            case PURPLE_BED:
            case PURPLE_CANDLE:
            case PURPLE_CANDLE_CAKE:
            case PURPLE_SHULKER_BOX:
            case REDSTONE_ORE:
            case REDSTONE_WIRE:
            case RED_BED:
            case RED_CANDLE:
            case RED_CANDLE_CAKE:
            case RED_SHULKER_BOX:
            case REPEATER:
            case REPEATING_COMMAND_BLOCK:
            case RESPAWN_ANCHOR:
            case SHULKER_BOX:
            case SMITHING_TABLE:
            case SMOKER:
            case SOUL_CAMPFIRE:
            case SPRUCE_BUTTON:
            case SPRUCE_DOOR:
            case SPRUCE_FENCE:
            case SPRUCE_FENCE_GATE:
            case SPRUCE_HANGING_SIGN:
            case SPRUCE_SIGN:
            case SPRUCE_TRAPDOOR:
            case SPRUCE_WALL_HANGING_SIGN:
            case SPRUCE_WALL_SIGN:
            case STONECUTTER:
            case STONE_BUTTON:
            case STRUCTURE_BLOCK:
            case SWEET_BERRY_BUSH:
            case TNT:
            case TRAPPED_CHEST:
            case WARPED_BUTTON:
            case WARPED_DOOR:
            case WARPED_FENCE:
            case WARPED_FENCE_GATE:
            case WARPED_HANGING_SIGN:
            case WARPED_SIGN:
            case WARPED_TRAPDOOR:
            case WARPED_WALL_HANGING_SIGN:
            case WARPED_WALL_SIGN:
            case WATER_CAULDRON:
            case WAXED_COPPER_DOOR:
            case WAXED_COPPER_TRAPDOOR:
            case WAXED_EXPOSED_COPPER_DOOR:
            case WAXED_EXPOSED_COPPER_TRAPDOOR:
            case WAXED_OXIDIZED_COPPER_DOOR:
            case WAXED_OXIDIZED_COPPER_TRAPDOOR:
            case WAXED_WEATHERED_COPPER_DOOR:
            case WAXED_WEATHERED_COPPER_TRAPDOOR:
            case WEATHERED_COPPER_DOOR:
            case WEATHERED_COPPER_TRAPDOOR:
            case WHITE_BED:
            case WHITE_CANDLE:
            case WHITE_CANDLE_CAKE:
            case WHITE_SHULKER_BOX:
            case YELLOW_BED:
            case YELLOW_CANDLE:
            case YELLOW_CANDLE_CAKE:
            case YELLOW_SHULKER_BOX:
            // Paper end - Generated/Material#isInteractable
                // </editor-fold>
                return true;
            default:
                return false;
        }
    }

    /**
     * Obtains the block's hardness level (also known as "strength").
     * <br>
     * This number is used to calculate the time required to break each block.
     * <br>
     * Only available when {@link #isBlock()} is true.
     *
     * @return the hardness of that material.
     */
    public float getHardness() {
        Preconditions.checkArgument(isBlock(), "The Material is not a block!");
        switch (this) {
            // <editor-fold defaultstate="collapsed" desc="getBlockHardness">
            // Paper start - Generated/Material#getHardness
            // @GeneratedFrom 1.20.4
            case BARRIER:
            case BEDROCK:
            case CHAIN_COMMAND_BLOCK:
            case COMMAND_BLOCK:
            case END_GATEWAY:
            case END_PORTAL:
            case END_PORTAL_FRAME:
            case JIGSAW:
            case LIGHT:
            case MOVING_PISTON:
            case NETHER_PORTAL:
            case REPEATING_COMMAND_BLOCK:
            case STRUCTURE_BLOCK:
                return -1.0F;
            case BIG_DRIPLEAF:
            case BIG_DRIPLEAF_STEM:
            case BLACK_CANDLE:
            case BLACK_CARPET:
            case BLUE_CANDLE:
            case BLUE_CARPET:
            case BROWN_CANDLE:
            case BROWN_CARPET:
            case CANDLE:
            case CYAN_CANDLE:
            case CYAN_CARPET:
            case GRAY_CANDLE:
            case GRAY_CARPET:
            case GREEN_CANDLE:
            case GREEN_CARPET:
            case LIGHT_BLUE_CANDLE:
            case LIGHT_BLUE_CARPET:
            case LIGHT_GRAY_CANDLE:
            case LIGHT_GRAY_CARPET:
            case LIME_CANDLE:
            case LIME_CARPET:
            case MAGENTA_CANDLE:
            case MAGENTA_CARPET:
            case MOSS_BLOCK:
            case MOSS_CARPET:
            case ORANGE_CANDLE:
            case ORANGE_CARPET:
            case PINK_CANDLE:
            case PINK_CARPET:
            case PURPLE_CANDLE:
            case PURPLE_CARPET:
            case RED_CANDLE:
            case RED_CARPET:
            case SNOW:
            case WHITE_CANDLE:
            case WHITE_CARPET:
            case YELLOW_CANDLE:
            case YELLOW_CARPET:
                return 0.1F;
            case ACACIA_LEAVES:
            case AZALEA_LEAVES:
            case BIRCH_LEAVES:
            case BLACK_BED:
            case BLUE_BED:
            case BROWN_BED:
            case BROWN_MUSHROOM_BLOCK:
            case CHERRY_LEAVES:
            case COCOA:
            case CYAN_BED:
            case DARK_OAK_LEAVES:
            case DAYLIGHT_DETECTOR:
            case FLOWERING_AZALEA_LEAVES:
            case GLOW_LICHEN:
            case GRAY_BED:
            case GREEN_BED:
            case JUNGLE_LEAVES:
            case LIGHT_BLUE_BED:
            case LIGHT_GRAY_BED:
            case LIME_BED:
            case MAGENTA_BED:
            case MANGROVE_LEAVES:
            case MUSHROOM_STEM:
            case OAK_LEAVES:
            case ORANGE_BED:
            case PINK_BED:
            case PURPLE_BED:
            case RED_BED:
            case RED_MUSHROOM_BLOCK:
            case SCULK:
            case SCULK_VEIN:
            case SNOW_BLOCK:
            case SPRUCE_LEAVES:
            case VINE:
            case WHITE_BED:
            case YELLOW_BED:
                return 0.2F;
            case POWDER_SNOW:
            case SUSPICIOUS_GRAVEL:
            case SUSPICIOUS_SAND:
                return 0.25F;
            case BEE_NEST:
            case BLACK_STAINED_GLASS:
            case BLACK_STAINED_GLASS_PANE:
            case BLUE_STAINED_GLASS:
            case BLUE_STAINED_GLASS_PANE:
            case BROWN_STAINED_GLASS:
            case BROWN_STAINED_GLASS_PANE:
            case CYAN_STAINED_GLASS:
            case CYAN_STAINED_GLASS_PANE:
            case GLASS:
            case GLASS_PANE:
            case GLOWSTONE:
            case GRAY_STAINED_GLASS:
            case GRAY_STAINED_GLASS_PANE:
            case GREEN_STAINED_GLASS:
            case GREEN_STAINED_GLASS_PANE:
            case LIGHT_BLUE_STAINED_GLASS:
            case LIGHT_BLUE_STAINED_GLASS_PANE:
            case LIGHT_GRAY_STAINED_GLASS:
            case LIGHT_GRAY_STAINED_GLASS_PANE:
            case LIME_STAINED_GLASS:
            case LIME_STAINED_GLASS_PANE:
            case MAGENTA_STAINED_GLASS:
            case MAGENTA_STAINED_GLASS_PANE:
            case OCHRE_FROGLIGHT:
            case ORANGE_STAINED_GLASS:
            case ORANGE_STAINED_GLASS_PANE:
            case PEARLESCENT_FROGLIGHT:
            case PINK_STAINED_GLASS:
            case PINK_STAINED_GLASS_PANE:
            case PURPLE_STAINED_GLASS:
            case PURPLE_STAINED_GLASS_PANE:
            case REDSTONE_LAMP:
            case RED_STAINED_GLASS:
            case RED_STAINED_GLASS_PANE:
            case SEA_LANTERN:
            case TINTED_GLASS:
            case VERDANT_FROGLIGHT:
            case WHITE_STAINED_GLASS:
            case WHITE_STAINED_GLASS_PANE:
            case YELLOW_STAINED_GLASS:
            case YELLOW_STAINED_GLASS_PANE:
                return 0.3F;
            case CACTUS:
            case CHORUS_FLOWER:
            case CHORUS_PLANT:
            case CRIMSON_NYLIUM:
            case LADDER:
            case NETHERRACK:
            case WARPED_NYLIUM:
                return 0.4F;
            case ACACIA_BUTTON:
            case ACACIA_PRESSURE_PLATE:
            case BAMBOO_BUTTON:
            case BAMBOO_PRESSURE_PLATE:
            case BIRCH_BUTTON:
            case BIRCH_PRESSURE_PLATE:
            case BLACK_CANDLE_CAKE:
            case BLACK_CONCRETE_POWDER:
            case BLUE_CANDLE_CAKE:
            case BLUE_CONCRETE_POWDER:
            case BREWING_STAND:
            case BROWN_CANDLE_CAKE:
            case BROWN_CONCRETE_POWDER:
            case CAKE:
            case CANDLE_CAKE:
            case CHERRY_BUTTON:
            case CHERRY_PRESSURE_PLATE:
            case COARSE_DIRT:
            case CRIMSON_BUTTON:
            case CRIMSON_PRESSURE_PLATE:
            case CYAN_CANDLE_CAKE:
            case CYAN_CONCRETE_POWDER:
            case DARK_OAK_BUTTON:
            case DARK_OAK_PRESSURE_PLATE:
            case DIRT:
            case DRIED_KELP_BLOCK:
            case FROSTED_ICE:
            case GRAY_CANDLE_CAKE:
            case GRAY_CONCRETE_POWDER:
            case GREEN_CANDLE_CAKE:
            case GREEN_CONCRETE_POWDER:
            case HAY_BLOCK:
            case HEAVY_WEIGHTED_PRESSURE_PLATE:
            case ICE:
            case JUNGLE_BUTTON:
            case JUNGLE_PRESSURE_PLATE:
            case LEVER:
            case LIGHT_BLUE_CANDLE_CAKE:
            case LIGHT_BLUE_CONCRETE_POWDER:
            case LIGHT_GRAY_CANDLE_CAKE:
            case LIGHT_GRAY_CONCRETE_POWDER:
            case LIGHT_WEIGHTED_PRESSURE_PLATE:
            case LIME_CANDLE_CAKE:
            case LIME_CONCRETE_POWDER:
            case MAGENTA_CANDLE_CAKE:
            case MAGENTA_CONCRETE_POWDER:
            case MAGMA_BLOCK:
            case MANGROVE_BUTTON:
            case MANGROVE_PRESSURE_PLATE:
            case MUD:
            case OAK_BUTTON:
            case OAK_PRESSURE_PLATE:
            case ORANGE_CANDLE_CAKE:
            case ORANGE_CONCRETE_POWDER:
            case PACKED_ICE:
            case PINK_CANDLE_CAKE:
            case PINK_CONCRETE_POWDER:
            case PODZOL:
            case POLISHED_BLACKSTONE_BUTTON:
            case POLISHED_BLACKSTONE_PRESSURE_PLATE:
            case PURPLE_CANDLE_CAKE:
            case PURPLE_CONCRETE_POWDER:
            case RED_CANDLE_CAKE:
            case RED_CONCRETE_POWDER:
            case RED_SAND:
            case ROOTED_DIRT:
            case SAND:
            case SNIFFER_EGG:
            case SOUL_SAND:
            case SOUL_SOIL:
            case SPRUCE_BUTTON:
            case SPRUCE_PRESSURE_PLATE:
            case STONE_BUTTON:
            case STONE_PRESSURE_PLATE:
            case TARGET:
            case TURTLE_EGG:
            case WARPED_BUTTON:
            case WARPED_PRESSURE_PLATE:
            case WHITE_CANDLE_CAKE:
            case WHITE_CONCRETE_POWDER:
            case YELLOW_CANDLE_CAKE:
            case YELLOW_CONCRETE_POWDER:
                return 0.5F;
            case BEEHIVE:
            case CLAY:
            case COMPOSTER:
            case FARMLAND:
            case GRASS_BLOCK:
            case GRAVEL:
            case HONEYCOMB_BLOCK:
            case MYCELIUM:
            case SPONGE:
            case WET_SPONGE:
                return 0.6F;
            case DIRT_PATH:
                return 0.65F;
            case ACTIVATOR_RAIL:
            case DETECTOR_RAIL:
            case MANGROVE_ROOTS:
            case MUDDY_MANGROVE_ROOTS:
            case POWERED_RAIL:
            case RAIL:
                return 0.7F;
            case CALCITE:
            case INFESTED_CHISELED_STONE_BRICKS:
            case INFESTED_CRACKED_STONE_BRICKS:
            case INFESTED_MOSSY_STONE_BRICKS:
            case INFESTED_STONE:
            case INFESTED_STONE_BRICKS:
                return 0.75F;
            case BLACK_WOOL:
            case BLUE_WOOL:
            case BROWN_WOOL:
            case CHISELED_QUARTZ_BLOCK:
            case CHISELED_RED_SANDSTONE:
            case CHISELED_SANDSTONE:
            case CUT_RED_SANDSTONE:
            case CUT_SANDSTONE:
            case CYAN_WOOL:
            case GRAY_WOOL:
            case GREEN_WOOL:
            case LIGHT_BLUE_WOOL:
            case LIGHT_GRAY_WOOL:
            case LIME_WOOL:
            case MAGENTA_WOOL:
            case NOTE_BLOCK:
            case ORANGE_WOOL:
            case PINK_WOOL:
            case PURPLE_WOOL:
            case QUARTZ_BLOCK:
            case QUARTZ_BRICKS:
            case QUARTZ_PILLAR:
            case QUARTZ_STAIRS:
            case RED_SANDSTONE:
            case RED_SANDSTONE_STAIRS:
            case RED_SANDSTONE_WALL:
            case RED_WOOL:
            case SANDSTONE:
            case SANDSTONE_STAIRS:
            case SANDSTONE_WALL:
            case WHITE_WOOL:
            case YELLOW_WOOL:
                return 0.8F;
            case ACACIA_HANGING_SIGN:
            case ACACIA_SIGN:
            case ACACIA_WALL_HANGING_SIGN:
            case ACACIA_WALL_SIGN:
            case BAMBOO:
            case BAMBOO_HANGING_SIGN:
            case BAMBOO_SAPLING:
            case BAMBOO_SIGN:
            case BAMBOO_WALL_HANGING_SIGN:
            case BAMBOO_WALL_SIGN:
            case BIRCH_HANGING_SIGN:
            case BIRCH_SIGN:
            case BIRCH_WALL_HANGING_SIGN:
            case BIRCH_WALL_SIGN:
            case BLACK_BANNER:
            case BLACK_WALL_BANNER:
            case BLUE_BANNER:
            case BLUE_WALL_BANNER:
            case BROWN_BANNER:
            case BROWN_WALL_BANNER:
            case CARVED_PUMPKIN:
            case CHERRY_HANGING_SIGN:
            case CHERRY_SIGN:
            case CHERRY_WALL_HANGING_SIGN:
            case CHERRY_WALL_SIGN:
            case CREEPER_HEAD:
            case CREEPER_WALL_HEAD:
            case CRIMSON_HANGING_SIGN:
            case CRIMSON_SIGN:
            case CRIMSON_WALL_HANGING_SIGN:
            case CRIMSON_WALL_SIGN:
            case CYAN_BANNER:
            case CYAN_WALL_BANNER:
            case DARK_OAK_HANGING_SIGN:
            case DARK_OAK_SIGN:
            case DARK_OAK_WALL_HANGING_SIGN:
            case DARK_OAK_WALL_SIGN:
            case DRAGON_HEAD:
            case DRAGON_WALL_HEAD:
            case GRAY_BANNER:
            case GRAY_WALL_BANNER:
            case GREEN_BANNER:
            case GREEN_WALL_BANNER:
            case INFESTED_COBBLESTONE:
            case JACK_O_LANTERN:
            case JUNGLE_HANGING_SIGN:
            case JUNGLE_SIGN:
            case JUNGLE_WALL_HANGING_SIGN:
            case JUNGLE_WALL_SIGN:
            case LIGHT_BLUE_BANNER:
            case LIGHT_BLUE_WALL_BANNER:
            case LIGHT_GRAY_BANNER:
            case LIGHT_GRAY_WALL_BANNER:
            case LIME_BANNER:
            case LIME_WALL_BANNER:
            case MAGENTA_BANNER:
            case MAGENTA_WALL_BANNER:
            case MANGROVE_HANGING_SIGN:
            case MANGROVE_SIGN:
            case MANGROVE_WALL_HANGING_SIGN:
            case MANGROVE_WALL_SIGN:
            case MELON:
            case NETHER_WART_BLOCK:
            case OAK_HANGING_SIGN:
            case OAK_SIGN:
            case OAK_WALL_HANGING_SIGN:
            case OAK_WALL_SIGN:
            case ORANGE_BANNER:
            case ORANGE_WALL_BANNER:
            case PACKED_MUD:
            case PIGLIN_HEAD:
            case PIGLIN_WALL_HEAD:
            case PINK_BANNER:
            case PINK_WALL_BANNER:
            case PLAYER_HEAD:
            case PLAYER_WALL_HEAD:
            case PUMPKIN:
            case PURPLE_BANNER:
            case PURPLE_WALL_BANNER:
            case RED_BANNER:
            case RED_WALL_BANNER:
            case SHROOMLIGHT:
            case SKELETON_SKULL:
            case SKELETON_WALL_SKULL:
            case SPRUCE_HANGING_SIGN:
            case SPRUCE_SIGN:
            case SPRUCE_WALL_HANGING_SIGN:
            case SPRUCE_WALL_SIGN:
            case WARPED_HANGING_SIGN:
            case WARPED_SIGN:
            case WARPED_WALL_HANGING_SIGN:
            case WARPED_WALL_SIGN:
            case WARPED_WART_BLOCK:
            case WHITE_BANNER:
            case WHITE_WALL_BANNER:
            case WITHER_SKELETON_SKULL:
            case WITHER_SKELETON_WALL_SKULL:
            case YELLOW_BANNER:
            case YELLOW_WALL_BANNER:
            case ZOMBIE_HEAD:
            case ZOMBIE_WALL_HEAD:
                return 1.0F;
            case BASALT:
            case BLACK_TERRACOTTA:
            case BLUE_TERRACOTTA:
            case BROWN_TERRACOTTA:
            case CYAN_TERRACOTTA:
            case GRAY_TERRACOTTA:
            case GREEN_TERRACOTTA:
            case LIGHT_BLUE_TERRACOTTA:
            case LIGHT_GRAY_TERRACOTTA:
            case LIME_TERRACOTTA:
            case MAGENTA_TERRACOTTA:
            case ORANGE_TERRACOTTA:
            case PINK_TERRACOTTA:
            case POLISHED_BASALT:
            case PURPLE_TERRACOTTA:
            case RED_TERRACOTTA:
            case SMOOTH_BASALT:
            case TERRACOTTA:
            case WHITE_TERRACOTTA:
            case YELLOW_TERRACOTTA:
                return 1.25F;
            case BLACK_GLAZED_TERRACOTTA:
            case BLUE_GLAZED_TERRACOTTA:
            case BROWN_GLAZED_TERRACOTTA:
            case CYAN_GLAZED_TERRACOTTA:
            case GRAY_GLAZED_TERRACOTTA:
            case GREEN_GLAZED_TERRACOTTA:
            case LIGHT_BLUE_GLAZED_TERRACOTTA:
            case LIGHT_GRAY_GLAZED_TERRACOTTA:
            case LIME_GLAZED_TERRACOTTA:
            case MAGENTA_GLAZED_TERRACOTTA:
            case ORANGE_GLAZED_TERRACOTTA:
            case PINK_GLAZED_TERRACOTTA:
            case PURPLE_GLAZED_TERRACOTTA:
            case RED_GLAZED_TERRACOTTA:
            case WHITE_GLAZED_TERRACOTTA:
            case YELLOW_GLAZED_TERRACOTTA:
                return 1.4F;
            case AMETHYST_BLOCK:
            case AMETHYST_CLUSTER:
            case ANDESITE:
            case ANDESITE_SLAB:
            case ANDESITE_STAIRS:
            case ANDESITE_WALL:
            case BLACKSTONE:
            case BLACKSTONE_STAIRS:
            case BLACKSTONE_WALL:
            case BOOKSHELF:
            case BRAIN_CORAL_BLOCK:
            case BUBBLE_CORAL_BLOCK:
            case BUDDING_AMETHYST:
            case CALIBRATED_SCULK_SENSOR:
            case CHISELED_BOOKSHELF:
            case CHISELED_POLISHED_BLACKSTONE:
            case CHISELED_STONE_BRICKS:
            case CHISELED_TUFF:
            case CHISELED_TUFF_BRICKS:
            case CRACKED_POLISHED_BLACKSTONE_BRICKS:
            case CRACKED_STONE_BRICKS:
            case CRAFTER:
            case DARK_PRISMARINE:
            case DARK_PRISMARINE_SLAB:
            case DARK_PRISMARINE_STAIRS:
            case DEAD_BRAIN_CORAL_BLOCK:
            case DEAD_BUBBLE_CORAL_BLOCK:
            case DEAD_FIRE_CORAL_BLOCK:
            case DEAD_HORN_CORAL_BLOCK:
            case DEAD_TUBE_CORAL_BLOCK:
            case DIORITE:
            case DIORITE_SLAB:
            case DIORITE_STAIRS:
            case DIORITE_WALL:
            case DRIPSTONE_BLOCK:
            case FIRE_CORAL_BLOCK:
            case GILDED_BLACKSTONE:
            case GRANITE:
            case GRANITE_SLAB:
            case GRANITE_STAIRS:
            case GRANITE_WALL:
            case HORN_CORAL_BLOCK:
            case INFESTED_DEEPSLATE:
            case LARGE_AMETHYST_BUD:
            case MEDIUM_AMETHYST_BUD:
            case MOSSY_STONE_BRICKS:
            case MOSSY_STONE_BRICK_SLAB:
            case MOSSY_STONE_BRICK_STAIRS:
            case MOSSY_STONE_BRICK_WALL:
            case MUD_BRICKS:
            case MUD_BRICK_SLAB:
            case MUD_BRICK_STAIRS:
            case MUD_BRICK_WALL:
            case PISTON:
            case PISTON_HEAD:
            case POINTED_DRIPSTONE:
            case POLISHED_ANDESITE:
            case POLISHED_ANDESITE_SLAB:
            case POLISHED_ANDESITE_STAIRS:
            case POLISHED_BLACKSTONE_BRICKS:
            case POLISHED_BLACKSTONE_BRICK_STAIRS:
            case POLISHED_BLACKSTONE_BRICK_WALL:
            case POLISHED_DIORITE:
            case POLISHED_DIORITE_SLAB:
            case POLISHED_DIORITE_STAIRS:
            case POLISHED_GRANITE:
            case POLISHED_GRANITE_SLAB:
            case POLISHED_GRANITE_STAIRS:
            case POLISHED_TUFF:
            case POLISHED_TUFF_SLAB:
            case POLISHED_TUFF_STAIRS:
            case POLISHED_TUFF_WALL:
            case PRISMARINE:
            case PRISMARINE_BRICKS:
            case PRISMARINE_BRICK_SLAB:
            case PRISMARINE_BRICK_STAIRS:
            case PRISMARINE_SLAB:
            case PRISMARINE_STAIRS:
            case PRISMARINE_WALL:
            case PURPUR_BLOCK:
            case PURPUR_PILLAR:
            case PURPUR_STAIRS:
            case SCULK_SENSOR:
            case SMALL_AMETHYST_BUD:
            case STICKY_PISTON:
            case STONE:
            case STONE_BRICKS:
            case STONE_BRICK_STAIRS:
            case STONE_BRICK_WALL:
            case STONE_STAIRS:
            case TUBE_CORAL_BLOCK:
            case TUFF:
            case TUFF_BRICKS:
            case TUFF_BRICK_SLAB:
            case TUFF_BRICK_STAIRS:
            case TUFF_BRICK_WALL:
            case TUFF_SLAB:
            case TUFF_STAIRS:
            case TUFF_WALL:
                return 1.5F;
            case BLACK_CONCRETE:
            case BLUE_CONCRETE:
            case BROWN_CONCRETE:
            case CYAN_CONCRETE:
            case GRAY_CONCRETE:
            case GREEN_CONCRETE:
            case LIGHT_BLUE_CONCRETE:
            case LIGHT_GRAY_CONCRETE:
            case LIME_CONCRETE:
            case MAGENTA_CONCRETE:
            case ORANGE_CONCRETE:
            case PINK_CONCRETE:
            case PURPLE_CONCRETE:
            case RED_CONCRETE:
            case WHITE_CONCRETE:
            case YELLOW_CONCRETE:
                return 1.8F;
            case ACACIA_FENCE:
            case ACACIA_FENCE_GATE:
            case ACACIA_LOG:
            case ACACIA_PLANKS:
            case ACACIA_SLAB:
            case ACACIA_STAIRS:
            case ACACIA_WOOD:
            case BAMBOO_BLOCK:
            case BAMBOO_FENCE:
            case BAMBOO_FENCE_GATE:
            case BAMBOO_MOSAIC:
            case BAMBOO_MOSAIC_SLAB:
            case BAMBOO_MOSAIC_STAIRS:
            case BAMBOO_PLANKS:
            case BAMBOO_SLAB:
            case BAMBOO_STAIRS:
            case BIRCH_FENCE:
            case BIRCH_FENCE_GATE:
            case BIRCH_LOG:
            case BIRCH_PLANKS:
            case BIRCH_SLAB:
            case BIRCH_STAIRS:
            case BIRCH_WOOD:
            case BLACKSTONE_SLAB:
            case BLACK_SHULKER_BOX:
            case BLUE_SHULKER_BOX:
            case BONE_BLOCK:
            case BRICKS:
            case BRICK_SLAB:
            case BRICK_STAIRS:
            case BRICK_WALL:
            case BROWN_SHULKER_BOX:
            case CAMPFIRE:
            case CAULDRON:
            case CHERRY_FENCE:
            case CHERRY_FENCE_GATE:
            case CHERRY_LOG:
            case CHERRY_PLANKS:
            case CHERRY_SLAB:
            case CHERRY_STAIRS:
            case CHERRY_WOOD:
            case CHISELED_NETHER_BRICKS:
            case COBBLESTONE:
            case COBBLESTONE_SLAB:
            case COBBLESTONE_STAIRS:
            case COBBLESTONE_WALL:
            case CRACKED_NETHER_BRICKS:
            case CRIMSON_FENCE:
            case CRIMSON_FENCE_GATE:
            case CRIMSON_HYPHAE:
            case CRIMSON_PLANKS:
            case CRIMSON_SLAB:
            case CRIMSON_STAIRS:
            case CRIMSON_STEM:
            case CUT_RED_SANDSTONE_SLAB:
            case CUT_SANDSTONE_SLAB:
            case CYAN_SHULKER_BOX:
            case DARK_OAK_FENCE:
            case DARK_OAK_FENCE_GATE:
            case DARK_OAK_LOG:
            case DARK_OAK_PLANKS:
            case DARK_OAK_SLAB:
            case DARK_OAK_STAIRS:
            case DARK_OAK_WOOD:
            case GRAY_SHULKER_BOX:
            case GREEN_SHULKER_BOX:
            case GRINDSTONE:
            case JUKEBOX:
            case JUNGLE_FENCE:
            case JUNGLE_FENCE_GATE:
            case JUNGLE_LOG:
            case JUNGLE_PLANKS:
            case JUNGLE_SLAB:
            case JUNGLE_STAIRS:
            case JUNGLE_WOOD:
            case LAVA_CAULDRON:
            case LIGHT_BLUE_SHULKER_BOX:
            case LIGHT_GRAY_SHULKER_BOX:
            case LIME_SHULKER_BOX:
            case MAGENTA_SHULKER_BOX:
            case MANGROVE_FENCE:
            case MANGROVE_FENCE_GATE:
            case MANGROVE_LOG:
            case MANGROVE_PLANKS:
            case MANGROVE_SLAB:
            case MANGROVE_STAIRS:
            case MANGROVE_WOOD:
            case MOSSY_COBBLESTONE:
            case MOSSY_COBBLESTONE_SLAB:
            case MOSSY_COBBLESTONE_STAIRS:
            case MOSSY_COBBLESTONE_WALL:
            case NETHER_BRICKS:
            case NETHER_BRICK_FENCE:
            case NETHER_BRICK_SLAB:
            case NETHER_BRICK_STAIRS:
            case NETHER_BRICK_WALL:
            case OAK_FENCE:
            case OAK_FENCE_GATE:
            case OAK_LOG:
            case OAK_PLANKS:
            case OAK_SLAB:
            case OAK_STAIRS:
            case OAK_WOOD:
            case ORANGE_SHULKER_BOX:
            case PETRIFIED_OAK_SLAB:
            case PINK_SHULKER_BOX:
            case POLISHED_BLACKSTONE:
            case POLISHED_BLACKSTONE_BRICK_SLAB:
            case POLISHED_BLACKSTONE_SLAB:
            case POLISHED_BLACKSTONE_STAIRS:
            case POLISHED_BLACKSTONE_WALL:
            case POWDER_SNOW_CAULDRON:
            case PURPLE_SHULKER_BOX:
            case PURPUR_SLAB:
            case QUARTZ_SLAB:
            case RED_NETHER_BRICKS:
            case RED_NETHER_BRICK_SLAB:
            case RED_NETHER_BRICK_STAIRS:
            case RED_NETHER_BRICK_WALL:
            case RED_SANDSTONE_SLAB:
            case RED_SHULKER_BOX:
            case SANDSTONE_SLAB:
            case SHULKER_BOX:
            case SMOOTH_QUARTZ:
            case SMOOTH_QUARTZ_SLAB:
            case SMOOTH_QUARTZ_STAIRS:
            case SMOOTH_RED_SANDSTONE:
            case SMOOTH_RED_SANDSTONE_SLAB:
            case SMOOTH_RED_SANDSTONE_STAIRS:
            case SMOOTH_SANDSTONE:
            case SMOOTH_SANDSTONE_SLAB:
            case SMOOTH_SANDSTONE_STAIRS:
            case SMOOTH_STONE:
            case SMOOTH_STONE_SLAB:
            case SOUL_CAMPFIRE:
            case SPRUCE_FENCE:
            case SPRUCE_FENCE_GATE:
            case SPRUCE_LOG:
            case SPRUCE_PLANKS:
            case SPRUCE_SLAB:
            case SPRUCE_STAIRS:
            case SPRUCE_WOOD:
            case STONE_BRICK_SLAB:
            case STONE_SLAB:
            case STRIPPED_ACACIA_LOG:
            case STRIPPED_ACACIA_WOOD:
            case STRIPPED_BAMBOO_BLOCK:
            case STRIPPED_BIRCH_LOG:
            case STRIPPED_BIRCH_WOOD:
            case STRIPPED_CHERRY_LOG:
            case STRIPPED_CHERRY_WOOD:
            case STRIPPED_CRIMSON_HYPHAE:
            case STRIPPED_CRIMSON_STEM:
            case STRIPPED_DARK_OAK_LOG:
            case STRIPPED_DARK_OAK_WOOD:
            case STRIPPED_JUNGLE_LOG:
            case STRIPPED_JUNGLE_WOOD:
            case STRIPPED_MANGROVE_LOG:
            case STRIPPED_MANGROVE_WOOD:
            case STRIPPED_OAK_LOG:
            case STRIPPED_OAK_WOOD:
            case STRIPPED_SPRUCE_LOG:
            case STRIPPED_SPRUCE_WOOD:
            case STRIPPED_WARPED_HYPHAE:
            case STRIPPED_WARPED_STEM:
            case WARPED_FENCE:
            case WARPED_FENCE_GATE:
            case WARPED_HYPHAE:
            case WARPED_PLANKS:
            case WARPED_SLAB:
            case WARPED_STAIRS:
            case WARPED_STEM:
            case WATER_CAULDRON:
            case WHITE_SHULKER_BOX:
            case YELLOW_SHULKER_BOX:
                return 2.0F;
            case BARREL:
            case CARTOGRAPHY_TABLE:
            case CHEST:
            case CRAFTING_TABLE:
            case FLETCHING_TABLE:
            case LECTERN:
            case LOOM:
            case SMITHING_TABLE:
            case TRAPPED_CHEST:
                return 2.5F;
            case BLUE_ICE:
                return 2.8F;
            case ACACIA_DOOR:
            case ACACIA_TRAPDOOR:
            case BAMBOO_DOOR:
            case BAMBOO_TRAPDOOR:
            case BEACON:
            case BIRCH_DOOR:
            case BIRCH_TRAPDOOR:
            case CHERRY_DOOR:
            case CHERRY_TRAPDOOR:
            case CHISELED_COPPER:
            case COAL_ORE:
            case CONDUIT:
            case COPPER_BLOCK:
            case COPPER_BULB:
            case COPPER_DOOR:
            case COPPER_GRATE:
            case COPPER_ORE:
            case COPPER_TRAPDOOR:
            case CRIMSON_DOOR:
            case CRIMSON_TRAPDOOR:
            case CUT_COPPER:
            case CUT_COPPER_SLAB:
            case CUT_COPPER_STAIRS:
            case DARK_OAK_DOOR:
            case DARK_OAK_TRAPDOOR:
            case DEEPSLATE:
            case DIAMOND_ORE:
            case DRAGON_EGG:
            case EMERALD_ORE:
            case END_STONE:
            case END_STONE_BRICKS:
            case END_STONE_BRICK_SLAB:
            case END_STONE_BRICK_STAIRS:
            case END_STONE_BRICK_WALL:
            case EXPOSED_CHISELED_COPPER:
            case EXPOSED_COPPER:
            case EXPOSED_COPPER_BULB:
            case EXPOSED_COPPER_DOOR:
            case EXPOSED_COPPER_GRATE:
            case EXPOSED_COPPER_TRAPDOOR:
            case EXPOSED_CUT_COPPER:
            case EXPOSED_CUT_COPPER_SLAB:
            case EXPOSED_CUT_COPPER_STAIRS:
            case GOLD_BLOCK:
            case GOLD_ORE:
            case HOPPER:
            case IRON_ORE:
            case JUNGLE_DOOR:
            case JUNGLE_TRAPDOOR:
            case LAPIS_BLOCK:
            case LAPIS_ORE:
            case LIGHTNING_ROD:
            case MANGROVE_DOOR:
            case MANGROVE_TRAPDOOR:
            case NETHER_GOLD_ORE:
            case NETHER_QUARTZ_ORE:
            case OAK_DOOR:
            case OAK_TRAPDOOR:
            case OBSERVER:
            case OXIDIZED_CHISELED_COPPER:
            case OXIDIZED_COPPER:
            case OXIDIZED_COPPER_BULB:
            case OXIDIZED_COPPER_DOOR:
            case OXIDIZED_COPPER_GRATE:
            case OXIDIZED_COPPER_TRAPDOOR:
            case OXIDIZED_CUT_COPPER:
            case OXIDIZED_CUT_COPPER_SLAB:
            case OXIDIZED_CUT_COPPER_STAIRS:
            case REDSTONE_ORE:
            case SCULK_CATALYST:
            case SCULK_SHRIEKER:
            case SPRUCE_DOOR:
            case SPRUCE_TRAPDOOR:
            case WARPED_DOOR:
            case WARPED_TRAPDOOR:
            case WAXED_CHISELED_COPPER:
            case WAXED_COPPER_BLOCK:
            case WAXED_COPPER_BULB:
            case WAXED_COPPER_DOOR:
            case WAXED_COPPER_GRATE:
            case WAXED_COPPER_TRAPDOOR:
            case WAXED_CUT_COPPER:
            case WAXED_CUT_COPPER_SLAB:
            case WAXED_CUT_COPPER_STAIRS:
            case WAXED_EXPOSED_CHISELED_COPPER:
            case WAXED_EXPOSED_COPPER:
            case WAXED_EXPOSED_COPPER_BULB:
            case WAXED_EXPOSED_COPPER_DOOR:
            case WAXED_EXPOSED_COPPER_GRATE:
            case WAXED_EXPOSED_COPPER_TRAPDOOR:
            case WAXED_EXPOSED_CUT_COPPER:
            case WAXED_EXPOSED_CUT_COPPER_SLAB:
            case WAXED_EXPOSED_CUT_COPPER_STAIRS:
            case WAXED_OXIDIZED_CHISELED_COPPER:
            case WAXED_OXIDIZED_COPPER:
            case WAXED_OXIDIZED_COPPER_BULB:
            case WAXED_OXIDIZED_COPPER_DOOR:
            case WAXED_OXIDIZED_COPPER_GRATE:
            case WAXED_OXIDIZED_COPPER_TRAPDOOR:
            case WAXED_OXIDIZED_CUT_COPPER:
            case WAXED_OXIDIZED_CUT_COPPER_SLAB:
            case WAXED_OXIDIZED_CUT_COPPER_STAIRS:
            case WAXED_WEATHERED_CHISELED_COPPER:
            case WAXED_WEATHERED_COPPER:
            case WAXED_WEATHERED_COPPER_BULB:
            case WAXED_WEATHERED_COPPER_DOOR:
            case WAXED_WEATHERED_COPPER_GRATE:
            case WAXED_WEATHERED_COPPER_TRAPDOOR:
            case WAXED_WEATHERED_CUT_COPPER:
            case WAXED_WEATHERED_CUT_COPPER_SLAB:
            case WAXED_WEATHERED_CUT_COPPER_STAIRS:
            case WEATHERED_CHISELED_COPPER:
            case WEATHERED_COPPER:
            case WEATHERED_COPPER_BULB:
            case WEATHERED_COPPER_DOOR:
            case WEATHERED_COPPER_GRATE:
            case WEATHERED_COPPER_TRAPDOOR:
            case WEATHERED_CUT_COPPER:
            case WEATHERED_CUT_COPPER_SLAB:
            case WEATHERED_CUT_COPPER_STAIRS:
                return 3.0F;
            case BLAST_FURNACE:
            case CHISELED_DEEPSLATE:
            case COBBLED_DEEPSLATE:
            case COBBLED_DEEPSLATE_SLAB:
            case COBBLED_DEEPSLATE_STAIRS:
            case COBBLED_DEEPSLATE_WALL:
            case CRACKED_DEEPSLATE_BRICKS:
            case CRACKED_DEEPSLATE_TILES:
            case DEEPSLATE_BRICKS:
            case DEEPSLATE_BRICK_SLAB:
            case DEEPSLATE_BRICK_STAIRS:
            case DEEPSLATE_BRICK_WALL:
            case DEEPSLATE_TILES:
            case DEEPSLATE_TILE_SLAB:
            case DEEPSLATE_TILE_STAIRS:
            case DEEPSLATE_TILE_WALL:
            case DISPENSER:
            case DROPPER:
            case FURNACE:
            case LANTERN:
            case LODESTONE:
            case POLISHED_DEEPSLATE:
            case POLISHED_DEEPSLATE_SLAB:
            case POLISHED_DEEPSLATE_STAIRS:
            case POLISHED_DEEPSLATE_WALL:
            case SMOKER:
            case SOUL_LANTERN:
            case STONECUTTER:
                return 3.5F;
            case COBWEB:
                return 4.0F;
            case DEEPSLATE_COAL_ORE:
            case DEEPSLATE_COPPER_ORE:
            case DEEPSLATE_DIAMOND_ORE:
            case DEEPSLATE_EMERALD_ORE:
            case DEEPSLATE_GOLD_ORE:
            case DEEPSLATE_IRON_ORE:
            case DEEPSLATE_LAPIS_ORE:
            case DEEPSLATE_REDSTONE_ORE:
                return 4.5F;
            case ANVIL:
            case BELL:
            case CHAIN:
            case CHIPPED_ANVIL:
            case COAL_BLOCK:
            case DAMAGED_ANVIL:
            case DIAMOND_BLOCK:
            case EMERALD_BLOCK:
            case ENCHANTING_TABLE:
            case IRON_BARS:
            case IRON_BLOCK:
            case IRON_DOOR:
            case IRON_TRAPDOOR:
            case RAW_COPPER_BLOCK:
            case RAW_GOLD_BLOCK:
            case RAW_IRON_BLOCK:
            case REDSTONE_BLOCK:
            case SPAWNER:
                return 5.0F;
            case ENDER_CHEST:
                return 22.5F;
            case ANCIENT_DEBRIS:
                return 30.0F;
            case CRYING_OBSIDIAN:
            case NETHERITE_BLOCK:
            case OBSIDIAN:
            case RESPAWN_ANCHOR:
            case TRIAL_SPAWNER:
                return 50.0F;
            case REINFORCED_DEEPSLATE:
                return 55.0F;
            case LAVA:
            case WATER:
                return 100.0F;
            default:
                return 0.0F;
            // Paper end - Generated/Material#getHardness
            // </editor-fold>
        }
    }

    /**
     * Obtains the blast resistance value (also known as block "durability").
     * <br>
     * This value is used in explosions to calculate whether a block should be
     * broken or not.
     * <br>
     * Only available when {@link #isBlock()} is true.
     *
     * @return the blast resistance of that material.
     */
    public float getBlastResistance() {
        Preconditions.checkArgument(isBlock(), "The Material is not a block!");
        switch (this) {
            // <editor-fold defaultstate="collapsed" desc="getBlastResistance">
            // Paper start - Generated/Material#getBlastResistance
            // @GeneratedFrom 1.20.4
            case BIG_DRIPLEAF:
            case BIG_DRIPLEAF_STEM:
            case BLACK_CANDLE:
            case BLACK_CARPET:
            case BLUE_CANDLE:
            case BLUE_CARPET:
            case BROWN_CANDLE:
            case BROWN_CARPET:
            case CANDLE:
            case CYAN_CANDLE:
            case CYAN_CARPET:
            case GRAY_CANDLE:
            case GRAY_CARPET:
            case GREEN_CANDLE:
            case GREEN_CARPET:
            case LIGHT_BLUE_CANDLE:
            case LIGHT_BLUE_CARPET:
            case LIGHT_GRAY_CANDLE:
            case LIGHT_GRAY_CARPET:
            case LIME_CANDLE:
            case LIME_CARPET:
            case MAGENTA_CANDLE:
            case MAGENTA_CARPET:
            case MOSS_BLOCK:
            case MOSS_CARPET:
            case ORANGE_CANDLE:
            case ORANGE_CARPET:
            case PINK_CANDLE:
            case PINK_CARPET:
            case PURPLE_CANDLE:
            case PURPLE_CARPET:
            case RED_CANDLE:
            case RED_CARPET:
            case SNOW:
            case WHITE_CANDLE:
            case WHITE_CARPET:
            case YELLOW_CANDLE:
            case YELLOW_CARPET:
                return 0.1F;
            case ACACIA_LEAVES:
            case AZALEA_LEAVES:
            case BIRCH_LEAVES:
            case BLACK_BED:
            case BLUE_BED:
            case BROWN_BED:
            case BROWN_MUSHROOM_BLOCK:
            case CHERRY_LEAVES:
            case CYAN_BED:
            case DARK_OAK_LEAVES:
            case DAYLIGHT_DETECTOR:
            case FLOWERING_AZALEA_LEAVES:
            case GLOW_LICHEN:
            case GRAY_BED:
            case GREEN_BED:
            case JUNGLE_LEAVES:
            case LIGHT_BLUE_BED:
            case LIGHT_GRAY_BED:
            case LIME_BED:
            case MAGENTA_BED:
            case MANGROVE_LEAVES:
            case MUSHROOM_STEM:
            case OAK_LEAVES:
            case ORANGE_BED:
            case PINK_BED:
            case PURPLE_BED:
            case RED_BED:
            case RED_MUSHROOM_BLOCK:
            case SCULK:
            case SCULK_VEIN:
            case SNOW_BLOCK:
            case SPRUCE_LEAVES:
            case VINE:
            case WHITE_BED:
            case YELLOW_BED:
                return 0.2F;
            case POWDER_SNOW:
            case SUSPICIOUS_GRAVEL:
            case SUSPICIOUS_SAND:
                return 0.25F;
            case BEE_NEST:
            case BLACK_STAINED_GLASS:
            case BLACK_STAINED_GLASS_PANE:
            case BLUE_STAINED_GLASS:
            case BLUE_STAINED_GLASS_PANE:
            case BROWN_STAINED_GLASS:
            case BROWN_STAINED_GLASS_PANE:
            case CYAN_STAINED_GLASS:
            case CYAN_STAINED_GLASS_PANE:
            case GLASS:
            case GLASS_PANE:
            case GLOWSTONE:
            case GRAY_STAINED_GLASS:
            case GRAY_STAINED_GLASS_PANE:
            case GREEN_STAINED_GLASS:
            case GREEN_STAINED_GLASS_PANE:
            case LIGHT_BLUE_STAINED_GLASS:
            case LIGHT_BLUE_STAINED_GLASS_PANE:
            case LIGHT_GRAY_STAINED_GLASS:
            case LIGHT_GRAY_STAINED_GLASS_PANE:
            case LIME_STAINED_GLASS:
            case LIME_STAINED_GLASS_PANE:
            case MAGENTA_STAINED_GLASS:
            case MAGENTA_STAINED_GLASS_PANE:
            case OCHRE_FROGLIGHT:
            case ORANGE_STAINED_GLASS:
            case ORANGE_STAINED_GLASS_PANE:
            case PEARLESCENT_FROGLIGHT:
            case PINK_STAINED_GLASS:
            case PINK_STAINED_GLASS_PANE:
            case PURPLE_STAINED_GLASS:
            case PURPLE_STAINED_GLASS_PANE:
            case REDSTONE_LAMP:
            case RED_STAINED_GLASS:
            case RED_STAINED_GLASS_PANE:
            case SEA_LANTERN:
            case TINTED_GLASS:
            case VERDANT_FROGLIGHT:
            case WHITE_STAINED_GLASS:
            case WHITE_STAINED_GLASS_PANE:
            case YELLOW_STAINED_GLASS:
            case YELLOW_STAINED_GLASS_PANE:
                return 0.3F;
            case CACTUS:
            case CHORUS_FLOWER:
            case CHORUS_PLANT:
            case CRIMSON_NYLIUM:
            case LADDER:
            case NETHERRACK:
            case WARPED_NYLIUM:
                return 0.4F;
            case ACACIA_BUTTON:
            case ACACIA_PRESSURE_PLATE:
            case BAMBOO_BUTTON:
            case BAMBOO_PRESSURE_PLATE:
            case BIRCH_BUTTON:
            case BIRCH_PRESSURE_PLATE:
            case BLACK_CANDLE_CAKE:
            case BLACK_CONCRETE_POWDER:
            case BLUE_CANDLE_CAKE:
            case BLUE_CONCRETE_POWDER:
            case BREWING_STAND:
            case BROWN_CANDLE_CAKE:
            case BROWN_CONCRETE_POWDER:
            case CAKE:
            case CANDLE_CAKE:
            case CHERRY_BUTTON:
            case CHERRY_PRESSURE_PLATE:
            case COARSE_DIRT:
            case CRIMSON_BUTTON:
            case CRIMSON_PRESSURE_PLATE:
            case CYAN_CANDLE_CAKE:
            case CYAN_CONCRETE_POWDER:
            case DARK_OAK_BUTTON:
            case DARK_OAK_PRESSURE_PLATE:
            case DIRT:
            case FROSTED_ICE:
            case GRAY_CANDLE_CAKE:
            case GRAY_CONCRETE_POWDER:
            case GREEN_CANDLE_CAKE:
            case GREEN_CONCRETE_POWDER:
            case HAY_BLOCK:
            case HEAVY_WEIGHTED_PRESSURE_PLATE:
            case ICE:
            case JUNGLE_BUTTON:
            case JUNGLE_PRESSURE_PLATE:
            case LEVER:
            case LIGHT_BLUE_CANDLE_CAKE:
            case LIGHT_BLUE_CONCRETE_POWDER:
            case LIGHT_GRAY_CANDLE_CAKE:
            case LIGHT_GRAY_CONCRETE_POWDER:
            case LIGHT_WEIGHTED_PRESSURE_PLATE:
            case LIME_CANDLE_CAKE:
            case LIME_CONCRETE_POWDER:
            case MAGENTA_CANDLE_CAKE:
            case MAGENTA_CONCRETE_POWDER:
            case MAGMA_BLOCK:
            case MANGROVE_BUTTON:
            case MANGROVE_PRESSURE_PLATE:
            case MUD:
            case OAK_BUTTON:
            case OAK_PRESSURE_PLATE:
            case ORANGE_CANDLE_CAKE:
            case ORANGE_CONCRETE_POWDER:
            case PACKED_ICE:
            case PINK_CANDLE_CAKE:
            case PINK_CONCRETE_POWDER:
            case PODZOL:
            case POLISHED_BLACKSTONE_BUTTON:
            case POLISHED_BLACKSTONE_PRESSURE_PLATE:
            case PURPLE_CANDLE_CAKE:
            case PURPLE_CONCRETE_POWDER:
            case RED_CANDLE_CAKE:
            case RED_CONCRETE_POWDER:
            case RED_SAND:
            case ROOTED_DIRT:
            case SAND:
            case SNIFFER_EGG:
            case SOUL_SAND:
            case SOUL_SOIL:
            case SPRUCE_BUTTON:
            case SPRUCE_PRESSURE_PLATE:
            case STONE_BUTTON:
            case STONE_PRESSURE_PLATE:
            case TARGET:
            case TURTLE_EGG:
            case WARPED_BUTTON:
            case WARPED_PRESSURE_PLATE:
            case WHITE_CANDLE_CAKE:
            case WHITE_CONCRETE_POWDER:
            case YELLOW_CANDLE_CAKE:
            case YELLOW_CONCRETE_POWDER:
                return 0.5F;
            case BEEHIVE:
            case CLAY:
            case COMPOSTER:
            case FARMLAND:
            case GRASS_BLOCK:
            case GRAVEL:
            case HONEYCOMB_BLOCK:
            case MYCELIUM:
            case SPONGE:
            case WET_SPONGE:
                return 0.6F;
            case DIRT_PATH:
                return 0.65F;
            case ACTIVATOR_RAIL:
            case DETECTOR_RAIL:
            case MANGROVE_ROOTS:
            case MUDDY_MANGROVE_ROOTS:
            case POWERED_RAIL:
            case RAIL:
                return 0.7F;
            case CALCITE:
            case INFESTED_CHISELED_STONE_BRICKS:
            case INFESTED_COBBLESTONE:
            case INFESTED_CRACKED_STONE_BRICKS:
            case INFESTED_DEEPSLATE:
            case INFESTED_MOSSY_STONE_BRICKS:
            case INFESTED_STONE:
            case INFESTED_STONE_BRICKS:
                return 0.75F;
            case BLACK_WOOL:
            case BLUE_WOOL:
            case BROWN_WOOL:
            case CHISELED_QUARTZ_BLOCK:
            case CHISELED_RED_SANDSTONE:
            case CHISELED_SANDSTONE:
            case CUT_RED_SANDSTONE:
            case CUT_SANDSTONE:
            case CYAN_WOOL:
            case GRAY_WOOL:
            case GREEN_WOOL:
            case LIGHT_BLUE_WOOL:
            case LIGHT_GRAY_WOOL:
            case LIME_WOOL:
            case MAGENTA_WOOL:
            case NOTE_BLOCK:
            case ORANGE_WOOL:
            case PINK_WOOL:
            case PURPLE_WOOL:
            case QUARTZ_BLOCK:
            case QUARTZ_BRICKS:
            case QUARTZ_PILLAR:
            case QUARTZ_STAIRS:
            case RED_SANDSTONE:
            case RED_SANDSTONE_STAIRS:
            case RED_SANDSTONE_WALL:
            case RED_WOOL:
            case SANDSTONE:
            case SANDSTONE_STAIRS:
            case SANDSTONE_WALL:
            case WHITE_WOOL:
            case YELLOW_WOOL:
                return 0.8F;
            case ACACIA_HANGING_SIGN:
            case ACACIA_SIGN:
            case ACACIA_WALL_HANGING_SIGN:
            case ACACIA_WALL_SIGN:
            case BAMBOO:
            case BAMBOO_HANGING_SIGN:
            case BAMBOO_SAPLING:
            case BAMBOO_SIGN:
            case BAMBOO_WALL_HANGING_SIGN:
            case BAMBOO_WALL_SIGN:
            case BIRCH_HANGING_SIGN:
            case BIRCH_SIGN:
            case BIRCH_WALL_HANGING_SIGN:
            case BIRCH_WALL_SIGN:
            case BLACK_BANNER:
            case BLACK_WALL_BANNER:
            case BLUE_BANNER:
            case BLUE_WALL_BANNER:
            case BROWN_BANNER:
            case BROWN_WALL_BANNER:
            case CARVED_PUMPKIN:
            case CHERRY_HANGING_SIGN:
            case CHERRY_SIGN:
            case CHERRY_WALL_HANGING_SIGN:
            case CHERRY_WALL_SIGN:
            case CREEPER_HEAD:
            case CREEPER_WALL_HEAD:
            case CRIMSON_HANGING_SIGN:
            case CRIMSON_SIGN:
            case CRIMSON_WALL_HANGING_SIGN:
            case CRIMSON_WALL_SIGN:
            case CYAN_BANNER:
            case CYAN_WALL_BANNER:
            case DARK_OAK_HANGING_SIGN:
            case DARK_OAK_SIGN:
            case DARK_OAK_WALL_HANGING_SIGN:
            case DARK_OAK_WALL_SIGN:
            case DRAGON_HEAD:
            case DRAGON_WALL_HEAD:
            case DRIPSTONE_BLOCK:
            case GRAY_BANNER:
            case GRAY_WALL_BANNER:
            case GREEN_BANNER:
            case GREEN_WALL_BANNER:
            case JACK_O_LANTERN:
            case JUNGLE_HANGING_SIGN:
            case JUNGLE_SIGN:
            case JUNGLE_WALL_HANGING_SIGN:
            case JUNGLE_WALL_SIGN:
            case LIGHT_BLUE_BANNER:
            case LIGHT_BLUE_WALL_BANNER:
            case LIGHT_GRAY_BANNER:
            case LIGHT_GRAY_WALL_BANNER:
            case LIME_BANNER:
            case LIME_WALL_BANNER:
            case MAGENTA_BANNER:
            case MAGENTA_WALL_BANNER:
            case MANGROVE_HANGING_SIGN:
            case MANGROVE_SIGN:
            case MANGROVE_WALL_HANGING_SIGN:
            case MANGROVE_WALL_SIGN:
            case MELON:
            case NETHER_WART_BLOCK:
            case OAK_HANGING_SIGN:
            case OAK_SIGN:
            case OAK_WALL_HANGING_SIGN:
            case OAK_WALL_SIGN:
            case ORANGE_BANNER:
            case ORANGE_WALL_BANNER:
            case PIGLIN_HEAD:
            case PIGLIN_WALL_HEAD:
            case PINK_BANNER:
            case PINK_WALL_BANNER:
            case PLAYER_HEAD:
            case PLAYER_WALL_HEAD:
            case PUMPKIN:
            case PURPLE_BANNER:
            case PURPLE_WALL_BANNER:
            case RED_BANNER:
            case RED_WALL_BANNER:
            case SHROOMLIGHT:
            case SKELETON_SKULL:
            case SKELETON_WALL_SKULL:
            case SPRUCE_HANGING_SIGN:
            case SPRUCE_SIGN:
            case SPRUCE_WALL_HANGING_SIGN:
            case SPRUCE_WALL_SIGN:
            case WARPED_HANGING_SIGN:
            case WARPED_SIGN:
            case WARPED_WALL_HANGING_SIGN:
            case WARPED_WALL_SIGN:
            case WARPED_WART_BLOCK:
            case WHITE_BANNER:
            case WHITE_WALL_BANNER:
            case WITHER_SKELETON_SKULL:
            case WITHER_SKELETON_WALL_SKULL:
            case YELLOW_BANNER:
            case YELLOW_WALL_BANNER:
            case ZOMBIE_HEAD:
            case ZOMBIE_WALL_HEAD:
                return 1.0F;
            case BLACK_GLAZED_TERRACOTTA:
            case BLUE_GLAZED_TERRACOTTA:
            case BROWN_GLAZED_TERRACOTTA:
            case CYAN_GLAZED_TERRACOTTA:
            case GRAY_GLAZED_TERRACOTTA:
            case GREEN_GLAZED_TERRACOTTA:
            case LIGHT_BLUE_GLAZED_TERRACOTTA:
            case LIGHT_GRAY_GLAZED_TERRACOTTA:
            case LIME_GLAZED_TERRACOTTA:
            case MAGENTA_GLAZED_TERRACOTTA:
            case ORANGE_GLAZED_TERRACOTTA:
            case PINK_GLAZED_TERRACOTTA:
            case PURPLE_GLAZED_TERRACOTTA:
            case RED_GLAZED_TERRACOTTA:
            case WHITE_GLAZED_TERRACOTTA:
            case YELLOW_GLAZED_TERRACOTTA:
                return 1.4F;
            case AMETHYST_BLOCK:
            case AMETHYST_CLUSTER:
            case BOOKSHELF:
            case BUDDING_AMETHYST:
            case CALIBRATED_SCULK_SENSOR:
            case CHISELED_BOOKSHELF:
            case LARGE_AMETHYST_BUD:
            case MEDIUM_AMETHYST_BUD:
            case PISTON:
            case PISTON_HEAD:
            case SCULK_SENSOR:
            case SMALL_AMETHYST_BUD:
            case STICKY_PISTON:
                return 1.5F;
            case BLACK_CONCRETE:
            case BLUE_CONCRETE:
            case BROWN_CONCRETE:
            case CYAN_CONCRETE:
            case GRAY_CONCRETE:
            case GREEN_CONCRETE:
            case LIGHT_BLUE_CONCRETE:
            case LIGHT_GRAY_CONCRETE:
            case LIME_CONCRETE:
            case MAGENTA_CONCRETE:
            case ORANGE_CONCRETE:
            case PINK_CONCRETE:
            case PURPLE_CONCRETE:
            case RED_CONCRETE:
            case WHITE_CONCRETE:
            case YELLOW_CONCRETE:
                return 1.8F;
            case ACACIA_LOG:
            case ACACIA_WOOD:
            case BAMBOO_BLOCK:
            case BIRCH_LOG:
            case BIRCH_WOOD:
            case BLACK_SHULKER_BOX:
            case BLUE_SHULKER_BOX:
            case BONE_BLOCK:
            case BROWN_SHULKER_BOX:
            case CAMPFIRE:
            case CAULDRON:
            case CHERRY_LOG:
            case CHERRY_WOOD:
            case CRIMSON_HYPHAE:
            case CRIMSON_STEM:
            case CYAN_SHULKER_BOX:
            case DARK_OAK_LOG:
            case DARK_OAK_WOOD:
            case GRAY_SHULKER_BOX:
            case GREEN_SHULKER_BOX:
            case JUNGLE_LOG:
            case JUNGLE_WOOD:
            case LAVA_CAULDRON:
            case LIGHT_BLUE_SHULKER_BOX:
            case LIGHT_GRAY_SHULKER_BOX:
            case LIME_SHULKER_BOX:
            case MAGENTA_SHULKER_BOX:
            case MANGROVE_LOG:
            case MANGROVE_WOOD:
            case OAK_LOG:
            case OAK_WOOD:
            case ORANGE_SHULKER_BOX:
            case PINK_SHULKER_BOX:
            case POWDER_SNOW_CAULDRON:
            case PURPLE_SHULKER_BOX:
            case RED_SHULKER_BOX:
            case SHULKER_BOX:
            case SOUL_CAMPFIRE:
            case SPRUCE_LOG:
            case SPRUCE_WOOD:
            case STRIPPED_ACACIA_LOG:
            case STRIPPED_ACACIA_WOOD:
            case STRIPPED_BAMBOO_BLOCK:
            case STRIPPED_BIRCH_LOG:
            case STRIPPED_BIRCH_WOOD:
            case STRIPPED_CHERRY_LOG:
            case STRIPPED_CHERRY_WOOD:
            case STRIPPED_CRIMSON_HYPHAE:
            case STRIPPED_CRIMSON_STEM:
            case STRIPPED_DARK_OAK_LOG:
            case STRIPPED_DARK_OAK_WOOD:
            case STRIPPED_JUNGLE_LOG:
            case STRIPPED_JUNGLE_WOOD:
            case STRIPPED_MANGROVE_LOG:
            case STRIPPED_MANGROVE_WOOD:
            case STRIPPED_OAK_LOG:
            case STRIPPED_OAK_WOOD:
            case STRIPPED_SPRUCE_LOG:
            case STRIPPED_SPRUCE_WOOD:
            case STRIPPED_WARPED_HYPHAE:
            case STRIPPED_WARPED_STEM:
            case WARPED_HYPHAE:
            case WARPED_STEM:
            case WATER_CAULDRON:
            case WHITE_SHULKER_BOX:
            case YELLOW_SHULKER_BOX:
                return 2.0F;
            case BARREL:
            case CARTOGRAPHY_TABLE:
            case CHEST:
            case CRAFTING_TABLE:
            case DRIED_KELP_BLOCK:
            case FLETCHING_TABLE:
            case LECTERN:
            case LOOM:
            case SMITHING_TABLE:
            case TRAPPED_CHEST:
                return 2.5F;
            case BLUE_ICE:
                return 2.8F;
            case ACACIA_DOOR:
            case ACACIA_FENCE:
            case ACACIA_FENCE_GATE:
            case ACACIA_PLANKS:
            case ACACIA_SLAB:
            case ACACIA_STAIRS:
            case ACACIA_TRAPDOOR:
            case BAMBOO_DOOR:
            case BAMBOO_FENCE:
            case BAMBOO_FENCE_GATE:
            case BAMBOO_MOSAIC:
            case BAMBOO_MOSAIC_SLAB:
            case BAMBOO_MOSAIC_STAIRS:
            case BAMBOO_PLANKS:
            case BAMBOO_SLAB:
            case BAMBOO_STAIRS:
            case BAMBOO_TRAPDOOR:
            case BEACON:
            case BIRCH_DOOR:
            case BIRCH_FENCE:
            case BIRCH_FENCE_GATE:
            case BIRCH_PLANKS:
            case BIRCH_SLAB:
            case BIRCH_STAIRS:
            case BIRCH_TRAPDOOR:
            case CHERRY_DOOR:
            case CHERRY_FENCE:
            case CHERRY_FENCE_GATE:
            case CHERRY_PLANKS:
            case CHERRY_SLAB:
            case CHERRY_STAIRS:
            case CHERRY_TRAPDOOR:
            case COAL_ORE:
            case COCOA:
            case CONDUIT:
            case COPPER_ORE:
            case CRIMSON_DOOR:
            case CRIMSON_FENCE:
            case CRIMSON_FENCE_GATE:
            case CRIMSON_PLANKS:
            case CRIMSON_SLAB:
            case CRIMSON_STAIRS:
            case CRIMSON_TRAPDOOR:
            case DARK_OAK_DOOR:
            case DARK_OAK_FENCE:
            case DARK_OAK_FENCE_GATE:
            case DARK_OAK_PLANKS:
            case DARK_OAK_SLAB:
            case DARK_OAK_STAIRS:
            case DARK_OAK_TRAPDOOR:
            case DEEPSLATE_COAL_ORE:
            case DEEPSLATE_COPPER_ORE:
            case DEEPSLATE_DIAMOND_ORE:
            case DEEPSLATE_EMERALD_ORE:
            case DEEPSLATE_GOLD_ORE:
            case DEEPSLATE_IRON_ORE:
            case DEEPSLATE_LAPIS_ORE:
            case DEEPSLATE_REDSTONE_ORE:
            case DIAMOND_ORE:
            case EMERALD_ORE:
            case GOLD_ORE:
            case IRON_ORE:
            case JUNGLE_DOOR:
            case JUNGLE_FENCE:
            case JUNGLE_FENCE_GATE:
            case JUNGLE_PLANKS:
            case JUNGLE_SLAB:
            case JUNGLE_STAIRS:
            case JUNGLE_TRAPDOOR:
            case LAPIS_BLOCK:
            case LAPIS_ORE:
            case MANGROVE_DOOR:
            case MANGROVE_FENCE:
            case MANGROVE_FENCE_GATE:
            case MANGROVE_PLANKS:
            case MANGROVE_SLAB:
            case MANGROVE_STAIRS:
            case MANGROVE_TRAPDOOR:
            case MUD_BRICKS:
            case MUD_BRICK_SLAB:
            case MUD_BRICK_STAIRS:
            case MUD_BRICK_WALL:
            case NETHER_GOLD_ORE:
            case NETHER_QUARTZ_ORE:
            case OAK_DOOR:
            case OAK_FENCE:
            case OAK_FENCE_GATE:
            case OAK_PLANKS:
            case OAK_SLAB:
            case OAK_STAIRS:
            case OAK_TRAPDOOR:
            case OBSERVER:
            case PACKED_MUD:
            case POINTED_DRIPSTONE:
            case REDSTONE_ORE:
            case SCULK_CATALYST:
            case SCULK_SHRIEKER:
            case SPRUCE_DOOR:
            case SPRUCE_FENCE:
            case SPRUCE_FENCE_GATE:
            case SPRUCE_PLANKS:
            case SPRUCE_SLAB:
            case SPRUCE_STAIRS:
            case SPRUCE_TRAPDOOR:
            case WARPED_DOOR:
            case WARPED_FENCE:
            case WARPED_FENCE_GATE:
            case WARPED_PLANKS:
            case WARPED_SLAB:
            case WARPED_STAIRS:
            case WARPED_TRAPDOOR:
                return 3.0F;
            case BLAST_FURNACE:
            case CRAFTER:
            case DISPENSER:
            case DROPPER:
            case FURNACE:
            case LANTERN:
            case LODESTONE:
            case SMOKER:
            case SOUL_LANTERN:
            case STONECUTTER:
                return 3.5F;
            case COBWEB:
                return 4.0F;
            case BASALT:
            case BLACK_TERRACOTTA:
            case BLUE_TERRACOTTA:
            case BROWN_TERRACOTTA:
            case CYAN_TERRACOTTA:
            case GRAY_TERRACOTTA:
            case GREEN_TERRACOTTA:
            case LIGHT_BLUE_TERRACOTTA:
            case LIGHT_GRAY_TERRACOTTA:
            case LIME_TERRACOTTA:
            case MAGENTA_TERRACOTTA:
            case ORANGE_TERRACOTTA:
            case PINK_TERRACOTTA:
            case POLISHED_BASALT:
            case PURPLE_TERRACOTTA:
            case RED_TERRACOTTA:
            case SMOOTH_BASALT:
            case TERRACOTTA:
            case WHITE_TERRACOTTA:
            case YELLOW_TERRACOTTA:
                return 4.2F;
            case HOPPER:
                return 4.8F;
            case BELL:
            case IRON_DOOR:
            case IRON_TRAPDOOR:
            case SPAWNER:
                return 5.0F;
            case ANDESITE:
            case ANDESITE_SLAB:
            case ANDESITE_STAIRS:
            case ANDESITE_WALL:
            case BLACKSTONE:
            case BLACKSTONE_SLAB:
            case BLACKSTONE_STAIRS:
            case BLACKSTONE_WALL:
            case BRAIN_CORAL_BLOCK:
            case BRICKS:
            case BRICK_SLAB:
            case BRICK_STAIRS:
            case BRICK_WALL:
            case BUBBLE_CORAL_BLOCK:
            case CHAIN:
            case CHISELED_COPPER:
            case CHISELED_DEEPSLATE:
            case CHISELED_NETHER_BRICKS:
            case CHISELED_POLISHED_BLACKSTONE:
            case CHISELED_STONE_BRICKS:
            case CHISELED_TUFF:
            case CHISELED_TUFF_BRICKS:
            case COAL_BLOCK:
            case COBBLED_DEEPSLATE:
            case COBBLED_DEEPSLATE_SLAB:
            case COBBLED_DEEPSLATE_STAIRS:
            case COBBLED_DEEPSLATE_WALL:
            case COBBLESTONE:
            case COBBLESTONE_SLAB:
            case COBBLESTONE_STAIRS:
            case COBBLESTONE_WALL:
            case COPPER_BLOCK:
            case COPPER_BULB:
            case COPPER_DOOR:
            case COPPER_GRATE:
            case COPPER_TRAPDOOR:
            case CRACKED_DEEPSLATE_BRICKS:
            case CRACKED_DEEPSLATE_TILES:
            case CRACKED_NETHER_BRICKS:
            case CRACKED_POLISHED_BLACKSTONE_BRICKS:
            case CRACKED_STONE_BRICKS:
            case CUT_COPPER:
            case CUT_COPPER_SLAB:
            case CUT_COPPER_STAIRS:
            case CUT_RED_SANDSTONE_SLAB:
            case CUT_SANDSTONE_SLAB:
            case DARK_PRISMARINE:
            case DARK_PRISMARINE_SLAB:
            case DARK_PRISMARINE_STAIRS:
            case DEAD_BRAIN_CORAL_BLOCK:
            case DEAD_BUBBLE_CORAL_BLOCK:
            case DEAD_FIRE_CORAL_BLOCK:
            case DEAD_HORN_CORAL_BLOCK:
            case DEAD_TUBE_CORAL_BLOCK:
            case DEEPSLATE:
            case DEEPSLATE_BRICKS:
            case DEEPSLATE_BRICK_SLAB:
            case DEEPSLATE_BRICK_STAIRS:
            case DEEPSLATE_BRICK_WALL:
            case DEEPSLATE_TILES:
            case DEEPSLATE_TILE_SLAB:
            case DEEPSLATE_TILE_STAIRS:
            case DEEPSLATE_TILE_WALL:
            case DIAMOND_BLOCK:
            case DIORITE:
            case DIORITE_SLAB:
            case DIORITE_STAIRS:
            case DIORITE_WALL:
            case EMERALD_BLOCK:
            case EXPOSED_CHISELED_COPPER:
            case EXPOSED_COPPER:
            case EXPOSED_COPPER_BULB:
            case EXPOSED_COPPER_DOOR:
            case EXPOSED_COPPER_GRATE:
            case EXPOSED_COPPER_TRAPDOOR:
            case EXPOSED_CUT_COPPER:
            case EXPOSED_CUT_COPPER_SLAB:
            case EXPOSED_CUT_COPPER_STAIRS:
            case FIRE_CORAL_BLOCK:
            case GILDED_BLACKSTONE:
            case GOLD_BLOCK:
            case GRANITE:
            case GRANITE_SLAB:
            case GRANITE_STAIRS:
            case GRANITE_WALL:
            case GRINDSTONE:
            case HORN_CORAL_BLOCK:
            case IRON_BARS:
            case IRON_BLOCK:
            case JUKEBOX:
            case LIGHTNING_ROD:
            case MOSSY_COBBLESTONE:
            case MOSSY_COBBLESTONE_SLAB:
            case MOSSY_COBBLESTONE_STAIRS:
            case MOSSY_COBBLESTONE_WALL:
            case MOSSY_STONE_BRICKS:
            case MOSSY_STONE_BRICK_SLAB:
            case MOSSY_STONE_BRICK_STAIRS:
            case MOSSY_STONE_BRICK_WALL:
            case NETHER_BRICKS:
            case NETHER_BRICK_FENCE:
            case NETHER_BRICK_SLAB:
            case NETHER_BRICK_STAIRS:
            case NETHER_BRICK_WALL:
            case OXIDIZED_CHISELED_COPPER:
            case OXIDIZED_COPPER:
            case OXIDIZED_COPPER_BULB:
            case OXIDIZED_COPPER_DOOR:
            case OXIDIZED_COPPER_GRATE:
            case OXIDIZED_COPPER_TRAPDOOR:
            case OXIDIZED_CUT_COPPER:
            case OXIDIZED_CUT_COPPER_SLAB:
            case OXIDIZED_CUT_COPPER_STAIRS:
            case PETRIFIED_OAK_SLAB:
            case POLISHED_ANDESITE:
            case POLISHED_ANDESITE_SLAB:
            case POLISHED_ANDESITE_STAIRS:
            case POLISHED_BLACKSTONE:
            case POLISHED_BLACKSTONE_BRICKS:
            case POLISHED_BLACKSTONE_BRICK_SLAB:
            case POLISHED_BLACKSTONE_BRICK_STAIRS:
            case POLISHED_BLACKSTONE_BRICK_WALL:
            case POLISHED_BLACKSTONE_SLAB:
            case POLISHED_BLACKSTONE_STAIRS:
            case POLISHED_BLACKSTONE_WALL:
            case POLISHED_DEEPSLATE:
            case POLISHED_DEEPSLATE_SLAB:
            case POLISHED_DEEPSLATE_STAIRS:
            case POLISHED_DEEPSLATE_WALL:
            case POLISHED_DIORITE:
            case POLISHED_DIORITE_SLAB:
            case POLISHED_DIORITE_STAIRS:
            case POLISHED_GRANITE:
            case POLISHED_GRANITE_SLAB:
            case POLISHED_GRANITE_STAIRS:
            case POLISHED_TUFF:
            case POLISHED_TUFF_SLAB:
            case POLISHED_TUFF_STAIRS:
            case POLISHED_TUFF_WALL:
            case PRISMARINE:
            case PRISMARINE_BRICKS:
            case PRISMARINE_BRICK_SLAB:
            case PRISMARINE_BRICK_STAIRS:
            case PRISMARINE_SLAB:
            case PRISMARINE_STAIRS:
            case PRISMARINE_WALL:
            case PURPUR_BLOCK:
            case PURPUR_PILLAR:
            case PURPUR_SLAB:
            case PURPUR_STAIRS:
            case QUARTZ_SLAB:
            case RAW_COPPER_BLOCK:
            case RAW_GOLD_BLOCK:
            case RAW_IRON_BLOCK:
            case REDSTONE_BLOCK:
            case RED_NETHER_BRICKS:
            case RED_NETHER_BRICK_SLAB:
            case RED_NETHER_BRICK_STAIRS:
            case RED_NETHER_BRICK_WALL:
            case RED_SANDSTONE_SLAB:
            case SANDSTONE_SLAB:
            case SMOOTH_QUARTZ:
            case SMOOTH_QUARTZ_SLAB:
            case SMOOTH_QUARTZ_STAIRS:
            case SMOOTH_RED_SANDSTONE:
            case SMOOTH_RED_SANDSTONE_SLAB:
            case SMOOTH_RED_SANDSTONE_STAIRS:
            case SMOOTH_SANDSTONE:
            case SMOOTH_SANDSTONE_SLAB:
            case SMOOTH_SANDSTONE_STAIRS:
            case SMOOTH_STONE:
            case SMOOTH_STONE_SLAB:
            case STONE:
            case STONE_BRICKS:
            case STONE_BRICK_SLAB:
            case STONE_BRICK_STAIRS:
            case STONE_BRICK_WALL:
            case STONE_SLAB:
            case STONE_STAIRS:
            case TUBE_CORAL_BLOCK:
            case TUFF:
            case TUFF_BRICKS:
            case TUFF_BRICK_SLAB:
            case TUFF_BRICK_STAIRS:
            case TUFF_BRICK_WALL:
            case TUFF_SLAB:
            case TUFF_STAIRS:
            case TUFF_WALL:
            case WAXED_CHISELED_COPPER:
            case WAXED_COPPER_BLOCK:
            case WAXED_COPPER_BULB:
            case WAXED_COPPER_DOOR:
            case WAXED_COPPER_GRATE:
            case WAXED_COPPER_TRAPDOOR:
            case WAXED_CUT_COPPER:
            case WAXED_CUT_COPPER_SLAB:
            case WAXED_CUT_COPPER_STAIRS:
            case WAXED_EXPOSED_CHISELED_COPPER:
            case WAXED_EXPOSED_COPPER:
            case WAXED_EXPOSED_COPPER_BULB:
            case WAXED_EXPOSED_COPPER_DOOR:
            case WAXED_EXPOSED_COPPER_GRATE:
            case WAXED_EXPOSED_COPPER_TRAPDOOR:
            case WAXED_EXPOSED_CUT_COPPER:
            case WAXED_EXPOSED_CUT_COPPER_SLAB:
            case WAXED_EXPOSED_CUT_COPPER_STAIRS:
            case WAXED_OXIDIZED_CHISELED_COPPER:
            case WAXED_OXIDIZED_COPPER:
            case WAXED_OXIDIZED_COPPER_BULB:
            case WAXED_OXIDIZED_COPPER_DOOR:
            case WAXED_OXIDIZED_COPPER_GRATE:
            case WAXED_OXIDIZED_COPPER_TRAPDOOR:
            case WAXED_OXIDIZED_CUT_COPPER:
            case WAXED_OXIDIZED_CUT_COPPER_SLAB:
            case WAXED_OXIDIZED_CUT_COPPER_STAIRS:
            case WAXED_WEATHERED_CHISELED_COPPER:
            case WAXED_WEATHERED_COPPER:
            case WAXED_WEATHERED_COPPER_BULB:
            case WAXED_WEATHERED_COPPER_DOOR:
            case WAXED_WEATHERED_COPPER_GRATE:
            case WAXED_WEATHERED_COPPER_TRAPDOOR:
            case WAXED_WEATHERED_CUT_COPPER:
            case WAXED_WEATHERED_CUT_COPPER_SLAB:
            case WAXED_WEATHERED_CUT_COPPER_STAIRS:
            case WEATHERED_CHISELED_COPPER:
            case WEATHERED_COPPER:
            case WEATHERED_COPPER_BULB:
            case WEATHERED_COPPER_DOOR:
            case WEATHERED_COPPER_GRATE:
            case WEATHERED_COPPER_TRAPDOOR:
            case WEATHERED_CUT_COPPER:
            case WEATHERED_CUT_COPPER_SLAB:
            case WEATHERED_CUT_COPPER_STAIRS:
                return 6.0F;
            case DRAGON_EGG:
            case END_STONE:
            case END_STONE_BRICKS:
            case END_STONE_BRICK_SLAB:
            case END_STONE_BRICK_STAIRS:
            case END_STONE_BRICK_WALL:
                return 9.0F;
            case TRIAL_SPAWNER:
                return 50.0F;
            case LAVA:
            case WATER:
                return 100.0F;
            case ENDER_CHEST:
                return 600.0F;
            case ANCIENT_DEBRIS:
            case ANVIL:
            case CHIPPED_ANVIL:
            case CRYING_OBSIDIAN:
            case DAMAGED_ANVIL:
            case ENCHANTING_TABLE:
            case NETHERITE_BLOCK:
            case OBSIDIAN:
            case REINFORCED_DEEPSLATE:
            case RESPAWN_ANCHOR:
                return 1200.0F;
            case BEDROCK:
            case CHAIN_COMMAND_BLOCK:
            case COMMAND_BLOCK:
            case END_GATEWAY:
            case END_PORTAL:
            case END_PORTAL_FRAME:
            case JIGSAW:
            case REPEATING_COMMAND_BLOCK:
            case STRUCTURE_BLOCK:
                return 3600000.0F;
            case BARRIER:
            case LIGHT:
                return 3600000.8F;
            default:
                return 0.0F;
            // Paper end - Generated/Material#getBlastResistance
            // </editor-fold>
        }
    }

    /**
     * Returns a value that represents how 'slippery' the block is.
     *
     * Blocks with higher slipperiness, like {@link Material#ICE} can be slid on
     * further by the player and other entities.
     *
     * Most blocks have a default slipperiness of {@code 0.6f}.
     *
     * Only available when {@link #isBlock()} is true.
     *
     * @return the slipperiness of this block
     */
    public float getSlipperiness() {
        Preconditions.checkArgument(isBlock(), "The Material is not a block!");
        switch (this) {
            // <editor-fold defaultstate="collapsed" desc="getSlipperiness">
            // Paper start - Generated/Material#getSlipperiness
            // @GeneratedFrom 1.20.4
            case SLIME_BLOCK:
                return 0.8F;
            case FROSTED_ICE:
            case ICE:
            case PACKED_ICE:
                return 0.98F;
            case BLUE_ICE:
                return 0.989F;
            default:
                return 0.6F;
            // Paper end - Generated/Material#getSlipperiness
            // </editor-fold>
        }
    }

    /**
     * Determines the remaining item in a crafting grid after crafting with this
     * ingredient.
     * <br>
     * Only available when {@link #isItem()} is true.
     *
     * @return the item left behind when crafting, or null if nothing is.
     */
    @Nullable
    public Material getCraftingRemainingItem() {
        Preconditions.checkArgument(isItem(), "The Material is not an item!");
        switch (this) {
            // <editor-fold defaultstate="collapsed" desc="getCraftingRemainingItem">
            // Paper start - Generated/Material#getCraftingRemainingItem
            // @GeneratedFrom 1.20.4
            case LAVA_BUCKET:
            case MILK_BUCKET:
            case WATER_BUCKET:
                return BUCKET;
            case DRAGON_BREATH:
            case HONEY_BOTTLE:
                return GLASS_BOTTLE;
            default:
                return null;
            // Paper end - Generated/Material#getCraftingRemainingItem
            // </editor-fold>
        }
    }

    /**
     * Get the best suitable slot for this Material.
     *
     * For most items this will be {@link EquipmentSlot#HAND}.
     *
     * @return the best EquipmentSlot for this Material
     */
    @NotNull
    public EquipmentSlot getEquipmentSlot() {
        Preconditions.checkArgument(isItem(), "The Material is not an item!");
        switch (this) {
            // <editor-fold defaultstate="collapsed" desc="getEquipmentSlot">
            // Paper start - Generated/Material#getEquipmentSlot
            // @GeneratedFrom 1.20.4
            case CARVED_PUMPKIN:
            case CHAINMAIL_HELMET:
            case CREEPER_HEAD:
            case DIAMOND_HELMET:
            case DRAGON_HEAD:
            case GOLDEN_HELMET:
            case IRON_HELMET:
            case LEATHER_HELMET:
            case NETHERITE_HELMET:
            case PIGLIN_HEAD:
            case PLAYER_HEAD:
            case SKELETON_SKULL:
            case TURTLE_HELMET:
            case WITHER_SKELETON_SKULL:
            case ZOMBIE_HEAD:
                return EquipmentSlot.HEAD;
            case CHAINMAIL_CHESTPLATE:
            case DIAMOND_CHESTPLATE:
            case ELYTRA:
            case GOLDEN_CHESTPLATE:
            case IRON_CHESTPLATE:
            case LEATHER_CHESTPLATE:
            case NETHERITE_CHESTPLATE:
                return EquipmentSlot.CHEST;
            case CHAINMAIL_LEGGINGS:
            case DIAMOND_LEGGINGS:
            case GOLDEN_LEGGINGS:
            case IRON_LEGGINGS:
            case LEATHER_LEGGINGS:
            case NETHERITE_LEGGINGS:
                return EquipmentSlot.LEGS;
            case CHAINMAIL_BOOTS:
            case DIAMOND_BOOTS:
            case GOLDEN_BOOTS:
            case IRON_BOOTS:
            case LEATHER_BOOTS:
            case NETHERITE_BOOTS:
                return EquipmentSlot.FEET;
            case SHIELD:
                return EquipmentSlot.OFF_HAND;
            default:
                return EquipmentSlot.HAND;
            // Paper end - Generated/Material#getEquipmentSlot
            // </editor-fold>
        }
    }

    /**
     * Return an immutable copy of all default {@link Attribute}s and their
     * {@link AttributeModifier}s for a given {@link EquipmentSlot}.
     *
     * Default attributes are those that are always preset on some items, such
     * as the attack damage on weapons or the armor value on armor.
     *
     * Only available when {@link #isItem()} is true.
     *
     * @param slot the {@link EquipmentSlot} to check
     * @return the immutable {@link Multimap} with the respective default
     * Attributes and modifiers, or an empty map if no attributes are set.
     */
    @NotNull
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(@NotNull EquipmentSlot slot) {
        Preconditions.checkArgument(isItem(), "The Material is not an item!");

        return Bukkit.getUnsafe().getDefaultAttributeModifiers(this, slot);
    }

    /**
     * Get the {@link CreativeCategory} to which this material belongs.
     *
     * @return the creative category. null if does not belong to a category
     */
    @Nullable
    public CreativeCategory getCreativeCategory() {
        return Bukkit.getUnsafe().getCreativeCategory(this);
    }

    /**
     * Get the translation key of the item or block associated with this
     * material.
     *
     * If this material has both an item and a block form, the item form is
     * used.
     *
     * @return the translation key of the item or block associated with this
     * material
     * @see #getBlockTranslationKey()
     * @see #getItemTranslationKey()
     * @deprecated use {@link #translationKey()}
     */
    @Override
    @NotNull
    @Deprecated(forRemoval = true) // Paper
    public String getTranslationKey() {
        if (this.isItem()) {
            return Bukkit.getUnsafe().getItemTranslationKey(this);
        } else {
            return Bukkit.getUnsafe().getBlockTranslationKey(this);
        }
    }

    /**
     * Get the translation key of the block associated with this material, or
     * null if this material does not have an associated block.
     *
     * @return the translation key of the block associated with this material,
     * or null if this material does not have an associated block
     */
    @Nullable
    public String getBlockTranslationKey() {
        return Bukkit.getUnsafe().getBlockTranslationKey(this);
    }

    /**
     * Get the translation key of the item associated with this material, or
     * null if this material does not have an associated item.
     *
     * @return the translation key of the item associated with this material, or
     * null if this material does not have an associated item.
     */
    @Nullable
    public String getItemTranslationKey() {
        return Bukkit.getUnsafe().getItemTranslationKey(this);
    }

    /**
     * Gets if the Material is enabled by the features in a world.
     *
     * @param world the world to check
     * @return true if this material can be used in this World.
     */
    public boolean isEnabledByFeature(@NotNull World world) {
        return Bukkit.getDataPackManager().isEnabledByFeature(this, world);
    }
}
