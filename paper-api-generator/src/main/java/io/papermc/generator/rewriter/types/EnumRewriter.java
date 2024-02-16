package io.papermc.generator.rewriter.types;

import io.papermc.generator.rewriter.SearchMetadata;
import io.papermc.generator.rewriter.SearchReplaceRewriter;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import java.util.Iterator;

public abstract class EnumRewriter<T, A extends Enum<A>> extends SearchReplaceRewriter {

    @MonotonicNonNull
    private Iterator<T> values;

    protected EnumRewriter(final Class<A> rewriteClass, final String pattern, final boolean equalsSize) {
        super(rewriteClass, pattern, equalsSize);
    }

    protected abstract Iterable<T> getValues();

    protected abstract String rewriteEnumName(T item);

    protected String rewriteEnumValue(T item) {
        return null;
    }

    protected void rewriteAnnotation(T item, StringBuilder builder, SearchMetadata metadata) {}

    private void appendEnumValue(StringBuilder builder, SearchMetadata metadata, boolean reachEnd) {
        T item = this.values.next();

        this.rewriteAnnotation(item, builder, metadata);

        builder.append(metadata.indent()).append(this.rewriteEnumName(item));
        String value = this.rewriteEnumValue(item);
        if (value != null) {
            builder.append('(').append(this.rewriteEnumValue(item)).append(')');
        }
        if (reachEnd && !this.values.hasNext()) {
            builder.append(';');
        } else {
            builder.append(',');
        }
        builder.append('\n');
    }

    @Override
    protected void beginSearch() {
        this.values = this.getValues().iterator();
    }

    @Override
    protected void replaceLine(final SearchMetadata metadata, final StringBuilder builder) {
        appendEnumValue(builder, metadata, metadata.replacedContent().stripTrailing().endsWith(";"));
    }

    @Override
    protected void insert(final SearchMetadata metadata, final StringBuilder builder) {
        boolean reachEnd = metadata.replacedContent().stripTrailing().endsWith(";");

        while (this.values.hasNext()) {
            appendEnumValue(builder, metadata, reachEnd);
        }
    }
}
