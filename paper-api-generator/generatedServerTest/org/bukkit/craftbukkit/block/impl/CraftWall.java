package org.bukkit.craftbukkit.block.impl;

import io.papermc.paper.generated.GeneratedFrom;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.WallSide;
import org.bukkit.block.data.type.Wall;
import org.bukkit.craftbukkit.block.data.CraftBlockData;

@GeneratedFrom("1.20.4")
@SuppressWarnings("unused")
public class CraftWall extends CraftBlockData implements Wall {
    private static final EnumProperty<WallSide> EAST_WALL = WallBlock.EAST_WALL;

    private static final EnumProperty<WallSide> NORTH_WALL = WallBlock.NORTH_WALL;

    private static final EnumProperty<WallSide> SOUTH_WALL = WallBlock.SOUTH_WALL;

    private static final BooleanProperty UP = WallBlock.UP;

    private static final BooleanProperty WATERLOGGED = WallBlock.WATERLOGGED;

    private static final EnumProperty<WallSide> WEST_WALL = WallBlock.WEST_WALL;

    public CraftWall(BlockState state) {
        super(state);
    }

    public Wall.Height getEast() {
        return this.get(EAST_WALL, Wall.Height.class);
    }

    public void setEast(final Wall.Height height) {
        this.set(EAST_WALL, height);
    }

    public Wall.Height getNorth() {
        return this.get(NORTH_WALL, Wall.Height.class);
    }

    public void setNorth(final Wall.Height height) {
        this.set(NORTH_WALL, height);
    }

    public Wall.Height getSouth() {
        return this.get(SOUTH_WALL, Wall.Height.class);
    }

    public void setSouth(final Wall.Height height) {
        this.set(SOUTH_WALL, height);
    }

    @Override
    public boolean isUp() {
        return this.get(UP);
    }

    @Override
    public void setUp(final boolean up) {
        this.set(UP, up);
    }

    @Override
    public boolean isWaterlogged() {
        return this.get(WATERLOGGED);
    }

    @Override
    public void setWaterlogged(final boolean waterlogged) {
        this.set(WATERLOGGED, waterlogged);
    }

    public Wall.Height getWest() {
        return this.get(WEST_WALL, Wall.Height.class);
    }

    public void setWest(final Wall.Height height) {
        this.set(WEST_WALL, height);
    }
}
