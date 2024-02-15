package io.papermc.generator.rewriter.types;

import com.google.common.base.Preconditions;
import io.papermc.generator.rewriter.SearchMetadata;
import io.papermc.generator.rewriter.SearchReplaceRewriter;

public class EnumCloneRewriter extends SearchReplaceRewriter {

    private final Enum<? extends Enum<?>>[] enums;

    public EnumCloneRewriter(final Class<? extends Enum<?>> rewriteClass, final Class<? extends Enum<?>> basedOn, final String pattern, boolean equalsSize) {
        super(rewriteClass, pattern, equalsSize);
        this.enums = basedOn.getEnumConstants();
    }

    private int index = 0;

    private void appendEnumValue(StringBuilder builder, String indent, Enum<? extends Enum<?>>[] enums, int index) {
        builder.append(indent).append(enums[index].name());
        if (index != enums.length - 1) {
            builder.append(',');
        } else {
            builder.append(';');
        }
        builder.append('\n');
    }

    @Override
    protected void replaceLine(final SearchMetadata metadata, final StringBuilder builder) {
        appendEnumValue(builder, metadata.indent(), this.enums, this.index++);
    }

    @Override
    protected void insert(final SearchMetadata metadata, final StringBuilder builder) {
        Preconditions.checkState(metadata.replacedContent().stripTrailing().endsWith(";"), "The generated comments must enclose the whole enum in the clone enum rewriter");

        for (int i = 0, len = this.enums.length; i < len; i++) {
            appendEnumValue(builder, metadata.indent(), this.enums, i);
        }
    }
}
