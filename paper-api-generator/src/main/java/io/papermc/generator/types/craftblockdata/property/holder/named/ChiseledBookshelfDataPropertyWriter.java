package io.papermc.generator.types.craftblockdata.property.holder.named;

import com.squareup.javapoet.TypeName;
import java.lang.reflect.Field;
import java.util.Collection;
import io.papermc.generator.types.craftblockdata.property.holder.DataPropertyWriter;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class ChiseledBookshelfDataPropertyWriter extends DataPropertyWriter<BooleanProperty> {

    public ChiseledBookshelfDataPropertyWriter(final Collection<BooleanProperty> properties, final Class<?> blockClass, final Field field, final TypeName enclosedType) {
        super(properties, blockClass, field, enclosedType);
    }

    @Override
    public String getBaseName() {
        String name = super.getBaseName();
        return name.substring(0, name.length() - "_PROPERTIES".length());
    }
}
