package io.papermc.generator.utils;

import com.google.common.collect.ImmutableMap;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import io.papermc.generator.rewriter.utils.ClassHelper;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.AbstractSkullBlock;
import net.minecraft.world.level.block.BaseCoralWallFanBlock;
import net.minecraft.world.level.block.BigDripleafStemBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CommandBlock;
import net.minecraft.world.level.block.NoteBlock;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.StructureBlock;
import net.minecraft.world.level.block.piston.PistonBaseBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Property;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.AnaloguePowerable;
import org.bukkit.block.data.Bisected;
import org.bukkit.block.data.Brushable;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.FaceAttachable;
import org.bukkit.block.data.Hangable;
import org.bukkit.block.data.Hatchable;
import org.bukkit.block.data.Levelled;
import org.bukkit.block.data.Lightable;
import org.bukkit.block.data.MultipleFacing;
import org.bukkit.block.data.Openable;
import org.bukkit.block.data.Orientable;
import org.bukkit.block.data.Powerable;
import org.bukkit.block.data.Rail;
import org.bukkit.block.data.Rotatable;
import org.bukkit.block.data.Snowable;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.block.data.type.CoralWallFan;
import org.bukkit.block.data.type.Dripleaf;
import org.bukkit.block.data.type.Furnace;
import org.bukkit.block.data.type.Piston;
import org.bukkit.block.data.type.RedstoneRail;
import org.bukkit.block.data.type.Sapling;
import org.bukkit.block.data.type.Stairs;
import org.bukkit.block.data.type.Switch;
import org.bukkit.block.data.type.TechnicalPiston;
import org.jetbrains.annotations.Nullable;

public final class BlockStateMapping {

    public record BlockData(String impl, @Nullable Class<? extends org.bukkit.block.data.BlockData> api, Collection<Property<?>> properties, Set<Class<?>> extensions) {
    }

    private static final Map<String, String> API_RENAMES = ImmutableMap.<String, String>builder()
        .put("SnowLayer", "Snow")
        .put("StainedGlassPane", "GlassPane")
        .put("CeilingHangingSign", "HangingSign")
        .put("RedStoneWire", "RedstoneWire")
        .put("TripWire", "Tripwire")
        .put("TripWireHook", "TripwireHook")
        .put("Tnt", "TNT")
        .put("BambooStalk", "Bamboo")
        .put("Farm", "Farmland")
        .put("ChiseledBookShelf", "ChiseledBookshelf")
        .put("StandingSign", "Sign")
        .build();

    private static final Set<Class<?>> BLOCK_SUFFIX_INTENDED = Set.of(
        CommandBlock.class,
        StructureBlock.class,
        NoteBlock.class
    );

    private static final Map<String, String> IMPL_RENAMES = Map.of(
        "CraftWall", "CraftCobbleWall",
        "CraftPlayerWallHead,", "CraftSkullPlayerWall",
        "CraftPlayerHead,", "CraftSkullPlayer",
        "CraftCarrot,", "CraftCarrots",
        "CraftTallFlower,", "CraftTallPlantFlower",
        "CraftFarmland", "CraftSoil",
        "CraftChiseledBookshelf", "CraftChiseledBookShelf",
        "CraftSign", "CraftFloorSign"
    );

    public static final Map<Class<?>, BlockData> MAPPING;
    static {
        Map<Class<?>, BlockData> map = new HashMap<>();
        Map<Class<?>, Collection<Property<?>>> specialBlocks = new HashMap<>();
        for (Block block : BuiltInRegistries.BLOCK) {
            if (!block.getStateDefinition().getProperties().isEmpty()) {
                specialBlocks.put(block.getClass(), block.getStateDefinition().getProperties());
            }
        }

        for (Map.Entry<Class<?>, Collection<Property<?>>> entry : specialBlocks.entrySet()) {
            Class<?> specialBlock = entry.getKey();
            Set<Class<?>> extensions = ClassHelper.getInterfacesUntil(specialBlock, Block.class, new LinkedHashSet<>());

            String apiName = formatApiName(specialBlock);
            Class<? extends org.bukkit.block.data.BlockData> api = classOr("org.bukkit.block.data.type." + apiName, null);
            if (api == null) {
                Class<?> directParent = specialBlock.getSuperclass();
                if (specialBlocks.containsKey(directParent)) {
                    // if the properties are the same then always consider the parent
                    // check deeper in the tree?
                    if (specialBlocks.get(directParent).equals(entry.getValue())) {
                        String parentApiName = formatApiName(directParent);
                        api = classOr("org.bukkit.block.data.type." + parentApiName, api);
                    }
                }
            }
            if (api == null) { // todo remove this part
                if (BaseCoralWallFanBlock.class.isAssignableFrom(specialBlock)) {
                    api = CoralWallFan.class; // annoying >.< for dead coral
                } else if (AbstractFurnaceBlock.class.isAssignableFrom(specialBlock)) {
                    api = Furnace.class; // for smoker and blast furnace
                } else if (PistonBaseBlock.class.isAssignableFrom(specialBlock)) {
                    api = Piston.class;
                } else if (specialBlock == BigDripleafStemBlock.class) {
                    api = Dripleaf.class;
                }
            }

            String implName = String.format("Craft%s", apiName);
            map.put(specialBlock, new BlockData(IMPL_RENAMES.getOrDefault(implName, implName), api, entry.getValue(), extensions));
        }
        MAPPING = Map.copyOf(map);
    }

