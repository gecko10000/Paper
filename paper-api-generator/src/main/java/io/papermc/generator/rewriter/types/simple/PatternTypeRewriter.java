package io.papermc.generator.rewriter.types.simple;

import io.papermc.generator.rewriter.types.EnumRegistryRewriter;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BannerPattern;
import org.bukkit.block.banner.PatternType;

import java.util.Map;

import static io.papermc.generator.utils.Formatting.quoted;

public class PatternTypeRewriter extends EnumRegistryRewriter<BannerPattern, PatternType> {

    private static final Map<String, String> FIELD_RENAMES = Map.of(
        "SMALL_STRIPES", "STRIPE_SMALL",
        "DIAGONAL_UP_RIGHT", "DIAGONAL_RIGHT",
        "DIAGONAL_UP_LEFT", "DIAGONAL_LEFT_MIRROR",
        "DIAGONAL_RIGHT", "DIAGONAL_RIGHT_MIRROR",
        "CIRCLE", "CIRCLE_MIDDLE",
        "RHOMBUS", "RHOMBUS_MIDDLE",
        "HALF_VERTICAL_RIGHT", "HALF_VERTICAL_MIRROR",
        "HALF_HORIZONTAL_BOTTOM", "HALF_HORIZONTAL_MIRROR"
    );

    public PatternTypeRewriter(final String pattern) {
        super(PatternType.class, Registries.BANNER_PATTERN, pattern, true);
    }

    @Override
    protected String rewriteEnumName(Holder.Reference<BannerPattern> reference) {
        String internalName = super.rewriteEnumName(reference);
        return FIELD_RENAMES.getOrDefault(internalName, internalName);
    }

    @Override
    protected String rewriteEnumValue(Holder.Reference<BannerPattern> reference) {
        return "%s, %s".formatted(quoted(reference.value().getHashname()), super.rewriteEnumValue(reference));
    }
}
