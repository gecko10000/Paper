package io.papermc.generator.utils;

import com.google.common.base.Function;
import java.util.Comparator;
import java.util.Locale;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import net.kyori.adventure.key.Key;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import org.apache.commons.lang3.math.NumberUtils;

public final class Formatting {

    public static String formatKeyAsField(Key key) {
        return formatPathAsField(key.value());
    }

    public static String formatPathAsField(String path) {
        return path.toUpperCase(Locale.ENGLISH).replaceAll("[.-/]", "_"); // replace invalid field name chars
    }

    public static String formatTagFieldPrefix(String name, ResourceKey<? extends Registry<?>> registryKey) {
        if (registryKey == Registries.BLOCK) {
            return "";
        }
        if (registryKey == Registries.GAME_EVENT) {
            return "GAME_EVENT_"; // Paper doesn't follow the format (should be GAME_EVENTS_)
        }
        return name.toUpperCase(Locale.ENGLISH) + "_";
    }

    public static String formatFeatureFlagSet(FeatureFlagSet featureFlagSet) {
        for (FeatureFlag flag : FeatureFlags.REGISTRY.names.values()) {
            if (flag == FeatureFlags.VANILLA) {
                continue;
            }
            if (featureFlagSet.contains(flag)) {
                return formatFeatureFlag(flag);
            }
        }
        return "";
    }

    public static String formatFeatureFlag(FeatureFlag featureFlag) {
        Set<ResourceLocation> names = FeatureFlags.REGISTRY.toNames(FeatureFlagSet.of(featureFlag));
        String name = names.iterator().next().getPath(); // eww
        return formatFeatureFlagName(name);
    }

    public static String formatFeatureFlagName(String name) {
        int updateIndex = name.indexOf("update_");
        if (updateIndex == 0) {
            return "update %s".formatted(name.substring(updateIndex + "update_".length()).replace('_', '.'));
        }
        return name.replace('_', ' ') + " feature";
    }

    public static Optional<String> formatTagKey(String tagDir, String resourcePath) {
        int tagsIndex = resourcePath.indexOf(tagDir);
        int dotIndex = resourcePath.lastIndexOf('.');
        if (tagsIndex == -1 || dotIndex == -1) {
            return Optional.empty();
        }
        return Optional.of(resourcePath.substring(tagsIndex + tagDir.length() + 1, dotIndex)); // namespace/tags/registry_key/[tag_key].json
    }

    public static String incrementalIndent(String unit, Class<?> clazz) {
        Class<?> parent = clazz.getEnclosingClass();
        StringBuilder indentBuilder = new StringBuilder(unit);
        while (parent != null) {
            indentBuilder.append(unit);
            parent = parent.getEnclosingClass();
        }
        return indentBuilder.toString();
    }

    public static String retrieveFileName(Class<?> clazz) {
        String name = clazz.getSimpleName();
        Class<?> parent = clazz.getEnclosingClass();
        while (parent != null) {
            name = parent.getSimpleName();
            parent = parent.getEnclosingClass();
        }
        return name;
    }

    public static String quoted(String value) {
        return String.format("\"%s\"", value);
    }

    public static <T> Comparator<T> alphabeticOrder(Function<T, String> mapper) {
        return (o1, o2) -> {
            String path1 = mapper.apply(o1);
            String path2 = mapper.apply(o2);

            OptionalInt trailingInt1 = tryParseTrailingInt(path1);
            OptionalInt trailingInt2 = tryParseTrailingInt(path2);

            if (trailingInt1.isPresent() && trailingInt2.isPresent()) {
                return Integer.compare(trailingInt1.getAsInt(), trailingInt2.getAsInt());
            }

            return path1.compareTo(path2);
        };
    }

    private static OptionalInt tryParseTrailingInt(String path) {
        int delimiterIndex = path.lastIndexOf('_');
        if (delimiterIndex != -1) {
            String score = path.substring(delimiterIndex + 1);
            if (NumberUtils.isDigits(score)) {
                return OptionalInt.of(Integer.parseInt(score));
            }
        }
        return OptionalInt.empty();
    }

    private Formatting() {
    }
}
