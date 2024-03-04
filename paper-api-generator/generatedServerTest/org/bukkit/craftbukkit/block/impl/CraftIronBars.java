package org.bukkit.craftbukkit.block.impl;

import io.papermc.paper.generated.GeneratedFrom;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.craftbukkit.block.data.CraftBlockData;

@GeneratedFrom("1.20.4")
@SuppressWarnings("unused")
public class CraftIronBars extends CraftBlockData implements Waterlogged {
    private static final BooleanProperty EAST = IronBarsBlock.EAST;

    private static final BooleanProperty NORTH = IronBarsBlock.NORTH;

    private static final BooleanProperty SOUTH = IronBarsBlock.SOUTH;

    private static final BooleanProperty WATERLOGGED = IronBarsBlock.WATERLOGGED;

    private static final BooleanProperty WEST = IronBarsBlock.WEST;

    public CraftIronBars(BlockState state) {
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
