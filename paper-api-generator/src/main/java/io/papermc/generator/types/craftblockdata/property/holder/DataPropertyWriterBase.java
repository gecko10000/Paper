package io.papermc.generator.types.craftblockdata.property.holder;

import com.squareup.javapoet.CodeBlock;
import io.papermc.generator.types.craftblockdata.property.PropertyWriter;
import io.papermc.generator.utils.Formatting;
import it.unimi.dsi.fastutil.Pair;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.Property;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class DataPropertyWriterBase<T extends Property<?>> implements DataPropertyMaker {

    protected final Collection<T> properties;
    protected final Class<? extends Block> blockClass;

    protected DataPropertyWriterBase(Collection<T> properties, Class<? extends Block> blockClass) {
        this.properties = properties;
        this.blockClass = blockClass;
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
            T property = it.next();
            Pair<Class<?>, String> fieldName = PropertyWriter.referenceField(this.blockClass, property, fieldNames);
            code.add("$T.$L", fieldName.left(), fieldName.right());
            if (it.hasNext()) {
                code.add(",");
            }
            code.add("\n");
        }
        code.unindent().add(isArray ? "}" : ")");
    }

    protected void createSyntheticMap(CodeBlock.Builder code, Class<?> indexClass, Map<String, String> fieldNames) {
        // assume indexClass is an enum with its values matching the property names
        code.add("$T.of(\n", Map.class).indent();
        Iterator<T> it = this.properties.iterator();
        while (it.hasNext()) {
            T property = it.next();
            String name = Formatting.formatPathAsField(property.getName());
            Pair<Class<?>, String> fieldName = PropertyWriter.referenceField(this.blockClass, property, fieldNames);
            code.add("$T.$L, $T.$L", indexClass, name, fieldName.left(), fieldName.right());
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
