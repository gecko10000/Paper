package org.bukkit.craftbukkit.block.impl;

import io.papermc.paper.generated.GeneratedFrom;
import net.minecraft.world.level.block.GrindstoneBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Grindstone;
import org.bukkit.craftbukkit.block.data.CraftBlockData;

@GeneratedFrom("1.20.4")
@SuppressWarnings("unused")
public class CraftGrindstone extends CraftBlockData implements Grindstone {
    private static final EnumProperty<AttachFace> FACE = GrindstoneBlock.FACE;

    private static final DirectionProperty FACING = GrindstoneBlock.FACING;

    public CraftGrindstone(BlockState state) {
        super(state);
    }

    @Override
    public org.bukkit.block.data.FaceAttachable.AttachedFace getAttachedFace() {
        return this.get(FACE, org.bukkit.block.data.FaceAttachable.AttachedFace.class);
    }

    @Override
    public void setAttachedFace(
            final org.bukkit.block.data.FaceAttachable.AttachedFace attachedFace) {
        this.set(FACE, attachedFace);
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
