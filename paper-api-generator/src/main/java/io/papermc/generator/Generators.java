package io.papermc.generator;

import io.papermc.generator.rewriter.replace.CompositeRewriter;
import io.papermc.generator.rewriter.SourceRewriter;
import io.papermc.generator.rewriter.types.EnumCloneRewriter;
import io.papermc.generator.rewriter.types.EnumRegistryRewriter;
import io.papermc.generator.rewriter.types.simple.CraftBlockDataMapping;
import io.papermc.generator.rewriter.types.simple.CraftBlockEntityStateMapping;
import io.papermc.generator.rewriter.types.simple.CraftPotionUtilRewriter;
import io.papermc.generator.rewriter.types.simple.MapCursorTypeRewriter;
import io.papermc.generator.rewriter.types.simple.MapPaletteRewriter;
import io.papermc.generator.rewriter.types.RegistryFieldRewriter;
import io.papermc.generator.rewriter.types.TagRewriter;
import io.papermc.generator.rewriter.types.simple.MaterialRewriter;
import io.papermc.generator.rewriter.types.simple.MemoryKeyRewriter;
import io.papermc.generator.rewriter.types.simple.PatternTypeRewriter;
import io.papermc.generator.rewriter.types.simple.PotionTypeRewriter;
import io.papermc.generator.rewriter.types.simple.StatisticRewriter;
import io.papermc.generator.utils.experimental.ExperimentalSounds;
import io.papermc.generator.types.registry.GeneratedKeyType;
import io.papermc.generator.types.SourceGenerator;
import io.papermc.generator.types.goal.MobGoalGenerator;
import io.papermc.generator.utils.Formatting;
import io.papermc.paper.enchantments.EnchantmentRarity;
import io.papermc.paper.inventory.ItemRarity;
import io.papermc.paper.registry.RegistryKey;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.item.Instrument;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.enchantment.Enchantment;
import org.bukkit.Art;
import org.bukkit.Fluid;
import org.bukkit.GameEvent;
import org.bukkit.Material;
import org.bukkit.MusicInstrument;
import org.bukkit.Sound;
import org.bukkit.Tag;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Biome;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.Boat;
import org.bukkit.entity.Cat;
import org.bukkit.entity.Fox;
import org.bukkit.entity.Frog;
import org.bukkit.entity.Panda;
import org.bukkit.entity.Sniffer;
import org.bukkit.entity.TropicalFish;
import org.bukkit.entity.Villager;
import org.bukkit.generator.structure.Structure;
import org.bukkit.generator.structure.StructureType;
import org.bukkit.inventory.meta.trim.TrimMaterial;
import org.bukkit.inventory.meta.trim.TrimPattern;
import org.bukkit.inventory.recipe.CookingBookCategory;
import org.bukkit.inventory.recipe.CraftingBookCategory;
import org.bukkit.scoreboard.DisplaySlot;

import java.util.Locale;

import static io.papermc.generator.utils.Formatting.quoted;

public interface Generators {

    SourceGenerator[] API = {
        simpleKey("GameEventKeys", GameEvent.class, Registries.GAME_EVENT, RegistryKey.GAME_EVENT, true),
        simpleKey("BiomeKeys", Biome.class, Registries.BIOME, RegistryKey.BIOME, true),
        simpleKey("TrimMaterialKeys", TrimMaterial.class, Registries.TRIM_MATERIAL, RegistryKey.TRIM_MATERIAL, true),
        simpleKey("TrimPatternKeys", TrimPattern.class, Registries.TRIM_PATTERN, RegistryKey.TRIM_PATTERN, true),
        simpleKey("StructureKeys", Structure.class, Registries.STRUCTURE, RegistryKey.STRUCTURE, true),
        simpleKey("StructureTypeKeys", StructureType.class, Registries.STRUCTURE_TYPE, RegistryKey.STRUCTURE_TYPE, false),
        new MobGoalGenerator("VanillaGoal", "com.destroystokyo.paper.entity.ai")
        // todo extract fields for registry based api
    };

