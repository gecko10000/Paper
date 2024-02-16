package io.papermc.generator;

import io.papermc.generator.rewriter.SourceRewriter;
import io.papermc.generator.rewriter.types.EnumCloneRewriter;
import io.papermc.generator.rewriter.types.EnumRegistryRewriter;
import io.papermc.generator.rewriter.types.simple.MapCursorTypeRewriter;
import io.papermc.generator.rewriter.types.simple.MapPaletteRewriter;
import io.papermc.generator.rewriter.types.RegistryFieldRewriter;
import io.papermc.generator.rewriter.types.TagRewriter;
import io.papermc.generator.rewriter.types.simple.PatternTypeRewriter;
import io.papermc.generator.utils.ExperimentalSounds;
import io.papermc.generator.types.registry.GeneratedKeyType;
import io.papermc.generator.types.SourceGenerator;
import io.papermc.generator.types.goal.MobGoalGenerator;
import io.papermc.generator.utils.Formatting;
import io.papermc.paper.registry.RegistryKey;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import org.bukkit.Art;
import org.bukkit.Fluid;
import org.bukkit.GameEvent;
import org.bukkit.Sound;
import org.bukkit.Tag;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Biome;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.Cat;
import org.bukkit.entity.Frog;
import org.bukkit.entity.Villager;
import org.bukkit.generator.structure.Structure;
import org.bukkit.generator.structure.StructureType;
import org.bukkit.inventory.meta.trim.TrimMaterial;
import org.bukkit.inventory.meta.trim.TrimPattern;
import org.bukkit.potion.PotionType;
import org.bukkit.scoreboard.DisplaySlot;

import static io.papermc.generator.utils.Formatting.quoted;

public interface Generators {

    SourceGenerator[] API = {
        simpleKey("GameEventKeys", GameEvent.class, Registries.GAME_EVENT, RegistryKey.GAME_EVENT, true),
        simpleKey("BiomeKeys", Biome.class, Registries.BIOME, RegistryKey.BIOME, true),
        simpleKey("TrimMaterialKeys", TrimMaterial.class, Registries.TRIM_MATERIAL, RegistryKey.TRIM_MATERIAL, true),
        simpleKey("TrimPatternKeys", TrimPattern.class, Registries.TRIM_PATTERN, RegistryKey.TRIM_PATTERN, true),
        simpleKey("StructureKeys", Structure.class, Registries.STRUCTURE, RegistryKey.STRUCTURE, true),
        simpleKey("StructureTypeKeys", StructureType.class, Registries.STRUCTURE_TYPE, RegistryKey.STRUCTURE_TYPE, false),
        new MobGoalGenerator("VanillaGoal", "com.destroystokyo.paper.entity.ai"),
        /*new SoundGenerator("Sound", "org.bukkit"), todo extract fields
        new BiomeGenerator("Biome", "org.bukkit.block"),
        new AttributeGenerator("Attribute", "org.bukkit.attribute"),
        new StructureTypeGenerator("StructureType", "org.bukkit.generator.structure"),
        new StructureGenerator("Structure", "org.bukkit.generator.structure"),
        new LegacyKeyedRegistryGenerator<>("TrimPattern", "org.bukkit.inventory.meta.trim", Registries.TRIM_PATTERN, RegistryKey.TRIM_PATTERN),
        new LegacyKeyedRegistryGenerator<>("TrimMaterial", "org.bukkit.inventory.meta.trim", Registries.TRIM_MATERIAL, RegistryKey.TRIM_MATERIAL),
        new TagGenerator("Tag", "org.bukkit")*/
    };

