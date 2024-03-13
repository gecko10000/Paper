package io.papermc.generator.types.registry;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.TypeSpec;
import io.papermc.generator.utils.experimental.ExperimentalSounds;
import io.papermc.generator.utils.Javadocs;
import net.kyori.adventure.sound.Sound;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.DefaultQualifier;

@DefaultQualifier(NonNull.class)
public class SoundGenerator extends EnumRegistryGenerator<SoundEvent> {

    private static final String CLASS_HEADER = Javadocs.getVersionDependentClassHeader("Sounds");

    public SoundGenerator(final String className, final String pkg) {
        super(className, pkg, Registries.SOUND_EVENT);
    }

    @Override
    public void addExtras(final TypeSpec.Builder builder, final FieldSpec keyField) {
        builder.addSuperinterface(Sound.Type.class);
        builder.addJavadoc(CLASS_HEADER);
    }

    @Override
    public @Nullable String getExperimentalValue(final Holder.Reference<SoundEvent> reference) {
        @Nullable String result = super.getExperimentalValue(reference);
        if (result != null) {
            return result;
        }

        return ExperimentalSounds.findExperimentalValue(reference.key().location());
    }
}
