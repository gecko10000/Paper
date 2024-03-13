package io.papermc.generator.types.craftblockdata.property.holder.converter;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import io.papermc.generator.types.craftblockdata.property.holder.DataHolderType;

public final class DataConverters {

    private static final ImmutableMap<DataHolderType, DataConverter> CONVERTERS;
    private static final ImmutableMap.Builder<DataHolderType, DataConverter> builder = ImmutableMap.builder();

    static {
        register(new ArrayConverter());
        register(new ListConverter());
        register(new MapConverter());
        CONVERTERS = Maps.immutableEnumMap(builder.build());
    }

    public static DataConverter getOrThrow(DataHolderType type) {
        DataConverter converter = CONVERTERS.get(type);
        if (converter == null) {
            throw new IllegalStateException("Cannot handle " + type);
        }
        return converter;
    }

    private static void register(DataConverter converter) {
        builder.put(converter.getType(), converter);
    }
}
