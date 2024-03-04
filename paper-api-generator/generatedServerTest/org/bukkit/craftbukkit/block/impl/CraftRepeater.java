package org.bukkit.craftbukkit.block.impl;

import io.papermc.paper.generated.GeneratedFrom;
import net.minecraft.world.level.block.RepeaterBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Repeater;
import org.bukkit.craftbukkit.block.data.CraftBlockData;

@GeneratedFrom("1.20.4")
@SuppressWarnings("unused")
public class CraftRepeater extends CraftBlockData implements Repeater {
    private static final IntegerProperty DELAY = RepeaterBlock.DELAY;

    private static final DirectionProperty FACING = RepeaterBlock.FACING;

    private static final BooleanProperty LOCKED = RepeaterBlock.LOCKED;

    private static final BooleanProperty POWERED = RepeaterBlock.POWERED;

    public CraftRepeater(BlockState state) {
        super(state);
    }

    @Override
    public int getDelay() {
        return this.get(DELAY);
    }

    @Override
    public void setDelay(final int delay) {
        this.set(DELAY, delay);
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
    public boolean isLocked() {
        return this.get(LOCKED);
    }

    @Override
    public void setLocked(final boolean locked) {
        this.set(LOCKED, locked);
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
