package io.papermc.generator.rewriter.types.simple;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import io.papermc.generator.rewriter.types.SwitchCaseRewriter;
import io.papermc.generator.rewriter.types.SwitchRewriter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.EmptyBlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

import static io.papermc.generator.utils.Formatting.floatStr;

@Deprecated(forRemoval = true)
public class MaterialRewriter { // todo sort values?

    // blocks

    public static class IsBlock extends SwitchCaseRewriter {

        public IsBlock(final String pattern, final boolean equalsSize) {
            super(Material.class, pattern, equalsSize);
        }

        @Override
        protected Iterable<String> getCases() {
            return BuiltInRegistries.BLOCK.keySet().stream().map(key -> key.getPath().toUpperCase(Locale.ENGLISH)).toList();
        }
    }

    public static class IsSolid extends SwitchCaseRewriter {

        public IsSolid(final String pattern, final boolean equalsSize) {
            super(Material.class, pattern, equalsSize);
        }

        @Override
        protected Iterable<String> getCases() {
            return BuiltInRegistries.BLOCK.holders().filter(reference -> reference.value().defaultBlockState().blocksMotion()).map(reference -> reference.key().location().getPath().toUpperCase(Locale.ENGLISH)).toList();
        }
    }

    public static class IsAir extends SwitchCaseRewriter {

        public IsAir(final String pattern, final boolean equalsSize) {
            super(Material.class, pattern, equalsSize);
        }

        @Override
        protected Iterable<String> getCases() {
            return BuiltInRegistries.BLOCK.holders().filter(reference -> reference.value().defaultBlockState().isAir()).map(reference -> reference.key().location().getPath().toUpperCase(Locale.ENGLISH)).toList();
        }
    }

    /* todo test is broken
    public static class IsTransparent extends SwitchCaseRewriter {

        public IsTransparent(final String pattern, final boolean equalsSize) {
            super(Material.class, pattern, equalsSize);
        }

        @Override
        protected Iterable<String> getCases() {
            return BuiltInRegistries.BLOCK.holders().filter(reference -> reference.value().defaultBlockState().useShapeForLightOcclusion()).map(reference -> reference.key().location().getPath().toUpperCase(Locale.ENGLISH)).toList();
        }
    }*/

    public static class IsFlammable extends SwitchCaseRewriter {

        public IsFlammable(final String pattern, final boolean equalsSize) {
            super(Material.class, pattern, equalsSize);
        }

        @Override
        protected Iterable<String> getCases() {
            return BuiltInRegistries.BLOCK.holders().filter(reference -> reference.value().defaultBlockState().ignitedByLava()).map(reference -> reference.key().location().getPath().toUpperCase(Locale.ENGLISH)).toList();
        }
    }

    public static class IsBurnable extends SwitchCaseRewriter {

        public IsBurnable(final String pattern, final boolean equalsSize) {
            super(Material.class, pattern, equalsSize);
        }

        @Override
        protected Iterable<String> getCases() {
            return BuiltInRegistries.BLOCK.holders().filter(reference -> ((FireBlock) Blocks.FIRE).igniteOdds.getInt(reference.value()) > 0)
                .map(reference -> reference.key().location().getPath().toUpperCase(Locale.ENGLISH)).toList();
        }
    }

    public static class IsOccluding extends SwitchCaseRewriter {

        public IsOccluding(final String pattern, final boolean equalsSize) {
            super(Material.class, pattern, equalsSize);
        }

        @Override
        protected Iterable<String> getCases() {
            return BuiltInRegistries.BLOCK.holders().filter(reference -> reference.value().defaultBlockState().isRedstoneConductor(EmptyBlockGetter.INSTANCE, BlockPos.ZERO))
                .map(reference -> reference.key().location().getPath().toUpperCase(Locale.ENGLISH)).toList();
        }
    }

    public static class HasGravity extends SwitchCaseRewriter {

        public HasGravity(final String pattern, final boolean equalsSize) {
            super(Material.class, pattern, equalsSize);
        }

        @Override
        protected Iterable<String> getCases() {
            return BuiltInRegistries.BLOCK.holders().filter(reference -> reference.value() instanceof FallingBlock)
                .map(reference -> reference.key().location().getPath().toUpperCase(Locale.ENGLISH)).toList();
        }
    }

    public static class IsInteractable extends SwitchCaseRewriter {

        public IsInteractable(final String pattern, final boolean equalsSize) {
            super(Material.class, pattern, equalsSize);
        }

        @Override
        protected Iterable<String> getCases() {
            return BuiltInRegistries.BLOCK.holders().filter(reference -> {
                try {
                    return !reference.value().getClass().getMethod("use", BlockState.class, net.minecraft.world.level.Level.class, BlockPos.class, Player.class, InteractionHand.class, BlockHitResult.class)
                        .getDeclaringClass().equals(BlockBehaviour.class);
                } catch (ReflectiveOperationException ignored) {}
                return false;
            }).map(reference -> reference.key().location().getPath().toUpperCase(Locale.ENGLISH)).toList();
        }
    }

    public static class GetHardness extends SwitchRewriter {

        public GetHardness(final String pattern, final boolean equalsSize) {
            super(Material.class, pattern, equalsSize);
            this.defaultValue = floatStr(0.0F);
        }

        @Override
        protected Multimap<String, String> getContent() {
            Multimap<String, String> map = HashMultimap.create();
            BuiltInRegistries.BLOCK.holders().forEach(reference -> {
                String destroySpeed = floatStr(reference.value().defaultBlockState().getDestroySpeed(EmptyBlockGetter.INSTANCE, BlockPos.ZERO));
                if (!destroySpeed.equals(this.defaultValue)) {
                    map.put(destroySpeed, reference.key().location().getPath().toUpperCase(Locale.ENGLISH));
                }
            });
            return map;
        }
    }

