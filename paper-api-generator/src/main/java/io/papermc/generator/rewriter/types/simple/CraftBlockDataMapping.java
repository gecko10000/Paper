package io.papermc.generator.rewriter.types.simple;

import io.papermc.generator.rewriter.replace.SearchMetadata;
import io.papermc.generator.rewriter.replace.SearchReplaceRewriter;
import io.papermc.generator.rewriter.types.Types;
import io.papermc.generator.utils.BlockStateMapping;
import java.util.Comparator;

public class CraftBlockDataMapping extends SearchReplaceRewriter {

    public CraftBlockDataMapping(final String pattern) {
        super(Types.CRAFT_BLOCKDATA, pattern, false);
    }

    @Override
    protected void insert(final SearchMetadata metadata, final StringBuilder builder) {
        BlockStateMapping.MAPPING.entrySet().stream().sorted(Comparator.comparing(entry -> entry.getKey().getCanonicalName())).forEach(entry -> {
            builder.append(metadata.indent());
            builder.append("register(%s.class, %s.%s::new);".formatted(entry.getKey().getCanonicalName(), Types.BLOCKDATA_IMPL_PACKAGE, entry.getValue().impl()));
            builder.append('\n');
        });
    }
}
