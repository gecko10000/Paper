package org.bukkit.craftbukkit.block.impl;

import io.papermc.paper.generated.GeneratedFrom;
import net.minecraft.world.level.block.ChiseledBookShelfBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.ChiseledBookshelf;
import org.bukkit.craftbukkit.block.data.CraftBlockData;

@GeneratedFrom("1.20.4")
@SuppressWarnings("unused")
public class CraftChiseledBookShelf extends CraftBlockData implements ChiseledBookshelf {
    private static final DirectionProperty HORIZONTAL_FACING = BlockStateProperties.HORIZONTAL_FACING;

    private static final BooleanProperty SLOT_OCCUPIED_PROPERTIES = ChiseledBookShelfBlock.SLOT_OCCUPIED_PROPERTIES;

    private static final BooleanProperty SLOT_OCCUPIED_PROPERTIES = ChiseledBookShelfBlock.SLOT_OCCUPIED_PROPERTIES;

    private static final BooleanProperty SLOT_OCCUPIED_PROPERTIES = ChiseledBookShelfBlock.SLOT_OCCUPIED_PROPERTIES;

    private static final BooleanProperty SLOT_OCCUPIED_PROPERTIES = ChiseledBookShelfBlock.SLOT_OCCUPIED_PROPERTIES;

    private static final BooleanProperty SLOT_OCCUPIED_PROPERTIES = ChiseledBookShelfBlock.SLOT_OCCUPIED_PROPERTIES;

    private static final BooleanProperty SLOT_OCCUPIED_PROPERTIES = ChiseledBookShelfBlock.SLOT_OCCUPIED_PROPERTIES;

    public CraftChiseledBookShelf(BlockState state) {
        super(state);
    }

    @Override
    public BlockFace getFacing() {
        return this.get(HORIZONTAL_FACING, BlockFace.class);
    }

    @Override
    public void setFacing(final BlockFace blockFace) {
        this.set(HORIZONTAL_FACING, blockFace);
    }

    public boolean getSlot0Occupied() {
        return this.get(SLOT_OCCUPIED_PROPERTIES);
    }

    public void setSlot0Occupied(final boolean slot0Occupied) {
        this.set(SLOT_OCCUPIED_PROPERTIES, slot0Occupied);
    }

    public boolean getSlot1Occupied() {
        return this.get(SLOT_OCCUPIED_PROPERTIES);
    }

    public void setSlot1Occupied(final boolean slot1Occupied) {
        this.set(SLOT_OCCUPIED_PROPERTIES, slot1Occupied);
    }

    public boolean getSlot2Occupied() {
        return this.get(SLOT_OCCUPIED_PROPERTIES);
    }

    public void setSlot2Occupied(final boolean slot2Occupied) {
        this.set(SLOT_OCCUPIED_PROPERTIES, slot2Occupied);
    }

    public boolean getSlot3Occupied() {
        return this.get(SLOT_OCCUPIED_PROPERTIES);
    }

    public void setSlot3Occupied(final boolean slot3Occupied) {
        this.set(SLOT_OCCUPIED_PROPERTIES, slot3Occupied);
    }

    public boolean getSlot4Occupied() {
        return this.get(SLOT_OCCUPIED_PROPERTIES);
    }

    public void setSlot4Occupied(final boolean slot4Occupied) {
        this.set(SLOT_OCCUPIED_PROPERTIES, slot4Occupied);
    }

    public boolean getSlot5Occupied() {
        return this.get(SLOT_OCCUPIED_PROPERTIES);
    }

    public void setSlot5Occupied(final boolean slot5Occupied) {
        this.set(SLOT_OCCUPIED_PROPERTIES, slot5Occupied);
    }
}
