package io.papermc.generator.types.craftblockdata;

import com.google.common.base.CaseFormat;
import com.google.common.collect.ImmutableMap;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import io.papermc.generator.types.StructuredGenerator;
import io.papermc.generator.types.craftblockdata.property.holder.DataPropertyMaker;
import io.papermc.generator.types.craftblockdata.property.PropertyMaker;
import io.papermc.generator.types.craftblockdata.property.converter.ConverterBase;
import io.papermc.generator.types.craftblockdata.property.converter.Converters;
import io.papermc.generator.types.craftblockdata.property.holder.converter.DataConverter;
import io.papermc.generator.types.craftblockdata.property.holder.converter.DataConverters;
import io.papermc.generator.utils.Annotations;
import io.papermc.generator.utils.BlockStateMapping;
import io.papermc.generator.utils.NamingManager;
import net.minecraft.core.FrontAndTop;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BrewingStandBlock;
import net.minecraft.world.level.block.ChiseledBookShelfBlock;
import net.minecraft.world.level.block.RedStoneWireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Property;
import org.bukkit.block.data.BlockData;

import java.util.Collection;
import java.util.Map;

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

    private static final NamingManager.AccessKeyword IS_KEYWORD = keywordGet("is");
    private static final Map<Property<?>, NamingManager.AccessKeyword> FLUENT_KEYWORD = ImmutableMap.<Property<?>, NamingManager.AccessKeyword>builder()
        .put(BlockStateProperties.WATERLOGGED, IS_KEYWORD)
        .put(BlockStateProperties.OPEN, IS_KEYWORD)
        .put(BlockStateProperties.OCCUPIED, IS_KEYWORD)
        .put(BlockStateProperties.LIT, IS_KEYWORD)
        .put(BlockStateProperties.DRAG, IS_KEYWORD)
        .put(BlockStateProperties.ATTACH_FACE, keywordGetSet("getAttached", "setAttached")) // todo remove this once switch methods are gone
        .put(BlockStateProperties.SIGNAL_FIRE, IS_KEYWORD)
        .put(BlockStateProperties.UP, IS_KEYWORD)
        .put(BlockStateProperties.CONDITIONAL, IS_KEYWORD)
        .put(BlockStateProperties.POWERED, IS_KEYWORD)
        .put(BlockStateProperties.CRAFTING, IS_KEYWORD)
        .put(BlockStateProperties.TRIGGERED, IS_KEYWORD)
        .put(BlockStateProperties.INVERTED, IS_KEYWORD)
        .put(BlockStateProperties.CRACKED, IS_KEYWORD)
        .put(BlockStateProperties.EYE, keywordGet("has"))
        .put(BlockStateProperties.IN_WALL, IS_KEYWORD)
        .put(BlockStateProperties.SNOWY, IS_KEYWORD)
        .put(BlockStateProperties.ENABLED, IS_KEYWORD)
        .put(BlockStateProperties.HANGING, IS_KEYWORD)
        .put(BlockStateProperties.PERSISTENT, IS_KEYWORD)
        .put(BlockStateProperties.EXTENDED, IS_KEYWORD)
        .put(BlockStateProperties.LOCKED, IS_KEYWORD)
        .put(BlockStateProperties.BOTTOM, IS_KEYWORD)
        .put(BlockStateProperties.BLOOM, IS_KEYWORD)
        .put(BlockStateProperties.UNSTABLE, IS_KEYWORD)
        .put(BlockStateProperties.DISARMED, IS_KEYWORD)
        .put(BlockStateProperties.ATTACHED, IS_KEYWORD)
        .put(BlockStateProperties.SHORT, IS_KEYWORD)
        .put(BlockStateProperties.SHRIEKING, IS_KEYWORD)
        .put(BlockStateProperties.CAN_SUMMON, IS_KEYWORD) // todo change this one isCan is weird
        .put(BlockStateProperties.BERRIES, keywordGet("has")) // spigot method rename
        // data holder keywords is only needed for the first property they hold
        .put(BrewingStandBlock.HAS_BOTTLE[0], keywordGet("has"))
        .put(ChiseledBookShelfBlock.SLOT_OCCUPIED_PROPERTIES.get(0), keywordGet("is"))
        //.put(RedStoneWireBlock.PROPERTY_BY_DIRECTION.values().iterator().next(), keywordGet("get"))
        .build();

    private TypeSpec.Builder propertyHolder() {
        TypeSpec.Builder typeBuilder = TypeSpec.classBuilder(this.className)
            .addModifiers(PUBLIC)
            .addAnnotation(Annotations.GENERATED_FROM)
            .addAnnotation(Annotations.suppressWarnings("unused"))
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

    public static final String INDEX_VARIABLE = "index";

    @Override
    protected TypeSpec getTypeSpec() {
        TypeSpec.Builder typeBuilder = this.propertyHolder();

        for (Property<?> property : this.blockData.properties()) {
            if (property.getValueClass() == FrontAndTop.class) {
                continue; // broken todo change api a bit? to avoid duplicate enum (jigsaw+crafter)
            }

            String fieldName = this.blockData.fieldNames().get(property.getName());
            Class<?> fieldAccess = this.blockClass;
            if (fieldName == null) {
                fieldAccess = BlockStateProperties.class;
                fieldName = BlockStateMapping.FALLBACK_GENERIC_FIELD_NAMES.get(property);
            }

            PropertyMaker propertyMaker = PropertyMaker.make(property);

            FieldSpec.Builder fieldBuilder = FieldSpec.builder(propertyMaker.getPropertyType(), fieldName, PRIVATE, STATIC, FINAL)
                .initializer("$T.$N", fieldAccess, fieldName);
            FieldSpec field = fieldBuilder.build();

            typeBuilder.addField(field);

            ConverterBase converter = Converters.getOrDefault(property, propertyMaker);
            Class<?> apiClass = converter.getApiType();

            NamingManager.AccessKeyword accessKeyword = FLUENT_KEYWORD.get(property);
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
                ParameterSpec parameter = ParameterSpec.builder(apiClass, naming.paramName(apiClass), FINAL).build();

                MethodSpec.Builder methodBuilder = createMethod(naming.simpleSetterName(name -> !name.startsWith("is_")), apiClass).addParameter(parameter);
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
            Class<?> apiClass = propertyConverter.getApiType();

            TypeName propertyType = propertyMaker.getPropertyType();
            DataPropertyMaker dataPropertyMaker = DataPropertyMaker.make(properties, this.blockClass, fieldDataHolder, propertyType);

            FieldSpec field = dataPropertyMaker.getOrCreateField(this.blockData.fieldNames()).build();
            typeBuilder.addField(field);

            DataConverter converter = DataConverters.getOrThrow(dataPropertyMaker.getType());

            NamingManager.AccessKeyword accessKeyword = FLUENT_KEYWORD.getOrDefault(firstProperty, dataPropertyMaker.getKeyword());
            NamingManager naming = new NamingManager(accessKeyword, CaseFormat.UPPER_UNDERSCORE, NamingManager.stripFieldAccessKeyword(dataPropertyMaker.getBaseName()));

            ParameterSpec indexParameter = ParameterSpec.builder(dataPropertyMaker.getIndexClass(), dataPropertyMaker.getIndexClass() == Integer.TYPE ? INDEX_VARIABLE : naming.paramName(dataPropertyMaker.getIndexClass()), FINAL).build();

            // get
            {
                MethodSpec.Builder methodBuilder = createMethod(naming.simpleGetterName(name -> true), dataPropertyMaker.getIndexClass())
                    .addParameter(indexParameter);
                converter.convertGetter(propertyConverter, methodBuilder, field, indexParameter);
                methodBuilder.returns(apiClass);

                typeBuilder.addMethod(methodBuilder.build());
            }

            // set
            {
                ParameterSpec parameter = ParameterSpec.builder(apiClass, naming.paramName(apiClass), FINAL).build();

                MethodSpec.Builder methodBuilder = createMethod(naming.simpleSetterName(name -> true), dataPropertyMaker.getIndexClass(), apiClass)
                    .addParameter(indexParameter)
                    .addParameter(parameter);
                converter.convertSetter(propertyConverter, methodBuilder, field, indexParameter, parameter);

                typeBuilder.addMethod(methodBuilder.build());
            }

            // extra
            dataPropertyMaker.addExtras(typeBuilder, field, indexParameter, propertyConverter, this, naming);
        }

        return typeBuilder.build();
    }
}
