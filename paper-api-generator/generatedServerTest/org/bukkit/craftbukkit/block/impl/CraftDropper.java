package org.bukkit.craftbukkit.block.impl;

import io.papermc.paper.generated.GeneratedFrom;
import net.minecraft.world.level.block.DropperBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Dispenser;
import org.bukkit.craftbukkit.block.data.CraftBlockData;

@GeneratedFrom("1.20.4")
@SuppressWarnings("unused")
public class CraftDropper extends CraftBlockData implements Dispenser {
    private static final DirectionProperty FACING = DropperBlock.FACING;

    private static final BooleanProperty TRIGGERED = DropperBlock.TRIGGERED;

    public CraftDropper(BlockState state) {
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
    public boolean isTriggered() {
        return this.get(TRIGGERED);
    }

    @Override
    public void setTriggered(final boolean triggered) {
        this.set(TRIGGERED, triggered);
    }
}
