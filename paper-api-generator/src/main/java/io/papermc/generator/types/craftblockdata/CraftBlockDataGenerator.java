package io.papermc.generator.types.craftblockdata;

import com.google.common.base.CaseFormat;
import com.google.common.collect.ImmutableMap;
import com.google.common.primitives.Primitives;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import io.papermc.generator.types.SimpleGenerator;
import io.papermc.generator.types.craftblockdata.converter.Converter;
import io.papermc.generator.types.craftblockdata.converter.Converters;
import io.papermc.generator.utils.Annotations;
import io.papermc.generator.utils.BlockStateMapping;
import net.minecraft.core.FrontAndTop;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import org.bukkit.block.data.BlockData;

import javax.lang.model.SourceVersion;

import java.util.Map;
import java.util.Optional;

import static javax.lang.model.element.Modifier.FINAL;
import static javax.lang.model.element.Modifier.PRIVATE;
import static javax.lang.model.element.Modifier.PUBLIC;
import static javax.lang.model.element.Modifier.STATIC;

public class CraftBlockDataGenerator extends SimpleGenerator {

    private static final String PACKAGE = "org.bukkit.craftbukkit.block";

    private final Class<? extends Block> blockClass;
    private final BlockStateMapping.BlockData blockData;
    private final Class<? extends BlockData> baseClass;

    protected CraftBlockDataGenerator(final Class<? extends Block> blockClass, final BlockStateMapping.BlockData blockData, final Class<? extends BlockData> baseClass) {
        super(blockData.impl(), PACKAGE + ".impl");
        this.blockClass = blockClass;
        this.blockData = blockData;
        this.baseClass = baseClass;
    }

    private static final Prefix IS_PREFIX = getter("is");
    private static final Map<Property<?>, Prefix> FLUENT_PREFIX = ImmutableMap.<Property<?>, Prefix>builder()
        .put(BlockStateProperties.WATERLOGGED, IS_PREFIX)
        .put(BlockStateProperties.OPEN, IS_PREFIX)
        .put(BlockStateProperties.OCCUPIED, IS_PREFIX)
        .put(BlockStateProperties.LIT, IS_PREFIX)
        .put(BlockStateProperties.DRAG, IS_PREFIX)
        .put(BlockStateProperties.ATTACH_FACE, both("getAttached", "setAttached")) // todo remove this once switch methods are gone
        .put(BlockStateProperties.SIGNAL_FIRE, IS_PREFIX)
        .put(BlockStateProperties.UP, IS_PREFIX)
        .put(BlockStateProperties.CONDITIONAL, IS_PREFIX)
        .put(BlockStateProperties.POWERED, IS_PREFIX)
        .put(BlockStateProperties.CRAFTING, IS_PREFIX)
        .put(BlockStateProperties.TRIGGERED, IS_PREFIX)
        .put(BlockStateProperties.INVERTED, IS_PREFIX)
        .put(BlockStateProperties.CRACKED, IS_PREFIX)
        .put(BlockStateProperties.EYE, getter("has"))
        .put(BlockStateProperties.IN_WALL, IS_PREFIX)
        .put(BlockStateProperties.SNOWY, IS_PREFIX)
        .put(BlockStateProperties.ENABLED, IS_PREFIX)
        .put(BlockStateProperties.HANGING, IS_PREFIX)
        .put(BlockStateProperties.PERSISTENT, IS_PREFIX)
        .put(BlockStateProperties.EXTENDED, IS_PREFIX)
        .put(BlockStateProperties.LOCKED, IS_PREFIX)
        .put(BlockStateProperties.BOTTOM, IS_PREFIX)
        .put(BlockStateProperties.BLOOM, IS_PREFIX)
        .put(BlockStateProperties.UNSTABLE, IS_PREFIX)
        .put(BlockStateProperties.DISARMED, IS_PREFIX)
        .put(BlockStateProperties.ATTACHED, IS_PREFIX)
        .put(BlockStateProperties.SHORT, IS_PREFIX)
        .put(BlockStateProperties.BERRIES, getter("has")) // spigot method rename
        .build();

