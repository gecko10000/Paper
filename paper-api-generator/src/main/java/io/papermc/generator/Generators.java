package io.papermc.generator;

import io.papermc.generator.rewriter.SourceRewriter;
import io.papermc.generator.rewriter.types.EnumRegistryRewriter;
import io.papermc.generator.types.registry.GeneratedKeyType;
import io.papermc.generator.types.SourceGenerator;
import io.papermc.generator.types.goal.MobGoalGenerator;
import io.papermc.paper.registry.RegistryKey;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import org.bukkit.Fluid;
import org.bukkit.GameEvent;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Biome;
import org.bukkit.generator.structure.Structure;
import org.bukkit.generator.structure.StructureType;
import org.bukkit.inventory.meta.trim.TrimMaterial;
import org.bukkit.inventory.meta.trim.TrimPattern;

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
        new EnumRegistryRewriter<>(Sound.class, Registries.SOUND_EVENT, "Sound", true),
        new EnumRegistryRewriter<>(Attribute.class, Registries.ATTRIBUTE, "Attribute", true),
        new EnumRegistryRewriter<>(Biome.class, Registries.BIOME, "Biome", false),
        //new EnumRegistryRewriter<>(EntityType.class, Registries.ENTITY_TYPE, "EntityType", false) seems complex to get the typeId?
    };

    private static <T, A> SourceGenerator simpleKey(final String className, final Class<A> apiType, final ResourceKey<? extends Registry<T>> registryKey, final RegistryKey<A> apiRegistryKey, final boolean publicCreateKeyMethod) {
        return new GeneratedKeyType<>(className, apiType, "io.papermc.paper.registry.keys", registryKey, apiRegistryKey, publicCreateKeyMethod);
    }
}
