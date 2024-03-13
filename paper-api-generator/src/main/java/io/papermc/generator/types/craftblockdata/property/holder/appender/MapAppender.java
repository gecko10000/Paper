package io.papermc.generator.types.craftblockdata.property.holder.appender;

import com.google.common.collect.ImmutableSet;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;
import io.papermc.generator.types.StructuredGenerator;
import io.papermc.generator.types.craftblockdata.property.converter.ConverterBase;
import io.papermc.generator.types.craftblockdata.property.holder.DataHolderType;
import io.papermc.generator.utils.NamingManager;
import java.util.Collections;
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
            methodBuilder.beginControlFlow("for ($T $N : $N.keySet())", indexParameter.type, indexParameter, field);
            {
                methodBuilder.beginControlFlow("if (" + childConverter.rawGetExprent().formatted("$N.get($N)") + ")", field, indexParameter);
                {
                    methodBuilder.addStatement("$L.add($N)", collectFieldName, indexParameter);
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
