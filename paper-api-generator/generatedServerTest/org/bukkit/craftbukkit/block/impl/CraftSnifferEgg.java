package org.bukkit.craftbukkit.block.impl;

import io.papermc.paper.generated.GeneratedFrom;
import net.minecraft.world.level.block.SnifferEggBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.bukkit.block.data.Hatchable;
import org.bukkit.craftbukkit.block.data.CraftBlockData;
import org.jetbrains.annotations.Range;

@GeneratedFrom("1.20.4")
@SuppressWarnings("unused")
public class CraftSnifferEgg extends CraftBlockData implements Hatchable {
    private static final IntegerProperty HATCH = SnifferEggBlock.HATCH;

    public CraftSnifferEgg(BlockState state) {
        super(state);
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
