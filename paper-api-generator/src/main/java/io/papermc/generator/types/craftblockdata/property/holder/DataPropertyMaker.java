package io.papermc.generator.types.craftblockdata.property.holder;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.TypeName;
import io.papermc.generator.types.craftblockdata.property.holder.appender.DataAppender;
import io.papermc.generator.utils.BlockStateMapping;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.Property;

import java.util.Collection;
import java.util.Map;

public interface DataPropertyMaker extends DataAppender {

    FieldSpec.Builder getOrCreateField(Map<String, String> fieldNames);

    Class<?> getIndexClass();

    @Override
    DataHolderType getType();

    String getBaseName();

    static <T extends Property<?>> DataPropertyMaker make(Collection<T> properties, Class<? extends Block> blockClass, BlockStateMapping.FieldDataHolder fieldDataHolder, TypeName enclosedType) {
        if (fieldDataHolder.field() == null) {
            return new VirtualDataPropertyWriter<>(fieldDataHolder.virtualFieldInfo(), properties, blockClass, enclosedType);
        }
        return new DataPropertyWriter<>(fieldDataHolder.field(), properties, blockClass, enclosedType);
    }
}
