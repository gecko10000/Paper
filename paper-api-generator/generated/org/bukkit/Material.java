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
    AIR(9648, 0),
    STONE(22948),
    GRANITE(21091),
    POLISHED_GRANITE(5477),
    DIORITE(24688),
    POLISHED_DIORITE(31615),
    ANDESITE(25975),
    POLISHED_ANDESITE(8335),
    /**
     * BlockData: {@link Orientable}
     */
    DEEPSLATE(26842, Orientable.class),
    COBBLED_DEEPSLATE(8021),
    POLISHED_DEEPSLATE(31772),
    CALCITE(20311),
    TUFF(24364),
    /**
     * BlockData: {@link Slab}
     */
    @MinecraftExperimental
    @org.jetbrains.annotations.ApiStatus.Experimental // Paper - add missing annotation
    TUFF_SLAB(19305, Slab.class),
    /**
     * BlockData: {@link Stairs}
     */
    @MinecraftExperimental
    @org.jetbrains.annotations.ApiStatus.Experimental // Paper - add missing annotation
    TUFF_STAIRS(11268, Stairs.class),
    /**
     * BlockData: {@link Wall}
     */
    @MinecraftExperimental
    @org.jetbrains.annotations.ApiStatus.Experimental // Paper - add missing annotation
    TUFF_WALL(24395, Wall.class),
    @MinecraftExperimental
    @org.jetbrains.annotations.ApiStatus.Experimental // Paper - add missing annotation
    CHISELED_TUFF(15831),
    @MinecraftExperimental
    @org.jetbrains.annotations.ApiStatus.Experimental // Paper - add missing annotation
    POLISHED_TUFF(17801),
    /**
     * BlockData: {@link Slab}
     */
    @MinecraftExperimental
    @org.jetbrains.annotations.ApiStatus.Experimental // Paper - add missing annotation
    POLISHED_TUFF_SLAB(31096, Slab.class),
    /**
     * BlockData: {@link Stairs}
     */
    @MinecraftExperimental
    @org.jetbrains.annotations.ApiStatus.Experimental // Paper - add missing annotation
    POLISHED_TUFF_STAIRS(7964, Stairs.class),
    /**
     * BlockData: {@link Wall}
     */
    @MinecraftExperimental
    @org.jetbrains.annotations.ApiStatus.Experimental // Paper - add missing annotation
    POLISHED_TUFF_WALL(28886, Wall.class),
    @MinecraftExperimental
    @org.jetbrains.annotations.ApiStatus.Experimental // Paper - add missing annotation
    TUFF_BRICKS(26276),
    /**
     * BlockData: {@link Slab}
     */
    @MinecraftExperimental
    @org.jetbrains.annotations.ApiStatus.Experimental // Paper - add missing annotation
    TUFF_BRICK_SLAB(11843, Slab.class),
    /**
     * BlockData: {@link Stairs}
     */
    @MinecraftExperimental
    @org.jetbrains.annotations.ApiStatus.Experimental // Paper - add missing annotation
    TUFF_BRICK_STAIRS(30753, Stairs.class),
    /**
     * BlockData: {@link Wall}
     */
    @MinecraftExperimental
    @org.jetbrains.annotations.ApiStatus.Experimental // Paper - add missing annotation
    TUFF_BRICK_WALL(11761, Wall.class),
    @MinecraftExperimental
    @org.jetbrains.annotations.ApiStatus.Experimental // Paper - add missing annotation
    CHISELED_TUFF_BRICKS(8601),
    DRIPSTONE_BLOCK(26227),
    /**
     * BlockData: {@link Snowable}
     */
    GRASS_BLOCK(28346, Snowable.class),
    DIRT(10580),
    COARSE_DIRT(15411),
    /**
     * BlockData: {@link Snowable}
     */
    PODZOL(24068, Snowable.class),
    ROOTED_DIRT(11410),
    MUD(32418),
    CRIMSON_NYLIUM(18139),
    WARPED_NYLIUM(26396),
    COBBLESTONE(32147),
    OAK_PLANKS(14905),
    SPRUCE_PLANKS(14593),
    BIRCH_PLANKS(29322),
    JUNGLE_PLANKS(26445),
    ACACIA_PLANKS(31312),
    CHERRY_PLANKS(8354),
    DARK_OAK_PLANKS(20869),
    MANGROVE_PLANKS(7078),
    BAMBOO_PLANKS(8520),
    CRIMSON_PLANKS(18812),
    WARPED_PLANKS(16045),
    BAMBOO_MOSAIC(10715),
    /**
     * BlockData: {@link Sapling}
     */
    OAK_SAPLING(9636, Sapling.class),
    /**
     * BlockData: {@link Sapling}
     */
    SPRUCE_SAPLING(19874, Sapling.class),
    /**
     * BlockData: {@link Sapling}
     */
    BIRCH_SAPLING(31533, Sapling.class),
    /**
     * BlockData: {@link Sapling}
     */
    JUNGLE_SAPLING(17951, Sapling.class),
    /**
     * BlockData: {@link Sapling}
     */
    ACACIA_SAPLING(20806, Sapling.class),
    /**
     * BlockData: {@link Sapling}
     */
    CHERRY_SAPLING(25204, Sapling.class),
    /**
     * BlockData: {@link Sapling}
     */
    DARK_OAK_SAPLING(14933, Sapling.class),
    /**
     * BlockData: {@link MangrovePropagule}
     */
    MANGROVE_PROPAGULE(18688, MangrovePropagule.class),
    BEDROCK(23130),
    SAND(11542),
    /**
     * BlockData: {@link Brushable}
     */
    SUSPICIOUS_SAND(18410, Brushable.class),
    /**
     * BlockData: {@link Brushable}
     */
    SUSPICIOUS_GRAVEL(7353, Brushable.class),
    RED_SAND(16279),
    GRAVEL(7804),
    COAL_ORE(30965),
    DEEPSLATE_COAL_ORE(16823),
    IRON_ORE(19834),
    DEEPSLATE_IRON_ORE(26021),
    COPPER_ORE(32666),
    DEEPSLATE_COPPER_ORE(6588),
    GOLD_ORE(32625),
    DEEPSLATE_GOLD_ORE(13582),
    /**
     * BlockData: {@link Lightable}
     */
    REDSTONE_ORE(10887, Lightable.class),
    /**
     * BlockData: {@link Lightable}
     */
    DEEPSLATE_REDSTONE_ORE(6331, Lightable.class),
    EMERALD_ORE(16630),
    DEEPSLATE_EMERALD_ORE(5299),
    LAPIS_ORE(22934),
    DEEPSLATE_LAPIS_ORE(13598),
    DIAMOND_ORE(9292),
    DEEPSLATE_DIAMOND_ORE(17792),
    NETHER_GOLD_ORE(4185),
    NETHER_QUARTZ_ORE(4807),
    ANCIENT_DEBRIS(18198),
    COAL_BLOCK(27968),
    RAW_IRON_BLOCK(32210),
    RAW_COPPER_BLOCK(17504),
    RAW_GOLD_BLOCK(23246),
    AMETHYST_BLOCK(18919),
    BUDDING_AMETHYST(13963),
    IRON_BLOCK(24754),
    COPPER_BLOCK(12880),
    GOLD_BLOCK(27392),
    DIAMOND_BLOCK(5944),
    NETHERITE_BLOCK(6527),
    EXPOSED_COPPER(28488),
    WEATHERED_COPPER(19699),
    OXIDIZED_COPPER(19490),
    @MinecraftExperimental
    @org.jetbrains.annotations.ApiStatus.Experimental // Paper - add missing annotation
    CHISELED_COPPER(12143),
    @MinecraftExperimental
    @org.jetbrains.annotations.ApiStatus.Experimental // Paper - add missing annotation
    EXPOSED_CHISELED_COPPER(4570),
    @MinecraftExperimental
    @org.jetbrains.annotations.ApiStatus.Experimental // Paper - add missing annotation
    WEATHERED_CHISELED_COPPER(30876),
    @MinecraftExperimental
    @org.jetbrains.annotations.ApiStatus.Experimental // Paper - add missing annotation
    OXIDIZED_CHISELED_COPPER(27719),
    CUT_COPPER(32519),
    EXPOSED_CUT_COPPER(18000),
    WEATHERED_CUT_COPPER(21158),
    OXIDIZED_CUT_COPPER(5382),
    /**
     * BlockData: {@link Stairs}
     */
    CUT_COPPER_STAIRS(25925, Stairs.class),
    /**
     * BlockData: {@link Stairs}
     */
    EXPOSED_CUT_COPPER_STAIRS(31621, Stairs.class),
    /**
     * BlockData: {@link Stairs}
     */
    WEATHERED_CUT_COPPER_STAIRS(5851, Stairs.class),
    /**
     * BlockData: {@link Stairs}
     */
    OXIDIZED_CUT_COPPER_STAIRS(25379, Stairs.class),
    /**
     * BlockData: {@link Slab}
     */
    CUT_COPPER_SLAB(28988, Slab.class),
    /**
     * BlockData: {@link Slab}
     */
    EXPOSED_CUT_COPPER_SLAB(26694, Slab.class),
    /**
     * BlockData: {@link Slab}
     */
    WEATHERED_CUT_COPPER_SLAB(4602, Slab.class),
    /**
     * BlockData: {@link Slab}
     */
    OXIDIZED_CUT_COPPER_SLAB(29642, Slab.class),
    WAXED_COPPER_BLOCK(14638),
    WAXED_EXPOSED_COPPER(27989),
    WAXED_WEATHERED_COPPER(5960),
    WAXED_OXIDIZED_COPPER(25626),
    @MinecraftExperimental
    @org.jetbrains.annotations.ApiStatus.Experimental // Paper - add missing annotation
    WAXED_CHISELED_COPPER(7500),
    @MinecraftExperimental
    @org.jetbrains.annotations.ApiStatus.Experimental // Paper - add missing annotation
    WAXED_EXPOSED_CHISELED_COPPER(30658),
    @MinecraftExperimental
    @org.jetbrains.annotations.ApiStatus.Experimental // Paper - add missing annotation
    WAXED_WEATHERED_CHISELED_COPPER(5970),
    @MinecraftExperimental
    @org.jetbrains.annotations.ApiStatus.Experimental // Paper - add missing annotation
    WAXED_OXIDIZED_CHISELED_COPPER(7735),
    WAXED_CUT_COPPER(11030),
    WAXED_EXPOSED_CUT_COPPER(30043),
    WAXED_WEATHERED_CUT_COPPER(13823),
    WAXED_OXIDIZED_CUT_COPPER(22582),
    /**
     * BlockData: {@link Stairs}
     */
    WAXED_CUT_COPPER_STAIRS(23125, Stairs.class),
    /**
     * BlockData: {@link Stairs}
     */
    WAXED_EXPOSED_CUT_COPPER_STAIRS(15532, Stairs.class),
    /**
     * BlockData: {@link Stairs}
     */
    WAXED_WEATHERED_CUT_COPPER_STAIRS(29701, Stairs.class),
    /**
     * BlockData: {@link Stairs}
     */
    WAXED_OXIDIZED_CUT_COPPER_STAIRS(9842, Stairs.class),
    /**
     * BlockData: {@link Slab}
     */
    WAXED_CUT_COPPER_SLAB(6271, Slab.class),
    /**
     * BlockData: {@link Slab}
     */
    WAXED_EXPOSED_CUT_COPPER_SLAB(22091, Slab.class),
    /**
     * BlockData: {@link Slab}
     */
    WAXED_WEATHERED_CUT_COPPER_SLAB(20035, Slab.class),
    /**
     * BlockData: {@link Slab}
     */
    WAXED_OXIDIZED_CUT_COPPER_SLAB(11202, Slab.class),
    /**
     * BlockData: {@link Orientable}
     */
    OAK_LOG(26723, Orientable.class),
    /**
     * BlockData: {@link Orientable}
     */
    SPRUCE_LOG(9726, Orientable.class),
    /**
     * BlockData: {@link Orientable}
     */
    BIRCH_LOG(26727, Orientable.class),
    /**
     * BlockData: {@link Orientable}
     */
    JUNGLE_LOG(20721, Orientable.class),
    /**
     * BlockData: {@link Orientable}
     */
    ACACIA_LOG(8385, Orientable.class),
    /**
     * BlockData: {@link Orientable}
     */
    CHERRY_LOG(20847, Orientable.class),
    /**
     * BlockData: {@link Orientable}
     */
    DARK_OAK_LOG(14831, Orientable.class),
    /**
     * BlockData: {@link Orientable}
     */
    MANGROVE_LOG(23890, Orientable.class),
    /**
     * BlockData: {@link Waterlogged}
     */
    MANGROVE_ROOTS(22124, Waterlogged.class),
    /**
     * BlockData: {@link Orientable}
     */
    MUDDY_MANGROVE_ROOTS(23244, Orientable.class),
    /**
     * BlockData: {@link Orientable}
     */
    CRIMSON_STEM(27920, Orientable.class),
    /**
     * BlockData: {@link Orientable}
     */
    WARPED_STEM(28920, Orientable.class),
    /**
     * BlockData: {@link Orientable}
     */
    BAMBOO_BLOCK(20770, Orientable.class),
    /**
     * BlockData: {@link Orientable}
     */
    STRIPPED_OAK_LOG(20523, Orientable.class),
    /**
     * BlockData: {@link Orientable}
     */
    STRIPPED_SPRUCE_LOG(6140, Orientable.class),
    /**
     * BlockData: {@link Orientable}
     */
    STRIPPED_BIRCH_LOG(8838, Orientable.class),
    /**
     * BlockData: {@link Orientable}
     */
    STRIPPED_JUNGLE_LOG(15476, Orientable.class),
    /**
     * BlockData: {@link Orientable}
     */
    STRIPPED_ACACIA_LOG(18167, Orientable.class),
    /**
     * BlockData: {@link Orientable}
     */
    STRIPPED_CHERRY_LOG(18061, Orientable.class),
    /**
     * BlockData: {@link Orientable}
     */
    STRIPPED_DARK_OAK_LOG(6492, Orientable.class),
    /**
     * BlockData: {@link Orientable}
     */
    STRIPPED_MANGROVE_LOG(15197, Orientable.class),
    /**
     * BlockData: {@link Orientable}
     */
    STRIPPED_CRIMSON_STEM(16882, Orientable.class),
    /**
     * BlockData: {@link Orientable}
     */
    STRIPPED_WARPED_STEM(15627, Orientable.class),
    /**
     * BlockData: {@link Orientable}
     */
    STRIPPED_OAK_WOOD(31455, Orientable.class),
    /**
     * BlockData: {@link Orientable}
     */
    STRIPPED_SPRUCE_WOOD(6467, Orientable.class),
    /**
     * BlockData: {@link Orientable}
     */
    STRIPPED_BIRCH_WOOD(22350, Orientable.class),
    /**
     * BlockData: {@link Orientable}
     */
    STRIPPED_JUNGLE_WOOD(30315, Orientable.class),
    /**
     * BlockData: {@link Orientable}
     */
    STRIPPED_ACACIA_WOOD(27193, Orientable.class),
    /**
     * BlockData: {@link Orientable}
     */
    STRIPPED_CHERRY_WOOD(19647, Orientable.class),
    /**
     * BlockData: {@link Orientable}
     */
    STRIPPED_DARK_OAK_WOOD(16000, Orientable.class),
    /**
     * BlockData: {@link Orientable}
     */
    STRIPPED_MANGROVE_WOOD(4828, Orientable.class),
    /**
     * BlockData: {@link Orientable}
     */
    STRIPPED_CRIMSON_HYPHAE(27488, Orientable.class),
    /**
     * BlockData: {@link Orientable}
     */
    STRIPPED_WARPED_HYPHAE(7422, Orientable.class),
    /**
     * BlockData: {@link Orientable}
     */
    STRIPPED_BAMBOO_BLOCK(14799, Orientable.class),
    /**
     * BlockData: {@link Orientable}
     */
    OAK_WOOD(7378, Orientable.class),
    /**
     * BlockData: {@link Orientable}
     */
    SPRUCE_WOOD(32328, Orientable.class),
    /**
     * BlockData: {@link Orientable}
     */
    BIRCH_WOOD(20913, Orientable.class),
    /**
     * BlockData: {@link Orientable}
     */
    JUNGLE_WOOD(10341, Orientable.class),
    /**
     * BlockData: {@link Orientable}
     */
    ACACIA_WOOD(9541, Orientable.class),
    /**
     * BlockData: {@link Orientable}
     */
    CHERRY_WOOD(9826, Orientable.class),
    /**
     * BlockData: {@link Orientable}
     */
    DARK_OAK_WOOD(16995, Orientable.class),
    /**
     * BlockData: {@link Orientable}
     */
    MANGROVE_WOOD(25484, Orientable.class),
    /**
     * BlockData: {@link Orientable}
     */
    CRIMSON_HYPHAE(6550, Orientable.class),
    /**
     * BlockData: {@link Orientable}
     */
    WARPED_HYPHAE(18439, Orientable.class),
    /**
     * BlockData: {@link Leaves}
     */
    OAK_LEAVES(4385, Leaves.class),
    /**
     * BlockData: {@link Leaves}
     */
    SPRUCE_LEAVES(20039, Leaves.class),
    /**
     * BlockData: {@link Leaves}
     */
    BIRCH_LEAVES(12601, Leaves.class),
    /**
     * BlockData: {@link Leaves}
     */
    JUNGLE_LEAVES(5133, Leaves.class),
    /**
     * BlockData: {@link Leaves}
     */
    ACACIA_LEAVES(16606, Leaves.class),
    /**
     * BlockData: {@link Leaves}
     */
    CHERRY_LEAVES(20856, Leaves.class),
    /**
     * BlockData: {@link Leaves}
     */
    DARK_OAK_LEAVES(22254, Leaves.class),
    /**
     * BlockData: {@link Leaves}
     */
    MANGROVE_LEAVES(15310, Leaves.class),
    /**
     * BlockData: {@link Leaves}
     */
    AZALEA_LEAVES(23001, Leaves.class),
    /**
     * BlockData: {@link Leaves}
     */
    FLOWERING_AZALEA_LEAVES(7139, Leaves.class),
    SPONGE(15860),
    WET_SPONGE(9043),
    GLASS(6195),
    TINTED_GLASS(19154),
    LAPIS_BLOCK(14485),
    SANDSTONE(13141),
    CHISELED_SANDSTONE(31763),
    CUT_SANDSTONE(6118),
    COBWEB(9469),
    SHORT_GRASS(6155),
    FERN(15794),
    AZALEA(29386),
    FLOWERING_AZALEA(28270),
    DEAD_BUSH(22888),
    SEAGRASS(23942),
    /**
     * BlockData: {@link SeaPickle}
     */
    SEA_PICKLE(19562, SeaPickle.class),
    WHITE_WOOL(8624),
    ORANGE_WOOL(23957),
    MAGENTA_WOOL(11853),
    LIGHT_BLUE_WOOL(21073),
    YELLOW_WOOL(29507),
    LIME_WOOL(10443),
    PINK_WOOL(7611),
    GRAY_WOOL(27209),
    LIGHT_GRAY_WOOL(22936),
    CYAN_WOOL(12221),
    PURPLE_WOOL(11922),
    BLUE_WOOL(15738),
    BROWN_WOOL(32638),
    GREEN_WOOL(25085),
    RED_WOOL(11621),
    BLACK_WOOL(16693),
    DANDELION(30558),
    POPPY(12851),
    BLUE_ORCHID(13432),
    ALLIUM(6871),
    AZURE_BLUET(17608),
    RED_TULIP(16781),
    ORANGE_TULIP(26038),
    WHITE_TULIP(31495),
    PINK_TULIP(27319),
    OXEYE_DAISY(11709),
    CORNFLOWER(15405),
    LILY_OF_THE_VALLEY(7185),
    WITHER_ROSE(8619),
    TORCHFLOWER(4501),
    /**
     * BlockData: {@link Bisected}
     */
    PITCHER_PLANT(28172, Bisected.class),
    SPORE_BLOSSOM(20627),
    BROWN_MUSHROOM(9665),
    RED_MUSHROOM(19728),
    CRIMSON_FUNGUS(26268),
    WARPED_FUNGUS(19799),
    CRIMSON_ROOTS(14064),
    WARPED_ROOTS(13932),
    NETHER_SPROUTS(10431),
    /**
     * BlockData: {@link Ageable}
     */
    WEEPING_VINES(29267, Ageable.class),
    /**
     * BlockData: {@link Ageable}
     */
    TWISTING_VINES(27283, Ageable.class),
    /**
     * BlockData: {@link Ageable}
     */
    SUGAR_CANE(7726, Ageable.class),
    /**
     * BlockData: {@link Ageable}
     */
    KELP(21916, Ageable.class),
    MOSS_CARPET(8221),
    /**
     * BlockData: {@link PinkPetals}
     */
    PINK_PETALS(10420, PinkPetals.class),
    MOSS_BLOCK(9175),
    /**
     * BlockData: {@link Waterlogged}
     */
    HANGING_ROOTS(15498, Waterlogged.class),
    /**
     * BlockData: {@link BigDripleaf}
     */
    BIG_DRIPLEAF(26173, BigDripleaf.class),
    /**
     * BlockData: {@link SmallDripleaf}
     */
    SMALL_DRIPLEAF(17540, SmallDripleaf.class),
    /**
     * BlockData: {@link Bamboo}
     */
    BAMBOO(18728, Bamboo.class),
    /**
     * BlockData: {@link Slab}
     */
    OAK_SLAB(12002, Slab.class),
    /**
     * BlockData: {@link Slab}
     */
    SPRUCE_SLAB(28798, Slab.class),
    /**
     * BlockData: {@link Slab}
     */
    BIRCH_SLAB(13807, Slab.class),
    /**
     * BlockData: {@link Slab}
     */
    JUNGLE_SLAB(19117, Slab.class),
    /**
     * BlockData: {@link Slab}
     */
    ACACIA_SLAB(23730, Slab.class),
    /**
     * BlockData: {@link Slab}
     */
    CHERRY_SLAB(16673, Slab.class),
    /**
     * BlockData: {@link Slab}
     */
    DARK_OAK_SLAB(28852, Slab.class),
    /**
     * BlockData: {@link Slab}
     */
    MANGROVE_SLAB(13704, Slab.class),
    /**
     * BlockData: {@link Slab}
     */
    BAMBOO_SLAB(17798, Slab.class),
    /**
     * BlockData: {@link Slab}
     */
    BAMBOO_MOSAIC_SLAB(22118, Slab.class),
    /**
     * BlockData: {@link Slab}
     */
    CRIMSON_SLAB(4691, Slab.class),
    /**
     * BlockData: {@link Slab}
     */
    WARPED_SLAB(27150, Slab.class),
    /**
     * BlockData: {@link Slab}
     */
    STONE_SLAB(19838, Slab.class),
    /**
     * BlockData: {@link Slab}
     */
    SMOOTH_STONE_SLAB(24129, Slab.class),
    /**
     * BlockData: {@link Slab}
     */
    SANDSTONE_SLAB(29830, Slab.class),
    /**
     * BlockData: {@link Slab}
     */
    CUT_SANDSTONE_SLAB(30944, Slab.class),
    /**
     * BlockData: {@link Slab}
     */
    PETRIFIED_OAK_SLAB(18658, Slab.class),
    /**
     * BlockData: {@link Slab}
     */
    COBBLESTONE_SLAB(6340, Slab.class),
    /**
     * BlockData: {@link Slab}
     */
    BRICK_SLAB(26333, Slab.class),
    /**
     * BlockData: {@link Slab}
     */
    STONE_BRICK_SLAB(19676, Slab.class),
    /**
     * BlockData: {@link Slab}
     */
    MUD_BRICK_SLAB(10611, Slab.class),
    /**
     * BlockData: {@link Slab}
     */
    NETHER_BRICK_SLAB(26586, Slab.class),
    /**
     * BlockData: {@link Slab}
     */
    QUARTZ_SLAB(4423, Slab.class),
    /**
     * BlockData: {@link Slab}
     */
    RED_SANDSTONE_SLAB(17550, Slab.class),
    /**
     * BlockData: {@link Slab}
     */
    CUT_RED_SANDSTONE_SLAB(7220, Slab.class),
    /**
     * BlockData: {@link Slab}
     */
    PURPUR_SLAB(11487, Slab.class),
    /**
     * BlockData: {@link Slab}
     */
    PRISMARINE_SLAB(31323, Slab.class),
    /**
     * BlockData: {@link Slab}
     */
    PRISMARINE_BRICK_SLAB(25624, Slab.class),
    /**
     * BlockData: {@link Slab}
     */
    DARK_PRISMARINE_SLAB(7577, Slab.class),
    SMOOTH_QUARTZ(14415),
    SMOOTH_RED_SANDSTONE(25180),
    SMOOTH_SANDSTONE(30039),
    SMOOTH_STONE(21910),
    BRICKS(14165),
    BOOKSHELF(10069),
    /**
     * BlockData: {@link ChiseledBookshelf}
     */
    CHISELED_BOOKSHELF(8099, ChiseledBookshelf.class),
    /**
     * BlockData: {@link DecoratedPot}
     */
    DECORATED_POT(8720, DecoratedPot.class),
    MOSSY_COBBLESTONE(21900),
    OBSIDIAN(32723),
    TORCH(6063),
    /**
     * BlockData: {@link Directional}
     */
    END_ROD(24832, Directional.class),
    /**
     * BlockData: {@link MultipleFacing}
     */
    CHORUS_PLANT(28243, MultipleFacing.class),
    /**
     * BlockData: {@link Ageable}
     */
    CHORUS_FLOWER(28542, Ageable.class),
    PURPUR_BLOCK(7538),
    /**
     * BlockData: {@link Orientable}
     */
    PURPUR_PILLAR(26718, Orientable.class),
    /**
     * BlockData: {@link Stairs}
     */
    PURPUR_STAIRS(8921, Stairs.class),
    SPAWNER(7018),
    /**
     * BlockData: {@link Chest}
     */
    CHEST(22969, Chest.class),
    CRAFTING_TABLE(20706),
    /**
     * BlockData: {@link Farmland}
     */
    FARMLAND(31166, Farmland.class),
    /**
     * BlockData: {@link Furnace}
     */
    FURNACE(8133, Furnace.class),
    /**
     * BlockData: {@link Ladder}
     */
    LADDER(23599, Ladder.class),
    /**
     * BlockData: {@link Stairs}
     */
    COBBLESTONE_STAIRS(24715, Stairs.class),
    /**
     * BlockData: {@link Snow}
     */
    SNOW(14146, Snow.class),
    ICE(30428),
    SNOW_BLOCK(19913),
    /**
     * BlockData: {@link Ageable}
     */
    CACTUS(12191, Ageable.class),
    CLAY(27880),
    /**
     * BlockData: {@link Jukebox}
     */
    JUKEBOX(19264, Jukebox.class),
    /**
     * BlockData: {@link Fence}
     */
    OAK_FENCE(6442, Fence.class),
    /**
     * BlockData: {@link Fence}
     */
    SPRUCE_FENCE(25416, Fence.class),
    /**
     * BlockData: {@link Fence}
     */
    BIRCH_FENCE(17347, Fence.class),
    /**
     * BlockData: {@link Fence}
     */
    JUNGLE_FENCE(14358, Fence.class),
    /**
     * BlockData: {@link Fence}
     */
    ACACIA_FENCE(4569, Fence.class),
    /**
     * BlockData: {@link Fence}
     */
    CHERRY_FENCE(32047, Fence.class),
    /**
     * BlockData: {@link Fence}
     */
    DARK_OAK_FENCE(21767, Fence.class),
    /**
     * BlockData: {@link Fence}
     */
    MANGROVE_FENCE(15021, Fence.class),
    /**
     * BlockData: {@link Fence}
     */
    BAMBOO_FENCE(17207, Fence.class),
    /**
     * BlockData: {@link Fence}
     */
    CRIMSON_FENCE(21075, Fence.class),
    /**
     * BlockData: {@link Fence}
     */
    WARPED_FENCE(18438, Fence.class),
    PUMPKIN(19170),
    /**
     * BlockData: {@link Directional}
     */
    CARVED_PUMPKIN(25833, Directional.class),
    /**
     * BlockData: {@link Directional}
     */
    JACK_O_LANTERN(13758, Directional.class),
    NETHERRACK(23425),
    SOUL_SAND(16841),
    SOUL_SOIL(31140),
    /**
     * BlockData: {@link Orientable}
     */
    BASALT(28478, Orientable.class),
    /**
     * BlockData: {@link Orientable}
     */
    POLISHED_BASALT(11659, Orientable.class),
    SMOOTH_BASALT(13617),
    SOUL_TORCH(14292),
    GLOWSTONE(32713),
    INFESTED_STONE(18440),
    INFESTED_COBBLESTONE(4348),
    INFESTED_STONE_BRICKS(19749),
    INFESTED_MOSSY_STONE_BRICKS(9850),
    INFESTED_CRACKED_STONE_BRICKS(7476),
    INFESTED_CHISELED_STONE_BRICKS(4728),
    /**
     * BlockData: {@link Orientable}
     */
    INFESTED_DEEPSLATE(9472, Orientable.class),
    STONE_BRICKS(6962),
    MOSSY_STONE_BRICKS(16415),
    CRACKED_STONE_BRICKS(27869),
    CHISELED_STONE_BRICKS(9087),
    PACKED_MUD(7472),
    MUD_BRICKS(29168),
    DEEPSLATE_BRICKS(13193),
    CRACKED_DEEPSLATE_BRICKS(17105),
    DEEPSLATE_TILES(11250),
    CRACKED_DEEPSLATE_TILES(26249),
    CHISELED_DEEPSLATE(23825),
    REINFORCED_DEEPSLATE(10949),
    /**
     * BlockData: {@link MultipleFacing}
     */
    BROWN_MUSHROOM_BLOCK(6291, MultipleFacing.class),
    /**
     * BlockData: {@link MultipleFacing}
     */
    RED_MUSHROOM_BLOCK(20766, MultipleFacing.class),
    /**
     * BlockData: {@link MultipleFacing}
     */
    MUSHROOM_STEM(16543, MultipleFacing.class),
    /**
     * BlockData: {@link Fence}
     */
    IRON_BARS(9378, Fence.class),
    /**
     * BlockData: {@link Chain}
     */
    CHAIN(28265, Chain.class),
    /**
     * BlockData: {@link Fence}
     */
    GLASS_PANE(5709, Fence.class),
    MELON(25172),
    /**
     * BlockData: {@link MultipleFacing}
     */
    VINE(14564, MultipleFacing.class),
    /**
     * BlockData: {@link GlowLichen}
     */
    GLOW_LICHEN(19165, GlowLichen.class),
    /**
     * BlockData: {@link Stairs}
     */
    BRICK_STAIRS(21534, Stairs.class),
    /**
     * BlockData: {@link Stairs}
     */
    STONE_BRICK_STAIRS(27032, Stairs.class),
    /**
     * BlockData: {@link Stairs}
     */
    MUD_BRICK_STAIRS(13620, Stairs.class),
    /**
     * BlockData: {@link Snowable}
     */
    MYCELIUM(9913, Snowable.class),
    LILY_PAD(19271),
    NETHER_BRICKS(27802),
    CRACKED_NETHER_BRICKS(10888),
    CHISELED_NETHER_BRICKS(21613),
    /**
     * BlockData: {@link Fence}
     */
    NETHER_BRICK_FENCE(5286, Fence.class),
    /**
     * BlockData: {@link Stairs}
     */
    NETHER_BRICK_STAIRS(12085, Stairs.class),
    SCULK(17870),
    /**
     * BlockData: {@link SculkVein}
     */
    SCULK_VEIN(11615, SculkVein.class),
    /**
     * BlockData: {@link SculkCatalyst}
     */
    SCULK_CATALYST(12017, SculkCatalyst.class),
    /**
     * BlockData: {@link SculkShrieker}
     */
    SCULK_SHRIEKER(20985, SculkShrieker.class),
    ENCHANTING_TABLE(16255),
    /**
     * BlockData: {@link EndPortalFrame}
     */
    END_PORTAL_FRAME(15480, EndPortalFrame.class),
    END_STONE(29686),
    END_STONE_BRICKS(20314),
    DRAGON_EGG(29946),
    /**
     * BlockData: {@link Stairs}
     */
    SANDSTONE_STAIRS(18474, Stairs.class),
    /**
     * BlockData: {@link EnderChest}
     */
    ENDER_CHEST(32349, EnderChest.class),
    EMERALD_BLOCK(9914),
    /**
     * BlockData: {@link Stairs}
     */
    OAK_STAIRS(5449, Stairs.class),
    /**
     * BlockData: {@link Stairs}
     */
    SPRUCE_STAIRS(11192, Stairs.class),
    /**
     * BlockData: {@link Stairs}
     */
    BIRCH_STAIRS(7657, Stairs.class),
    /**
     * BlockData: {@link Stairs}
     */
    JUNGLE_STAIRS(20636, Stairs.class),
    /**
     * BlockData: {@link Stairs}
     */
    ACACIA_STAIRS(17453, Stairs.class),
    /**
     * BlockData: {@link Stairs}
     */
    CHERRY_STAIRS(18380, Stairs.class),
    /**
     * BlockData: {@link Stairs}
     */
    DARK_OAK_STAIRS(22921, Stairs.class),
    /**
     * BlockData: {@link Stairs}
     */
    MANGROVE_STAIRS(27641, Stairs.class),
    /**
     * BlockData: {@link Stairs}
     */
    BAMBOO_STAIRS(25674, Stairs.class),
    /**
     * BlockData: {@link Stairs}
     */
    BAMBOO_MOSAIC_STAIRS(20977, Stairs.class),
    /**
     * BlockData: {@link Stairs}
     */
    CRIMSON_STAIRS(32442, Stairs.class),
    /**
     * BlockData: {@link Stairs}
     */
    WARPED_STAIRS(17721, Stairs.class),
    /**
     * BlockData: {@link CommandBlock}
     */
    COMMAND_BLOCK(4355, CommandBlock.class),
    BEACON(6608),
    /**
     * BlockData: {@link Wall}
     */
    COBBLESTONE_WALL(12616, Wall.class),
    /**
     * BlockData: {@link Wall}
     */
    MOSSY_COBBLESTONE_WALL(11536, Wall.class),
    /**
     * BlockData: {@link Wall}
     */
    BRICK_WALL(18995, Wall.class),
    /**
     * BlockData: {@link Wall}
     */
    PRISMARINE_WALL(18184, Wall.class),
    /**
     * BlockData: {@link Wall}
     */
    RED_SANDSTONE_WALL(4753, Wall.class),
    /**
     * BlockData: {@link Wall}
     */
    MOSSY_STONE_BRICK_WALL(18259, Wall.class),
    /**
     * BlockData: {@link Wall}
     */
    GRANITE_WALL(23279, Wall.class),
    /**
     * BlockData: {@link Wall}
     */
    STONE_BRICK_WALL(29073, Wall.class),
    /**
     * BlockData: {@link Wall}
     */
    MUD_BRICK_WALL(18292, Wall.class),
    /**
     * BlockData: {@link Wall}
     */
    NETHER_BRICK_WALL(10398, Wall.class),
    /**
     * BlockData: {@link Wall}
     */
    ANDESITE_WALL(14938, Wall.class),
    /**
     * BlockData: {@link Wall}
     */
    RED_NETHER_BRICK_WALL(4580, Wall.class),
    /**
     * BlockData: {@link Wall}
     */
    SANDSTONE_WALL(18470, Wall.class),
    /**
     * BlockData: {@link Wall}
     */
    END_STONE_BRICK_WALL(27225, Wall.class),
    /**
     * BlockData: {@link Wall}
     */
    DIORITE_WALL(17412, Wall.class),
    /**
     * BlockData: {@link Wall}
     */
    BLACKSTONE_WALL(17327, Wall.class),
    /**
     * BlockData: {@link Wall}
     */
    POLISHED_BLACKSTONE_WALL(15119, Wall.class),
    /**
     * BlockData: {@link Wall}
     */
    POLISHED_BLACKSTONE_BRICK_WALL(9540, Wall.class),
    /**
     * BlockData: {@link Wall}
     */
    COBBLED_DEEPSLATE_WALL(21893, Wall.class),
    /**
     * BlockData: {@link Wall}
     */
    POLISHED_DEEPSLATE_WALL(6574, Wall.class),
    /**
     * BlockData: {@link Wall}
     */
    DEEPSLATE_BRICK_WALL(13304, Wall.class),
    /**
     * BlockData: {@link Wall}
     */
    DEEPSLATE_TILE_WALL(17077, Wall.class),
    /**
     * BlockData: {@link Directional}
     */
    ANVIL(18718, Directional.class),
    /**
     * BlockData: {@link Directional}
     */
    CHIPPED_ANVIL(10623, Directional.class),
    /**
     * BlockData: {@link Directional}
     */
    DAMAGED_ANVIL(10274, Directional.class),
    CHISELED_QUARTZ_BLOCK(30964),
    QUARTZ_BLOCK(11987),
    QUARTZ_BRICKS(23358),
    /**
     * BlockData: {@link Orientable}
     */
    QUARTZ_PILLAR(16452, Orientable.class),
    /**
     * BlockData: {@link Stairs}
     */
    QUARTZ_STAIRS(24079, Stairs.class),
    WHITE_TERRACOTTA(20975),
    ORANGE_TERRACOTTA(18684),
    MAGENTA_TERRACOTTA(25900),
    LIGHT_BLUE_TERRACOTTA(31779),
    YELLOW_TERRACOTTA(32129),
    LIME_TERRACOTTA(24013),
    PINK_TERRACOTTA(23727),
    GRAY_TERRACOTTA(18004),
    LIGHT_GRAY_TERRACOTTA(26388),
    CYAN_TERRACOTTA(25940),
    PURPLE_TERRACOTTA(10387),
    BLUE_TERRACOTTA(5236),
    BROWN_TERRACOTTA(23664),
    GREEN_TERRACOTTA(4105),
    RED_TERRACOTTA(5086),
    BLACK_TERRACOTTA(26691),
    /**
     * BlockData: {@link Waterlogged}
     */
    BARRIER(26453, Waterlogged.class),
    /**
     * BlockData: {@link Light}
     */
    LIGHT(17829, Light.class),
    /**
     * BlockData: {@link Orientable}
     */
    HAY_BLOCK(17461, Orientable.class),
    WHITE_CARPET(15117),
    ORANGE_CARPET(24752),
    MAGENTA_CARPET(6180),
    LIGHT_BLUE_CARPET(21194),
    YELLOW_CARPET(18149),
    LIME_CARPET(15443),
    PINK_CARPET(27381),
    GRAY_CARPET(26991),
    LIGHT_GRAY_CARPET(11317),
    CYAN_CARPET(9742),
    PURPLE_CARPET(5574),
    BLUE_CARPET(13292),
    BROWN_CARPET(23352),
    GREEN_CARPET(7780),
    RED_CARPET(5424),
    BLACK_CARPET(6056),
    TERRACOTTA(16544),
    PACKED_ICE(28993),
    DIRT_PATH(10846),
    /**
     * BlockData: {@link Bisected}
     */
    SUNFLOWER(7408, Bisected.class),
    /**
     * BlockData: {@link Bisected}
     */
    LILAC(22837, Bisected.class),
    /**
     * BlockData: {@link Bisected}
     */
    ROSE_BUSH(6080, Bisected.class),
    /**
     * BlockData: {@link Bisected}
     */
    PEONY(21155, Bisected.class),
    /**
     * BlockData: {@link Bisected}
     */
    TALL_GRASS(21559, Bisected.class),
    /**
     * BlockData: {@link Bisected}
     */
    LARGE_FERN(30177, Bisected.class),
    WHITE_STAINED_GLASS(31190),
    ORANGE_STAINED_GLASS(25142),
    MAGENTA_STAINED_GLASS(26814),
    LIGHT_BLUE_STAINED_GLASS(17162),
    YELLOW_STAINED_GLASS(12182),
    LIME_STAINED_GLASS(24266),
    PINK_STAINED_GLASS(16164),
    GRAY_STAINED_GLASS(29979),
    LIGHT_GRAY_STAINED_GLASS(5843),
    CYAN_STAINED_GLASS(30604),
    PURPLE_STAINED_GLASS(21845),
    BLUE_STAINED_GLASS(7107),
    BROWN_STAINED_GLASS(20945),
    GREEN_STAINED_GLASS(22503),
    RED_STAINED_GLASS(9717),
    BLACK_STAINED_GLASS(13941),
    /**
     * BlockData: {@link GlassPane}
     */
    WHITE_STAINED_GLASS_PANE(10557, GlassPane.class),
    /**
     * BlockData: {@link GlassPane}
     */
    ORANGE_STAINED_GLASS_PANE(21089, GlassPane.class),
    /**
     * BlockData: {@link GlassPane}
     */
    MAGENTA_STAINED_GLASS_PANE(14082, GlassPane.class),
    /**
     * BlockData: {@link GlassPane}
     */
    LIGHT_BLUE_STAINED_GLASS_PANE(18721, GlassPane.class),
    /**
     * BlockData: {@link GlassPane}
     */
    YELLOW_STAINED_GLASS_PANE(20298, GlassPane.class),
    /**
     * BlockData: {@link GlassPane}
     */
    LIME_STAINED_GLASS_PANE(10610, GlassPane.class),
    /**
     * BlockData: {@link GlassPane}
     */
    PINK_STAINED_GLASS_PANE(24637, GlassPane.class),
    /**
     * BlockData: {@link GlassPane}
     */
    GRAY_STAINED_GLASS_PANE(25272, GlassPane.class),
    /**
     * BlockData: {@link GlassPane}
     */
    LIGHT_GRAY_STAINED_GLASS_PANE(19008, GlassPane.class),
    /**
     * BlockData: {@link GlassPane}
     */
    CYAN_STAINED_GLASS_PANE(11784, GlassPane.class),
    /**
     * BlockData: {@link GlassPane}
     */
    PURPLE_STAINED_GLASS_PANE(10948, GlassPane.class),
    /**
     * BlockData: {@link GlassPane}
     */
    BLUE_STAINED_GLASS_PANE(28484, GlassPane.class),
    /**
     * BlockData: {@link GlassPane}
     */
    BROWN_STAINED_GLASS_PANE(17557, GlassPane.class),
    /**
     * BlockData: {@link GlassPane}
     */
    GREEN_STAINED_GLASS_PANE(4767, GlassPane.class),
    /**
     * BlockData: {@link GlassPane}
     */
    RED_STAINED_GLASS_PANE(8630, GlassPane.class),
    /**
     * BlockData: {@link GlassPane}
     */
    BLACK_STAINED_GLASS_PANE(13201, GlassPane.class),
    PRISMARINE(7539),
    PRISMARINE_BRICKS(29118),
    DARK_PRISMARINE(19940),
    /**
     * BlockData: {@link Stairs}
     */
    PRISMARINE_STAIRS(19217, Stairs.class),
    /**
     * BlockData: {@link Stairs}
     */
    PRISMARINE_BRICK_STAIRS(15445, Stairs.class),
    /**
     * BlockData: {@link Stairs}
     */
    DARK_PRISMARINE_STAIRS(26511, Stairs.class),
    SEA_LANTERN(20780),
    RED_SANDSTONE(9092),
    CHISELED_RED_SANDSTONE(15529),
    CUT_RED_SANDSTONE(29108),
    /**
     * BlockData: {@link Stairs}
     */
    RED_SANDSTONE_STAIRS(25466, Stairs.class),
    /**
     * BlockData: {@link CommandBlock}
     */
    REPEATING_COMMAND_BLOCK(12405, CommandBlock.class),
    /**
     * BlockData: {@link CommandBlock}
     */
    CHAIN_COMMAND_BLOCK(26798, CommandBlock.class),
    MAGMA_BLOCK(25927),
    NETHER_WART_BLOCK(15486),
    WARPED_WART_BLOCK(15463),
    RED_NETHER_BRICKS(18056),
    /**
     * BlockData: {@link Orientable}
     */
    BONE_BLOCK(17312, Orientable.class),
    STRUCTURE_VOID(30806),
    /**
     * BlockData: {@link Directional}
     */
    SHULKER_BOX(7776, 1, Directional.class),
    /**
     * BlockData: {@link Directional}
     */
    WHITE_SHULKER_BOX(31750, 1, Directional.class),
    /**
     * BlockData: {@link Directional}
     */
    ORANGE_SHULKER_BOX(21673, 1, Directional.class),
    /**
     * BlockData: {@link Directional}
     */
    MAGENTA_SHULKER_BOX(21566, 1, Directional.class),
    /**
     * BlockData: {@link Directional}
     */
    LIGHT_BLUE_SHULKER_BOX(18226, 1, Directional.class),
    /**
     * BlockData: {@link Directional}
     */
    YELLOW_SHULKER_BOX(28700, 1, Directional.class),
    /**
     * BlockData: {@link Directional}
     */
    LIME_SHULKER_BOX(28360, 1, Directional.class),
    /**
     * BlockData: {@link Directional}
     */
    PINK_SHULKER_BOX(24968, 1, Directional.class),
    /**
     * BlockData: {@link Directional}
     */
    GRAY_SHULKER_BOX(12754, 1, Directional.class),
    /**
     * BlockData: {@link Directional}
     */
    LIGHT_GRAY_SHULKER_BOX(21345, 1, Directional.class),
    /**
     * BlockData: {@link Directional}
     */
    CYAN_SHULKER_BOX(28123, 1, Directional.class),
    /**
     * BlockData: {@link Directional}
     */
    PURPLE_SHULKER_BOX(10373, 1, Directional.class),
    /**
     * BlockData: {@link Directional}
     */
    BLUE_SHULKER_BOX(11476, 1, Directional.class),
    /**
     * BlockData: {@link Directional}
     */
    BROWN_SHULKER_BOX(24230, 1, Directional.class),
    /**
     * BlockData: {@link Directional}
     */
    GREEN_SHULKER_BOX(9377, 1, Directional.class),
    /**
     * BlockData: {@link Directional}
     */
    RED_SHULKER_BOX(32448, 1, Directional.class),
    /**
     * BlockData: {@link Directional}
     */
    BLACK_SHULKER_BOX(24076, 1, Directional.class),
    /**
     * BlockData: {@link Directional}
     */
    WHITE_GLAZED_TERRACOTTA(11326, Directional.class),
    /**
     * BlockData: {@link Directional}
     */
    ORANGE_GLAZED_TERRACOTTA(27451, Directional.class),
    /**
     * BlockData: {@link Directional}
     */
    MAGENTA_GLAZED_TERRACOTTA(8067, Directional.class),
    /**
     * BlockData: {@link Directional}
     */
    LIGHT_BLUE_GLAZED_TERRACOTTA(4336, Directional.class),
    /**
     * BlockData: {@link Directional}
     */
    YELLOW_GLAZED_TERRACOTTA(10914, Directional.class),
    /**
     * BlockData: {@link Directional}
     */
    LIME_GLAZED_TERRACOTTA(13861, Directional.class),
    /**
     * BlockData: {@link Directional}
     */
    PINK_GLAZED_TERRACOTTA(10260, Directional.class),
    /**
     * BlockData: {@link Directional}
     */
    GRAY_GLAZED_TERRACOTTA(6256, Directional.class),
    /**
     * BlockData: {@link Directional}
     */
    LIGHT_GRAY_GLAZED_TERRACOTTA(10707, Directional.class),
    /**
     * BlockData: {@link Directional}
     */
    CYAN_GLAZED_TERRACOTTA(9550, Directional.class),
    /**
     * BlockData: {@link Directional}
     */
    PURPLE_GLAZED_TERRACOTTA(4818, Directional.class),
    /**
     * BlockData: {@link Directional}
     */
    BLUE_GLAZED_TERRACOTTA(23823, Directional.class),
    /**
     * BlockData: {@link Directional}
     */
    BROWN_GLAZED_TERRACOTTA(5655, Directional.class),
    /**
     * BlockData: {@link Directional}
     */
    GREEN_GLAZED_TERRACOTTA(6958, Directional.class),
    /**
     * BlockData: {@link Directional}
     */
    RED_GLAZED_TERRACOTTA(24989, Directional.class),
    /**
     * BlockData: {@link Directional}
     */
    BLACK_GLAZED_TERRACOTTA(29678, Directional.class),
    WHITE_CONCRETE(6281),
    ORANGE_CONCRETE(19914),
    MAGENTA_CONCRETE(20591),
    LIGHT_BLUE_CONCRETE(29481),
    YELLOW_CONCRETE(15722),
    LIME_CONCRETE(5863),
    PINK_CONCRETE(5227),
    GRAY_CONCRETE(13959),
    LIGHT_GRAY_CONCRETE(14453),
    CYAN_CONCRETE(26522),
    PURPLE_CONCRETE(20623),
    BLUE_CONCRETE(18756),
    BROWN_CONCRETE(19006),
    GREEN_CONCRETE(17949),
    RED_CONCRETE(8032),
    BLACK_CONCRETE(13338),
    WHITE_CONCRETE_POWDER(10363),
    ORANGE_CONCRETE_POWDER(30159),
    MAGENTA_CONCRETE_POWDER(8272),
    LIGHT_BLUE_CONCRETE_POWDER(31206),
    YELLOW_CONCRETE_POWDER(10655),
    LIME_CONCRETE_POWDER(28859),
    PINK_CONCRETE_POWDER(6421),
    GRAY_CONCRETE_POWDER(13031),
    LIGHT_GRAY_CONCRETE_POWDER(21589),
    CYAN_CONCRETE_POWDER(15734),
    PURPLE_CONCRETE_POWDER(26808),
    BLUE_CONCRETE_POWDER(17773),
    BROWN_CONCRETE_POWDER(21485),
    GREEN_CONCRETE_POWDER(6904),
    RED_CONCRETE_POWDER(13286),
    BLACK_CONCRETE_POWDER(16150),
    /**
     * BlockData: {@link TurtleEgg}
     */
    TURTLE_EGG(32101, TurtleEgg.class),
    /**
     * BlockData: {@link Hatchable}
     */
    SNIFFER_EGG(12980, Hatchable.class),
    DEAD_TUBE_CORAL_BLOCK(28350),
    DEAD_BRAIN_CORAL_BLOCK(12979),
    DEAD_BUBBLE_CORAL_BLOCK(28220),
    DEAD_FIRE_CORAL_BLOCK(5307),
    DEAD_HORN_CORAL_BLOCK(15103),
    TUBE_CORAL_BLOCK(23723),
    BRAIN_CORAL_BLOCK(30618),
    BUBBLE_CORAL_BLOCK(15437),
    FIRE_CORAL_BLOCK(12119),
    HORN_CORAL_BLOCK(19958),
    /**
     * BlockData: {@link Waterlogged}
     */
    TUBE_CORAL(23048, Waterlogged.class),
    /**
     * BlockData: {@link Waterlogged}
     */
    BRAIN_CORAL(31316, Waterlogged.class),
    /**
     * BlockData: {@link Waterlogged}
     */
    BUBBLE_CORAL(12464, Waterlogged.class),
    /**
     * BlockData: {@link Waterlogged}
     */
    FIRE_CORAL(29151, Waterlogged.class),
    /**
     * BlockData: {@link Waterlogged}
     */
    HORN_CORAL(19511, Waterlogged.class),
    /**
     * BlockData: {@link Waterlogged}
     */
    DEAD_BRAIN_CORAL(9116, Waterlogged.class),
    /**
     * BlockData: {@link Waterlogged}
     */
    DEAD_BUBBLE_CORAL(30583, Waterlogged.class),
    /**
     * BlockData: {@link Waterlogged}
     */
    DEAD_FIRE_CORAL(8365, Waterlogged.class),
    /**
     * BlockData: {@link Waterlogged}
     */
    DEAD_HORN_CORAL(5755, Waterlogged.class),
    /**
     * BlockData: {@link Waterlogged}
     */
    DEAD_TUBE_CORAL(18028, Waterlogged.class),
    /**
     * BlockData: {@link Waterlogged}
     */
    TUBE_CORAL_FAN(19929, Waterlogged.class),
    /**
     * BlockData: {@link Waterlogged}
     */
    BRAIN_CORAL_FAN(13849, Waterlogged.class),
    /**
     * BlockData: {@link Waterlogged}
     */
    BUBBLE_CORAL_FAN(10795, Waterlogged.class),
    /**
     * BlockData: {@link Waterlogged}
     */
    FIRE_CORAL_FAN(11112, Waterlogged.class),
    /**
     * BlockData: {@link Waterlogged}
     */
    HORN_CORAL_FAN(13610, Waterlogged.class),
    /**
     * BlockData: {@link Waterlogged}
     */
    DEAD_TUBE_CORAL_FAN(17628, Waterlogged.class),
    /**
     * BlockData: {@link Waterlogged}
     */
    DEAD_BRAIN_CORAL_FAN(26150, Waterlogged.class),
    /**
     * BlockData: {@link Waterlogged}
     */
    DEAD_BUBBLE_CORAL_FAN(17322, Waterlogged.class),
    /**
     * BlockData: {@link Waterlogged}
     */
    DEAD_FIRE_CORAL_FAN(27073, Waterlogged.class),
    /**
     * BlockData: {@link Waterlogged}
     */
    DEAD_HORN_CORAL_FAN(11387, Waterlogged.class),
    BLUE_ICE(22449),
    /**
     * BlockData: {@link Waterlogged}
     */
    CONDUIT(5148, Waterlogged.class),
    /**
     * BlockData: {@link Stairs}
     */
    POLISHED_GRANITE_STAIRS(29588, Stairs.class),
    /**
     * BlockData: {@link Stairs}
     */
    SMOOTH_RED_SANDSTONE_STAIRS(17561, Stairs.class),
    /**
     * BlockData: {@link Stairs}
     */
    MOSSY_STONE_BRICK_STAIRS(27578, Stairs.class),
    /**
     * BlockData: {@link Stairs}
     */
    POLISHED_DIORITE_STAIRS(4625, Stairs.class),
    /**
     * BlockData: {@link Stairs}
     */
    MOSSY_COBBLESTONE_STAIRS(29210, Stairs.class),
    /**
     * BlockData: {@link Stairs}
     */
    END_STONE_BRICK_STAIRS(28831, Stairs.class),
    /**
     * BlockData: {@link Stairs}
     */
    STONE_STAIRS(23784, Stairs.class),
    /**
     * BlockData: {@link Stairs}
     */
    SMOOTH_SANDSTONE_STAIRS(21183, Stairs.class),
    /**
     * BlockData: {@link Stairs}
     */
    SMOOTH_QUARTZ_STAIRS(19560, Stairs.class),
    /**
     * BlockData: {@link Stairs}
     */
    GRANITE_STAIRS(21840, Stairs.class),
    /**
     * BlockData: {@link Stairs}
     */
    ANDESITE_STAIRS(17747, Stairs.class),
    /**
     * BlockData: {@link Stairs}
     */
    RED_NETHER_BRICK_STAIRS(26374, Stairs.class),
    /**
     * BlockData: {@link Stairs}
     */
    POLISHED_ANDESITE_STAIRS(7573, Stairs.class),
    /**
     * BlockData: {@link Stairs}
     */
    DIORITE_STAIRS(13134, Stairs.class),
    /**
     * BlockData: {@link Stairs}
     */
    COBBLED_DEEPSLATE_STAIRS(20699, Stairs.class),
    /**
     * BlockData: {@link Stairs}
     */
    POLISHED_DEEPSLATE_STAIRS(19513, Stairs.class),
    /**
     * BlockData: {@link Stairs}
     */
    DEEPSLATE_BRICK_STAIRS(29624, Stairs.class),
    /**
     * BlockData: {@link Stairs}
     */
    DEEPSLATE_TILE_STAIRS(6361, Stairs.class),
    /**
     * BlockData: {@link Slab}
     */
    POLISHED_GRANITE_SLAB(4521, Slab.class),
    /**
     * BlockData: {@link Slab}
     */
    SMOOTH_RED_SANDSTONE_SLAB(16304, Slab.class),
    /**
     * BlockData: {@link Slab}
     */
    MOSSY_STONE_BRICK_SLAB(14002, Slab.class),
    /**
     * BlockData: {@link Slab}
     */
    POLISHED_DIORITE_SLAB(18303, Slab.class),
    /**
     * BlockData: {@link Slab}
     */
    MOSSY_COBBLESTONE_SLAB(12139, Slab.class),
    /**
     * BlockData: {@link Slab}
     */
    END_STONE_BRICK_SLAB(23239, Slab.class),
    /**
     * BlockData: {@link Slab}
     */
    SMOOTH_SANDSTONE_SLAB(9030, Slab.class),
    /**
     * BlockData: {@link Slab}
     */
    SMOOTH_QUARTZ_SLAB(26543, Slab.class),
    /**
     * BlockData: {@link Slab}
     */
    GRANITE_SLAB(10901, Slab.class),
    /**
     * BlockData: {@link Slab}
     */
    ANDESITE_SLAB(32124, Slab.class),
    /**
     * BlockData: {@link Slab}
     */
    RED_NETHER_BRICK_SLAB(12462, Slab.class),
    /**
     * BlockData: {@link Slab}
     */
    POLISHED_ANDESITE_SLAB(24573, Slab.class),
    /**
     * BlockData: {@link Slab}
     */
    DIORITE_SLAB(25526, Slab.class),
    /**
     * BlockData: {@link Slab}
     */
    COBBLED_DEEPSLATE_SLAB(17388, Slab.class),
    /**
     * BlockData: {@link Slab}
     */
    POLISHED_DEEPSLATE_SLAB(32201, Slab.class),
    /**
     * BlockData: {@link Slab}
     */
    DEEPSLATE_BRICK_SLAB(23910, Slab.class),
    /**
     * BlockData: {@link Slab}
     */
    DEEPSLATE_TILE_SLAB(13315, Slab.class),
    /**
     * BlockData: {@link Scaffolding}
     */
    SCAFFOLDING(15757, Scaffolding.class),
    REDSTONE(11233),
    /**
     * BlockData: {@link Lightable}
     */
    REDSTONE_TORCH(22547, Lightable.class),
    REDSTONE_BLOCK(19496),
    /**
     * BlockData: {@link Repeater}
     */
    REPEATER(28823, Repeater.class),
    /**
     * BlockData: {@link Comparator}
     */
    COMPARATOR(18911, Comparator.class),
    /**
     * BlockData: {@link Piston}
     */
    PISTON(21130, Piston.class),
    /**
     * BlockData: {@link Piston}
     */
    STICKY_PISTON(18127, Piston.class),
    SLIME_BLOCK(31892),
    HONEY_BLOCK(30615),
    /**
     * BlockData: {@link Observer}
     */
    OBSERVER(10726, Observer.class),
    /**
     * BlockData: {@link Hopper}
     */
    HOPPER(31974, Hopper.class),
    /**
     * BlockData: {@link Dispenser}
     */
    DISPENSER(20871, Dispenser.class),
    /**
     * BlockData: {@link Dispenser}
     */
    DROPPER(31273, Dispenser.class),
    /**
     * BlockData: {@link Lectern}
     */
    LECTERN(23490, Lectern.class),
    /**
     * BlockData: {@link AnaloguePowerable}
     */
    TARGET(22637, AnaloguePowerable.class),
    /**
     * BlockData: {@link Switch}
     */
    LEVER(15319, Switch.class),
    /**
     * BlockData: {@link LightningRod}
     */
    LIGHTNING_ROD(30770, LightningRod.class),
    /**
     * BlockData: {@link DaylightDetector}
     */
    DAYLIGHT_DETECTOR(8864, DaylightDetector.class),
    /**
     * BlockData: {@link SculkSensor}
     */
    SCULK_SENSOR(5598, SculkSensor.class),
    /**
     * BlockData: {@link CalibratedSculkSensor}
     */
    CALIBRATED_SCULK_SENSOR(21034, CalibratedSculkSensor.class),
    /**
     * BlockData: {@link TripwireHook}
     */
    TRIPWIRE_HOOK(8130, TripwireHook.class),
    /**
     * BlockData: {@link Chest}
     */
    TRAPPED_CHEST(18970, Chest.class),
    /**
     * BlockData: {@link TNT}
     */
    TNT(7896, TNT.class),
    /**
     * BlockData: {@link Lightable}
     */
    REDSTONE_LAMP(8217, Lightable.class),
    /**
     * BlockData: {@link NoteBlock}
     */
    NOTE_BLOCK(20979, NoteBlock.class),
    /**
     * BlockData: {@link Switch}
     */
    STONE_BUTTON(12279, Switch.class),
    /**
     * BlockData: {@link Switch}
     */
    POLISHED_BLACKSTONE_BUTTON(20760, Switch.class),
    /**
     * BlockData: {@link Switch}
     */
    OAK_BUTTON(13510, Switch.class),
    /**
     * BlockData: {@link Switch}
     */
    SPRUCE_BUTTON(23281, Switch.class),
    /**
     * BlockData: {@link Switch}
     */
    BIRCH_BUTTON(26934, Switch.class),
    /**
     * BlockData: {@link Switch}
     */
    JUNGLE_BUTTON(25317, Switch.class),
    /**
     * BlockData: {@link Switch}
     */
    ACACIA_BUTTON(13993, Switch.class),
    /**
     * BlockData: {@link Switch}
     */
    CHERRY_BUTTON(9058, Switch.class),
    /**
     * BlockData: {@link Switch}
     */
    DARK_OAK_BUTTON(6214, Switch.class),
    /**
     * BlockData: {@link Switch}
     */
    MANGROVE_BUTTON(9838, Switch.class),
    /**
     * BlockData: {@link Switch}
     */
    BAMBOO_BUTTON(21810, Switch.class),
    /**
     * BlockData: {@link Switch}
     */
    CRIMSON_BUTTON(26799, Switch.class),
    /**
     * BlockData: {@link Switch}
     */
    WARPED_BUTTON(25264, Switch.class),
    /**
     * BlockData: {@link Powerable}
     */
    STONE_PRESSURE_PLATE(22591, Powerable.class),
    /**
     * BlockData: {@link Powerable}
     */
    POLISHED_BLACKSTONE_PRESSURE_PLATE(32340, Powerable.class),
    /**
     * BlockData: {@link AnaloguePowerable}
     */
    LIGHT_WEIGHTED_PRESSURE_PLATE(14875, AnaloguePowerable.class),
    /**
     * BlockData: {@link AnaloguePowerable}
     */
    HEAVY_WEIGHTED_PRESSURE_PLATE(16970, AnaloguePowerable.class),
    /**
     * BlockData: {@link Powerable}
     */
    OAK_PRESSURE_PLATE(20108, Powerable.class),
    /**
     * BlockData: {@link Powerable}
     */
    SPRUCE_PRESSURE_PLATE(15932, Powerable.class),
    /**
     * BlockData: {@link Powerable}
     */
    BIRCH_PRESSURE_PLATE(9664, Powerable.class),
    /**
     * BlockData: {@link Powerable}
     */
    JUNGLE_PRESSURE_PLATE(11376, Powerable.class),
    /**
     * BlockData: {@link Powerable}
     */
    ACACIA_PRESSURE_PLATE(17586, Powerable.class),
    /**
     * BlockData: {@link Powerable}
     */
    CHERRY_PRESSURE_PLATE(8651, Powerable.class),
    /**
     * BlockData: {@link Powerable}
     */
    DARK_OAK_PRESSURE_PLATE(31375, Powerable.class),
    /**
     * BlockData: {@link Powerable}
     */
    MANGROVE_PRESSURE_PLATE(9748, Powerable.class),
    /**
     * BlockData: {@link Powerable}
     */
    BAMBOO_PRESSURE_PLATE(26740, Powerable.class),
    /**
     * BlockData: {@link Powerable}
     */
    CRIMSON_PRESSURE_PLATE(18316, Powerable.class),
    /**
     * BlockData: {@link Powerable}
     */
    WARPED_PRESSURE_PLATE(29516, Powerable.class),
    /**
     * BlockData: {@link Door}
     */
    IRON_DOOR(4788, Door.class),
    /**
     * BlockData: {@link Door}
     */
    OAK_DOOR(20341, Door.class),
    /**
     * BlockData: {@link Door}
     */
    SPRUCE_DOOR(10642, Door.class),
    /**
     * BlockData: {@link Door}
     */
    BIRCH_DOOR(14759, Door.class),
    /**
     * BlockData: {@link Door}
     */
    JUNGLE_DOOR(28163, Door.class),
    /**
     * BlockData: {@link Door}
     */
    ACACIA_DOOR(23797, Door.class),
    /**
     * BlockData: {@link Door}
     */
    CHERRY_DOOR(12684, Door.class),
    /**
     * BlockData: {@link Door}
     */
    DARK_OAK_DOOR(10669, Door.class),
    /**
     * BlockData: {@link Door}
     */
    MANGROVE_DOOR(18964, Door.class),
    /**
     * BlockData: {@link Door}
     */
    BAMBOO_DOOR(19971, Door.class),
    /**
     * BlockData: {@link Door}
     */
    CRIMSON_DOOR(19544, Door.class),
    /**
     * BlockData: {@link Door}
     */
    WARPED_DOOR(15062, Door.class),
    /**
     * BlockData: {@link Door}
     */
    @MinecraftExperimental
    @org.jetbrains.annotations.ApiStatus.Experimental // Paper - add missing annotation
    COPPER_DOOR(26809, Door.class),
    /**
     * BlockData: {@link Door}
     */
    @MinecraftExperimental
    @org.jetbrains.annotations.ApiStatus.Experimental // Paper - add missing annotation
    EXPOSED_COPPER_DOOR(13236, Door.class),
    /**
     * BlockData: {@link Door}
     */
    @MinecraftExperimental
    @org.jetbrains.annotations.ApiStatus.Experimental // Paper - add missing annotation
    WEATHERED_COPPER_DOOR(10208, Door.class),
    /**
     * BlockData: {@link Door}
     */
    @MinecraftExperimental
    @org.jetbrains.annotations.ApiStatus.Experimental // Paper - add missing annotation
    OXIDIZED_COPPER_DOOR(5348, Door.class),
    /**
     * BlockData: {@link Door}
     */
    @MinecraftExperimental
    @org.jetbrains.annotations.ApiStatus.Experimental // Paper - add missing annotation
    WAXED_COPPER_DOOR(9954, Door.class),
    /**
     * BlockData: {@link Door}
     */
    @MinecraftExperimental
    @org.jetbrains.annotations.ApiStatus.Experimental // Paper - add missing annotation
    WAXED_EXPOSED_COPPER_DOOR(20748, Door.class),
    /**
     * BlockData: {@link Door}
     */
    @MinecraftExperimental
    @org.jetbrains.annotations.ApiStatus.Experimental // Paper - add missing annotation
    WAXED_WEATHERED_COPPER_DOOR(25073, Door.class),
    /**
     * BlockData: {@link Door}
     */
    @MinecraftExperimental
    @org.jetbrains.annotations.ApiStatus.Experimental // Paper - add missing annotation
    WAXED_OXIDIZED_COPPER_DOOR(23888, Door.class),
    /**
     * BlockData: {@link TrapDoor}
     */
    IRON_TRAPDOOR(17095, TrapDoor.class),
    /**
     * BlockData: {@link TrapDoor}
     */
    OAK_TRAPDOOR(16927, TrapDoor.class),
    /**
     * BlockData: {@link TrapDoor}
     */
    SPRUCE_TRAPDOOR(10289, TrapDoor.class),
    /**
     * BlockData: {@link TrapDoor}
     */
    BIRCH_TRAPDOOR(32585, TrapDoor.class),
    /**
     * BlockData: {@link TrapDoor}
     */
    JUNGLE_TRAPDOOR(8626, TrapDoor.class),
    /**
     * BlockData: {@link TrapDoor}
     */
    ACACIA_TRAPDOOR(18343, TrapDoor.class),
    /**
     * BlockData: {@link TrapDoor}
     */
    CHERRY_TRAPDOOR(6293, TrapDoor.class),
    /**
     * BlockData: {@link TrapDoor}
     */
    DARK_OAK_TRAPDOOR(10355, TrapDoor.class),
    /**
     * BlockData: {@link TrapDoor}
     */
    MANGROVE_TRAPDOOR(17066, TrapDoor.class),
    /**
     * BlockData: {@link TrapDoor}
     */
    BAMBOO_TRAPDOOR(9174, TrapDoor.class),
    /**
     * BlockData: {@link TrapDoor}
     */
    CRIMSON_TRAPDOOR(25056, TrapDoor.class),
    /**
     * BlockData: {@link TrapDoor}
     */
    WARPED_TRAPDOOR(7708, TrapDoor.class),
    /**
     * BlockData: {@link TrapDoor}
     */
    @MinecraftExperimental
    @org.jetbrains.annotations.ApiStatus.Experimental // Paper - add missing annotation
    COPPER_TRAPDOOR(12110, TrapDoor.class),
    /**
     * BlockData: {@link TrapDoor}
     */
    @MinecraftExperimental
    @org.jetbrains.annotations.ApiStatus.Experimental // Paper - add missing annotation
    EXPOSED_COPPER_TRAPDOOR(19219, TrapDoor.class),
    /**
     * BlockData: {@link TrapDoor}
     */
    @MinecraftExperimental
    @org.jetbrains.annotations.ApiStatus.Experimental // Paper - add missing annotation
    WEATHERED_COPPER_TRAPDOOR(28254, TrapDoor.class),
    /**
     * BlockData: {@link TrapDoor}
     */
    @MinecraftExperimental
    @org.jetbrains.annotations.ApiStatus.Experimental // Paper - add missing annotation
    OXIDIZED_COPPER_TRAPDOOR(26518, TrapDoor.class),
    /**
     * BlockData: {@link TrapDoor}
     */
    @MinecraftExperimental
    @org.jetbrains.annotations.ApiStatus.Experimental // Paper - add missing annotation
    WAXED_COPPER_TRAPDOOR(12626, TrapDoor.class),
    /**
     * BlockData: {@link TrapDoor}
     */
    @MinecraftExperimental
    @org.jetbrains.annotations.ApiStatus.Experimental // Paper - add missing annotation
    WAXED_EXPOSED_COPPER_TRAPDOOR(11010, TrapDoor.class),
    /**
     * BlockData: {@link TrapDoor}
     */
    @MinecraftExperimental
    @org.jetbrains.annotations.ApiStatus.Experimental // Paper - add missing annotation
    WAXED_WEATHERED_COPPER_TRAPDOOR(30709, TrapDoor.class),
    /**
     * BlockData: {@link TrapDoor}
     */
    @MinecraftExperimental
    @org.jetbrains.annotations.ApiStatus.Experimental // Paper - add missing annotation
    WAXED_OXIDIZED_COPPER_TRAPDOOR(21450, TrapDoor.class),
    /**
     * BlockData: {@link Gate}
     */
    OAK_FENCE_GATE(16689, Gate.class),
    /**
     * BlockData: {@link Gate}
     */
    SPRUCE_FENCE_GATE(26423, Gate.class),
    /**
     * BlockData: {@link Gate}
     */
    BIRCH_FENCE_GATE(6322, Gate.class),
    /**
     * BlockData: {@link Gate}
     */
    JUNGLE_FENCE_GATE(21360, Gate.class),
    /**
     * BlockData: {@link Gate}
     */
    ACACIA_FENCE_GATE(14145, Gate.class),
    /**
     * BlockData: {@link Gate}
     */
    CHERRY_FENCE_GATE(28222, Gate.class),
    /**
     * BlockData: {@link Gate}
     */
    DARK_OAK_FENCE_GATE(10679, Gate.class),
    /**
     * BlockData: {@link Gate}
     */
    MANGROVE_FENCE_GATE(28476, Gate.class),
    /**
     * BlockData: {@link Gate}
     */
    BAMBOO_FENCE_GATE(14290, Gate.class),
    /**
     * BlockData: {@link Gate}
     */
    CRIMSON_FENCE_GATE(15602, Gate.class),
    /**
     * BlockData: {@link Gate}
     */
    WARPED_FENCE_GATE(11115, Gate.class),
    /**
     * BlockData: {@link RedstoneRail}
     */
    POWERED_RAIL(11064, RedstoneRail.class),
    /**
     * BlockData: {@link RedstoneRail}
     */
    DETECTOR_RAIL(13475, RedstoneRail.class),
    /**
     * BlockData: {@link Rail}
     */
    RAIL(13285, Rail.class),
    /**
     * BlockData: {@link RedstoneRail}
     */
    ACTIVATOR_RAIL(5834, RedstoneRail.class),
    SADDLE(30206, 1),
    MINECART(14352, 1),
    CHEST_MINECART(4497, 1),
    FURNACE_MINECART(14196, 1),
    TNT_MINECART(4277, 1),
    HOPPER_MINECART(19024, 1),
    CARROT_ON_A_STICK(27809, 1, 25),
    WARPED_FUNGUS_ON_A_STICK(11706, 1, 100),
    ELYTRA(23829, 1, 432),
    OAK_BOAT(17570, 1),
    OAK_CHEST_BOAT(7765, 1),
    SPRUCE_BOAT(31427, 1),
    SPRUCE_CHEST_BOAT(30841, 1),
    BIRCH_BOAT(28104, 1),
    BIRCH_CHEST_BOAT(18546, 1),
    JUNGLE_BOAT(4495, 1),
    JUNGLE_CHEST_BOAT(20133, 1),
    ACACIA_BOAT(27326, 1),
    ACACIA_CHEST_BOAT(28455, 1),
    CHERRY_BOAT(13628, 1),
    CHERRY_CHEST_BOAT(7165, 1),
    DARK_OAK_BOAT(28618, 1),
    DARK_OAK_CHEST_BOAT(8733, 1),
    MANGROVE_BOAT(20792, 1),
    MANGROVE_CHEST_BOAT(18572, 1),
    BAMBOO_RAFT(25901, 1),
    BAMBOO_CHEST_RAFT(20056, 1),
    /**
     * BlockData: {@link StructureBlock}
     */
    STRUCTURE_BLOCK(26831, StructureBlock.class),
    /**
     * BlockData: {@link Jigsaw}
     */
    JIGSAW(17398, Jigsaw.class),
    TURTLE_HELMET(30120, 1, 275),
    SCUTE(11914),
    FLINT_AND_STEEL(28620, 1, 64),
    APPLE(7720),
    BOW(8745, 1, 384),
    ARROW(31091),
    COAL(29067),
    CHARCOAL(5390),
    DIAMOND(20865),
    EMERALD(5654),
    LAPIS_LAZULI(11075),
    QUARTZ(23608),
    AMETHYST_SHARD(7613),
    RAW_IRON(5329),
    IRON_INGOT(24895),
    RAW_COPPER(6162),
    COPPER_INGOT(12611),
    RAW_GOLD(19564),
    GOLD_INGOT(28927),
    NETHERITE_INGOT(32457),
    NETHERITE_SCRAP(29331),
    WOODEN_SWORD(7175, 1, 59),
    WOODEN_SHOVEL(28432, 1, 59),
    WOODEN_PICKAXE(12792, 1, 59),
    WOODEN_AXE(6292, 1, 59),
    WOODEN_HOE(16043, 1, 59),
    STONE_SWORD(25084, 1, 131),
    STONE_SHOVEL(9520, 1, 131),
    STONE_PICKAXE(14611, 1, 131),
    STONE_AXE(6338, 1, 131),
    STONE_HOE(22855, 1, 131),
    GOLDEN_SWORD(10505, 1, 32),
    GOLDEN_SHOVEL(15597, 1, 32),
    GOLDEN_PICKAXE(25898, 1, 32),
    GOLDEN_AXE(4878, 1, 32),
    GOLDEN_HOE(19337, 1, 32),
    IRON_SWORD(10904, 1, 250),
    IRON_SHOVEL(30045, 1, 250),
    IRON_PICKAXE(8842, 1, 250),
    IRON_AXE(15894, 1, 250),
    IRON_HOE(11339, 1, 250),
    DIAMOND_SWORD(27707, 1, 1561),
    DIAMOND_SHOVEL(25415, 1, 1561),
    DIAMOND_PICKAXE(24291, 1, 1561),
    DIAMOND_AXE(27277, 1, 1561),
    DIAMOND_HOE(24050, 1, 1561),
    NETHERITE_SWORD(23871, 1, 2031),
    NETHERITE_SHOVEL(29728, 1, 2031),
    NETHERITE_PICKAXE(9930, 1, 2031),
    NETHERITE_AXE(29533, 1, 2031),
    NETHERITE_HOE(27385, 1, 2031),
    STICK(9773),
    BOWL(32661),
    MUSHROOM_STEW(16336, 1),
    STRING(12806),
    FEATHER(30548),
    GUNPOWDER(29974),
    WHEAT_SEEDS(28742),
    /**
     * BlockData: {@link Ageable}
     */
    WHEAT(27709, Ageable.class),
    BREAD(32049),
    LEATHER_HELMET(11624, 1, 55),
    LEATHER_CHESTPLATE(29275, 1, 80),
    LEATHER_LEGGINGS(28210, 1, 75),
    LEATHER_BOOTS(15282, 1, 65),
    CHAINMAIL_HELMET(26114, 1, 165),
    CHAINMAIL_CHESTPLATE(23602, 1, 240),
    CHAINMAIL_LEGGINGS(19087, 1, 225),
    CHAINMAIL_BOOTS(17953, 1, 195),
    IRON_HELMET(12025, 1, 165),
    IRON_CHESTPLATE(28112, 1, 240),
    IRON_LEGGINGS(18951, 1, 225),
    IRON_BOOTS(8531, 1, 195),
    DIAMOND_HELMET(10755, 1, 363),
    DIAMOND_CHESTPLATE(32099, 1, 528),
    DIAMOND_LEGGINGS(26500, 1, 495),
    DIAMOND_BOOTS(16522, 1, 429),
    GOLDEN_HELMET(7945, 1, 77),
    GOLDEN_CHESTPLATE(4507, 1, 112),
    GOLDEN_LEGGINGS(21002, 1, 105),
    GOLDEN_BOOTS(7859, 1, 91),
    NETHERITE_HELMET(15907, 1, 407),
    NETHERITE_CHESTPLATE(6106, 1, 592),
    NETHERITE_LEGGINGS(25605, 1, 555),
    NETHERITE_BOOTS(8923, 1, 481),
    FLINT(23596),
    PORKCHOP(30896),
    COOKED_PORKCHOP(27231),
    PAINTING(23945),
    GOLDEN_APPLE(27732),
    ENCHANTED_GOLDEN_APPLE(8280),
    /**
     * BlockData: {@link Sign}
     */
    OAK_SIGN(8192, 16, Sign.class),
    /**
     * BlockData: {@link Sign}
     */
    SPRUCE_SIGN(21502, 16, Sign.class),
    /**
     * BlockData: {@link Sign}
     */
    BIRCH_SIGN(11351, 16, Sign.class),
    /**
     * BlockData: {@link Sign}
     */
    JUNGLE_SIGN(24717, 16, Sign.class),
    /**
     * BlockData: {@link Sign}
     */
    ACACIA_SIGN(29808, 16, Sign.class),
    /**
     * BlockData: {@link Sign}
     */
    CHERRY_SIGN(16520, 16, Sign.class),
    /**
     * BlockData: {@link Sign}
     */
    DARK_OAK_SIGN(15127, 16, Sign.class),
    /**
     * BlockData: {@link Sign}
     */
    MANGROVE_SIGN(21975, 16, Sign.class),
    /**
     * BlockData: {@link Sign}
     */
    BAMBOO_SIGN(26139, 16, Sign.class),
    /**
     * BlockData: {@link Sign}
     */
    CRIMSON_SIGN(12162, 16, Sign.class),
    /**
     * BlockData: {@link Sign}
     */
    WARPED_SIGN(10407, 16, Sign.class),
    /**
     * BlockData: {@link HangingSign}
     */
    OAK_HANGING_SIGN(20116, 16, HangingSign.class),
    /**
     * BlockData: {@link HangingSign}
     */
    SPRUCE_HANGING_SIGN(24371, 16, HangingSign.class),
    /**
     * BlockData: {@link HangingSign}
     */
    BIRCH_HANGING_SIGN(17938, 16, HangingSign.class),
    /**
     * BlockData: {@link HangingSign}
     */
    JUNGLE_HANGING_SIGN(27671, 16, HangingSign.class),
    /**
     * BlockData: {@link HangingSign}
     */
    ACACIA_HANGING_SIGN(30257, 16, HangingSign.class),
    /**
     * BlockData: {@link HangingSign}
     */
    CHERRY_HANGING_SIGN(5088, 16, HangingSign.class),
    /**
     * BlockData: {@link HangingSign}
     */
    DARK_OAK_HANGING_SIGN(23360, 16, HangingSign.class),
    /**
     * BlockData: {@link HangingSign}
     */
    MANGROVE_HANGING_SIGN(25106, 16, HangingSign.class),
    /**
     * BlockData: {@link HangingSign}
     */
    BAMBOO_HANGING_SIGN(4726, 16, HangingSign.class),
    /**
     * BlockData: {@link HangingSign}
     */
    CRIMSON_HANGING_SIGN(20696, 16, HangingSign.class),
    /**
     * BlockData: {@link HangingSign}
     */
    WARPED_HANGING_SIGN(8195, 16, HangingSign.class),
    BUCKET(15215, 16),
    WATER_BUCKET(8802, 1),
    LAVA_BUCKET(9228, 1),
    POWDER_SNOW_BUCKET(31101, 1),
    SNOWBALL(19487, 16),
    LEATHER(16414),
    MILK_BUCKET(9680, 1),
    PUFFERFISH_BUCKET(8861, 1),
    SALMON_BUCKET(9606, 1),
    COD_BUCKET(28601, 1),
    TROPICAL_FISH_BUCKET(29995, 1),
    AXOLOTL_BUCKET(20669, 1),
    TADPOLE_BUCKET(9731, 1),
    BRICK(6820),
    CLAY_BALL(24603),
    DRIED_KELP_BLOCK(12966),
    PAPER(9923),
    BOOK(23097),
    SLIME_BALL(5242),
    EGG(21603, 16),
    COMPASS(24139),
    RECOVERY_COMPASS(12710),
    BUNDLE(16835, 1),
    FISHING_ROD(4167, 1, 64),
    CLOCK(14980),
    SPYGLASS(27490, 1),
    GLOWSTONE_DUST(6665),
    COD(24691),
    SALMON(18516),
    TROPICAL_FISH(24879),
    PUFFERFISH(8115),
    COOKED_COD(9681),
    COOKED_SALMON(5615),
    INK_SAC(7184),
    GLOW_INK_SAC(9686),
    COCOA_BEANS(30186),
    WHITE_DYE(10758),
    ORANGE_DYE(13866),
    MAGENTA_DYE(11788),
    LIGHT_BLUE_DYE(28738),
    YELLOW_DYE(5952),
    LIME_DYE(6147),
    PINK_DYE(31151),
    GRAY_DYE(9184),
    LIGHT_GRAY_DYE(27643),
    CYAN_DYE(8043),
    PURPLE_DYE(6347),
    BLUE_DYE(11588),
    BROWN_DYE(7648),
    GREEN_DYE(23215),
    RED_DYE(5728),
    BLACK_DYE(6202),
    BONE_MEAL(32458),
    BONE(5686),
    SUGAR(30638),
    /**
     * BlockData: {@link Cake}
     */
    CAKE(27048, 1, Cake.class),
    /**
     * BlockData: {@link Bed}
     */
    WHITE_BED(8185, 1, Bed.class),
    /**
     * BlockData: {@link Bed}
     */
    ORANGE_BED(11194, 1, Bed.class),
    /**
     * BlockData: {@link Bed}
     */
    MAGENTA_BED(20061, 1, Bed.class),
    /**
     * BlockData: {@link Bed}
     */
    LIGHT_BLUE_BED(20957, 1, Bed.class),
    /**
     * BlockData: {@link Bed}
     */
    YELLOW_BED(30410, 1, Bed.class),
    /**
     * BlockData: {@link Bed}
     */
    LIME_BED(27860, 1, Bed.class),
    /**
     * BlockData: {@link Bed}
     */
    PINK_BED(13795, 1, Bed.class),
    /**
     * BlockData: {@link Bed}
     */
    GRAY_BED(15745, 1, Bed.class),
    /**
     * BlockData: {@link Bed}
     */
    LIGHT_GRAY_BED(5090, 1, Bed.class),
    /**
     * BlockData: {@link Bed}
     */
    CYAN_BED(16746, 1, Bed.class),
    /**
     * BlockData: {@link Bed}
     */
    PURPLE_BED(29755, 1, Bed.class),
    /**
     * BlockData: {@link Bed}
     */
    BLUE_BED(12714, 1, Bed.class),
    /**
     * BlockData: {@link Bed}
     */
    BROWN_BED(26672, 1, Bed.class),
    /**
     * BlockData: {@link Bed}
     */
    GREEN_BED(13797, 1, Bed.class),
    /**
     * BlockData: {@link Bed}
     */
    RED_BED(30910, 1, Bed.class),
    /**
     * BlockData: {@link Bed}
     */
    BLACK_BED(20490, 1, Bed.class),
    COOKIE(27431),
    /**
     * BlockData: {@link Crafter}
     */
    @MinecraftExperimental
    @org.jetbrains.annotations.ApiStatus.Experimental // Paper - add missing annotation
    CRAFTER(25243, Crafter.class),
    FILLED_MAP(23504),
    SHEARS(27971, 1, 238),
    MELON_SLICE(5347),
    DRIED_KELP(21042),
    PUMPKIN_SEEDS(28985),
    MELON_SEEDS(18340),
    BEEF(4803),
    COOKED_BEEF(21595),
    CHICKEN(17281),
    COOKED_CHICKEN(16984),
    ROTTEN_FLESH(21591),
    ENDER_PEARL(5259, 16),
    BLAZE_ROD(8289),
    GHAST_TEAR(18222),
    GOLD_NUGGET(28814),
    /**
     * BlockData: {@link Ageable}
     */
    NETHER_WART(29227, Ageable.class),
    POTION(24020, 1),
    GLASS_BOTTLE(6116),
    SPIDER_EYE(9318),
    FERMENTED_SPIDER_EYE(19386),
    BLAZE_POWDER(18941),
    MAGMA_CREAM(25097),
    /**
     * BlockData: {@link BrewingStand}
     */
    BREWING_STAND(14539, BrewingStand.class),
    CAULDRON(26531),
    ENDER_EYE(24860),
    GLISTERING_MELON_SLICE(20158),
    ALLAY_SPAWN_EGG(7909),
    AXOLOTL_SPAWN_EGG(30381),
    BAT_SPAWN_EGG(14607),
    BEE_SPAWN_EGG(22924),
    BLAZE_SPAWN_EGG(4759),
    @MinecraftExperimental
    @org.jetbrains.annotations.ApiStatus.Experimental // Paper - add missing annotation
    BREEZE_SPAWN_EGG(7580),
    CAT_SPAWN_EGG(29583),
    CAMEL_SPAWN_EGG(14760),
    CAVE_SPIDER_SPAWN_EGG(23341),
    CHICKEN_SPAWN_EGG(5462),
    COD_SPAWN_EGG(27248),
    COW_SPAWN_EGG(14761),
    CREEPER_SPAWN_EGG(9653),
    DOLPHIN_SPAWN_EGG(20787),
    DONKEY_SPAWN_EGG(14513),
    DROWNED_SPAWN_EGG(19368),
    ELDER_GUARDIAN_SPAWN_EGG(11418),
    ENDER_DRAGON_SPAWN_EGG(28092),
    ENDERMAN_SPAWN_EGG(29488),
    ENDERMITE_SPAWN_EGG(16617),
    EVOKER_SPAWN_EGG(21271),
    FOX_SPAWN_EGG(22376),
    FROG_SPAWN_EGG(26682),
    GHAST_SPAWN_EGG(9970),
    GLOW_SQUID_SPAWN_EGG(31578),
    GOAT_SPAWN_EGG(30639),
    GUARDIAN_SPAWN_EGG(20113),
    HOGLIN_SPAWN_EGG(14088),
    HORSE_SPAWN_EGG(25981),
    HUSK_SPAWN_EGG(20178),
    IRON_GOLEM_SPAWN_EGG(12781),
    LLAMA_SPAWN_EGG(23640),
    MAGMA_CUBE_SPAWN_EGG(26638),
    MOOSHROOM_SPAWN_EGG(22125),
    MULE_SPAWN_EGG(11229),
    OCELOT_SPAWN_EGG(30080),
    PANDA_SPAWN_EGG(23759),
    PARROT_SPAWN_EGG(23614),
    PHANTOM_SPAWN_EGG(24648),
    PIG_SPAWN_EGG(22584),
    PIGLIN_SPAWN_EGG(16193),
    PIGLIN_BRUTE_SPAWN_EGG(30230),
    PILLAGER_SPAWN_EGG(28659),
    POLAR_BEAR_SPAWN_EGG(17015),
    PUFFERFISH_SPAWN_EGG(24570),
    RABBIT_SPAWN_EGG(26496),
    RAVAGER_SPAWN_EGG(8726),
    SALMON_SPAWN_EGG(18739),
    SHEEP_SPAWN_EGG(24488),
    SHULKER_SPAWN_EGG(31848),
    SILVERFISH_SPAWN_EGG(14537),
    SKELETON_SPAWN_EGG(15261),
    SKELETON_HORSE_SPAWN_EGG(21356),
    SLIME_SPAWN_EGG(17196),
    SNIFFER_SPAWN_EGG(27473),
    SNOW_GOLEM_SPAWN_EGG(24732),
    SPIDER_SPAWN_EGG(14984),
    SQUID_SPAWN_EGG(10682),
    STRAY_SPAWN_EGG(30153),
    STRIDER_SPAWN_EGG(6203),
    TADPOLE_SPAWN_EGG(32467),
    TRADER_LLAMA_SPAWN_EGG(8439),
    TROPICAL_FISH_SPAWN_EGG(19713),
    TURTLE_SPAWN_EGG(17324),
    VEX_SPAWN_EGG(27751),
    VILLAGER_SPAWN_EGG(30348),
    VINDICATOR_SPAWN_EGG(25324),
    WANDERING_TRADER_SPAWN_EGG(17904),
    WARDEN_SPAWN_EGG(27553),
    WITCH_SPAWN_EGG(11837),
    WITHER_SPAWN_EGG(8024),
    WITHER_SKELETON_SPAWN_EGG(10073),
    WOLF_SPAWN_EGG(21692),
    ZOGLIN_SPAWN_EGG(7442),
    ZOMBIE_SPAWN_EGG(5814),
    ZOMBIE_HORSE_SPAWN_EGG(4275),
    ZOMBIE_VILLAGER_SPAWN_EGG(10311),
    ZOMBIFIED_PIGLIN_SPAWN_EGG(6626),
    EXPERIENCE_BOTTLE(12858),
    FIRE_CHARGE(4842),
    WRITABLE_BOOK(13393, 1),
    WRITTEN_BOOK(24164, 16),
    ITEM_FRAME(27318),
    GLOW_ITEM_FRAME(26473),
    FLOWER_POT(30567),
    CARROT(22824),
    POTATO(21088),
    BAKED_POTATO(14624),
    POISONOUS_POTATO(32640),
    MAP(21655),
    GOLDEN_CARROT(5300),
    /**
     * BlockData: {@link Rotatable}
     */
    SKELETON_SKULL(13270, Rotatable.class),
    /**
     * BlockData: {@link Rotatable}
     */
    WITHER_SKELETON_SKULL(31487, Rotatable.class),
    /**
     * BlockData: {@link Rotatable}
     */
    PLAYER_HEAD(21174, Rotatable.class),
    /**
     * BlockData: {@link Rotatable}
     */
    ZOMBIE_HEAD(9304, Rotatable.class),
    /**
     * BlockData: {@link Rotatable}
     */
    CREEPER_HEAD(29146, Rotatable.class),
    /**
     * BlockData: {@link Rotatable}
     */
    DRAGON_HEAD(20084, Rotatable.class),
    /**
     * BlockData: {@link Rotatable}
     */
    PIGLIN_HEAD(5512, Rotatable.class),
    NETHER_STAR(12469),
    PUMPKIN_PIE(28725),
    FIREWORK_ROCKET(23841),
    FIREWORK_STAR(12190),
    ENCHANTED_BOOK(11741, 1),
    NETHER_BRICK(19996),
    PRISMARINE_SHARD(10993),
    PRISMARINE_CRYSTALS(31546),
    RABBIT(23068),
    COOKED_RABBIT(4454),
    RABBIT_STEW(25318, 1),
    RABBIT_FOOT(13864),
    RABBIT_HIDE(12467),
    ARMOR_STAND(12852, 16),
    IRON_HORSE_ARMOR(30108, 1),
    GOLDEN_HORSE_ARMOR(7996, 1),
    DIAMOND_HORSE_ARMOR(10321, 1),
    LEATHER_HORSE_ARMOR(30667, 1),
    LEAD(29539),
    NAME_TAG(30731),
    COMMAND_BLOCK_MINECART(7992, 1),
    MUTTON(4792),
    COOKED_MUTTON(31447),
    /**
     * BlockData: {@link Rotatable}
     */
    WHITE_BANNER(17562, 16, Rotatable.class),
    /**
     * BlockData: {@link Rotatable}
     */
    ORANGE_BANNER(4839, 16, Rotatable.class),
    /**
     * BlockData: {@link Rotatable}
     */
    MAGENTA_BANNER(15591, 16, Rotatable.class),
    /**
     * BlockData: {@link Rotatable}
     */
    LIGHT_BLUE_BANNER(18060, 16, Rotatable.class),
    /**
     * BlockData: {@link Rotatable}
     */
    YELLOW_BANNER(30382, 16, Rotatable.class),
    /**
     * BlockData: {@link Rotatable}
     */
    LIME_BANNER(18887, 16, Rotatable.class),
    /**
     * BlockData: {@link Rotatable}
     */
    PINK_BANNER(19439, 16, Rotatable.class),
    /**
     * BlockData: {@link Rotatable}
     */
    GRAY_BANNER(12053, 16, Rotatable.class),
    /**
     * BlockData: {@link Rotatable}
     */
    LIGHT_GRAY_BANNER(11417, 16, Rotatable.class),
    /**
     * BlockData: {@link Rotatable}
     */
    CYAN_BANNER(9839, 16, Rotatable.class),
    /**
     * BlockData: {@link Rotatable}
     */
    PURPLE_BANNER(29027, 16, Rotatable.class),
    /**
     * BlockData: {@link Rotatable}
     */
    BLUE_BANNER(18481, 16, Rotatable.class),
    /**
     * BlockData: {@link Rotatable}
     */
    BROWN_BANNER(11481, 16, Rotatable.class),
    /**
     * BlockData: {@link Rotatable}
     */
    GREEN_BANNER(10698, 16, Rotatable.class),
    /**
     * BlockData: {@link Rotatable}
     */
    RED_BANNER(26961, 16, Rotatable.class),
    /**
     * BlockData: {@link Rotatable}
     */
    BLACK_BANNER(9365, 16, Rotatable.class),
    END_CRYSTAL(19090),
    CHORUS_FRUIT(7652),
    POPPED_CHORUS_FRUIT(27844),
    TORCHFLOWER_SEEDS(18153),
    PITCHER_POD(7977),
    BEETROOT(23305),
    BEETROOT_SEEDS(21282),
    BEETROOT_SOUP(16036, 1),
    DRAGON_BREATH(20154),
    SPLASH_POTION(30248, 1),
    SPECTRAL_ARROW(4568),
    TIPPED_ARROW(25164),
    LINGERING_POTION(25857, 1),
    SHIELD(29943, 1, 336),
    TOTEM_OF_UNDYING(10139, 1),
    SHULKER_SHELL(27848),
    IRON_NUGGET(13715),
    KNOWLEDGE_BOOK(12646, 1),
    DEBUG_STICK(24562, 1),
    MUSIC_DISC_13(16359, 1),
    MUSIC_DISC_CAT(16246, 1),
    MUSIC_DISC_BLOCKS(26667, 1),
    MUSIC_DISC_CHIRP(19436, 1),
    MUSIC_DISC_FAR(31742, 1),
    MUSIC_DISC_MALL(11517, 1),
    MUSIC_DISC_MELLOHI(26117, 1),
    MUSIC_DISC_STAL(14989, 1),
    MUSIC_DISC_STRAD(16785, 1),
    MUSIC_DISC_WARD(24026, 1),
    MUSIC_DISC_11(27426, 1),
    MUSIC_DISC_WAIT(26499, 1),
    MUSIC_DISC_OTHERSIDE(12974, 1),
    MUSIC_DISC_RELIC(8200, 1),
    MUSIC_DISC_5(9212, 1),
    MUSIC_DISC_PIGSTEP(21323, 1),
    DISC_FRAGMENT_5(29729),
    TRIDENT(7534, 1, 250),
    PHANTOM_MEMBRANE(18398),
    NAUTILUS_SHELL(19989),
    HEART_OF_THE_SEA(11807),
    CROSSBOW(4340, 1, 465),
    SUSPICIOUS_STEW(8173, 1),
    /**
     * BlockData: {@link Directional}
     */
    LOOM(14276, Directional.class),
    FLOWER_BANNER_PATTERN(5762, 1),
    CREEPER_BANNER_PATTERN(15774, 1),
    SKULL_BANNER_PATTERN(7680, 1),
    MOJANG_BANNER_PATTERN(11903, 1),
    GLOBE_BANNER_PATTERN(27753, 1),
    PIGLIN_BANNER_PATTERN(22028, 1),
    GOAT_HORN(28237, 1),
    /**
     * BlockData: {@link Levelled}
     */
    COMPOSTER(31247, Levelled.class),
    /**
     * BlockData: {@link Barrel}
     */
    BARREL(22396, Barrel.class),
    /**
     * BlockData: {@link Furnace}
     */
    SMOKER(24781, Furnace.class),
    /**
     * BlockData: {@link Furnace}
     */
    BLAST_FURNACE(31157, Furnace.class),
    CARTOGRAPHY_TABLE(28529),
    FLETCHING_TABLE(30838),
    /**
     * BlockData: {@link Grindstone}
     */
    GRINDSTONE(26260, Grindstone.class),
    SMITHING_TABLE(9082),
    /**
     * BlockData: {@link Directional}
     */
    STONECUTTER(25170, Directional.class),
    /**
     * BlockData: {@link Bell}
     */
    BELL(20000, Bell.class),
    /**
     * BlockData: {@link Lantern}
     */
    LANTERN(5992, Lantern.class),
    /**
     * BlockData: {@link Lantern}
     */
    SOUL_LANTERN(27778, Lantern.class),
    SWEET_BERRIES(19747),
    GLOW_BERRIES(11584),
    /**
     * BlockData: {@link Campfire}
     */
    CAMPFIRE(8488, Campfire.class),
    /**
     * BlockData: {@link Campfire}
     */
    SOUL_CAMPFIRE(4238, Campfire.class),
    SHROOMLIGHT(20424),
    HONEYCOMB(9482),
    /**
     * BlockData: {@link Beehive}
     */
    BEE_NEST(8825, Beehive.class),
    /**
     * BlockData: {@link Beehive}
     */
    BEEHIVE(11830, Beehive.class),
    HONEY_BOTTLE(22927, 16),
    HONEYCOMB_BLOCK(28780),
    LODESTONE(23127),
    CRYING_OBSIDIAN(31545),
    BLACKSTONE(7354),
    /**
     * BlockData: {@link Slab}
     */
    BLACKSTONE_SLAB(11948, Slab.class),
    /**
     * BlockData: {@link Stairs}
     */
    BLACKSTONE_STAIRS(14646, Stairs.class),
    GILDED_BLACKSTONE(8498),
    POLISHED_BLACKSTONE(18144),
    /**
     * BlockData: {@link Slab}
     */
    POLISHED_BLACKSTONE_SLAB(23430, Slab.class),
    /**
     * BlockData: {@link Stairs}
     */
    POLISHED_BLACKSTONE_STAIRS(8653, Stairs.class),
    CHISELED_POLISHED_BLACKSTONE(21942),
    POLISHED_BLACKSTONE_BRICKS(19844),
    /**
     * BlockData: {@link Slab}
     */
    POLISHED_BLACKSTONE_BRICK_SLAB(12219, Slab.class),
    /**
     * BlockData: {@link Stairs}
     */
    POLISHED_BLACKSTONE_BRICK_STAIRS(17983, Stairs.class),
    CRACKED_POLISHED_BLACKSTONE_BRICKS(16846),
    /**
     * BlockData: {@link RespawnAnchor}
     */
    RESPAWN_ANCHOR(4099, RespawnAnchor.class),
    /**
     * BlockData: {@link Candle}
     */
    CANDLE(16122, Candle.class),
    /**
     * BlockData: {@link Candle}
     */
    WHITE_CANDLE(26410, Candle.class),
    /**
     * BlockData: {@link Candle}
     */
    ORANGE_CANDLE(22668, Candle.class),
    /**
     * BlockData: {@link Candle}
     */
    MAGENTA_CANDLE(25467, Candle.class),
    /**
     * BlockData: {@link Candle}
     */
    LIGHT_BLUE_CANDLE(28681, Candle.class),
    /**
     * BlockData: {@link Candle}
     */
    YELLOW_CANDLE(14351, Candle.class),
    /**
     * BlockData: {@link Candle}
     */
    LIME_CANDLE(21778, Candle.class),
    /**
     * BlockData: {@link Candle}
     */
    PINK_CANDLE(28259, Candle.class),
    /**
     * BlockData: {@link Candle}
     */
    GRAY_CANDLE(10721, Candle.class),
    /**
     * BlockData: {@link Candle}
     */
    LIGHT_GRAY_CANDLE(10031, Candle.class),
    /**
     * BlockData: {@link Candle}
     */
    CYAN_CANDLE(24765, Candle.class),
    /**
     * BlockData: {@link Candle}
     */
    PURPLE_CANDLE(19606, Candle.class),
    /**
     * BlockData: {@link Candle}
     */
    BLUE_CANDLE(29047, Candle.class),
    /**
     * BlockData: {@link Candle}
     */
    BROWN_CANDLE(26145, Candle.class),
    /**
     * BlockData: {@link Candle}
     */
    GREEN_CANDLE(29756, Candle.class),
    /**
     * BlockData: {@link Candle}
     */
    RED_CANDLE(4214, Candle.class),
    /**
     * BlockData: {@link Candle}
     */
    BLACK_CANDLE(12617, Candle.class),
    /**
     * BlockData: {@link AmethystCluster}
     */
    SMALL_AMETHYST_BUD(14958, AmethystCluster.class),
    /**
     * BlockData: {@link AmethystCluster}
     */
    MEDIUM_AMETHYST_BUD(8429, AmethystCluster.class),
    /**
     * BlockData: {@link AmethystCluster}
     */
    LARGE_AMETHYST_BUD(7279, AmethystCluster.class),
    /**
     * BlockData: {@link AmethystCluster}
     */
    AMETHYST_CLUSTER(13142, AmethystCluster.class),
    /**
     * BlockData: {@link PointedDripstone}
     */
    POINTED_DRIPSTONE(18755, PointedDripstone.class),
    /**
     * BlockData: {@link Orientable}
     */
    OCHRE_FROGLIGHT(25330, Orientable.class),
    /**
     * BlockData: {@link Orientable}
     */
    VERDANT_FROGLIGHT(22793, Orientable.class),
    /**
     * BlockData: {@link Orientable}
     */
    PEARLESCENT_FROGLIGHT(21441, Orientable.class),
    FROGSPAWN(8350),
    ECHO_SHARD(12529),
    BRUSH(30569, 1, 64),
    NETHERITE_UPGRADE_SMITHING_TEMPLATE(7615),
    SENTRY_ARMOR_TRIM_SMITHING_TEMPLATE(16124),
    DUNE_ARMOR_TRIM_SMITHING_TEMPLATE(30925),
    COAST_ARMOR_TRIM_SMITHING_TEMPLATE(25501),
    WILD_ARMOR_TRIM_SMITHING_TEMPLATE(5870),
    WARD_ARMOR_TRIM_SMITHING_TEMPLATE(24534),
    EYE_ARMOR_TRIM_SMITHING_TEMPLATE(14663),
    VEX_ARMOR_TRIM_SMITHING_TEMPLATE(25818),
    TIDE_ARMOR_TRIM_SMITHING_TEMPLATE(20420),
    SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE(14386),
    RIB_ARMOR_TRIM_SMITHING_TEMPLATE(6010),
    SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE(29143),
    WAYFINDER_ARMOR_TRIM_SMITHING_TEMPLATE(4957),
    SHAPER_ARMOR_TRIM_SMITHING_TEMPLATE(20537),
    SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE(7070),
    RAISER_ARMOR_TRIM_SMITHING_TEMPLATE(29116),
    HOST_ARMOR_TRIM_SMITHING_TEMPLATE(12165),
    ANGLER_POTTERY_SHERD(9952),
    ARCHER_POTTERY_SHERD(21629),
    ARMS_UP_POTTERY_SHERD(5484),
    BLADE_POTTERY_SHERD(25079),
    BREWER_POTTERY_SHERD(23429),
    BURN_POTTERY_SHERD(21259),
    DANGER_POTTERY_SHERD(30506),
    EXPLORER_POTTERY_SHERD(5124),
    FRIEND_POTTERY_SHERD(18221),
    HEART_POTTERY_SHERD(17607),
    HEARTBREAK_POTTERY_SHERD(21108),
    HOWL_POTTERY_SHERD(24900),
    MINER_POTTERY_SHERD(30602),
    MOURNER_POTTERY_SHERD(23993),
    PLENTY_POTTERY_SHERD(28236),
    PRIZE_POTTERY_SHERD(4341),
    SHEAF_POTTERY_SHERD(23652),
    SHELTER_POTTERY_SHERD(28390),
    SKULL_POTTERY_SHERD(16980),
    SNORT_POTTERY_SHERD(15921),
    /**
     * BlockData: {@link Waterlogged}
     */
    @MinecraftExperimental
    @org.jetbrains.annotations.ApiStatus.Experimental // Paper - add missing annotation
    COPPER_GRATE(16221, Waterlogged.class),
    /**
     * BlockData: {@link Waterlogged}
     */
    @MinecraftExperimental
    @org.jetbrains.annotations.ApiStatus.Experimental // Paper - add missing annotation
    EXPOSED_COPPER_GRATE(7783, Waterlogged.class),
    /**
     * BlockData: {@link Waterlogged}
     */
    @MinecraftExperimental
    @org.jetbrains.annotations.ApiStatus.Experimental // Paper - add missing annotation
    WEATHERED_COPPER_GRATE(24954, Waterlogged.class),
    /**
     * BlockData: {@link Waterlogged}
     */
    @MinecraftExperimental
    @org.jetbrains.annotations.ApiStatus.Experimental // Paper - add missing annotation
    OXIDIZED_COPPER_GRATE(14122, Waterlogged.class),
    /**
     * BlockData: {@link Waterlogged}
     */
    @MinecraftExperimental
    @org.jetbrains.annotations.ApiStatus.Experimental // Paper - add missing annotation
    WAXED_COPPER_GRATE(11230, Waterlogged.class),
    /**
     * BlockData: {@link Waterlogged}
     */
    @MinecraftExperimental
    @org.jetbrains.annotations.ApiStatus.Experimental // Paper - add missing annotation
    WAXED_EXPOSED_COPPER_GRATE(20520, Waterlogged.class),
    /**
     * BlockData: {@link Waterlogged}
     */
    @MinecraftExperimental
    @org.jetbrains.annotations.ApiStatus.Experimental // Paper - add missing annotation
    WAXED_WEATHERED_COPPER_GRATE(16533, Waterlogged.class),
    /**
     * BlockData: {@link Waterlogged}
     */
    @MinecraftExperimental
    @org.jetbrains.annotations.ApiStatus.Experimental // Paper - add missing annotation
    WAXED_OXIDIZED_COPPER_GRATE(32010, Waterlogged.class),
    /**
     * BlockData: {@link CopperBulb}
     */
    @MinecraftExperimental
    @org.jetbrains.annotations.ApiStatus.Experimental // Paper - add missing annotation
    COPPER_BULB(21370, CopperBulb.class),
    /**
     * BlockData: {@link CopperBulb}
     */
    @MinecraftExperimental
    @org.jetbrains.annotations.ApiStatus.Experimental // Paper - add missing annotation
    EXPOSED_COPPER_BULB(11944, CopperBulb.class),
    /**
     * BlockData: {@link CopperBulb}
     */
    @MinecraftExperimental
    @org.jetbrains.annotations.ApiStatus.Experimental // Paper - add missing annotation
    WEATHERED_COPPER_BULB(10800, CopperBulb.class),
    /**
     * BlockData: {@link CopperBulb}
     */
    @MinecraftExperimental
    @org.jetbrains.annotations.ApiStatus.Experimental // Paper - add missing annotation
    OXIDIZED_COPPER_BULB(22421, CopperBulb.class),
    /**
     * BlockData: {@link CopperBulb}
     */
    @MinecraftExperimental
    @org.jetbrains.annotations.ApiStatus.Experimental // Paper - add missing annotation
    WAXED_COPPER_BULB(23756, CopperBulb.class),
    /**
     * BlockData: {@link CopperBulb}
     */
    @MinecraftExperimental
    @org.jetbrains.annotations.ApiStatus.Experimental // Paper - add missing annotation
    WAXED_EXPOSED_COPPER_BULB(5530, CopperBulb.class),
    /**
     * BlockData: {@link CopperBulb}
     */
    @MinecraftExperimental
    @org.jetbrains.annotations.ApiStatus.Experimental // Paper - add missing annotation
    WAXED_WEATHERED_COPPER_BULB(13239, CopperBulb.class),
    /**
     * BlockData: {@link CopperBulb}
     */
    @MinecraftExperimental
    @org.jetbrains.annotations.ApiStatus.Experimental // Paper - add missing annotation
    WAXED_OXIDIZED_COPPER_BULB(26892, CopperBulb.class),
    /**
     * BlockData: {@link TrialSpawner}
     */
    @MinecraftExperimental
    @org.jetbrains.annotations.ApiStatus.Experimental // Paper - add missing annotation
    TRIAL_SPAWNER(19902, TrialSpawner.class),
    @MinecraftExperimental
    @org.jetbrains.annotations.ApiStatus.Experimental // Paper - add missing annotation
    TRIAL_KEY(12725),
    /**
     * BlockData: {@link Levelled}
     */
    WATER(24998, Levelled.class),
    /**
     * BlockData: {@link Levelled}
     */
    LAVA(8415, Levelled.class),
    /**
     * BlockData: {@link Bisected}
     */
    TALL_SEAGRASS(27189, Bisected.class),
    /**
     * BlockData: {@link PistonHead}
     */
    PISTON_HEAD(30226, PistonHead.class),
    /**
     * BlockData: {@link TechnicalPiston}
     */
    MOVING_PISTON(13831, TechnicalPiston.class),
    /**
     * BlockData: {@link Directional}
     */
    WALL_TORCH(25890, Directional.class),
    /**
     * BlockData: {@link Fire}
     */
    FIRE(16396, Fire.class),
    SOUL_FIRE(30163),
    /**
     * BlockData: {@link RedstoneWire}
     */
    REDSTONE_WIRE(25984, RedstoneWire.class),
    /**
     * BlockData: {@link WallSign}
     */
    OAK_WALL_SIGN(12984, 16, WallSign.class),
    /**
     * BlockData: {@link WallSign}
     */
    SPRUCE_WALL_SIGN(7352, 16, WallSign.class),
    /**
     * BlockData: {@link WallSign}
     */
    BIRCH_WALL_SIGN(9887, 16, WallSign.class),
    /**
     * BlockData: {@link WallSign}
     */
    ACACIA_WALL_SIGN(20316, 16, WallSign.class),
    /**
     * BlockData: {@link WallSign}
     */
    CHERRY_WALL_SIGN(20188, 16, WallSign.class),
    /**
     * BlockData: {@link WallSign}
     */
    JUNGLE_WALL_SIGN(29629, 16, WallSign.class),
    /**
     * BlockData: {@link WallSign}
     */
    DARK_OAK_WALL_SIGN(9508, 16, WallSign.class),
    /**
     * BlockData: {@link WallSign}
     */
    MANGROVE_WALL_SIGN(27203, 16, WallSign.class),
    /**
     * BlockData: {@link WallSign}
     */
    BAMBOO_WALL_SIGN(18857, 16, WallSign.class),
    /**
     * BlockData: {@link WallHangingSign}
     */
    OAK_WALL_HANGING_SIGN(15637, WallHangingSign.class),
    /**
     * BlockData: {@link WallHangingSign}
     */
    SPRUCE_WALL_HANGING_SIGN(18833, WallHangingSign.class),
    /**
     * BlockData: {@link WallHangingSign}
     */
    BIRCH_WALL_HANGING_SIGN(15937, WallHangingSign.class),
    /**
     * BlockData: {@link WallHangingSign}
     */
    ACACIA_WALL_HANGING_SIGN(22477, WallHangingSign.class),
    /**
     * BlockData: {@link WallHangingSign}
     */
    CHERRY_WALL_HANGING_SIGN(10953, WallHangingSign.class),
    /**
     * BlockData: {@link WallHangingSign}
     */
    JUNGLE_WALL_HANGING_SIGN(16691, WallHangingSign.class),
    /**
     * BlockData: {@link WallHangingSign}
     */
    DARK_OAK_WALL_HANGING_SIGN(14296, WallHangingSign.class),
    /**
     * BlockData: {@link WallHangingSign}
     */
    MANGROVE_WALL_HANGING_SIGN(16974, WallHangingSign.class),
    /**
     * BlockData: {@link WallHangingSign}
     */
    CRIMSON_WALL_HANGING_SIGN(28982, WallHangingSign.class),
    /**
     * BlockData: {@link WallHangingSign}
     */
    WARPED_WALL_HANGING_SIGN(20605, WallHangingSign.class),
    /**
     * BlockData: {@link WallHangingSign}
     */
    BAMBOO_WALL_HANGING_SIGN(6669, WallHangingSign.class),
    /**
     * BlockData: {@link RedstoneWallTorch}
     */
    REDSTONE_WALL_TORCH(7595, RedstoneWallTorch.class),
    /**
     * BlockData: {@link Directional}
     */
    SOUL_WALL_TORCH(27500, Directional.class),
    /**
     * BlockData: {@link Orientable}
     */
    NETHER_PORTAL(19469, Orientable.class),
    /**
     * BlockData: {@link Directional}
     */
    ATTACHED_PUMPKIN_STEM(12724, Directional.class),
    /**
     * BlockData: {@link Directional}
     */
    ATTACHED_MELON_STEM(30882, Directional.class),
    /**
     * BlockData: {@link Ageable}
     */
    PUMPKIN_STEM(19021, Ageable.class),
    /**
     * BlockData: {@link Ageable}
     */
    MELON_STEM(8247, Ageable.class),
    /**
     * BlockData: {@link Levelled}
     */
    WATER_CAULDRON(32008, Levelled.class),
    LAVA_CAULDRON(4514),
    /**
     * BlockData: {@link Levelled}
     */
    POWDER_SNOW_CAULDRON(31571, Levelled.class),
    END_PORTAL(16782),
    /**
     * BlockData: {@link Cocoa}
     */
    COCOA(29709, Cocoa.class),
    /**
     * BlockData: {@link Tripwire}
     */
    TRIPWIRE(8810, Tripwire.class),
    POTTED_TORCHFLOWER(21278),
    POTTED_OAK_SAPLING(11905),
    POTTED_SPRUCE_SAPLING(29498),
    POTTED_BIRCH_SAPLING(32484),
    POTTED_JUNGLE_SAPLING(7525),
    POTTED_ACACIA_SAPLING(14096),
    POTTED_CHERRY_SAPLING(30785),
    POTTED_DARK_OAK_SAPLING(6486),
    POTTED_MANGROVE_PROPAGULE(22003),
    POTTED_FERN(23315),
    POTTED_DANDELION(9727),
    POTTED_POPPY(7457),
    POTTED_BLUE_ORCHID(6599),
    POTTED_ALLIUM(13184),
    POTTED_AZURE_BLUET(8754),
    POTTED_RED_TULIP(28594),
    POTTED_ORANGE_TULIP(28807),
    POTTED_WHITE_TULIP(24330),
    POTTED_PINK_TULIP(10089),
    POTTED_OXEYE_DAISY(19707),
    POTTED_CORNFLOWER(28917),
    POTTED_LILY_OF_THE_VALLEY(9364),
    POTTED_WITHER_ROSE(26876),
    POTTED_RED_MUSHROOM(22881),
    POTTED_BROWN_MUSHROOM(14481),
    POTTED_DEAD_BUSH(13020),
    POTTED_CACTUS(8777),
    /**
     * BlockData: {@link Ageable}
     */
    CARROTS(17258, Ageable.class),
    /**
     * BlockData: {@link Ageable}
     */
    POTATOES(10879, Ageable.class),
    /**
     * BlockData: {@link Directional}
     */
    SKELETON_WALL_SKULL(31650, Directional.class),
    /**
     * BlockData: {@link Directional}
     */
    WITHER_SKELETON_WALL_SKULL(9326, Directional.class),
    /**
     * BlockData: {@link Directional}
     */
    ZOMBIE_WALL_HEAD(16296, Directional.class),
    /**
     * BlockData: {@link Directional}
     */
    PLAYER_WALL_HEAD(13164, Directional.class),
    /**
     * BlockData: {@link Directional}
     */
    CREEPER_WALL_HEAD(30123, Directional.class),
    /**
     * BlockData: {@link Directional}
     */
    DRAGON_WALL_HEAD(19818, Directional.class),
    /**
     * BlockData: {@link Directional}
     */
    PIGLIN_WALL_HEAD(4446, Directional.class),
    /**
     * BlockData: {@link Directional}
     */
    WHITE_WALL_BANNER(15967, Directional.class),
    /**
     * BlockData: {@link Directional}
     */
    ORANGE_WALL_BANNER(9936, Directional.class),
    /**
     * BlockData: {@link Directional}
     */
    MAGENTA_WALL_BANNER(23291, Directional.class),
    /**
     * BlockData: {@link Directional}
     */
    LIGHT_BLUE_WALL_BANNER(12011, Directional.class),
    /**
     * BlockData: {@link Directional}
     */
    YELLOW_WALL_BANNER(32004, Directional.class),
    /**
     * BlockData: {@link Directional}
     */
    LIME_WALL_BANNER(21422, Directional.class),
    /**
     * BlockData: {@link Directional}
     */
    PINK_WALL_BANNER(9421, Directional.class),
    /**
     * BlockData: {@link Directional}
     */
    GRAY_WALL_BANNER(24275, Directional.class),
    /**
     * BlockData: {@link Directional}
     */
    LIGHT_GRAY_WALL_BANNER(31088, Directional.class),
    /**
     * BlockData: {@link Directional}
     */
    CYAN_WALL_BANNER(10889, Directional.class),
    /**
     * BlockData: {@link Directional}
     */
    PURPLE_WALL_BANNER(14298, Directional.class),
    /**
     * BlockData: {@link Directional}
     */
    BLUE_WALL_BANNER(17757, Directional.class),
    /**
     * BlockData: {@link Directional}
     */
    BROWN_WALL_BANNER(14731, Directional.class),
    /**
     * BlockData: {@link Directional}
     */
    GREEN_WALL_BANNER(15046, Directional.class),
    /**
     * BlockData: {@link Directional}
     */
    RED_WALL_BANNER(4378, Directional.class),
    /**
     * BlockData: {@link Directional}
     */
    BLACK_WALL_BANNER(4919, Directional.class),
    /**
     * BlockData: {@link Ageable}
     */
    TORCHFLOWER_CROP(28460, Ageable.class),
    /**
     * BlockData: {@link PitcherCrop}
     */
    PITCHER_CROP(15420, PitcherCrop.class),
    /**
     * BlockData: {@link Ageable}
     */
    BEETROOTS(22075, Ageable.class),
    END_GATEWAY(26605),
    /**
     * BlockData: {@link Ageable}
     */
    FROSTED_ICE(21814, Ageable.class),
    KELP_PLANT(29697),
    /**
     * BlockData: {@link CoralWallFan}
     */
    DEAD_TUBE_CORAL_WALL_FAN(5128, CoralWallFan.class),
    /**
     * BlockData: {@link CoralWallFan}
     */
    DEAD_BRAIN_CORAL_WALL_FAN(23718, CoralWallFan.class),
    /**
     * BlockData: {@link CoralWallFan}
     */
    DEAD_BUBBLE_CORAL_WALL_FAN(18453, CoralWallFan.class),
    /**
     * BlockData: {@link CoralWallFan}
     */
    DEAD_FIRE_CORAL_WALL_FAN(23375, CoralWallFan.class),
    /**
     * BlockData: {@link CoralWallFan}
     */
    DEAD_HORN_CORAL_WALL_FAN(27550, CoralWallFan.class),
    /**
     * BlockData: {@link CoralWallFan}
     */
    TUBE_CORAL_WALL_FAN(25282, CoralWallFan.class),
    /**
     * BlockData: {@link CoralWallFan}
     */
    BRAIN_CORAL_WALL_FAN(22685, CoralWallFan.class),
    /**
     * BlockData: {@link CoralWallFan}
     */
    BUBBLE_CORAL_WALL_FAN(20382, CoralWallFan.class),
    /**
     * BlockData: {@link CoralWallFan}
     */
    FIRE_CORAL_WALL_FAN(20100, CoralWallFan.class),
    /**
     * BlockData: {@link CoralWallFan}
     */
    HORN_CORAL_WALL_FAN(28883, CoralWallFan.class),
    BAMBOO_SAPLING(8478),
    POTTED_BAMBOO(22542),
    VOID_AIR(13668),
    CAVE_AIR(17422),
    /**
     * BlockData: {@link BubbleColumn}
     */
    BUBBLE_COLUMN(31612, BubbleColumn.class),
    /**
     * BlockData: {@link Ageable}
     */
    SWEET_BERRY_BUSH(11958, Ageable.class),
    WEEPING_VINES_PLANT(19437),
    TWISTING_VINES_PLANT(25338),
    /**
     * BlockData: {@link WallSign}
     */
    CRIMSON_WALL_SIGN(19242, 16, WallSign.class),
    /**
     * BlockData: {@link WallSign}
     */
    WARPED_WALL_SIGN(13534, 16, WallSign.class),
    POTTED_CRIMSON_FUNGUS(5548),
    POTTED_WARPED_FUNGUS(30800),
    POTTED_CRIMSON_ROOTS(13852),
    POTTED_WARPED_ROOTS(6403),
    /**
     * BlockData: {@link Lightable}
     */
    CANDLE_CAKE(25423, Lightable.class),
    /**
     * BlockData: {@link Lightable}
     */
    WHITE_CANDLE_CAKE(12674, Lightable.class),
    /**
     * BlockData: {@link Lightable}
     */
    ORANGE_CANDLE_CAKE(24982, Lightable.class),
    /**
     * BlockData: {@link Lightable}
     */
    MAGENTA_CANDLE_CAKE(11022, Lightable.class),
    /**
     * BlockData: {@link Lightable}
     */
    LIGHT_BLUE_CANDLE_CAKE(7787, Lightable.class),
    /**
     * BlockData: {@link Lightable}
     */
    YELLOW_CANDLE_CAKE(17157, Lightable.class),
    /**
     * BlockData: {@link Lightable}
     */
    LIME_CANDLE_CAKE(14309, Lightable.class),
    /**
     * BlockData: {@link Lightable}
     */
    PINK_CANDLE_CAKE(20405, Lightable.class),
    /**
     * BlockData: {@link Lightable}
     */
    GRAY_CANDLE_CAKE(6777, Lightable.class),
    /**
     * BlockData: {@link Lightable}
     */
    LIGHT_GRAY_CANDLE_CAKE(11318, Lightable.class),
    /**
     * BlockData: {@link Lightable}
     */
    CYAN_CANDLE_CAKE(21202, Lightable.class),
    /**
     * BlockData: {@link Lightable}
     */
    PURPLE_CANDLE_CAKE(22663, Lightable.class),
    /**
     * BlockData: {@link Lightable}
     */
    BLUE_CANDLE_CAKE(26425, Lightable.class),
    /**
     * BlockData: {@link Lightable}
     */
    BROWN_CANDLE_CAKE(26024, Lightable.class),
    /**
     * BlockData: {@link Lightable}
     */
    GREEN_CANDLE_CAKE(16334, Lightable.class),
    /**
     * BlockData: {@link Lightable}
     */
    RED_CANDLE_CAKE(24151, Lightable.class),
    /**
     * BlockData: {@link Lightable}
     */
    BLACK_CANDLE_CAKE(15191, Lightable.class),
    POWDER_SNOW(24077),
    /**
     * BlockData: {@link CaveVines}
     */
    CAVE_VINES(7339, CaveVines.class),
    /**
     * BlockData: {@link CaveVinesPlant}
     */
    CAVE_VINES_PLANT(30645, CaveVinesPlant.class),
    /**
     * BlockData: {@link Dripleaf}
     */
    BIG_DRIPLEAF_STEM(13167, Dripleaf.class),
    POTTED_AZALEA_BUSH(20430),
    POTTED_FLOWERING_AZALEA_BUSH(10609),
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
            case DARK_OAK_BUTTON:
            case WALL_TORCH:
            case STRUCTURE_BLOCK:
            case POLISHED_BLACKSTONE_BRICK_SLAB:
            case CHERRY_SAPLING:
            case BASALT:
            case CHISELED_DEEPSLATE:
            case GRAY_CANDLE:
            case BROWN_MUSHROOM:
            case RED_WOOL:
            case CHISELED_TUFF_BRICKS:
            case STRIPPED_DARK_OAK_WOOD:
            case STONECUTTER:
            case SOUL_TORCH:
            case POTTED_WHITE_TULIP:
            case NETHER_GOLD_ORE:
            case BONE_BLOCK:
            case BLACK_CONCRETE:
            case MELON:
            case BAMBOO_WALL_SIGN:
            case BIRCH_FENCE:
            case CRACKED_DEEPSLATE_TILES:
            case RED_NETHER_BRICK_SLAB:
            case WAXED_WEATHERED_COPPER_DOOR:
            case DEEPSLATE_GOLD_ORE:
            case TALL_GRASS:
            case WARPED_FENCE_GATE:
            case CORNFLOWER:
            case RED_MUSHROOM:
            case RED_MUSHROOM_BLOCK:
            case NETHER_WART_BLOCK:
            case BAMBOO_FENCE_GATE:
            case DEAD_BRAIN_CORAL_WALL_FAN:
            case CHISELED_TUFF:
            case MAGENTA_BANNER:
            case GREEN_CONCRETE:
            case COBBLESTONE_WALL:
            case MAGENTA_WOOL:
            case LIME_STAINED_GLASS_PANE:
            case MANGROVE_FENCE_GATE:
            case WARPED_PRESSURE_PLATE:
            case WAXED_CUT_COPPER_SLAB:
            case COAL_BLOCK:
            case LARGE_FERN:
            case SOUL_FIRE:
            case BLACK_WOOL:
            case OAK_WALL_SIGN:
            case MANGROVE_BUTTON:
            case SPRUCE_FENCE_GATE:
            case SPRUCE_SAPLING:
            case YELLOW_BED:
            case IRON_BLOCK:
            case BEEHIVE:
            case SPRUCE_SIGN:
            case DEAD_TUBE_CORAL_FAN:
            case DARK_OAK_DOOR:
            case BLACKSTONE:
            case LAPIS_ORE:
            case GRAVEL:
            case BIG_DRIPLEAF:
            case TRIPWIRE:
            case BIRCH_WOOD:
            case POTTED_WARPED_FUNGUS:
            case BIRCH_SAPLING:
            case LIGHT_BLUE_BED:
            case OAK_WALL_HANGING_SIGN:
            case BAMBOO_MOSAIC:
            case OAK_WOOD:
            case SCAFFOLDING:
            case EMERALD_BLOCK:
            case CRIMSON_FUNGUS:
            case FLOWER_POT:
            case CHISELED_NETHER_BRICKS:
            case COCOA:
            case ACACIA_BUTTON:
            case POTTED_RED_MUSHROOM:
            case MAGENTA_STAINED_GLASS_PANE:
            case COPPER_BLOCK:
            case AMETHYST_CLUSTER:
            case NETHER_BRICK_STAIRS:
            case PURPLE_CANDLE:
            case POTTED_FLOWERING_AZALEA_BUSH:
            case BRAIN_CORAL_FAN:
            case LILAC:
            case PURPLE_WOOL:
            case MAGENTA_BED:
            case NETHER_SPROUTS:
            case SPRUCE_WOOD:
            case GREEN_STAINED_GLASS:
            case SPRUCE_FENCE:
            case CHERRY_FENCE:
            case POLISHED_TUFF_SLAB:
            case DIAMOND_ORE:
            case MOSSY_STONE_BRICK_SLAB:
            case ANDESITE_SLAB:
            case DARK_PRISMARINE_SLAB:
            case DRAGON_HEAD:
            case HONEY_BLOCK:
            case BAMBOO_PLANKS:
            case SUGAR_CANE:
            case FLOWERING_AZALEA_LEAVES:
            case PINK_GLAZED_TERRACOTTA:
            case PINK_TERRACOTTA:
            case BLACK_SHULKER_BOX:
            case OXIDIZED_COPPER_GRATE:
            case WAXED_EXPOSED_CUT_COPPER_STAIRS:
            case ORANGE_STAINED_GLASS:
            case EXPOSED_CUT_COPPER_SLAB:
            case MAGENTA_CONCRETE:
            case POLISHED_BLACKSTONE_WALL:
            case COBBLESTONE_STAIRS:
            case CUT_SANDSTONE_SLAB:
            case SMOOTH_RED_SANDSTONE_SLAB:
            case CANDLE_CAKE:
            case END_GATEWAY:
            case BAMBOO_PRESSURE_PLATE:
            case CHERRY_DOOR:
            case SMALL_DRIPLEAF:
            case BIRCH_TRAPDOOR:
            case WEATHERED_CUT_COPPER_SLAB:
            case LIME_WALL_BANNER:
            case TUFF_BRICKS:
            case ACACIA_DOOR:
            case MANGROVE_WOOD:
            case GREEN_WALL_BANNER:
            case REDSTONE_ORE:
            case POLISHED_BASALT:
            case PRISMARINE_STAIRS:
            case STRIPPED_BAMBOO_BLOCK:
            case BAMBOO_WALL_HANGING_SIGN:
            case CYAN_CONCRETE_POWDER:
            case CONDUIT:
            case SAND:
            case POTTED_SPRUCE_SAPLING:
            case POLISHED_GRANITE_SLAB:
            case ACACIA_LOG:
            case LIGHT_BLUE_CONCRETE:
            case BRAIN_CORAL_WALL_FAN:
            case WAXED_EXPOSED_COPPER_TRAPDOOR:
            case STICKY_PISTON:
            case GRAY_CONCRETE:
            case DEAD_TUBE_CORAL_WALL_FAN:
            case BLUE_GLAZED_TERRACOTTA:
            case JIGSAW:
            case STRIPPED_SPRUCE_LOG:
            case POLISHED_ANDESITE_SLAB:
            case SHORT_GRASS:
            case CHERRY_PLANKS:
            case POWERED_RAIL:
            case KELP_PLANT:
            case POTTED_FERN:
            case POTTED_CORNFLOWER:
            case WARPED_HANGING_SIGN:
            case GREEN_CONCRETE_POWDER:
            case HORN_CORAL_WALL_FAN:
            case OAK_STAIRS:
            case POTTED_RED_TULIP:
            case CRACKED_DEEPSLATE_BRICKS:
            case STONE_BUTTON:
            case POTTED_CHERRY_SAPLING:
            case HORN_CORAL_FAN:
            case POLISHED_BLACKSTONE_BRICK_STAIRS:
            case WAXED_OXIDIZED_CHISELED_COPPER:
            case BROWN_STAINED_GLASS:
            case PINK_SHULKER_BOX:
            case WARPED_ROOTS:
            case TINTED_GLASS:
            case BLUE_TERRACOTTA:
            case YELLOW_CANDLE:
            case STRIPPED_OAK_WOOD:
            case SANDSTONE:
            case YELLOW_CONCRETE_POWDER:
            case GRAY_TERRACOTTA:
            case JUNGLE_FENCE_GATE:
            case DEAD_BRAIN_CORAL_BLOCK:
            case COMPARATOR:
            case LODESTONE:
            case TALL_SEAGRASS:
            case REPEATER:
            case DEEPSLATE_COPPER_ORE:
            case RED_CONCRETE_POWDER:
            case DEAD_FIRE_CORAL:
            case GRAY_SHULKER_BOX:
            case PURPUR_BLOCK:
            case POTTED_MANGROVE_PROPAGULE:
            case GLASS:
            case POLISHED_BLACKSTONE_SLAB:
            case LIGHT_GRAY_SHULKER_BOX:
            case STRUCTURE_VOID:
            case STONE_STAIRS:
            case WAXED_OXIDIZED_COPPER_BULB:
            case TUBE_CORAL_WALL_FAN:
            case DRIED_KELP_BLOCK:
            case END_STONE_BRICK_STAIRS:
            case TERRACOTTA:
            case BUDDING_AMETHYST:
            case STRIPPED_CRIMSON_HYPHAE:
            case BIRCH_WALL_SIGN:
            case ACACIA_WALL_HANGING_SIGN:
            case CYAN_CARPET:
            case NETHER_BRICK_WALL:
            case WARPED_NYLIUM:
            case WAXED_COPPER_DOOR:
            case INFESTED_CHISELED_STONE_BRICKS:
            case GRAY_CONCRETE_POWDER:
            case TORCHFLOWER:
            case LIGHT_BLUE_CONCRETE_POWDER:
            case SPRUCE_WALL_SIGN:
            case SCULK_SENSOR:
            case CHAIN_COMMAND_BLOCK:
            case OAK_LOG:
            case GRANITE_STAIRS:
            case WEEPING_VINES:
            case JUNGLE_WALL_SIGN:
            case CYAN_TERRACOTTA:
            case AIR:
            case CUT_RED_SANDSTONE:
            case TWISTING_VINES_PLANT:
            case MANGROVE_FENCE:
            case NETHER_PORTAL:
            case WARPED_PLANKS:
            case DEAD_HORN_CORAL_BLOCK:
            case LIME_CANDLE:
            case DEAD_HORN_CORAL_WALL_FAN:
            case MUSHROOM_STEM:
            case BEACON:
            case TNT:
            case TUFF_STAIRS:
            case LIGHT_GRAY_BED:
            case RED_CANDLE:
            case DARK_OAK_WALL_SIGN:
            case DEEPSLATE_LAPIS_ORE:
            case BROWN_CARPET:
            case COBWEB:
            case JUNGLE_DOOR:
            case EXPOSED_COPPER_TRAPDOOR:
            case BLACK_STAINED_GLASS_PANE:
            case PRISMARINE_BRICK_SLAB:
            case POTTED_AZALEA_BUSH:
            case PRISMARINE_BRICKS:
            case CYAN_SHULKER_BOX:
            case NETHER_BRICKS:
            case BLUE_CARPET:
            case POTTED_LILY_OF_THE_VALLEY:
            case BAMBOO_DOOR:
            case CRIMSON_FENCE:
            case STONE_BRICKS:
            case OXIDIZED_CUT_COPPER_SLAB:
            case BIRCH_STAIRS:
            case INFESTED_STONE_BRICKS:
            case FROGSPAWN:
            case PINK_WALL_BANNER:
            case LIGHT_GRAY_STAINED_GLASS:
            case SPRUCE_LEAVES:
            case WHITE_STAINED_GLASS_PANE:
            case INFESTED_CRACKED_STONE_BRICKS:
            case CRIMSON_STAIRS:
            case LIGHT_BLUE_STAINED_GLASS_PANE:
            case END_PORTAL:
            case BROWN_CONCRETE_POWDER:
            case CYAN_STAINED_GLASS:
            case CYAN_GLAZED_TERRACOTTA:
            case OAK_PRESSURE_PLATE:
            case STONE_BRICK_STAIRS:
            case PEARLESCENT_FROGLIGHT:
            case DIORITE:
            case ACACIA_FENCE:
            case CRIMSON_HYPHAE:
            case BEDROCK:
            case OAK_SLAB:
            case BUBBLE_COLUMN:
            case CYAN_BANNER:
            case DRIPSTONE_BLOCK:
            case CRIMSON_BUTTON:
            case WARPED_DOOR:
            case JUNGLE_HANGING_SIGN:
            case JUNGLE_PLANKS:
            case SNOW:
            case PLAYER_HEAD:
            case ACACIA_FENCE_GATE:
            case WEATHERED_COPPER_GRATE:
            case MANGROVE_SIGN:
            case MAGENTA_CARPET:
            case YELLOW_WOOL:
            case LIME_SHULKER_BOX:
            case WATER:
            case JUNGLE_TRAPDOOR:
            case TORCHFLOWER_CROP:
            case WAXED_WEATHERED_COPPER_TRAPDOOR:
            case GRINDSTONE:
            case SEAGRASS:
            case LIGHT_GRAY_WALL_BANNER:
            case BLUE_ICE:
            case RED_NETHER_BRICKS:
            case GLASS_PANE:
            case POTTED_BLUE_ORCHID:
            case CRYING_OBSIDIAN:
            case SCULK_SHRIEKER:
            case WARPED_SLAB:
            case MANGROVE_STAIRS:
            case LECTERN:
            case PRISMARINE:
            case CRIMSON_STEM:
            case PURPLE_STAINED_GLASS:
            case POLISHED_TUFF_WALL:
            case CHISELED_SANDSTONE:
            case CLAY:
            case CUT_COPPER_STAIRS:
            case CHISELED_BOOKSHELF:
            case SMOOTH_STONE_SLAB:
            case DARK_OAK_WALL_HANGING_SIGN:
            case LADDER:
            case DEAD_BUBBLE_CORAL_BLOCK:
            case REDSTONE_TORCH:
            case MOSS_CARPET:
            case DEAD_FIRE_CORAL_FAN:
            case MOSSY_COBBLESTONE_SLAB:
            case RED_CANDLE_CAKE:
            case TARGET:
            case GRAY_WOOL:
            case WHITE_CONCRETE_POWDER:
            case WET_SPONGE:
            case YELLOW_TERRACOTTA:
            case LAVA:
            case ORANGE_CARPET:
            case BROWN_WALL_BANNER:
            case WAXED_WEATHERED_CUT_COPPER:
            case GRAY_BED:
            case CAKE:
            case YELLOW_CONCRETE:
            case ORANGE_BANNER:
            case STRIPPED_BIRCH_LOG:
            case DARK_OAK_SIGN:
            case GRANITE_SLAB:
            case RED_WALL_BANNER:
            case MUD_BRICK_STAIRS:
            case FIRE_CORAL:
            case WHITE_TERRACOTTA:
            case MYCELIUM:
            case OAK_HANGING_SIGN:
            case CRIMSON_ROOTS:
            case PINK_STAINED_GLASS_PANE:
            case MANGROVE_SLAB:
            case LIME_CONCRETE_POWDER:
            case DEAD_HORN_CORAL_FAN:
            case PINK_CANDLE_CAKE:
            case STRIPPED_CHERRY_WOOD:
            case WAXED_COPPER_BLOCK:
            case BROWN_BANNER:
            case STRIPPED_CHERRY_LOG:
            case BARREL:
            case CHERRY_LEAVES:
            case CHERRY_PRESSURE_PLATE:
            case SCULK:
            case WARPED_HYPHAE:
            case POLISHED_BLACKSTONE_BUTTON:
            case GRAY_WALL_BANNER:
            case MAGENTA_GLAZED_TERRACOTTA:
            case CHERRY_WALL_HANGING_SIGN:
            case HORN_CORAL:
            case CHISELED_STONE_BRICKS:
            case DEAD_TUBE_CORAL:
            case END_STONE_BRICK_SLAB:
            case SMITHING_TABLE:
            case GREEN_BED:
            case MAGMA_BLOCK:
            case SPRUCE_PRESSURE_PLATE:
            case PURPLE_TERRACOTTA:
            case CAULDRON:
            case PINK_CONCRETE_POWDER:
            case BAMBOO_TRAPDOOR:
            case BIRCH_LEAVES:
            case ACACIA_LEAVES:
            case BEE_NEST:
            case EXPOSED_COPPER:
            case MAGENTA_CONCRETE_POWDER:
            case END_PORTAL_FRAME:
            case WHITE_TULIP:
            case ANDESITE_WALL:
            case SANDSTONE_SLAB:
            case HAY_BLOCK:
            case LIGHT_BLUE_CANDLE:
            case VOID_AIR:
            case CAVE_VINES:
            case TUFF_SLAB:
            case COBBLESTONE_SLAB:
            case GRAY_BANNER:
            case PURPLE_SHULKER_BOX:
            case WEATHERED_COPPER:
            case WARPED_STAIRS:
            case SPRUCE_HANGING_SIGN:
            case RED_SAND:
            case CRACKED_POLISHED_BLACKSTONE_BRICKS:
            case EXPOSED_COPPER_BULB:
            case BAMBOO_SAPLING:
            case SPRUCE_WALL_HANGING_SIGN:
            case SMOOTH_SANDSTONE_SLAB:
            case ZOMBIE_HEAD:
            case DARK_OAK_LEAVES:
            case CHEST:
            case MANGROVE_LEAVES:
            case GRAY_STAINED_GLASS:
            case RAIL:
            case POTTED_BROWN_MUSHROOM:
            case PINK_CANDLE:
            case WHITE_CONCRETE:
            case WEATHERED_COPPER_DOOR:
            case IRON_ORE:
            case MOSS_BLOCK:
            case REINFORCED_DEEPSLATE:
            case STRIPPED_DARK_OAK_LOG:
            case RED_TERRACOTTA:
            case YELLOW_BANNER:
            case REDSTONE_WIRE:
            case SMALL_AMETHYST_BUD:
            case VINE:
            case WAXED_EXPOSED_CUT_COPPER_SLAB:
            case RED_BED:
            case CALCITE:
            case DARK_OAK_FENCE:
            case ATTACHED_MELON_STEM:
            case CUT_COPPER:
            case DEEPSLATE_TILE_STAIRS:
            case SOUL_LANTERN:
            case SWEET_BERRY_BUSH:
            case WAXED_WEATHERED_CUT_COPPER_SLAB:
            case STRIPPED_MANGROVE_LOG:
            case DEEPSLATE_EMERALD_ORE:
            case CREEPER_HEAD:
            case CRIMSON_TRAPDOOR:
            case END_STONE_BRICK_WALL:
            case CAVE_AIR:
            case POLISHED_DIORITE_SLAB:
            case WEEPING_VINES_PLANT:
            case YELLOW_CANDLE_CAKE:
            case BAMBOO_STAIRS:
            case BROWN_GLAZED_TERRACOTTA:
            case SMOOTH_SANDSTONE_STAIRS:
            case SMOOTH_BASALT:
            case CHAIN:
            case BROWN_CONCRETE:
            case WHITE_WOOL:
            case PUMPKIN_STEM:
            case BLUE_CONCRETE_POWDER:
            case BLUE_STAINED_GLASS:
            case POTTED_WITHER_ROSE:
            case OAK_FENCE:
            case HANGING_ROOTS:
            case WARPED_TRAPDOOR:
            case PRISMARINE_BRICK_STAIRS:
            case DEEPSLATE_COAL_ORE:
            case OAK_SIGN:
            case SMOOTH_RED_SANDSTONE:
            case CRIMSON_PLANKS:
            case BLUE_CANDLE:
            case FIRE_CORAL_FAN:
            case BIRCH_SIGN:
            case POTTED_CRIMSON_FUNGUS:
            case WAXED_OXIDIZED_COPPER:
            case BIRCH_PRESSURE_PLATE:
            case PURPLE_CANDLE_CAKE:
            case NETHERITE_BLOCK:
            case OXEYE_DAISY:
            case LIGHT:
            case DEEPSLATE:
            case LIGHT_BLUE_TERRACOTTA:
            case SUNFLOWER:
            case WEATHERED_CHISELED_COPPER:
            case SPRUCE_STAIRS:
            case MANGROVE_PLANKS:
            case DEAD_BRAIN_CORAL_FAN:
            case BLACK_BED:
            case JUNGLE_SIGN:
            case CYAN_CANDLE:
            case OXIDIZED_COPPER_TRAPDOOR:
            case AZURE_BLUET:
            case CAVE_VINES_PLANT:
            case COPPER_TRAPDOOR:
            case ORANGE_TULIP:
            case DIRT_PATH:
            case DEAD_BUBBLE_CORAL:
            case YELLOW_SHULKER_BOX:
            case CALIBRATED_SCULK_SENSOR:
            case SKELETON_SKULL:
            case CRAFTER:
            case HORN_CORAL_BLOCK:
            case MAGENTA_CANDLE:
            case GREEN_STAINED_GLASS_PANE:
            case STRIPPED_OAK_LOG:
            case POTTED_DANDELION:
            case CRIMSON_SLAB:
            case ANCIENT_DEBRIS:
            case BLACK_CONCRETE_POWDER:
            case REDSTONE_WALL_TORCH:
            case INFESTED_STONE:
            case LIME_CARPET:
            case PURPLE_BANNER:
            case PURPLE_CONCRETE_POWDER:
            case BIRCH_PLANKS:
            case LIGHT_GRAY_STAINED_GLASS_PANE:
            case POTTED_BIRCH_SAPLING:
            case GREEN_TERRACOTTA:
            case WHITE_STAINED_GLASS:
            case RED_CARPET:
            case ACACIA_PLANKS:
            case SLIME_BLOCK:
            case QUARTZ_PILLAR:
            case CHERRY_LOG:
            case CHERRY_BUTTON:
            case LIME_CONCRETE:
            case MUD_BRICKS:
            case STRIPPED_WARPED_STEM:
            case BLACK_CANDLE_CAKE:
            case LIME_CANDLE_CAKE:
            case PURPUR_STAIRS:
            case SOUL_SAND:
            case BROWN_TERRACOTTA:
            case WAXED_OXIDIZED_CUT_COPPER_STAIRS:
            case BAMBOO_BLOCK:
            case WARPED_WALL_SIGN:
            case BLUE_WOOL:
            case GLOW_LICHEN:
            case NOTE_BLOCK:
            case MOSSY_COBBLESTONE_STAIRS:
            case BAMBOO:
            case ACACIA_HANGING_SIGN:
            case RED_SHULKER_BOX:
            case MUDDY_MANGROVE_ROOTS:
            case TUFF_BRICK_WALL:
            case PISTON:
            case WAXED_EXPOSED_COPPER_GRATE:
            case BROWN_BED:
            case DEAD_FIRE_CORAL_BLOCK:
            case WITHER_ROSE:
            case POTTED_JUNGLE_SAPLING:
            case WAXED_CHISELED_COPPER:
            case BUBBLE_CORAL_BLOCK:
            case BLUE_BED:
            case LIGHT_GRAY_CARPET:
            case WHITE_BANNER:
            case BUBBLE_CORAL:
            case SMOOTH_QUARTZ:
            case SMOOTH_SANDSTONE:
            case SMOOTH_RED_SANDSTONE_STAIRS:
            case LIGHT_GRAY_BANNER:
            case SEA_PICKLE:
            case LIGHT_BLUE_GLAZED_TERRACOTTA:
            case SHULKER_BOX:
            case DISPENSER:
            case CYAN_STAINED_GLASS_PANE:
            case DAYLIGHT_DETECTOR:
            case SPONGE:
            case WAXED_OXIDIZED_COPPER_DOOR:
            case QUARTZ_STAIRS:
            case WAXED_OXIDIZED_COPPER_TRAPDOOR:
            case DIORITE_WALL:
            case WHITE_CARPET:
            case PURPLE_GLAZED_TERRACOTTA:
            case SPRUCE_PLANKS:
            case MANGROVE_ROOTS:
            case RED_STAINED_GLASS:
            case DEEPSLATE_IRON_ORE:
            case PURPLE_WALL_BANNER:
            case MOSSY_STONE_BRICKS:
            case GREEN_CARPET:
            case PURPUR_SLAB:
            case POLISHED_BLACKSTONE_BRICKS:
            case WAXED_WEATHERED_COPPER_GRATE:
            case CRACKED_STONE_BRICKS:
            case CYAN_CANDLE_CAKE:
            case JACK_O_LANTERN:
            case TRIPWIRE_HOOK:
            case TRAPPED_CHEST:
            case HOPPER:
            case WAXED_EXPOSED_COPPER_DOOR:
            case TUFF_WALL:
            case LANTERN:
            case GREEN_BANNER:
            case MAGENTA_STAINED_GLASS:
            case ENDER_CHEST:
            case IRON_BARS:
            case DANDELION:
            case IRON_TRAPDOOR:
            case GREEN_GLAZED_TERRACOTTA:
            case BROWN_CANDLE:
            case DEEPSLATE_BRICK_WALL:
            case COBBLESTONE:
            case ROSE_BUSH:
            case LIME_TERRACOTTA:
            case JUNGLE_WOOD:
            case WAXED_OXIDIZED_CUT_COPPER:
            case CARTOGRAPHY_TABLE:
            case POLISHED_DIORITE:
            case PINK_TULIP:
            case CRIMSON_NYLIUM:
            case STONE_SLAB:
            case ACACIA_PRESSURE_PLATE:
            case LIME_BED:
            case YELLOW_WALL_BANNER:
            case DARK_OAK_PRESSURE_PLATE:
            case ACACIA_SLAB:
            case JUNGLE_STAIRS:
            case WITHER_SKELETON_SKULL:
            case BLAST_FURNACE:
            case POLISHED_ANDESITE_STAIRS:
            case STRIPPED_JUNGLE_LOG:
            case GRASS_BLOCK:
            case ALLIUM:
            case BELL:
            case ACACIA_WALL_SIGN:
            case INFESTED_DEEPSLATE:
            case LIME_WOOL:
            case OXIDIZED_COPPER_DOOR:
            case LIGHT_GRAY_WOOL:
            case TUBE_CORAL_FAN:
            case WARPED_STEM:
            case WAXED_CUT_COPPER:
            case JUNGLE_LEAVES:
            case STRIPPED_CRIMSON_STEM:
            case LIGHT_BLUE_CARPET:
            case CHISELED_POLISHED_BLACKSTONE:
            case WARPED_WALL_HANGING_SIGN:
            case BRAIN_CORAL_BLOCK:
            case SOUL_WALL_TORCH:
            case POTTED_ACACIA_SAPLING:
            case COBBLED_DEEPSLATE_STAIRS:
            case LIGHT_BLUE_BANNER:
            case FLETCHING_TABLE:
            case DRAGON_WALL_HEAD:
            case MOSSY_COBBLESTONE_WALL:
            case CHIPPED_ANVIL:
            case POWDER_SNOW_CAULDRON:
            case RED_BANNER:
            case PURPUR_PILLAR:
            case HEAVY_WEIGHTED_PRESSURE_PLATE:
            case PINK_STAINED_GLASS:
            case CYAN_CONCRETE:
            case DEEPSLATE_TILE_SLAB:
            case STRIPPED_BIRCH_WOOD:
            case WAXED_OXIDIZED_CUT_COPPER_SLAB:
            case SCULK_CATALYST:
            case POLISHED_BLACKSTONE_STAIRS:
            case COPPER_DOOR:
            case LIME_BANNER:
            case KELP:
            case STONE_PRESSURE_PLATE:
            case DEEPSLATE_BRICK_STAIRS:
            case DROPPER:
            case REDSTONE_LAMP:
            case YELLOW_STAINED_GLASS_PANE:
            case BLACK_CARPET:
            case END_ROD:
            case DEAD_BUBBLE_CORAL_FAN:
            case PISTON_HEAD:
            case AZALEA_LEAVES:
            case BAMBOO_MOSAIC_STAIRS:
            case MOSSY_COBBLESTONE:
            case CYAN_WALL_BANNER:
            case FROSTED_ICE:
            case WEATHERED_CUT_COPPER_STAIRS:
            case WARPED_BUTTON:
            case CHERRY_WALL_SIGN:
            case EXPOSED_COPPER_GRATE:
            case POLISHED_DEEPSLATE:
            case WAXED_COPPER_GRATE:
            case POTTED_OXEYE_DAISY:
            case TUBE_CORAL:
            case BROWN_SHULKER_BOX:
            case TORCH:
            case POLISHED_GRANITE_STAIRS:
            case POLISHED_TUFF:
            case POLISHED_TUFF_STAIRS:
            case MANGROVE_WALL_SIGN:
            case LEVER:
            case AZALEA:
            case SUSPICIOUS_GRAVEL:
            case NETHER_QUARTZ_ORE:
            case POTTED_BAMBOO:
            case BRAIN_CORAL:
            case POTATOES:
            case DETECTOR_RAIL:
            case STRIPPED_ACACIA_LOG:
            case PINK_BED:
            case WAXED_EXPOSED_COPPER:
            case POTTED_OAK_SAPLING:
            case BLUE_CONCRETE:
            case BLACKSTONE_SLAB:
            case DAMAGED_ANVIL:
            case BAMBOO_SLAB:
            case CRIMSON_SIGN:
            case SPORE_BLOSSOM:
            case SPAWNER:
            case DARK_OAK_TRAPDOOR:
            case BLACK_BANNER:
            case STONE_BRICK_SLAB:
            case WAXED_WEATHERED_COPPER:
            case POTTED_PINK_TULIP:
            case DEEPSLATE_REDSTONE_ORE:
            case WAXED_WEATHERED_CHISELED_COPPER:
            case POLISHED_BLACKSTONE_PRESSURE_PLATE:
            case QUARTZ_BRICKS:
            case CRAFTING_TABLE:
            case BLACK_WALL_BANNER:
            case MOVING_PISTON:
            case SANDSTONE_WALL:
            case PURPLE_CONCRETE:
            case RED_SANDSTONE_WALL:
            case POLISHED_DEEPSLATE_SLAB:
            case DARK_OAK_WOOD:
            case CUT_COPPER_SLAB:
            case BIRCH_HANGING_SIGN:
            case LIGHT_BLUE_WOOL:
            case INFESTED_MOSSY_STONE_BRICKS:
            case LIGHT_GRAY_CONCRETE:
            case ORANGE_WOOL:
            case PINK_CARPET:
            case ORANGE_GLAZED_TERRACOTTA:
            case GRAY_GLAZED_TERRACOTTA:
            case ACACIA_STAIRS:
            case CHERRY_SLAB:
            case DEEPSLATE_BRICKS:
            case RAW_GOLD_BLOCK:
            case BIRCH_DOOR:
            case ROOTED_DIRT:
            case ANVIL:
            case RED_CONCRETE:
            case STRIPPED_ACACIA_WOOD:
            case WITHER_SKELETON_WALL_SKULL:
            case WAXED_WEATHERED_CUT_COPPER_STAIRS:
            case MOSSY_STONE_BRICK_STAIRS:
            case OAK_TRAPDOOR:
            case JUNGLE_SAPLING:
            case AMETHYST_BLOCK:
            case OAK_DOOR:
            case SNIFFER_EGG:
            case BLUE_CANDLE_CAKE:
            case ORANGE_BED:
            case BIRCH_BUTTON:
            case POTTED_TORCHFLOWER:
            case CHORUS_PLANT:
            case BIRCH_FENCE_GATE:
            case LIGHT_GRAY_CANDLE_CAKE:
            case EXPOSED_CHISELED_COPPER:
            case MANGROVE_PROPAGULE:
            case MANGROVE_WALL_HANGING_SIGN:
            case POTTED_POPPY:
            case BLACK_GLAZED_TERRACOTTA:
            case POTTED_ALLIUM:
            case PLAYER_WALL_HEAD:
            case WAXED_OXIDIZED_COPPER_GRATE:
            case CYAN_BED:
            case SPRUCE_DOOR:
            case SMOOTH_QUARTZ_SLAB:
            case RED_STAINED_GLASS_PANE:
            case OCHRE_FROGLIGHT:
            case BROWN_WOOL:
            case BRICK_WALL:
            case RAW_COPPER_BLOCK:
            case PURPLE_BED:
            case ORANGE_CONCRETE:
            case MUD_BRICK_WALL:
            case WARPED_WART_BLOCK:
            case DARK_PRISMARINE:
            case PUMPKIN:
            case RAW_IRON_BLOCK:
            case DARK_OAK_PLANKS:
            case DEAD_HORN_CORAL:
            case CANDLE:
            case LIGHTNING_ROD:
            case OAK_BUTTON:
            case GRANITE_WALL:
            case ORANGE_CONCRETE_POWDER:
            case DARK_OAK_HANGING_SIGN:
            case PETRIFIED_OAK_SLAB:
            case REDSTONE_BLOCK:
            case WHEAT:
            case HONEYCOMB_BLOCK:
            case ORANGE_STAINED_GLASS_PANE:
            case SCULK_VEIN:
            case ACACIA_SAPLING:
            case QUARTZ_BLOCK:
            case POTTED_CRIMSON_ROOTS:
            case WARPED_FUNGUS:
            case COBBLED_DEEPSLATE:
            case WARPED_FENCE:
            case DARK_OAK_FENCE_GATE:
            case WAXED_EXPOSED_CUT_COPPER:
            case DEAD_BUBBLE_CORAL_WALL_FAN:
            case POWDER_SNOW:
            case DIRT:
            case ANDESITE_STAIRS:
            case PINK_CONCRETE:
            case WATER_CAULDRON:
            case JUNGLE_SLAB:
            case LOOM:
            case BIRCH_LOG:
            case CREEPER_WALL_HEAD:
            case SMOKER:
            case OAK_LEAVES:
            case LIGHT_BLUE_CANDLE_CAKE:
            case CARROTS:
            case OAK_FENCE_GATE:
            case GREEN_SHULKER_BOX:
            case POTTED_ORANGE_TULIP:
            case ICE:
            case MOSSY_STONE_BRICK_WALL:
            case WHITE_CANDLE_CAKE:
            case GRANITE:
            case BUBBLE_CORAL_FAN:
            case BRICK_SLAB:
            case RED_SANDSTONE_STAIRS:
            case PITCHER_CROP:
            case BRICKS:
            case CARVED_PUMPKIN:
            case MELON_STEM:
            case RED_SANDSTONE_SLAB:
            case GRAY_STAINED_GLASS_PANE:
            case GRAY_CARPET:
            case OXIDIZED_CUT_COPPER_STAIRS:
            case GREEN_WOOL:
            case LILY_PAD:
            case YELLOW_GLAZED_TERRACOTTA:
            case SPRUCE_LOG:
            case MANGROVE_TRAPDOOR:
            case ANDESITE:
            case CRIMSON_HANGING_SIGN:
            case DEEPSLATE_TILE_WALL:
            case BLUE_SHULKER_BOX:
            case POLISHED_DEEPSLATE_STAIRS:
            case LILY_OF_THE_VALLEY:
            case EXPOSED_CUT_COPPER:
            case POLISHED_BLACKSTONE:
            case MANGROVE_HANGING_SIGN:
            case PINK_PETALS:
            case MAGENTA_CANDLE_CAKE:
            case GRAY_CANDLE_CAKE:
            case POINTED_DRIPSTONE:
            case RED_NETHER_BRICK_STAIRS:
            case JUNGLE_PRESSURE_PLATE:
            case DARK_OAK_STAIRS:
            case BREWING_STAND:
            case WHITE_CANDLE:
            case NETHERRACK:
            case BAMBOO_MOSAIC_SLAB:
            case RED_TULIP:
            case TUFF:
            case EMERALD_ORE:
            case ORANGE_WALL_BANNER:
            case POLISHED_BLACKSTONE_BRICK_WALL:
            case JUNGLE_BUTTON:
            case DEAD_BRAIN_CORAL:
            case BIRCH_WALL_HANGING_SIGN:
            case PACKED_ICE:
            case STONE:
            case BLACKSTONE_STAIRS:
            case BLUE_BANNER:
            case POTTED_CACTUS:
            case COPPER_GRATE:
            case PRISMARINE_WALL:
            case DIAMOND_BLOCK:
            case POTTED_WARPED_ROOTS:
            case MANGROVE_LOG:
            case DIORITE_SLAB:
            case TUBE_CORAL_BLOCK:
            case SPRUCE_TRAPDOOR:
            case YELLOW_STAINED_GLASS:
            case LIGHT_BLUE_STAINED_GLASS:
            case WAXED_WEATHERED_COPPER_BULB:
            case POPPY:
            case GLOWSTONE:
            case RESPAWN_ANCHOR:
            case FIRE:
            case COBBLED_DEEPSLATE_WALL:
            case DEEPSLATE_BRICK_SLAB:
            case RED_NETHER_BRICK_WALL:
            case OAK_SAPLING:
            case CHISELED_RED_SANDSTONE:
            case DARK_OAK_LOG:
            case NETHER_BRICK_FENCE:
            case ORANGE_CANDLE_CAKE:
            case SUSPICIOUS_SAND:
            case SMOOTH_STONE:
            case BLACKSTONE_WALL:
            case BOOKSHELF:
            case COPPER_ORE:
            case CRIMSON_WALL_HANGING_SIGN:
            case DARK_PRISMARINE_STAIRS:
            case WHITE_WALL_BANNER:
            case TRIAL_SPAWNER:
            case LIGHT_GRAY_CANDLE:
            case LAPIS_BLOCK:
            case PURPLE_CARPET:
            case FIRE_CORAL_BLOCK:
            case PIGLIN_WALL_HEAD:
            case BROWN_CANDLE_CAKE:
            case BROWN_STAINED_GLASS_PANE:
            case BLACK_TERRACOTTA:
            case MAGENTA_SHULKER_BOX:
            case SNOW_BLOCK:
            case CHERRY_SIGN:
            case POTTED_DEAD_BUSH:
            case NETHER_WART:
            case PIGLIN_HEAD:
            case PODZOL:
            case CYAN_WOOL:
            case SANDSTONE_STAIRS:
            case COMPOSTER:
            case WEATHERED_COPPER_BULB:
            case CACTUS:
            case JUKEBOX:
            case LIGHT_GRAY_TERRACOTTA:
            case WHITE_BED:
            case BIRCH_SLAB:
            case SPRUCE_SLAB:
            case PEONY:
            case COARSE_DIRT:
            case ACACIA_SIGN:
            case WAXED_CUT_COPPER_STAIRS:
            case SEA_LANTERN:
            case YELLOW_CARPET:
            case WAXED_EXPOSED_CHISELED_COPPER:
            case DEAD_BUSH:
            case GREEN_CANDLE:
            case LIGHT_BLUE_SHULKER_BOX:
            case PRISMARINE_SLAB:
            case EXPOSED_CUT_COPPER_STAIRS:
            case BIG_DRIPLEAF_STEM:
            case OBSIDIAN:
            case DECORATED_POT:
            case LIGHT_GRAY_GLAZED_TERRACOTTA:
            case MAGENTA_TERRACOTTA:
            case MAGENTA_WALL_BANNER:
            case TURTLE_EGG:
            case STRIPPED_MANGROVE_WOOD:
            case FARMLAND:
            case JUNGLE_WALL_HANGING_SIGN:
            case JUNGLE_FENCE:
            case LIGHT_WEIGHTED_PRESSURE_PLATE:
            case COBBLED_DEEPSLATE_SLAB:
            case CHORUS_FLOWER:
            case WEATHERED_CUT_COPPER:
            case IRON_DOOR:
            case PURPLE_STAINED_GLASS_PANE:
            case POLISHED_GRANITE:
            case BLUE_WALL_BANNER:
            case MUD_BRICK_SLAB:
            case BLUE_STAINED_GLASS_PANE:
            case PINK_BANNER:
            case OXIDIZED_CUT_COPPER:
            case CHISELED_QUARTZ_BLOCK:
            case SOUL_CAMPFIRE:
            case STRIPPED_WARPED_HYPHAE:
            case BAMBOO_BUTTON:
            case BROWN_MUSHROOM_BLOCK:
            case CRIMSON_DOOR:
            case FIRE_CORAL_WALL_FAN:
            case PINK_WOOL:
            case EXPOSED_COPPER_DOOR:
            case DARK_OAK_SAPLING:
            case CHERRY_HANGING_SIGN:
            case CHERRY_TRAPDOOR:
            case END_STONE_BRICKS:
            case BEETROOTS:
            case TUFF_BRICK_STAIRS:
            case LIGHT_BLUE_WALL_BANNER:
            case ACACIA_TRAPDOOR:
            case OXIDIZED_COPPER:
            case END_STONE:
            case BLUE_ORCHID:
            case BLACK_CANDLE:
            case LIGHT_GRAY_CONCRETE_POWDER:
            case SMOOTH_QUARTZ_STAIRS:
            case FURNACE:
            case DIORITE_STAIRS:
            case LARGE_AMETHYST_BUD:
            case CRIMSON_WALL_SIGN:
            case MANGROVE_PRESSURE_PLATE:
            case CRACKED_NETHER_BRICKS:
            case POTTED_DARK_OAK_SAPLING:
            case COMMAND_BLOCK:
            case DEAD_FIRE_CORAL_WALL_FAN:
            case WEATHERED_COPPER_TRAPDOOR:
            case CRIMSON_FENCE_GATE:
            case DEAD_TUBE_CORAL_BLOCK:
            case ACTIVATOR_RAIL:
            case BAMBOO_HANGING_SIGN:
            case REPEATING_COMMAND_BLOCK:
            case SKELETON_WALL_SKULL:
            case CRIMSON_PRESSURE_PLATE:
            case MEDIUM_AMETHYST_BUD:
            case OAK_PLANKS:
            case ORANGE_CANDLE:
            case GREEN_CANDLE_CAKE:
            case WHITE_SHULKER_BOX:
            case WARPED_SIGN:
            case SOUL_SOIL:
            case WAXED_EXPOSED_COPPER_BULB:
            case CHISELED_COPPER:
            case OXIDIZED_CHISELED_COPPER:
            case MANGROVE_DOOR:
            case TUFF_BRICK_SLAB:
            case CHERRY_STAIRS:
            case PITCHER_PLANT:
            case GILDED_BLACKSTONE:
            case RED_SANDSTONE:
            case STONE_BRICK_WALL:
            case QUARTZ_SLAB:
            case CUT_SANDSTONE:
            case BAMBOO_SIGN:
            case ZOMBIE_WALL_HEAD:
            case VERDANT_FROGLIGHT:
            case FERN:
            case SPRUCE_BUTTON:
            case SHROOMLIGHT:
            case FLOWERING_AZALEA:
            case WAXED_COPPER_BULB:
            case INFESTED_COBBLESTONE:
            case COPPER_BULB:
            case NETHER_BRICK_SLAB:
            case GOLD_ORE:
            case TWISTING_VINES:
            case ORANGE_TERRACOTTA:
            case DEEPSLATE_TILES:
            case BAMBOO_FENCE:
            case ENCHANTING_TABLE:
            case WHITE_GLAZED_TERRACOTTA:
            case RED_GLAZED_TERRACOTTA:
            case GOLD_BLOCK:
            case POLISHED_DIORITE_STAIRS:
            case DEEPSLATE_DIAMOND_ORE:
            case OBSERVER:
            case POTTED_AZURE_BLUET:
            case CAMPFIRE:
            case POLISHED_DEEPSLATE_WALL:
            case CHERRY_WOOD:
            case DARK_OAK_SLAB:
            case LIME_GLAZED_TERRACOTTA:
            case JUNGLE_LOG:
            case PACKED_MUD:
            case DRAGON_EGG:
            case BARRIER:
            case BRICK_STAIRS:
            case BUBBLE_CORAL_WALL_FAN:
            case LIME_STAINED_GLASS:
            case ORANGE_SHULKER_BOX:
            case STRIPPED_SPRUCE_WOOD:
            case ACACIA_WOOD:
            case CHERRY_FENCE_GATE:
            case ATTACHED_PUMPKIN_STEM:
            case STRIPPED_JUNGLE_WOOD:
            case COAL_ORE:
            case WAXED_COPPER_TRAPDOOR:
            case LAVA_CAULDRON:
            case CUT_RED_SANDSTONE_SLAB:
            case OXIDIZED_COPPER_BULB:
            case POLISHED_ANDESITE:
            case BLACK_STAINED_GLASS:
            case MUD:
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
            case MUSHROOM_STEW:
            case BREAD:
            case PORKCHOP:
            case COOKED_PORKCHOP:
            case GOLDEN_APPLE:
            case ENCHANTED_GOLDEN_APPLE:
            case COD:
            case SALMON:
            case TROPICAL_FISH:
            case PUFFERFISH:
            case COOKED_COD:
            case COOKED_SALMON:
            case COOKIE:
            case MELON_SLICE:
            case DRIED_KELP:
            case BEEF:
            case COOKED_BEEF:
            case CHICKEN:
            case COOKED_CHICKEN:
            case ROTTEN_FLESH:
            case SPIDER_EYE:
            case CARROT:
            case POTATO:
            case BAKED_POTATO:
            case POISONOUS_POTATO:
            case GOLDEN_CARROT:
            case PUMPKIN_PIE:
            case RABBIT:
            case COOKED_RABBIT:
            case RABBIT_STEW:
            case MUTTON:
            case COOKED_MUTTON:
            case CHORUS_FRUIT:
            case BEETROOT:
            case BEETROOT_SOUP:
            case SUSPICIOUS_STEW:
            case SWEET_BERRIES:
            case GLOW_BERRIES:
            case HONEY_BOTTLE:
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
            case MUSIC_DISC_13:
            case MUSIC_DISC_CAT:
            case MUSIC_DISC_BLOCKS:
            case MUSIC_DISC_CHIRP:
            case MUSIC_DISC_FAR:
            case MUSIC_DISC_MALL:
            case MUSIC_DISC_MELLOHI:
            case MUSIC_DISC_STAL:
            case MUSIC_DISC_STRAD:
            case MUSIC_DISC_WARD:
            case MUSIC_DISC_11:
            case MUSIC_DISC_WAIT:
            case MUSIC_DISC_OTHERSIDE:
            case MUSIC_DISC_RELIC:
            case MUSIC_DISC_5:
            case MUSIC_DISC_PIGSTEP:
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
            case STONE:
            case GRANITE:
            case POLISHED_GRANITE:
            case DIORITE:
            case POLISHED_DIORITE:
            case ANDESITE:
            case POLISHED_ANDESITE:
            case GRASS_BLOCK:
            case DIRT:
            case COARSE_DIRT:
            case PODZOL:
            case COBBLESTONE:
            case OAK_PLANKS:
            case SPRUCE_PLANKS:
            case BIRCH_PLANKS:
            case JUNGLE_PLANKS:
            case ACACIA_PLANKS:
            case CHERRY_PLANKS:
            case DARK_OAK_PLANKS:
            case MANGROVE_PLANKS:
            case BAMBOO_PLANKS:
            case BAMBOO_MOSAIC:
            case BEDROCK:
            case SAND:
            case SUSPICIOUS_SAND:
            case RED_SAND:
            case GRAVEL:
            case SUSPICIOUS_GRAVEL:
            case GOLD_ORE:
            case DEEPSLATE_GOLD_ORE:
            case IRON_ORE:
            case DEEPSLATE_IRON_ORE:
            case COAL_ORE:
            case DEEPSLATE_COAL_ORE:
            case NETHER_GOLD_ORE:
            case OAK_LOG:
            case SPRUCE_LOG:
            case BIRCH_LOG:
            case JUNGLE_LOG:
            case ACACIA_LOG:
            case CHERRY_LOG:
            case DARK_OAK_LOG:
            case MANGROVE_LOG:
            case MANGROVE_ROOTS:
            case MUDDY_MANGROVE_ROOTS:
            case BAMBOO_BLOCK:
            case STRIPPED_SPRUCE_LOG:
            case STRIPPED_BIRCH_LOG:
            case STRIPPED_JUNGLE_LOG:
            case STRIPPED_ACACIA_LOG:
            case STRIPPED_CHERRY_LOG:
            case STRIPPED_DARK_OAK_LOG:
            case STRIPPED_OAK_LOG:
            case STRIPPED_MANGROVE_LOG:
            case STRIPPED_BAMBOO_BLOCK:
            case OAK_WOOD:
            case SPRUCE_WOOD:
            case BIRCH_WOOD:
            case JUNGLE_WOOD:
            case ACACIA_WOOD:
            case CHERRY_WOOD:
            case DARK_OAK_WOOD:
            case MANGROVE_WOOD:
            case STRIPPED_OAK_WOOD:
            case STRIPPED_SPRUCE_WOOD:
            case STRIPPED_BIRCH_WOOD:
            case STRIPPED_JUNGLE_WOOD:
            case STRIPPED_ACACIA_WOOD:
            case STRIPPED_CHERRY_WOOD:
            case STRIPPED_DARK_OAK_WOOD:
            case STRIPPED_MANGROVE_WOOD:
            case OAK_LEAVES:
            case SPRUCE_LEAVES:
            case BIRCH_LEAVES:
            case JUNGLE_LEAVES:
            case ACACIA_LEAVES:
            case CHERRY_LEAVES:
            case DARK_OAK_LEAVES:
            case MANGROVE_LEAVES:
            case AZALEA_LEAVES:
            case FLOWERING_AZALEA_LEAVES:
            case SPONGE:
            case WET_SPONGE:
            case GLASS:
            case LAPIS_ORE:
            case DEEPSLATE_LAPIS_ORE:
            case LAPIS_BLOCK:
            case DISPENSER:
            case SANDSTONE:
            case CHISELED_SANDSTONE:
            case CUT_SANDSTONE:
            case NOTE_BLOCK:
            case WHITE_BED:
            case ORANGE_BED:
            case MAGENTA_BED:
            case LIGHT_BLUE_BED:
            case YELLOW_BED:
            case LIME_BED:
            case PINK_BED:
            case GRAY_BED:
            case LIGHT_GRAY_BED:
            case CYAN_BED:
            case PURPLE_BED:
            case BLUE_BED:
            case BROWN_BED:
            case GREEN_BED:
            case RED_BED:
            case BLACK_BED:
            case STICKY_PISTON:
            case PISTON:
            case PISTON_HEAD:
            case WHITE_WOOL:
            case ORANGE_WOOL:
            case MAGENTA_WOOL:
            case LIGHT_BLUE_WOOL:
            case YELLOW_WOOL:
            case LIME_WOOL:
            case PINK_WOOL:
            case GRAY_WOOL:
            case LIGHT_GRAY_WOOL:
            case CYAN_WOOL:
            case PURPLE_WOOL:
            case BLUE_WOOL:
            case BROWN_WOOL:
            case GREEN_WOOL:
            case RED_WOOL:
            case BLACK_WOOL:
            case MOVING_PISTON:
            case GOLD_BLOCK:
            case IRON_BLOCK:
            case BRICKS:
            case TNT:
            case BOOKSHELF:
            case CHISELED_BOOKSHELF:
            case MOSSY_COBBLESTONE:
            case OBSIDIAN:
            case SPAWNER:
            case OAK_STAIRS:
            case CHEST:
            case DIAMOND_ORE:
            case DEEPSLATE_DIAMOND_ORE:
            case DIAMOND_BLOCK:
            case CRAFTING_TABLE:
            case FARMLAND:
            case FURNACE:
            case OAK_SIGN:
            case SPRUCE_SIGN:
            case BIRCH_SIGN:
            case ACACIA_SIGN:
            case CHERRY_SIGN:
            case JUNGLE_SIGN:
            case DARK_OAK_SIGN:
            case MANGROVE_SIGN:
            case BAMBOO_SIGN:
            case OAK_DOOR:
            case COBBLESTONE_STAIRS:
            case OAK_WALL_SIGN:
            case SPRUCE_WALL_SIGN:
            case BIRCH_WALL_SIGN:
            case ACACIA_WALL_SIGN:
            case CHERRY_WALL_SIGN:
            case JUNGLE_WALL_SIGN:
            case DARK_OAK_WALL_SIGN:
            case MANGROVE_WALL_SIGN:
            case BAMBOO_WALL_SIGN:
            case OAK_HANGING_SIGN:
            case SPRUCE_HANGING_SIGN:
            case BIRCH_HANGING_SIGN:
            case ACACIA_HANGING_SIGN:
            case CHERRY_HANGING_SIGN:
            case JUNGLE_HANGING_SIGN:
            case DARK_OAK_HANGING_SIGN:
            case CRIMSON_HANGING_SIGN:
            case WARPED_HANGING_SIGN:
            case MANGROVE_HANGING_SIGN:
            case BAMBOO_HANGING_SIGN:
            case OAK_WALL_HANGING_SIGN:
            case SPRUCE_WALL_HANGING_SIGN:
            case BIRCH_WALL_HANGING_SIGN:
            case ACACIA_WALL_HANGING_SIGN:
            case CHERRY_WALL_HANGING_SIGN:
            case JUNGLE_WALL_HANGING_SIGN:
            case DARK_OAK_WALL_HANGING_SIGN:
            case MANGROVE_WALL_HANGING_SIGN:
            case CRIMSON_WALL_HANGING_SIGN:
            case WARPED_WALL_HANGING_SIGN:
            case BAMBOO_WALL_HANGING_SIGN:
            case STONE_PRESSURE_PLATE:
            case IRON_DOOR:
            case OAK_PRESSURE_PLATE:
            case SPRUCE_PRESSURE_PLATE:
            case BIRCH_PRESSURE_PLATE:
            case JUNGLE_PRESSURE_PLATE:
            case ACACIA_PRESSURE_PLATE:
            case CHERRY_PRESSURE_PLATE:
            case DARK_OAK_PRESSURE_PLATE:
            case MANGROVE_PRESSURE_PLATE:
            case BAMBOO_PRESSURE_PLATE:
            case REDSTONE_ORE:
            case DEEPSLATE_REDSTONE_ORE:
            case ICE:
            case SNOW_BLOCK:
            case CACTUS:
            case CLAY:
            case JUKEBOX:
            case OAK_FENCE:
            case NETHERRACK:
            case SOUL_SAND:
            case SOUL_SOIL:
            case BASALT:
            case POLISHED_BASALT:
            case GLOWSTONE:
            case CARVED_PUMPKIN:
            case JACK_O_LANTERN:
            case CAKE:
            case WHITE_STAINED_GLASS:
            case ORANGE_STAINED_GLASS:
            case MAGENTA_STAINED_GLASS:
            case LIGHT_BLUE_STAINED_GLASS:
            case YELLOW_STAINED_GLASS:
            case LIME_STAINED_GLASS:
            case PINK_STAINED_GLASS:
            case GRAY_STAINED_GLASS:
            case LIGHT_GRAY_STAINED_GLASS:
            case CYAN_STAINED_GLASS:
            case PURPLE_STAINED_GLASS:
            case BLUE_STAINED_GLASS:
            case BROWN_STAINED_GLASS:
            case GREEN_STAINED_GLASS:
            case RED_STAINED_GLASS:
            case BLACK_STAINED_GLASS:
            case OAK_TRAPDOOR:
            case SPRUCE_TRAPDOOR:
            case BIRCH_TRAPDOOR:
            case JUNGLE_TRAPDOOR:
            case ACACIA_TRAPDOOR:
            case CHERRY_TRAPDOOR:
            case DARK_OAK_TRAPDOOR:
            case MANGROVE_TRAPDOOR:
            case BAMBOO_TRAPDOOR:
            case STONE_BRICKS:
            case MOSSY_STONE_BRICKS:
            case CRACKED_STONE_BRICKS:
            case CHISELED_STONE_BRICKS:
            case PACKED_MUD:
            case MUD_BRICKS:
            case INFESTED_STONE:
            case INFESTED_COBBLESTONE:
            case INFESTED_STONE_BRICKS:
            case INFESTED_MOSSY_STONE_BRICKS:
            case INFESTED_CRACKED_STONE_BRICKS:
            case INFESTED_CHISELED_STONE_BRICKS:
            case BROWN_MUSHROOM_BLOCK:
            case RED_MUSHROOM_BLOCK:
            case MUSHROOM_STEM:
            case IRON_BARS:
            case CHAIN:
            case GLASS_PANE:
            case PUMPKIN:
            case MELON:
            case OAK_FENCE_GATE:
            case BRICK_STAIRS:
            case STONE_BRICK_STAIRS:
            case MUD_BRICK_STAIRS:
            case MYCELIUM:
            case NETHER_BRICKS:
            case NETHER_BRICK_FENCE:
            case NETHER_BRICK_STAIRS:
            case ENCHANTING_TABLE:
            case BREWING_STAND:
            case CAULDRON:
            case WATER_CAULDRON:
            case LAVA_CAULDRON:
            case POWDER_SNOW_CAULDRON:
            case END_PORTAL_FRAME:
            case END_STONE:
            case DRAGON_EGG:
            case REDSTONE_LAMP:
            case SANDSTONE_STAIRS:
            case EMERALD_ORE:
            case DEEPSLATE_EMERALD_ORE:
            case ENDER_CHEST:
            case EMERALD_BLOCK:
            case SPRUCE_STAIRS:
            case BIRCH_STAIRS:
            case JUNGLE_STAIRS:
            case COMMAND_BLOCK:
            case BEACON:
            case COBBLESTONE_WALL:
            case MOSSY_COBBLESTONE_WALL:
            case ANVIL:
            case CHIPPED_ANVIL:
            case DAMAGED_ANVIL:
            case TRAPPED_CHEST:
            case LIGHT_WEIGHTED_PRESSURE_PLATE:
            case HEAVY_WEIGHTED_PRESSURE_PLATE:
            case DAYLIGHT_DETECTOR:
            case REDSTONE_BLOCK:
            case NETHER_QUARTZ_ORE:
            case HOPPER:
            case QUARTZ_BLOCK:
            case CHISELED_QUARTZ_BLOCK:
            case QUARTZ_PILLAR:
            case QUARTZ_STAIRS:
            case DROPPER:
            case WHITE_TERRACOTTA:
            case ORANGE_TERRACOTTA:
            case MAGENTA_TERRACOTTA:
            case LIGHT_BLUE_TERRACOTTA:
            case YELLOW_TERRACOTTA:
            case LIME_TERRACOTTA:
            case PINK_TERRACOTTA:
            case GRAY_TERRACOTTA:
            case LIGHT_GRAY_TERRACOTTA:
            case CYAN_TERRACOTTA:
            case PURPLE_TERRACOTTA:
            case BLUE_TERRACOTTA:
            case BROWN_TERRACOTTA:
            case GREEN_TERRACOTTA:
            case RED_TERRACOTTA:
            case BLACK_TERRACOTTA:
            case WHITE_STAINED_GLASS_PANE:
            case ORANGE_STAINED_GLASS_PANE:
            case MAGENTA_STAINED_GLASS_PANE:
            case LIGHT_BLUE_STAINED_GLASS_PANE:
            case YELLOW_STAINED_GLASS_PANE:
            case LIME_STAINED_GLASS_PANE:
            case PINK_STAINED_GLASS_PANE:
            case GRAY_STAINED_GLASS_PANE:
            case LIGHT_GRAY_STAINED_GLASS_PANE:
            case CYAN_STAINED_GLASS_PANE:
            case PURPLE_STAINED_GLASS_PANE:
            case BLUE_STAINED_GLASS_PANE:
            case BROWN_STAINED_GLASS_PANE:
            case GREEN_STAINED_GLASS_PANE:
            case RED_STAINED_GLASS_PANE:
            case BLACK_STAINED_GLASS_PANE:
            case ACACIA_STAIRS:
            case CHERRY_STAIRS:
            case DARK_OAK_STAIRS:
            case MANGROVE_STAIRS:
            case BAMBOO_STAIRS:
            case BAMBOO_MOSAIC_STAIRS:
            case SLIME_BLOCK:
            case BARRIER:
            case IRON_TRAPDOOR:
            case PRISMARINE:
            case PRISMARINE_BRICKS:
            case DARK_PRISMARINE:
            case PRISMARINE_STAIRS:
            case PRISMARINE_BRICK_STAIRS:
            case DARK_PRISMARINE_STAIRS:
            case PRISMARINE_SLAB:
            case PRISMARINE_BRICK_SLAB:
            case DARK_PRISMARINE_SLAB:
            case SEA_LANTERN:
            case HAY_BLOCK:
            case TERRACOTTA:
            case COAL_BLOCK:
            case PACKED_ICE:
            case WHITE_BANNER:
            case ORANGE_BANNER:
            case MAGENTA_BANNER:
            case LIGHT_BLUE_BANNER:
            case YELLOW_BANNER:
            case LIME_BANNER:
            case PINK_BANNER:
            case GRAY_BANNER:
            case LIGHT_GRAY_BANNER:
            case CYAN_BANNER:
            case PURPLE_BANNER:
            case BLUE_BANNER:
            case BROWN_BANNER:
            case GREEN_BANNER:
            case RED_BANNER:
            case BLACK_BANNER:
            case WHITE_WALL_BANNER:
            case ORANGE_WALL_BANNER:
            case MAGENTA_WALL_BANNER:
            case LIGHT_BLUE_WALL_BANNER:
            case YELLOW_WALL_BANNER:
            case LIME_WALL_BANNER:
            case PINK_WALL_BANNER:
            case GRAY_WALL_BANNER:
            case LIGHT_GRAY_WALL_BANNER:
            case CYAN_WALL_BANNER:
            case PURPLE_WALL_BANNER:
            case BLUE_WALL_BANNER:
            case BROWN_WALL_BANNER:
            case GREEN_WALL_BANNER:
            case RED_WALL_BANNER:
            case BLACK_WALL_BANNER:
            case RED_SANDSTONE:
            case CHISELED_RED_SANDSTONE:
            case CUT_RED_SANDSTONE:
            case RED_SANDSTONE_STAIRS:
            case OAK_SLAB:
            case SPRUCE_SLAB:
            case BIRCH_SLAB:
            case JUNGLE_SLAB:
            case ACACIA_SLAB:
            case CHERRY_SLAB:
            case DARK_OAK_SLAB:
            case MANGROVE_SLAB:
            case BAMBOO_SLAB:
            case BAMBOO_MOSAIC_SLAB:
            case STONE_SLAB:
            case SMOOTH_STONE_SLAB:
            case SANDSTONE_SLAB:
            case CUT_SANDSTONE_SLAB:
            case PETRIFIED_OAK_SLAB:
            case COBBLESTONE_SLAB:
            case BRICK_SLAB:
            case STONE_BRICK_SLAB:
            case MUD_BRICK_SLAB:
            case NETHER_BRICK_SLAB:
            case QUARTZ_SLAB:
            case RED_SANDSTONE_SLAB:
            case CUT_RED_SANDSTONE_SLAB:
            case PURPUR_SLAB:
            case SMOOTH_STONE:
            case SMOOTH_SANDSTONE:
            case SMOOTH_QUARTZ:
            case SMOOTH_RED_SANDSTONE:
            case SPRUCE_FENCE_GATE:
            case BIRCH_FENCE_GATE:
            case JUNGLE_FENCE_GATE:
            case ACACIA_FENCE_GATE:
            case CHERRY_FENCE_GATE:
            case DARK_OAK_FENCE_GATE:
            case MANGROVE_FENCE_GATE:
            case BAMBOO_FENCE_GATE:
            case SPRUCE_FENCE:
            case BIRCH_FENCE:
            case JUNGLE_FENCE:
            case ACACIA_FENCE:
            case CHERRY_FENCE:
            case DARK_OAK_FENCE:
            case MANGROVE_FENCE:
            case BAMBOO_FENCE:
            case SPRUCE_DOOR:
            case BIRCH_DOOR:
            case JUNGLE_DOOR:
            case ACACIA_DOOR:
            case CHERRY_DOOR:
            case DARK_OAK_DOOR:
            case MANGROVE_DOOR:
            case BAMBOO_DOOR:
            case PURPUR_BLOCK:
            case PURPUR_PILLAR:
            case PURPUR_STAIRS:
            case END_STONE_BRICKS:
            case DIRT_PATH:
            case REPEATING_COMMAND_BLOCK:
            case CHAIN_COMMAND_BLOCK:
            case FROSTED_ICE:
            case MAGMA_BLOCK:
            case NETHER_WART_BLOCK:
            case RED_NETHER_BRICKS:
            case BONE_BLOCK:
            case OBSERVER:
            case SHULKER_BOX:
            case WHITE_SHULKER_BOX:
            case ORANGE_SHULKER_BOX:
            case MAGENTA_SHULKER_BOX:
            case LIGHT_BLUE_SHULKER_BOX:
            case YELLOW_SHULKER_BOX:
            case LIME_SHULKER_BOX:
            case PINK_SHULKER_BOX:
            case GRAY_SHULKER_BOX:
            case LIGHT_GRAY_SHULKER_BOX:
            case CYAN_SHULKER_BOX:
            case PURPLE_SHULKER_BOX:
            case BLUE_SHULKER_BOX:
            case BROWN_SHULKER_BOX:
            case GREEN_SHULKER_BOX:
            case RED_SHULKER_BOX:
            case BLACK_SHULKER_BOX:
            case WHITE_GLAZED_TERRACOTTA:
            case ORANGE_GLAZED_TERRACOTTA:
            case MAGENTA_GLAZED_TERRACOTTA:
            case LIGHT_BLUE_GLAZED_TERRACOTTA:
            case YELLOW_GLAZED_TERRACOTTA:
            case LIME_GLAZED_TERRACOTTA:
            case PINK_GLAZED_TERRACOTTA:
            case GRAY_GLAZED_TERRACOTTA:
            case LIGHT_GRAY_GLAZED_TERRACOTTA:
            case CYAN_GLAZED_TERRACOTTA:
            case PURPLE_GLAZED_TERRACOTTA:
            case BLUE_GLAZED_TERRACOTTA:
            case BROWN_GLAZED_TERRACOTTA:
            case GREEN_GLAZED_TERRACOTTA:
            case RED_GLAZED_TERRACOTTA:
            case BLACK_GLAZED_TERRACOTTA:
            case WHITE_CONCRETE:
            case ORANGE_CONCRETE:
            case MAGENTA_CONCRETE:
            case LIGHT_BLUE_CONCRETE:
            case YELLOW_CONCRETE:
            case LIME_CONCRETE:
            case PINK_CONCRETE:
            case GRAY_CONCRETE:
            case LIGHT_GRAY_CONCRETE:
            case CYAN_CONCRETE:
            case PURPLE_CONCRETE:
            case BLUE_CONCRETE:
            case BROWN_CONCRETE:
            case GREEN_CONCRETE:
            case RED_CONCRETE:
            case BLACK_CONCRETE:
            case WHITE_CONCRETE_POWDER:
            case ORANGE_CONCRETE_POWDER:
            case MAGENTA_CONCRETE_POWDER:
            case LIGHT_BLUE_CONCRETE_POWDER:
            case YELLOW_CONCRETE_POWDER:
            case LIME_CONCRETE_POWDER:
            case PINK_CONCRETE_POWDER:
            case GRAY_CONCRETE_POWDER:
            case LIGHT_GRAY_CONCRETE_POWDER:
            case CYAN_CONCRETE_POWDER:
            case PURPLE_CONCRETE_POWDER:
            case BLUE_CONCRETE_POWDER:
            case BROWN_CONCRETE_POWDER:
            case GREEN_CONCRETE_POWDER:
            case RED_CONCRETE_POWDER:
            case BLACK_CONCRETE_POWDER:
            case DRIED_KELP_BLOCK:
            case TURTLE_EGG:
            case SNIFFER_EGG:
            case DEAD_TUBE_CORAL_BLOCK:
            case DEAD_BRAIN_CORAL_BLOCK:
            case DEAD_BUBBLE_CORAL_BLOCK:
            case DEAD_FIRE_CORAL_BLOCK:
            case DEAD_HORN_CORAL_BLOCK:
            case TUBE_CORAL_BLOCK:
            case BRAIN_CORAL_BLOCK:
            case BUBBLE_CORAL_BLOCK:
            case FIRE_CORAL_BLOCK:
            case HORN_CORAL_BLOCK:
            case DEAD_TUBE_CORAL:
            case DEAD_BRAIN_CORAL:
            case DEAD_BUBBLE_CORAL:
            case DEAD_FIRE_CORAL:
            case DEAD_HORN_CORAL:
            case DEAD_TUBE_CORAL_FAN:
            case DEAD_BRAIN_CORAL_FAN:
            case DEAD_BUBBLE_CORAL_FAN:
            case DEAD_FIRE_CORAL_FAN:
            case DEAD_HORN_CORAL_FAN:
            case DEAD_TUBE_CORAL_WALL_FAN:
            case DEAD_BRAIN_CORAL_WALL_FAN:
            case DEAD_BUBBLE_CORAL_WALL_FAN:
            case DEAD_FIRE_CORAL_WALL_FAN:
            case DEAD_HORN_CORAL_WALL_FAN:
            case BLUE_ICE:
            case CONDUIT:
            case BAMBOO:
            case POLISHED_GRANITE_STAIRS:
            case SMOOTH_RED_SANDSTONE_STAIRS:
            case MOSSY_STONE_BRICK_STAIRS:
            case POLISHED_DIORITE_STAIRS:
            case MOSSY_COBBLESTONE_STAIRS:
            case END_STONE_BRICK_STAIRS:
            case STONE_STAIRS:
            case SMOOTH_SANDSTONE_STAIRS:
            case SMOOTH_QUARTZ_STAIRS:
            case GRANITE_STAIRS:
            case ANDESITE_STAIRS:
            case RED_NETHER_BRICK_STAIRS:
            case POLISHED_ANDESITE_STAIRS:
            case DIORITE_STAIRS:
            case POLISHED_GRANITE_SLAB:
            case SMOOTH_RED_SANDSTONE_SLAB:
            case MOSSY_STONE_BRICK_SLAB:
            case POLISHED_DIORITE_SLAB:
            case MOSSY_COBBLESTONE_SLAB:
            case END_STONE_BRICK_SLAB:
            case SMOOTH_SANDSTONE_SLAB:
            case SMOOTH_QUARTZ_SLAB:
            case GRANITE_SLAB:
            case ANDESITE_SLAB:
            case RED_NETHER_BRICK_SLAB:
            case POLISHED_ANDESITE_SLAB:
            case DIORITE_SLAB:
            case BRICK_WALL:
            case PRISMARINE_WALL:
            case RED_SANDSTONE_WALL:
            case MOSSY_STONE_BRICK_WALL:
            case GRANITE_WALL:
            case STONE_BRICK_WALL:
            case MUD_BRICK_WALL:
            case NETHER_BRICK_WALL:
            case ANDESITE_WALL:
            case RED_NETHER_BRICK_WALL:
            case SANDSTONE_WALL:
            case END_STONE_BRICK_WALL:
            case DIORITE_WALL:
            case LOOM:
            case BARREL:
            case SMOKER:
            case BLAST_FURNACE:
            case CARTOGRAPHY_TABLE:
            case FLETCHING_TABLE:
            case GRINDSTONE:
            case LECTERN:
            case SMITHING_TABLE:
            case STONECUTTER:
            case BELL:
            case LANTERN:
            case SOUL_LANTERN:
            case CAMPFIRE:
            case SOUL_CAMPFIRE:
            case WARPED_STEM:
            case STRIPPED_WARPED_STEM:
            case WARPED_HYPHAE:
            case STRIPPED_WARPED_HYPHAE:
            case WARPED_NYLIUM:
            case WARPED_WART_BLOCK:
            case CRIMSON_STEM:
            case STRIPPED_CRIMSON_STEM:
            case CRIMSON_HYPHAE:
            case STRIPPED_CRIMSON_HYPHAE:
            case CRIMSON_NYLIUM:
            case SHROOMLIGHT:
            case CRIMSON_PLANKS:
            case WARPED_PLANKS:
            case CRIMSON_SLAB:
            case WARPED_SLAB:
            case CRIMSON_PRESSURE_PLATE:
            case WARPED_PRESSURE_PLATE:
            case CRIMSON_FENCE:
            case WARPED_FENCE:
            case CRIMSON_TRAPDOOR:
            case WARPED_TRAPDOOR:
            case CRIMSON_FENCE_GATE:
            case WARPED_FENCE_GATE:
            case CRIMSON_STAIRS:
            case WARPED_STAIRS:
            case CRIMSON_DOOR:
            case WARPED_DOOR:
            case CRIMSON_SIGN:
            case WARPED_SIGN:
            case CRIMSON_WALL_SIGN:
            case WARPED_WALL_SIGN:
            case STRUCTURE_BLOCK:
            case JIGSAW:
            case COMPOSTER:
            case TARGET:
            case BEE_NEST:
            case BEEHIVE:
            case HONEY_BLOCK:
            case HONEYCOMB_BLOCK:
            case NETHERITE_BLOCK:
            case ANCIENT_DEBRIS:
            case CRYING_OBSIDIAN:
            case RESPAWN_ANCHOR:
            case LODESTONE:
            case BLACKSTONE:
            case BLACKSTONE_STAIRS:
            case BLACKSTONE_WALL:
            case BLACKSTONE_SLAB:
            case POLISHED_BLACKSTONE:
            case POLISHED_BLACKSTONE_BRICKS:
            case CRACKED_POLISHED_BLACKSTONE_BRICKS:
            case CHISELED_POLISHED_BLACKSTONE:
            case POLISHED_BLACKSTONE_BRICK_SLAB:
            case POLISHED_BLACKSTONE_BRICK_STAIRS:
            case POLISHED_BLACKSTONE_BRICK_WALL:
            case GILDED_BLACKSTONE:
            case POLISHED_BLACKSTONE_STAIRS:
            case POLISHED_BLACKSTONE_SLAB:
            case POLISHED_BLACKSTONE_PRESSURE_PLATE:
            case POLISHED_BLACKSTONE_WALL:
            case CHISELED_NETHER_BRICKS:
            case CRACKED_NETHER_BRICKS:
            case QUARTZ_BRICKS:
            case CANDLE_CAKE:
            case WHITE_CANDLE_CAKE:
            case ORANGE_CANDLE_CAKE:
            case MAGENTA_CANDLE_CAKE:
            case LIGHT_BLUE_CANDLE_CAKE:
            case YELLOW_CANDLE_CAKE:
            case LIME_CANDLE_CAKE:
            case PINK_CANDLE_CAKE:
            case GRAY_CANDLE_CAKE:
            case LIGHT_GRAY_CANDLE_CAKE:
            case CYAN_CANDLE_CAKE:
            case PURPLE_CANDLE_CAKE:
            case BLUE_CANDLE_CAKE:
            case BROWN_CANDLE_CAKE:
            case GREEN_CANDLE_CAKE:
            case RED_CANDLE_CAKE:
            case BLACK_CANDLE_CAKE:
            case AMETHYST_BLOCK:
            case BUDDING_AMETHYST:
            case AMETHYST_CLUSTER:
            case LARGE_AMETHYST_BUD:
            case MEDIUM_AMETHYST_BUD:
            case SMALL_AMETHYST_BUD:
            case TUFF:
            case TUFF_SLAB:
            case TUFF_STAIRS:
            case TUFF_WALL:
            case POLISHED_TUFF:
            case POLISHED_TUFF_SLAB:
            case POLISHED_TUFF_STAIRS:
            case POLISHED_TUFF_WALL:
            case CHISELED_TUFF:
            case TUFF_BRICKS:
            case TUFF_BRICK_SLAB:
            case TUFF_BRICK_STAIRS:
            case TUFF_BRICK_WALL:
            case CHISELED_TUFF_BRICKS:
            case CALCITE:
            case TINTED_GLASS:
            case SCULK_SENSOR:
            case CALIBRATED_SCULK_SENSOR:
            case SCULK:
            case SCULK_VEIN:
            case SCULK_CATALYST:
            case SCULK_SHRIEKER:
            case COPPER_BLOCK:
            case EXPOSED_COPPER:
            case WEATHERED_COPPER:
            case OXIDIZED_COPPER:
            case COPPER_ORE:
            case DEEPSLATE_COPPER_ORE:
            case OXIDIZED_CUT_COPPER:
            case WEATHERED_CUT_COPPER:
            case EXPOSED_CUT_COPPER:
            case CUT_COPPER:
            case OXIDIZED_CHISELED_COPPER:
            case WEATHERED_CHISELED_COPPER:
            case EXPOSED_CHISELED_COPPER:
            case CHISELED_COPPER:
            case WAXED_OXIDIZED_CHISELED_COPPER:
            case WAXED_WEATHERED_CHISELED_COPPER:
            case WAXED_EXPOSED_CHISELED_COPPER:
            case WAXED_CHISELED_COPPER:
            case OXIDIZED_CUT_COPPER_STAIRS:
            case WEATHERED_CUT_COPPER_STAIRS:
            case EXPOSED_CUT_COPPER_STAIRS:
            case CUT_COPPER_STAIRS:
            case OXIDIZED_CUT_COPPER_SLAB:
            case WEATHERED_CUT_COPPER_SLAB:
            case EXPOSED_CUT_COPPER_SLAB:
            case CUT_COPPER_SLAB:
            case WAXED_COPPER_BLOCK:
            case WAXED_WEATHERED_COPPER:
            case WAXED_EXPOSED_COPPER:
            case WAXED_OXIDIZED_COPPER:
            case WAXED_OXIDIZED_CUT_COPPER:
            case WAXED_WEATHERED_CUT_COPPER:
            case WAXED_EXPOSED_CUT_COPPER:
            case WAXED_CUT_COPPER:
            case WAXED_OXIDIZED_CUT_COPPER_STAIRS:
            case WAXED_WEATHERED_CUT_COPPER_STAIRS:
            case WAXED_EXPOSED_CUT_COPPER_STAIRS:
            case WAXED_CUT_COPPER_STAIRS:
            case WAXED_OXIDIZED_CUT_COPPER_SLAB:
            case WAXED_WEATHERED_CUT_COPPER_SLAB:
            case WAXED_EXPOSED_CUT_COPPER_SLAB:
            case WAXED_CUT_COPPER_SLAB:
            case COPPER_DOOR:
            case EXPOSED_COPPER_DOOR:
            case OXIDIZED_COPPER_DOOR:
            case WEATHERED_COPPER_DOOR:
            case WAXED_COPPER_DOOR:
            case WAXED_EXPOSED_COPPER_DOOR:
            case WAXED_OXIDIZED_COPPER_DOOR:
            case WAXED_WEATHERED_COPPER_DOOR:
            case COPPER_TRAPDOOR:
            case EXPOSED_COPPER_TRAPDOOR:
            case OXIDIZED_COPPER_TRAPDOOR:
            case WEATHERED_COPPER_TRAPDOOR:
            case WAXED_COPPER_TRAPDOOR:
            case WAXED_EXPOSED_COPPER_TRAPDOOR:
            case WAXED_OXIDIZED_COPPER_TRAPDOOR:
            case WAXED_WEATHERED_COPPER_TRAPDOOR:
            case COPPER_GRATE:
            case EXPOSED_COPPER_GRATE:
            case WEATHERED_COPPER_GRATE:
            case OXIDIZED_COPPER_GRATE:
            case WAXED_COPPER_GRATE:
            case WAXED_EXPOSED_COPPER_GRATE:
            case WAXED_WEATHERED_COPPER_GRATE:
            case WAXED_OXIDIZED_COPPER_GRATE:
            case COPPER_BULB:
            case EXPOSED_COPPER_BULB:
            case WEATHERED_COPPER_BULB:
            case OXIDIZED_COPPER_BULB:
            case WAXED_COPPER_BULB:
            case WAXED_EXPOSED_COPPER_BULB:
            case WAXED_WEATHERED_COPPER_BULB:
            case WAXED_OXIDIZED_COPPER_BULB:
            case LIGHTNING_ROD:
            case POINTED_DRIPSTONE:
            case DRIPSTONE_BLOCK:
            case MOSS_BLOCK:
            case ROOTED_DIRT:
            case MUD:
            case DEEPSLATE:
            case COBBLED_DEEPSLATE:
            case COBBLED_DEEPSLATE_STAIRS:
            case COBBLED_DEEPSLATE_SLAB:
            case COBBLED_DEEPSLATE_WALL:
            case POLISHED_DEEPSLATE:
            case POLISHED_DEEPSLATE_STAIRS:
            case POLISHED_DEEPSLATE_SLAB:
            case POLISHED_DEEPSLATE_WALL:
            case DEEPSLATE_TILES:
            case DEEPSLATE_TILE_STAIRS:
            case DEEPSLATE_TILE_SLAB:
            case DEEPSLATE_TILE_WALL:
            case DEEPSLATE_BRICKS:
            case DEEPSLATE_BRICK_STAIRS:
            case DEEPSLATE_BRICK_SLAB:
            case DEEPSLATE_BRICK_WALL:
            case CHISELED_DEEPSLATE:
            case CRACKED_DEEPSLATE_BRICKS:
            case CRACKED_DEEPSLATE_TILES:
            case INFESTED_DEEPSLATE:
            case SMOOTH_BASALT:
            case RAW_IRON_BLOCK:
            case RAW_COPPER_BLOCK:
            case RAW_GOLD_BLOCK:
            case OCHRE_FROGLIGHT:
            case VERDANT_FROGLIGHT:
            case PEARLESCENT_FROGLIGHT:
            case REINFORCED_DEEPSLATE:
            case DECORATED_POT:
            case CRAFTER:
            case TRIAL_SPAWNER:
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
            case VOID_AIR:
            case CAVE_AIR:
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
            case OAK_PLANKS:
            case SPRUCE_PLANKS:
            case BIRCH_PLANKS:
            case JUNGLE_PLANKS:
            case ACACIA_PLANKS:
            case CHERRY_PLANKS:
            case DARK_OAK_PLANKS:
            case MANGROVE_PLANKS:
            case BAMBOO_PLANKS:
            case BAMBOO_MOSAIC:
            case OAK_LOG:
            case SPRUCE_LOG:
            case BIRCH_LOG:
            case JUNGLE_LOG:
            case ACACIA_LOG:
            case CHERRY_LOG:
            case DARK_OAK_LOG:
            case MANGROVE_LOG:
            case MANGROVE_ROOTS:
            case BAMBOO_BLOCK:
            case STRIPPED_SPRUCE_LOG:
            case STRIPPED_BIRCH_LOG:
            case STRIPPED_JUNGLE_LOG:
            case STRIPPED_ACACIA_LOG:
            case STRIPPED_CHERRY_LOG:
            case STRIPPED_DARK_OAK_LOG:
            case STRIPPED_OAK_LOG:
            case STRIPPED_MANGROVE_LOG:
            case STRIPPED_BAMBOO_BLOCK:
            case OAK_WOOD:
            case SPRUCE_WOOD:
            case BIRCH_WOOD:
            case JUNGLE_WOOD:
            case ACACIA_WOOD:
            case CHERRY_WOOD:
            case DARK_OAK_WOOD:
            case MANGROVE_WOOD:
            case STRIPPED_OAK_WOOD:
            case STRIPPED_SPRUCE_WOOD:
            case STRIPPED_BIRCH_WOOD:
            case STRIPPED_JUNGLE_WOOD:
            case STRIPPED_ACACIA_WOOD:
            case STRIPPED_CHERRY_WOOD:
            case STRIPPED_DARK_OAK_WOOD:
            case STRIPPED_MANGROVE_WOOD:
            case OAK_LEAVES:
            case SPRUCE_LEAVES:
            case BIRCH_LEAVES:
            case JUNGLE_LEAVES:
            case ACACIA_LEAVES:
            case CHERRY_LEAVES:
            case DARK_OAK_LEAVES:
            case MANGROVE_LEAVES:
            case AZALEA_LEAVES:
            case FLOWERING_AZALEA_LEAVES:
            case NOTE_BLOCK:
            case WHITE_BED:
            case ORANGE_BED:
            case MAGENTA_BED:
            case LIGHT_BLUE_BED:
            case YELLOW_BED:
            case LIME_BED:
            case PINK_BED:
            case GRAY_BED:
            case LIGHT_GRAY_BED:
            case CYAN_BED:
            case PURPLE_BED:
            case BLUE_BED:
            case BROWN_BED:
            case GREEN_BED:
            case RED_BED:
            case BLACK_BED:
            case SHORT_GRASS:
            case FERN:
            case DEAD_BUSH:
            case WHITE_WOOL:
            case ORANGE_WOOL:
            case MAGENTA_WOOL:
            case LIGHT_BLUE_WOOL:
            case YELLOW_WOOL:
            case LIME_WOOL:
            case PINK_WOOL:
            case GRAY_WOOL:
            case LIGHT_GRAY_WOOL:
            case CYAN_WOOL:
            case PURPLE_WOOL:
            case BLUE_WOOL:
            case BROWN_WOOL:
            case GREEN_WOOL:
            case RED_WOOL:
            case BLACK_WOOL:
            case TNT:
            case BOOKSHELF:
            case CHISELED_BOOKSHELF:
            case OAK_STAIRS:
            case CHEST:
            case CRAFTING_TABLE:
            case OAK_SIGN:
            case SPRUCE_SIGN:
            case BIRCH_SIGN:
            case ACACIA_SIGN:
            case CHERRY_SIGN:
            case JUNGLE_SIGN:
            case DARK_OAK_SIGN:
            case MANGROVE_SIGN:
            case BAMBOO_SIGN:
            case OAK_DOOR:
            case OAK_WALL_SIGN:
            case SPRUCE_WALL_SIGN:
            case BIRCH_WALL_SIGN:
            case ACACIA_WALL_SIGN:
            case CHERRY_WALL_SIGN:
            case JUNGLE_WALL_SIGN:
            case DARK_OAK_WALL_SIGN:
            case MANGROVE_WALL_SIGN:
            case BAMBOO_WALL_SIGN:
            case OAK_HANGING_SIGN:
            case SPRUCE_HANGING_SIGN:
            case BIRCH_HANGING_SIGN:
            case ACACIA_HANGING_SIGN:
            case CHERRY_HANGING_SIGN:
            case JUNGLE_HANGING_SIGN:
            case DARK_OAK_HANGING_SIGN:
            case MANGROVE_HANGING_SIGN:
            case BAMBOO_HANGING_SIGN:
            case OAK_WALL_HANGING_SIGN:
            case SPRUCE_WALL_HANGING_SIGN:
            case BIRCH_WALL_HANGING_SIGN:
            case ACACIA_WALL_HANGING_SIGN:
            case CHERRY_WALL_HANGING_SIGN:
            case JUNGLE_WALL_HANGING_SIGN:
            case DARK_OAK_WALL_HANGING_SIGN:
            case MANGROVE_WALL_HANGING_SIGN:
            case BAMBOO_WALL_HANGING_SIGN:
            case OAK_PRESSURE_PLATE:
            case SPRUCE_PRESSURE_PLATE:
            case BIRCH_PRESSURE_PLATE:
            case JUNGLE_PRESSURE_PLATE:
            case ACACIA_PRESSURE_PLATE:
            case CHERRY_PRESSURE_PLATE:
            case DARK_OAK_PRESSURE_PLATE:
            case MANGROVE_PRESSURE_PLATE:
            case BAMBOO_PRESSURE_PLATE:
            case JUKEBOX:
            case OAK_FENCE:
            case OAK_TRAPDOOR:
            case SPRUCE_TRAPDOOR:
            case BIRCH_TRAPDOOR:
            case JUNGLE_TRAPDOOR:
            case ACACIA_TRAPDOOR:
            case CHERRY_TRAPDOOR:
            case DARK_OAK_TRAPDOOR:
            case MANGROVE_TRAPDOOR:
            case BAMBOO_TRAPDOOR:
            case BROWN_MUSHROOM_BLOCK:
            case RED_MUSHROOM_BLOCK:
            case MUSHROOM_STEM:
            case VINE:
            case GLOW_LICHEN:
            case OAK_FENCE_GATE:
            case SPRUCE_STAIRS:
            case BIRCH_STAIRS:
            case JUNGLE_STAIRS:
            case TRAPPED_CHEST:
            case DAYLIGHT_DETECTOR:
            case ACACIA_STAIRS:
            case CHERRY_STAIRS:
            case DARK_OAK_STAIRS:
            case MANGROVE_STAIRS:
            case BAMBOO_STAIRS:
            case BAMBOO_MOSAIC_STAIRS:
            case WHITE_CARPET:
            case ORANGE_CARPET:
            case MAGENTA_CARPET:
            case LIGHT_BLUE_CARPET:
            case YELLOW_CARPET:
            case LIME_CARPET:
            case PINK_CARPET:
            case GRAY_CARPET:
            case LIGHT_GRAY_CARPET:
            case CYAN_CARPET:
            case PURPLE_CARPET:
            case BLUE_CARPET:
            case BROWN_CARPET:
            case GREEN_CARPET:
            case RED_CARPET:
            case BLACK_CARPET:
            case SUNFLOWER:
            case LILAC:
            case ROSE_BUSH:
            case PEONY:
            case TALL_GRASS:
            case LARGE_FERN:
            case WHITE_BANNER:
            case ORANGE_BANNER:
            case MAGENTA_BANNER:
            case LIGHT_BLUE_BANNER:
            case YELLOW_BANNER:
            case LIME_BANNER:
            case PINK_BANNER:
            case GRAY_BANNER:
            case LIGHT_GRAY_BANNER:
            case CYAN_BANNER:
            case PURPLE_BANNER:
            case BLUE_BANNER:
            case BROWN_BANNER:
            case GREEN_BANNER:
            case RED_BANNER:
            case BLACK_BANNER:
            case WHITE_WALL_BANNER:
            case ORANGE_WALL_BANNER:
            case MAGENTA_WALL_BANNER:
            case LIGHT_BLUE_WALL_BANNER:
            case YELLOW_WALL_BANNER:
            case LIME_WALL_BANNER:
            case PINK_WALL_BANNER:
            case GRAY_WALL_BANNER:
            case LIGHT_GRAY_WALL_BANNER:
            case CYAN_WALL_BANNER:
            case PURPLE_WALL_BANNER:
            case BLUE_WALL_BANNER:
            case BROWN_WALL_BANNER:
            case GREEN_WALL_BANNER:
            case RED_WALL_BANNER:
            case BLACK_WALL_BANNER:
            case OAK_SLAB:
            case SPRUCE_SLAB:
            case BIRCH_SLAB:
            case JUNGLE_SLAB:
            case ACACIA_SLAB:
            case CHERRY_SLAB:
            case DARK_OAK_SLAB:
            case MANGROVE_SLAB:
            case BAMBOO_SLAB:
            case BAMBOO_MOSAIC_SLAB:
            case SPRUCE_FENCE_GATE:
            case BIRCH_FENCE_GATE:
            case JUNGLE_FENCE_GATE:
            case ACACIA_FENCE_GATE:
            case CHERRY_FENCE_GATE:
            case DARK_OAK_FENCE_GATE:
            case MANGROVE_FENCE_GATE:
            case BAMBOO_FENCE_GATE:
            case SPRUCE_FENCE:
            case BIRCH_FENCE:
            case JUNGLE_FENCE:
            case ACACIA_FENCE:
            case CHERRY_FENCE:
            case DARK_OAK_FENCE:
            case MANGROVE_FENCE:
            case BAMBOO_FENCE:
            case SPRUCE_DOOR:
            case BIRCH_DOOR:
            case JUNGLE_DOOR:
            case ACACIA_DOOR:
            case CHERRY_DOOR:
            case DARK_OAK_DOOR:
            case MANGROVE_DOOR:
            case BAMBOO_DOOR:
            case PITCHER_PLANT:
            case BAMBOO_SAPLING:
            case BAMBOO:
            case LOOM:
            case BARREL:
            case CARTOGRAPHY_TABLE:
            case FLETCHING_TABLE:
            case LECTERN:
            case SMITHING_TABLE:
            case CAMPFIRE:
            case SOUL_CAMPFIRE:
            case COMPOSTER:
            case BEE_NEST:
            case BEEHIVE:
            case HANGING_ROOTS:
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
            case OAK_PLANKS:
            case SPRUCE_PLANKS:
            case BIRCH_PLANKS:
            case JUNGLE_PLANKS:
            case ACACIA_PLANKS:
            case CHERRY_PLANKS:
            case DARK_OAK_PLANKS:
            case MANGROVE_PLANKS:
            case BAMBOO_PLANKS:
            case BAMBOO_MOSAIC:
            case OAK_LOG:
            case SPRUCE_LOG:
            case BIRCH_LOG:
            case JUNGLE_LOG:
            case ACACIA_LOG:
            case CHERRY_LOG:
            case DARK_OAK_LOG:
            case MANGROVE_LOG:
            case MANGROVE_ROOTS:
            case BAMBOO_BLOCK:
            case STRIPPED_SPRUCE_LOG:
            case STRIPPED_BIRCH_LOG:
            case STRIPPED_JUNGLE_LOG:
            case STRIPPED_ACACIA_LOG:
            case STRIPPED_CHERRY_LOG:
            case STRIPPED_DARK_OAK_LOG:
            case STRIPPED_OAK_LOG:
            case STRIPPED_MANGROVE_LOG:
            case STRIPPED_BAMBOO_BLOCK:
            case OAK_WOOD:
            case SPRUCE_WOOD:
            case BIRCH_WOOD:
            case JUNGLE_WOOD:
            case ACACIA_WOOD:
            case CHERRY_WOOD:
            case DARK_OAK_WOOD:
            case MANGROVE_WOOD:
            case STRIPPED_OAK_WOOD:
            case STRIPPED_SPRUCE_WOOD:
            case STRIPPED_BIRCH_WOOD:
            case STRIPPED_JUNGLE_WOOD:
            case STRIPPED_ACACIA_WOOD:
            case STRIPPED_CHERRY_WOOD:
            case STRIPPED_DARK_OAK_WOOD:
            case STRIPPED_MANGROVE_WOOD:
            case OAK_LEAVES:
            case SPRUCE_LEAVES:
            case BIRCH_LEAVES:
            case JUNGLE_LEAVES:
            case ACACIA_LEAVES:
            case CHERRY_LEAVES:
            case DARK_OAK_LEAVES:
            case MANGROVE_LEAVES:
            case AZALEA_LEAVES:
            case FLOWERING_AZALEA_LEAVES:
            case SHORT_GRASS:
            case FERN:
            case DEAD_BUSH:
            case WHITE_WOOL:
            case ORANGE_WOOL:
            case MAGENTA_WOOL:
            case LIGHT_BLUE_WOOL:
            case YELLOW_WOOL:
            case LIME_WOOL:
            case PINK_WOOL:
            case GRAY_WOOL:
            case LIGHT_GRAY_WOOL:
            case CYAN_WOOL:
            case PURPLE_WOOL:
            case BLUE_WOOL:
            case BROWN_WOOL:
            case GREEN_WOOL:
            case RED_WOOL:
            case BLACK_WOOL:
            case DANDELION:
            case TORCHFLOWER:
            case POPPY:
            case BLUE_ORCHID:
            case ALLIUM:
            case AZURE_BLUET:
            case RED_TULIP:
            case ORANGE_TULIP:
            case WHITE_TULIP:
            case PINK_TULIP:
            case OXEYE_DAISY:
            case CORNFLOWER:
            case WITHER_ROSE:
            case LILY_OF_THE_VALLEY:
            case TNT:
            case BOOKSHELF:
            case OAK_STAIRS:
            case OAK_FENCE:
            case VINE:
            case GLOW_LICHEN:
            case OAK_FENCE_GATE:
            case SPRUCE_STAIRS:
            case BIRCH_STAIRS:
            case JUNGLE_STAIRS:
            case ACACIA_STAIRS:
            case CHERRY_STAIRS:
            case DARK_OAK_STAIRS:
            case MANGROVE_STAIRS:
            case BAMBOO_STAIRS:
            case BAMBOO_MOSAIC_STAIRS:
            case HAY_BLOCK:
            case WHITE_CARPET:
            case ORANGE_CARPET:
            case MAGENTA_CARPET:
            case LIGHT_BLUE_CARPET:
            case YELLOW_CARPET:
            case LIME_CARPET:
            case PINK_CARPET:
            case GRAY_CARPET:
            case LIGHT_GRAY_CARPET:
            case CYAN_CARPET:
            case PURPLE_CARPET:
            case BLUE_CARPET:
            case BROWN_CARPET:
            case GREEN_CARPET:
            case RED_CARPET:
            case BLACK_CARPET:
            case COAL_BLOCK:
            case SUNFLOWER:
            case LILAC:
            case ROSE_BUSH:
            case PEONY:
            case TALL_GRASS:
            case LARGE_FERN:
            case OAK_SLAB:
            case SPRUCE_SLAB:
            case BIRCH_SLAB:
            case JUNGLE_SLAB:
            case ACACIA_SLAB:
            case CHERRY_SLAB:
            case DARK_OAK_SLAB:
            case MANGROVE_SLAB:
            case BAMBOO_SLAB:
            case BAMBOO_MOSAIC_SLAB:
            case SPRUCE_FENCE_GATE:
            case BIRCH_FENCE_GATE:
            case JUNGLE_FENCE_GATE:
            case ACACIA_FENCE_GATE:
            case CHERRY_FENCE_GATE:
            case DARK_OAK_FENCE_GATE:
            case MANGROVE_FENCE_GATE:
            case BAMBOO_FENCE_GATE:
            case SPRUCE_FENCE:
            case BIRCH_FENCE:
            case JUNGLE_FENCE:
            case ACACIA_FENCE:
            case CHERRY_FENCE:
            case DARK_OAK_FENCE:
            case MANGROVE_FENCE:
            case BAMBOO_FENCE:
            case PITCHER_PLANT:
            case DRIED_KELP_BLOCK:
            case BAMBOO:
            case SCAFFOLDING:
            case LECTERN:
            case SWEET_BERRY_BUSH:
            case COMPOSTER:
            case TARGET:
            case BEE_NEST:
            case BEEHIVE:
            case CAVE_VINES:
            case CAVE_VINES_PLANT:
            case SPORE_BLOSSOM:
            case AZALEA:
            case FLOWERING_AZALEA:
            case PINK_PETALS:
            case BIG_DRIPLEAF:
            case BIG_DRIPLEAF_STEM:
            case SMALL_DRIPLEAF:
            case HANGING_ROOTS:
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
            case OAK_PLANKS:
            case SPRUCE_PLANKS:
            case BIRCH_PLANKS:
            case JUNGLE_PLANKS:
            case ACACIA_PLANKS:
            case CHERRY_PLANKS:
            case DARK_OAK_PLANKS:
            case MANGROVE_PLANKS:
            case BAMBOO_PLANKS:
            case BAMBOO_MOSAIC:
            case OAK_SAPLING:
            case SPRUCE_SAPLING:
            case BIRCH_SAPLING:
            case JUNGLE_SAPLING:
            case ACACIA_SAPLING:
            case CHERRY_SAPLING:
            case DARK_OAK_SAPLING:
            case MANGROVE_PROPAGULE:
            case COAL_BLOCK:
            case OAK_LOG:
            case SPRUCE_LOG:
            case BIRCH_LOG:
            case JUNGLE_LOG:
            case ACACIA_LOG:
            case CHERRY_LOG:
            case DARK_OAK_LOG:
            case MANGROVE_LOG:
            case MANGROVE_ROOTS:
            case BAMBOO_BLOCK:
            case STRIPPED_OAK_LOG:
            case STRIPPED_SPRUCE_LOG:
            case STRIPPED_BIRCH_LOG:
            case STRIPPED_JUNGLE_LOG:
            case STRIPPED_ACACIA_LOG:
            case STRIPPED_CHERRY_LOG:
            case STRIPPED_DARK_OAK_LOG:
            case STRIPPED_MANGROVE_LOG:
            case STRIPPED_OAK_WOOD:
            case STRIPPED_SPRUCE_WOOD:
            case STRIPPED_BIRCH_WOOD:
            case STRIPPED_JUNGLE_WOOD:
            case STRIPPED_ACACIA_WOOD:
            case STRIPPED_CHERRY_WOOD:
            case STRIPPED_DARK_OAK_WOOD:
            case STRIPPED_MANGROVE_WOOD:
            case STRIPPED_BAMBOO_BLOCK:
            case OAK_WOOD:
            case SPRUCE_WOOD:
            case BIRCH_WOOD:
            case JUNGLE_WOOD:
            case ACACIA_WOOD:
            case CHERRY_WOOD:
            case DARK_OAK_WOOD:
            case MANGROVE_WOOD:
            case AZALEA:
            case FLOWERING_AZALEA:
            case DEAD_BUSH:
            case WHITE_WOOL:
            case ORANGE_WOOL:
            case MAGENTA_WOOL:
            case LIGHT_BLUE_WOOL:
            case YELLOW_WOOL:
            case LIME_WOOL:
            case PINK_WOOL:
            case GRAY_WOOL:
            case LIGHT_GRAY_WOOL:
            case CYAN_WOOL:
            case PURPLE_WOOL:
            case BLUE_WOOL:
            case BROWN_WOOL:
            case GREEN_WOOL:
            case RED_WOOL:
            case BLACK_WOOL:
            case BAMBOO:
            case OAK_SLAB:
            case SPRUCE_SLAB:
            case BIRCH_SLAB:
            case JUNGLE_SLAB:
            case ACACIA_SLAB:
            case CHERRY_SLAB:
            case DARK_OAK_SLAB:
            case MANGROVE_SLAB:
            case BAMBOO_SLAB:
            case BAMBOO_MOSAIC_SLAB:
            case BOOKSHELF:
            case CHISELED_BOOKSHELF:
            case CHEST:
            case CRAFTING_TABLE:
            case LADDER:
            case JUKEBOX:
            case OAK_FENCE:
            case SPRUCE_FENCE:
            case BIRCH_FENCE:
            case JUNGLE_FENCE:
            case ACACIA_FENCE:
            case CHERRY_FENCE:
            case DARK_OAK_FENCE:
            case MANGROVE_FENCE:
            case BAMBOO_FENCE:
            case OAK_STAIRS:
            case SPRUCE_STAIRS:
            case BIRCH_STAIRS:
            case JUNGLE_STAIRS:
            case ACACIA_STAIRS:
            case CHERRY_STAIRS:
            case DARK_OAK_STAIRS:
            case MANGROVE_STAIRS:
            case BAMBOO_STAIRS:
            case BAMBOO_MOSAIC_STAIRS:
            case WHITE_CARPET:
            case ORANGE_CARPET:
            case MAGENTA_CARPET:
            case LIGHT_BLUE_CARPET:
            case YELLOW_CARPET:
            case LIME_CARPET:
            case PINK_CARPET:
            case GRAY_CARPET:
            case LIGHT_GRAY_CARPET:
            case CYAN_CARPET:
            case PURPLE_CARPET:
            case BLUE_CARPET:
            case BROWN_CARPET:
            case GREEN_CARPET:
            case RED_CARPET:
            case BLACK_CARPET:
            case SCAFFOLDING:
            case LECTERN:
            case DAYLIGHT_DETECTOR:
            case TRAPPED_CHEST:
            case NOTE_BLOCK:
            case OAK_BUTTON:
            case SPRUCE_BUTTON:
            case BIRCH_BUTTON:
            case JUNGLE_BUTTON:
            case ACACIA_BUTTON:
            case CHERRY_BUTTON:
            case DARK_OAK_BUTTON:
            case MANGROVE_BUTTON:
            case BAMBOO_BUTTON:
            case OAK_PRESSURE_PLATE:
            case SPRUCE_PRESSURE_PLATE:
            case BIRCH_PRESSURE_PLATE:
            case JUNGLE_PRESSURE_PLATE:
            case ACACIA_PRESSURE_PLATE:
            case CHERRY_PRESSURE_PLATE:
            case DARK_OAK_PRESSURE_PLATE:
            case MANGROVE_PRESSURE_PLATE:
            case BAMBOO_PRESSURE_PLATE:
            case OAK_DOOR:
            case SPRUCE_DOOR:
            case BIRCH_DOOR:
            case JUNGLE_DOOR:
            case ACACIA_DOOR:
            case CHERRY_DOOR:
            case DARK_OAK_DOOR:
            case MANGROVE_DOOR:
            case BAMBOO_DOOR:
            case OAK_TRAPDOOR:
            case SPRUCE_TRAPDOOR:
            case BIRCH_TRAPDOOR:
            case JUNGLE_TRAPDOOR:
            case ACACIA_TRAPDOOR:
            case CHERRY_TRAPDOOR:
            case DARK_OAK_TRAPDOOR:
            case MANGROVE_TRAPDOOR:
            case BAMBOO_TRAPDOOR:
            case OAK_FENCE_GATE:
            case SPRUCE_FENCE_GATE:
            case BIRCH_FENCE_GATE:
            case JUNGLE_FENCE_GATE:
            case ACACIA_FENCE_GATE:
            case CHERRY_FENCE_GATE:
            case DARK_OAK_FENCE_GATE:
            case MANGROVE_FENCE_GATE:
            case BAMBOO_FENCE_GATE:
            case OAK_BOAT:
            case OAK_CHEST_BOAT:
            case SPRUCE_BOAT:
            case SPRUCE_CHEST_BOAT:
            case BIRCH_BOAT:
            case BIRCH_CHEST_BOAT:
            case JUNGLE_BOAT:
            case JUNGLE_CHEST_BOAT:
            case ACACIA_BOAT:
            case ACACIA_CHEST_BOAT:
            case CHERRY_BOAT:
            case CHERRY_CHEST_BOAT:
            case DARK_OAK_BOAT:
            case DARK_OAK_CHEST_BOAT:
            case MANGROVE_BOAT:
            case MANGROVE_CHEST_BOAT:
            case BAMBOO_RAFT:
            case BAMBOO_CHEST_RAFT:
            case BOW:
            case COAL:
            case CHARCOAL:
            case WOODEN_SWORD:
            case WOODEN_SHOVEL:
            case WOODEN_PICKAXE:
            case WOODEN_AXE:
            case WOODEN_HOE:
            case STICK:
            case BOWL:
            case OAK_SIGN:
            case SPRUCE_SIGN:
            case BIRCH_SIGN:
            case JUNGLE_SIGN:
            case ACACIA_SIGN:
            case CHERRY_SIGN:
            case DARK_OAK_SIGN:
            case MANGROVE_SIGN:
            case BAMBOO_SIGN:
            case OAK_HANGING_SIGN:
            case SPRUCE_HANGING_SIGN:
            case BIRCH_HANGING_SIGN:
            case JUNGLE_HANGING_SIGN:
            case ACACIA_HANGING_SIGN:
            case CHERRY_HANGING_SIGN:
            case DARK_OAK_HANGING_SIGN:
            case MANGROVE_HANGING_SIGN:
            case BAMBOO_HANGING_SIGN:
            case LAVA_BUCKET:
            case DRIED_KELP_BLOCK:
            case FISHING_ROD:
            case BLAZE_ROD:
            case WHITE_BANNER:
            case ORANGE_BANNER:
            case MAGENTA_BANNER:
            case LIGHT_BLUE_BANNER:
            case YELLOW_BANNER:
            case LIME_BANNER:
            case PINK_BANNER:
            case GRAY_BANNER:
            case LIGHT_GRAY_BANNER:
            case CYAN_BANNER:
            case PURPLE_BANNER:
            case BLUE_BANNER:
            case BROWN_BANNER:
            case GREEN_BANNER:
            case RED_BANNER:
            case BLACK_BANNER:
            case CROSSBOW:
            case LOOM:
            case COMPOSTER:
            case BARREL:
            case CARTOGRAPHY_TABLE:
            case FLETCHING_TABLE:
            case SMITHING_TABLE:
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
            case STONE:
            case GRANITE:
            case POLISHED_GRANITE:
            case DIORITE:
            case POLISHED_DIORITE:
            case ANDESITE:
            case POLISHED_ANDESITE:
            case GRASS_BLOCK:
            case DIRT:
            case COARSE_DIRT:
            case PODZOL:
            case COBBLESTONE:
            case OAK_PLANKS:
            case SPRUCE_PLANKS:
            case BIRCH_PLANKS:
            case JUNGLE_PLANKS:
            case ACACIA_PLANKS:
            case CHERRY_PLANKS:
            case DARK_OAK_PLANKS:
            case MANGROVE_PLANKS:
            case BAMBOO_PLANKS:
            case BAMBOO_MOSAIC:
            case BEDROCK:
            case SAND:
            case SUSPICIOUS_SAND:
            case RED_SAND:
            case GRAVEL:
            case SUSPICIOUS_GRAVEL:
            case GOLD_ORE:
            case DEEPSLATE_GOLD_ORE:
            case IRON_ORE:
            case DEEPSLATE_IRON_ORE:
            case COAL_ORE:
            case DEEPSLATE_COAL_ORE:
            case NETHER_GOLD_ORE:
            case OAK_LOG:
            case SPRUCE_LOG:
            case BIRCH_LOG:
            case JUNGLE_LOG:
            case ACACIA_LOG:
            case CHERRY_LOG:
            case DARK_OAK_LOG:
            case MANGROVE_LOG:
            case MANGROVE_ROOTS:
            case MUDDY_MANGROVE_ROOTS:
            case BAMBOO_BLOCK:
            case STRIPPED_SPRUCE_LOG:
            case STRIPPED_BIRCH_LOG:
            case STRIPPED_JUNGLE_LOG:
            case STRIPPED_ACACIA_LOG:
            case STRIPPED_CHERRY_LOG:
            case STRIPPED_DARK_OAK_LOG:
            case STRIPPED_OAK_LOG:
            case STRIPPED_MANGROVE_LOG:
            case STRIPPED_BAMBOO_BLOCK:
            case OAK_WOOD:
            case SPRUCE_WOOD:
            case BIRCH_WOOD:
            case JUNGLE_WOOD:
            case ACACIA_WOOD:
            case CHERRY_WOOD:
            case DARK_OAK_WOOD:
            case MANGROVE_WOOD:
            case STRIPPED_OAK_WOOD:
            case STRIPPED_SPRUCE_WOOD:
            case STRIPPED_BIRCH_WOOD:
            case STRIPPED_JUNGLE_WOOD:
            case STRIPPED_ACACIA_WOOD:
            case STRIPPED_CHERRY_WOOD:
            case STRIPPED_DARK_OAK_WOOD:
            case STRIPPED_MANGROVE_WOOD:
            case SPONGE:
            case WET_SPONGE:
            case LAPIS_ORE:
            case DEEPSLATE_LAPIS_ORE:
            case LAPIS_BLOCK:
            case DISPENSER:
            case SANDSTONE:
            case CHISELED_SANDSTONE:
            case CUT_SANDSTONE:
            case NOTE_BLOCK:
            case WHITE_WOOL:
            case ORANGE_WOOL:
            case MAGENTA_WOOL:
            case LIGHT_BLUE_WOOL:
            case YELLOW_WOOL:
            case LIME_WOOL:
            case PINK_WOOL:
            case GRAY_WOOL:
            case LIGHT_GRAY_WOOL:
            case CYAN_WOOL:
            case PURPLE_WOOL:
            case BLUE_WOOL:
            case BROWN_WOOL:
            case GREEN_WOOL:
            case RED_WOOL:
            case BLACK_WOOL:
            case GOLD_BLOCK:
            case IRON_BLOCK:
            case BRICKS:
            case BOOKSHELF:
            case CHISELED_BOOKSHELF:
            case MOSSY_COBBLESTONE:
            case OBSIDIAN:
            case SPAWNER:
            case DIAMOND_ORE:
            case DEEPSLATE_DIAMOND_ORE:
            case DIAMOND_BLOCK:
            case CRAFTING_TABLE:
            case FURNACE:
            case REDSTONE_ORE:
            case DEEPSLATE_REDSTONE_ORE:
            case SNOW_BLOCK:
            case CLAY:
            case JUKEBOX:
            case NETHERRACK:
            case SOUL_SAND:
            case SOUL_SOIL:
            case BASALT:
            case POLISHED_BASALT:
            case CARVED_PUMPKIN:
            case JACK_O_LANTERN:
            case STONE_BRICKS:
            case MOSSY_STONE_BRICKS:
            case CRACKED_STONE_BRICKS:
            case CHISELED_STONE_BRICKS:
            case PACKED_MUD:
            case MUD_BRICKS:
            case INFESTED_STONE:
            case INFESTED_COBBLESTONE:
            case INFESTED_STONE_BRICKS:
            case INFESTED_MOSSY_STONE_BRICKS:
            case INFESTED_CRACKED_STONE_BRICKS:
            case INFESTED_CHISELED_STONE_BRICKS:
            case BROWN_MUSHROOM_BLOCK:
            case RED_MUSHROOM_BLOCK:
            case MUSHROOM_STEM:
            case PUMPKIN:
            case MELON:
            case MYCELIUM:
            case NETHER_BRICKS:
            case END_STONE:
            case REDSTONE_LAMP:
            case EMERALD_ORE:
            case DEEPSLATE_EMERALD_ORE:
            case EMERALD_BLOCK:
            case COMMAND_BLOCK:
            case NETHER_QUARTZ_ORE:
            case QUARTZ_BLOCK:
            case CHISELED_QUARTZ_BLOCK:
            case QUARTZ_PILLAR:
            case DROPPER:
            case WHITE_TERRACOTTA:
            case ORANGE_TERRACOTTA:
            case MAGENTA_TERRACOTTA:
            case LIGHT_BLUE_TERRACOTTA:
            case YELLOW_TERRACOTTA:
            case LIME_TERRACOTTA:
            case PINK_TERRACOTTA:
            case GRAY_TERRACOTTA:
            case LIGHT_GRAY_TERRACOTTA:
            case CYAN_TERRACOTTA:
            case PURPLE_TERRACOTTA:
            case BLUE_TERRACOTTA:
            case BROWN_TERRACOTTA:
            case GREEN_TERRACOTTA:
            case RED_TERRACOTTA:
            case BLACK_TERRACOTTA:
            case SLIME_BLOCK:
            case BARRIER:
            case PRISMARINE:
            case PRISMARINE_BRICKS:
            case DARK_PRISMARINE:
            case HAY_BLOCK:
            case TERRACOTTA:
            case COAL_BLOCK:
            case PACKED_ICE:
            case RED_SANDSTONE:
            case CHISELED_RED_SANDSTONE:
            case CUT_RED_SANDSTONE:
            case SMOOTH_STONE:
            case SMOOTH_SANDSTONE:
            case SMOOTH_QUARTZ:
            case SMOOTH_RED_SANDSTONE:
            case PURPUR_BLOCK:
            case PURPUR_PILLAR:
            case END_STONE_BRICKS:
            case REPEATING_COMMAND_BLOCK:
            case CHAIN_COMMAND_BLOCK:
            case MAGMA_BLOCK:
            case NETHER_WART_BLOCK:
            case RED_NETHER_BRICKS:
            case BONE_BLOCK:
            case SHULKER_BOX:
            case WHITE_SHULKER_BOX:
            case ORANGE_SHULKER_BOX:
            case MAGENTA_SHULKER_BOX:
            case LIGHT_BLUE_SHULKER_BOX:
            case YELLOW_SHULKER_BOX:
            case LIME_SHULKER_BOX:
            case PINK_SHULKER_BOX:
            case GRAY_SHULKER_BOX:
            case LIGHT_GRAY_SHULKER_BOX:
            case CYAN_SHULKER_BOX:
            case PURPLE_SHULKER_BOX:
            case BLUE_SHULKER_BOX:
            case BROWN_SHULKER_BOX:
            case GREEN_SHULKER_BOX:
            case RED_SHULKER_BOX:
            case BLACK_SHULKER_BOX:
            case WHITE_GLAZED_TERRACOTTA:
            case ORANGE_GLAZED_TERRACOTTA:
            case MAGENTA_GLAZED_TERRACOTTA:
            case LIGHT_BLUE_GLAZED_TERRACOTTA:
            case YELLOW_GLAZED_TERRACOTTA:
            case LIME_GLAZED_TERRACOTTA:
            case PINK_GLAZED_TERRACOTTA:
            case GRAY_GLAZED_TERRACOTTA:
            case LIGHT_GRAY_GLAZED_TERRACOTTA:
            case CYAN_GLAZED_TERRACOTTA:
            case PURPLE_GLAZED_TERRACOTTA:
            case BLUE_GLAZED_TERRACOTTA:
            case BROWN_GLAZED_TERRACOTTA:
            case GREEN_GLAZED_TERRACOTTA:
            case RED_GLAZED_TERRACOTTA:
            case BLACK_GLAZED_TERRACOTTA:
            case WHITE_CONCRETE:
            case ORANGE_CONCRETE:
            case MAGENTA_CONCRETE:
            case LIGHT_BLUE_CONCRETE:
            case YELLOW_CONCRETE:
            case LIME_CONCRETE:
            case PINK_CONCRETE:
            case GRAY_CONCRETE:
            case LIGHT_GRAY_CONCRETE:
            case CYAN_CONCRETE:
            case PURPLE_CONCRETE:
            case BLUE_CONCRETE:
            case BROWN_CONCRETE:
            case GREEN_CONCRETE:
            case RED_CONCRETE:
            case BLACK_CONCRETE:
            case WHITE_CONCRETE_POWDER:
            case ORANGE_CONCRETE_POWDER:
            case MAGENTA_CONCRETE_POWDER:
            case LIGHT_BLUE_CONCRETE_POWDER:
            case YELLOW_CONCRETE_POWDER:
            case LIME_CONCRETE_POWDER:
            case PINK_CONCRETE_POWDER:
            case GRAY_CONCRETE_POWDER:
            case LIGHT_GRAY_CONCRETE_POWDER:
            case CYAN_CONCRETE_POWDER:
            case PURPLE_CONCRETE_POWDER:
            case BLUE_CONCRETE_POWDER:
            case BROWN_CONCRETE_POWDER:
            case GREEN_CONCRETE_POWDER:
            case RED_CONCRETE_POWDER:
            case BLACK_CONCRETE_POWDER:
            case DRIED_KELP_BLOCK:
            case DEAD_TUBE_CORAL_BLOCK:
            case DEAD_BRAIN_CORAL_BLOCK:
            case DEAD_BUBBLE_CORAL_BLOCK:
            case DEAD_FIRE_CORAL_BLOCK:
            case DEAD_HORN_CORAL_BLOCK:
            case TUBE_CORAL_BLOCK:
            case BRAIN_CORAL_BLOCK:
            case BUBBLE_CORAL_BLOCK:
            case FIRE_CORAL_BLOCK:
            case HORN_CORAL_BLOCK:
            case BLUE_ICE:
            case LOOM:
            case BARREL:
            case SMOKER:
            case BLAST_FURNACE:
            case CARTOGRAPHY_TABLE:
            case FLETCHING_TABLE:
            case SMITHING_TABLE:
            case WARPED_STEM:
            case STRIPPED_WARPED_STEM:
            case WARPED_HYPHAE:
            case STRIPPED_WARPED_HYPHAE:
            case WARPED_NYLIUM:
            case WARPED_WART_BLOCK:
            case CRIMSON_STEM:
            case STRIPPED_CRIMSON_STEM:
            case CRIMSON_HYPHAE:
            case STRIPPED_CRIMSON_HYPHAE:
            case CRIMSON_NYLIUM:
            case SHROOMLIGHT:
            case CRIMSON_PLANKS:
            case WARPED_PLANKS:
            case STRUCTURE_BLOCK:
            case JIGSAW:
            case TARGET:
            case BEE_NEST:
            case BEEHIVE:
            case HONEYCOMB_BLOCK:
            case NETHERITE_BLOCK:
            case ANCIENT_DEBRIS:
            case CRYING_OBSIDIAN:
            case RESPAWN_ANCHOR:
            case LODESTONE:
            case BLACKSTONE:
            case POLISHED_BLACKSTONE:
            case POLISHED_BLACKSTONE_BRICKS:
            case CRACKED_POLISHED_BLACKSTONE_BRICKS:
            case CHISELED_POLISHED_BLACKSTONE:
            case GILDED_BLACKSTONE:
            case CHISELED_NETHER_BRICKS:
            case CRACKED_NETHER_BRICKS:
            case QUARTZ_BRICKS:
            case AMETHYST_BLOCK:
            case BUDDING_AMETHYST:
            case TUFF:
            case POLISHED_TUFF:
            case CHISELED_TUFF:
            case TUFF_BRICKS:
            case CHISELED_TUFF_BRICKS:
            case CALCITE:
            case SCULK:
            case SCULK_CATALYST:
            case COPPER_BLOCK:
            case EXPOSED_COPPER:
            case WEATHERED_COPPER:
            case OXIDIZED_COPPER:
            case COPPER_ORE:
            case DEEPSLATE_COPPER_ORE:
            case OXIDIZED_CUT_COPPER:
            case WEATHERED_CUT_COPPER:
            case EXPOSED_CUT_COPPER:
            case CUT_COPPER:
            case OXIDIZED_CHISELED_COPPER:
            case WEATHERED_CHISELED_COPPER:
            case EXPOSED_CHISELED_COPPER:
            case CHISELED_COPPER:
            case WAXED_OXIDIZED_CHISELED_COPPER:
            case WAXED_WEATHERED_CHISELED_COPPER:
            case WAXED_EXPOSED_CHISELED_COPPER:
            case WAXED_CHISELED_COPPER:
            case WAXED_COPPER_BLOCK:
            case WAXED_WEATHERED_COPPER:
            case WAXED_EXPOSED_COPPER:
            case WAXED_OXIDIZED_COPPER:
            case WAXED_OXIDIZED_CUT_COPPER:
            case WAXED_WEATHERED_CUT_COPPER:
            case WAXED_EXPOSED_CUT_COPPER:
            case WAXED_CUT_COPPER:
            case DRIPSTONE_BLOCK:
            case MOSS_BLOCK:
            case ROOTED_DIRT:
            case MUD:
            case DEEPSLATE:
            case COBBLED_DEEPSLATE:
            case POLISHED_DEEPSLATE:
            case DEEPSLATE_TILES:
            case DEEPSLATE_BRICKS:
            case CHISELED_DEEPSLATE:
            case CRACKED_DEEPSLATE_BRICKS:
            case CRACKED_DEEPSLATE_TILES:
            case INFESTED_DEEPSLATE:
            case SMOOTH_BASALT:
            case RAW_IRON_BLOCK:
            case RAW_COPPER_BLOCK:
            case RAW_GOLD_BLOCK:
            case OCHRE_FROGLIGHT:
            case VERDANT_FROGLIGHT:
            case PEARLESCENT_FROGLIGHT:
            case REINFORCED_DEEPSLATE:
            case CRAFTER:
            case TRIAL_SPAWNER:
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
            case SAND:
            case RED_SAND:
            case GRAVEL:
            case DRAGON_EGG:
            case ANVIL:
            case CHIPPED_ANVIL:
            case DAMAGED_ANVIL:
            case WHITE_CONCRETE_POWDER:
            case ORANGE_CONCRETE_POWDER:
            case MAGENTA_CONCRETE_POWDER:
            case LIGHT_BLUE_CONCRETE_POWDER:
            case YELLOW_CONCRETE_POWDER:
            case LIME_CONCRETE_POWDER:
            case PINK_CONCRETE_POWDER:
            case GRAY_CONCRETE_POWDER:
            case LIGHT_GRAY_CONCRETE_POWDER:
            case CYAN_CONCRETE_POWDER:
            case PURPLE_CONCRETE_POWDER:
            case BLUE_CONCRETE_POWDER:
            case BROWN_CONCRETE_POWDER:
            case GREEN_CONCRETE_POWDER:
            case RED_CONCRETE_POWDER:
            case BLACK_CONCRETE_POWDER:
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
            case TUBE_CORAL_WALL_FAN:
            case WATER_CAULDRON:
            case WATER:
            case MELON_STEM:
            case CYAN_CANDLE_CAKE:
            case RED_WALL_BANNER:
            case SPRUCE_WALL_SIGN:
            case POTTED_FLOWERING_AZALEA_BUSH:
            case JUNGLE_WALL_SIGN:
            case POTTED_RED_MUSHROOM:
            case POTTED_ORANGE_TULIP:
            case OAK_WALL_SIGN:
            case WHITE_WALL_BANNER:
            case CREEPER_WALL_HEAD:
            case REDSTONE_WIRE:
            case YELLOW_WALL_BANNER:
            case CARROTS:
            case POTTED_BROWN_MUSHROOM:
            case WARPED_WALL_SIGN:
            case SPRUCE_WALL_HANGING_SIGN:
            case PISTON_HEAD:
            case CRIMSON_WALL_SIGN:
            case SOUL_FIRE:
            case POTTED_AZALEA_BUSH:
            case POTTED_RED_TULIP:
            case POTTED_AZURE_BLUET:
            case SKELETON_WALL_SKULL:
            case BROWN_CANDLE_CAKE:
            case PURPLE_WALL_BANNER:
            case DEAD_BUBBLE_CORAL_WALL_FAN:
            case BRAIN_CORAL_WALL_FAN:
            case POTTED_CHERRY_SAPLING:
            case BAMBOO_WALL_SIGN:
            case BEETROOTS:
            case CAVE_VINES:
            case LIME_WALL_BANNER:
            case MANGROVE_WALL_SIGN:
            case CHERRY_WALL_HANGING_SIGN:
            case BLUE_CANDLE_CAKE:
            case JUNGLE_WALL_HANGING_SIGN:
            case DRAGON_WALL_HEAD:
            case MAGENTA_CANDLE_CAKE:
            case GRAY_CANDLE_CAKE:
            case DEAD_BRAIN_CORAL_WALL_FAN:
            case WITHER_SKELETON_WALL_SKULL:
            case PITCHER_CROP:
            case LAVA:
            case VOID_AIR:
            case ORANGE_WALL_BANNER:
            case TRIPWIRE:
            case POTTED_BAMBOO:
            case POTTED_SPRUCE_SAPLING:
            case BUBBLE_COLUMN:
            case POTTED_ALLIUM:
            case BLACK_WALL_BANNER:
            case POTTED_LILY_OF_THE_VALLEY:
            case WARPED_WALL_HANGING_SIGN:
            case GRAY_WALL_BANNER:
            case NETHER_PORTAL:
            case CYAN_WALL_BANNER:
            case PURPLE_CANDLE_CAKE:
            case BIG_DRIPLEAF_STEM:
            case LIGHT_BLUE_WALL_BANNER:
            case BUBBLE_CORAL_WALL_FAN:
            case FROSTED_ICE:
            case POWDER_SNOW_CAULDRON:
            case POTTED_ACACIA_SAPLING:
            case POTTED_WARPED_ROOTS:
            case DEAD_HORN_CORAL_WALL_FAN:
            case OAK_WALL_HANGING_SIGN:
            case BAMBOO_SAPLING:
            case RED_CANDLE_CAKE:
            case POTTED_CACTUS:
            case POTTED_BIRCH_SAPLING:
            case DEAD_FIRE_CORAL_WALL_FAN:
            case BROWN_WALL_BANNER:
            case POTTED_OAK_SAPLING:
            case POTTED_TORCHFLOWER:
            case CAVE_AIR:
            case POTTED_JUNGLE_SAPLING:
            case ATTACHED_MELON_STEM:
            case POTTED_CRIMSON_ROOTS:
            case YELLOW_CANDLE_CAKE:
            case ACACIA_WALL_HANGING_SIGN:
            case TORCHFLOWER_CROP:
            case POTTED_FERN:
            case POTTED_WITHER_ROSE:
            case DARK_OAK_WALL_SIGN:
            case END_GATEWAY:
            case POTTED_WARPED_FUNGUS:
            case POTTED_DARK_OAK_SAPLING:
            case CAVE_VINES_PLANT:
            case WHITE_CANDLE_CAKE:
            case REDSTONE_WALL_TORCH:
            case BLACK_CANDLE_CAKE:
            case TWISTING_VINES_PLANT:
            case WALL_TORCH:
            case MAGENTA_WALL_BANNER:
            case MANGROVE_WALL_HANGING_SIGN:
            case BAMBOO_WALL_HANGING_SIGN:
            case LIGHT_BLUE_CANDLE_CAKE:
            case PIGLIN_WALL_HEAD:
            case POTTED_OXEYE_DAISY:
            case CHERRY_WALL_SIGN:
            case DEAD_TUBE_CORAL_WALL_FAN:
            case SWEET_BERRY_BUSH:
            case LAVA_CAULDRON:
            case POTTED_DANDELION:
            case ATTACHED_PUMPKIN_STEM:
            case FIRE:
            case BLUE_WALL_BANNER:
            case ORANGE_CANDLE_CAKE:
            case POTTED_POPPY:
            case KELP_PLANT:
            case PINK_CANDLE_CAKE:
            case HORN_CORAL_WALL_FAN:
            case LIGHT_GRAY_CANDLE_CAKE:
            case POTTED_WHITE_TULIP:
            case POTTED_MANGROVE_PROPAGULE:
            case ZOMBIE_WALL_HEAD:
            case BIRCH_WALL_SIGN:
            case END_PORTAL:
            case PLAYER_WALL_HEAD:
            case PINK_WALL_BANNER:
            case CANDLE_CAKE:
            case COCOA:
            case POTTED_CORNFLOWER:
            case GREEN_CANDLE_CAKE:
            case BIRCH_WALL_HANGING_SIGN:
            case GREEN_WALL_BANNER:
            case TALL_SEAGRASS:
            case POTTED_DEAD_BUSH:
            case POTTED_PINK_TULIP:
            case LIGHT_GRAY_WALL_BANNER:
            case PUMPKIN_STEM:
            case POTTED_BLUE_ORCHID:
            case POTATOES:
            case DARK_OAK_WALL_HANGING_SIGN:
            case POTTED_CRIMSON_FUNGUS:
            case ACACIA_WALL_SIGN:
            case FIRE_CORAL_WALL_FAN:
            case LIME_CANDLE_CAKE:
            case WEEPING_VINES_PLANT:
            case POWDER_SNOW:
            case CRIMSON_WALL_HANGING_SIGN:
            case MOVING_PISTON:
            case SOUL_WALL_TORCH:
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
            case DISPENSER:
            case NOTE_BLOCK:
            case WHITE_BED:
            case ORANGE_BED:
            case MAGENTA_BED:
            case LIGHT_BLUE_BED:
            case YELLOW_BED:
            case LIME_BED:
            case PINK_BED:
            case GRAY_BED:
            case LIGHT_GRAY_BED:
            case CYAN_BED:
            case PURPLE_BED:
            case BLUE_BED:
            case BROWN_BED:
            case GREEN_BED:
            case RED_BED:
            case BLACK_BED:
            case MOVING_PISTON:
            case TNT:
            case CHISELED_BOOKSHELF:
            case CHEST:
            case REDSTONE_WIRE:
            case CRAFTING_TABLE:
            case FURNACE:
            case OAK_SIGN:
            case SPRUCE_SIGN:
            case BIRCH_SIGN:
            case ACACIA_SIGN:
            case CHERRY_SIGN:
            case JUNGLE_SIGN:
            case DARK_OAK_SIGN:
            case MANGROVE_SIGN:
            case BAMBOO_SIGN:
            case OAK_DOOR:
            case OAK_WALL_SIGN:
            case SPRUCE_WALL_SIGN:
            case BIRCH_WALL_SIGN:
            case ACACIA_WALL_SIGN:
            case CHERRY_WALL_SIGN:
            case JUNGLE_WALL_SIGN:
            case DARK_OAK_WALL_SIGN:
            case MANGROVE_WALL_SIGN:
            case BAMBOO_WALL_SIGN:
            case OAK_HANGING_SIGN:
            case SPRUCE_HANGING_SIGN:
            case BIRCH_HANGING_SIGN:
            case ACACIA_HANGING_SIGN:
            case CHERRY_HANGING_SIGN:
            case JUNGLE_HANGING_SIGN:
            case DARK_OAK_HANGING_SIGN:
            case CRIMSON_HANGING_SIGN:
            case WARPED_HANGING_SIGN:
            case MANGROVE_HANGING_SIGN:
            case BAMBOO_HANGING_SIGN:
            case OAK_WALL_HANGING_SIGN:
            case SPRUCE_WALL_HANGING_SIGN:
            case BIRCH_WALL_HANGING_SIGN:
            case ACACIA_WALL_HANGING_SIGN:
            case CHERRY_WALL_HANGING_SIGN:
            case JUNGLE_WALL_HANGING_SIGN:
            case DARK_OAK_WALL_HANGING_SIGN:
            case MANGROVE_WALL_HANGING_SIGN:
            case CRIMSON_WALL_HANGING_SIGN:
            case WARPED_WALL_HANGING_SIGN:
            case BAMBOO_WALL_HANGING_SIGN:
            case LEVER:
            case IRON_DOOR:
            case REDSTONE_ORE:
            case DEEPSLATE_REDSTONE_ORE:
            case STONE_BUTTON:
            case JUKEBOX:
            case OAK_FENCE:
            case CAKE:
            case REPEATER:
            case OAK_TRAPDOOR:
            case SPRUCE_TRAPDOOR:
            case BIRCH_TRAPDOOR:
            case JUNGLE_TRAPDOOR:
            case ACACIA_TRAPDOOR:
            case CHERRY_TRAPDOOR:
            case DARK_OAK_TRAPDOOR:
            case MANGROVE_TRAPDOOR:
            case BAMBOO_TRAPDOOR:
            case PUMPKIN:
            case OAK_FENCE_GATE:
            case NETHER_BRICK_FENCE:
            case ENCHANTING_TABLE:
            case BREWING_STAND:
            case CAULDRON:
            case WATER_CAULDRON:
            case LAVA_CAULDRON:
            case POWDER_SNOW_CAULDRON:
            case DRAGON_EGG:
            case ENDER_CHEST:
            case COMMAND_BLOCK:
            case BEACON:
            case FLOWER_POT:
            case POTTED_TORCHFLOWER:
            case POTTED_OAK_SAPLING:
            case POTTED_SPRUCE_SAPLING:
            case POTTED_BIRCH_SAPLING:
            case POTTED_JUNGLE_SAPLING:
            case POTTED_ACACIA_SAPLING:
            case POTTED_CHERRY_SAPLING:
            case POTTED_DARK_OAK_SAPLING:
            case POTTED_MANGROVE_PROPAGULE:
            case POTTED_FERN:
            case POTTED_DANDELION:
            case POTTED_POPPY:
            case POTTED_BLUE_ORCHID:
            case POTTED_ALLIUM:
            case POTTED_AZURE_BLUET:
            case POTTED_RED_TULIP:
            case POTTED_ORANGE_TULIP:
            case POTTED_WHITE_TULIP:
            case POTTED_PINK_TULIP:
            case POTTED_OXEYE_DAISY:
            case POTTED_CORNFLOWER:
            case POTTED_LILY_OF_THE_VALLEY:
            case POTTED_WITHER_ROSE:
            case POTTED_RED_MUSHROOM:
            case POTTED_BROWN_MUSHROOM:
            case POTTED_DEAD_BUSH:
            case POTTED_CACTUS:
            case OAK_BUTTON:
            case SPRUCE_BUTTON:
            case BIRCH_BUTTON:
            case JUNGLE_BUTTON:
            case ACACIA_BUTTON:
            case CHERRY_BUTTON:
            case DARK_OAK_BUTTON:
            case MANGROVE_BUTTON:
            case BAMBOO_BUTTON:
            case ANVIL:
            case CHIPPED_ANVIL:
            case DAMAGED_ANVIL:
            case TRAPPED_CHEST:
            case COMPARATOR:
            case DAYLIGHT_DETECTOR:
            case HOPPER:
            case DROPPER:
            case LIGHT:
            case IRON_TRAPDOOR:
            case SPRUCE_FENCE_GATE:
            case BIRCH_FENCE_GATE:
            case JUNGLE_FENCE_GATE:
            case ACACIA_FENCE_GATE:
            case CHERRY_FENCE_GATE:
            case DARK_OAK_FENCE_GATE:
            case MANGROVE_FENCE_GATE:
            case BAMBOO_FENCE_GATE:
            case SPRUCE_FENCE:
            case BIRCH_FENCE:
            case JUNGLE_FENCE:
            case ACACIA_FENCE:
            case CHERRY_FENCE:
            case DARK_OAK_FENCE:
            case MANGROVE_FENCE:
            case BAMBOO_FENCE:
            case SPRUCE_DOOR:
            case BIRCH_DOOR:
            case JUNGLE_DOOR:
            case ACACIA_DOOR:
            case CHERRY_DOOR:
            case DARK_OAK_DOOR:
            case MANGROVE_DOOR:
            case BAMBOO_DOOR:
            case REPEATING_COMMAND_BLOCK:
            case CHAIN_COMMAND_BLOCK:
            case SHULKER_BOX:
            case WHITE_SHULKER_BOX:
            case ORANGE_SHULKER_BOX:
            case MAGENTA_SHULKER_BOX:
            case LIGHT_BLUE_SHULKER_BOX:
            case YELLOW_SHULKER_BOX:
            case LIME_SHULKER_BOX:
            case PINK_SHULKER_BOX:
            case GRAY_SHULKER_BOX:
            case LIGHT_GRAY_SHULKER_BOX:
            case CYAN_SHULKER_BOX:
            case PURPLE_SHULKER_BOX:
            case BLUE_SHULKER_BOX:
            case BROWN_SHULKER_BOX:
            case GREEN_SHULKER_BOX:
            case RED_SHULKER_BOX:
            case BLACK_SHULKER_BOX:
            case POTTED_BAMBOO:
            case LOOM:
            case BARREL:
            case SMOKER:
            case BLAST_FURNACE:
            case CARTOGRAPHY_TABLE:
            case FLETCHING_TABLE:
            case GRINDSTONE:
            case LECTERN:
            case SMITHING_TABLE:
            case STONECUTTER:
            case BELL:
            case CAMPFIRE:
            case SOUL_CAMPFIRE:
            case SWEET_BERRY_BUSH:
            case CRIMSON_FENCE:
            case WARPED_FENCE:
            case CRIMSON_TRAPDOOR:
            case WARPED_TRAPDOOR:
            case CRIMSON_FENCE_GATE:
            case WARPED_FENCE_GATE:
            case CRIMSON_BUTTON:
            case WARPED_BUTTON:
            case CRIMSON_DOOR:
            case WARPED_DOOR:
            case CRIMSON_SIGN:
            case WARPED_SIGN:
            case CRIMSON_WALL_SIGN:
            case WARPED_WALL_SIGN:
            case STRUCTURE_BLOCK:
            case JIGSAW:
            case COMPOSTER:
            case BEE_NEST:
            case BEEHIVE:
            case RESPAWN_ANCHOR:
            case POTTED_CRIMSON_FUNGUS:
            case POTTED_WARPED_FUNGUS:
            case POTTED_CRIMSON_ROOTS:
            case POTTED_WARPED_ROOTS:
            case POLISHED_BLACKSTONE_BUTTON:
            case CANDLE:
            case WHITE_CANDLE:
            case ORANGE_CANDLE:
            case MAGENTA_CANDLE:
            case LIGHT_BLUE_CANDLE:
            case YELLOW_CANDLE:
            case LIME_CANDLE:
            case PINK_CANDLE:
            case GRAY_CANDLE:
            case LIGHT_GRAY_CANDLE:
            case CYAN_CANDLE:
            case PURPLE_CANDLE:
            case BLUE_CANDLE:
            case BROWN_CANDLE:
            case GREEN_CANDLE:
            case RED_CANDLE:
            case BLACK_CANDLE:
            case CANDLE_CAKE:
            case WHITE_CANDLE_CAKE:
            case ORANGE_CANDLE_CAKE:
            case MAGENTA_CANDLE_CAKE:
            case LIGHT_BLUE_CANDLE_CAKE:
            case YELLOW_CANDLE_CAKE:
            case LIME_CANDLE_CAKE:
            case PINK_CANDLE_CAKE:
            case GRAY_CANDLE_CAKE:
            case LIGHT_GRAY_CANDLE_CAKE:
            case CYAN_CANDLE_CAKE:
            case PURPLE_CANDLE_CAKE:
            case BLUE_CANDLE_CAKE:
            case BROWN_CANDLE_CAKE:
            case GREEN_CANDLE_CAKE:
            case RED_CANDLE_CAKE:
            case BLACK_CANDLE_CAKE:
            case COPPER_DOOR:
            case EXPOSED_COPPER_DOOR:
            case OXIDIZED_COPPER_DOOR:
            case WEATHERED_COPPER_DOOR:
            case WAXED_COPPER_DOOR:
            case WAXED_EXPOSED_COPPER_DOOR:
            case WAXED_OXIDIZED_COPPER_DOOR:
            case WAXED_WEATHERED_COPPER_DOOR:
            case COPPER_TRAPDOOR:
            case EXPOSED_COPPER_TRAPDOOR:
            case OXIDIZED_COPPER_TRAPDOOR:
            case WEATHERED_COPPER_TRAPDOOR:
            case WAXED_COPPER_TRAPDOOR:
            case WAXED_EXPOSED_COPPER_TRAPDOOR:
            case WAXED_OXIDIZED_COPPER_TRAPDOOR:
            case WAXED_WEATHERED_COPPER_TRAPDOOR:
            case CAVE_VINES:
            case CAVE_VINES_PLANT:
            case POTTED_AZALEA_BUSH:
            case POTTED_FLOWERING_AZALEA_BUSH:
            case DECORATED_POT:
            case CRAFTER:
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
            case BROWN_MUSHROOM_BLOCK:
            case VINE:
            case BROWN_BED:
            case MANGROVE_LEAVES:
            case RED_MUSHROOM_BLOCK:
            case DARK_OAK_LEAVES:
            case LIGHT_BLUE_BED:
            case BLACK_BED:
            case ACACIA_LEAVES:
            case LIGHT_GRAY_BED:
            case BLUE_BED:
            case ORANGE_BED:
            case GLOW_LICHEN:
            case GREEN_BED:
            case SCULK_VEIN:
            case CYAN_BED:
            case YELLOW_BED:
            case WHITE_BED:
            case JUNGLE_LEAVES:
            case CHERRY_LEAVES:
            case FLOWERING_AZALEA_LEAVES:
            case LIME_BED:
            case GRAY_BED:
            case OAK_LEAVES:
            case DAYLIGHT_DETECTOR:
            case SPRUCE_LEAVES:
            case MAGENTA_BED:
            case PINK_BED:
            case RED_BED:
            case SCULK:
            case SNOW_BLOCK:
            case BIRCH_LEAVES:
            case PURPLE_BED:
            case AZALEA_LEAVES:
            case COCOA:
            case MUSHROOM_STEM:
                return 0.2F;
            case STRIPPED_SPRUCE_LOG:
            case STRIPPED_CHERRY_WOOD:
            case CYAN_SHULKER_BOX:
            case POLISHED_BLACKSTONE_SLAB:
            case SMOOTH_RED_SANDSTONE:
            case CRIMSON_STAIRS:
            case SMOOTH_SANDSTONE_SLAB:
            case ACACIA_LOG:
            case SMOOTH_SANDSTONE_STAIRS:
            case SPRUCE_PLANKS:
            case POWDER_SNOW_CAULDRON:
            case WATER_CAULDRON:
            case BRICKS:
            case RED_NETHER_BRICK_WALL:
            case SMOOTH_QUARTZ_STAIRS:
            case RED_SHULKER_BOX:
            case BLACKSTONE_SLAB:
            case STRIPPED_ACACIA_LOG:
            case CHERRY_FENCE:
            case OAK_WOOD:
            case MOSSY_COBBLESTONE_SLAB:
            case SPRUCE_WOOD:
            case SMOOTH_QUARTZ_SLAB:
            case STRIPPED_ACACIA_WOOD:
            case SMOOTH_QUARTZ:
            case STRIPPED_BIRCH_LOG:
            case COBBLESTONE_WALL:
            case BAMBOO_MOSAIC_SLAB:
            case STRIPPED_OAK_LOG:
            case ACACIA_SLAB:
            case MANGROVE_SLAB:
            case WARPED_PLANKS:
            case CHERRY_SLAB:
            case ORANGE_SHULKER_BOX:
            case STRIPPED_BIRCH_WOOD:
            case SPRUCE_LOG:
            case BRICK_SLAB:
            case SPRUCE_FENCE:
            case JUNGLE_SLAB:
            case MANGROVE_STAIRS:
            case STRIPPED_DARK_OAK_WOOD:
            case BIRCH_FENCE:
            case MANGROVE_FENCE:
            case BAMBOO_FENCE:
            case JUNGLE_PLANKS:
            case BIRCH_STAIRS:
            case SMOOTH_RED_SANDSTONE_STAIRS:
            case OAK_STAIRS:
            case GREEN_SHULKER_BOX:
            case COBBLESTONE:
            case STRIPPED_CRIMSON_HYPHAE:
            case OAK_SLAB:
            case STRIPPED_MANGROVE_LOG:
            case SMOOTH_RED_SANDSTONE_SLAB:
            case WHITE_SHULKER_BOX:
            case LIGHT_BLUE_SHULKER_BOX:
            case CHERRY_PLANKS:
            case NETHER_BRICK_WALL:
            case CAULDRON:
            case PETRIFIED_OAK_SLAB:
            case CHERRY_FENCE_GATE:
            case OAK_FENCE_GATE:
            case POLISHED_BLACKSTONE_STAIRS:
            case BAMBOO_BLOCK:
            case CAMPFIRE:
            case WARPED_FENCE_GATE:
            case SPRUCE_STAIRS:
            case SANDSTONE_SLAB:
            case PURPLE_SHULKER_BOX:
            case BAMBOO_MOSAIC_STAIRS:
            case BLUE_SHULKER_BOX:
            case STRIPPED_OAK_WOOD:
            case WARPED_SLAB:
            case CRIMSON_SLAB:
            case DARK_OAK_WOOD:
            case CHISELED_NETHER_BRICKS:
            case CRIMSON_HYPHAE:
            case POLISHED_BLACKSTONE:
            case JUKEBOX:
            case DARK_OAK_LOG:
            case ACACIA_STAIRS:
            case STRIPPED_WARPED_HYPHAE:
            case STRIPPED_CHERRY_LOG:
            case BAMBOO_PLANKS:
            case STRIPPED_JUNGLE_LOG:
            case CHERRY_WOOD:
            case CRIMSON_STEM:
            case MAGENTA_SHULKER_BOX:
            case LIGHT_GRAY_SHULKER_BOX:
            case WARPED_STAIRS:
            case MANGROVE_PLANKS:
            case STRIPPED_BAMBOO_BLOCK:
            case CRIMSON_PLANKS:
            case DARK_OAK_FENCE_GATE:
            case POLISHED_BLACKSTONE_WALL:
            case STRIPPED_CRIMSON_STEM:
            case STRIPPED_MANGROVE_WOOD:
            case BRICK_STAIRS:
            case ACACIA_FENCE_GATE:
            case BRICK_WALL:
            case CHERRY_LOG:
            case COBBLESTONE_SLAB:
            case BIRCH_SLAB:
            case STRIPPED_SPRUCE_WOOD:
            case BIRCH_FENCE_GATE:
            case LIME_SHULKER_BOX:
            case QUARTZ_SLAB:
            case MANGROVE_LOG:
            case MOSSY_COBBLESTONE_STAIRS:
            case CUT_RED_SANDSTONE_SLAB:
            case ACACIA_FENCE:
            case RED_NETHER_BRICK_SLAB:
            case JUNGLE_WOOD:
            case DARK_OAK_FENCE:
            case BONE_BLOCK:
            case JUNGLE_FENCE:
            case JUNGLE_LOG:
            case MOSSY_COBBLESTONE:
            case WARPED_FENCE:
            case DARK_OAK_STAIRS:
            case WARPED_HYPHAE:
            case YELLOW_SHULKER_BOX:
            case JUNGLE_STAIRS:
            case MOSSY_COBBLESTONE_WALL:
            case SHULKER_BOX:
            case GRINDSTONE:
            case CRIMSON_FENCE_GATE:
            case SMOOTH_SANDSTONE:
            case DARK_OAK_PLANKS:
            case SPRUCE_SLAB:
            case BAMBOO_MOSAIC:
            case ACACIA_WOOD:
            case COBBLESTONE_STAIRS:
            case CHERRY_STAIRS:
            case WARPED_STEM:
            case MANGROVE_WOOD:
            case STRIPPED_DARK_OAK_LOG:
            case SOUL_CAMPFIRE:
            case CUT_SANDSTONE_SLAB:
            case RED_SANDSTONE_SLAB:
            case MANGROVE_FENCE_GATE:
            case STONE_BRICK_SLAB:
            case DARK_OAK_SLAB:
            case SPRUCE_FENCE_GATE:
            case STRIPPED_WARPED_STEM:
            case CRACKED_NETHER_BRICKS:
            case NETHER_BRICK_STAIRS:
            case OAK_PLANKS:
            case SMOOTH_STONE_SLAB:
            case SMOOTH_STONE:
            case OAK_LOG:
            case GRAY_SHULKER_BOX:
            case RED_NETHER_BRICKS:
            case STRIPPED_JUNGLE_WOOD:
            case PURPUR_SLAB:
            case OAK_FENCE:
            case NETHER_BRICKS:
            case BAMBOO_SLAB:
            case BIRCH_LOG:
            case PINK_SHULKER_BOX:
            case BIRCH_PLANKS:
            case NETHER_BRICK_SLAB:
            case BIRCH_WOOD:
            case ACACIA_PLANKS:
            case JUNGLE_FENCE_GATE:
            case POLISHED_BLACKSTONE_BRICK_SLAB:
            case BAMBOO_STAIRS:
            case STONE_SLAB:
            case BLACK_SHULKER_BOX:
            case CRIMSON_FENCE:
            case RED_NETHER_BRICK_STAIRS:
            case BAMBOO_FENCE_GATE:
            case BROWN_SHULKER_BOX:
            case NETHER_BRICK_FENCE:
            case LAVA_CAULDRON:
                return 2.0F;
            case NETHERRACK:
            case CRIMSON_NYLIUM:
            case CHORUS_PLANT:
            case WARPED_NYLIUM:
            case CACTUS:
            case LADDER:
            case CHORUS_FLOWER:
                return 0.4F;
            case ENDER_CHEST:
                return 22.5F;
            case COBWEB:
                return 4.0F;
            case RESPAWN_ANCHOR:
            case OBSIDIAN:
            case NETHERITE_BLOCK:
            case CRYING_OBSIDIAN:
            case TRIAL_SPAWNER:
                return 50.0F;
            case INFESTED_CRACKED_STONE_BRICKS:
            case INFESTED_STONE:
            case INFESTED_CHISELED_STONE_BRICKS:
            case CALCITE:
            case INFESTED_STONE_BRICKS:
            case INFESTED_MOSSY_STONE_BRICKS:
                return 0.75F;
            case DIRT_PATH:
                return 0.65F;
            case MUD_BRICKS:
            case MOSSY_STONE_BRICK_SLAB:
            case STONE_BRICKS:
            case TUFF_BRICK_SLAB:
            case PRISMARINE_SLAB:
            case BLACKSTONE_STAIRS:
            case SMALL_AMETHYST_BUD:
            case POLISHED_GRANITE_STAIRS:
            case ANDESITE_STAIRS:
            case ANDESITE_WALL:
            case POLISHED_TUFF_STAIRS:
            case PISTON_HEAD:
            case MUD_BRICK_STAIRS:
            case STONE_BRICK_STAIRS:
            case BOOKSHELF:
            case LARGE_AMETHYST_BUD:
            case POLISHED_GRANITE:
            case DEAD_BUBBLE_CORAL_BLOCK:
            case STICKY_PISTON:
            case TUFF:
            case DARK_PRISMARINE_STAIRS:
            case POLISHED_BLACKSTONE_BRICK_STAIRS:
            case STONE_STAIRS:
            case GRANITE_STAIRS:
            case GRANITE_WALL:
            case POLISHED_TUFF_SLAB:
            case CHISELED_TUFF_BRICKS:
            case CRACKED_STONE_BRICKS:
            case SCULK_SENSOR:
            case TUFF_BRICKS:
            case MOSSY_STONE_BRICK_STAIRS:
            case POLISHED_GRANITE_SLAB:
            case POLISHED_BLACKSTONE_BRICKS:
            case MEDIUM_AMETHYST_BUD:
            case PURPUR_STAIRS:
            case POLISHED_DIORITE_STAIRS:
            case DIORITE_WALL:
            case CHISELED_BOOKSHELF:
            case TUFF_WALL:
            case CRAFTER:
            case GILDED_BLACKSTONE:
            case POINTED_DRIPSTONE:
            case DEAD_FIRE_CORAL_BLOCK:
            case POLISHED_ANDESITE_STAIRS:
            case MUD_BRICK_WALL:
            case PRISMARINE_STAIRS:
            case POLISHED_ANDESITE:
            case CALIBRATED_SCULK_SENSOR:
            case POLISHED_BLACKSTONE_BRICK_WALL:
            case DIORITE:
            case POLISHED_ANDESITE_SLAB:
            case MOSSY_STONE_BRICK_WALL:
            case HORN_CORAL_BLOCK:
            case STONE_BRICK_WALL:
            case PRISMARINE_BRICKS:
            case PURPUR_PILLAR:
            case TUBE_CORAL_BLOCK:
            case PRISMARINE_BRICK_SLAB:
            case DRIPSTONE_BLOCK:
            case DARK_PRISMARINE:
            case ANDESITE_SLAB:
            case TUFF_BRICK_STAIRS:
            case CHISELED_STONE_BRICKS:
            case PURPUR_BLOCK:
            case BUBBLE_CORAL_BLOCK:
            case PRISMARINE_WALL:
            case DIORITE_STAIRS:
            case BLACKSTONE_WALL:
            case CHISELED_POLISHED_BLACKSTONE:
            case GRANITE:
            case FIRE_CORAL_BLOCK:
            case BLACKSTONE:
            case CHISELED_TUFF:
            case POLISHED_DIORITE:
            case POLISHED_TUFF_WALL:
            case MOSSY_STONE_BRICKS:
            case PRISMARINE:
            case DEAD_TUBE_CORAL_BLOCK:
            case TUFF_SLAB:
            case DEAD_HORN_CORAL_BLOCK:
            case AMETHYST_BLOCK:
            case INFESTED_DEEPSLATE:
            case TUFF_BRICK_WALL:
            case AMETHYST_CLUSTER:
            case BUDDING_AMETHYST:
            case TUFF_STAIRS:
            case CRACKED_POLISHED_BLACKSTONE_BRICKS:
            case GRANITE_SLAB:
            case PISTON:
            case DIORITE_SLAB:
            case DEAD_BRAIN_CORAL_BLOCK:
            case DARK_PRISMARINE_SLAB:
            case POLISHED_TUFF:
            case MUD_BRICK_SLAB:
            case PRISMARINE_BRICK_STAIRS:
            case POLISHED_DIORITE_SLAB:
            case ANDESITE:
            case STONE:
            case BRAIN_CORAL_BLOCK:
                return 1.5F;
            case SPONGE:
            case FARMLAND:
            case BEEHIVE:
            case COMPOSTER:
            case GRASS_BLOCK:
            case MYCELIUM:
            case WET_SPONGE:
            case GRAVEL:
            case CLAY:
            case HONEYCOMB_BLOCK:
                return 0.6F;
            case RED_SANDSTONE_STAIRS:
            case SANDSTONE_STAIRS:
            case PINK_WOOL:
            case GRAY_WOOL:
            case SANDSTONE:
            case LIGHT_GRAY_WOOL:
            case PURPLE_WOOL:
            case QUARTZ_PILLAR:
            case RED_SANDSTONE_WALL:
            case CHISELED_SANDSTONE:
            case GREEN_WOOL:
            case RED_WOOL:
            case BLACK_WOOL:
            case QUARTZ_STAIRS:
            case CUT_RED_SANDSTONE:
            case SANDSTONE_WALL:
            case QUARTZ_BLOCK:
            case CHISELED_RED_SANDSTONE:
            case QUARTZ_BRICKS:
            case BLUE_WOOL:
            case RED_SANDSTONE:
            case MAGENTA_WOOL:
            case BROWN_WOOL:
            case CHISELED_QUARTZ_BLOCK:
            case NOTE_BLOCK:
            case CUT_SANDSTONE:
            case CYAN_WOOL:
            case ORANGE_WOOL:
            case LIME_WOOL:
            case WHITE_WOOL:
            case LIGHT_BLUE_WOOL:
            case YELLOW_WOOL:
                return 0.8F;
            case POLISHED_DEEPSLATE_SLAB:
            case BLAST_FURNACE:
            case COBBLED_DEEPSLATE_SLAB:
            case CHISELED_DEEPSLATE:
            case CRACKED_DEEPSLATE_BRICKS:
            case DEEPSLATE_TILE_WALL:
            case FURNACE:
            case LANTERN:
            case STONECUTTER:
            case POLISHED_DEEPSLATE:
            case COBBLED_DEEPSLATE_WALL:
            case POLISHED_DEEPSLATE_STAIRS:
            case DEEPSLATE_BRICKS:
            case DEEPSLATE_TILE_STAIRS:
            case DEEPSLATE_BRICK_WALL:
            case SMOKER:
            case DISPENSER:
            case COBBLED_DEEPSLATE:
            case COBBLED_DEEPSLATE_STAIRS:
            case CRACKED_DEEPSLATE_TILES:
            case LODESTONE:
            case DEEPSLATE_BRICK_STAIRS:
            case POLISHED_DEEPSLATE_WALL:
            case DEEPSLATE_TILES:
            case DEEPSLATE_BRICK_SLAB:
            case DEEPSLATE_TILE_SLAB:
            case DROPPER:
            case SOUL_LANTERN:
                return 3.5F;
            case BLUE_ICE:
                return 2.8F;
            case SUSPICIOUS_SAND:
            case SUSPICIOUS_GRAVEL:
            case POWDER_SNOW:
                return 0.25F;
            case ANCIENT_DEBRIS:
                return 30.0F;
            case PIGLIN_HEAD:
            case JUNGLE_WALL_HANGING_SIGN:
            case BIRCH_WALL_HANGING_SIGN:
            case PACKED_MUD:
            case SKELETON_SKULL:
            case MAGENTA_BANNER:
            case GREEN_WALL_BANNER:
            case DRAGON_HEAD:
            case SPRUCE_WALL_HANGING_SIGN:
            case PINK_BANNER:
            case CRIMSON_HANGING_SIGN:
            case CRIMSON_WALL_SIGN:
            case CHERRY_HANGING_SIGN:
            case BIRCH_SIGN:
            case SPRUCE_HANGING_SIGN:
            case OAK_WALL_HANGING_SIGN:
            case BAMBOO_SAPLING:
            case BROWN_BANNER:
            case PUMPKIN:
            case RED_BANNER:
            case NETHER_WART_BLOCK:
            case ZOMBIE_HEAD:
            case BLUE_WALL_BANNER:
            case LIGHT_GRAY_BANNER:
            case GREEN_BANNER:
            case SPRUCE_SIGN:
            case LIME_BANNER:
            case PLAYER_HEAD:
            case WARPED_HANGING_SIGN:
            case BLUE_BANNER:
            case DRAGON_WALL_HEAD:
            case DARK_OAK_SIGN:
            case BAMBOO_HANGING_SIGN:
            case LIGHT_GRAY_WALL_BANNER:
            case WITHER_SKELETON_SKULL:
            case MAGENTA_WALL_BANNER:
            case BAMBOO_SIGN:
            case DARK_OAK_HANGING_SIGN:
            case BROWN_WALL_BANNER:
            case MANGROVE_WALL_HANGING_SIGN:
            case ORANGE_BANNER:
            case RED_WALL_BANNER:
            case INFESTED_COBBLESTONE:
            case SPRUCE_WALL_SIGN:
            case JUNGLE_HANGING_SIGN:
            case DARK_OAK_WALL_HANGING_SIGN:
            case SKELETON_WALL_SKULL:
            case JUNGLE_WALL_SIGN:
            case ZOMBIE_WALL_HEAD:
            case CREEPER_HEAD:
            case ACACIA_HANGING_SIGN:
            case ACACIA_WALL_SIGN:
            case BLACK_BANNER:
            case WARPED_WART_BLOCK:
            case BIRCH_WALL_SIGN:
            case JACK_O_LANTERN:
            case PURPLE_WALL_BANNER:
            case PLAYER_WALL_HEAD:
            case BLACK_WALL_BANNER:
            case WITHER_SKELETON_WALL_SKULL:
            case BIRCH_HANGING_SIGN:
            case BAMBOO_WALL_HANGING_SIGN:
            case PINK_WALL_BANNER:
            case BAMBOO:
            case WARPED_WALL_HANGING_SIGN:
            case OAK_WALL_SIGN:
            case WHITE_WALL_BANNER:
            case ACACIA_WALL_HANGING_SIGN:
            case MANGROVE_SIGN:
            case WHITE_BANNER:
            case GRAY_WALL_BANNER:
            case CHERRY_SIGN:
            case JUNGLE_SIGN:
            case SHROOMLIGHT:
            case CARVED_PUMPKIN:
            case PIGLIN_WALL_HEAD:
            case CREEPER_WALL_HEAD:
            case BAMBOO_WALL_SIGN:
            case OAK_HANGING_SIGN:
            case LIGHT_BLUE_BANNER:
            case CHERRY_WALL_SIGN:
            case CYAN_WALL_BANNER:
            case ACACIA_SIGN:
            case OAK_SIGN:
            case MELON:
            case YELLOW_WALL_BANNER:
            case ORANGE_WALL_BANNER:
            case DARK_OAK_WALL_SIGN:
            case MANGROVE_HANGING_SIGN:
            case YELLOW_BANNER:
            case CRIMSON_WALL_HANGING_SIGN:
            case PURPLE_BANNER:
            case GRAY_BANNER:
            case CYAN_BANNER:
            case LIGHT_BLUE_WALL_BANNER:
            case CRIMSON_SIGN:
            case LIME_WALL_BANNER:
            case WARPED_SIGN:
            case MANGROVE_WALL_SIGN:
            case CHERRY_WALL_HANGING_SIGN:
            case WARPED_WALL_SIGN:
                return 1.0F;
            case BROWN_CARPET:
            case CANDLE:
            case BROWN_CANDLE:
            case WHITE_CARPET:
            case WHITE_CANDLE:
            case MOSS_CARPET:
            case PURPLE_CARPET:
            case ORANGE_CARPET:
            case PINK_CARPET:
            case ORANGE_CANDLE:
            case PINK_CANDLE:
            case MOSS_BLOCK:
            case PURPLE_CANDLE:
            case BLACK_CANDLE:
            case LIGHT_GRAY_CARPET:
            case BLACK_CARPET:
            case RED_CARPET:
            case RED_CANDLE:
            case LIGHT_BLUE_CARPET:
            case LIGHT_BLUE_CANDLE:
            case BIG_DRIPLEAF:
            case BIG_DRIPLEAF_STEM:
            case MAGENTA_CARPET:
            case MAGENTA_CANDLE:
            case SNOW:
            case LIGHT_GRAY_CANDLE:
            case LIME_CARPET:
            case LIME_CANDLE:
            case GRAY_CANDLE:
            case YELLOW_CARPET:
            case CYAN_CANDLE:
            case GREEN_CANDLE:
            case BLUE_CANDLE:
            case CYAN_CARPET:
            case GREEN_CARPET:
            case YELLOW_CANDLE:
            case GRAY_CARPET:
            case BLUE_CARPET:
                return 0.1F;
            case MANGROVE_TRAPDOOR:
            case WAXED_CHISELED_COPPER:
            case WAXED_EXPOSED_COPPER_DOOR:
            case DRAGON_EGG:
            case WAXED_EXPOSED_CHISELED_COPPER:
            case COPPER_GRATE:
            case EXPOSED_COPPER:
            case WEATHERED_CUT_COPPER_STAIRS:
            case CHERRY_TRAPDOOR:
            case END_STONE_BRICK_WALL:
            case EXPOSED_CUT_COPPER:
            case WAXED_WEATHERED_COPPER_BULB:
            case OXIDIZED_COPPER_BULB:
            case WAXED_OXIDIZED_COPPER_GRATE:
            case LIGHTNING_ROD:
            case SPRUCE_DOOR:
            case DEEPSLATE:
            case NETHER_GOLD_ORE:
            case COPPER_TRAPDOOR:
            case OXIDIZED_COPPER_TRAPDOOR:
            case LAPIS_BLOCK:
            case END_STONE_BRICK_STAIRS:
            case OAK_DOOR:
            case WAXED_WEATHERED_COPPER:
            case EXPOSED_COPPER_DOOR:
            case WAXED_COPPER_TRAPDOOR:
            case EXPOSED_CUT_COPPER_SLAB:
            case DARK_OAK_DOOR:
            case BIRCH_TRAPDOOR:
            case REDSTONE_ORE:
            case OXIDIZED_CUT_COPPER:
            case WAXED_EXPOSED_COPPER:
            case WAXED_COPPER_BULB:
            case WAXED_OXIDIZED_COPPER:
            case WAXED_WEATHERED_CUT_COPPER_STAIRS:
            case EXPOSED_COPPER_TRAPDOOR:
            case EMERALD_ORE:
            case COAL_ORE:
            case EXPOSED_CUT_COPPER_STAIRS:
            case WAXED_EXPOSED_COPPER_BULB:
            case SCULK_CATALYST:
            case WAXED_WEATHERED_CHISELED_COPPER:
            case WAXED_CUT_COPPER_SLAB:
            case SCULK_SHRIEKER:
            case OXIDIZED_COPPER:
            case WAXED_OXIDIZED_CUT_COPPER_STAIRS:
            case BAMBOO_DOOR:
            case HOPPER:
            case WAXED_OXIDIZED_COPPER_BULB:
            case GOLD_ORE:
            case WAXED_EXPOSED_CUT_COPPER_STAIRS:
            case WEATHERED_CUT_COPPER:
            case COPPER_BLOCK:
            case WEATHERED_COPPER_DOOR:
            case WEATHERED_CUT_COPPER_SLAB:
            case WAXED_CUT_COPPER_STAIRS:
            case ACACIA_TRAPDOOR:
            case NETHER_QUARTZ_ORE:
            case COPPER_ORE:
            case WAXED_EXPOSED_COPPER_GRATE:
            case BAMBOO_TRAPDOOR:
            case WARPED_TRAPDOOR:
            case COPPER_DOOR:
            case WAXED_COPPER_DOOR:
            case GOLD_BLOCK:
            case WAXED_CUT_COPPER:
            case WEATHERED_COPPER_BULB:
            case WAXED_WEATHERED_CUT_COPPER:
            case CHERRY_DOOR:
            case EXPOSED_CHISELED_COPPER:
            case WAXED_COPPER_GRATE:
            case WAXED_OXIDIZED_CHISELED_COPPER:
            case CUT_COPPER_SLAB:
            case OXIDIZED_CUT_COPPER_SLAB:
            case OXIDIZED_COPPER_GRATE:
            case DARK_OAK_TRAPDOOR:
            case CONDUIT:
            case WEATHERED_CHISELED_COPPER:
            case WEATHERED_COPPER_GRATE:
            case WAXED_WEATHERED_COPPER_DOOR:
            case ACACIA_DOOR:
            case OXIDIZED_COPPER_DOOR:
            case OBSERVER:
            case SPRUCE_TRAPDOOR:
            case END_STONE_BRICK_SLAB:
            case WAXED_WEATHERED_CUT_COPPER_SLAB:
            case OAK_TRAPDOOR:
            case CUT_COPPER:
            case CUT_COPPER_STAIRS:
            case END_STONE:
            case EXPOSED_COPPER_GRATE:
            case BEACON:
            case JUNGLE_DOOR:
            case WAXED_OXIDIZED_CUT_COPPER:
            case WEATHERED_COPPER:
            case END_STONE_BRICKS:
            case WAXED_WEATHERED_COPPER_GRATE:
            case LAPIS_ORE:
            case WAXED_OXIDIZED_COPPER_DOOR:
            case EXPOSED_COPPER_BULB:
            case WAXED_OXIDIZED_COPPER_TRAPDOOR:
            case WARPED_DOOR:
            case WAXED_WEATHERED_COPPER_TRAPDOOR:
            case MANGROVE_DOOR:
            case WAXED_EXPOSED_COPPER_TRAPDOOR:
            case CRIMSON_DOOR:
            case WAXED_COPPER_BLOCK:
            case WAXED_EXPOSED_CUT_COPPER_SLAB:
            case DIAMOND_ORE:
            case WAXED_EXPOSED_CUT_COPPER:
            case IRON_ORE:
            case BIRCH_DOOR:
            case WAXED_OXIDIZED_CUT_COPPER_SLAB:
            case COPPER_BULB:
            case JUNGLE_TRAPDOOR:
            case CRIMSON_TRAPDOOR:
            case CHISELED_COPPER:
            case OXIDIZED_CUT_COPPER_STAIRS:
            case WEATHERED_COPPER_TRAPDOOR:
            case OXIDIZED_CHISELED_COPPER:
                return 3.0F;
            case LIGHT_GRAY_STAINED_GLASS:
            case BLACK_STAINED_GLASS:
            case WHITE_STAINED_GLASS_PANE:
            case LIME_STAINED_GLASS:
            case VERDANT_FROGLIGHT:
            case BROWN_STAINED_GLASS:
            case ORANGE_STAINED_GLASS:
            case PURPLE_STAINED_GLASS:
            case PURPLE_STAINED_GLASS_PANE:
            case SEA_LANTERN:
            case GLASS:
            case CYAN_STAINED_GLASS_PANE:
            case CYAN_STAINED_GLASS:
            case PINK_STAINED_GLASS_PANE:
            case LIGHT_BLUE_STAINED_GLASS_PANE:
            case GLOWSTONE:
            case GREEN_STAINED_GLASS_PANE:
            case MAGENTA_STAINED_GLASS:
            case YELLOW_STAINED_GLASS_PANE:
            case REDSTONE_LAMP:
            case RED_STAINED_GLASS:
            case LIME_STAINED_GLASS_PANE:
            case TINTED_GLASS:
            case PEARLESCENT_FROGLIGHT:
            case BROWN_STAINED_GLASS_PANE:
            case YELLOW_STAINED_GLASS:
            case GRAY_STAINED_GLASS:
            case GREEN_STAINED_GLASS:
            case GRAY_STAINED_GLASS_PANE:
            case GLASS_PANE:
            case PINK_STAINED_GLASS:
            case ORANGE_STAINED_GLASS_PANE:
            case BEE_NEST:
            case BLUE_STAINED_GLASS:
            case BLUE_STAINED_GLASS_PANE:
            case RED_STAINED_GLASS_PANE:
            case MAGENTA_STAINED_GLASS_PANE:
            case LIGHT_GRAY_STAINED_GLASS_PANE:
            case OCHRE_FROGLIGHT:
            case WHITE_STAINED_GLASS:
            case BLACK_STAINED_GLASS_PANE:
            case LIGHT_BLUE_STAINED_GLASS:
                return 0.3F;
            case REINFORCED_DEEPSLATE:
                return 55.0F;
            case WATER:
            case LAVA:
                return 100.0F;
            case YELLOW_TERRACOTTA:
            case PURPLE_TERRACOTTA:
            case RED_TERRACOTTA:
            case MAGENTA_TERRACOTTA:
            case LIGHT_BLUE_TERRACOTTA:
            case BROWN_TERRACOTTA:
            case BLACK_TERRACOTTA:
            case POLISHED_BASALT:
            case BASALT:
            case TERRACOTTA:
            case LIGHT_GRAY_TERRACOTTA:
            case WHITE_TERRACOTTA:
            case GRAY_TERRACOTTA:
            case CYAN_TERRACOTTA:
            case ORANGE_TERRACOTTA:
            case LIME_TERRACOTTA:
            case SMOOTH_BASALT:
            case PINK_TERRACOTTA:
            case BLUE_TERRACOTTA:
            case GREEN_TERRACOTTA:
                return 1.25F;
            case MOVING_PISTON:
            case BARRIER:
            case LIGHT:
            case END_GATEWAY:
            case REPEATING_COMMAND_BLOCK:
            case JIGSAW:
            case NETHER_PORTAL:
            case END_PORTAL_FRAME:
            case BEDROCK:
            case END_PORTAL:
            case COMMAND_BLOCK:
            case STRUCTURE_BLOCK:
            case CHAIN_COMMAND_BLOCK:
                return -1.0F;
            case DEEPSLATE_IRON_ORE:
            case DEEPSLATE_LAPIS_ORE:
            case DEEPSLATE_COAL_ORE:
            case DEEPSLATE_DIAMOND_ORE:
            case DEEPSLATE_REDSTONE_ORE:
            case DEEPSLATE_COPPER_ORE:
            case DEEPSLATE_GOLD_ORE:
            case DEEPSLATE_EMERALD_ORE:
                return 4.5F;
            case DIAMOND_BLOCK:
            case IRON_DOOR:
            case DAMAGED_ANVIL:
            case IRON_TRAPDOOR:
            case RAW_IRON_BLOCK:
            case RAW_GOLD_BLOCK:
            case CHIPPED_ANVIL:
            case SPAWNER:
            case CHAIN:
            case IRON_BARS:
            case ANVIL:
            case REDSTONE_BLOCK:
            case IRON_BLOCK:
            case RAW_COPPER_BLOCK:
            case COAL_BLOCK:
            case BELL:
            case ENCHANTING_TABLE:
            case EMERALD_BLOCK:
                return 5.0F;
            case DARK_OAK_BUTTON:
            case TARGET:
            case GREEN_CONCRETE_POWDER:
            case WHITE_CANDLE_CAKE:
            case CHERRY_PRESSURE_PLATE:
            case ACACIA_PRESSURE_PLATE:
            case RED_SAND:
            case FROSTED_ICE:
            case BLACK_CANDLE_CAKE:
            case PODZOL:
            case BROWN_CANDLE_CAKE:
            case BLACK_CONCRETE_POWDER:
            case SPRUCE_PRESSURE_PLATE:
            case JUNGLE_PRESSURE_PLATE:
            case DARK_OAK_PRESSURE_PLATE:
            case BIRCH_PRESSURE_PLATE:
            case LIME_CONCRETE_POWDER:
            case POLISHED_BLACKSTONE_PRESSURE_PLATE:
            case MAGENTA_CONCRETE_POWDER:
            case DRIED_KELP_BLOCK:
            case HEAVY_WEIGHTED_PRESSURE_PLATE:
            case PINK_CONCRETE_POWDER:
            case CHERRY_BUTTON:
            case MUD:
            case LIGHT_BLUE_CONCRETE_POWDER:
            case OAK_BUTTON:
            case SNIFFER_EGG:
            case BREWING_STAND:
            case SAND:
            case LEVER:
            case STONE_PRESSURE_PLATE:
            case DIRT:
            case CAKE:
            case YELLOW_CANDLE_CAKE:
            case LIGHT_BLUE_CANDLE_CAKE:
            case LIME_CANDLE_CAKE:
            case WHITE_CONCRETE_POWDER:
            case HAY_BLOCK:
            case ORANGE_CONCRETE_POWDER:
            case CANDLE_CAKE:
            case WARPED_BUTTON:
            case ORANGE_CANDLE_CAKE:
            case CYAN_CANDLE_CAKE:
            case OAK_PRESSURE_PLATE:
            case RED_CANDLE_CAKE:
            case JUNGLE_BUTTON:
            case YELLOW_CONCRETE_POWDER:
            case TURTLE_EGG:
            case PINK_CANDLE_CAKE:
            case MANGROVE_BUTTON:
            case BAMBOO_BUTTON:
            case GRAY_CONCRETE_POWDER:
            case MAGMA_BLOCK:
            case WARPED_PRESSURE_PLATE:
            case BROWN_CONCRETE_POWDER:
            case BAMBOO_PRESSURE_PLATE:
            case STONE_BUTTON:
            case MAGENTA_CANDLE_CAKE:
            case COARSE_DIRT:
            case BLUE_CONCRETE_POWDER:
            case CRIMSON_BUTTON:
            case ACACIA_BUTTON:
            case CRIMSON_PRESSURE_PLATE:
            case ROOTED_DIRT:
            case LIGHT_GRAY_CANDLE_CAKE:
            case PURPLE_CANDLE_CAKE:
            case SOUL_SAND:
            case PURPLE_CONCRETE_POWDER:
            case CYAN_CONCRETE_POWDER:
            case ICE:
            case GRAY_CANDLE_CAKE:
            case MANGROVE_PRESSURE_PLATE:
            case BIRCH_BUTTON:
            case SPRUCE_BUTTON:
            case LIGHT_GRAY_CONCRETE_POWDER:
            case RED_CONCRETE_POWDER:
            case POLISHED_BLACKSTONE_BUTTON:
            case LIGHT_WEIGHTED_PRESSURE_PLATE:
            case BLUE_CANDLE_CAKE:
            case PACKED_ICE:
            case SOUL_SOIL:
            case GREEN_CANDLE_CAKE:
                return 0.5F;
            case GRAY_GLAZED_TERRACOTTA:
            case MAGENTA_GLAZED_TERRACOTTA:
            case LIGHT_BLUE_GLAZED_TERRACOTTA:
            case LIME_GLAZED_TERRACOTTA:
            case PURPLE_GLAZED_TERRACOTTA:
            case WHITE_GLAZED_TERRACOTTA:
            case BLUE_GLAZED_TERRACOTTA:
            case ORANGE_GLAZED_TERRACOTTA:
            case GREEN_GLAZED_TERRACOTTA:
            case LIGHT_GRAY_GLAZED_TERRACOTTA:
            case PINK_GLAZED_TERRACOTTA:
            case BLACK_GLAZED_TERRACOTTA:
            case BROWN_GLAZED_TERRACOTTA:
            case RED_GLAZED_TERRACOTTA:
            case YELLOW_GLAZED_TERRACOTTA:
            case CYAN_GLAZED_TERRACOTTA:
                return 1.4F;
            case CHEST:
            case LOOM:
            case CRAFTING_TABLE:
            case TRAPPED_CHEST:
            case LECTERN:
            case BARREL:
            case CARTOGRAPHY_TABLE:
            case SMITHING_TABLE:
            case FLETCHING_TABLE:
                return 2.5F;
            case DETECTOR_RAIL:
            case ACTIVATOR_RAIL:
            case POWERED_RAIL:
            case RAIL:
            case MANGROVE_ROOTS:
            case MUDDY_MANGROVE_ROOTS:
                return 0.7F;
            case GRAY_CONCRETE:
            case BROWN_CONCRETE:
            case CYAN_CONCRETE:
            case RED_CONCRETE:
            case LIGHT_BLUE_CONCRETE:
            case GREEN_CONCRETE:
            case MAGENTA_CONCRETE:
            case WHITE_CONCRETE:
            case PINK_CONCRETE:
            case ORANGE_CONCRETE:
            case PURPLE_CONCRETE:
            case BLUE_CONCRETE:
            case YELLOW_CONCRETE:
            case LIME_CONCRETE:
            case BLACK_CONCRETE:
            case LIGHT_GRAY_CONCRETE:
                return 1.8F;
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
            case BROWN_MUSHROOM_BLOCK:
            case VINE:
            case BROWN_BED:
            case MANGROVE_LEAVES:
            case RED_MUSHROOM_BLOCK:
            case DARK_OAK_LEAVES:
            case LIGHT_BLUE_BED:
            case BLACK_BED:
            case ACACIA_LEAVES:
            case LIGHT_GRAY_BED:
            case BLUE_BED:
            case ORANGE_BED:
            case GLOW_LICHEN:
            case GREEN_BED:
            case SCULK_VEIN:
            case CYAN_BED:
            case YELLOW_BED:
            case WHITE_BED:
            case JUNGLE_LEAVES:
            case CHERRY_LEAVES:
            case FLOWERING_AZALEA_LEAVES:
            case LIME_BED:
            case GRAY_BED:
            case OAK_LEAVES:
            case DAYLIGHT_DETECTOR:
            case SPRUCE_LEAVES:
            case MAGENTA_BED:
            case PINK_BED:
            case RED_BED:
            case SCULK:
            case SNOW_BLOCK:
            case BIRCH_LEAVES:
            case PURPLE_BED:
            case AZALEA_LEAVES:
            case MUSHROOM_STEM:
                return 0.2F;
            case STRIPPED_SPRUCE_LOG:
            case STRIPPED_WARPED_HYPHAE:
            case STRIPPED_CHERRY_LOG:
            case STRIPPED_CHERRY_WOOD:
            case CYAN_SHULKER_BOX:
            case STRIPPED_JUNGLE_LOG:
            case CHERRY_WOOD:
            case CRIMSON_STEM:
            case MAGENTA_SHULKER_BOX:
            case LIGHT_GRAY_SHULKER_BOX:
            case ACACIA_LOG:
            case POWDER_SNOW_CAULDRON:
            case STRIPPED_BAMBOO_BLOCK:
            case STRIPPED_CRIMSON_STEM:
            case STRIPPED_MANGROVE_WOOD:
            case WATER_CAULDRON:
            case CHERRY_LOG:
            case STRIPPED_SPRUCE_WOOD:
            case LIME_SHULKER_BOX:
            case MANGROVE_LOG:
            case RED_SHULKER_BOX:
            case STRIPPED_ACACIA_LOG:
            case OAK_WOOD:
            case SPRUCE_WOOD:
            case JUNGLE_WOOD:
            case STRIPPED_ACACIA_WOOD:
            case STRIPPED_BIRCH_LOG:
            case BONE_BLOCK:
            case STRIPPED_OAK_LOG:
            case JUNGLE_LOG:
            case ORANGE_SHULKER_BOX:
            case WARPED_HYPHAE:
            case STRIPPED_BIRCH_WOOD:
            case YELLOW_SHULKER_BOX:
            case SPRUCE_LOG:
            case SHULKER_BOX:
            case STRIPPED_DARK_OAK_WOOD:
            case ACACIA_WOOD:
            case WARPED_STEM:
            case MANGROVE_WOOD:
            case STRIPPED_DARK_OAK_LOG:
            case SOUL_CAMPFIRE:
            case GREEN_SHULKER_BOX:
            case STRIPPED_CRIMSON_HYPHAE:
            case STRIPPED_WARPED_STEM:
            case STRIPPED_MANGROVE_LOG:
            case WHITE_SHULKER_BOX:
            case LIGHT_BLUE_SHULKER_BOX:
            case OAK_LOG:
            case GRAY_SHULKER_BOX:
            case CAULDRON:
            case STRIPPED_JUNGLE_WOOD:
            case BIRCH_LOG:
            case PINK_SHULKER_BOX:
            case BAMBOO_BLOCK:
            case CAMPFIRE:
            case BIRCH_WOOD:
            case PURPLE_SHULKER_BOX:
            case BLUE_SHULKER_BOX:
            case STRIPPED_OAK_WOOD:
            case BLACK_SHULKER_BOX:
            case DARK_OAK_WOOD:
            case CRIMSON_HYPHAE:
            case BROWN_SHULKER_BOX:
            case DARK_OAK_LOG:
            case LAVA_CAULDRON:
                return 2.0F;
            case NETHERRACK:
            case CRIMSON_NYLIUM:
            case CHORUS_PLANT:
            case WARPED_NYLIUM:
            case CACTUS:
            case LADDER:
            case CHORUS_FLOWER:
                return 0.4F;
            case COBWEB:
                return 4.0F;
            case TRIAL_SPAWNER:
                return 50.0F;
            case INFESTED_CRACKED_STONE_BRICKS:
            case INFESTED_CHISELED_STONE_BRICKS:
            case INFESTED_STONE_BRICKS:
            case INFESTED_COBBLESTONE:
            case INFESTED_DEEPSLATE:
            case INFESTED_STONE:
            case CALCITE:
            case INFESTED_MOSSY_STONE_BRICKS:
                return 0.75F;
            case HOPPER:
                return 4.8F;
            case DIRT_PATH:
                return 0.65F;
            case YELLOW_TERRACOTTA:
            case PURPLE_TERRACOTTA:
            case RED_TERRACOTTA:
            case MAGENTA_TERRACOTTA:
            case LIGHT_BLUE_TERRACOTTA:
            case BROWN_TERRACOTTA:
            case BLACK_TERRACOTTA:
            case POLISHED_BASALT:
            case BASALT:
            case TERRACOTTA:
            case LIGHT_GRAY_TERRACOTTA:
            case WHITE_TERRACOTTA:
            case GRAY_TERRACOTTA:
            case CYAN_TERRACOTTA:
            case ORANGE_TERRACOTTA:
            case LIME_TERRACOTTA:
            case SMOOTH_BASALT:
            case PINK_TERRACOTTA:
            case BLUE_TERRACOTTA:
            case GREEN_TERRACOTTA:
                return 4.2F;
            case MOSSY_STONE_BRICK_SLAB:
            case STONE_BRICKS:
            case TUFF_BRICK_SLAB:
            case POLISHED_BLACKSTONE_SLAB:
            case WAXED_EXPOSED_CHISELED_COPPER:
            case SMOOTH_SANDSTONE_SLAB:
            case POLISHED_GRANITE_STAIRS:
            case SMOOTH_SANDSTONE_STAIRS:
            case EXPOSED_CUT_COPPER:
            case ANDESITE_WALL:
            case LIGHTNING_ROD:
            case STONE_BRICK_STAIRS:
            case MOSSY_COBBLESTONE_SLAB:
            case SMOOTH_QUARTZ_SLAB:
            case DARK_PRISMARINE_STAIRS:
            case SMOOTH_QUARTZ:
            case COBBLED_DEEPSLATE_STAIRS:
            case COBBLESTONE_WALL:
            case GRANITE_WALL:
            case CHISELED_TUFF_BRICKS:
            case CRACKED_STONE_BRICKS:
            case BRICK_SLAB:
            case OXIDIZED_COPPER_TRAPDOOR:
            case WAXED_WEATHERED_COPPER:
            case EXPOSED_COPPER_DOOR:
            case MOSSY_STONE_BRICK_STAIRS:
            case POLISHED_GRANITE_SLAB:
            case SMOOTH_RED_SANDSTONE_STAIRS:
            case WAXED_COPPER_TRAPDOOR:
            case COBBLESTONE:
            case POLISHED_DIORITE_STAIRS:
            case TUFF_WALL:
            case GILDED_BLACKSTONE:
            case WAXED_EXPOSED_COPPER:
            case DEAD_FIRE_CORAL_BLOCK:
            case WAXED_COPPER_BULB:
            case SMOOTH_RED_SANDSTONE_SLAB:
            case WAXED_OXIDIZED_COPPER:
            case NETHER_BRICK_WALL:
            case WAXED_WEATHERED_CUT_COPPER_STAIRS:
            case EXPOSED_COPPER_TRAPDOOR:
            case POLISHED_ANDESITE_STAIRS:
            case POLISHED_DEEPSLATE_STAIRS:
            case DEEPSLATE_BRICKS:
            case EMERALD_BLOCK:
            case WAXED_EXPOSED_COPPER_BULB:
            case PRISMARINE_STAIRS:
            case DEEPSLATE_TILE_STAIRS:
            case POLISHED_ANDESITE:
            case POLISHED_BLACKSTONE_STAIRS:
            case WAXED_WEATHERED_CHISELED_COPPER:
            case WAXED_CUT_COPPER_SLAB:
            case OXIDIZED_COPPER:
            case DIORITE:
            case SANDSTONE_SLAB:
            case POLISHED_ANDESITE_SLAB:
            case MOSSY_STONE_BRICK_WALL:
            case POLISHED_DEEPSLATE_WALL:
            case HORN_CORAL_BLOCK:
            case STONE_BRICK_WALL:
            case DEEPSLATE_BRICK_SLAB:
            case POLISHED_BLACKSTONE:
            case WAXED_EXPOSED_CUT_COPPER_STAIRS:
            case JUKEBOX:
            case TUBE_CORAL_BLOCK:
            case COPPER_BLOCK:
            case PRISMARINE_BRICK_SLAB:
            case WAXED_CUT_COPPER_STAIRS:
            case DARK_PRISMARINE:
            case ANDESITE_SLAB:
            case CRACKED_DEEPSLATE_BRICKS:
            case WAXED_EXPOSED_COPPER_GRATE:
            case COPPER_DOOR:
            case WAXED_COPPER_DOOR:
            case DEEPSLATE_TILE_WALL:
            case BUBBLE_CORAL_BLOCK:
            case GOLD_BLOCK:
            case PRISMARINE_WALL:
            case WEATHERED_COPPER_BULB:
            case DIORITE_STAIRS:
            case BLACKSTONE_WALL:
            case BRICK_STAIRS:
            case BRICK_WALL:
            case COBBLESTONE_SLAB:
            case WAXED_COPPER_GRATE:
            case MOSSY_COBBLESTONE_STAIRS:
            case OXIDIZED_CUT_COPPER_SLAB:
            case GRANITE:
            case WEATHERED_CHISELED_COPPER:
            case CHISELED_TUFF:
            case CRACKED_DEEPSLATE_TILES:
            case WAXED_WEATHERED_COPPER_DOOR:
            case DEEPSLATE_TILES:
            case MOSSY_STONE_BRICKS:
            case PRISMARINE:
            case DEAD_TUBE_CORAL_BLOCK:
            case TUFF_SLAB:
            case RAW_COPPER_BLOCK:
            case SMOOTH_SANDSTONE:
            case COBBLESTONE_STAIRS:
            case TUFF_BRICK_WALL:
            case DIAMOND_BLOCK:
            case POLISHED_DEEPSLATE_SLAB:
            case CUT_SANDSTONE_SLAB:
            case CHISELED_DEEPSLATE:
            case STONE_BRICK_SLAB:
            case CUT_COPPER_STAIRS:
            case EXPOSED_COPPER_GRATE:
            case TUFF_STAIRS:
            case WAXED_OXIDIZED_CUT_COPPER:
            case NETHER_BRICK_STAIRS:
            case SMOOTH_STONE_SLAB:
            case SMOOTH_STONE:
            case DIORITE_SLAB:
            case RED_NETHER_BRICKS:
            case WAXED_WEATHERED_COPPER_TRAPDOOR:
            case DEAD_BRAIN_CORAL_BLOCK:
            case WAXED_EXPOSED_COPPER_TRAPDOOR:
            case WAXED_COPPER_BLOCK:
            case NETHER_BRICK_SLAB:
            case POLISHED_TUFF:
            case POLISHED_BLACKSTONE_BRICK_SLAB:
            case IRON_BARS:
            case WAXED_OXIDIZED_CUT_COPPER_SLAB:
            case CHISELED_COPPER:
            case WEATHERED_COPPER_TRAPDOOR:
            case STONE:
            case DEEPSLATE_TILE_SLAB:
            case WAXED_CHISELED_COPPER:
            case WAXED_EXPOSED_COPPER_DOOR:
            case PRISMARINE_SLAB:
            case COPPER_GRATE:
            case SMOOTH_RED_SANDSTONE:
            case BLACKSTONE_STAIRS:
            case EXPOSED_COPPER:
            case WEATHERED_CUT_COPPER_STAIRS:
            case COBBLED_DEEPSLATE_SLAB:
            case ANDESITE_STAIRS:
            case WAXED_WEATHERED_COPPER_BULB:
            case POLISHED_TUFF_STAIRS:
            case BRICKS:
            case OXIDIZED_COPPER_BULB:
            case RED_NETHER_BRICK_WALL:
            case WAXED_OXIDIZED_COPPER_GRATE:
            case SMOOTH_QUARTZ_STAIRS:
            case POLISHED_GRANITE:
            case BLACKSTONE_SLAB:
            case RAW_IRON_BLOCK:
            case DEAD_BUBBLE_CORAL_BLOCK:
            case TUFF:
            case POLISHED_BLACKSTONE_BRICK_STAIRS:
            case DEEPSLATE:
            case STONE_STAIRS:
            case GRANITE_STAIRS:
            case POLISHED_TUFF_SLAB:
            case COPPER_TRAPDOOR:
            case TUFF_BRICKS:
            case POLISHED_BLACKSTONE_BRICKS:
            case PURPUR_STAIRS:
            case EXPOSED_CUT_COPPER_SLAB:
            case DIORITE_WALL:
            case OXIDIZED_CUT_COPPER:
            case POLISHED_DEEPSLATE:
            case IRON_BLOCK:
            case EXPOSED_CUT_COPPER_STAIRS:
            case PETRIFIED_OAK_SLAB:
            case POLISHED_BLACKSTONE_BRICK_WALL:
            case WAXED_OXIDIZED_CUT_COPPER_STAIRS:
            case DEEPSLATE_BRICK_STAIRS:
            case CHAIN:
            case WAXED_OXIDIZED_COPPER_BULB:
            case CHISELED_NETHER_BRICKS:
            case COAL_BLOCK:
            case PRISMARINE_BRICKS:
            case WEATHERED_CUT_COPPER:
            case PURPUR_PILLAR:
            case WEATHERED_COPPER_DOOR:
            case WEATHERED_CUT_COPPER_SLAB:
            case TUFF_BRICK_STAIRS:
            case CHISELED_STONE_BRICKS:
            case RAW_GOLD_BLOCK:
            case PURPUR_BLOCK:
            case POLISHED_BLACKSTONE_WALL:
            case WAXED_CUT_COPPER:
            case REDSTONE_BLOCK:
            case COBBLED_DEEPSLATE_WALL:
            case CHISELED_POLISHED_BLACKSTONE:
            case WAXED_WEATHERED_CUT_COPPER:
            case EXPOSED_CHISELED_COPPER:
            case WAXED_OXIDIZED_CHISELED_COPPER:
            case QUARTZ_SLAB:
            case CUT_COPPER_SLAB:
            case OXIDIZED_COPPER_GRATE:
            case FIRE_CORAL_BLOCK:
            case CUT_RED_SANDSTONE_SLAB:
            case BLACKSTONE:
            case RED_NETHER_BRICK_SLAB:
            case COBBLED_DEEPSLATE:
            case WEATHERED_COPPER_GRATE:
            case POLISHED_DIORITE:
            case MOSSY_COBBLESTONE:
            case POLISHED_TUFF_WALL:
            case MOSSY_COBBLESTONE_WALL:
            case GRINDSTONE:
            case OXIDIZED_COPPER_DOOR:
            case DEAD_HORN_CORAL_BLOCK:
            case WAXED_WEATHERED_CUT_COPPER_SLAB:
            case CUT_COPPER:
            case RED_SANDSTONE_SLAB:
            case CRACKED_POLISHED_BLACKSTONE_BRICKS:
            case WEATHERED_COPPER:
            case GRANITE_SLAB:
            case CRACKED_NETHER_BRICKS:
            case WAXED_WEATHERED_COPPER_GRATE:
            case WAXED_OXIDIZED_COPPER_DOOR:
            case EXPOSED_COPPER_BULB:
            case WAXED_OXIDIZED_COPPER_TRAPDOOR:
            case PURPUR_SLAB:
            case NETHER_BRICKS:
            case DEEPSLATE_BRICK_WALL:
            case DARK_PRISMARINE_SLAB:
            case WAXED_EXPOSED_CUT_COPPER_SLAB:
            case WAXED_EXPOSED_CUT_COPPER:
            case PRISMARINE_BRICK_STAIRS:
            case STONE_SLAB:
            case COPPER_BULB:
            case POLISHED_DIORITE_SLAB:
            case OXIDIZED_CUT_COPPER_STAIRS:
            case ANDESITE:
            case RED_NETHER_BRICK_STAIRS:
            case OXIDIZED_CHISELED_COPPER:
            case NETHER_BRICK_FENCE:
            case BRAIN_CORAL_BLOCK:
                return 6.0F;
            case SPONGE:
            case FARMLAND:
            case BEEHIVE:
            case COMPOSTER:
            case GRASS_BLOCK:
            case MYCELIUM:
            case WET_SPONGE:
            case GRAVEL:
            case CLAY:
            case HONEYCOMB_BLOCK:
                return 0.6F;
            case MEDIUM_AMETHYST_BUD:
            case CALIBRATED_SCULK_SENSOR:
            case STICKY_PISTON:
            case AMETHYST_CLUSTER:
            case CHISELED_BOOKSHELF:
            case SMALL_AMETHYST_BUD:
            case BUDDING_AMETHYST:
            case PISTON:
            case SCULK_SENSOR:
            case PISTON_HEAD:
            case BOOKSHELF:
            case AMETHYST_BLOCK:
            case LARGE_AMETHYST_BUD:
                return 1.5F;
            case RED_SANDSTONE_STAIRS:
            case SANDSTONE_STAIRS:
            case PINK_WOOL:
            case GRAY_WOOL:
            case SANDSTONE:
            case LIGHT_GRAY_WOOL:
            case PURPLE_WOOL:
            case QUARTZ_PILLAR:
            case RED_SANDSTONE_WALL:
            case CHISELED_SANDSTONE:
            case GREEN_WOOL:
            case RED_WOOL:
            case BLACK_WOOL:
            case QUARTZ_STAIRS:
            case CUT_RED_SANDSTONE:
            case SANDSTONE_WALL:
            case QUARTZ_BLOCK:
            case CHISELED_RED_SANDSTONE:
            case QUARTZ_BRICKS:
            case BLUE_WOOL:
            case RED_SANDSTONE:
            case MAGENTA_WOOL:
            case BROWN_WOOL:
            case CHISELED_QUARTZ_BLOCK:
            case NOTE_BLOCK:
            case CUT_SANDSTONE:
            case CYAN_WOOL:
            case ORANGE_WOOL:
            case LIME_WOOL:
            case WHITE_WOOL:
            case LIGHT_BLUE_WOOL:
            case YELLOW_WOOL:
                return 0.8F;
            case FURNACE:
            case LANTERN:
            case BLAST_FURNACE:
            case STONECUTTER:
            case SMOKER:
            case DISPENSER:
            case DROPPER:
            case LODESTONE:
            case CRAFTER:
            case SOUL_LANTERN:
                return 3.5F;
            case DAMAGED_ANVIL:
            case OBSIDIAN:
            case ANVIL:
            case NETHERITE_BLOCK:
            case RESPAWN_ANCHOR:
            case CHIPPED_ANVIL:
            case ANCIENT_DEBRIS:
            case REINFORCED_DEEPSLATE:
            case ENCHANTING_TABLE:
            case CRYING_OBSIDIAN:
                return 1200.0F;
            case BLUE_ICE:
                return 2.8F;
            case SUSPICIOUS_SAND:
            case SUSPICIOUS_GRAVEL:
            case POWDER_SNOW:
                return 0.25F;
            case PIGLIN_HEAD:
            case JUNGLE_WALL_HANGING_SIGN:
            case BIRCH_WALL_HANGING_SIGN:
            case SKELETON_SKULL:
            case MAGENTA_BANNER:
            case GREEN_WALL_BANNER:
            case DRAGON_HEAD:
            case SPRUCE_WALL_HANGING_SIGN:
            case PINK_BANNER:
            case CRIMSON_HANGING_SIGN:
            case CRIMSON_WALL_SIGN:
            case CHERRY_HANGING_SIGN:
            case BIRCH_SIGN:
            case SPRUCE_HANGING_SIGN:
            case OAK_WALL_HANGING_SIGN:
            case BAMBOO_SAPLING:
            case BROWN_BANNER:
            case PUMPKIN:
            case RED_BANNER:
            case NETHER_WART_BLOCK:
            case ZOMBIE_HEAD:
            case BLUE_WALL_BANNER:
            case LIGHT_GRAY_BANNER:
            case GREEN_BANNER:
            case SPRUCE_SIGN:
            case LIME_BANNER:
            case PLAYER_HEAD:
            case WARPED_HANGING_SIGN:
            case BLUE_BANNER:
            case DRAGON_WALL_HEAD:
            case DARK_OAK_SIGN:
            case BAMBOO_HANGING_SIGN:
            case LIGHT_GRAY_WALL_BANNER:
            case WITHER_SKELETON_SKULL:
            case MAGENTA_WALL_BANNER:
            case BAMBOO_SIGN:
            case DARK_OAK_HANGING_SIGN:
            case BROWN_WALL_BANNER:
            case MANGROVE_WALL_HANGING_SIGN:
            case ORANGE_BANNER:
            case RED_WALL_BANNER:
            case SPRUCE_WALL_SIGN:
            case JUNGLE_HANGING_SIGN:
            case DARK_OAK_WALL_HANGING_SIGN:
            case SKELETON_WALL_SKULL:
            case JUNGLE_WALL_SIGN:
            case ZOMBIE_WALL_HEAD:
            case DRIPSTONE_BLOCK:
            case CREEPER_HEAD:
            case ACACIA_HANGING_SIGN:
            case ACACIA_WALL_SIGN:
            case BLACK_BANNER:
            case WARPED_WART_BLOCK:
            case BIRCH_WALL_SIGN:
            case JACK_O_LANTERN:
            case PURPLE_WALL_BANNER:
            case PLAYER_WALL_HEAD:
            case BLACK_WALL_BANNER:
            case WITHER_SKELETON_WALL_SKULL:
            case BIRCH_HANGING_SIGN:
            case BAMBOO_WALL_HANGING_SIGN:
            case PINK_WALL_BANNER:
            case BAMBOO:
            case WARPED_WALL_HANGING_SIGN:
            case OAK_WALL_SIGN:
            case WHITE_WALL_BANNER:
            case ACACIA_WALL_HANGING_SIGN:
            case MANGROVE_SIGN:
            case WHITE_BANNER:
            case GRAY_WALL_BANNER:
            case CHERRY_SIGN:
            case JUNGLE_SIGN:
            case SHROOMLIGHT:
            case CARVED_PUMPKIN:
            case PIGLIN_WALL_HEAD:
            case CREEPER_WALL_HEAD:
            case BAMBOO_WALL_SIGN:
            case OAK_HANGING_SIGN:
            case LIGHT_BLUE_BANNER:
            case CHERRY_WALL_SIGN:
            case CYAN_WALL_BANNER:
            case ACACIA_SIGN:
            case OAK_SIGN:
            case MELON:
            case YELLOW_WALL_BANNER:
            case ORANGE_WALL_BANNER:
            case DARK_OAK_WALL_SIGN:
            case MANGROVE_HANGING_SIGN:
            case YELLOW_BANNER:
            case CRIMSON_WALL_HANGING_SIGN:
            case PURPLE_BANNER:
            case GRAY_BANNER:
            case CYAN_BANNER:
            case LIGHT_BLUE_WALL_BANNER:
            case CRIMSON_SIGN:
            case LIME_WALL_BANNER:
            case WARPED_SIGN:
            case MANGROVE_WALL_SIGN:
            case CHERRY_WALL_HANGING_SIGN:
            case WARPED_WALL_SIGN:
                return 1.0F;
            case BROWN_CARPET:
            case CANDLE:
            case BROWN_CANDLE:
            case WHITE_CARPET:
            case WHITE_CANDLE:
            case MOSS_CARPET:
            case PURPLE_CARPET:
            case ORANGE_CARPET:
            case PINK_CARPET:
            case ORANGE_CANDLE:
            case PINK_CANDLE:
            case MOSS_BLOCK:
            case PURPLE_CANDLE:
            case BLACK_CANDLE:
            case LIGHT_GRAY_CARPET:
            case BLACK_CARPET:
            case RED_CARPET:
            case RED_CANDLE:
            case LIGHT_BLUE_CARPET:
            case LIGHT_BLUE_CANDLE:
            case BIG_DRIPLEAF:
            case BIG_DRIPLEAF_STEM:
            case MAGENTA_CARPET:
            case MAGENTA_CANDLE:
            case SNOW:
            case LIGHT_GRAY_CANDLE:
            case LIME_CARPET:
            case LIME_CANDLE:
            case GRAY_CANDLE:
            case YELLOW_CARPET:
            case CYAN_CANDLE:
            case GREEN_CANDLE:
            case BLUE_CANDLE:
            case CYAN_CARPET:
            case GREEN_CARPET:
            case YELLOW_CANDLE:
            case GRAY_CARPET:
            case BLUE_CARPET:
                return 0.1F;
            case MANGROVE_TRAPDOOR:
            case MUD_BRICKS:
            case CRIMSON_STAIRS:
            case CHERRY_TRAPDOOR:
            case PACKED_MUD:
            case SPRUCE_PLANKS:
            case MUD_BRICK_STAIRS:
            case CHERRY_FENCE:
            case SPRUCE_DOOR:
            case NETHER_GOLD_ORE:
            case BAMBOO_MOSAIC_SLAB:
            case ACACIA_SLAB:
            case MANGROVE_SLAB:
            case WARPED_PLANKS:
            case CHERRY_SLAB:
            case SPRUCE_FENCE:
            case LAPIS_BLOCK:
            case JUNGLE_SLAB:
            case MANGROVE_STAIRS:
            case BIRCH_FENCE:
            case OAK_DOOR:
            case MANGROVE_FENCE:
            case BAMBOO_FENCE:
            case JUNGLE_PLANKS:
            case BIRCH_STAIRS:
            case OAK_STAIRS:
            case DARK_OAK_DOOR:
            case BIRCH_TRAPDOOR:
            case REDSTONE_ORE:
            case OAK_SLAB:
            case POINTED_DRIPSTONE:
            case CHERRY_PLANKS:
            case EMERALD_ORE:
            case COAL_ORE:
            case CHERRY_FENCE_GATE:
            case MUD_BRICK_WALL:
            case OAK_FENCE_GATE:
            case SCULK_CATALYST:
            case SCULK_SHRIEKER:
            case WARPED_FENCE_GATE:
            case SPRUCE_STAIRS:
            case DEEPSLATE_REDSTONE_ORE:
            case BAMBOO_MOSAIC_STAIRS:
            case BAMBOO_DOOR:
            case WARPED_SLAB:
            case CRIMSON_SLAB:
            case DEEPSLATE_IRON_ORE:
            case GOLD_ORE:
            case ACACIA_STAIRS:
            case BAMBOO_PLANKS:
            case ACACIA_TRAPDOOR:
            case NETHER_QUARTZ_ORE:
            case COPPER_ORE:
            case BAMBOO_TRAPDOOR:
            case WARPED_STAIRS:
            case MANGROVE_PLANKS:
            case WARPED_TRAPDOOR:
            case CRIMSON_PLANKS:
            case DARK_OAK_FENCE_GATE:
            case DEEPSLATE_LAPIS_ORE:
            case ACACIA_FENCE_GATE:
            case BIRCH_SLAB:
            case CHERRY_DOOR:
            case BIRCH_FENCE_GATE:
            case DARK_OAK_TRAPDOOR:
            case CONDUIT:
            case ACACIA_FENCE:
            case DARK_OAK_FENCE:
            case JUNGLE_FENCE:
            case WARPED_FENCE:
            case DARK_OAK_STAIRS:
            case ACACIA_DOOR:
            case JUNGLE_STAIRS:
            case CRIMSON_FENCE_GATE:
            case OBSERVER:
            case DEEPSLATE_DIAMOND_ORE:
            case COCOA:
            case DARK_OAK_PLANKS:
            case SPRUCE_SLAB:
            case BAMBOO_MOSAIC:
            case SPRUCE_TRAPDOOR:
            case CHERRY_STAIRS:
            case OAK_TRAPDOOR:
            case MANGROVE_FENCE_GATE:
            case DARK_OAK_SLAB:
            case SPRUCE_FENCE_GATE:
            case BEACON:
            case JUNGLE_DOOR:
            case OAK_PLANKS:
            case LAPIS_ORE:
            case DEEPSLATE_COAL_ORE:
            case WARPED_DOOR:
            case OAK_FENCE:
            case BAMBOO_SLAB:
            case MANGROVE_DOOR:
            case CRIMSON_DOOR:
            case BIRCH_PLANKS:
            case ACACIA_PLANKS:
            case DIAMOND_ORE:
            case DEEPSLATE_COPPER_ORE:
            case DEEPSLATE_EMERALD_ORE:
            case MUD_BRICK_SLAB:
            case IRON_ORE:
            case JUNGLE_FENCE_GATE:
            case BIRCH_DOOR:
            case BAMBOO_STAIRS:
            case JUNGLE_TRAPDOOR:
            case CRIMSON_FENCE:
            case CRIMSON_TRAPDOOR:
            case BAMBOO_FENCE_GATE:
            case DEEPSLATE_GOLD_ORE:
                return 3.0F;
            case LIGHT_GRAY_STAINED_GLASS:
            case BLACK_STAINED_GLASS:
            case WHITE_STAINED_GLASS_PANE:
            case LIME_STAINED_GLASS:
            case VERDANT_FROGLIGHT:
            case BROWN_STAINED_GLASS:
            case ORANGE_STAINED_GLASS:
            case PURPLE_STAINED_GLASS:
            case PURPLE_STAINED_GLASS_PANE:
            case SEA_LANTERN:
            case GLASS:
            case CYAN_STAINED_GLASS_PANE:
            case CYAN_STAINED_GLASS:
            case PINK_STAINED_GLASS_PANE:
            case LIGHT_BLUE_STAINED_GLASS_PANE:
            case GLOWSTONE:
            case GREEN_STAINED_GLASS_PANE:
            case MAGENTA_STAINED_GLASS:
            case YELLOW_STAINED_GLASS_PANE:
            case REDSTONE_LAMP:
            case RED_STAINED_GLASS:
            case LIME_STAINED_GLASS_PANE:
            case TINTED_GLASS:
            case PEARLESCENT_FROGLIGHT:
            case BROWN_STAINED_GLASS_PANE:
            case YELLOW_STAINED_GLASS:
            case GRAY_STAINED_GLASS:
            case GREEN_STAINED_GLASS:
            case GRAY_STAINED_GLASS_PANE:
            case GLASS_PANE:
            case PINK_STAINED_GLASS:
            case ORANGE_STAINED_GLASS_PANE:
            case BEE_NEST:
            case BLUE_STAINED_GLASS:
            case BLUE_STAINED_GLASS_PANE:
            case RED_STAINED_GLASS_PANE:
            case MAGENTA_STAINED_GLASS_PANE:
            case LIGHT_GRAY_STAINED_GLASS_PANE:
            case OCHRE_FROGLIGHT:
            case WHITE_STAINED_GLASS:
            case BLACK_STAINED_GLASS_PANE:
            case LIGHT_BLUE_STAINED_GLASS:
                return 0.3F;
            case WATER:
            case LAVA:
                return 100.0F;
            case ENDER_CHEST:
                return 600.0F;
            case END_PORTAL_FRAME:
            case END_GATEWAY:
            case BEDROCK:
            case END_PORTAL:
            case REPEATING_COMMAND_BLOCK:
            case COMMAND_BLOCK:
            case STRUCTURE_BLOCK:
            case JIGSAW:
            case CHAIN_COMMAND_BLOCK:
                return 3600000.0F;
            case END_STONE_BRICKS:
            case END_STONE_BRICK_STAIRS:
            case END_STONE:
            case DRAGON_EGG:
            case END_STONE_BRICK_SLAB:
            case END_STONE_BRICK_WALL:
                return 9.0F;
            case IRON_DOOR:
            case IRON_TRAPDOOR:
            case BELL:
            case SPAWNER:
                return 5.0F;
            case BARRIER:
            case LIGHT:
                return 3600000.8F;
            case DARK_OAK_BUTTON:
            case TARGET:
            case GREEN_CONCRETE_POWDER:
            case WHITE_CANDLE_CAKE:
            case CHERRY_PRESSURE_PLATE:
            case ACACIA_PRESSURE_PLATE:
            case RED_SAND:
            case FROSTED_ICE:
            case BLACK_CANDLE_CAKE:
            case PODZOL:
            case BROWN_CANDLE_CAKE:
            case BLACK_CONCRETE_POWDER:
            case SPRUCE_PRESSURE_PLATE:
            case JUNGLE_PRESSURE_PLATE:
            case DARK_OAK_PRESSURE_PLATE:
            case BIRCH_PRESSURE_PLATE:
            case LIME_CONCRETE_POWDER:
            case POLISHED_BLACKSTONE_PRESSURE_PLATE:
            case MAGENTA_CONCRETE_POWDER:
            case HEAVY_WEIGHTED_PRESSURE_PLATE:
            case PINK_CONCRETE_POWDER:
            case CHERRY_BUTTON:
            case MUD:
            case LIGHT_BLUE_CONCRETE_POWDER:
            case OAK_BUTTON:
            case SNIFFER_EGG:
            case BREWING_STAND:
            case SAND:
            case LEVER:
            case STONE_PRESSURE_PLATE:
            case DIRT:
            case CAKE:
            case YELLOW_CANDLE_CAKE:
            case LIGHT_BLUE_CANDLE_CAKE:
            case LIME_CANDLE_CAKE:
            case WHITE_CONCRETE_POWDER:
            case HAY_BLOCK:
            case ORANGE_CONCRETE_POWDER:
            case CANDLE_CAKE:
            case WARPED_BUTTON:
            case ORANGE_CANDLE_CAKE:
            case CYAN_CANDLE_CAKE:
            case OAK_PRESSURE_PLATE:
            case RED_CANDLE_CAKE:
            case JUNGLE_BUTTON:
            case YELLOW_CONCRETE_POWDER:
            case TURTLE_EGG:
            case PINK_CANDLE_CAKE:
            case MANGROVE_BUTTON:
            case BAMBOO_BUTTON:
            case GRAY_CONCRETE_POWDER:
            case MAGMA_BLOCK:
            case WARPED_PRESSURE_PLATE:
            case BROWN_CONCRETE_POWDER:
            case BAMBOO_PRESSURE_PLATE:
            case STONE_BUTTON:
            case MAGENTA_CANDLE_CAKE:
            case COARSE_DIRT:
            case BLUE_CONCRETE_POWDER:
            case CRIMSON_BUTTON:
            case ACACIA_BUTTON:
            case CRIMSON_PRESSURE_PLATE:
            case ROOTED_DIRT:
            case LIGHT_GRAY_CANDLE_CAKE:
            case PURPLE_CANDLE_CAKE:
            case SOUL_SAND:
            case PURPLE_CONCRETE_POWDER:
            case CYAN_CONCRETE_POWDER:
            case ICE:
            case GRAY_CANDLE_CAKE:
            case MANGROVE_PRESSURE_PLATE:
            case BIRCH_BUTTON:
            case SPRUCE_BUTTON:
            case LIGHT_GRAY_CONCRETE_POWDER:
            case RED_CONCRETE_POWDER:
            case POLISHED_BLACKSTONE_BUTTON:
            case LIGHT_WEIGHTED_PRESSURE_PLATE:
            case BLUE_CANDLE_CAKE:
            case PACKED_ICE:
            case SOUL_SOIL:
            case GREEN_CANDLE_CAKE:
                return 0.5F;
            case GRAY_GLAZED_TERRACOTTA:
            case MAGENTA_GLAZED_TERRACOTTA:
            case LIGHT_BLUE_GLAZED_TERRACOTTA:
            case LIME_GLAZED_TERRACOTTA:
            case PURPLE_GLAZED_TERRACOTTA:
            case WHITE_GLAZED_TERRACOTTA:
            case BLUE_GLAZED_TERRACOTTA:
            case ORANGE_GLAZED_TERRACOTTA:
            case GREEN_GLAZED_TERRACOTTA:
            case LIGHT_GRAY_GLAZED_TERRACOTTA:
            case PINK_GLAZED_TERRACOTTA:
            case BLACK_GLAZED_TERRACOTTA:
            case BROWN_GLAZED_TERRACOTTA:
            case RED_GLAZED_TERRACOTTA:
            case YELLOW_GLAZED_TERRACOTTA:
            case CYAN_GLAZED_TERRACOTTA:
                return 1.4F;
            case CHEST:
            case LOOM:
            case CRAFTING_TABLE:
            case TRAPPED_CHEST:
            case LECTERN:
            case DRIED_KELP_BLOCK:
            case BARREL:
            case CARTOGRAPHY_TABLE:
            case SMITHING_TABLE:
            case FLETCHING_TABLE:
                return 2.5F;
            case DETECTOR_RAIL:
            case ACTIVATOR_RAIL:
            case POWERED_RAIL:
            case RAIL:
            case MANGROVE_ROOTS:
            case MUDDY_MANGROVE_ROOTS:
                return 0.7F;
            case GRAY_CONCRETE:
            case BROWN_CONCRETE:
            case CYAN_CONCRETE:
            case RED_CONCRETE:
            case LIGHT_BLUE_CONCRETE:
            case GREEN_CONCRETE:
            case MAGENTA_CONCRETE:
            case WHITE_CONCRETE:
            case PINK_CONCRETE:
            case ORANGE_CONCRETE:
            case PURPLE_CONCRETE:
            case BLUE_CONCRETE:
            case YELLOW_CONCRETE:
            case LIME_CONCRETE:
            case BLACK_CONCRETE:
            case LIGHT_GRAY_CONCRETE:
                return 1.8F;
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
            case BLUE_ICE:
                return 0.989F;
            case FROSTED_ICE:
            case ICE:
            case PACKED_ICE:
                return 0.98F;
            case SLIME_BLOCK:
                return 0.8F;
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
            case WATER_BUCKET:
            case MILK_BUCKET:
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
            case CHAINMAIL_BOOTS:
            case IRON_BOOTS:
            case NETHERITE_BOOTS:
            case DIAMOND_BOOTS:
            case GOLDEN_BOOTS:
            case LEATHER_BOOTS:
                return EquipmentSlot.FEET;
            case NETHERITE_HELMET:
            case TURTLE_HELMET:
            case DIAMOND_HELMET:
            case CREEPER_HEAD:
            case LEATHER_HELMET:
            case PIGLIN_HEAD:
            case ZOMBIE_HEAD:
            case CHAINMAIL_HELMET:
            case SKELETON_SKULL:
            case IRON_HELMET:
            case DRAGON_HEAD:
            case WITHER_SKELETON_SKULL:
            case PLAYER_HEAD:
            case GOLDEN_HELMET:
            case CARVED_PUMPKIN:
                return EquipmentSlot.HEAD;
            case SHIELD:
                return EquipmentSlot.OFF_HAND;
            case LEATHER_LEGGINGS:
            case CHAINMAIL_LEGGINGS:
            case NETHERITE_LEGGINGS:
            case GOLDEN_LEGGINGS:
            case DIAMOND_LEGGINGS:
            case IRON_LEGGINGS:
                return EquipmentSlot.LEGS;
            case DIAMOND_CHESTPLATE:
            case IRON_CHESTPLATE:
            case ELYTRA:
            case CHAINMAIL_CHESTPLATE:
            case GOLDEN_CHESTPLATE:
            case NETHERITE_CHESTPLATE:
            case LEATHER_CHESTPLATE:
                return EquipmentSlot.CHEST;
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
