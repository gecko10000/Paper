package org.bukkit.craftbukkit.block.impl;

import io.papermc.paper.generated.GeneratedFrom;
import net.minecraft.world.level.block.PoweredRailBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.RailShape;
import org.bukkit.block.data.type.RedstoneRail;
import org.bukkit.craftbukkit.block.data.CraftBlockData;

@GeneratedFrom("1.20.4")
@SuppressWarnings("unused")
public class CraftPoweredRail extends CraftBlockData implements RedstoneRail {
    private static final BooleanProperty POWERED = PoweredRailBlock.POWERED;

    private static final EnumProperty<RailShape> SHAPE = PoweredRailBlock.SHAPE;

    private static final BooleanProperty WATERLOGGED = PoweredRailBlock.WATERLOGGED;

    public CraftPoweredRail(BlockState state) {
        super(state);
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
    public org.bukkit.block.data.Rail.Shape getShape() {
        return this.get(SHAPE, org.bukkit.block.data.Rail.Shape.class);
    }

    @Override
    public void setShape(final org.bukkit.block.data.Rail.Shape shape) {
        this.set(SHAPE, shape);
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
