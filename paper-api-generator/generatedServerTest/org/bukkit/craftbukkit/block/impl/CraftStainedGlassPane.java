package org.bukkit.craftbukkit.block.impl;

import io.papermc.paper.generated.GeneratedFrom;
import net.minecraft.world.level.block.StainedGlassPaneBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.bukkit.block.data.type.GlassPane;
import org.bukkit.craftbukkit.block.data.CraftBlockData;

@GeneratedFrom("1.20.4")
@SuppressWarnings("unused")
public class CraftStainedGlassPane extends CraftBlockData implements GlassPane {
    private static final BooleanProperty EAST = StainedGlassPaneBlock.EAST;

    private static final BooleanProperty NORTH = StainedGlassPaneBlock.NORTH;

    private static final BooleanProperty SOUTH = StainedGlassPaneBlock.SOUTH;

    private static final BooleanProperty WATERLOGGED = StainedGlassPaneBlock.WATERLOGGED;

    private static final BooleanProperty WEST = StainedGlassPaneBlock.WEST;

    public CraftStainedGlassPane(BlockState state) {
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
