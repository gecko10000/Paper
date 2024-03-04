package org.bukkit.craftbukkit.block.impl;

import io.papermc.paper.generated.GeneratedFrom;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.CommandBlock;
import org.bukkit.craftbukkit.block.data.CraftBlockData;

@GeneratedFrom("1.20.4")
@SuppressWarnings("unused")
public class CraftCommandBlock extends CraftBlockData implements CommandBlock {
    private static final BooleanProperty CONDITIONAL = net.minecraft.world.level.block.CommandBlock.CONDITIONAL;

    private static final DirectionProperty FACING = net.minecraft.world.level.block.CommandBlock.FACING;

    public CraftCommandBlock(BlockState state) {
        super(state);
    }

    @Override
    public boolean isConditional() {
        return this.get(CONDITIONAL);
    }

    @Override
    public void setConditional(final boolean conditional) {
        this.set(CONDITIONAL, conditional);
    }

    @Override
    public BlockFace getFacing() {
        return this.get(FACING, BlockFace.class);
    }

    @Override
    public void setFacing(final BlockFace blockFace) {
        this.set(FACING, blockFace);
    }
}
