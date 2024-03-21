package io.papermc.generator.rewriter.types.simple;

import io.papermc.generator.rewriter.SearchMetadata;
import io.papermc.generator.rewriter.SearchReplaceRewriter;
import io.papermc.generator.rewriter.types.Types;
import io.papermc.generator.utils.Formatting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import org.bukkit.potion.PotionType;
import java.util.Locale;

@Deprecated(forRemoval = true)
public class CraftPotionUtilRewriter extends SearchReplaceRewriter {

    private final String statePrefix;

    public CraftPotionUtilRewriter(final String pattern, String statePrefix) {
        super(Types.CRAFT_POTION_UTIL, pattern, false);
        this.statePrefix = statePrefix;
    }

    @Override
    protected void insert(SearchMetadata metadata, StringBuilder builder) {
        String upperStatePrefix = statePrefix.toUpperCase(Locale.ENGLISH);
        BuiltInRegistries.POTION.keySet().stream()
            .filter(key -> BuiltInRegistries.POTION.containsKey(new ResourceLocation(key.getNamespace(), this.statePrefix + "_" + key.getPath())))
            .sorted(Formatting.alphabeticKeyOrder(ResourceLocation::getPath)).forEach(key -> {
                String internalName = Formatting.formatPathAsField(key.getPath());
                builder.append(metadata.indent());
                builder.append(".put(%s.%s, %s.%s_%s)".formatted(PotionType.class.getSimpleName(), PotionTypeRewriter.FIELD_RENAMES.getOrDefault(internalName, internalName), PotionType.class.getSimpleName(), upperStatePrefix, internalName));
                builder.append('\n');
            });
    }

}