    private static final Map<String, Class<? extends org.bukkit.block.data.BlockData>> NAME_TO_DATA = Map.of(
        BlockStateProperties.AGE_1.getName(), Ageable.class,
        BlockStateProperties.LEVEL.getName(), Levelled.class
    );

    private static final Map<Property<?>, Class<? extends org.bukkit.block.data.BlockData>> PROPERTY_TO_DATA = ImmutableMap.<Property<?>, Class<? extends org.bukkit.block.data.BlockData>>builder()
        // levelled and ageable are done using the property name
        .put(BlockStateProperties.POWER, AnaloguePowerable.class)
        .put(BlockStateProperties.HALF, Bisected.class)
        .put(BlockStateProperties.DOUBLE_BLOCK_HALF, Bisected.class)
        .put(BlockStateProperties.DUSTED, Brushable.class)
        .put(BlockStateProperties.FACING, Directional.class)
        .put(BlockStateProperties.HORIZONTAL_FACING, Directional.class) // todo check impl restriction
        .put(BlockStateProperties.ATTACH_FACE, FaceAttachable.class)
        .put(BlockStateProperties.HANGING, Hangable.class)
        .put(BlockStateProperties.HATCH, Hatchable.class)
        .put(BlockStateProperties.LIT, Lightable.class)
        // multiple facing is done by matching two or more pipe block properties
        .put(BlockStateProperties.OPEN, Openable.class)
        .put(BlockStateProperties.HORIZONTAL_AXIS, Orientable.class) // todo check impl restriction
        .put(BlockStateProperties.AXIS, Orientable.class)
        .put(BlockStateProperties.POWERED, Powerable.class)
        .put(BlockStateProperties.RAIL_SHAPE, Rail.class)
        .put(BlockStateProperties.RAIL_SHAPE_STRAIGHT, Rail.class)
        .put(BlockStateProperties.ROTATION_16, Rotatable.class)
        .put(BlockStateProperties.SNOWY, Snowable.class)
        .put(BlockStateProperties.WATERLOGGED, Waterlogged.class)
        .build();

    private static final Map<Property<?>, Class<? extends org.bukkit.block.data.BlockData>> MAIN_PROPERTY_TO_DATA = Map.of(
        BlockStateProperties.PISTON_TYPE, TechnicalPiston.class,
        BlockStateProperties.STAGE, Sapling.class,
        BlockStateProperties.STAIRS_SHAPE, Stairs.class
    );

    /*
    TODO:
    org.bukkit.block.data.type.Switch methods = FaceAttachable and should redirect to those in the api directly without generated impl
    clear paper api list now generated/remove patches not needed anymore
    remove scrap of old spigot tooling (archetype)
     */
    @Nullable
    public static Class<?> getBestSuitedApiClass(Class<?> block) {
        if (!MAPPING.containsKey(block)) {
            return null;
        }

        BlockData data = MAPPING.get(block);
        if (data.api() != null) {
            return data.api();
        }

        int pipeProps = 0;
        Set<Class<? extends org.bukkit.block.data.BlockData>> extensions = new LinkedHashSet<>();
        for (Property<?> property : data.properties()) {
            if (property == BlockStateProperties.POWERED && AbstractSkullBlock.class.isAssignableFrom(block)) {
                continue; // let's skip that like spigot do for now TODO new api and remove this hack
            }

            if (MAIN_PROPERTY_TO_DATA.containsKey(property)) {
                return MAIN_PROPERTY_TO_DATA.get(property);
            }

            if (NAME_TO_DATA.containsKey(property.getName())) {
                extensions.add(NAME_TO_DATA.get(property.getName()));
                continue;
            }

            if (PROPERTY_TO_DATA.containsKey(property)) {
                extensions.add(PROPERTY_TO_DATA.get(property));
                continue;
            }

            if (PipeBlock.PROPERTY_BY_DIRECTION.containsValue(property)) {
                pipeProps++;
            }
        }

        if (!extensions.isEmpty()) {
            if (isExactly(extensions, Switch.class)) {
                return Switch.class;
            }
            if (isExactly(extensions, RedstoneRail.class)) {
                return RedstoneRail.class;
            }

            return extensions.iterator().next();
        }

        if (pipeProps >= 2) {
            return MultipleFacing.class;
        }
        return null;
    }

    private static boolean isExactly(Set<Class<? extends org.bukkit.block.data.BlockData>> extensions, Class<? extends org.bukkit.block.data.BlockData> globClass) {
        return extensions.equals(ClassHelper.getAllInterfaces(globClass, org.bukkit.block.data.BlockData.class, new HashSet<>()));
    }

    private static String formatApiName(Class<?> specialBlock) {
        String apiName = specialBlock.getSimpleName();
        if (!BLOCK_SUFFIX_INTENDED.contains(specialBlock)) {
            apiName = apiName.substring(0, apiName.length() - "Block".length());
        }
        return API_RENAMES.getOrDefault(apiName, apiName);
    }

    private static Class<? extends org.bukkit.block.data.BlockData> classOr(String className, Class<? extends org.bukkit.block.data.BlockData> defaultClass) {
        try {
            return (Class<? extends org.bukkit.block.data.BlockData>) Class.forName(className);
        } catch (ClassNotFoundException ignored) {
            return defaultClass;
        }
    }
}
