package org.bukkit.craftbukkit.block.impl;

import io.papermc.paper.generated.GeneratedFrom;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.bukkit.block.data.Ageable;
import org.bukkit.craftbukkit.block.data.CraftBlockData;
import org.jetbrains.annotations.Range;

@GeneratedFrom("1.20.4")
@SuppressWarnings("unused")
public class CraftCrop extends CraftBlockData implements Ageable {
    private static final IntegerProperty AGE = CropBlock.AGE;

    public CraftCrop(BlockState state) {
        super(state);
    }

    @Override
    @Range(
            from = 0,
            to = 7
    )
    public int getAge() {
        return this.get(AGE);
    }

    @Override
    public void setAge(@Range(from = 0, to = 7) final int age) {
        this.set(AGE, age);
    }

    @Override
    public int getMaximumAge() {
        return AGE.max;
    }
}