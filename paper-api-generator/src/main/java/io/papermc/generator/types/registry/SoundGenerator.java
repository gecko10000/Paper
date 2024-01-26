package io.papermc.generator.types.registry;

import com.google.common.base.Suppliers;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.TypeSpec;
import io.papermc.generator.Main;
import io.papermc.generator.utils.Formatting;
import io.papermc.generator.utils.Javadocs;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import net.kyori.adventure.sound.Sound;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.flag.FeatureElement;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.BundleItem;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.DefaultQualifier;

@DefaultQualifier(NonNull.class)
public class SoundGenerator extends EnumRegistryGenerator<SoundEvent> {

    private static final String CLASS_HEADER = Javadocs.getVersionDependentClassHeader("Sounds");

    private static final Map<String, Supplier<Registry<? extends FeatureElement>>> FILTERED_REGISTRIES = FeatureElement.FILTERED_REGISTRIES.stream()
        .collect(Collectors.toMap((key) -> {
            if (key == Registries.ENTITY_TYPE) {
                return "entity";
            }
            return key.location().getPath();
        }, key -> Suppliers.memoize(() -> Main.REGISTRY_ACCESS.registryOrThrow(key))));

    private static final Set<String> EXPERIMENTAL_EXCEPTIONS = Set.of(
        "entity.generic.wind_burst"
    );

    public SoundGenerator(final String className, final String pkg) {
        super(className, pkg, Registries.SOUND_EVENT);
    }

    @Override
    public void addExtras(final TypeSpec.Builder builder, final FieldSpec keyField) {
        builder.addSuperinterface(Sound.Type.class)
            .addJavadoc(CLASS_HEADER);
    }

    @Override
    public @Nullable String getExperimentalValue(final Map.Entry<ResourceKey<SoundEvent>, SoundEvent> entry) {
        @Nullable String result = super.getExperimentalValue(entry);
        if (result != null) {
            return result;
        }

        String path = entry.getKey().location().getPath();
        // the below way is not perfect, but it tries its best
        if (EXPERIMENTAL_EXCEPTIONS.contains(path)) {
            return Formatting.formatFeatureFlag(FeatureFlags.UPDATE_1_21);
        }

        String[] fragments = path.split("\\.");
        if (fragments.length < 2) {
            return null;
        }

        Supplier<Registry<? extends FeatureElement>> filteredRegistry = FILTERED_REGISTRIES.get(fragments[0]);
        if (filteredRegistry == null) {
            return null;
        }

        Registry<? extends FeatureElement> registry = filteredRegistry.get();
        Optional<? extends FeatureElement> optionalElement = registry.getOptional(new ResourceLocation(ResourceLocation.DEFAULT_NAMESPACE, fragments[1]));
        if (optionalElement.isPresent()) {
            FeatureElement element = optionalElement.get();
            if (element instanceof BundleItem) {
                return Formatting.formatFeatureFlag(FeatureFlags.BUNDLE); // special case since the item is not locked itself just in the creative menu
            }
            if (FeatureFlags.isExperimental(element.requiredFeatures())) {
                return Formatting.formatFeatureFlagSet(element.requiredFeatures());
            }
        }

        return null;
    }
}
