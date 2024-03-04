package org.bukkit.craftbukkit.block.impl;

import io.papermc.paper.generated.GeneratedFrom;
import net.minecraft.world.level.block.WeatheringCopperTrapDoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.TrapDoor;
import org.bukkit.craftbukkit.block.data.CraftBlockData;

@GeneratedFrom("1.20.4")
@SuppressWarnings("unused")
public class CraftWeatheringCopperTrapDoor extends CraftBlockData implements TrapDoor {
    private static final DirectionProperty FACING = WeatheringCopperTrapDoorBlock.FACING;

    private static final EnumProperty<net.minecraft.world.level.block.state.properties.Half> HALF = WeatheringCopperTrapDoorBlock.HALF;

    private static final BooleanProperty OPEN = WeatheringCopperTrapDoorBlock.OPEN;

    private static final BooleanProperty POWERED = WeatheringCopperTrapDoorBlock.POWERED;

    private static final BooleanProperty WATERLOGGED = WeatheringCopperTrapDoorBlock.WATERLOGGED;

    public CraftWeatheringCopperTrapDoor(BlockState state) {
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
    public org.bukkit.block.data.Bisected.Half getHalf() {
        return this.get(HALF, org.bukkit.block.data.Bisected.Half.class);
    }

    @Override
    public void setHalf(final org.bukkit.block.data.Bisected.Half half) {
        this.set(HALF, half);
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

    @Override
    public boolean isWaterlogged() {
        return this.get(WATERLOGGED);
    }

    @Override
    public void setWaterlogged(final boolean waterlogged) {
        this.set(WATERLOGGED, waterlogged);
    }
}
