package io.papermc.generator.types.craftblockdata.property.holder.converter;

import com.google.common.base.Preconditions;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import io.papermc.generator.types.craftblockdata.property.EnumPropertyWriter;
import io.papermc.generator.types.craftblockdata.property.converter.ConverterBase;
import io.papermc.generator.types.craftblockdata.property.holder.DataHolderType;
import java.util.stream.Collectors;

public class MapConverter implements DataConverter {

    @Override
    public DataHolderType getType() {
        return DataHolderType.MAP;
    }

    @Override
    public void convertSetter(final ConverterBase childConverter, final MethodSpec.Builder method, final FieldSpec field, final ParameterSpec indexParameter, final ParameterSpec parameter) {
        method.addStatement("$T property = $N.get($N)", ((ParameterizedTypeName) field.type).typeArguments.get(1), field, indexParameter);
        method.addStatement("$T.checkArgument($N != null, $S, $N.keySet().stream().map($T::name).collect($T.joining($S)))",
            Preconditions.class, "property", "Invalid " + indexParameter.name + ", only %s are allowed!", field, Enum.class, Collectors.class, ", ");

        method.addStatement(childConverter.rawSetExprent().formatted("$L"), "property", parameter);
    }

    @Override
    public void convertGetter(final ConverterBase childConverter, final MethodSpec.Builder method, final FieldSpec field, final ParameterSpec indexParameter) {
        method.addStatement("$T property = $N.get($N)", ((ParameterizedTypeName) field.type).typeArguments.get(1), field, indexParameter);
        method.addStatement("$T.checkArgument($N != null, $S, $N.keySet().stream().map($T::name).collect($T.joining($S)))",
            Preconditions.class, "property", "Invalid " + indexParameter.name + ", only %s are allowed!", field, Enum.class, Collectors.class, ", ");

        if (childConverter instanceof EnumPropertyWriter<?> enumConverter) {
            method.addStatement("return " + childConverter.rawGetExprent().formatted("$L"), "property", enumConverter.getApiType());
        } else {
            method.addStatement("return " + childConverter.rawGetExprent().formatted("$L"), "property");
        }
    }
}
