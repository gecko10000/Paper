package org.bukkit.craftbukkit.block.impl;

import io.papermc.paper.generated.GeneratedFrom;
import net.minecraft.world.level.block.BeehiveBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Beehive;
import org.bukkit.craftbukkit.block.data.CraftBlockData;

@GeneratedFrom("1.20.4")
@SuppressWarnings("unused")
public class CraftBeehive extends CraftBlockData implements Beehive {
    private static final DirectionProperty FACING = BeehiveBlock.FACING;

    private static final IntegerProperty HONEY_LEVEL = BeehiveBlock.HONEY_LEVEL;

    public CraftBeehive(BlockState state) {
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
    public int getHoneyLevel() {
        return this.get(HONEY_LEVEL);
    }

    @Override
    public void setHoneyLevel(final int honeyLevel) {
        this.set(HONEY_LEVEL, honeyLevel);
    }
}
