package org.bukkit.craftbukkit.block.impl;

import io.papermc.paper.generated.GeneratedFrom;
import net.minecraft.world.level.block.MangrovePropaguleBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.bukkit.block.data.type.MangrovePropagule;
import org.bukkit.craftbukkit.block.data.CraftBlockData;
import org.jetbrains.annotations.Range;

@GeneratedFrom("1.20.4")
@SuppressWarnings("unused")
public class CraftMangrovePropagule extends CraftBlockData implements MangrovePropagule {
    private static final IntegerProperty AGE = MangrovePropaguleBlock.AGE;

    private static final BooleanProperty HANGING = MangrovePropaguleBlock.HANGING;

    private static final IntegerProperty STAGE = MangrovePropaguleBlock.STAGE;

    private static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public CraftMangrovePropagule(BlockState state) {
        super(state);
    }

    @Override
    @Range(
            from = 0,
            to = 4
    )
    public int getAge() {
        return this.get(AGE);
    }

    @Override
    public void setAge(@Range(from = 0, to = 4) final int age) {
        this.set(AGE, age);
    }

    @Override
    public int getMaximumAge() {
        return AGE.max;
    }

    @Override
    public boolean isHanging() {
        return this.get(HANGING);
    }

    @Override
    public void setHanging(final boolean hanging) {
        this.set(HANGING, hanging);
    }

    @Override
    @Range(
            from = 0,
            to = 1
    )
    public int getStage() {
        return this.get(STAGE);
    }

    @Override
    public void setStage(@Range(from = 0, to = 1) final int stage) {
        this.set(STAGE, stage);
    }

    @Override
    public int getMaximumStage() {
        return STAGE.max;
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
