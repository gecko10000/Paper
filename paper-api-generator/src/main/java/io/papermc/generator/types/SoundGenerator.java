package io.papermc.generator.types;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import io.papermc.generator.Main;
import io.papermc.generator.utils.Annotations;
import io.papermc.generator.utils.Formatting;
import io.papermc.generator.utils.Javadocs;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import javax.lang.model.element.Modifier;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;

@DefaultQualifier(NonNull.class)
public class SoundGenerator extends SimpleGenerator {

    private static final String CLASS_HEADER = Javadocs.getVersionDependentClassHeader("Sounds");
    private static final List<Pattern> EXPERIEMENTAL_REGEX = of(
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
        super(keysClassName, pkg);
    }

    @Override
    protected TypeSpec getTypeSpec() {
        TypeSpec.Builder typeBuilder = TypeSpec.enumBuilder(this.className)
            .addSuperinterface(Keyed.class)
            .addSuperinterface(Sound.Type.class)
            .addModifiers(Modifier.PUBLIC)
            .addAnnotations(Annotations.CLASS_HEADER)
            .addJavadoc(CLASS_HEADER);

        Registry<SoundEvent> event = Main.REGISTRY_ACCESS.registryOrThrow(Registries.SOUND_EVENT);
        List<String> paths = new ArrayList<>();
        event.entrySet().forEach(key -> paths.add(key.getKey().location().getPath()));
        paths.sort(Comparator.naturalOrder());

        paths.forEach(key -> {
            String fieldName = Formatting.formatKeyAsField(Key.key(key));
            boolean isExperiemental = false;
            for (Pattern pattern : EXPERIEMENTAL_REGEX) {
                if (pattern.matcher(key).find()) {
                    isExperiemental = true;
                    break;
                }
            }
            TypeSpec.Builder builder = TypeSpec.anonymousClassBuilder("$S", key);
            if (isExperiemental) {
                builder.addAnnotations(Annotations.experimentalAnnotations(""));
            }

            typeBuilder.addEnumConstant(fieldName, builder.build());
        });

        typeBuilder.addField(FieldSpec.builder(NamespacedKey.class, "key", Modifier.PRIVATE).build());
        typeBuilder.addMethod(MethodSpec.constructorBuilder()
            .addParameter(String.class, "key").addCode("this.key = NamespacedKey.minecraft(key);").build());

        typeBuilder.addMethod(MethodSpec.methodBuilder("key")
                .returns(Key.class)
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Annotations.NOT_NULL)
                .addAnnotation(Annotations.OVERRIDE)
            .addCode("return this.key;").build());

        typeBuilder.addMethod(MethodSpec.methodBuilder("getKey")
            .returns(NamespacedKey.class)
            .addModifiers(Modifier.PUBLIC)
            .addAnnotation(Annotations.NOT_NULL)
            .addAnnotation(Annotations.OVERRIDE)
            .addCode("return this.key;").build());

        return typeBuilder.build();
    }

    @Override
    protected JavaFile.Builder file(JavaFile.Builder builder) {
        return builder
            .skipJavaLangImports(true);
    }


    private static List<Pattern> of(String... strings) {
        List<Pattern> patterns = new ArrayList<>();
        for (String pattern : strings) {
            patterns.add(Pattern.compile(pattern));
        }

        return patterns;
    }
}
