package io.papermc.generator.utils;

import java.util.Locale;
import net.kyori.adventure.key.Key;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;

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

    private Formatting() {
    }
}
