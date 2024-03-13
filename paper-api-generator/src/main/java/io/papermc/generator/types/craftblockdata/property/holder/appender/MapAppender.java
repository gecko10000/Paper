package io.papermc.generator.types.craftblockdata.property.holder.appender;

import com.google.common.collect.ImmutableSet;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;
import io.papermc.generator.types.StructuredGenerator;
import io.papermc.generator.types.craftblockdata.Types;
import io.papermc.generator.types.craftblockdata.property.converter.ConverterBase;
import io.papermc.generator.types.craftblockdata.property.holder.DataHolderType;
import io.papermc.generator.utils.NamingManager;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class MapAppender implements DataAppender {

    @Override
    public DataHolderType getType() {
        return DataHolderType.MAP;
    }

    @Override
    public void addExtras(final TypeSpec.Builder builder, final FieldSpec field, final ParameterSpec indexParameter, final ConverterBase childConverter, final StructuredGenerator<?> generator, final NamingManager naming) {
        if (childConverter.getApiType() == Boolean.TYPE) {
            String collectFieldName = naming.getVariableNameWrapper().post("s").concat();
            MethodSpec.Builder methodBuilder = generator.createMethod(naming.getMethodNameWrapper().post("s").concat());
            methodBuilder.addStatement("$T $L = $T.builder()", ParameterizedTypeName.get(ClassName.get(ImmutableSet.Builder.class), indexParameter.type), collectFieldName, ImmutableSet.class);
            methodBuilder.beginControlFlow("for ($T $N : $N.entrySet())", ParameterizedTypeName.get(ClassName.get(Map.Entry.class), indexParameter.type, ClassName.get(BooleanProperty.class)), Types.ENTRY_VARIABLE, field);
            {
                methodBuilder.beginControlFlow("if (" + childConverter.rawGetExprent().formatted("$L.getValue()") + ")", Types.ENTRY_VARIABLE);
                {
                    methodBuilder.addStatement("$L.add($N.getKey())", collectFieldName, Types.ENTRY_VARIABLE);
                }
                methodBuilder.endControlFlow();
            }
            methodBuilder.endControlFlow();
            methodBuilder.addStatement("return $L.build()", collectFieldName);
            methodBuilder.returns(ParameterizedTypeName.get(ClassName.get(Set.class), indexParameter.type));

            builder.addMethod(methodBuilder.build());
        }

        {
            MethodSpec.Builder methodBuilder = generator.createMethod(naming.getMethodNameWrapper().pre("Allowed").post("s").concat());
            methodBuilder.addStatement("return $T.unmodifiableSet($N.keySet())", Collections.class, field);
            methodBuilder.returns(ParameterizedTypeName.get(ClassName.get(Set.class), indexParameter.type));

            builder.addMethod(methodBuilder.build());
        }
    }
}
