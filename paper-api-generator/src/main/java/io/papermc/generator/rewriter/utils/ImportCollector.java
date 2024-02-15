package io.papermc.generator.rewriter.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ImportCollector {

    private final Map<Class<?>, String> typeCache = new HashMap<>();

    private final Set<String> imports = new HashSet<>();
    private final Set<String> globalImports = new HashSet<>();
    private final Map<String, String> staticImports = new HashMap<>(); // <fqn.alias:alias>

    private final Class<?> rewriteClass;

    public ImportCollector(Class<?> rewriteClass) {
        this.rewriteClass = rewriteClass;
    }

    public void addImport(String fqn) {
        if (fqn.endsWith("*")) {
            this.globalImports.add(fqn.substring(0, fqn.lastIndexOf('.')));
        } else {
            this.imports.add(fqn);
        }
    }

    public void addStaticImport(String fqn) { // todo support star import?
        this.staticImports.put(fqn, fqn.substring(fqn.lastIndexOf('.') + 1));
    }

    public String getStaticAlias(String fqn) {
        return this.staticImports.getOrDefault(fqn, fqn);
    }

    public String getTypeName(Class<?> clazz) {
        if (this.typeCache.containsKey(clazz)) {
            return this.typeCache.get(clazz);
        }

        Class<?> rootClass = clazz;
        Class<?> parentClass = clazz;
        while (true) {
            parentClass = parentClass.getEnclosingClass();
            if (parentClass == null) {
                break;
            }
            rootClass = parentClass;
        }

        final String typeName;
        if (this.rewriteClass == clazz ||
            this.imports.contains(rootClass.getName()) ||
            clazz.getPackageName().equals(this.rewriteClass.getPackageName()) || // same package don't need fqn too
            this.globalImports.contains(clazz.getPackageName())) { // star import
            typeName = retrieveFullNestedName(clazz);
        } else {
            typeName = clazz.getCanonicalName();
        }

        this.typeCache.put(clazz, typeName);
        return typeName;
    }

    public void consume(String line) {
        if (line.startsWith("import static ")) {
            addStaticImport(line.substring("import static ".length(), line.length() - 1));
        } else {
            addImport(line.substring("import ".length(), line.length() - 1));
        }
    }

    public static String retrieveFullNestedName(Class<?> clazz) {
        String fqn = clazz.getCanonicalName();
        return fqn.substring(clazz.getPackageName().length() + 1);
    }
}
