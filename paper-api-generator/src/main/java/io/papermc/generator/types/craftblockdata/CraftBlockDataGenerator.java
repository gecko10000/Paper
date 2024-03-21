package io.papermc.generator.types.craftblockdata;

import com.google.common.base.CaseFormat;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import io.papermc.generator.types.Types;
import io.papermc.generator.types.StructuredGenerator;
import io.papermc.generator.types.craftblockdata.property.PropertyWriter;
import io.papermc.generator.types.craftblockdata.property.holder.DataPropertyMaker;
import io.papermc.generator.types.craftblockdata.property.PropertyMaker;
import io.papermc.generator.types.craftblockdata.property.converter.ConverterBase;
import io.papermc.generator.types.craftblockdata.property.converter.Converters;
import io.papermc.generator.types.craftblockdata.property.holder.converter.DataConverter;
import io.papermc.generator.types.craftblockdata.property.holder.converter.DataConverters;
import io.papermc.generator.utils.Annotations;
import io.papermc.generator.utils.BlockStateMapping;
import io.papermc.generator.utils.CommonVariable;
import io.papermc.generator.utils.NamingManager;
import it.unimi.dsi.fastutil.Pair;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChiseledBookShelfBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Property;
import org.bukkit.Axis;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Rail;

import java.util.Collection;
import java.util.Map;
import java.util.function.BiConsumer;

import static io.papermc.generator.utils.NamingManager.keywordGet;
import static io.papermc.generator.utils.NamingManager.keywordGetSet;
import static javax.lang.model.element.Modifier.FINAL;
import static javax.lang.model.element.Modifier.PRIVATE;
import static javax.lang.model.element.Modifier.PUBLIC;
import static javax.lang.model.element.Modifier.STATIC;

public class CraftBlockDataGenerator<T extends BlockData> extends StructuredGenerator<T> {

    private final Class<? extends Block> blockClass;
    private final BlockStateMapping.BlockData blockData;

    protected CraftBlockDataGenerator(final Class<? extends Block> blockClass, final BlockStateMapping.BlockData blockData, final Class<T> baseClass) {
        super(baseClass, blockData.impl(), Types.BASE_PACKAGE + ".block.impl");
        this.blockClass = blockClass;
        this.blockData = blockData;
    }

    // default keywords: get/set
    // for single boolean property: get = is
    // for indexed boolean property: get = has
    private static final Map<Property<?>, NamingManager.AccessKeyword> FLUENT_KEYWORD = ImmutableMap.<Property<?>, NamingManager.AccessKeyword>builder()
        .put(BlockStateProperties.ATTACH_FACE, keywordGetSet("getAttached", "setAttached")) // todo remove this once switch methods are gone
        .put(BlockStateProperties.EYE, keywordGet("has"))
        .put(BlockStateProperties.BERRIES, keywordGet("has")) // spigot method rename
        // data holder keywords is only needed for the first property they hold
        .put(ChiseledBookShelfBlock.SLOT_OCCUPIED_PROPERTIES.get(0), keywordGet("is"))
        .build();

