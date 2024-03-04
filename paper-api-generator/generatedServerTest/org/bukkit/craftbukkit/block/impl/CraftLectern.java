package org.bukkit.craftbukkit.block.impl;

import io.papermc.paper.generated.GeneratedFrom;
import net.minecraft.world.level.block.LecternBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Lectern;
import org.bukkit.craftbukkit.block.data.CraftBlockData;

@GeneratedFrom("1.20.4")
@SuppressWarnings("unused")
public class CraftLectern extends CraftBlockData implements Lectern {
    private static final DirectionProperty FACING = LecternBlock.FACING;

    private static final BooleanProperty HAS_BOOK = LecternBlock.HAS_BOOK;

    private static final BooleanProperty POWERED = LecternBlock.POWERED;

    public CraftLectern(BlockState state) {
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
    public boolean hasBook() {
        return this.get(HAS_BOOK);
    }

    public void hasBook(final boolean hasBook) {
        this.set(HAS_BOOK, hasBook);
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
