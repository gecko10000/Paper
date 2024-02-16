package io.papermc.generator.rewriter.types.simple;

import io.papermc.generator.rewriter.types.EnumCloneRewriter;
import io.papermc.generator.rewriter.types.EnumRegistryRewriter;
import java.util.Map;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraft.world.level.saveddata.maps.MapDecoration;
import org.bukkit.block.banner.PatternType;
import org.bukkit.map.MapCursor;

import static io.papermc.generator.utils.Formatting.quoted;

public class MapCursorTypeRewriter extends EnumCloneRewriter<MapDecoration.Type, MapCursor.Type> {

    private static final Map<String, String> FIELD_RENAMES = Map.of(
        "PLAYER", "WHITE_POINTER",
        "FRAME", "GREEN_POINTER",
        "RED_MARKER", "RED_POINTER",
        "BLUE_MARKER", "BLUE_POINTER",
        "TARGET_X", "WHITE_CROSS",
        "TARGET_POINT", "RED_MARKER",
        "PLAYER_OFF_MAP", "WHITE_CIRCLE",
        "PLAYER_OFF_LIMITS", "SMALL_WHITE_CIRCLE",
        "MONUMENT", "TEMPLE"
    );

    public MapCursorTypeRewriter(final String pattern) {
        super(MapCursor.Type.class, MapDecoration.Type.class,pattern, false);
    }

    @Override
    protected String rewriteEnumName(MapDecoration.Type type) {
        String internalName = super.rewriteEnumName(type);
        return FIELD_RENAMES.getOrDefault(internalName, internalName);
    }

    @Override
    protected String rewriteEnumValue(MapDecoration.Type type) {
        return String.valueOf(type.ordinal());
    }
}
