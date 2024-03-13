package io.papermc.generator.types.craftblockdata.property.appender;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.TypeSpec;
import io.papermc.generator.types.StructuredGenerator;
import io.papermc.generator.utils.NamingManager;

public interface AppenderBase {

    void addExtras(final TypeSpec.Builder builder, final FieldSpec field, final StructuredGenerator<?> generator, final NamingManager naming);
}
