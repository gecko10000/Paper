package org.bukkit.craftbukkit.block.impl;

import io.papermc.paper.generated.GeneratedFrom;
import net.minecraft.world.level.block.CrafterBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.bukkit.block.data.type.Crafter;
import org.bukkit.craftbukkit.block.data.CraftBlockData;

@GeneratedFrom("1.20.4")
@SuppressWarnings("unused")
public class CraftCrafter extends CraftBlockData implements Crafter {
    private static final BooleanProperty CRAFTING = CrafterBlock.CRAFTING;

    private static final BooleanProperty TRIGGERED = CrafterBlock.TRIGGERED;

    public CraftCrafter(BlockState state) {
        super(state);
    }

    @Override
    public boolean isCrafting() {
        return this.get(CRAFTING);
    }

    @Override
    public void setCrafting(final boolean crafting) {
        this.set(CRAFTING, crafting);
    }

    @Override
    public boolean isTriggered() {
        return this.get(TRIGGERED);
    }

    @Override
    public void setTriggered(final boolean triggered) {
        this.set(TRIGGERED, triggered);
    }
}
