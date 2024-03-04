package org.bukkit.craftbukkit.block.impl;

import io.papermc.paper.generated.GeneratedFrom;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.RotationSegment;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Skull;
import org.bukkit.craftbukkit.block.data.CraftBlockData;
import org.bukkit.util.Vector;

@GeneratedFrom("1.20.4")
@SuppressWarnings("unused")
public class CraftSkull extends CraftBlockData implements Skull {
    private static final BooleanProperty POWERED = SkullBlock.POWERED;

    private static final IntegerProperty ROTATION = SkullBlock.ROTATION;

    public CraftSkull(BlockState state) {
        super(state);
    }

    @Override
    public boolean isPowered() {
        return this.get(POWERED);
    }

    @Override
    public void setPowered(final boolean powered) {
        this.set(POWERED, powered);
    }

    @Override
    public BlockFace getRotation() {
        return CraftBlockData.ROTATION_CYCLE[this.get(ROTATION)];
    }

    @Override
    public void setRotation(final BlockFace blockFace) {
        Vector vec = blockFace.getDirection();
        float angle = (float) -Math.toDegrees(Math.atan2(vec.getX(), vec.getZ()));
        this.set(ROTATION, RotationSegment.convertToSegment(angle));
    }
}
