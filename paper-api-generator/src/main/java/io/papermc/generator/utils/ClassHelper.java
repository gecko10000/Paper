package io.papermc.generator.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Set;

public final class ClassHelper {

    public static Class<?> getRootClass(Class<?> clazz) {
        Class<?> rootClass = clazz;
        Class<?> parentClass = clazz;
        while (true) {
            parentClass = parentClass.getEnclosingClass();
            if (parentClass == null) {
                break;
            }
            rootClass = parentClass;
        }
        return rootClass;
    }

    public static Set<Class<?>> getAllInterfaces(Class<?> clazz, Class<?> ignored, Set<Class<?>> interfaces) {
        Class<?>[] classes = clazz.getInterfaces();
        interfaces.addAll(Arrays.asList(classes));
        for (Class<?> farClass : classes) {
            if (farClass == ignored) {
                continue;
            }
            getAllInterfaces(farClass, ignored, interfaces);
        }
        interfaces.remove(ignored);
        return interfaces;
    }

    public static String retrieveFullNestedName(Class<?> clazz) {
        String fqn = clazz.getCanonicalName();
        return fqn.substring(clazz.getPackageName().length() + 1);
    }

    public static Class<?> eraseType(Type type) {
        if (type instanceof Class<?> clazz) {
            return clazz;
        }
        if (type instanceof ParameterizedType complexType) {
            return eraseType(complexType.getRawType());
        }
        throw new UnsupportedOperationException("Don't know how to turn " + type + " into its base class!");
    }

    public static <T> Class<? extends T> classOr(String className, Class<? extends T> defaultClass) {
        try {
            return (Class<? extends T>) Class.forName(className);
        } catch (ClassNotFoundException ignored) {
            return defaultClass;
        }
    }

    private ClassHelper() {
    }
}
