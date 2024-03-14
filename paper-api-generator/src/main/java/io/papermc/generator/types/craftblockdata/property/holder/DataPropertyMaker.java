package io.papermc.generator.types.craftblockdata.property.holder;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.TypeName;
import io.papermc.generator.types.craftblockdata.property.holder.appender.DataAppender;
import io.papermc.generator.utils.BlockStateMapping;
import io.papermc.generator.utils.NamingManager;
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

    Map<String, String> FIELD_TO_BASE_NAME = Map.of(
        BlockStateMapping.PIPE_FIELD_NAME, "FACE",
        BlockStateMapping.CHISELED_BOOKSHELF_FIELD_NAME, "SLOT_OCCUPIED"
    );

    static <T extends Property<?>> DataPropertyMaker make(Collection<T> properties, Class<?> blockClass, BlockStateMapping.FieldDataHolder fieldDataHolder, TypeName enclosedType, Class<?> apiClass) {
        if (fieldDataHolder.field() == null) {
            return new VirtualDataPropertyWriter<>(fieldDataHolder.virtualFieldInfo(), properties, blockClass, enclosedType);
        }

        Field field = fieldDataHolder.field();
        DataPropertyWriter<T> writer = new DataPropertyWriter<>(properties, blockClass, field, enclosedType);
        if (apiClass == Boolean.TYPE) {
            writer.setKeyword(NamingManager.keywordGet("has"));
        }
        writer.setBaseName(FIELD_TO_BASE_NAME.getOrDefault(field.getName(), field.getName()));
        return writer;
    }
}
