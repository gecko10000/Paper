package io.papermc.generator.rewriter.data.yml;

public class ImportMapping {

    public ImportSet imports;
    public ImportSet staticImports;

    public ImportSet getImports() {
        return this.imports;
    }

    public ImportSet getStaticImports() {
        return this.staticImports;
    }
}
