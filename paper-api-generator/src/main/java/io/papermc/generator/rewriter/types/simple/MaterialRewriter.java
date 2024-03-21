package io.papermc.generator.rewriter.types.simple;

import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import com.google.common.collect.Sets;
import io.papermc.generator.rewriter.SearchMetadata;
import io.papermc.generator.rewriter.types.EnumRegistryRewriter;
import io.papermc.generator.rewriter.types.SwitchCaseRewriter;
import io.papermc.generator.rewriter.types.SwitchRewriter;
import io.papermc.generator.rewriter.utils.Annotations;
import io.papermc.generator.utils.BlockStateMapping;
import io.papermc.generator.utils.Formatting;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.BundleItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.EmptyBlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.inventory.EquipmentSlot;

import static io.papermc.generator.utils.Formatting.floatStr;

@Deprecated(forRemoval = true)
public class MaterialRewriter {

    // blocks

    public static class Blocks extends EnumRegistryRewriter<Block, Material> {

        public Blocks(final String pattern) {
            super(Material.class, Registries.BLOCK, pattern, true);
        }

        @Override
        protected Iterable<Holder.Reference<Block>> getValues() {
            return BuiltInRegistries.BLOCK.holders().filter(reference -> !reference.value().equals(net.minecraft.world.level.block.Blocks.AIR))
                .sorted(Formatting.alphabeticKeyOrder(reference -> reference.key().location().getPath())).toList();
        }

        @Override
        protected String rewriteEnumValue(Holder.Reference<Block> reference) {
            Block block = reference.value();
            if (BlockStateMapping.MAPPING.containsKey(block.getClass())) {
                // some block can also be represented as item in that enum
                // doing a double job
                Optional<Item> equivalentItem = BuiltInRegistries.ITEM.getOptional(reference.key().location());

                if (equivalentItem.isEmpty() && block instanceof WallSignBlock) {
                    // wall sign block stack size is 16 for some reason like the sign item?
                    // but that rule doesn't work for the wall hanging sign block??
                    equivalentItem = Optional.of(block.asItem());
                }

                Class<?> blockData = BlockStateMapping.getBestSuitedApiClass(block.getClass());
                if (blockData == null) {
                    blockData = BlockData.class;
                }
                if (equivalentItem.isPresent() && equivalentItem.get().getMaxStackSize() != Item.MAX_STACK_SIZE) {
                    return "%d, %d, %s.class".formatted(-1, equivalentItem.get().getMaxStackSize(), blockData.getSimpleName());
                }
                return "%d, %s.class".formatted(-1, blockData.getSimpleName());
            }
            return String.valueOf(-1); // id not needed for non legacy material
        }
    }

    public static class IsBlock extends SwitchCaseRewriter {

        public IsBlock(final String pattern) {
            super(Material.class, pattern, false);
        }

        @Override
        protected Iterable<String> getCases() {
            return BuiltInRegistries.BLOCK.keySet().stream().map(key -> key.getPath().toUpperCase(Locale.ENGLISH))
                .sorted(Formatting.ALPHABETIC_KEY_ORDER).toList();
        }
    }

    public static class IsSolid extends SwitchCaseRewriter {

        public IsSolid(final String pattern) {
            super(Material.class, pattern, false);
        }

        @Override
        protected Iterable<String> getCases() {
            return BuiltInRegistries.BLOCK.holders().filter(reference -> reference.value().defaultBlockState().blocksMotion())
                .map(reference -> reference.key().location().getPath().toUpperCase(Locale.ENGLISH)).sorted(Formatting.ALPHABETIC_KEY_ORDER).toList();
        }
    }

    public static class IsAir extends SwitchCaseRewriter {

        public IsAir(final String pattern) {
            super(Material.class, pattern, false);
        }

        @Override
        protected Iterable<String> getCases() {
            return BuiltInRegistries.BLOCK.holders().filter(reference -> reference.value().defaultBlockState().isAir())
                .map(reference -> reference.key().location().getPath().toUpperCase(Locale.ENGLISH)).sorted(Formatting.ALPHABETIC_KEY_ORDER).toList();
        }
    }

