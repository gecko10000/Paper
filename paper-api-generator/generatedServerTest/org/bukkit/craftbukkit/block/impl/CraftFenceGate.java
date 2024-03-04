package org.bukkit.craftbukkit.block.impl;

import io.papermc.paper.generated.GeneratedFrom;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Gate;
import org.bukkit.craftbukkit.block.data.CraftBlockData;

@GeneratedFrom("1.20.4")
@SuppressWarnings("unused")
public class CraftFenceGate extends CraftBlockData implements Gate {
    private static final DirectionProperty FACING = FenceGateBlock.FACING;

    private static final BooleanProperty IN_WALL = FenceGateBlock.IN_WALL;

    private static final BooleanProperty OPEN = FenceGateBlock.OPEN;

    private static final BooleanProperty POWERED = FenceGateBlock.POWERED;

    public CraftFenceGate(BlockState state) {
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
    public boolean isInWall() {
        return this.get(IN_WALL);
    }

    @Override
    public void setInWall(final boolean inWall) {
        this.set(IN_WALL, inWall);
    }

    @Override
    public boolean isOpen() {
        return this.get(OPEN);
    }

    @Override
    public void setOpen(final boolean open) {
        this.set(OPEN, open);
    }

    @Override
    public boolean isPowered() {
        return this.get(POWERED);
    }

    @Override
    public void setPowered(final boolean powered) {
        this.set(POWERED, powered);
    }
}
