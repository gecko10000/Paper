package org.bukkit;

import io.papermc.paper.generated.GeneratedFrom;
import java.util.Set;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a tag that may be defined by the server or a resource pack to
 * group like things together.
 * <p>
 * Note that whilst all tags defined within this interface must be present in
 * Implementations, their existence is not guaranteed across future versions.
 * <p>
 * Custom tags defined by Paper are not present (as constants) in this class.
 * To access them please refer to {@link com.destroystokyo.paper.MaterialTags}
 * and {@link io.papermc.paper.tag.EntityTags}.
 *
 * @param <T> the type of things grouped by this tag
 */
@SuppressWarnings({
        "unused",
        "SpellCheckingInspection"
})
@GeneratedFrom("1.20.4")
public interface Tag<T extends Keyed> extends Keyed {
    /**
     * Key for the built in item registry.
     */
    String REGISTRY_ITEMS = "items";

    /**
     * {@code minecraft:acacia_logs}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_ACACIA_LOGS = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("acacia_logs"), Material.class);

    /**
     * {@code minecraft:anvil}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_ANVIL = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("anvil"), Material.class);

    /**
     * {@code minecraft:arrows}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_ARROWS = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("arrows"), Material.class);

    /**
     * {@code minecraft:axes}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_AXES = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("axes"), Material.class);

    /**
     * {@code minecraft:axolotl_tempt_items}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_AXOLOTL_TEMPT_ITEMS = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("axolotl_tempt_items"), Material.class);

    /**
     * {@code minecraft:bamboo_blocks}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_BAMBOO_BLOCKS = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("bamboo_blocks"), Material.class);

    /**
     * {@code minecraft:banners}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_BANNERS = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("banners"), Material.class);

    /**
     * {@code minecraft:beacon_payment_items}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_BEACON_PAYMENT_ITEMS = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("beacon_payment_items"), Material.class);

    /**
     * {@code minecraft:beds}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_BEDS = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("beds"), Material.class);

    /**
     * {@code minecraft:birch_logs}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_BIRCH_LOGS = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("birch_logs"), Material.class);

    /**
     * {@code minecraft:boats}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_BOATS = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("boats"), Material.class);

    /**
     * {@code minecraft:bookshelf_books}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_BOOKSHELF_BOOKS = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("bookshelf_books"), Material.class);

    /**
     * {@code minecraft:breaks_decorated_pots}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_BREAKS_DECORATED_POTS = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("breaks_decorated_pots"), Material.class);

    /**
     * {@code minecraft:buttons}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_BUTTONS = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("buttons"), Material.class);

    /**
     * {@code minecraft:candles}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_CANDLES = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("candles"), Material.class);

    /**
     * {@code minecraft:cherry_logs}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_CHERRY_LOGS = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("cherry_logs"), Material.class);

    /**
     * {@code minecraft:chest_boats}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_CHEST_BOATS = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("chest_boats"), Material.class);

    /**
     * {@code minecraft:cluster_max_harvestables}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_CLUSTER_MAX_HARVESTABLES = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("cluster_max_harvestables"), Material.class);

    /**
     * {@code minecraft:coal_ores}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_COAL_ORES = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("coal_ores"), Material.class);

    /**
     * {@code minecraft:coals}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_COALS = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("coals"), Material.class);

    /**
     * {@code minecraft:compasses}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_COMPASSES = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("compasses"), Material.class);

    /**
     * {@code minecraft:completes_find_tree_tutorial}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_COMPLETES_FIND_TREE_TUTORIAL = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("completes_find_tree_tutorial"), Material.class);

    /**
     * {@code minecraft:copper_ores}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_COPPER_ORES = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("copper_ores"), Material.class);

    /**
     * {@code minecraft:creeper_drop_music_discs}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_CREEPER_DROP_MUSIC_DISCS = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("creeper_drop_music_discs"), Material.class);

    /**
     * {@code minecraft:creeper_igniters}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_CREEPER_IGNITERS = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("creeper_igniters"), Material.class);

    /**
     * {@code minecraft:crimson_stems}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_CRIMSON_STEMS = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("crimson_stems"), Material.class);

    /**
     * {@code minecraft:dampens_vibrations}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_DAMPENS_VIBRATIONS = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("dampens_vibrations"), Material.class);

    /**
     * {@code minecraft:dark_oak_logs}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_DARK_OAK_LOGS = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("dark_oak_logs"), Material.class);

    /**
     * {@code minecraft:decorated_pot_ingredients}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_DECORATED_POT_INGREDIENTS = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("decorated_pot_ingredients"), Material.class);

    /**
     * {@code minecraft:decorated_pot_sherds}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_DECORATED_POT_SHERDS = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("decorated_pot_sherds"), Material.class);

    /**
     * {@code minecraft:diamond_ores}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_DIAMOND_ORES = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("diamond_ores"), Material.class);

    /**
     * {@code minecraft:dirt}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_DIRT = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("dirt"), Material.class);

    /**
     * {@code minecraft:doors}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_DOORS = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("doors"), Material.class);

    /**
     * {@code minecraft:emerald_ores}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_EMERALD_ORES = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("emerald_ores"), Material.class);

    /**
     * {@code minecraft:fence_gates}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_FENCE_GATES = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("fence_gates"), Material.class);

    /**
     * {@code minecraft:fences}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_FENCES = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("fences"), Material.class);

    /**
     * {@code minecraft:fishes}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_FISHES = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("fishes"), Material.class);

    /**
     * {@code minecraft:flowers}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_FLOWERS = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("flowers"), Material.class);

    /**
     * {@code minecraft:fox_food}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_FOX_FOOD = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("fox_food"), Material.class);

    /**
     * {@code minecraft:freeze_immune_wearables}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_FREEZE_IMMUNE_WEARABLES = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("freeze_immune_wearables"), Material.class);

    /**
     * {@code minecraft:gold_ores}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_GOLD_ORES = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("gold_ores"), Material.class);

    /**
     * {@code minecraft:hanging_signs}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_HANGING_SIGNS = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("hanging_signs"), Material.class);

    /**
     * {@code minecraft:hoes}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_HOES = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("hoes"), Material.class);

    /**
     * {@code minecraft:ignored_by_piglin_babies}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_IGNORED_BY_PIGLIN_BABIES = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("ignored_by_piglin_babies"), Material.class);

    /**
     * {@code minecraft:iron_ores}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_IRON_ORES = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("iron_ores"), Material.class);

    /**
     * {@code minecraft:jungle_logs}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_JUNGLE_LOGS = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("jungle_logs"), Material.class);

    /**
     * {@code minecraft:lapis_ores}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_LAPIS_ORES = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("lapis_ores"), Material.class);

    /**
     * {@code minecraft:leaves}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_LEAVES = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("leaves"), Material.class);

    /**
     * {@code minecraft:lectern_books}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_LECTERN_BOOKS = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("lectern_books"), Material.class);

    /**
     * {@code minecraft:logs}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_LOGS = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("logs"), Material.class);

    /**
     * {@code minecraft:logs_that_burn}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_LOGS_THAT_BURN = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("logs_that_burn"), Material.class);

    /**
     * {@code minecraft:mangrove_logs}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_MANGROVE_LOGS = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("mangrove_logs"), Material.class);

    /**
     * {@code minecraft:music_discs}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_MUSIC_DISCS = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("music_discs"), Material.class);

    /**
     * {@code minecraft:non_flammable_wood}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_NON_FLAMMABLE_WOOD = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("non_flammable_wood"), Material.class);

    /**
     * {@code minecraft:noteblock_top_instruments}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_NOTEBLOCK_TOP_INSTRUMENTS = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("noteblock_top_instruments"), Material.class);

    /**
     * {@code minecraft:oak_logs}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_OAK_LOGS = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("oak_logs"), Material.class);

    /**
     * {@code minecraft:pickaxes}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_PICKAXES = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("pickaxes"), Material.class);

    /**
     * {@code minecraft:piglin_food}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_PIGLIN_FOOD = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("piglin_food"), Material.class);

    /**
     * {@code minecraft:piglin_loved}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_PIGLIN_LOVED = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("piglin_loved"), Material.class);

    /**
     * {@code minecraft:piglin_repellents}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_PIGLIN_REPELLENTS = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("piglin_repellents"), Material.class);

    /**
     * {@code minecraft:planks}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_PLANKS = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("planks"), Material.class);

    /**
     * {@code minecraft:rails}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_RAILS = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("rails"), Material.class);

    /**
     * {@code minecraft:redstone_ores}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_REDSTONE_ORES = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("redstone_ores"), Material.class);

    /**
     * {@code minecraft:sand}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_SAND = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("sand"), Material.class);

    /**
     * {@code minecraft:saplings}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_SAPLINGS = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("saplings"), Material.class);

    /**
     * {@code minecraft:shovels}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_SHOVELS = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("shovels"), Material.class);

    /**
     * {@code minecraft:signs}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_SIGNS = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("signs"), Material.class);

    /**
     * {@code minecraft:slabs}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_SLABS = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("slabs"), Material.class);

    /**
     * {@code minecraft:small_flowers}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_SMALL_FLOWERS = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("small_flowers"), Material.class);

    /**
     * {@code minecraft:smelts_to_glass}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_SMELTS_TO_GLASS = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("smelts_to_glass"), Material.class);

    /**
     * {@code minecraft:sniffer_food}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_SNIFFER_FOOD = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("sniffer_food"), Material.class);

    /**
     * {@code minecraft:soul_fire_base_blocks}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_SOUL_FIRE_BASE_BLOCKS = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("soul_fire_base_blocks"), Material.class);

    /**
     * {@code minecraft:spruce_logs}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_SPRUCE_LOGS = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("spruce_logs"), Material.class);

    /**
     * {@code minecraft:stairs}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_STAIRS = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("stairs"), Material.class);

    /**
     * {@code minecraft:stone_bricks}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_STONE_BRICKS = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("stone_bricks"), Material.class);

    /**
     * {@code minecraft:stone_buttons}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_STONE_BUTTONS = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("stone_buttons"), Material.class);

    /**
     * {@code minecraft:stone_crafting_materials}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_STONE_CRAFTING_MATERIALS = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("stone_crafting_materials"), Material.class);

    /**
     * {@code minecraft:stone_tool_materials}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_STONE_TOOL_MATERIALS = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("stone_tool_materials"), Material.class);

    /**
     * {@code minecraft:swords}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_SWORDS = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("swords"), Material.class);

    /**
     * {@code minecraft:tall_flowers}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_TALL_FLOWERS = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("tall_flowers"), Material.class);

    /**
     * {@code minecraft:terracotta}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_TERRACOTTA = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("terracotta"), Material.class);

    /**
     * {@code minecraft:tools}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_TOOLS = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("tools"), Material.class);

    /**
     * {@code minecraft:trapdoors}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_TRAPDOORS = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("trapdoors"), Material.class);

    /**
     * {@code minecraft:trim_materials}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_TRIM_MATERIALS = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("trim_materials"), Material.class);

    /**
     * {@code minecraft:trim_templates}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_TRIM_TEMPLATES = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("trim_templates"), Material.class);

    /**
     * {@code minecraft:trimmable_armor}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_TRIMMABLE_ARMOR = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("trimmable_armor"), Material.class);

    /**
     * {@code minecraft:villager_plantable_seeds}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_VILLAGER_PLANTABLE_SEEDS = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("villager_plantable_seeds"), Material.class);

    /**
     * {@code minecraft:walls}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_WALLS = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("walls"), Material.class);

    /**
     * {@code minecraft:warped_stems}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_WARPED_STEMS = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("warped_stems"), Material.class);

    /**
     * {@code minecraft:wart_blocks}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_WART_BLOCKS = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("wart_blocks"), Material.class);

    /**
     * {@code minecraft:wooden_buttons}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_WOODEN_BUTTONS = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("wooden_buttons"), Material.class);

    /**
     * {@code minecraft:wooden_doors}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_WOODEN_DOORS = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("wooden_doors"), Material.class);

    /**
     * {@code minecraft:wooden_fences}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_WOODEN_FENCES = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("wooden_fences"), Material.class);

    /**
     * {@code minecraft:wooden_pressure_plates}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_WOODEN_PRESSURE_PLATES = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("wooden_pressure_plates"), Material.class);

    /**
     * {@code minecraft:wooden_slabs}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_WOODEN_SLABS = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("wooden_slabs"), Material.class);

    /**
     * {@code minecraft:wooden_stairs}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_WOODEN_STAIRS = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("wooden_stairs"), Material.class);

    /**
     * {@code minecraft:wooden_trapdoors}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_WOODEN_TRAPDOORS = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("wooden_trapdoors"), Material.class);

    /**
     * {@code minecraft:wool}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_WOOL = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("wool"), Material.class);

    /**
     * {@code minecraft:wool_carpets}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ITEMS_WOOL_CARPETS = Bukkit.getTag(REGISTRY_ITEMS, NamespacedKey.minecraft("wool_carpets"), Material.class);

    /**
     * Key for the built in game_event registry.
     */
    String REGISTRY_GAME_EVENTS = "game_events";

