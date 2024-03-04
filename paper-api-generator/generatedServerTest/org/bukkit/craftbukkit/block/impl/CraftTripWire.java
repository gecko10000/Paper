package org.bukkit.craftbukkit.block.impl;

import io.papermc.paper.generated.GeneratedFrom;
import net.minecraft.world.level.block.TripWireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.bukkit.block.data.type.Tripwire;
import org.bukkit.craftbukkit.block.data.CraftBlockData;

@GeneratedFrom("1.20.4")
@SuppressWarnings("unused")
public class CraftTripWire extends CraftBlockData implements Tripwire {
    private static final BooleanProperty ATTACHED = TripWireBlock.ATTACHED;

    private static final BooleanProperty DISARMED = TripWireBlock.DISARMED;

    private static final BooleanProperty EAST = TripWireBlock.EAST;

    private static final BooleanProperty NORTH = TripWireBlock.NORTH;

    private static final BooleanProperty POWERED = TripWireBlock.POWERED;

    private static final BooleanProperty SOUTH = TripWireBlock.SOUTH;

    private static final BooleanProperty WEST = TripWireBlock.WEST;

    public CraftTripWire(BlockState state) {
        super(state);
    }

    @Override
    public boolean isAttached() {
        return this.get(ATTACHED);
    }

    @Override
    public void setAttached(final boolean attached) {
        this.set(ATTACHED, attached);
    }

    @Override
    public boolean isDisarmed() {
        return this.get(DISARMED);
    }

    @Override
    public void setDisarmed(final boolean disarmed) {
        this.set(DISARMED, disarmed);
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

    @Override
    public boolean isPowered() {
        return this.get(POWERED);
    }

    @Override
    public void setPowered(final boolean powered) {
        this.set(POWERED, powered);
    }

    public boolean getSouth() {
        return this.get(SOUTH);
    }

    public void setSouth(final boolean south) {
        this.set(SOUTH, south);
    }

    public boolean getWest() {
        return this.get(WEST);
    }

    public void setWest(final boolean west) {
        this.set(WEST, west);
    }
}
