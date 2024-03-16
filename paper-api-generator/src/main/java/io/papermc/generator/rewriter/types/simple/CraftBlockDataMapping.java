package io.papermc.generator.rewriter.types.simple;

import io.papermc.generator.rewriter.SearchMetadata;
import io.papermc.generator.rewriter.SearchReplaceRewriter;
import io.papermc.generator.rewriter.types.Types;
import io.papermc.generator.utils.BlockStateMapping;
import io.papermc.generator.utils.Formatting;

public class CraftBlockDataMapping extends SearchReplaceRewriter {

    public CraftBlockDataMapping(final String pattern) {
        super(Types.CRAFT_BLOCKDATA, pattern, false);
    }

    @Override
    protected void insert(final SearchMetadata metadata, final StringBuilder builder) {
        BlockStateMapping.MAPPING.entrySet().stream().sorted(Formatting.alphabeticKeyOrder(entry -> entry.getKey().getCanonicalName())).forEach(entry -> {
            builder.append(metadata.indent());
            builder.append("register(%s.class, %s.%s::new);".formatted(entry.getKey().getCanonicalName(), Types.BLOCKDATA_IMPL_PACKAGE, entry.getValue().impl()));
            builder.append('\n');
        });
    }
}