    /* todo test is broken
    public static class IsTransparent extends SwitchCaseRewriter {

        public IsTransparent(final String pattern) {
            super(Material.class, pattern, false);
        }

        @Override
        protected Iterable<String> getCases() {
            return BuiltInRegistries.BLOCK.holders().filter(reference -> reference.value().defaultBlockState().useShapeForLightOcclusion())
            .map(reference -> reference.key().location().getPath().toUpperCase(Locale.ENGLISH)).sorted(Formatting.ALPHABETIC_KEY_ORDER).toList();
        }
    }*/

    public static class IsFlammable extends SwitchCaseRewriter {

        public IsFlammable(final String pattern) {
            super(Material.class, pattern, false);
        }

        @Override
        protected Iterable<String> getCases() {
            return BuiltInRegistries.BLOCK.holders().filter(reference -> reference.value().defaultBlockState().ignitedByLava())
                .map(reference -> reference.key().location().getPath().toUpperCase(Locale.ENGLISH)).sorted(Formatting.ALPHABETIC_KEY_ORDER).toList();
        }
    }

    public static class IsBurnable extends SwitchCaseRewriter {

        public IsBurnable(final String pattern) {
            super(Material.class, pattern, false);
        }

        @Override
        protected Iterable<String> getCases() {
            return BuiltInRegistries.BLOCK.holders().filter(reference -> ((FireBlock) net.minecraft.world.level.block.Blocks.FIRE).igniteOdds.getInt(reference.value()) > 0)
                .map(reference -> reference.key().location().getPath().toUpperCase(Locale.ENGLISH)).sorted(Formatting.ALPHABETIC_KEY_ORDER).toList();
        }
    }

    public static class IsOccluding extends SwitchCaseRewriter {

        public IsOccluding(final String pattern) {
            super(Material.class, pattern, false);
        }

        @Override
        protected Iterable<String> getCases() {
            return BuiltInRegistries.BLOCK.holders().filter(reference -> reference.value().defaultBlockState().isRedstoneConductor(EmptyBlockGetter.INSTANCE, BlockPos.ZERO))
                .map(reference -> reference.key().location().getPath().toUpperCase(Locale.ENGLISH)).sorted(Formatting.ALPHABETIC_KEY_ORDER).toList();
        }
    }

    public static class HasGravity extends SwitchCaseRewriter {

        public HasGravity(final String pattern) {
            super(Material.class, pattern, false);
        }

        @Override
        protected Iterable<String> getCases() {
            return BuiltInRegistries.BLOCK.holders().filter(reference -> reference.value() instanceof FallingBlock)
                .map(reference -> reference.key().location().getPath().toUpperCase(Locale.ENGLISH)).sorted(Formatting.ALPHABETIC_KEY_ORDER).toList();
        }
    }

    public static class IsInteractable extends SwitchCaseRewriter {

        public IsInteractable(final String pattern) {
            super(Material.class, pattern, false);
        }

        @Override
        protected Iterable<String> getCases() {
            return BuiltInRegistries.BLOCK.holders().filter(reference -> {
                try {
                    return !reference.value().getClass().getMethod("use", BlockState.class, net.minecraft.world.level.Level.class, BlockPos.class, Player.class, InteractionHand.class, BlockHitResult.class)
                        .getDeclaringClass().equals(BlockBehaviour.class);
                } catch (ReflectiveOperationException ignored) {}
                return false;
            }).map(reference -> reference.key().location().getPath().toUpperCase(Locale.ENGLISH)).sorted(Formatting.ALPHABETIC_KEY_ORDER).toList();
        }
    }

    public static class GetHardness extends SwitchRewriter<Float> {

        public GetHardness(final String pattern) {
            super(Material.class, pattern, false);
            this.defaultValue = returnOf(0.0F, floatStr(0.0F));
        }

        @Override
        protected Multimap<Return<Float>, String> getContent() {
            Multimap<Return<Float>, String> map = MultimapBuilder.treeKeys(Comparator.<Return<Float>>comparingDouble(Return::object))
                                                                 .treeSetValues(Formatting.ALPHABETIC_KEY_ORDER).build();
            BuiltInRegistries.BLOCK.holders().forEach(reference -> {
                float destroySpeed = reference.value().defaultBlockState().getDestroySpeed(EmptyBlockGetter.INSTANCE, BlockPos.ZERO);
                if (destroySpeed != this.defaultValue.object()) {
                    map.put(returnOf(destroySpeed, floatStr(destroySpeed)), reference.key().location().getPath().toUpperCase(Locale.ENGLISH));
                }
            });

            return map;
        }
    }