    public static class GetBlastResistance extends SwitchRewriter {

        public GetBlastResistance(final String pattern, final boolean equalsSize) {
            super(Material.class, pattern, equalsSize);
            this.defaultValue = floatStr(0.0F);
        }

        @Override
        protected Multimap<String, String> getContent() {
            Multimap<String, String> map = HashMultimap.create();
            BuiltInRegistries.BLOCK.holders().forEach(reference -> {
                String explosionResistance = floatStr(reference.value().getExplosionResistance());
                if (!explosionResistance.equals(this.defaultValue)) {
                    map.put(explosionResistance, reference.key().location().getPath().toUpperCase(Locale.ENGLISH));
                }
            });
            return map;
        }
    }

    public static class GetSlipperiness extends SwitchRewriter {

        public GetSlipperiness(final String pattern, final boolean equalsSize) {
            super(Material.class, pattern, equalsSize);
            this.defaultValue = floatStr(0.6F);
        }

        @Override
        protected Multimap<String, String> getContent() {
            Multimap<String, String> map = HashMultimap.create();
            BuiltInRegistries.BLOCK.holders().forEach(reference -> {
                String friction = floatStr(reference.value().getFriction());
                if (!friction.equals(this.defaultValue)) {
                    map.put(friction, reference.key().location().getPath().toUpperCase(Locale.ENGLISH));
                }
            });
            return map;
        }
    }

    // items

    public static class IsItem extends SwitchCaseRewriter {

        public IsItem(final String pattern, final boolean equalsSize) {
            super(Material.class, pattern, equalsSize);
        }

        @Override
        protected Iterable<String> getCases() {
            Set<String> items = BuiltInRegistries.ITEM.keySet().stream().map(key -> key.getPath().toUpperCase(Locale.ENGLISH)).collect(Collectors.toSet());
            Set<String> blocks = BuiltInRegistries.BLOCK.keySet().stream().map(key -> key.getPath().toUpperCase(Locale.ENGLISH)).collect(Collectors.toSet());

            return Sets.difference(blocks, items).immutableCopy(); // those cases return false
        }
    }

    public static class IsEdible extends SwitchCaseRewriter {

        public IsEdible(final String pattern, final boolean equalsSize) {
            super(Material.class, pattern, equalsSize);
        }

        @Override
        protected Iterable<String> getCases() {
            return BuiltInRegistries.ITEM.holders().filter(reference -> reference.value().isEdible()).map(reference -> reference.key().location().getPath().toUpperCase(Locale.ENGLISH)).toList();
        }
    }

    public static class IsRecord extends SwitchCaseRewriter {

        public IsRecord(final String pattern, final boolean equalsSize) {
            super(Material.class, pattern, equalsSize);
        }

        @Override
        protected Iterable<String> getCases() {
            return BuiltInRegistries.ITEM.holders().filter(reference -> reference.is(ItemTags.MUSIC_DISCS)).map(reference -> reference.key().location().getPath().toUpperCase(Locale.ENGLISH)).toList();
        }
    }

    public static class IsFuel extends SwitchCaseRewriter {

        public IsFuel(final String pattern, final boolean equalsSize) {
            super(Material.class, pattern, equalsSize);
        }

        @Override
        protected Iterable<String> getCases() {
            return BuiltInRegistries.ITEM.holders().filter(reference -> AbstractFurnaceBlockEntity.getFuel().containsKey(reference.value()))
                .map(reference -> reference.key().location().getPath().toUpperCase(Locale.ENGLISH)).toList();
        }
    }

    public static class GetCraftingRemainingItem extends SwitchRewriter {

        public GetCraftingRemainingItem(final String pattern, final boolean equalsSize) {
            super(Material.class, pattern, equalsSize);
            this.defaultValue = "null";
        }

        @Override
        protected Multimap<String, String> getContent() {
            Multimap<String, String> map = HashMultimap.create();
            BuiltInRegistries.ITEM.holders().forEach(reference -> {
                Item item = reference.value().getCraftingRemainingItem();
                String itemKey = item == null ? this.defaultValue : BuiltInRegistries.ITEM.getKey(item).getPath().toUpperCase(Locale.ENGLISH);
                if (!itemKey.equals(this.defaultValue)) {
                    map.put(itemKey, reference.key().location().getPath().toUpperCase(Locale.ENGLISH));
                }
            });
            return map;
        }
    }

    public static class GetEquipmentSlot extends SwitchRewriter {

        public GetEquipmentSlot(final String pattern, final boolean equalsSize) {
            super(Material.class, pattern, equalsSize);
            this.defaultValue = "%s.%s".formatted(EquipmentSlot.class.getSimpleName(), EquipmentSlot.HAND.name());
        }

        @Override
        protected Multimap<String, String> getContent() {
            Multimap<String, String> map = HashMultimap.create();
            BuiltInRegistries.ITEM.holders().forEach(reference -> {
                net.minecraft.world.entity.EquipmentSlot slot = Mob.getEquipmentSlotForItem(new ItemStack(reference.value()));
                String apiSlot = "%s.%s".formatted(EquipmentSlot.class.getSimpleName(), EquipmentSlot.values()[slot.ordinal()].name()); // name doesn't match
                if (!apiSlot.equals(this.defaultValue)) {
                    map.put(apiSlot, reference.key().location().getPath().toUpperCase(Locale.ENGLISH));
                }
            });
            return map;
        }
    }
}
