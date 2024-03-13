package io.papermc.generator.types.craftblockdata.property.holder;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.TypeName;
import io.papermc.generator.types.craftblockdata.property.holder.appender.DataAppender;
import io.papermc.generator.types.craftblockdata.property.holder.named.ChiseledBookshelfDataPropertyWriter;
import io.papermc.generator.types.craftblockdata.property.holder.named.MultipleFaceDataPropertyWriter;
import io.papermc.generator.utils.BlockStateMapping;
import io.papermc.generator.utils.NamingManager;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

public interface DataPropertyMaker extends DataAppender {

    FieldSpec.Builder getOrCreateField(Map<String, String> fieldNames);

    Class<?> getIndexClass();

    @Override
    DataHolderType getType();

    String getBaseName();

    NamingManager.AccessKeyword getKeyword();

    static <T extends Property<?>> DataPropertyMaker make(Collection<T> properties, Class<?> blockClass, BlockStateMapping.FieldDataHolder fieldDataHolder, TypeName enclosedType) {
        if (fieldDataHolder.field() == null) {
            return new VirtualDataPropertyWriter<>(fieldDataHolder.virtualFieldInfo(), properties, blockClass, enclosedType);
        }

        Field field = fieldDataHolder.field();
        if (field.getName().equals(BlockStateMapping.PIPE_FIELD_NAME)) {
            return new MultipleFaceDataPropertyWriter<>(properties, blockClass, field, enclosedType);
        }
        if (field.getName().equals(BlockStateMapping.CHISELED_BOOKSHELF_FIELD_NAME)) {
            return new ChiseledBookshelfDataPropertyWriter((Collection<BooleanProperty>) properties, blockClass, field, enclosedType);
        }
        return new DataPropertyWriter<>(properties, blockClass, field, enclosedType);
    }
}
