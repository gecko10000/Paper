package io.papermc.generator.rewriter.types.simple;

import io.papermc.generator.rewriter.types.EnumRegistryRewriter;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.alchemy.Potion;
import org.bukkit.potion.PotionType;
import java.util.Map;

public class PotionTypeRewriter extends EnumRegistryRewriter<Potion, PotionType> {

    private static final Map<String, String> FIELD_RENAMES = Map.of(
        "EMPTY", "UNCRAFTABLE",
        "HEALING", "INSTANT_HEAL",
        "REGENERATION", "REGEN",
        "SWIFTNESS", "SPEED",
        "LEAPING", "JUMP",
        "HARMING", "INSTANT_DAMAGE"
    );

    public PotionTypeRewriter(final String pattern) {
        super(PotionType.class, Registries.POTION, pattern, true);
    }

    @Override
    protected String rewriteEnumName(Holder.Reference<Potion> reference) {
        String internalName = super.rewriteEnumName(reference);
        return FIELD_RENAMES.getOrDefault(internalName, internalName);
    }
}