    /**
     * {@code minecraft:allay_can_listen}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<GameEvent> GAME_EVENT_ALLAY_CAN_LISTEN = Bukkit.getTag(REGISTRY_GAME_EVENTS, NamespacedKey.minecraft("allay_can_listen"), GameEvent.class);

    /**
     * {@code minecraft:ignore_vibrations_sneaking}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<GameEvent> GAME_EVENT_IGNORE_VIBRATIONS_SNEAKING = Bukkit.getTag(REGISTRY_GAME_EVENTS, NamespacedKey.minecraft("ignore_vibrations_sneaking"), GameEvent.class);

    /**
     * {@code minecraft:shrieker_can_listen}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<GameEvent> GAME_EVENT_SHRIEKER_CAN_LISTEN = Bukkit.getTag(REGISTRY_GAME_EVENTS, NamespacedKey.minecraft("shrieker_can_listen"), GameEvent.class);

    /**
     * {@code minecraft:vibrations}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<GameEvent> GAME_EVENT_VIBRATIONS = Bukkit.getTag(REGISTRY_GAME_EVENTS, NamespacedKey.minecraft("vibrations"), GameEvent.class);

    /**
     * {@code minecraft:warden_can_listen}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<GameEvent> GAME_EVENT_WARDEN_CAN_LISTEN = Bukkit.getTag(REGISTRY_GAME_EVENTS, NamespacedKey.minecraft("warden_can_listen"), GameEvent.class);

    /**
     * Key for the built in entity_type registry.
     */
    String REGISTRY_ENTITY_TYPES = "entity_types";

