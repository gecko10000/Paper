package io.papermc.generator.rewriter.context;

import io.papermc.generator.rewriter.ClassNamed;
import io.papermc.generator.utils.ClassHelper;
import org.jetbrains.annotations.VisibleForTesting;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ImportCollectorType implements ImportCollector {

    private final Map<Class<?>, String> typeCache = new HashMap<>();

    private final Set<String> imports = new HashSet<>();
    private final Set<String> globalImports = new HashSet<>();
    private final Map<String, String> staticImports = new HashMap<>(); // <fqn.alias:alias>

    private final ClassNamed rewriteClass;

    public ImportCollectorType(ClassNamed rewriteClass) {
        this.rewriteClass = rewriteClass;
    }

    @VisibleForTesting
    public void addImport(String fqn) {
        if (fqn.endsWith("*")) {
            this.globalImports.add(fqn.substring(0, fqn.lastIndexOf('.')));
        } else {
            this.imports.add(fqn);
        }
    }

    @VisibleForTesting
    public void addStaticImport(String fqn) { // todo support star import?
        this.staticImports.put(fqn, fqn.substring(fqn.lastIndexOf('.') + 1));
    }

    @Override
    public String getStaticAlias(String fqn) {
        return this.staticImports.getOrDefault(fqn, fqn);
    }

    @Override
    public String getTypeName(Class<?> clazz) {
        return this.typeCache.computeIfAbsent(clazz, type -> {
            Class<?> rootClass = ClassHelper.getRootClass(type);
            final String typeName;
            if (this.imports.contains(rootClass.getName()) ||
                type.getPackageName().equals(this.rewriteClass.packageName()) || // same package don't need fqn too (include self class too)
                this.globalImports.contains(type.getPackageName())) { // star import
                typeName = ClassHelper.retrieveFullNestedName(type);
            } else {
                typeName = type.getCanonicalName();
            }
            return typeName;
        });
    }

    private void addImportLine(String importLine) {
        if (importLine.startsWith("import static ")) {
            addStaticImport(importLine.substring("import static ".length()));
        } else {
            addImport(importLine.substring("import ".length()));
        }
    }

    @Override
    public void consume(String line) {
        for (String rawImport : line.split(";")) {
            String importLine = rawImport.trim();
            if (importLine.startsWith("import ")) {
                addImportLine(importLine);
            }
        }
    }
}
