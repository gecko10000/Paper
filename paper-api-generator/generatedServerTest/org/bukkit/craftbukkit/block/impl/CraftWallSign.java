package org.bukkit.craftbukkit.block.impl;

import io.papermc.paper.generated.GeneratedFrom;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.WallSign;
import org.bukkit.craftbukkit.block.data.CraftBlockData;

@GeneratedFrom("1.20.4")
@SuppressWarnings("unused")
public class CraftWallSign extends CraftBlockData implements WallSign {
    private static final DirectionProperty FACING = WallSignBlock.FACING;

    private static final BooleanProperty WATERLOGGED = WallSignBlock.WATERLOGGED;

    public CraftWallSign(BlockState state) {
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
    public boolean isWaterlogged() {
        return this.get(WATERLOGGED);
    }

    @Override
    public void setWaterlogged(final boolean waterlogged) {
        this.set(WATERLOGGED, waterlogged);
    }
}
