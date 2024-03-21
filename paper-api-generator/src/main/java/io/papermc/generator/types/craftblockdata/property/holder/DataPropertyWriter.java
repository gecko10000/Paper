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
import io.papermc.generator.types.Types;
import io.papermc.generator.types.craftblockdata.property.holder.appender.ArrayAppender;
import io.papermc.generator.types.craftblockdata.property.holder.appender.DataAppender;
import io.papermc.generator.types.craftblockdata.property.holder.appender.ListAppender;
import io.papermc.generator.types.craftblockdata.property.holder.appender.MapAppender;
import io.papermc.generator.types.craftblockdata.property.converter.ConverterBase;
import io.papermc.generator.utils.BlockStateMapping;
import io.papermc.generator.utils.ClassHelper;
import io.papermc.generator.utils.CommonVariable;
import io.papermc.generator.utils.NamingManager;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.Property;
import org.bukkit.block.BlockFace;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static javax.lang.model.element.Modifier.FINAL;
import static javax.lang.model.element.Modifier.PRIVATE;
import static javax.lang.model.element.Modifier.STATIC;

public class DataPropertyWriter<T extends Property<?>> extends DataPropertyWriterBase<T> {

    private static final Map<String, String> FIELD_TO_BASE_NAME = Map.of(
        BlockStateMapping.PIPE_FIELD_NAME, "FACE",
        "SLOT_OCCUPIED_PROPERTIES", "SLOT_OCCUPIED"
    );

    protected final Field field;
    protected DataHolderType type;
    protected Class<?> indexClass, internalIndexClass;
    protected TypeName fieldType;

    protected DataPropertyWriter(Field field, Collection<T> properties, Class<? extends Block> blockClass, TypeName enclosedType) {
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
            this.internalIndexClass = ClassHelper.eraseType(complexType.getActualTypeArguments()[0]);
            if (this.internalIndexClass.isEnum()) {
                this.indexClass = BlockStateMapping.ENUM_BRIDGE.getOrDefault(this.internalIndexClass, (Class<? extends Enum<?>>) this.internalIndexClass);
            } else {
                this.indexClass = this.internalIndexClass;
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
            if (this.type == DataHolderType.MAP &&
                this.internalIndexClass == Direction.class && this.indexClass == BlockFace.class) { // Direction -> BlockFace
                // convert the key manually only this one is needed for now
                fieldBuilder.initializer("$[$1T.$2L.entrySet().stream()\n.collect($3T.toMap($4L -> $5T.notchToBlockFace($4L.getKey()), $4L -> $4L.getValue()))$]",
                    this.blockClass, this.field.getName(), Collectors.class, CommonVariable.MAP_ENTRY, Types.CRAFT_BLOCK);
            } else {
                fieldBuilder.initializer("$T.$L", this.blockClass, this.field.getName());
            }
        } else {
            if (this.type == DataHolderType.ARRAY || this.type == DataHolderType.LIST) {
                CodeBlock.Builder code = CodeBlock.builder();
                this.createSyntheticCollection(code, this.type == DataHolderType.ARRAY, fieldNames);
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
        String constantName = this.field.getName();
        if (FIELD_TO_BASE_NAME.containsKey(constantName)) {
            return FIELD_TO_BASE_NAME.get(constantName);
        }
        return stripFieldAccessKeyword(constantName);
    }

    private static final Set<String> CUSTOM_KEYWORD = Set.of("HAS", "IS", "CAN");

    private String stripFieldAccessKeyword(String name) {
        for (String keyword : CUSTOM_KEYWORD) {
            if (name.startsWith(keyword + "_")) {
                return name.substring(keyword.length() + 1);
            }
        }
        return name;
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
