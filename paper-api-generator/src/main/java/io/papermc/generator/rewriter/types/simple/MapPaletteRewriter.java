package io.papermc.generator.rewriter.types.simple;

import io.papermc.generator.rewriter.replace.SearchMetadata;
import io.papermc.generator.rewriter.replace.SearchReplaceRewriter;
import net.minecraft.world.level.material.MapColor;
import org.bukkit.map.MapPalette;

import java.awt.Color;

public class MapPaletteRewriter extends SearchReplaceRewriter {

    public MapPaletteRewriter(final String pattern) {
        super(MapPalette.class, pattern, false);
    }

    @Override
    protected void insert(final SearchMetadata metadata, final StringBuilder builder) {
        for (final MapColor mapColor : MapColor.MATERIAL_COLORS) {
            if (mapColor == null) {
                continue;
            }

            for (final MapColor.Brightness brightness : MapColor.Brightness.values()) {
                builder.append(metadata.indent());
                Color color = new Color(mapColor.calculateRGBColor(brightness), true);
                if (color.getAlpha() != 0xff) {
                    builder.append("new %s(%d, %d, %d, %d),".formatted(color.getClass().getSimpleName(), color.getBlue(), color.getGreen(), color.getRed(), color.getAlpha()));
                } else {
                    builder.append("new %s(%d, %d, %d),".formatted(color.getClass().getSimpleName(), color.getBlue(), color.getGreen(), color.getRed())); // int is encoded as blue << 16 | green << 8 | red (BGR and not RGB)
                }
                builder.append('\n');
            }
        }
    }
}