    @Override
    protected TypeSpec getTypeSpec() {
        TypeSpec.Builder typeBuilder = TypeSpec.classBuilder(this.className)
            .addModifiers(PUBLIC)
            .addAnnotation(Annotations.GENERATED_FROM)
            .addAnnotation(Annotations.suppressWarnings("unused"))
            .superclass(ClassName.get(PACKAGE + ".data", "CraftBlockData"))
            .addSuperinterface(this.baseClass);

        MethodSpec constructor = MethodSpec.constructorBuilder()
            .addModifiers(PUBLIC)
            .addParameter(BlockState.class, "state")
            .addStatement("super(state)")
            .build();

        typeBuilder.addMethod(constructor);

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

            TypeName propertyType;
            if (property.getClass() == EnumProperty.class) {
                propertyType = ParameterizedTypeName.get(property.getClass(), property.getValueClass());
            } else {
                propertyType = TypeName.get(property.getClass());
            }

            FieldSpec.Builder fieldBuilder = FieldSpec.builder(propertyType, fieldName, PRIVATE, STATIC, FINAL)
                .initializer("$T.$N", fieldAccess, fieldName);


            typeBuilder.addField(fieldBuilder.build());

            String name = property.getName();
            String upperCamelName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, name);
            String lowerCamelName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, name);

            boolean withPrefix = this.withPrefix(name);
            Class<?> apiClass = property.getValueClass();
            if (Primitives.isWrapperType(apiClass)) {
                apiClass = Primitives.unwrap(apiClass);
            } else if (property instanceof EnumProperty<?>) {
                apiClass = BlockStateMapping.ENUM_BRIDGE.get((Class<? extends StringRepresentable>) apiClass);
                if (apiClass == null) {
                    throw new RuntimeException("Unknown type for " + property);
                }
            }

            Converter<?, ?> converter = Converters.VALUES.get(property);
            if (converter != null) {
                apiClass = converter.getApiType();
            }

            Prefix prefix = null;
            if (withPrefix) {
                prefix = FLUENT_PREFIX.get(property);
            }

            // getter
            {
                final String methodName;
                if (withPrefix) {
                    methodName = Optional.ofNullable(prefix).flatMap(Prefix::getter).orElse("get") + upperCamelName;
                } else {
                    methodName = lowerCamelName;
                }
                MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder(methodName)
                    .addModifiers(PUBLIC);
                if (this.methodExists(methodName)) {
                    methodBuilder.addAnnotation(Override.class);
                }
                if (converter == null) {
                    if (!(property instanceof EnumProperty<?>)) {
                        methodBuilder.addCode("return this.get($N);", fieldBuilder.build());
                    } else {
                        methodBuilder.addCode("return this.get($N, $T.class);", fieldBuilder.build(), apiClass);
                    }
                } else {
                    converter.convertGetter(methodBuilder, fieldBuilder.build());
                }
                methodBuilder.returns(apiClass);

                typeBuilder.addMethod(methodBuilder.build());
            }

            // setter
            {
                final String methodName;
                if (withPrefix) {
                    methodName = Optional.ofNullable(prefix).flatMap(Prefix::setter).orElse("set") + upperCamelName;
                } else {
                    methodName = lowerCamelName;
                }
                String paramName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, apiClass.getSimpleName());
                if (apiClass.isPrimitive()) {
                    paramName = lowerCamelName;
                }
                if (!SourceVersion.isName(paramName)) {
                    paramName = "_" + paramName;
                }
                ParameterSpec parameter = ParameterSpec.builder(apiClass, paramName, FINAL).build();

                MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder(methodName)
                    .addModifiers(PUBLIC)
                    .addParameter(parameter);
                if (this.methodExists(methodName, apiClass)) {
                    methodBuilder.addAnnotation(Override.class);
                }

                if (converter != null) {
                    converter.convertSetter(methodBuilder, fieldBuilder.build(), parameter);
                } else {
                    methodBuilder.addCode("this.set($N, $N);", fieldBuilder.build(), parameter);
                }

                typeBuilder.addMethod(methodBuilder.build());
            }
        }

        return typeBuilder.build();
    }

    private boolean methodExists(String name, Class<?>... parameterTypes) {
        try {
            this.baseClass.getMethod(name, parameterTypes);
            return true;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }

    private boolean withPrefix(String name) {
        if (name.startsWith("is_") || name.startsWith("has_")) {
            return false;
        }
        return true;
    }

    private record Prefix(Optional<String> getter, Optional<String> setter) {}
    private static Prefix getter(String prefix) {
        return new Prefix(Optional.of(prefix), Optional.empty());
    }
    private static Prefix setter(String prefix) {
        return new Prefix(Optional.empty(), Optional.of(prefix));
    }
    private static Prefix both(String getter, String setter) {
        return new Prefix(Optional.of(getter), Optional.of(setter));
    }
}
