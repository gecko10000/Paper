package org.bukkit.craftbukkit.block.impl;

import io.papermc.paper.generated.GeneratedFrom;
import net.minecraft.world.level.block.SculkShriekerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.bukkit.block.data.type.SculkShrieker;
import org.bukkit.craftbukkit.block.data.CraftBlockData;

@GeneratedFrom("1.20.4")
@SuppressWarnings("unused")
public class CraftSculkShrieker extends CraftBlockData implements SculkShrieker {
    private static final BooleanProperty CAN_SUMMON = SculkShriekerBlock.CAN_SUMMON;

    private static final BooleanProperty SHRIEKING = SculkShriekerBlock.SHRIEKING;

    private static final BooleanProperty WATERLOGGED = SculkShriekerBlock.WATERLOGGED;

    public CraftSculkShrieker(BlockState state) {
        super(state);
    }

    public boolean getCanSummon() {
        return this.get(CAN_SUMMON);
    }

    @Override
    public void setCanSummon(final boolean canSummon) {
        this.set(CAN_SUMMON, canSummon);
    }

    public boolean getShrieking() {
        return this.get(SHRIEKING);
    }

    @Override
    public void setShrieking(final boolean shrieking) {
        this.set(SHRIEKING, shrieking);
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