    public static class GetBlastResistance extends SwitchRewriter<Float> {

        public GetBlastResistance(final String pattern) {
            super(Material.class, pattern, false);
            this.defaultValue = returnOf(0.0F, floatStr(0.0F));
        }

        @Override
        protected Multimap<Return<Float>, String> getContent() {
            Multimap<Return<Float>, String> map = MultimapBuilder.treeKeys(Comparator.<Return<Float>>comparingDouble(Return::object))
                                                                 .treeSetValues(Formatting.ALPHABETIC_KEY_ORDER).build();
            BuiltInRegistries.BLOCK.holders().forEach(reference -> {
                float explosionResistance = reference.value().getExplosionResistance();
                if (explosionResistance != this.defaultValue.object()) {
                    map.put(returnOf(explosionResistance, floatStr(explosionResistance)), reference.key().location().getPath().toUpperCase(Locale.ENGLISH));
                }
            });
            return map;
        }
    }

    public static class GetSlipperiness extends SwitchRewriter<Float> {

        private static final float DEFAULT_SLIPPERINESS = 0.6F;

        public GetSlipperiness(final String pattern) {
            super(Material.class, pattern, false);
            this.defaultValue = returnOf(DEFAULT_SLIPPERINESS, floatStr(DEFAULT_SLIPPERINESS));
        }

        @Override
        protected Multimap<Return<Float>, String> getContent() {
            Multimap<Return<Float>, String> map = MultimapBuilder.treeKeys(Comparator.<Return<Float>>comparingDouble(Return::object))
                                                                 .treeSetValues(Formatting.ALPHABETIC_KEY_ORDER).build();
            BuiltInRegistries.BLOCK.holders().forEach(reference -> {
                float friction = reference.value().getFriction();
                if (friction != this.defaultValue.object()) {
                    map.put(returnOf(friction, floatStr(friction)), reference.key().location().getPath().toUpperCase(Locale.ENGLISH));
                }
            });
            return map;
        }
    }

    // items

    public static class Items extends EnumRegistryRewriter<Item, Material> {

        public Items(final String pattern) {
            super(Material.class, Registries.ITEM, pattern, true);
        }

        @Override
        protected Iterable<Holder.Reference<Item>> getValues() {
            return BuiltInRegistries.ITEM.holders().filter(reference -> BuiltInRegistries.BLOCK.getOptional(reference.key().location()).isEmpty() || reference.value().equals(net.minecraft.world.item.Items.AIR))
                .sorted(Formatting.alphabeticKeyOrder(reference -> reference.key().location().getPath())).toList();
        }

        @Override
        protected String rewriteEnumValue(Holder.Reference<Item> reference) {
            Item item = reference.value();
            if (item.equals(net.minecraft.world.item.Items.AIR)) {
                return "%d, %d".formatted(-1, 0); // item+block
            }

            if (item.getMaxStackSize() != Item.MAX_STACK_SIZE) {
                if (item.getMaxDamage() != 0) {
                    return "%d, %d, %d".formatted(-1, item.getMaxStackSize(), item.getMaxDamage());
                }
                return "%d, %d".formatted(-1, item.getMaxStackSize());
            }

            return String.valueOf(-1); // id not needed for non legacy material
        }

        @Override
        protected void rewriteAnnotation(Holder.Reference<Item> reference, StringBuilder builder, SearchMetadata metadata) {
            if (reference.value() instanceof BundleItem) {
                Annotations.experimentalAnnotations(builder, metadata, FeatureFlags.BUNDLE); // special case since the item is not locked itself just in the creative menu
            } else {
                super.rewriteAnnotation(reference, builder, metadata);
            }
        }
    }

    public static class IsItem extends SwitchCaseRewriter {

        public IsItem(final String pattern) {
            super(Material.class, pattern, false);
        }

        @Override
        protected Iterable<String> getCases() {
            Set<String> items = BuiltInRegistries.ITEM.keySet().stream().map(key -> key.getPath().toUpperCase(Locale.ENGLISH)).collect(Collectors.toSet());
            Set<String> blocks = BuiltInRegistries.BLOCK.keySet().stream().map(key -> key.getPath().toUpperCase(Locale.ENGLISH)).collect(Collectors.toSet());

            List<String> result = new ArrayList<>(Sets.difference(blocks, items).copyInto(new HashSet<>())); // too much copy happens here
            result.sort(Formatting.ALPHABETIC_KEY_ORDER);
            return result; // those cases return false
        }
    }

