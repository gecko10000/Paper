package io.papermc.generator.types.registry;

import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import io.papermc.generator.Main;
import io.papermc.generator.utils.Annotations;
import io.papermc.generator.utils.Javadocs;
import io.papermc.generator.utils.RegistryUtils;
import java.util.List;
import java.util.Map;
import javax.lang.model.element.Modifier;
import net.kyori.adventure.translation.Translatable;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;

@DefaultQualifier(NonNull.class)
public class BiomeGenerator extends EnumRegistryGenerator<Biome> {

    private static final String CLASS_HEADER = Javadocs.getVersionDependentClassHeader("Biomes");
    private final List<ResourceKey<Biome>> experimental;

    public BiomeGenerator(final String keysClassName, final String pkg) {
        super(keysClassName, pkg, Registries.BIOME);
        Registry<Biome> biomes = Main.REGISTRY_ACCESS.registryOrThrow(this.registryResourceKey);
        this.experimental = RegistryUtils.collectExperimentalKeys(biomes, biomes.key());
    }

    @Override
    public void addExtras(final TypeSpec.Builder builder) {
        builder.addSuperinterface(Translatable.class);
        builder.addJavadoc(CLASS_HEADER);

        builder.addMethod(MethodSpec.methodBuilder("translationKey")
            .returns(String.class)
            .addModifiers(Modifier.PUBLIC)
            .addAnnotation(Annotations.NOT_NULL)
            .addAnnotation(Annotations.OVERRIDE)
            .addCode("return $S + $L;", "biome.minecraft.", "this.key.getKey()").build());

        builder.addEnumConstant("CUSTOM", TypeSpec.anonymousClassBuilder("$S", "custom").build());
    }

    @Override
    public boolean isExperimental(final Map.Entry<ResourceKey<Biome>, Biome> entry) {
        return this.experimental.contains(entry.getKey());
    }

}
