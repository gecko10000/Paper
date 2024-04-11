package io.papermc.generator.rewriter.parser;

public enum ClosureType {
    PARENTHESIS("(", ")"),
    CURLY_BRACKET("{", "}"),
    BRACKET("[", "]"),
    COMMENT("/*", "*/");

    public final String start;
    public final String end;

    ClosureType(String start, String end) {
        this.start = start;
        this.end = end;
    }
}