    private static <T, A> SourceGenerator simpleKey(final String className, final Class<A> apiType, final ResourceKey<? extends Registry<T>> registryKey, final RegistryKey<A> apiRegistryKey, final boolean publicCreateKeyMethod) {
        return new GeneratedKeyType<>(className, apiType, "io.papermc.paper.registry.keys", registryKey, apiRegistryKey, publicCreateKeyMethod);
    }

    SourceRewriter[] API_REWRITE = {
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
        //new EnumRegistryRewriter<>(EntityType.class, Registries.ENTITY_TYPE, "EntityType", true), seems complex to get the typeId?
        new EnumRegistryRewriter<>(Art.class, Registries.PAINTING_VARIANT, "Art", true) {

            private static final int PIXELS_PER_BLOCK = 16;
            @Override
            protected String rewriteEnumValue(Holder.Reference<PaintingVariant> reference) {
                PaintingVariant variant = reference.value();
                return "%d, %d, %d".formatted(
                    BuiltInRegistries.PAINTING_VARIANT.getId(variant),
                    Mth.positiveCeilDiv(variant.getWidth(), PIXELS_PER_BLOCK),
                    Mth.positiveCeilDiv(variant.getHeight(), PIXELS_PER_BLOCK)
                );
            }
        },
        new PotionTypeRewriter("PotionType"),
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
        new EnumCloneRewriter<>(ItemRarity.class, Rarity.class, "ItemRarity", false) {
            @Override
            protected String rewriteEnumValue(Rarity rarity) {
                return "%s.%s".formatted(NamedTextColor.class.getSimpleName(), NamedTextColor.NAMES.value(rarity.color.getName()).toString().toUpperCase(Locale.ENGLISH));
            }
        },
        new EnumCloneRewriter<>(EnchantmentRarity.class, Enchantment.Rarity.class, "EnchantmentRarity", false) {
            @Override
            protected String rewriteEnumValue(Enchantment.Rarity rarity) {
                return String.valueOf(rarity.getWeight());
            }
        },
        new EnumCloneRewriter<>(Sniffer.State.class, net.minecraft.world.entity.animal.sniffer.Sniffer.State.class, "SnifferState", false),
        new EnumCloneRewriter<>(Panda.Gene.class, net.minecraft.world.entity.animal.Panda.Gene.class, "PandaGene", false) {
            @Override
            protected String rewriteEnumValue(net.minecraft.world.entity.animal.Panda.Gene gene) {
                return String.valueOf(gene.isRecessive());
            }
        },
        new EnumCloneRewriter<>(CookingBookCategory.class, net.minecraft.world.item.crafting.CookingBookCategory.class, "CookingBookCategory", false),
        new EnumCloneRewriter<>(CraftingBookCategory.class, net.minecraft.world.item.crafting.CraftingBookCategory.class, "CraftingBookCategory", false),
        new EnumCloneRewriter<>(TropicalFish.Pattern.class, net.minecraft.world.entity.animal.TropicalFish.Pattern.class, "TropicalFishPattern", false),
        new EnumCloneRewriter<>(Fox.Type.class, net.minecraft.world.entity.animal.Fox.Type.class, "FoxType", false),
        CompositeRewriter.bind(
            new EnumCloneRewriter<>(Boat.Type.class, net.minecraft.world.entity.vehicle.Boat.Type.class, "BoatType", false) {
                @Override
                protected String rewriteEnumValue(net.minecraft.world.entity.vehicle.Boat.Type type) {
                    return "%s.%s".formatted(Material.class.getSimpleName(), BuiltInRegistries.BLOCK.getKey(type.getPlanks()).getPath().toUpperCase(Locale.ENGLISH));
                }
            },
            new EnumCloneRewriter<>(Boat.Status.class, net.minecraft.world.entity.vehicle.Boat.Status.class, "BoatStatus", false)
        ),
        CompositeRewriter.bind(
            new MaterialRewriter.Blocks("Blocks"),
            new MaterialRewriter.IsBlock("Material#isBlock"),
            new MaterialRewriter.IsSolid("Material#isSolid"),
            new MaterialRewriter.IsAir("Material#isAir"),
            //new MaterialRewriter.IsTransparent("Material#isTransparent"),
            new MaterialRewriter.IsFlammable("Material#isFlammable"),
            new MaterialRewriter.IsBurnable("Material#isBurnable"),
            new MaterialRewriter.IsOccluding("Material#isOccluding"),
            new MaterialRewriter.HasGravity("Material#hasGravity"),
            new MaterialRewriter.IsInteractable("Material#isInteractable"),
            new MaterialRewriter.GetHardness("Material#getHardness"),
            new MaterialRewriter.GetBlastResistance("Material#getBlastResistance"),
            new MaterialRewriter.GetSlipperiness("Material#getSlipperiness"),

            new MaterialRewriter.Items("Items"),
            new MaterialRewriter.IsItem("Material#isItem"),
            new MaterialRewriter.IsEdible("Material#isEdible"),
            new MaterialRewriter.IsRecord("Material#isRecord"),
            new MaterialRewriter.IsFuel("Material#isFuel"),
            new MaterialRewriter.GetCraftingRemainingItem("Material#getCraftingRemainingItem"),
            new MaterialRewriter.GetEquipmentSlot("Material#getEquipmentSlot")
        ),
        CompositeRewriter.bind(
            new StatisticRewriter.Custom("StatisticCustom"),
            new StatisticRewriter.Type("StatisticType")
        ),
        new RegistryFieldRewriter<>(Structure.class, Registries.STRUCTURE, "Structure", "getStructure"),
        new RegistryFieldRewriter<>(StructureType.class, Registries.STRUCTURE_TYPE, "StructureType", "getStructureType"),
        new RegistryFieldRewriter<>(TrimPattern.class, Registries.TRIM_PATTERN, "TrimPattern", null),
        new RegistryFieldRewriter<>(TrimMaterial.class, Registries.TRIM_MATERIAL, "TrimMaterial", null),
        new RegistryFieldRewriter<>(DamageType.class, Registries.DAMAGE_TYPE, "DamageType", "getDamageType"),
        new RegistryFieldRewriter<>(GameEvent.class, Registries.GAME_EVENT, "GameEvent", "getEvent"),
        new RegistryFieldRewriter<>(MusicInstrument.class, Registries.INSTRUMENT, "MusicInstrument", "getInstrument") {
            @Override
            protected String rewriteFieldName(Holder.Reference<Instrument> reference) {
                String internalName = super.rewriteFieldName(reference);
                int goatHornSuffixIndex = internalName.lastIndexOf("_GOAT_HORN");
                return goatHornSuffixIndex == -1 ? internalName : internalName.substring(0, goatHornSuffixIndex);
            }
        },
        new MemoryKeyRewriter("MemoryKey"),
        new TagRewriter(Tag.class, "Tag"),
        new MapPaletteRewriter("MapPalette#colors")
    };


    SourceRewriter[] SERVER_REWRITE = {
        new CraftBlockDataMapping("CraftBlockData#MAP"),
        new CraftBlockEntityStateMapping("CraftBlockEntityStates"),
        CompositeRewriter.bind(
            new StatisticRewriter.CraftCustom("CraftStatisticCustom"),
            new StatisticRewriter.CraftType("CraftStatisticType")
        ),
        CompositeRewriter.bind(
            new CraftPotionUtilRewriter("CraftPotionUtil#upgradeable", "strong"),
            new CraftPotionUtilRewriter("CraftPotionUtil#extendable", "long")
        )
    };

}
