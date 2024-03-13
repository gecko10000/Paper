package io.papermc.generator.types.craftblockdata.property.converter;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;

public interface ConverterBase {

    Class<?> getApiType();

    default void convertSetter(final MethodSpec.Builder method, final FieldSpec field, final ParameterSpec parameter) {
        method.addStatement(this.rawSetExprent().formatted("$N"), field, parameter);
    }

    String rawSetExprent(); // this go on two layers which can be hard to follow refactor?

    default void convertGetter(final MethodSpec.Builder method, final FieldSpec field) {
        method.addStatement("return " + this.rawGetExprent().formatted("$N"), field);
    }

    String rawGetExprent();

}