    private static final Map<Property<?>, BiConsumer<ParameterSpec, MethodSpec.Builder>> SETTER_PRECONDITIONS = Map.of(
        BlockStateProperties.FACING, (param, method) -> {
            method.addStatement("$T.checkArgument($N.isCartesian(), $S)", Preconditions.class, param, "Invalid face, only cartesian face are allowed for this property!");
        },
        BlockStateProperties.HORIZONTAL_FACING, (param, method) -> {
            method.addStatement("$1T.checkArgument($2N.isCartesian() && $2N.getModY() == 0, $3S)", Preconditions.class, param, "Invalid face, only cartesian horizontal face are allowed for this property!");
        },
        BlockStateProperties.FACING_HOPPER, (param, method) -> {
            method.addStatement("$1T.checkArgument($2N.isCartesian() && $2N != $3T.UP, $4S)", Preconditions.class, param, BlockFace.class, "Invalid face, only cartesian face (excluding UP) are allowed for this property!");
        },
        BlockStateProperties.VERTICAL_DIRECTION, (param, method) -> {
            method.addStatement("$T.checkArgument($N.getModY() != 0, $S)", Preconditions.class, param, "Invalid face, only vertical face are allowed for this property!");
        },
        BlockStateProperties.ROTATION_16, (param, method) -> {
            method.addStatement("$1T.checkArgument($2N != $3T.SELF && $2N.getModY() == 0, $4S)", Preconditions.class, param, BlockFace.class, "Invalid face, only horizontal face are allowed for this property!");
        },
        BlockStateProperties.HORIZONTAL_AXIS, (param, method) -> {
            method.addStatement("$1T.checkArgument($2N == $3T.X || $2N == $3T.Z, $4S)", Preconditions.class, param, Axis.class, "Invalid axis, only horizontal axis are allowed for this property!");
        },
        BlockStateProperties.RAIL_SHAPE_STRAIGHT, (param, method) -> {
            method.addStatement("$1T.checkArgument($2N != $3T.NORTH_EAST && $2N != $3T.NORTH_WEST && $2N != $3T.SOUTH_EAST && $2N != $3T.SOUTH_WEST, $4S)", Preconditions.class, param, Rail.Shape.class, "Invalid rail shape, only straight rail are allowed for this property!");
        }
    );

    private TypeSpec.Builder propertyHolder() {
        TypeSpec.Builder typeBuilder = TypeSpec.classBuilder(this.className)
            .addModifiers(PUBLIC)
            .addAnnotation(Annotations.GENERATED_FROM)
            .addAnnotation(Annotations.suppressWarnings("unused")) // todo remove
            .superclass(Types.CRAFT_BLOCKDATA)
            .addSuperinterface(this.baseClass);

        ParameterSpec parameter = ParameterSpec.builder(BlockState.class, "state").build();
        MethodSpec constructor = MethodSpec.constructorBuilder()
            .addModifiers(PUBLIC)
            .addParameter(parameter)
            .addStatement("super($N)", parameter)
            .build();

        typeBuilder.addMethod(constructor);
        return typeBuilder;
    }

