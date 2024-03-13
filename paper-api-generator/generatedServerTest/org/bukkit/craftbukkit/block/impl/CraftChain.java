package org.bukkit.craftbukkit.block.impl;

import io.papermc.paper.generated.GeneratedFrom;
import java.util.Set;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.ChainBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.bukkit.Axis;
import org.bukkit.block.data.type.Chain;
import org.bukkit.craftbukkit.block.data.CraftBlockData;

@GeneratedFrom("1.20.4")
@SuppressWarnings("unused")
public class CraftChain extends CraftBlockData implements Chain {
    private static final EnumProperty<Direction.Axis> AXIS = ChainBlock.AXIS;

    private static final BooleanProperty WATERLOGGED = ChainBlock.WATERLOGGED;

    public CraftChain(BlockState state) {
        super(state);
    }

    @Override
    public Axis getAxis() {
        return this.get(AXIS, Axis.class);
    }

    @Override
    public void setAxis(final Axis axis) {
        this.set(AXIS, axis);
    }

    @Override
    public Set<Axis> getAxes() {
        return this.getValues(AXIS, Axis.class);
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