    /**
     * {@code minecraft:arrows}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<EntityType> ENTITY_TYPES_ARROWS = Bukkit.getTag(REGISTRY_ENTITY_TYPES, NamespacedKey.minecraft("arrows"), EntityType.class);

    /**
     * {@code minecraft:axolotl_always_hostiles}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<EntityType> ENTITY_TYPES_AXOLOTL_ALWAYS_HOSTILES = Bukkit.getTag(REGISTRY_ENTITY_TYPES, NamespacedKey.minecraft("axolotl_always_hostiles"), EntityType.class);

    /**
     * {@code minecraft:axolotl_hunt_targets}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<EntityType> ENTITY_TYPES_AXOLOTL_HUNT_TARGETS = Bukkit.getTag(REGISTRY_ENTITY_TYPES, NamespacedKey.minecraft("axolotl_hunt_targets"), EntityType.class);

    /**
     * {@code minecraft:beehive_inhabitors}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<EntityType> ENTITY_TYPES_BEEHIVE_INHABITORS = Bukkit.getTag(REGISTRY_ENTITY_TYPES, NamespacedKey.minecraft("beehive_inhabitors"), EntityType.class);

    /**
     * {@code minecraft:can_breathe_under_water}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<EntityType> ENTITY_TYPES_CAN_BREATHE_UNDER_WATER = Bukkit.getTag(REGISTRY_ENTITY_TYPES, NamespacedKey.minecraft("can_breathe_under_water"), EntityType.class);

    /**
     * {@code minecraft:can_turn_in_boats}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<EntityType> ENTITY_TYPES_CAN_TURN_IN_BOATS = Bukkit.getTag(REGISTRY_ENTITY_TYPES, NamespacedKey.minecraft("can_turn_in_boats"), EntityType.class);

    /**
     * {@code minecraft:deflects_arrows}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<EntityType> ENTITY_TYPES_DEFLECTS_ARROWS = Bukkit.getTag(REGISTRY_ENTITY_TYPES, NamespacedKey.minecraft("deflects_arrows"), EntityType.class);

    /**
     * {@code minecraft:deflects_tridents}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<EntityType> ENTITY_TYPES_DEFLECTS_TRIDENTS = Bukkit.getTag(REGISTRY_ENTITY_TYPES, NamespacedKey.minecraft("deflects_tridents"), EntityType.class);

    /**
     * {@code minecraft:dismounts_underwater}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<EntityType> ENTITY_TYPES_DISMOUNTS_UNDERWATER = Bukkit.getTag(REGISTRY_ENTITY_TYPES, NamespacedKey.minecraft("dismounts_underwater"), EntityType.class);

    /**
     * {@code minecraft:fall_damage_immune}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<EntityType> ENTITY_TYPES_FALL_DAMAGE_IMMUNE = Bukkit.getTag(REGISTRY_ENTITY_TYPES, NamespacedKey.minecraft("fall_damage_immune"), EntityType.class);

    /**
     * {@code minecraft:freeze_hurts_extra_types}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<EntityType> ENTITY_TYPES_FREEZE_HURTS_EXTRA_TYPES = Bukkit.getTag(REGISTRY_ENTITY_TYPES, NamespacedKey.minecraft("freeze_hurts_extra_types"), EntityType.class);

    /**
     * {@code minecraft:freeze_immune_entity_types}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<EntityType> ENTITY_TYPES_FREEZE_IMMUNE_ENTITY_TYPES = Bukkit.getTag(REGISTRY_ENTITY_TYPES, NamespacedKey.minecraft("freeze_immune_entity_types"), EntityType.class);

    /**
     * {@code minecraft:frog_food}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<EntityType> ENTITY_TYPES_FROG_FOOD = Bukkit.getTag(REGISTRY_ENTITY_TYPES, NamespacedKey.minecraft("frog_food"), EntityType.class);

    /**
     * {@code minecraft:impact_projectiles}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<EntityType> ENTITY_TYPES_IMPACT_PROJECTILES = Bukkit.getTag(REGISTRY_ENTITY_TYPES, NamespacedKey.minecraft("impact_projectiles"), EntityType.class);

    /**
     * {@code minecraft:non_controlling_rider}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<EntityType> ENTITY_TYPES_NON_CONTROLLING_RIDER = Bukkit.getTag(REGISTRY_ENTITY_TYPES, NamespacedKey.minecraft("non_controlling_rider"), EntityType.class);

    /**
     * {@code minecraft:powder_snow_walkable_mobs}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<EntityType> ENTITY_TYPES_POWDER_SNOW_WALKABLE_MOBS = Bukkit.getTag(REGISTRY_ENTITY_TYPES, NamespacedKey.minecraft("powder_snow_walkable_mobs"), EntityType.class);

    /**
     * {@code minecraft:raiders}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<EntityType> ENTITY_TYPES_RAIDERS = Bukkit.getTag(REGISTRY_ENTITY_TYPES, NamespacedKey.minecraft("raiders"), EntityType.class);

    /**
     * {@code minecraft:skeletons}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<EntityType> ENTITY_TYPES_SKELETONS = Bukkit.getTag(REGISTRY_ENTITY_TYPES, NamespacedKey.minecraft("skeletons"), EntityType.class);

    /**
     * {@code minecraft:undead}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<EntityType> ENTITY_TYPES_UNDEAD = Bukkit.getTag(REGISTRY_ENTITY_TYPES, NamespacedKey.minecraft("undead"), EntityType.class);

    /**
     * {@code minecraft:zombies}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<EntityType> ENTITY_TYPES_ZOMBIES = Bukkit.getTag(REGISTRY_ENTITY_TYPES, NamespacedKey.minecraft("zombies"), EntityType.class);

    /**
     * Key for the built in fluid registry.
     */
    String REGISTRY_FLUIDS = "fluids";

