package org.bukkit.entity;

import org.bukkit.DyeColor;
import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

/**
 * Meow.
 */
public interface Cat extends Tameable, Sittable, io.papermc.paper.entity.CollarColorable { // Paper - CollarColorable

    /**
     * Gets the current type of this cat.
     *
     * @return Type of the cat.
     */
    @NotNull
    public Type getCatType();

    /**
     * Sets the current type of this cat.
     *
     * @param type New type of this cat.
     */
    public void setCatType(@NotNull Type type);

    /**
     * Get the collar color of this cat
     *
     * @return the color of the collar
     */
    @NotNull
    @Override // Paper
    public DyeColor getCollarColor();

    /**
     * Set the collar color of this cat
     *
     * @param color the color to apply
     */
    @Override // Paper
    public void setCollarColor(@NotNull DyeColor color);

    /**
     * Represents the various different cat types there are.
     */
    public enum Type implements Keyed {
        // Paper start - Generated/CatType
        // @GeneratedFrom 1.20.4
        ALL_BLACK("all_black"),
        BLACK("black"),
        BRITISH_SHORTHAIR("british_shorthair"),
        CALICO("calico"),
        JELLIE("jellie"),
        PERSIAN("persian"),
        RAGDOLL("ragdoll"),
        RED("red"),
        SIAMESE("siamese"),
        TABBY("tabby"),
        WHITE("white");
        // Paper end - Generated/CatType

        private final NamespacedKey key;

        private Type(String key) {
            this.key = NamespacedKey.minecraft(key);
        }

        @Override
        @NotNull
        public NamespacedKey getKey() {
            return key;
        }
    }

    // Paper start - More cat api
    /**
     * Sets if the cat is lying down.
     * This is visual and does not affect the behaviour of the cat.
     *
     * @param lyingDown whether the cat should lie down
     */
    public void setLyingDown(boolean lyingDown);

    /**
     * Gets if the cat is lying down.
     *
     * @return whether the cat is lying down
     */
    public boolean isLyingDown();

    /**
     * Sets if the cat has its head up.
     * This is visual and does not affect the behaviour of the cat.
     *
     * @param headUp head is up
     */
    public void setHeadUp(boolean headUp);

    /**
     * Gets if the cat has its head up.
     *
     * @return head is up
     */
    public boolean isHeadUp();
    // Paper end - More cat api
}
