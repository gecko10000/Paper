package io.papermc.generator.types.enumgen;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import io.papermc.generator.types.SimpleGenerator;
import io.papermc.generator.utils.Annotations;
import javax.lang.model.element.Modifier;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;

@DefaultQualifier(NonNull.class)
public class EnumGenerator<T extends Enum<T>> extends SimpleGenerator {

    private final Class<T> enumClass;

    public EnumGenerator(final Class<T> enumClass, final String pkg) {
        super(enumClass.getSimpleName(), pkg);
        this.enumClass = enumClass;
    }

    @Override
    protected TypeSpec getTypeSpec() {
        TypeSpec.Builder typeBuilder = TypeSpec.enumBuilder(this.className)
            .addModifiers(Modifier.PUBLIC)
            .addAnnotations(Annotations.CLASS_HEADER);

        for (T enumValue : this.enumClass.getEnumConstants()) {
            typeBuilder.addEnumConstant(enumValue.name());
        }

        return typeBuilder.build();
    }

    @Override
    protected JavaFile.Builder file(JavaFile.Builder builder) {
        return builder
            .skipJavaLangImports(true);
    }

}
