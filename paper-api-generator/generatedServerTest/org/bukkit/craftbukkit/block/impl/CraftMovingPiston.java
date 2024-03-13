package org.bukkit.craftbukkit.block.impl;

import io.papermc.paper.generated.GeneratedFrom;
import java.util.Set;
import net.minecraft.world.level.block.piston.MovingPistonBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.PistonType;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.TechnicalPiston;
import org.bukkit.craftbukkit.block.data.CraftBlockData;

@GeneratedFrom("1.20.4")
@SuppressWarnings("unused")
public class CraftMovingPiston extends CraftBlockData implements TechnicalPiston {
    private static final DirectionProperty FACING = MovingPistonBlock.FACING;

    private static final EnumProperty<PistonType> TYPE = MovingPistonBlock.TYPE;

    public CraftMovingPiston(BlockState state) {
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
    public TechnicalPiston.Type getType() {
        return this.get(TYPE, TechnicalPiston.Type.class);
    }

    @Override
    public void setType(final TechnicalPiston.Type type) {
        this.set(TYPE, type);
    }
}
