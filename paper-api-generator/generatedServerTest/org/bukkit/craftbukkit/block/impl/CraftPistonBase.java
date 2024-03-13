package org.bukkit.craftbukkit.block.impl;

import io.papermc.paper.generated.GeneratedFrom;
import java.util.Set;
import net.minecraft.world.level.block.piston.PistonBaseBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Piston;
import org.bukkit.craftbukkit.block.data.CraftBlockData;

@GeneratedFrom("1.20.4")
@SuppressWarnings("unused")
public class CraftPistonBase extends CraftBlockData implements Piston {
    private static final BooleanProperty EXTENDED = PistonBaseBlock.EXTENDED;

    private static final DirectionProperty FACING = PistonBaseBlock.FACING;

    public CraftPistonBase(BlockState state) {
        super(state);
    }

    @Override
    public boolean isExtended() {
        return this.get(EXTENDED);
    }

    @Override
    public void setExtended(final boolean extended) {
        this.set(EXTENDED, extended);
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
}
