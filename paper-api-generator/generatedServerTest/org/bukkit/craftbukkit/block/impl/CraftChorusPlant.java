package org.bukkit.craftbukkit.block.impl;

import io.papermc.paper.generated.GeneratedFrom;
import net.minecraft.world.level.block.ChorusPlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.bukkit.block.data.MultipleFacing;
import org.bukkit.craftbukkit.block.data.CraftBlockData;

@GeneratedFrom("1.20.4")
@SuppressWarnings("unused")
public class CraftChorusPlant extends CraftBlockData implements MultipleFacing {
    private static final BooleanProperty DOWN = ChorusPlantBlock.DOWN;

    private static final BooleanProperty EAST = ChorusPlantBlock.EAST;

    private static final BooleanProperty NORTH = ChorusPlantBlock.NORTH;

    private static final BooleanProperty SOUTH = ChorusPlantBlock.SOUTH;

    private static final BooleanProperty UP = ChorusPlantBlock.UP;

    private static final BooleanProperty WEST = ChorusPlantBlock.WEST;

    public CraftChorusPlant(BlockState state) {
        super(state);
    }

    public boolean getDown() {
        return this.get(DOWN);
    }

    public void setDown(final boolean down) {
        this.set(DOWN, down);
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

    public boolean isUp() {
        return this.get(UP);
    }

    public void setUp(final boolean up) {
        this.set(UP, up);
    }

    public boolean getWest() {
        return this.get(WEST);
    }

    public void setWest(final boolean west) {
        this.set(WEST, west);
    }
}
