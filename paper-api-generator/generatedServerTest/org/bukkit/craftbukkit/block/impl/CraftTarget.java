package org.bukkit.craftbukkit.block.impl;

import io.papermc.paper.generated.GeneratedFrom;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.bukkit.block.data.AnaloguePowerable;
import org.bukkit.craftbukkit.block.data.CraftBlockData;

@GeneratedFrom("1.20.4")
@SuppressWarnings("unused")
public class CraftTarget extends CraftBlockData implements AnaloguePowerable {
    private static final IntegerProperty POWER = BlockStateProperties.POWER;

    public CraftTarget(BlockState state) {
        super(state);
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
    public int getMaximumPower() {
        return POWER.max;
    }
}
