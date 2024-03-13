package io.papermc.generator.types.craftblockdata.property.holder.converter;

import io.papermc.generator.types.craftblockdata.property.holder.DataHolderType;

public class MapConverter extends ListConverter {

    @Override
    public DataHolderType getType() {
        return DataHolderType.MAP;
    }
}
