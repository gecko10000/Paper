package io.papermc.generator.rewriter.types.simple;

import io.papermc.generator.rewriter.SearchMetadata;
import io.papermc.generator.rewriter.SearchReplaceRewriter;
import io.papermc.generator.rewriter.types.Types;
import io.papermc.generator.utils.BlockEntityMapping;
import io.papermc.generator.utils.Formatting;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class CraftBlockEntityStateMapping extends SearchReplaceRewriter {

    public CraftBlockEntityStateMapping(final String pattern) {
        super(Types.CRAFT_BLOCKSTATES, pattern, false);
    }

    @Override
    protected void insert(final SearchMetadata metadata, final StringBuilder builder) {
        BlockEntityMapping.MAPPING.entrySet().stream().sorted(Formatting.alphabeticKeyOrder(entry -> entry.getKey().location().getPath())).forEach(entry -> {
            builder.append(metadata.indent());
            builder.append("register(%s.%s, %s.class, %s::new);".formatted(
                BlockEntityType.class.getSimpleName(), Formatting.formatPathAsField(entry.getKey().location().getPath()),
                entry.getValue(), entry.getValue()));
            builder.append('\n');
        });
    }
}
