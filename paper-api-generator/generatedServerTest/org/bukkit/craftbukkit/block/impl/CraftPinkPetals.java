package org.bukkit.craftbukkit.block.impl;

import io.papermc.paper.generated.GeneratedFrom;
import java.util.Set;
import net.minecraft.world.level.block.PinkPetalsBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.PinkPetals;
import org.bukkit.craftbukkit.block.data.CraftBlockData;

@GeneratedFrom("1.20.4")
@SuppressWarnings("unused")
public class CraftPinkPetals extends CraftBlockData implements PinkPetals {
    private static final DirectionProperty FACING = PinkPetalsBlock.FACING;

    private static final IntegerProperty AMOUNT = PinkPetalsBlock.AMOUNT;

    public CraftPinkPetals(BlockState state) {
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
    public Set<BlockFace> getFaces() {
        return this.getValues(FACING, BlockFace.class);
    }

    @Override
    public int getFlowerAmount() {
        return this.get(AMOUNT);
    }

    @Override
    public void setFlowerAmount(final int flowerAmount) {
        this.set(AMOUNT, flowerAmount);
    }

    @Override
    public int getMinimumFlowerAmount() {
        return AMOUNT.min;
    }

    @Override
    public int getMaximumFlowerAmount() {
        return AMOUNT.max;
    }
}
