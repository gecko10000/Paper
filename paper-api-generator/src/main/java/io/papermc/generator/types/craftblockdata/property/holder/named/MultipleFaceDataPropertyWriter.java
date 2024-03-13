package io.papermc.generator.types.craftblockdata.property.holder.named;

import com.google.common.base.Preconditions;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.TypeName;
import io.papermc.generator.types.craftblockdata.Types;
import io.papermc.generator.types.craftblockdata.property.holder.DataHolderType;
import io.papermc.generator.types.craftblockdata.property.holder.DataPropertyWriter;
import io.papermc.generator.utils.NamingManager;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import net.minecraft.world.level.block.RedStoneWireBlock;
import net.minecraft.world.level.block.state.properties.Property;

import static javax.lang.model.element.Modifier.FINAL;
import static javax.lang.model.element.Modifier.PRIVATE;
import static javax.lang.model.element.Modifier.STATIC;

public class MultipleFaceDataPropertyWriter<T extends Property<?>> extends DataPropertyWriter<T> { // pipe + redstone wire connection, the name seems too generic to detect the right property without doing more check there

    public MultipleFaceDataPropertyWriter(final Collection<T> properties, final Class<?> blockClass, final Field field, final TypeName enclosedType) {
        super(properties, blockClass, field, enclosedType);
    }

    @Override
    public FieldSpec.Builder getOrCreateField(Map<String, String> fieldNames) {
        Preconditions.checkState(this.getType() == DataHolderType.MAP, "Cannot hold multiple face in a " + this.getType());
        FieldSpec.Builder fieldBuilder = FieldSpec.builder(this.fieldType, this.field.getName(), PRIVATE, STATIC, FINAL);
        if (Modifier.isPublic(this.field.getModifiers())) {
            // accessible phew
            fieldBuilder.initializer("$[$T.$L.entrySet().stream()\n.collect($T.toMap(entry -> $T.notchToBlockFace(entry.getKey()), entry -> entry.getValue()))$]",
                this.blockClass, this.field.getName(), Collectors.class, Types.CRAFT_BLOCK);
        } else {
            CodeBlock.Builder code = CodeBlock.builder();
            this.createSyntheticMap(code, this.indexClass, fieldNames);
            fieldBuilder.initializer(code.build());
        }
        return fieldBuilder;
    }

    @Override
    public String getBaseName() {
        return "FACE";
    }

    @Override
    public NamingManager.AccessKeyword getKeyword() {
        if (this.blockClass == RedStoneWireBlock.class) {
            return null;
        }
        return NamingManager.keywordGet("has");
    }
}
