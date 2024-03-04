package org.bukkit.craftbukkit.block.impl;

import io.papermc.paper.generated.GeneratedFrom;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.bukkit.block.data.type.Fence;
import org.bukkit.craftbukkit.block.data.CraftBlockData;

@GeneratedFrom("1.20.4")
@SuppressWarnings("unused")
public class CraftFence extends CraftBlockData implements Fence {
    private static final BooleanProperty EAST = FenceBlock.EAST;

    private static final BooleanProperty NORTH = FenceBlock.NORTH;

    private static final BooleanProperty SOUTH = FenceBlock.SOUTH;

    private static final BooleanProperty WATERLOGGED = FenceBlock.WATERLOGGED;

    private static final BooleanProperty WEST = FenceBlock.WEST;

    public CraftFence(BlockState state) {
        super(state);
    }

    public boolean getEast() {
        return this.get(EAST);
    }

    public void setEast(final boolean east) {
        this.set(EAST, east);
    }

    public boolean getNorth() {
        return this.get(NORTH);
    }

    public void setNorth(final boolean north) {
        this.set(NORTH, north);
    }

    public boolean getSouth() {
        return this.get(SOUTH);
    }

    public void setSouth(final boolean south) {
        this.set(SOUTH, south);
    }

    @Override
    public boolean isWaterlogged() {
        return this.get(WATERLOGGED);
    }

    @Override
    public void setWaterlogged(final boolean waterlogged) {
        this.set(WATERLOGGED, waterlogged);
    }

    public boolean getWest() {
        return this.get(WEST);
    }

    public void setWest(final boolean west) {
        this.set(WEST, west);
    }
}
