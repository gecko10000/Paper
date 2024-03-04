package org.bukkit.craftbukkit.block.impl;

import io.papermc.paper.generated.GeneratedFrom;
import net.minecraft.world.level.block.CalibratedSculkSensorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.SculkSensorPhase;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.CalibratedSculkSensor;
import org.bukkit.craftbukkit.block.data.CraftBlockData;

@GeneratedFrom("1.20.4")
@SuppressWarnings("unused")
public class CraftCalibratedSculkSensor extends CraftBlockData implements CalibratedSculkSensor {
    private static final DirectionProperty FACING = CalibratedSculkSensorBlock.FACING;

    private static final IntegerProperty POWER = CalibratedSculkSensorBlock.POWER;

    private static final EnumProperty<SculkSensorPhase> PHASE = CalibratedSculkSensorBlock.PHASE;

    private static final BooleanProperty WATERLOGGED = CalibratedSculkSensorBlock.WATERLOGGED;

    public CraftCalibratedSculkSensor(BlockState state) {
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
    public int getPower() {
        return this.get(POWER);
    }

    @Override
    public void setPower(final int power) {
        this.set(POWER, power);
    }

    @Override
    public org.bukkit.block.data.type.SculkSensor.Phase getSculkSensorPhase() {
        return this.get(PHASE, org.bukkit.block.data.type.SculkSensor.Phase.class);
    }

    @Override
    public void setSculkSensorPhase(final org.bukkit.block.data.type.SculkSensor.Phase phase) {
        this.set(PHASE, phase);
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
