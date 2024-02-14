package io.papermc.generator.rewriter.utils;

import io.papermc.generator.utils.Formatting;
import net.minecraft.world.flag.FeatureFlag;
import org.bukkit.MinecraftExperimental;
import org.jetbrains.annotations.ApiStatus;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Annotations {

    private static String retrieveFullNestedName(Class<?> clazz) {
        List<Class<?>> nestedClasses = new ArrayList<>();
        nestedClasses.add(clazz);
        Class<?> parent = clazz.getEnclosingClass();
        while (parent != null) {
            nestedClasses.add(parent);
            parent = parent.getEnclosingClass();
        }
        Collections.reverse(nestedClasses);
        return nestedClasses.stream().map(Class::getSimpleName).collect(Collectors.joining("."));
    }

    public static String annotation(Class<? extends Annotation> clazz, boolean imported) { // todo importCollector
        return "@%s".formatted(imported ? retrieveFullNestedName(clazz) : clazz.getCanonicalName());
    }

    public static String annotation(Class<? extends Annotation> clazz, String param, String value) {
        if (value.isEmpty()) {
            return annotation(clazz, false);
        }
        return "@%s(%s = %s)".formatted(clazz.getCanonicalName(), param, value);
    }

    public static void experimentalAnnotations(final StringBuilder builder, final Supplier<String> indent, final FeatureFlag featureFlag) {
        experimentalAnnotations(builder, indent, Formatting.formatFeatureFlag(featureFlag));
    }

    public static void experimentalAnnotations(final StringBuilder builder, Supplier<String> indent, final String value) {
        builder.append(indent.get()).append(annotation(MinecraftExperimental.class, "value", '"' + value + '"')).append('\n');
        builder.append(indent.get()).append(annotation(ApiStatus.Experimental.class, false)).append('\n');
    }
}
