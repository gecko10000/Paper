package org.bukkit.craftbukkit.block.impl;

import io.papermc.paper.generated.GeneratedFrom;
import net.minecraft.world.level.block.BannerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.RotationSegment;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Rotatable;
import org.bukkit.craftbukkit.block.data.CraftBlockData;
import org.bukkit.util.Vector;

@GeneratedFrom("1.20.4")
@SuppressWarnings("unused")
public class CraftBanner extends CraftBlockData implements Rotatable {
    private static final IntegerProperty ROTATION = BannerBlock.ROTATION;

    public CraftBanner(BlockState state) {
        super(state);
    }

    @Override
    public BlockFace getRotation() {
        return CraftBlockData.ROTATION_CYCLE[this.get(ROTATION)];
    }

    @Override
    public void setRotation(final BlockFace blockFace) {
        Vector dir = blockFace.getDirection();
        float angle = (float) -Math.toDegrees(Math.atan2(dir.getX(), dir.getZ()));
        this.set(ROTATION, RotationSegment.convertToSegment(angle));
    }
}
