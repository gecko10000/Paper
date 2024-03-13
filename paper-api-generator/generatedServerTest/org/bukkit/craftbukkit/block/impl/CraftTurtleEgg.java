package org.bukkit.craftbukkit.block.impl;

import io.papermc.paper.generated.GeneratedFrom;
import net.minecraft.world.level.block.TurtleEggBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.bukkit.block.data.type.TurtleEgg;
import org.bukkit.craftbukkit.block.data.CraftBlockData;
import org.jetbrains.annotations.Range;

@GeneratedFrom("1.20.4")
@SuppressWarnings("unused")
public class CraftTurtleEgg extends CraftBlockData implements TurtleEgg {
    private static final IntegerProperty EGGS = TurtleEggBlock.EGGS;

    private static final IntegerProperty HATCH = TurtleEggBlock.HATCH;

    public CraftTurtleEgg(BlockState state) {
        super(state);
    }

    @Override
    @Range(
            from = 1,
            to = 4
    )
    public int getEggs() {
        return this.get(EGGS);
    }

    @Override
    public void setEggs(@Range(from = 1, to = 4) final int eggs) {
        this.set(EGGS, eggs);
    }

    @Override
    public int getMinimumEggs() {
        return EGGS.min;
    }

    @Override
    public int getMaximumEggs() {
        return EGGS.max;
    }

    @Override
    @Range(
            from = 0,
            to = 2
    )
    public int getHatch() {
        return this.get(HATCH);
    }

    @Override
    public void setHatch(@Range(from = 0, to = 2) final int hatch) {
        this.set(HATCH, hatch);
    }

    @Override
    public int getMaximumHatch() {
        return HATCH.max;
    }
}