    public static class IsEdible extends SwitchCaseRewriter {

        public IsEdible(final String pattern) {
            super(Material.class, pattern, false);
        }

        @Override
        protected Iterable<String> getCases() {
            return BuiltInRegistries.ITEM.holders().filter(reference -> reference.value().isEdible())
                .map(reference -> reference.key().location().getPath().toUpperCase(Locale.ENGLISH)).sorted(Formatting.ALPHABETIC_KEY_ORDER).toList();
        }
    }

    public static class IsRecord extends SwitchCaseRewriter {

        public IsRecord(final String pattern) {
            super(Material.class, pattern, false);
        }

        @Override
        protected Iterable<String> getCases() {
            Optional<HolderSet.Named<Item>> musicDiscs = BuiltInRegistries.ITEM.getTag(ItemTags.MUSIC_DISCS);
            if (musicDiscs.isEmpty()) {
                return Collections.emptyList();
            }
            return musicDiscs.get().stream().map(reference -> reference.unwrapKey().orElseThrow().location().getPath().toUpperCase(Locale.ENGLISH))
                .sorted(Formatting.ALPHABETIC_KEY_ORDER).toList();
        }
    }

    public static class IsFuel extends SwitchCaseRewriter {

        public IsFuel(final String pattern) {
            super(Material.class, pattern, false);
        }

        @Override
        protected Iterable<String> getCases() {
            return BuiltInRegistries.ITEM.holders().filter(reference -> AbstractFurnaceBlockEntity.getFuel().containsKey(reference.value()))
                .map(reference -> reference.key().location().getPath().toUpperCase(Locale.ENGLISH)).sorted(Formatting.ALPHABETIC_KEY_ORDER).toList();
        }
    }

    public static class GetCraftingRemainingItem extends SwitchRewriter<Item> {

        public GetCraftingRemainingItem(final String pattern) {
            super(Material.class, pattern, false);
            this.defaultValue = returnOf(null, "null");
        }

        @Override
        protected Multimap<Return<Item>, String> getContent() {
            Multimap<Return<Item>, String> map = MultimapBuilder.treeKeys(Formatting.<Return<Item>>alphabeticKeyOrder(Return::code))
                                                                         .treeSetValues(Formatting.ALPHABETIC_KEY_ORDER).build();
            BuiltInRegistries.ITEM.holders().forEach(reference -> {
                Item item = reference.value().getCraftingRemainingItem();
                if (item != this.defaultValue.object()) {
                    //noinspection ConstantConditions
                    String itemKey = BuiltInRegistries.ITEM.getKey(item).getPath().toUpperCase(Locale.ENGLISH);
                    map.put(returnOf(item, itemKey), reference.key().location().getPath().toUpperCase(Locale.ENGLISH));
                }
            });
            return map;
        }
    }

    public static class GetEquipmentSlot extends SwitchRewriter<EquipmentSlot> {

        public GetEquipmentSlot(final String pattern) {
            super(Material.class, pattern, false);
            this.defaultValue = returnOf(EquipmentSlot.HAND, "%s.%s".formatted(EquipmentSlot.class.getSimpleName(), EquipmentSlot.HAND.name()));
        }

        @Override
        protected Multimap<Return<EquipmentSlot>, String> getContent() {
            Multimap<Return<EquipmentSlot>, String> map = MultimapBuilder.treeKeys(Comparator.<Return<EquipmentSlot>>comparingInt(key -> key.object().ordinal()).reversed())
                                                                         .treeSetValues(Formatting.ALPHABETIC_KEY_ORDER).build();
            BuiltInRegistries.ITEM.holders().forEach(reference -> {
                net.minecraft.world.entity.EquipmentSlot slot = Mob.getEquipmentSlotForItem(new ItemStack(reference.value()));
                EquipmentSlot apiSlot = EquipmentSlot.values()[slot.ordinal()];
                if (apiSlot != this.defaultValue.object()) {
                    String formattedSlot = "%s.%s".formatted(EquipmentSlot.class.getSimpleName(), EquipmentSlot.values()[slot.ordinal()].name()); // name doesn't match
                    map.put(returnOf(apiSlot, formattedSlot), reference.key().location().getPath().toUpperCase(Locale.ENGLISH));
                }
            });
            return map;
        }
    }
}
