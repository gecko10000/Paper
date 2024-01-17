package io.papermc.generator.utils;

import java.util.Locale;
import net.kyori.adventure.key.Key;

public final class Formatting {

    public static String formatKeyAsField(Key key) {
       return key.value().toUpperCase(Locale.ENGLISH).replaceAll("[.-/]", "_"); // replace invalid field name chars
    }

    private Formatting() {
    }
}
