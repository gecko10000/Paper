package io.papermc.generator.types.craftblockdata.property.holder;

import com.squareup.javapoet.CodeBlock;
import io.papermc.generator.utils.BlockStateMapping;
import io.papermc.generator.utils.Formatting;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Property;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public abstract class DataPropertyWriterBase<T extends Property<?>> implements DataPropertyMaker {

    protected final Class<?> blockClass;
    protected final Collection<T> properties;

    protected DataPropertyWriterBase(Collection<T> properties, Class<?> blockClass) {
        this.properties = properties;
        this.blockClass = blockClass;
    }

    private void referenceFieldProperty(T property, Map<String, String> fieldNames, BiConsumer<Class<?>, String> callback) {
        String fieldName = fieldNames.get(property.getName());
        Class<?> fieldAccess = this.blockClass;
        if (fieldName == null) {
            fieldName = BlockStateMapping.FALLBACK_GENERIC_FIELD_NAMES.get(property);
            fieldAccess = BlockStateProperties.class;
        }
        callback.accept(fieldAccess, fieldName);
    }

    protected void createSyntheticCollection(CodeBlock.Builder code, boolean isArray, Map<String, String> fieldNames) {
        if (isArray) {
            code.add("{\n");
        } else {
            code.add("$T.of(\n", List.class);
        }
        code.indent();
        Iterator<T> it = this.properties.iterator();
        while (it.hasNext()) {
            this.referenceFieldProperty(it.next(), fieldNames, (fieldAccess, fieldName) -> {
                code.add("$T.$L", fieldAccess, fieldName);
            });
            if (it.hasNext()) {
                code.add(",");
            }
            code.add("\n");
        }
        code.unindent().add(isArray ? "}" : ")");
    }

    protected void createSyntheticMap(CodeBlock.Builder code, Class<?> indexClass, Map<String, String> fieldNames) {
        code.add("$T.of(\n", Map.class).indent();
        Iterator<T> it = this.properties.iterator();
        while (it.hasNext()) {
            T property = it.next();
            String name = Formatting.formatPathAsField(property.getName());
            this.referenceFieldProperty(property, fieldNames, (fieldAccess, fieldName) -> {
                code.add("$T.$L, $T.$L", indexClass, name, fieldAccess, fieldName);
            });
            if (it.hasNext()) {
                code.add(",");
            }
            code.add("\n");
        }
        code.unindent().add(")");
    }

    @Override
    public abstract Class<?> getIndexClass();
}
