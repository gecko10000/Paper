package io.papermc.generator.types.craftblockdata.property.holder;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.squareup.javapoet.ArrayTypeName;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import io.papermc.generator.types.StructuredGenerator;
import io.papermc.generator.types.craftblockdata.property.holder.appender.ArrayAppender;
import io.papermc.generator.types.craftblockdata.property.holder.appender.DataAppender;
import io.papermc.generator.types.craftblockdata.property.holder.appender.ListAppender;
import io.papermc.generator.types.craftblockdata.property.holder.appender.MapAppender;
import io.papermc.generator.types.craftblockdata.property.converter.ConverterBase;
import io.papermc.generator.utils.BlockStateMapping;
import io.papermc.generator.utils.NamingManager;
import net.minecraft.world.level.block.state.properties.Property;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static javax.lang.model.element.Modifier.FINAL;
import static javax.lang.model.element.Modifier.PRIVATE;
import static javax.lang.model.element.Modifier.STATIC;

public class DataPropertyWriter<T extends Property<?>> extends DataPropertyWriterBase<T> {

    protected final Field field;
    protected DataHolderType type;
    protected Class<?> indexClass;
    protected TypeName fieldType;

    protected DataPropertyWriter(Collection<T> properties, Class<?> blockClass, Field field, TypeName enclosedType) {
        super(properties, blockClass);
        this.field = field;
        this.computeTypes(field, enclosedType);
    }

    protected void computeTypes(Field field, TypeName enclosedType) {
        if (field.getType().isArray()) {
            this.type = DataHolderType.ARRAY;
            this.indexClass = Integer.TYPE;
            this.fieldType = ArrayTypeName.of(enclosedType);
        } else if (List.class.isAssignableFrom(field.getType())) {
            this.type = DataHolderType.LIST;
            this.indexClass = Integer.TYPE;
            this.fieldType = ParameterizedTypeName.get(ClassName.get(field.getType()), enclosedType);
        } else if (Map.class.isAssignableFrom(field.getType()) && field.getGenericType() instanceof ParameterizedType complexType) {
            this.type = DataHolderType.MAP;
            this.indexClass = (Class<?>) complexType.getActualTypeArguments()[0];
            if (this.indexClass.isEnum()) {
                this.indexClass = BlockStateMapping.ENUM_BRIDGE.getOrDefault(this.indexClass, (Class<? extends Enum<?>>) this.indexClass);
            }
            this.fieldType = ParameterizedTypeName.get(ClassName.get(field.getType()), ClassName.get(this.indexClass), enclosedType);
        } else {
            throw new IllegalStateException("Don't know how to turn " + field + " into api");
        }
    }

    @Override
    public FieldSpec.Builder getOrCreateField(Map<String, String> fieldNames) {
        FieldSpec.Builder fieldBuilder = FieldSpec.builder(this.fieldType, this.field.getName(), PRIVATE, STATIC, FINAL);
        if (Modifier.isPublic(this.field.getModifiers())) {
            // accessible phew
            fieldBuilder.initializer("$T.$N", this.blockClass, this.field.getName());
        } else {
            if (this.type == DataHolderType.ARRAY || this.type == DataHolderType.LIST) {
                CodeBlock.Builder code = CodeBlock.builder();
                this.createSyntheticCollection(code, this.getType() == DataHolderType.ARRAY, fieldNames);
                fieldBuilder.initializer(code.build());
            } else if (this.type == DataHolderType.MAP) {
                CodeBlock.Builder code = CodeBlock.builder();
                this.createSyntheticMap(code, this.indexClass, fieldNames);
                fieldBuilder.initializer(code.build());
            }
        }
        return fieldBuilder;
    }

    @Override
    public Class<?> getIndexClass() {
        return this.indexClass;
    }

    @Override
    public DataHolderType getType() {
        return this.type;
    }

    @Override
    public String getBaseName() {
        return this.field.getName();
    }

    @Override
    public NamingManager.AccessKeyword getKeyword() {
        return null;
    }

    private static final Map<DataHolderType, DataAppender> APPENDERS;
    private static final ImmutableMap.Builder<DataHolderType, DataAppender> builder = ImmutableMap.builder();

    static {
        register(new ArrayAppender());
        register(new ListAppender());
        register(new MapAppender());
        APPENDERS = Maps.immutableEnumMap(builder.build());
    }

    private static void register(DataAppender converter) {
        builder.put(converter.getType(), converter);
    }

    @Override
    public void addExtras(final TypeSpec.Builder builder, final FieldSpec field, final ParameterSpec indexParameter, final ConverterBase childConverter, final StructuredGenerator<?> generator, final NamingManager naming) {
        if (APPENDERS.containsKey(this.type)) {
            APPENDERS.get(this.type).addExtras(builder, field, indexParameter, childConverter, generator, naming);
        }
    }

}
