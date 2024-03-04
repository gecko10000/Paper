package io.papermc.generator.types.craftblockdata.converter;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import net.minecraft.world.level.block.state.properties.Property;

public abstract class Converter<T extends Comparable<T>, A> {

    public abstract Property<T> getProperty();

    public abstract Class<A> getApiType();

    public abstract void convertSetter(final MethodSpec.Builder method, final FieldSpec field, final ParameterSpec parameter);

    public abstract void convertGetter(final MethodSpec.Builder method, final FieldSpec field);

}
