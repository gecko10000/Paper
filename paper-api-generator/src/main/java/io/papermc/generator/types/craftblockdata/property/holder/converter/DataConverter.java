package io.papermc.generator.types.craftblockdata.property.holder.converter;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import io.papermc.generator.types.craftblockdata.property.converter.ConverterBase;
import io.papermc.generator.types.craftblockdata.property.holder.DataHolderType;

public interface DataConverter {

    DataHolderType getType();

    void convertSetter(final ConverterBase childConverter, final MethodSpec.Builder method, final FieldSpec field, final ParameterSpec indexParameter, final ParameterSpec parameter);

    void convertGetter(final ConverterBase childConverter, final MethodSpec.Builder method, final FieldSpec field, final ParameterSpec indexParameter);

}
