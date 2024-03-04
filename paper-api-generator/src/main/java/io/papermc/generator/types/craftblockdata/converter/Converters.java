package io.papermc.generator.types.craftblockdata.converter;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import net.minecraft.world.level.block.state.properties.Property;

public final class Converters {

    public static final Map<Property<?>, Converter<?, ?>> VALUES;
    private static final ImmutableMap.Builder<Property<?>, Converter<?, ?>> builder = ImmutableMap.builder();
    static {
        register(new RotationConverter());
        register(new NoteConverter());
        VALUES = builder.build();
    }

    private static void register(Converter<?, ?> converter) {
        builder.put(converter.getProperty(), converter);
    }
}
