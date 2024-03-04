package org.bukkit.craftbukkit.block.impl;

import io.papermc.paper.generated.GeneratedFrom;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Bed;
import org.bukkit.craftbukkit.block.data.CraftBlockData;

@GeneratedFrom("1.20.4")
@SuppressWarnings("unused")
public class CraftBed extends CraftBlockData implements Bed {
    private static final DirectionProperty FACING = BedBlock.FACING;

    private static final BooleanProperty OCCUPIED = BedBlock.OCCUPIED;

    private static final EnumProperty<BedPart> PART = BedBlock.PART;

    public CraftBed(BlockState state) {
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
    public boolean isOccupied() {
        return this.get(OCCUPIED);
    }

    @Override
    public void setOccupied(final boolean occupied) {
        this.set(OCCUPIED, occupied);
    }

    @Override
    public Bed.Part getPart() {
        return this.get(PART, Bed.Part.class);
    }

    @Override
    public void setPart(final Bed.Part part) {
        this.set(PART, part);
    }
}
