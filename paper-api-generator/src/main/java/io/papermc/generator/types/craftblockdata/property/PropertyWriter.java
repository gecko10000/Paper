package io.papermc.generator.types.craftblockdata.property;

import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableMap;
import com.google.common.primitives.Primitives;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import io.papermc.generator.types.StructuredGenerator;
import io.papermc.generator.types.craftblockdata.property.appender.EnumValuesAppender;
import io.papermc.generator.types.craftblockdata.property.appender.PropertyAppender;
import io.papermc.generator.types.craftblockdata.property.appender.AppenderBase;
import io.papermc.generator.utils.BlockStateMapping;
import io.papermc.generator.utils.NamingManager;
import it.unimi.dsi.fastutil.Pair;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Property;
import org.bukkit.Axis;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Rail;
import java.util.Map;
import java.util.function.Supplier;

public class PropertyWriter<T extends Comparable<T>> implements PropertyMaker {

    protected final Property<T> property;
    private final Supplier<Class<?>> apiClassSupplier;

    protected PropertyWriter(Property<T> property) {
        this.property = property;
        this.apiClassSupplier = Suppliers.memoize(this::processApiType);
    }

    @Override
    public TypeName getPropertyType() {
        return TypeName.get(this.property.getClass());
    }

    protected Class<?> processApiType() {
        Class<T> apiClass = this.property.getValueClass();
        if (Primitives.isWrapperType(apiClass)) {
            apiClass = Primitives.unwrap(apiClass);
        }
        return apiClass;
    }

    @Override
    public Class<?> getApiType() {
        return this.apiClassSupplier.get();
    }

    @Override
    public String rawSetExprent() {
        return "this.set(%s, $N)";
    }

    @Override
    public String rawGetExprent() {
        return "this.get(%s)";
    }

    private static final Map<Property<?>, AppenderBase> APPENDERS;
    private static final ImmutableMap.Builder<Property<?>, AppenderBase> builder = ImmutableMap.builder();

    static {
        register(new EnumValuesAppender<>(BlockStateProperties.AXIS, Axis.class, "getAxes"));
        register(new EnumValuesAppender<>(BlockStateProperties.HORIZONTAL_AXIS, Axis.class, "getAxes"));
        register(new EnumValuesAppender<>(BlockStateProperties.FACING, BlockFace.class, "getFaces"));
        register(new EnumValuesAppender<>(BlockStateProperties.HORIZONTAL_FACING, BlockFace.class, "getFaces"));
        register(new EnumValuesAppender<>(BlockStateProperties.FACING_HOPPER, BlockFace.class, "getFaces"));
        register(new EnumValuesAppender<>(BlockStateProperties.RAIL_SHAPE, Rail.Shape.class, "getShapes"));
        register(new EnumValuesAppender<>(BlockStateProperties.RAIL_SHAPE_STRAIGHT, Rail.Shape.class, "getShapes"));
        register(new EnumValuesAppender<>(BlockStateProperties.VERTICAL_DIRECTION, BlockFace.class, "getVerticalDirections"));
        APPENDERS = builder.build();
    }

    private static void register(PropertyAppender<? extends Comparable<?>, ?> converter) {
        builder.put(converter.getProperty(), converter);
    }

    @Override
    public void addExtras(final TypeSpec.Builder builder, final FieldSpec field, final StructuredGenerator<?> generator, final NamingManager naming) {
        if (APPENDERS.containsKey(this.property)) {
            APPENDERS.get(this.property).addExtras(builder, field, generator, naming);
        }
    }

    public static Pair<Class<?>, String> referenceField(Class<? extends Block> from, Property<?> property, Map<String, String> fieldNames) {
        Class<?> fieldAccess = from;
        String fieldName = fieldNames.get(property.getName());
        if (fieldName == null) {
            fieldAccess = BlockStateProperties.class;
            fieldName = BlockStateMapping.FALLBACK_GENERIC_FIELD_NAMES.get(property);
        }
        return Pair.of(fieldAccess, fieldName);
    }
}
