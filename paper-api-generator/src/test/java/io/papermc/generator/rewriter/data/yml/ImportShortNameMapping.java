package io.papermc.generator.rewriter.data.yml;

import java.util.Map;

public class ImportShortNameMapping {

    public Map<String, String> imports;
    public Map<String, String> staticImports;

    /**
     * Note: unlike {@link #getStaticImports()} (which only use a dot), nested class must be identified using '$' since the typeName is then
     * converted into a class object
     */
    public Map<String, String> getImports() {
        return this.imports;
    }

    public Map<String, String> getStaticImports() {
        return this.staticImports;
    }
}
