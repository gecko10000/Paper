package org.bukkit.craftbukkit.block.impl;

import io.papermc.paper.generated.GeneratedFrom;
import java.util.Set;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Campfire;
import org.bukkit.craftbukkit.block.data.CraftBlockData;

@GeneratedFrom("1.20.4")
@SuppressWarnings("unused")
public class CraftCampfire extends CraftBlockData implements Campfire {
    private static final DirectionProperty FACING = CampfireBlock.FACING;

    private static final BooleanProperty LIT = CampfireBlock.LIT;

    private static final BooleanProperty SIGNAL_FIRE = CampfireBlock.SIGNAL_FIRE;

    private static final BooleanProperty WATERLOGGED = CampfireBlock.WATERLOGGED;

    public CraftCampfire(BlockState state) {
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
    public boolean isLit() {
        return this.get(LIT);
    }

    @Override
    public void setLit(final boolean lit) {
        this.set(LIT, lit);
    }

    @Override
    public boolean isSignalFire() {
        return this.get(SIGNAL_FIRE);
    }

    @Override
    public void setSignalFire(final boolean signalFire) {
        this.set(SIGNAL_FIRE, signalFire);
    }

    @Override
    public boolean isWaterlogged() {
        return this.get(WATERLOGGED);
    }

    @Override
    public void setWaterlogged(final boolean waterlogged) {
        this.set(WATERLOGGED, waterlogged);
    }
}
