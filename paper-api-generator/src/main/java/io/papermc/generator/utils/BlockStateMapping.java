package io.papermc.generator.utils;

import com.google.common.collect.ImmutableMap;
import io.papermc.generator.rewriter.utils.ClassHelper;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.BigDripleafStemBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CommandBlock;
import net.minecraft.world.level.block.NoteBlock;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.StructureBlock;
import net.minecraft.world.level.block.entity.trialspawner.TrialSpawnerState;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BambooLeaves;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.BellAttachType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.level.block.state.properties.ComparatorMode;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.DripstoneThickness;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.PistonType;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.RailShape;
import net.minecraft.world.level.block.state.properties.RedstoneSide;
import net.minecraft.world.level.block.state.properties.SculkSensorPhase;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.block.state.properties.StairsShape;
import net.minecraft.world.level.block.state.properties.StructureMode;
import net.minecraft.world.level.block.state.properties.Tilt;
import net.minecraft.world.level.block.state.properties.WallSide;
import org.bukkit.Axis;
import org.bukkit.Instrument;
import org.bukkit.block.BlockFace;
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
import org.bukkit.block.data.type.Bamboo;
import org.bukkit.block.data.type.Bed;
import org.bukkit.block.data.type.Bell;
import org.bukkit.block.data.type.BigDripleaf;
import org.bukkit.block.data.type.Chest;
import org.bukkit.block.data.type.Comparator;
import org.bukkit.block.data.type.Door;
import org.bukkit.block.data.type.Dripleaf;
import org.bukkit.block.data.type.Furnace;
import org.bukkit.block.data.type.PointedDripstone;
import org.bukkit.block.data.type.RedstoneRail;
import org.bukkit.block.data.type.RedstoneWire;
import org.bukkit.block.data.type.SculkSensor;
import org.bukkit.block.data.type.Slab;
import org.bukkit.block.data.type.Stairs;
import org.bukkit.block.data.type.Switch;
import org.bukkit.block.data.type.TechnicalPiston;
import org.bukkit.block.data.type.TrialSpawner;
import org.bukkit.block.data.type.Wall;
import org.jetbrains.annotations.Nullable;

public final class BlockStateMapping {

    public record BlockData(String impl, @Nullable Class<? extends org.bukkit.block.data.BlockData> api, Collection<Property<?>> properties, Set<Class<?>> extensions, Map<String, String> fieldNames) {
    }

    private static final Map<String, String> API_RENAMES = ImmutableMap.<String, String>builder()
        .put("WallSkull", "SkullWall")
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
        .put("FenceGate", "Gate")
        .build();

    private static final Set<Class<? extends Block>> BLOCK_SUFFIX_INTENDED = Set.of(
        CommandBlock.class,
        StructureBlock.class,
        NoteBlock.class
    );