    /**
     * {@code minecraft:lava}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Fluid> FLUIDS_LAVA = Bukkit.getTag(REGISTRY_FLUIDS, NamespacedKey.minecraft("lava"), Fluid.class);

    /**
     * {@code minecraft:water}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Fluid> FLUIDS_WATER = Bukkit.getTag(REGISTRY_FLUIDS, NamespacedKey.minecraft("water"), Fluid.class);

    /**
     * Key for the built in block registry.
     */
    String REGISTRY_BLOCKS = "blocks";

    /**
     * {@code minecraft:acacia_logs}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ACACIA_LOGS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("acacia_logs"), Material.class);

    /**
     * {@code minecraft:all_hanging_signs}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ALL_HANGING_SIGNS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("all_hanging_signs"), Material.class);

    /**
     * {@code minecraft:all_signs}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ALL_SIGNS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("all_signs"), Material.class);

    /**
     * {@code minecraft:ancient_city_replaceable}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ANCIENT_CITY_REPLACEABLE = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("ancient_city_replaceable"), Material.class);

    /**
     * {@code minecraft:animals_spawnable_on}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ANIMALS_SPAWNABLE_ON = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("animals_spawnable_on"), Material.class);

    /**
     * {@code minecraft:anvil}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ANVIL = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("anvil"), Material.class);

    /**
     * {@code minecraft:axolotls_spawnable_on}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> AXOLOTLS_SPAWNABLE_ON = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("axolotls_spawnable_on"), Material.class);

    /**
     * {@code minecraft:azalea_grows_on}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> AZALEA_GROWS_ON = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("azalea_grows_on"), Material.class);

    /**
     * {@code minecraft:azalea_root_replaceable}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> AZALEA_ROOT_REPLACEABLE = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("azalea_root_replaceable"), Material.class);

    /**
     * {@code minecraft:bamboo_blocks}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> BAMBOO_BLOCKS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("bamboo_blocks"), Material.class);

    /**
     * {@code minecraft:bamboo_plantable_on}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> BAMBOO_PLANTABLE_ON = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("bamboo_plantable_on"), Material.class);

    /**
     * {@code minecraft:banners}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> BANNERS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("banners"), Material.class);

    /**
     * {@code minecraft:base_stone_nether}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> BASE_STONE_NETHER = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("base_stone_nether"), Material.class);

    /**
     * {@code minecraft:base_stone_overworld}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> BASE_STONE_OVERWORLD = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("base_stone_overworld"), Material.class);

    /**
     * {@code minecraft:beacon_base_blocks}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> BEACON_BASE_BLOCKS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("beacon_base_blocks"), Material.class);

    /**
     * {@code minecraft:beds}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> BEDS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("beds"), Material.class);

    /**
     * {@code minecraft:bee_growables}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> BEE_GROWABLES = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("bee_growables"), Material.class);

    /**
     * {@code minecraft:beehives}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> BEEHIVES = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("beehives"), Material.class);

    /**
     * {@code minecraft:big_dripleaf_placeable}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> BIG_DRIPLEAF_PLACEABLE = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("big_dripleaf_placeable"), Material.class);

    /**
     * {@code minecraft:birch_logs}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> BIRCH_LOGS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("birch_logs"), Material.class);

    /**
     * {@code minecraft:buttons}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> BUTTONS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("buttons"), Material.class);

    /**
     * {@code minecraft:camel_sand_step_sound_blocks}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> CAMEL_SAND_STEP_SOUND_BLOCKS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("camel_sand_step_sound_blocks"), Material.class);

    /**
     * {@code minecraft:campfires}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> CAMPFIRES = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("campfires"), Material.class);

    /**
     * {@code minecraft:candle_cakes}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> CANDLE_CAKES = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("candle_cakes"), Material.class);

    /**
     * {@code minecraft:candles}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> CANDLES = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("candles"), Material.class);

    /**
     * {@code minecraft:cauldrons}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> CAULDRONS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("cauldrons"), Material.class);

    /**
     * {@code minecraft:cave_vines}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> CAVE_VINES = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("cave_vines"), Material.class);

    /**
     * {@code minecraft:ceiling_hanging_signs}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> CEILING_HANGING_SIGNS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("ceiling_hanging_signs"), Material.class);

    /**
     * {@code minecraft:cherry_logs}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> CHERRY_LOGS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("cherry_logs"), Material.class);

    /**
     * {@code minecraft:climbable}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> CLIMBABLE = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("climbable"), Material.class);

    /**
     * {@code minecraft:coal_ores}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> COAL_ORES = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("coal_ores"), Material.class);

    /**
     * {@code minecraft:combination_step_sound_blocks}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> COMBINATION_STEP_SOUND_BLOCKS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("combination_step_sound_blocks"), Material.class);

    /**
     * {@code minecraft:completes_find_tree_tutorial}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> COMPLETES_FIND_TREE_TUTORIAL = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("completes_find_tree_tutorial"), Material.class);

    /**
     * {@code minecraft:concrete_powder}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> CONCRETE_POWDER = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("concrete_powder"), Material.class);

    /**
     * {@code minecraft:convertable_to_mud}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> CONVERTABLE_TO_MUD = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("convertable_to_mud"), Material.class);

    /**
     * {@code minecraft:copper_ores}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> COPPER_ORES = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("copper_ores"), Material.class);

    /**
     * {@code minecraft:coral_blocks}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> CORAL_BLOCKS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("coral_blocks"), Material.class);

    /**
     * {@code minecraft:coral_plants}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> CORAL_PLANTS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("coral_plants"), Material.class);

    /**
     * {@code minecraft:corals}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> CORALS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("corals"), Material.class);

    /**
     * {@code minecraft:crimson_stems}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> CRIMSON_STEMS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("crimson_stems"), Material.class);

    /**
     * {@code minecraft:crops}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> CROPS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("crops"), Material.class);

    /**
     * {@code minecraft:crystal_sound_blocks}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> CRYSTAL_SOUND_BLOCKS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("crystal_sound_blocks"), Material.class);

    /**
     * {@code minecraft:dampens_vibrations}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> DAMPENS_VIBRATIONS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("dampens_vibrations"), Material.class);

    /**
     * {@code minecraft:dark_oak_logs}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> DARK_OAK_LOGS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("dark_oak_logs"), Material.class);

    /**
     * {@code minecraft:dead_bush_may_place_on}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> DEAD_BUSH_MAY_PLACE_ON = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("dead_bush_may_place_on"), Material.class);

    /**
     * {@code minecraft:deepslate_ore_replaceables}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> DEEPSLATE_ORE_REPLACEABLES = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("deepslate_ore_replaceables"), Material.class);

    /**
     * {@code minecraft:diamond_ores}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> DIAMOND_ORES = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("diamond_ores"), Material.class);

    /**
     * {@code minecraft:dirt}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> DIRT = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("dirt"), Material.class);

    /**
     * {@code minecraft:doors}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> DOORS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("doors"), Material.class);

    /**
     * {@code minecraft:dragon_immune}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> DRAGON_IMMUNE = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("dragon_immune"), Material.class);

    /**
     * {@code minecraft:dragon_transparent}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> DRAGON_TRANSPARENT = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("dragon_transparent"), Material.class);

    /**
     * {@code minecraft:dripstone_replaceable_blocks}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> DRIPSTONE_REPLACEABLE_BLOCKS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("dripstone_replaceable_blocks"), Material.class);

    /**
     * {@code minecraft:emerald_ores}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> EMERALD_ORES = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("emerald_ores"), Material.class);

    /**
     * {@code minecraft:enchantment_power_provider}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ENCHANTMENT_POWER_PROVIDER = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("enchantment_power_provider"), Material.class);

    /**
     * {@code minecraft:enchantment_power_transmitter}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ENCHANTMENT_POWER_TRANSMITTER = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("enchantment_power_transmitter"), Material.class);

    /**
     * {@code minecraft:enderman_holdable}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ENDERMAN_HOLDABLE = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("enderman_holdable"), Material.class);

    /**
     * {@code minecraft:fall_damage_resetting}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> FALL_DAMAGE_RESETTING = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("fall_damage_resetting"), Material.class);

    /**
     * {@code minecraft:features_cannot_replace}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> FEATURES_CANNOT_REPLACE = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("features_cannot_replace"), Material.class);

    /**
     * {@code minecraft:fence_gates}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> FENCE_GATES = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("fence_gates"), Material.class);

    /**
     * {@code minecraft:fences}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> FENCES = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("fences"), Material.class);

    /**
     * {@code minecraft:fire}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> FIRE = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("fire"), Material.class);

    /**
     * {@code minecraft:flower_pots}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> FLOWER_POTS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("flower_pots"), Material.class);

    /**
     * {@code minecraft:flowers}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> FLOWERS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("flowers"), Material.class);

    /**
     * {@code minecraft:foxes_spawnable_on}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> FOXES_SPAWNABLE_ON = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("foxes_spawnable_on"), Material.class);

    /**
     * {@code minecraft:frog_prefer_jump_to}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> FROG_PREFER_JUMP_TO = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("frog_prefer_jump_to"), Material.class);

    /**
     * {@code minecraft:frogs_spawnable_on}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> FROGS_SPAWNABLE_ON = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("frogs_spawnable_on"), Material.class);

    /**
     * {@code minecraft:geode_invalid_blocks}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> GEODE_INVALID_BLOCKS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("geode_invalid_blocks"), Material.class);

    /**
     * {@code minecraft:goats_spawnable_on}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> GOATS_SPAWNABLE_ON = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("goats_spawnable_on"), Material.class);

    /**
     * {@code minecraft:gold_ores}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> GOLD_ORES = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("gold_ores"), Material.class);

    /**
     * {@code minecraft:guarded_by_piglins}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> GUARDED_BY_PIGLINS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("guarded_by_piglins"), Material.class);

    /**
     * {@code minecraft:hoglin_repellents}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> HOGLIN_REPELLENTS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("hoglin_repellents"), Material.class);

    /**
     * {@code minecraft:ice}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> ICE = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("ice"), Material.class);

    /**
     * {@code minecraft:impermeable}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> IMPERMEABLE = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("impermeable"), Material.class);

    /**
     * {@code minecraft:infiniburn_end}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> INFINIBURN_END = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("infiniburn_end"), Material.class);

    /**
     * {@code minecraft:infiniburn_nether}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> INFINIBURN_NETHER = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("infiniburn_nether"), Material.class);

    /**
     * {@code minecraft:infiniburn_overworld}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> INFINIBURN_OVERWORLD = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("infiniburn_overworld"), Material.class);

    /**
     * {@code minecraft:inside_step_sound_blocks}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> INSIDE_STEP_SOUND_BLOCKS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("inside_step_sound_blocks"), Material.class);

    /**
     * {@code minecraft:invalid_spawn_inside}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> INVALID_SPAWN_INSIDE = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("invalid_spawn_inside"), Material.class);

    /**
     * {@code minecraft:iron_ores}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> IRON_ORES = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("iron_ores"), Material.class);

    /**
     * {@code minecraft:jungle_logs}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> JUNGLE_LOGS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("jungle_logs"), Material.class);

    /**
     * {@code minecraft:lapis_ores}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> LAPIS_ORES = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("lapis_ores"), Material.class);

    /**
     * {@code minecraft:lava_pool_stone_cannot_replace}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> LAVA_POOL_STONE_CANNOT_REPLACE = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("lava_pool_stone_cannot_replace"), Material.class);

    /**
     * {@code minecraft:leaves}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> LEAVES = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("leaves"), Material.class);

    /**
     * {@code minecraft:logs}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> LOGS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("logs"), Material.class);

    /**
     * {@code minecraft:logs_that_burn}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> LOGS_THAT_BURN = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("logs_that_burn"), Material.class);

    /**
     * {@code minecraft:lush_ground_replaceable}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> LUSH_GROUND_REPLACEABLE = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("lush_ground_replaceable"), Material.class);

    /**
     * {@code minecraft:maintains_farmland}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> MAINTAINS_FARMLAND = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("maintains_farmland"), Material.class);

    /**
     * {@code minecraft:mangrove_logs}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> MANGROVE_LOGS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("mangrove_logs"), Material.class);

    /**
     * {@code minecraft:mangrove_logs_can_grow_through}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> MANGROVE_LOGS_CAN_GROW_THROUGH = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("mangrove_logs_can_grow_through"), Material.class);

    /**
     * {@code minecraft:mangrove_roots_can_grow_through}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> MANGROVE_ROOTS_CAN_GROW_THROUGH = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("mangrove_roots_can_grow_through"), Material.class);

    /**
     * {@code minecraft:mineable/axe}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> MINEABLE_AXE = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("mineable/axe"), Material.class);

    /**
     * {@code minecraft:mineable/hoe}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> MINEABLE_HOE = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("mineable/hoe"), Material.class);

    /**
     * {@code minecraft:mineable/pickaxe}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> MINEABLE_PICKAXE = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("mineable/pickaxe"), Material.class);

    /**
     * {@code minecraft:mineable/shovel}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> MINEABLE_SHOVEL = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("mineable/shovel"), Material.class);

    /**
     * {@code minecraft:mooshrooms_spawnable_on}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> MOOSHROOMS_SPAWNABLE_ON = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("mooshrooms_spawnable_on"), Material.class);

    /**
     * {@code minecraft:moss_replaceable}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> MOSS_REPLACEABLE = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("moss_replaceable"), Material.class);

    /**
     * {@code minecraft:mushroom_grow_block}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> MUSHROOM_GROW_BLOCK = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("mushroom_grow_block"), Material.class);

    /**
     * {@code minecraft:needs_diamond_tool}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> NEEDS_DIAMOND_TOOL = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("needs_diamond_tool"), Material.class);

    /**
     * {@code minecraft:needs_iron_tool}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> NEEDS_IRON_TOOL = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("needs_iron_tool"), Material.class);

    /**
     * {@code minecraft:needs_stone_tool}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> NEEDS_STONE_TOOL = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("needs_stone_tool"), Material.class);

    /**
     * {@code minecraft:nether_carver_replaceables}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> NETHER_CARVER_REPLACEABLES = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("nether_carver_replaceables"), Material.class);

    /**
     * {@code minecraft:nylium}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> NYLIUM = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("nylium"), Material.class);

    /**
     * {@code minecraft:oak_logs}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> OAK_LOGS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("oak_logs"), Material.class);

    /**
     * {@code minecraft:occludes_vibration_signals}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> OCCLUDES_VIBRATION_SIGNALS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("occludes_vibration_signals"), Material.class);

    /**
     * {@code minecraft:overworld_carver_replaceables}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> OVERWORLD_CARVER_REPLACEABLES = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("overworld_carver_replaceables"), Material.class);

    /**
     * {@code minecraft:overworld_natural_logs}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> OVERWORLD_NATURAL_LOGS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("overworld_natural_logs"), Material.class);

    /**
     * {@code minecraft:parrots_spawnable_on}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> PARROTS_SPAWNABLE_ON = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("parrots_spawnable_on"), Material.class);

    /**
     * {@code minecraft:piglin_repellents}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> PIGLIN_REPELLENTS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("piglin_repellents"), Material.class);

    /**
     * {@code minecraft:planks}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> PLANKS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("planks"), Material.class);

    /**
     * {@code minecraft:polar_bears_spawnable_on_alternate}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> POLAR_BEARS_SPAWNABLE_ON_ALTERNATE = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("polar_bears_spawnable_on_alternate"), Material.class);

    /**
     * {@code minecraft:portals}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> PORTALS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("portals"), Material.class);

    /**
     * {@code minecraft:pressure_plates}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> PRESSURE_PLATES = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("pressure_plates"), Material.class);

    /**
     * {@code minecraft:prevent_mob_spawning_inside}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> PREVENT_MOB_SPAWNING_INSIDE = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("prevent_mob_spawning_inside"), Material.class);

    /**
     * {@code minecraft:rabbits_spawnable_on}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> RABBITS_SPAWNABLE_ON = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("rabbits_spawnable_on"), Material.class);

    /**
     * {@code minecraft:rails}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> RAILS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("rails"), Material.class);

    /**
     * {@code minecraft:redstone_ores}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> REDSTONE_ORES = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("redstone_ores"), Material.class);

    /**
     * {@code minecraft:replaceable}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> REPLACEABLE = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("replaceable"), Material.class);

    /**
     * {@code minecraft:replaceable_by_trees}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> REPLACEABLE_BY_TREES = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("replaceable_by_trees"), Material.class);

    /**
     * {@code minecraft:sand}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> SAND = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("sand"), Material.class);

    /**
     * {@code minecraft:saplings}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> SAPLINGS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("saplings"), Material.class);

    /**
     * {@code minecraft:sculk_replaceable}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> SCULK_REPLACEABLE = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("sculk_replaceable"), Material.class);

    /**
     * {@code minecraft:sculk_replaceable_world_gen}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> SCULK_REPLACEABLE_WORLD_GEN = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("sculk_replaceable_world_gen"), Material.class);

    /**
     * {@code minecraft:shulker_boxes}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> SHULKER_BOXES = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("shulker_boxes"), Material.class);

    /**
     * {@code minecraft:signs}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> SIGNS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("signs"), Material.class);

    /**
     * {@code minecraft:slabs}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> SLABS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("slabs"), Material.class);

    /**
     * {@code minecraft:small_dripleaf_placeable}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> SMALL_DRIPLEAF_PLACEABLE = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("small_dripleaf_placeable"), Material.class);

    /**
     * {@code minecraft:small_flowers}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> SMALL_FLOWERS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("small_flowers"), Material.class);

    /**
     * {@code minecraft:smelts_to_glass}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> SMELTS_TO_GLASS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("smelts_to_glass"), Material.class);

    /**
     * {@code minecraft:snaps_goat_horn}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> SNAPS_GOAT_HORN = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("snaps_goat_horn"), Material.class);

    /**
     * {@code minecraft:sniffer_diggable_block}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> SNIFFER_DIGGABLE_BLOCK = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("sniffer_diggable_block"), Material.class);

    /**
     * {@code minecraft:sniffer_egg_hatch_boost}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> SNIFFER_EGG_HATCH_BOOST = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("sniffer_egg_hatch_boost"), Material.class);

    /**
     * {@code minecraft:snow}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> SNOW = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("snow"), Material.class);

    /**
     * {@code minecraft:snow_layer_can_survive_on}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> SNOW_LAYER_CAN_SURVIVE_ON = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("snow_layer_can_survive_on"), Material.class);

    /**
     * {@code minecraft:snow_layer_cannot_survive_on}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> SNOW_LAYER_CANNOT_SURVIVE_ON = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("snow_layer_cannot_survive_on"), Material.class);

    /**
     * {@code minecraft:soul_fire_base_blocks}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> SOUL_FIRE_BASE_BLOCKS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("soul_fire_base_blocks"), Material.class);

    /**
     * {@code minecraft:soul_speed_blocks}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> SOUL_SPEED_BLOCKS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("soul_speed_blocks"), Material.class);

    /**
     * {@code minecraft:spruce_logs}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> SPRUCE_LOGS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("spruce_logs"), Material.class);

    /**
     * {@code minecraft:stairs}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> STAIRS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("stairs"), Material.class);

    /**
     * {@code minecraft:standing_signs}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> STANDING_SIGNS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("standing_signs"), Material.class);

    /**
     * {@code minecraft:stone_bricks}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> STONE_BRICKS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("stone_bricks"), Material.class);

    /**
     * {@code minecraft:stone_buttons}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> STONE_BUTTONS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("stone_buttons"), Material.class);

    /**
     * {@code minecraft:stone_ore_replaceables}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> STONE_ORE_REPLACEABLES = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("stone_ore_replaceables"), Material.class);

    /**
     * {@code minecraft:stone_pressure_plates}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> STONE_PRESSURE_PLATES = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("stone_pressure_plates"), Material.class);

    /**
     * {@code minecraft:strider_warm_blocks}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> STRIDER_WARM_BLOCKS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("strider_warm_blocks"), Material.class);

    /**
     * {@code minecraft:sword_efficient}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> SWORD_EFFICIENT = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("sword_efficient"), Material.class);

    /**
     * {@code minecraft:tall_flowers}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> TALL_FLOWERS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("tall_flowers"), Material.class);

    /**
     * {@code minecraft:terracotta}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> TERRACOTTA = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("terracotta"), Material.class);

    /**
     * {@code minecraft:trail_ruins_replaceable}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> TRAIL_RUINS_REPLACEABLE = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("trail_ruins_replaceable"), Material.class);

    /**
     * {@code minecraft:trapdoors}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> TRAPDOORS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("trapdoors"), Material.class);

    /**
     * {@code minecraft:underwater_bonemeals}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> UNDERWATER_BONEMEALS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("underwater_bonemeals"), Material.class);

    /**
     * {@code minecraft:unstable_bottom_center}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> UNSTABLE_BOTTOM_CENTER = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("unstable_bottom_center"), Material.class);

    /**
     * {@code minecraft:valid_spawn}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> VALID_SPAWN = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("valid_spawn"), Material.class);

    /**
     * {@code minecraft:vibration_resonators}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> VIBRATION_RESONATORS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("vibration_resonators"), Material.class);

    /**
     * {@code minecraft:wall_corals}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> WALL_CORALS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("wall_corals"), Material.class);

    /**
     * {@code minecraft:wall_hanging_signs}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> WALL_HANGING_SIGNS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("wall_hanging_signs"), Material.class);

    /**
     * {@code minecraft:wall_post_override}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> WALL_POST_OVERRIDE = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("wall_post_override"), Material.class);

    /**
     * {@code minecraft:wall_signs}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> WALL_SIGNS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("wall_signs"), Material.class);

    /**
     * {@code minecraft:walls}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> WALLS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("walls"), Material.class);

    /**
     * {@code minecraft:warped_stems}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> WARPED_STEMS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("warped_stems"), Material.class);

    /**
     * {@code minecraft:wart_blocks}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> WART_BLOCKS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("wart_blocks"), Material.class);

    /**
     * {@code minecraft:wither_immune}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> WITHER_IMMUNE = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("wither_immune"), Material.class);

    /**
     * {@code minecraft:wither_summon_base_blocks}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> WITHER_SUMMON_BASE_BLOCKS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("wither_summon_base_blocks"), Material.class);

    /**
     * {@code minecraft:wolves_spawnable_on}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> WOLVES_SPAWNABLE_ON = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("wolves_spawnable_on"), Material.class);

    /**
     * {@code minecraft:wooden_buttons}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> WOODEN_BUTTONS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("wooden_buttons"), Material.class);

    /**
     * {@code minecraft:wooden_doors}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> WOODEN_DOORS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("wooden_doors"), Material.class);

    /**
     * {@code minecraft:wooden_fences}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> WOODEN_FENCES = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("wooden_fences"), Material.class);

    /**
     * {@code minecraft:wooden_pressure_plates}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> WOODEN_PRESSURE_PLATES = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("wooden_pressure_plates"), Material.class);

    /**
     * {@code minecraft:wooden_slabs}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> WOODEN_SLABS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("wooden_slabs"), Material.class);

    /**
     * {@code minecraft:wooden_stairs}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> WOODEN_STAIRS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("wooden_stairs"), Material.class);

    /**
     * {@code minecraft:wooden_trapdoors}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> WOODEN_TRAPDOORS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("wooden_trapdoors"), Material.class);

    /**
     * {@code minecraft:wool}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> WOOL = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("wool"), Material.class);

    /**
     * {@code minecraft:wool_carpets}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    Tag<Material> WOOL_CARPETS = Bukkit.getTag(REGISTRY_BLOCKS, NamespacedKey.minecraft("wool_carpets"), Material.class);

    /**
     * @deprecated in favour of {@link #WOOL_CARPETS}.
     */
    @Deprecated
    Tag<Material> CARPETS = WOOL_CARPETS;

    /**
     * Returns whether or not this tag has an entry for the specified item.
     *
     * @param item to check
     * @return if it is tagged
     */
    boolean isTagged(@NotNull T item);

    /**
     * Gets an immutable set of all tagged items.
     *
     * @return set of tagged items
     */
    @NotNull
    Set<T> getValues();
}
