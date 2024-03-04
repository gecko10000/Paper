package org.bukkit.craftbukkit.block.impl;

import io.papermc.paper.generated.GeneratedFrom;
import net.minecraft.world.level.block.BrewingStandBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.bukkit.block.data.type.BrewingStand;
import org.bukkit.craftbukkit.block.data.CraftBlockData;

@GeneratedFrom("1.20.4")
@SuppressWarnings("unused")
public class CraftBrewingStand extends CraftBlockData implements BrewingStand {
    private static final BooleanProperty HAS_BOTTLE = BrewingStandBlock.HAS_BOTTLE;

    private static final BooleanProperty HAS_BOTTLE = BrewingStandBlock.HAS_BOTTLE;

    private static final BooleanProperty HAS_BOTTLE = BrewingStandBlock.HAS_BOTTLE;

    public CraftBrewingStand(BlockState state) {
        super(state);
    }

    public boolean hasBottle0() {
        return this.get(HAS_BOTTLE);
    }

    public void hasBottle0(final boolean hasBottle0) {
        this.set(HAS_BOTTLE, hasBottle0);
    }

    public boolean hasBottle1() {
        return this.get(HAS_BOTTLE);
    }

    public void hasBottle1(final boolean hasBottle1) {
        this.set(HAS_BOTTLE, hasBottle1);
    }

    public boolean hasBottle2() {
        return this.get(HAS_BOTTLE);
    }

    public void hasBottle2(final boolean hasBottle2) {
        this.set(HAS_BOTTLE, hasBottle2);
    }
}
