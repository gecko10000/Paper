package io.papermc.generator.types.enumgen;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import io.papermc.generator.types.SimpleGenerator;
import io.papermc.generator.utils.Annotations;
import javax.lang.model.element.Modifier;
import net.minecraft.world.entity.Pose;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;

@DefaultQualifier(NonNull.class)
public class PoseGenerator extends SimpleGenerator {

    public PoseGenerator(final String keysClassName, final String pkg) {
        super(keysClassName, pkg);
    }

    @Override
    protected TypeSpec getTypeSpec() {
        TypeSpec.Builder typeBuilder = TypeSpec.enumBuilder(this.className)
            .addModifiers(Modifier.PUBLIC)
            .addAnnotations(Annotations.CLASS_HEADER);


        for (Pose nmsPose : Pose.values()) {
            String fieldName = nmsPose.name();
            typeBuilder.addEnumConstant(fieldName);
        }

        return typeBuilder.build();
    }

    @Override
    protected JavaFile.Builder file(JavaFile.Builder builder) {
        return builder
            .skipJavaLangImports(true);
    }

}
