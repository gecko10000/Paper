package org.bukkit.craftbukkit.block.impl;

import io.papermc.paper.generated.GeneratedFrom;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.HayBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.bukkit.Axis;
import org.bukkit.block.data.Orientable;
import org.bukkit.craftbukkit.block.data.CraftBlockData;

@GeneratedFrom("1.20.4")
@SuppressWarnings("unused")
public class CraftHay extends CraftBlockData implements Orientable {
    private static final EnumProperty<Direction.Axis> AXIS = HayBlock.AXIS;

    public CraftHay(BlockState state) {
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
}
