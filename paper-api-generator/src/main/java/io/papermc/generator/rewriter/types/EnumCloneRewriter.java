package io.papermc.generator.rewriter.types;

import com.google.common.base.Preconditions;
import io.papermc.generator.rewriter.SearchMetadata;
import java.util.Arrays;

public class EnumCloneRewriter<T extends Enum<T>, A extends Enum<A>> extends EnumRewriter<T, A> {

    private final Class<T> basedOn;

    public EnumCloneRewriter(final Class<A> rewriteClass, final Class<T> basedOn, final String pattern, boolean equalsSize) {
        super(rewriteClass, pattern, equalsSize);
        this.basedOn = basedOn;
    }

    @Override
    protected Iterable<T> getValues() {
        return Arrays.asList(this.basedOn.getEnumConstants());
    }

    @Override
    protected String rewriteEnumName(final T item) {
        return item.name();
    }

    @Override
    protected void insert(final SearchMetadata metadata, final StringBuilder builder) {
        Preconditions.checkState(metadata.replacedContent().stripTrailing().endsWith(";"), "The generated comments must enclose the whole enum in the clone enum rewriter");

        super.insert(metadata, builder);
    }
}
