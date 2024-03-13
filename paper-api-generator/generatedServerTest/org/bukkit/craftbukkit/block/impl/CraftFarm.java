package org.bukkit.craftbukkit.block.impl;

import io.papermc.paper.generated.GeneratedFrom;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.bukkit.block.data.type.Farmland;
import org.bukkit.craftbukkit.block.data.CraftBlockData;
import org.jetbrains.annotations.Range;

@GeneratedFrom("1.20.4")
@SuppressWarnings("unused")
public class CraftFarm extends CraftBlockData implements Farmland {
    private static final IntegerProperty MOISTURE = FarmBlock.MOISTURE;

    public CraftFarm(BlockState state) {
        super(state);
    }

    @Override
    @Range(
            from = 0,
            to = 7
    )
    public int getMoisture() {
        return this.get(MOISTURE);
    }

    @Override
    public void setMoisture(@Range(from = 0, to = 7) final int moisture) {
        this.set(MOISTURE, moisture);
    }

    @Override
    public int getMaximumMoisture() {
        return MOISTURE.max;
    }
}
