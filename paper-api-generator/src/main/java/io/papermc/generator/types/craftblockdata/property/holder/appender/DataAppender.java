package io.papermc.generator.types.craftblockdata.property.holder.appender;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeSpec;
import io.papermc.generator.types.StructuredGenerator;
import io.papermc.generator.types.craftblockdata.property.converter.ConverterBase;
import io.papermc.generator.types.craftblockdata.property.holder.DataHolderType;
import io.papermc.generator.utils.NamingManager;

public interface DataAppender {

    DataHolderType getType();

    void addExtras(final TypeSpec.Builder builder, final FieldSpec field, final ParameterSpec indexParameter, final ConverterBase converter, final StructuredGenerator<?> generator, final NamingManager naming);

}
