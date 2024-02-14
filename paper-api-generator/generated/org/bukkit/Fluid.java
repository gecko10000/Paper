package org.bukkit;

import java.util.Locale;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a fluid type.
 */
public enum Fluid implements Keyed {

    // Paper start - Generated/Fluid
    // @GeneratedFrom 1.20.4
    EMPTY,
    FLOWING_LAVA,
    FLOWING_WATER,
    LAVA,
    WATER;
    // Paper end - Generated/Fluid

    private final NamespacedKey key;

    private Fluid() {
        this.key = NamespacedKey.minecraft(this.name().toLowerCase(Locale.ROOT));
    }

    @NotNull
    @Override
    public NamespacedKey getKey() {
        return key;
    }
}
