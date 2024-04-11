package io.papermc.generator.rewriter.replace;

import io.papermc.generator.rewriter.context.ImportCollector;

public record SearchMetadata(ImportCollector importCollector, String indent, String replacedContent, int line) {
}
