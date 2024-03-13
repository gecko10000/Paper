package org.bukkit.craftbukkit.block.impl;

import io.papermc.paper.generated.GeneratedFrom;
import java.util.Set;
import net.minecraft.world.level.block.piston.PistonHeadBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.PistonType;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.PistonHead;
import org.bukkit.craftbukkit.block.data.CraftBlockData;

@GeneratedFrom("1.20.4")
@SuppressWarnings("unused")
public class CraftPistonHead extends CraftBlockData implements PistonHead {
    private static final DirectionProperty FACING = PistonHeadBlock.FACING;

    private static final BooleanProperty SHORT = PistonHeadBlock.SHORT;

    private static final EnumProperty<PistonType> TYPE = PistonHeadBlock.TYPE;

    public CraftPistonHead(BlockState state) {
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
    public boolean isShort() {
        return this.get(SHORT);
    }

    @Override
    public void setShort(final boolean _short) {
        this.set(SHORT, _short);
    }

    @Override
    public org.bukkit.block.data.type.TechnicalPiston.Type getType() {
        return this.get(TYPE, org.bukkit.block.data.type.TechnicalPiston.Type.class);
    }

    @Override
    public void setType(final org.bukkit.block.data.type.TechnicalPiston.Type type) {
        this.set(TYPE, type);
    }
}
