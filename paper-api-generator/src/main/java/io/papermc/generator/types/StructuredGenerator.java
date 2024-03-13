package io.papermc.generator.types;

import com.squareup.javapoet.MethodSpec;
import io.papermc.generator.utils.Annotations;

import static javax.lang.model.element.Modifier.PUBLIC;

public abstract class StructuredGenerator<T> extends SimpleGenerator {

    protected final Class<? extends T> baseClass;

    protected StructuredGenerator(final Class<T> baseClass, final String className, final String packageName) {
        super(className, packageName);
        this.baseClass = baseClass;
    }

    public MethodSpec.Builder createMethod(String name, Class<?>... parameterTypes) {
        MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder(name)
            .addModifiers(PUBLIC);
        if (methodExists(name, parameterTypes)) {
            methodBuilder.addAnnotation(Annotations.OVERRIDE);
        }
        return methodBuilder;
    }

    protected boolean methodExists(String name, Class<?>... parameterTypes) {
        try {
            this.baseClass.getMethod(name, parameterTypes);
            return true;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }

}
