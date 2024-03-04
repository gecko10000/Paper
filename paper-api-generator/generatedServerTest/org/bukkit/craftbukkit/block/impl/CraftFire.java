package org.bukkit.craftbukkit.block.impl;

import io.papermc.paper.generated.GeneratedFrom;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.bukkit.block.data.type.Fire;
import org.bukkit.craftbukkit.block.data.CraftBlockData;

@GeneratedFrom("1.20.4")
@SuppressWarnings("unused")
public class CraftFire extends CraftBlockData implements Fire {
    private static final IntegerProperty AGE = FireBlock.AGE;

    private static final BooleanProperty EAST = FireBlock.EAST;

    private static final BooleanProperty NORTH = FireBlock.NORTH;

    private static final BooleanProperty SOUTH = FireBlock.SOUTH;

    private static final BooleanProperty UP = FireBlock.UP;

    private static final BooleanProperty WEST = FireBlock.WEST;

    public CraftFire(BlockState state) {
        super(state);
    }

    @Override
    public int getAge() {
        return this.get(AGE);
    }

    @Override
    public void setAge(final int age) {
        this.set(AGE, age);
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
