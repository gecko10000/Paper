package io.papermc.generator.rewriter.types;

import com.google.common.collect.Multimap;
import io.papermc.generator.rewriter.SearchMetadata;
import io.papermc.generator.rewriter.SearchReplaceRewriter;

import java.util.Collection;

public abstract class SwitchRewriter extends SearchReplaceRewriter {

    protected String defaultValue;

    protected SwitchRewriter(final Class<?> rewriteClass, final String pattern, final boolean equalsSize) {
        super(rewriteClass, pattern, equalsSize);
    }

    protected abstract Multimap<String, String> getContent(); // <return:cases>

    @Override
    protected void insert(final SearchMetadata metadata, final StringBuilder builder) {
        Multimap<String, String> content = this.getContent();
        for (String key : content.keySet()) {
            Collection<String> conditions = content.get(key);
            for (String cond : conditions) {
                builder.append(metadata.indent()).append("case ").append(cond).append(':');
                builder.append('\n');
            }
            builder.append(metadata.indent()).append(INDENT_UNIT).append("return ").append(key).append(';');
            builder.append('\n');
        }

        if (this.defaultValue != null) {
            builder.append(metadata.indent()).append("default:");
            builder.append('\n');
            builder.append(metadata.indent()).append(INDENT_UNIT).append("return ").append(this.defaultValue).append(';');
            builder.append('\n');
        }
    }
}
