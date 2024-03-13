package org.bukkit.craftbukkit.block.impl;

import io.papermc.paper.generated.GeneratedFrom;
import net.minecraft.world.level.block.CaveVinesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.bukkit.block.data.type.CaveVines;
import org.bukkit.craftbukkit.block.data.CraftBlockData;
import org.jetbrains.annotations.Range;

@GeneratedFrom("1.20.4")
@SuppressWarnings("unused")
public class CraftCaveVines extends CraftBlockData implements CaveVines {
    private static final IntegerProperty AGE = CaveVinesBlock.AGE;

    private static final BooleanProperty BERRIES = CaveVinesBlock.BERRIES;

    public CraftCaveVines(BlockState state) {
        super(state);
    }

    @Override
    @Range(
            from = 0,
            to = 25
    )
    public int getAge() {
        return this.get(AGE);
    }

    @Override
    public void setAge(@Range(from = 0, to = 25) final int age) {
        this.set(AGE, age);
    }

    @Override
    public int getMaximumAge() {
        return AGE.max;
    }

    @Override
    public boolean hasBerries() {
        return this.get(BERRIES);
    }

    @Override
    public void setBerries(final boolean berries) {
        this.set(BERRIES, berries);
    }
}
