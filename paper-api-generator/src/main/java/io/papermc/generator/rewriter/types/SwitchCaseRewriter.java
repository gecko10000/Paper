package io.papermc.generator.rewriter.types;

import io.papermc.generator.rewriter.SearchMetadata;
import io.papermc.generator.rewriter.SearchReplaceRewriter;
import io.papermc.generator.rewriter.ClassNamed;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import java.util.Iterator;

public abstract class SwitchCaseRewriter extends SearchReplaceRewriter {

    @MonotonicNonNull
    private Iterator<String> cases;

    protected SwitchCaseRewriter(final Class<?> rewriteClass, final String pattern, final boolean equalsSize) {
        super(rewriteClass, pattern, equalsSize);
    }

    protected SwitchCaseRewriter(final ClassNamed rewriteClass, final String pattern, final boolean equalsSize) {
        super(rewriteClass, pattern, equalsSize);
    }

    protected abstract Iterable<String> getCases();

    @Override
    protected void beginSearch() {
        this.cases = this.getCases().iterator();
    }

    private void appendCase(StringBuilder builder, SearchMetadata metadata) {
        builder.append(metadata.indent()).append("case ").append(this.cases.next()).append(':');
        builder.append('\n');
    }

    @Override
    protected void replaceLine(final SearchMetadata metadata, final StringBuilder builder) {
        appendCase(builder, metadata);
    }

    @Override
    protected void insert(final SearchMetadata metadata, final StringBuilder builder) {
        while (this.cases.hasNext()) {
            appendCase(builder, metadata);
        }
    }
}
