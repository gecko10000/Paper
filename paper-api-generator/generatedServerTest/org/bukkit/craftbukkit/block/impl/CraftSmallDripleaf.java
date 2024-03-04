package org.bukkit.craftbukkit.block.impl;

import io.papermc.paper.generated.GeneratedFrom;
import net.minecraft.world.level.block.SmallDripleafBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.SmallDripleaf;
import org.bukkit.craftbukkit.block.data.CraftBlockData;

@GeneratedFrom("1.20.4")
@SuppressWarnings("unused")
public class CraftSmallDripleaf extends CraftBlockData implements SmallDripleaf {
    private static final DirectionProperty FACING = SmallDripleafBlock.FACING;

    private static final EnumProperty<DoubleBlockHalf> HALF = SmallDripleafBlock.HALF;

    private static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public CraftSmallDripleaf(BlockState state) {
        super(state);
    }

    @Override
    public BlockFace getFacing() {
        return this.get(FACING, BlockFace.class);
    }

    @Override
    public void setFacing(final BlockFace blockFace) {
        this.set(FACING, blockFace);
    }

    @Override
    public org.bukkit.block.data.Bisected.Half getHalf() {
        return this.get(HALF, org.bukkit.block.data.Bisected.Half.class);
    }

    @Override
    public void setHalf(final org.bukkit.block.data.Bisected.Half half) {
        this.set(HALF, half);
    }

    @Override
    public boolean isWaterlogged() {
        return this.get(WATERLOGGED);
    }

    @Override
    public void setWaterlogged(final boolean waterlogged) {
        this.set(WATERLOGGED, waterlogged);
    }
}
