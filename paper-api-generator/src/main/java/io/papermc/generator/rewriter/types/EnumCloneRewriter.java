package io.papermc.generator.rewriter.types;

import io.papermc.generator.rewriter.ClassNamed;
import java.util.Arrays;

public class EnumCloneRewriter<T extends Enum<T>, A extends Enum<A>> extends EnumRewriter<T, A> { // not really a clone anymore

    private final Class<T> basedOn;

    public EnumCloneRewriter(final Class<A> rewriteClass, final Class<T> basedOn, final String pattern, boolean equalsSize) {
        this(new ClassNamed(rewriteClass), basedOn, pattern, equalsSize);
    }

    public EnumCloneRewriter(final ClassNamed rewriteClass, final Class<T> basedOn, final String pattern, boolean equalsSize) {
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
}
