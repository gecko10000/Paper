package org.bukkit.craftbukkit.block.impl;

import io.papermc.paper.generated.GeneratedFrom;
import java.util.Set;
import net.minecraft.world.level.block.HopperBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Hopper;
import org.bukkit.craftbukkit.block.data.CraftBlockData;

@GeneratedFrom("1.20.4")
@SuppressWarnings("unused")
public class CraftHopper extends CraftBlockData implements Hopper {
    private static final BooleanProperty ENABLED = HopperBlock.ENABLED;

    private static final DirectionProperty FACING = HopperBlock.FACING;

    public CraftHopper(BlockState state) {
        super(state);
    }

    @Override
    public boolean isEnabled() {
        return this.get(ENABLED);
    }

    @Override
    public void setEnabled(final boolean enabled) {
        this.set(ENABLED, enabled);
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
