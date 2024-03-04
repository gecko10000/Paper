package org.bukkit.craftbukkit.block.impl;

import io.papermc.paper.generated.GeneratedFrom;
import net.minecraft.world.level.block.BellBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BellAttachType;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Bell;
import org.bukkit.craftbukkit.block.data.CraftBlockData;

@GeneratedFrom("1.20.4")
@SuppressWarnings("unused")
public class CraftBell extends CraftBlockData implements Bell {
    private static final EnumProperty<BellAttachType> ATTACHMENT = BellBlock.ATTACHMENT;

    private static final DirectionProperty FACING = BellBlock.FACING;

    private static final BooleanProperty POWERED = BellBlock.POWERED;

    public CraftBell(BlockState state) {
        super(state);
    }

    @Override
    public Bell.Attachment getAttachment() {
        return this.get(ATTACHMENT, Bell.Attachment.class);
    }

    @Override
    public void setAttachment(final Bell.Attachment attachment) {
        this.set(ATTACHMENT, attachment);
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