    public static final Map<Class<? extends Block>, BlockData> MAPPING;
    public static final Map<Property<?>, String> FALLBACK_GENERIC_FIELD_NAMES;
    static {
        Map<Class<? extends Block>, BlockData> map = new HashMap<>();
        Map<Class<? extends Block>, Collection<Property<?>>> specialBlocks = new HashMap<>();
        for (Block block : BuiltInRegistries.BLOCK) {
            if (!block.getStateDefinition().getProperties().isEmpty()) {
                specialBlocks.put(block.getClass(), block.getStateDefinition().getProperties());
            }
        }

        Map<Property<?>, String> fallbackGenericFieldNames = new HashMap<>();
        fetchProperties(BlockStateProperties.class, (name, property) -> fallbackGenericFieldNames.put(property, name));
        FALLBACK_GENERIC_FIELD_NAMES = Map.copyOf(fallbackGenericFieldNames);

        for (Map.Entry<Class<? extends Block>, Collection<Property<?>>> entry : specialBlocks.entrySet()) {
            Class<? extends Block> specialBlock = entry.getKey();
            Set<Class<?>> extensions = ClassHelper.getInterfacesUntil(specialBlock, Block.class, new LinkedHashSet<>());
            Map<String, String> propertyNames = new HashMap<>();
            fetchProperties(specialBlock, (name, property) -> propertyNames.put(property.getName(), name));

            String apiName = formatApiName(specialBlock);
            String implName = String.format("Craft%s", apiName); // before renames

            apiName = Formatting.stripWordOfCamelCaseName(apiName, "Base", true);
            apiName = API_RENAMES.getOrDefault(apiName, apiName);

            Class<? extends org.bukkit.block.data.BlockData> api = classOr("org.bukkit.block.data.type." + apiName, null);
            if (api == null) {
                Class<?> directParent = specialBlock.getSuperclass();
                if (specialBlocks.containsKey(directParent)) {
                    // if the properties are the same then always consider the parent
                    // check deeper in the tree?
                    if (specialBlocks.get(directParent).equals(entry.getValue())) {
                        String parentApiName = formatApiName(directParent);
                        parentApiName = Formatting.stripWordOfCamelCaseName(parentApiName, "Base", true);
                        parentApiName = API_RENAMES.getOrDefault(parentApiName, parentApiName);
                        api = classOr("org.bukkit.block.data.type." + parentApiName, api);
                    }
                }
            }
            if (api == null) { // todo remove this part
                if (AbstractFurnaceBlock.class.isAssignableFrom(specialBlock)) {
                    api = Furnace.class; // for smoker and blast furnace
                } else if (specialBlock == BigDripleafStemBlock.class) {
                    api = Dripleaf.class;
                }
            }

            map.put(specialBlock, new BlockData(implName, api, entry.getValue(), extensions, propertyNames));
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
        BlockStateProperties.STAIRS_SHAPE, Stairs.class
    );

    public static final Map<Class<? extends Enum<? extends StringRepresentable>>, Class<? extends Enum<?>>> ENUM_BRIDGE = ImmutableMap.<Class<? extends Enum<? extends StringRepresentable>>, Class<? extends Enum<?>>>builder()
        .put(DoorHingeSide.class, Door.Hinge.class)
        .put(SlabType.class, Slab.Type.class)
        .put(StructureMode.class, org.bukkit.block.data.type.StructureBlock.Mode.class)
        .put(DripstoneThickness.class, PointedDripstone.Thickness.class)
        .put(WallSide.class, Wall.Height.class)
        .put(BellAttachType.class, Bell.Attachment.class)
        .put(NoteBlockInstrument.class, Instrument.class)
        .put(StairsShape.class, Stairs.Shape.class)
        .put(Direction.class, BlockFace.class)
        .put(ComparatorMode.class, Comparator.Mode.class)
        .put(PistonType.class, TechnicalPiston.Type.class)
        .put(BedPart.class, Bed.Part.class)
        .put(Half.class, Bisected.Half.class)
        .put(AttachFace.class, FaceAttachable.AttachedFace.class)
        .put(RailShape.class, Rail.Shape.class)
        .put(SculkSensorPhase.class, SculkSensor.Phase.class)
        .put(DoubleBlockHalf.class, Bisected.Half.class)
        .put(Tilt.class, BigDripleaf.Tilt.class)
        .put(ChestType.class, Chest.Type.class)
        .put(RedstoneSide.class, RedstoneWire.Connection.class)
        .put(Direction.Axis.class, Axis.class)
        .put(BambooLeaves.class, Bamboo.Leaves.class)
        .put(TrialSpawnerState.class, TrialSpawner.State.class)
        .build();
    // todo a mess: Crafter.Orientation / Jigsaw.Orientation = FrontAndTop

    /*
    TODO:
    clear paper dataClass list now generated/remove patches not needed anymore
    remove scrap of old spigot tooling (archetype)
     */
    @Nullable
    public static Class<? extends org.bukkit.block.data.BlockData> getBestSuitedApiClass(Class<?> block) {
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
        return apiName;
    }

    private static void fetchProperties(Class<?> block, BiConsumer<String, Property<?>> callback) {
        try {
            for (Field field : block.getFields()) {
                int mod = field.getModifiers();
                if (Modifier.isPublic(mod) & Modifier.isStatic(mod) & Modifier.isFinal(mod)) {
                    if (Iterable.class.isAssignableFrom(field.getType()) && field.getGenericType() instanceof ParameterizedType complexType) {
                        if (complexType.getActualTypeArguments().length == 1 && Property.class.isAssignableFrom(unwrapType(complexType.getActualTypeArguments()[0]))) {
                            for (Property<?> property : (Iterable<Property<?>>) field.get(null)) {
                                callback.accept(field.getName(), property);
                            }
                        }
                        continue;
                    }
                    if (field.getType().isArray() && Property.class.isAssignableFrom(field.getType().getComponentType())) {
                        for (Property<?> property : (Property<?>[]) field.get(null)) {
                            callback.accept(field.getName(), property);
                        }
                        continue;
                    }

                    if (!Property.class.isAssignableFrom(field.getType())) {
                        continue;
                    }

                    Property<?> property = ((Property<?>) field.get(null));
                    callback.accept(field.getName(), property);
                }
            }
        } catch (ReflectiveOperationException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static Class<?> unwrapType(Type type) {
        if (type instanceof Class<?> clazz) {
            return clazz;
        }
        if (type instanceof ParameterizedType complexType) {
            return unwrapType(complexType.getRawType());
        }
        throw new UnsupportedOperationException("Don't know how to turn " + type + " into its base class!");
    }

    private static Class<? extends org.bukkit.block.data.BlockData> classOr(String className, Class<? extends org.bukkit.block.data.BlockData> defaultClass) {
        try {
            return (Class<? extends org.bukkit.block.data.BlockData>) Class.forName(className);
        } catch (ClassNotFoundException ignored) {
            return defaultClass;
        }
    }
}
