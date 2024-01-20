package io.papermc.generator.utils;

import java.util.Locale;
import net.kyori.adventure.key.Key;

public final class Formatting {

    public static String formatKeyAsField(Key key) {
        return formatPathAsField(key.value());
    }

    public static String formatPathAsField(String path) {
        return path.toUpperCase(Locale.ENGLISH).replaceAll("[.-/]", "_"); // replace invalid field name chars
    }

    public static String formatPathAsTagField(String path, String prefix) {
        return prefix + formatPathAsField(path);
    }

    private Formatting() {
    }
}
