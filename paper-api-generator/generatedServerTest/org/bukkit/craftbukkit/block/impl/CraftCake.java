package org.bukkit.craftbukkit.block.impl;

import io.papermc.paper.generated.GeneratedFrom;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.bukkit.block.data.type.Cake;
import org.bukkit.craftbukkit.block.data.CraftBlockData;
import org.jetbrains.annotations.Range;

@GeneratedFrom("1.20.4")
@SuppressWarnings("unused")
public class CraftCake extends CraftBlockData implements Cake {
    private static final IntegerProperty BITES = CakeBlock.BITES;

    public CraftCake(BlockState state) {
        super(state);
    }

    @Override
    @Range(
            from = 0,
            to = 6
    )
    public int getBites() {
        return this.get(BITES);
    }

    @Override
    public void setBites(@Range(from = 0, to = 6) final int bites) {
        this.set(BITES, bites);
    }

    @Override
    public int getMaximumBites() {
        return BITES.max;
    }
}
