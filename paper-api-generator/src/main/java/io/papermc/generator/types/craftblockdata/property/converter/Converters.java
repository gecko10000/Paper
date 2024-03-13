package io.papermc.generator.types.craftblockdata.property.converter;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import io.papermc.generator.types.craftblockdata.property.PropertyMaker;
import net.minecraft.world.level.block.state.properties.Property;

public final class Converters {

    private static final Map<Property<?>, ConverterBase> CONVERTERS;
    private static final ImmutableMap.Builder<Property<?>, ConverterBase> builder = ImmutableMap.builder();

    static {
        register(new RotationConverter());
        register(new NoteConverter());
        CONVERTERS = builder.build();
    }

    public static ConverterBase getOrDefault(Property<?> property, PropertyMaker maker) {
        return CONVERTERS.getOrDefault(property, maker);
    }

    public static boolean has(Property<?> property) {
        return CONVERTERS.containsKey(property);
    }

    private static void register(Converter<? extends Comparable<?>, ?> converter) {
        builder.put(converter.getProperty(), converter);
    }
}
