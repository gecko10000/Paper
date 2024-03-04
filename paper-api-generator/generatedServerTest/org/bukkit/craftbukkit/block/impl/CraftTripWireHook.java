package org.bukkit.craftbukkit.block.impl;

import io.papermc.paper.generated.GeneratedFrom;
import net.minecraft.world.level.block.TripWireHookBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.TripwireHook;
import org.bukkit.craftbukkit.block.data.CraftBlockData;

@GeneratedFrom("1.20.4")
@SuppressWarnings("unused")
public class CraftTripWireHook extends CraftBlockData implements TripwireHook {
    private static final BooleanProperty ATTACHED = TripWireHookBlock.ATTACHED;

    private static final DirectionProperty FACING = TripWireHookBlock.FACING;

    private static final BooleanProperty POWERED = TripWireHookBlock.POWERED;

    public CraftTripWireHook(BlockState state) {
        super(state);
    }

    @Override
    public boolean isAttached() {
        return this.get(ATTACHED);
    }

    @Override
    public void setAttached(final boolean attached) {
        this.set(ATTACHED, attached);
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
    public boolean isPowered() {
        return this.get(POWERED);
    }

    @Override
    public void setPowered(final boolean powered) {
        this.set(POWERED, powered);
    }
}
