package io.papermc.generator.types.registry;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import io.papermc.generator.utils.Annotations;
import io.papermc.generator.utils.Javadocs;
import javax.lang.model.element.Modifier;
import net.kyori.adventure.translation.Translatable;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;

@DefaultQualifier(NonNull.class)
public class AttributeGenerator extends EnumRegistryGenerator<Attribute> {

    private static final String CLASS_HEADER = Javadocs.getVersionDependentClassHeader("Attributes");

    public AttributeGenerator(final String className, final String pkg) {
        super(className, pkg, Registries.ATTRIBUTE);
    }

    @Override
    public void addExtras(final TypeSpec.Builder builder, final FieldSpec keyField) {
        builder.addSuperinterface(Translatable.class);
        builder.addJavadoc(CLASS_HEADER);

        builder.addMethod(MethodSpec.methodBuilder("translationKey")
            .returns(String.class)
            .addModifiers(Modifier.PUBLIC)
            .addAnnotation(Annotations.NOT_NULL)
            .addAnnotation(Annotations.OVERRIDE)
            .addCode("return $S + this.$N.getKey();", "attribute.name.", keyField).build());
    }

}
