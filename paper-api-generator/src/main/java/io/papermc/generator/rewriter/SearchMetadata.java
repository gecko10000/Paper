package io.papermc.generator.rewriter;

import io.papermc.generator.rewriter.utils.ImportCollector;

public record SearchMetadata(ImportCollector importCollector, String indent, String replacedContent, int line) {
}