    @Override
    protected TypeSpec getTypeSpec() {
        TypeSpec.Builder typeBuilder = this.propertyHolder();

        for (Property<?> property : this.blockData.properties()) {
            Pair<Class<?>, String> fieldName = PropertyWriter.referenceField(this.blockClass, property, this.blockData.fieldNames());

            PropertyMaker propertyMaker = PropertyMaker.make(property);

            FieldSpec.Builder fieldBuilder = FieldSpec.builder(propertyMaker.getPropertyType(), fieldName.right(), PRIVATE, STATIC, FINAL)
                .initializer("$T.$L", fieldName.left(), fieldName.right());
            FieldSpec field = fieldBuilder.build();

            typeBuilder.addField(field);

            ConverterBase converter = Converters.getOrDefault(property, propertyMaker);
            Class<?> apiClass = converter.getApiType();

            NamingManager.AccessKeyword accessKeyword = null;
            if (apiClass == Boolean.TYPE) {
                accessKeyword = keywordGet("is");
            }
            accessKeyword = FLUENT_KEYWORD.getOrDefault(property, accessKeyword);
            NamingManager naming = new NamingManager(accessKeyword, CaseFormat.LOWER_UNDERSCORE, property.getName());

            // get
            {
                MethodSpec.Builder methodBuilder = createMethod(naming.simpleGetterName(name -> !name.startsWith("is_") && !name.startsWith("has_")));
                converter.convertGetter(methodBuilder, field);
                methodBuilder.returns(apiClass);

                typeBuilder.addMethod(methodBuilder.build());
            }

            // set
            {
                String paramName = naming.paramName(apiClass);
                ParameterSpec parameter = ParameterSpec.builder(apiClass, paramName, FINAL).build();

                MethodSpec.Builder methodBuilder = createMethod(naming.simpleSetterName(name -> !name.startsWith("is_")), apiClass).addParameter(parameter);
                if (!apiClass.isPrimitive()) {
                    methodBuilder.addStatement("$T.checkArgument($N != null, $S)", Preconditions.class, parameter, "%s cannot be null!".formatted(paramName));
                }
                if (SETTER_PRECONDITIONS.containsKey(property)) {
                    SETTER_PRECONDITIONS.get(property).accept(parameter, methodBuilder);
                }
                converter.convertSetter(methodBuilder, field, parameter);

                typeBuilder.addMethod(methodBuilder.build());
            }

            // extra
            propertyMaker.addExtras(typeBuilder, field, this, naming);
        }

        for (BlockStateMapping.FieldDataHolder fieldDataHolder : this.blockData.complexFields().keySet()) {
            Collection<Property<?>> properties = this.blockData.complexFields().get(fieldDataHolder);
            Property<?> firstProperty = properties.iterator().next();

            PropertyMaker propertyMaker = PropertyMaker.make(firstProperty);
            ConverterBase propertyConverter = Converters.getOrDefault(firstProperty, propertyMaker);

            TypeName propertyType = propertyMaker.getPropertyType();
            DataPropertyMaker dataPropertyMaker = DataPropertyMaker.make(properties, this.blockClass, fieldDataHolder, propertyType);

            FieldSpec field = dataPropertyMaker.getOrCreateField(this.blockData.fieldNames()).build();
            typeBuilder.addField(field);

            DataConverter converter = DataConverters.getOrThrow(dataPropertyMaker.getType());
            Class<?> apiClass = propertyConverter.getApiType();

            NamingManager.AccessKeyword accessKeyword = null;
            if (apiClass == Boolean.TYPE) {
                accessKeyword = NamingManager.keywordGet("has");
            }
            accessKeyword = FLUENT_KEYWORD.getOrDefault(firstProperty, accessKeyword);
            NamingManager naming = new NamingManager(accessKeyword, CaseFormat.UPPER_UNDERSCORE, dataPropertyMaker.getBaseName());

            ParameterSpec indexParameter = ParameterSpec.builder(dataPropertyMaker.getIndexClass(), dataPropertyMaker.getIndexClass() == Integer.TYPE ? CommonVariable.INDEX : naming.paramName(dataPropertyMaker.getIndexClass()), FINAL).build();

            // get
            {
                MethodSpec.Builder methodBuilder = createMethod(naming.simpleGetterName(name -> true), dataPropertyMaker.getIndexClass())
                    .addParameter(indexParameter);
                if (!dataPropertyMaker.getIndexClass().isPrimitive()) {
                    methodBuilder.addStatement("$T.checkArgument($N != null, $S)", Preconditions.class, indexParameter, "%s cannot be null!".formatted(indexParameter.name));
                }
                converter.convertGetter(propertyConverter, methodBuilder, field, indexParameter);
                methodBuilder.returns(apiClass);

                typeBuilder.addMethod(methodBuilder.build());
            }

            // set
            {
                String paramName = naming.paramName(apiClass);
                ParameterSpec parameter = ParameterSpec.builder(apiClass, paramName, FINAL).build();

                MethodSpec.Builder methodBuilder = createMethod(naming.simpleSetterName(name -> true), dataPropertyMaker.getIndexClass(), apiClass)
                    .addParameter(indexParameter)
                    .addParameter(parameter);
                if (!dataPropertyMaker.getIndexClass().isPrimitive()) {
                    methodBuilder.addStatement("$T.checkArgument($N != null, $S)", Preconditions.class, indexParameter, "%s cannot be null!".formatted(indexParameter.name));
                }
                if (!apiClass.isPrimitive()) {
                    methodBuilder.addStatement("$T.checkArgument($N != null, $S)", Preconditions.class, parameter, "%s cannot be null!".formatted(paramName));
                }
                if (SETTER_PRECONDITIONS.containsKey(firstProperty)) {
                    SETTER_PRECONDITIONS.get(firstProperty).accept(parameter, methodBuilder);
                }
                converter.convertSetter(propertyConverter, methodBuilder, field, indexParameter, parameter);

                typeBuilder.addMethod(methodBuilder.build());
            }

            // extra
            dataPropertyMaker.addExtras(typeBuilder, field, indexParameter, propertyConverter, this, naming);
        }

        return typeBuilder.build();
    }
}
