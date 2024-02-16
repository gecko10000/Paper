package org.bukkit;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import java.util.HashMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents the art on a painting
 */
public enum Art implements Keyed {
    // Paper start - Generated/Art
    // @GeneratedFrom 1.20.4
    ALBAN(2, 1, 1),
    AZTEC(1, 1, 1),
    AZTEC2(3, 1, 1),
    BOMB(4, 1, 1),
    BURNING_SKULL(23, 4, 4),
    BUST(15, 2, 2),
    COURBET(8, 2, 1),
    CREEBET(11, 2, 1),
    DONKEY_KONG(29, 4, 3),
    EARTH(25, 2, 2),
    FIGHTERS(20, 4, 2),
    FIRE(28, 2, 2),
    GRAHAM(13, 1, 2),
    KEBAB(0, 1, 1),
    MATCH(14, 2, 2),
    PIGSCENE(22, 4, 4),
    PLANT(5, 1, 1),
    POINTER(21, 4, 4),
    POOL(7, 2, 1),
    SEA(9, 2, 1),
    SKELETON(24, 4, 3),
    SKULL_AND_ROSES(18, 2, 2),
    STAGE(16, 2, 2),
    SUNSET(10, 2, 1),
    VOID(17, 2, 2),
    WANDERER(12, 1, 2),
    WASTELAND(6, 1, 1),
    WATER(27, 2, 2),
    WIND(26, 2, 2),
    WITHER(19, 2, 2);
    // Paper end - Generated/Art

    private final int id, width, height;
    private final NamespacedKey key;
    private static final HashMap<String, Art> BY_NAME = Maps.newHashMap();
    private static final HashMap<Integer, Art> BY_ID = Maps.newHashMap();

    private Art(int id, int width, int height) {
        this.id = id;
        this.width = width;
        this.height = height;
        this.key = NamespacedKey.minecraft(name().toLowerCase(java.util.Locale.ENGLISH));
    }

    /**
     * Gets the width of the painting, in blocks
     *
     * @return The width of the painting, in blocks
     */
    public int getBlockWidth() {
        return width;
    }

    /**
     * Gets the height of the painting, in blocks
     *
     * @return The height of the painting, in blocks
     */
    public int getBlockHeight() {
        return height;
    }

    /**
     * Get the ID of this painting.
     *
     * @return The ID of this painting
     * @apiNote Internal Use Only
     */
    @org.jetbrains.annotations.ApiStatus.Internal // Paper
    public int getId() {
        return id;
    }

    @NotNull
    @Override
    public NamespacedKey getKey() {
        return key;
    }

    /**
     * Get a painting by its numeric ID
     *
     * @param id The ID
     * @return The painting
     * @apiNote Internal Use Only
     */
    @org.jetbrains.annotations.ApiStatus.Internal // Paper
    @Nullable
    public static Art getById(int id) {
        return BY_ID.get(id);
    }

    /**
     * Get a painting by its unique name
     * <p>
     * This ignores underscores and capitalization
     *
     * @param name The name
     * @return The painting
     */
    @Nullable
    public static Art getByName(@NotNull String name) {
        Preconditions.checkArgument(name != null, "Name cannot be null");

        return BY_NAME.get(name.toLowerCase(java.util.Locale.ENGLISH));
    }

    static {
        for (Art art : values()) {
            BY_ID.put(art.id, art);
            BY_NAME.put(art.toString().toLowerCase(java.util.Locale.ENGLISH), art);
        }
    }
}
