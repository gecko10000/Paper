package io.papermc.generator.rewriter.utils;

public final class ClassHelper {

    public static String getDeclaredType(Class<?> clazz) {
        if (clazz.isAnnotation()) {
            return "@interface";
        }
        if (clazz.isInterface()) {
            return "interface";
        }
        if (clazz.isEnum()) {
            return "enum";
        }
        if (clazz.isRecord()) {
            return "record";
        }
        return "class";
    }

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

    public static String retrieveFullNestedName(Class<?> clazz) {
        String fqn = clazz.getCanonicalName();
        return fqn.substring(clazz.getPackageName().length() + 1);
    }

    private ClassHelper() {
    }
}
