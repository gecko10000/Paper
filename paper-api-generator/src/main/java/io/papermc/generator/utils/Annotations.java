package io.papermc.generator.utils;

import com.squareup.javapoet.AnnotationSpec;
import io.papermc.paper.generated.GeneratedFrom;
import java.util.List;
import net.minecraft.SharedConstants;
import net.minecraft.world.flag.FeatureFlag;
import org.bukkit.MinecraftExperimental;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class Annotations {

    public static List<AnnotationSpec> experimentalAnnotations(final FeatureFlag featureFlag) {
        return experimentalAnnotations(Formatting.formatFeatureFlag(featureFlag));
    }

    public static List<AnnotationSpec> experimentalAnnotations(final String value) {
        AnnotationSpec.Builder builder = AnnotationSpec.builder(MinecraftExperimental.class);
        if (!value.isEmpty()) {
            builder.addMember("value", "$S", value);
        }

        return List.of(
            AnnotationSpec.builder(ApiStatus.Experimental.class).build(),
            builder.build()
        );
    }

    public static AnnotationSpec deprecatedVersioned(final @Nullable String version, boolean forRemoval) {
        AnnotationSpec.Builder annotationSpec = AnnotationSpec.builder(Deprecated.class);
        if (forRemoval) {
            annotationSpec.addMember("forRemoval", "$L", true);
        }
        if (version != null) {
            annotationSpec.addMember("since", "$S", version);
        }

        return annotationSpec.build();
    }

    public static AnnotationSpec scheduledRemoval(final String version) {
        return AnnotationSpec.builder(ApiStatus.ScheduledForRemoval.class)
            .addMember("inVersion", "$S", version)
            .build();
    }

    public static AnnotationSpec suppressWarnings(final String... values) {
        final AnnotationSpec.Builder builder = AnnotationSpec.builder(SuppressWarnings.class);
        for (final String value : values) {
            builder.addMember("value", "$S", value);
        }
        return builder.build();
    }

    @ApiStatus.Experimental
    public static final AnnotationSpec EXPERIMENTAL_API_ANNOTATION = AnnotationSpec.builder(ApiStatus.Experimental.class).build();
    public static final AnnotationSpec NOT_NULL = AnnotationSpec.builder(NotNull.class).build();
    public static final AnnotationSpec OVERRIDE = AnnotationSpec.builder(Override.class).build();
    private static final AnnotationSpec SUPPRESS_WARNINGS = suppressWarnings("unused", "SpellCheckingInspection");
    public static final AnnotationSpec GENERATED_FROM = AnnotationSpec.builder(GeneratedFrom.class)
        .addMember("value", "$S", SharedConstants.getCurrentVersion().getName())
        .build();
    public static final Iterable<AnnotationSpec> CLASS_HEADER = List.of(
        SUPPRESS_WARNINGS,
        GENERATED_FROM
    );

    private Annotations() {
    }
}
