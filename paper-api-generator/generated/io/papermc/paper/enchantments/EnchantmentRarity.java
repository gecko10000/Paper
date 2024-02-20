package io.papermc.paper.enchantments;

public enum EnchantmentRarity {

    // Paper start - Generated/EnchantmentRarity
    // @GeneratedFrom 1.20.4
    COMMON(10),
    UNCOMMON(5),
    RARE(2),
    VERY_RARE(1);
    // Paper end - Generated/EnchantmentRarity

    private final int weight;

    EnchantmentRarity(int weight) {
        this.weight = weight;
    }

    /**
     * Gets the weight for the rarity.
     *
     * @return the weight
     */
    public int getWeight() {
        return weight;
    }
}
