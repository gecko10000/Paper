package org.bukkit.craftbukkit.block.impl;

import io.papermc.paper.generated.GeneratedFrom;
import net.minecraft.world.level.block.RedStoneWireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.RedstoneSide;
import org.bukkit.block.data.type.RedstoneWire;
import org.bukkit.craftbukkit.block.data.CraftBlockData;

@GeneratedFrom("1.20.4")
@SuppressWarnings("unused")
public class CraftRedStoneWire extends CraftBlockData implements RedstoneWire {
    private static final EnumProperty<RedstoneSide> EAST = RedStoneWireBlock.EAST;

    private static final EnumProperty<RedstoneSide> NORTH = RedStoneWireBlock.NORTH;

    private static final IntegerProperty POWER = RedStoneWireBlock.POWER;

    private static final EnumProperty<RedstoneSide> SOUTH = RedStoneWireBlock.SOUTH;

    private static final EnumProperty<RedstoneSide> WEST = RedStoneWireBlock.WEST;

    public CraftRedStoneWire(BlockState state) {
        super(state);
    }

    public RedstoneWire.Connection getEast() {
        return this.get(EAST, RedstoneWire.Connection.class);
    }

    public void setEast(final RedstoneWire.Connection connection) {
        this.set(EAST, connection);
    }

    public RedstoneWire.Connection getNorth() {
        return this.get(NORTH, RedstoneWire.Connection.class);
    }

    public void setNorth(final RedstoneWire.Connection connection) {
        this.set(NORTH, connection);
    }

    @Override
    public int getPower() {
        return this.get(POWER);
    }

    @Override
    public void setPower(final int power) {
        this.set(POWER, power);
    }

    public RedstoneWire.Connection getSouth() {
        return this.get(SOUTH, RedstoneWire.Connection.class);
    }

    public void setSouth(final RedstoneWire.Connection connection) {
        this.set(SOUTH, connection);
    }

    public RedstoneWire.Connection getWest() {
        return this.get(WEST, RedstoneWire.Connection.class);
    }

    public void setWest(final RedstoneWire.Connection connection) {
        this.set(WEST, connection);
    }
}
