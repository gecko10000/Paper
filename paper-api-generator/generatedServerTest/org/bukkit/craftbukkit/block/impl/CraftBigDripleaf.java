package org.bukkit.craftbukkit.block.impl;

import io.papermc.paper.generated.GeneratedFrom;
import java.util.Set;
import net.minecraft.world.level.block.BigDripleafBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.BigDripleaf;
import org.bukkit.craftbukkit.block.data.CraftBlockData;

@GeneratedFrom("1.20.4")
@SuppressWarnings("unused")
public class CraftBigDripleaf extends CraftBlockData implements BigDripleaf {
    private static final DirectionProperty FACING = BigDripleafBlock.FACING;

    private static final EnumProperty<net.minecraft.world.level.block.state.properties.Tilt> TILT = BlockStateProperties.TILT;

    private static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public CraftBigDripleaf(BlockState state) {
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
    public Set<BlockFace> getFaces() {
        return this.getValues(FACING, BlockFace.class);
    }

    @Override
    public BigDripleaf.Tilt getTilt() {
        return this.get(TILT, BigDripleaf.Tilt.class);
    }

    @Override
    public void setTilt(final BigDripleaf.Tilt tilt) {
        this.set(TILT, tilt);
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
