package io.papermc.generator.types.registry;

import com.squareup.javapoet.TypeSpec;
import io.papermc.generator.utils.Javadocs;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import net.kyori.adventure.sound.Sound;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;

@DefaultQualifier(NonNull.class)
public class SoundGenerator extends EnumRegistryGenerator<SoundEvent> {

    private static final String CLASS_HEADER = Javadocs.getVersionDependentClassHeader("Sounds");
    private static final List<Pattern> EXPERIMENTAL_REGEX = of(
        "block.copper_door.*",
        "block.copper_bulb.*",
        "block.copper_grate.*",
        "block.copper_grate.*",
        "block.copper_trapdoor.*",
        "block.polished_tuff.*",
        "block.trial_spawner.*",
        "block.tuff_bricks.*",
        "block.crafter.*",
        "entity.breeze.*",
        "entity.generic.wind_burst",
        "item.bundle.*"
    );

    public SoundGenerator(final String keysClassName, final String pkg) {
        super(keysClassName, pkg, Registries.SOUND_EVENT);
    }

    @Override
    public void addExtras(final TypeSpec.Builder builder) {
        builder.addSuperinterface(Sound.Type.class)
            .addJavadoc(CLASS_HEADER);
    }

    @Override
    public boolean isExperimental(final Map.Entry<ResourceKey<SoundEvent>, SoundEvent> entry) {
        for (Pattern pattern : EXPERIMENTAL_REGEX) {
            if (pattern.matcher(entry.getKey().location().toString()).find()) {
                return true;
            }
        }

        return false;
    }

    private static List<Pattern> of(String... strings) {
        List<Pattern> patterns = new ArrayList<>(strings.length);
        for (String pattern : strings) {
            patterns.add(Pattern.compile(pattern));
        }

        return patterns;
    }
}
