package io.papermc.generator.rewriter.types;

import io.papermc.generator.rewriter.replace.SearchMetadata;
import io.papermc.generator.rewriter.replace.SearchReplaceRewriter;
import io.papermc.generator.rewriter.ClassNamed;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import java.util.Iterator;

public abstract class EnumRewriter<T, A extends Enum<A>> extends SearchReplaceRewriter {

    @MonotonicNonNull
    private Iterator<T> values;

    protected EnumRewriter(final Class<A> rewriteClass, final String pattern, final boolean exactReplacement) {
        super(rewriteClass, pattern, exactReplacement);
    }

    protected EnumRewriter(final ClassNamed rewriteClass, final String pattern, final boolean exactReplacement) {
        super(rewriteClass, pattern, exactReplacement);
    }

    protected abstract Iterable<T> getValues();

    protected abstract String rewriteEnumName(T item);

    @Nullable
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
            builder.append('(').append(value).append(')');
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