    SourceRewriter[] API_REWRITE = {
        //new EnumCloneRewriter(Pose.class, net.minecraft.world.entity.Pose.class, "Pose", false)
        new EnumRegistryRewriter<>(Fluid.class, Registries.FLUID, "Fluid", false),
        new EnumRegistryRewriter<>(Sound.class, Registries.SOUND_EVENT, "Sound", true) {
            @Override
            protected String getExperimentalValue(Holder.Reference<SoundEvent> reference) {
                String result = super.getExperimentalValue(reference);
                if (result != null) {
                    return result;
                }
                return ExperimentalSounds.findExperimentalValue(reference.key().location());
            }
        },
        new EnumRegistryRewriter<>(Biome.class, Registries.BIOME, "Biome", false),
        new EnumRegistryRewriter<>(Frog.Variant.class, Registries.FROG_VARIANT, "FrogVariant", false),
        new EnumRegistryRewriter<>(Villager.Type.class, Registries.VILLAGER_TYPE, "VillagerType", false),
        new EnumRegistryRewriter<>(Attribute.class, Registries.ATTRIBUTE, "Attribute", true),
        new EnumRegistryRewriter<>(Cat.Type.class, Registries.CAT_VARIANT, "CatType", true),
        new EnumRegistryRewriter<>(PotionType.class, Registries.POTION, "PotionType", true) {
            @Override
            protected String rewriteEnumName(Holder.Reference<Potion> reference) {
                if (reference.key() == Potions.EMPTY_ID) {
                    return "UNCRAFTABLE";
                }
                return super.rewriteEnumName(reference);
            }
        },
        new EnumRegistryRewriter<>(Art.class, Registries.PAINTING_VARIANT, "Art", true) {

            private static final int PIXELS_PER_BLOCK = 16;
            @Override
            protected String rewriteEnumValue(Holder.Reference<PaintingVariant> reference) {
                PaintingVariant variant = reference.value();
                return "%d, %d, %d".formatted(
                    BuiltInRegistries.PAINTING_VARIANT.getId(reference.value()),
                    Mth.positiveCeilDiv(variant.getWidth(), PIXELS_PER_BLOCK),
                    Mth.positiveCeilDiv(variant.getHeight(), PIXELS_PER_BLOCK)
                );
            }
        },
        new PatternTypeRewriter("PatternType"),
        new MapCursorTypeRewriter("MapCursorType"),
        new EnumCloneRewriter<>(DisplaySlot.class, net.minecraft.world.scores.DisplaySlot.class, "DisplaySlot", false) {
            @Override
            protected String rewriteEnumName(net.minecraft.world.scores.DisplaySlot slot) {
                if (slot == net.minecraft.world.scores.DisplaySlot.LIST) {
                    return "PLAYER_LIST";
                }

                return Formatting.formatPathAsField(slot.getSerializedName());
            }

            @Override
            protected String rewriteEnumValue(net.minecraft.world.scores.DisplaySlot slot) {
                return quoted(slot.getSerializedName());
            }
        },
        //new EnumRegistryRewriter<>(EntityType.class, Registries.ENTITY_TYPE, "EntityType", false), seems complex to get the typeId?
        new RegistryFieldRewriter<>(Structure.class, Registries.STRUCTURE, "Structure", "getStructure"),
        new RegistryFieldRewriter<>(StructureType.class, Registries.STRUCTURE_TYPE, "StructureType", "getStructureType"),
        new RegistryFieldRewriter<>(TrimPattern.class, Registries.TRIM_PATTERN, "TrimPattern", null),
        new RegistryFieldRewriter<>(TrimMaterial.class, Registries.TRIM_MATERIAL, "TrimMaterial", null),
        new RegistryFieldRewriter<>(DamageType.class, Registries.DAMAGE_TYPE, "DamageType", "getDamageType"),
        new TagRewriter(Tag.class, "Tag"),
        new MapPaletteRewriter("MapPalette#colors"),
    };

    private static <T, A> SourceGenerator simpleKey(final String className, final Class<A> apiType, final ResourceKey<? extends Registry<T>> registryKey, final RegistryKey<A> apiRegistryKey, final boolean publicCreateKeyMethod) {
        return new GeneratedKeyType<>(className, apiType, "io.papermc.paper.registry.keys", registryKey, apiRegistryKey, publicCreateKeyMethod);
    }
}
