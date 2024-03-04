package org.bukkit.craftbukkit.block.impl;

import io.papermc.paper.generated.GeneratedFrom;
import net.minecraft.world.level.block.TrialSpawnerBlock;
import net.minecraft.world.level.block.entity.trialspawner.TrialSpawnerState;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.bukkit.block.data.type.TrialSpawner;
import org.bukkit.craftbukkit.block.data.CraftBlockData;

@GeneratedFrom("1.20.4")
@SuppressWarnings("unused")
public class CraftTrialSpawner extends CraftBlockData implements TrialSpawner {
    private static final EnumProperty<TrialSpawnerState> STATE = TrialSpawnerBlock.STATE;

    public CraftTrialSpawner(BlockState state) {
        super(state);
    }

    @Override
    public TrialSpawner.State getTrialSpawnerState() {
        return this.get(STATE, TrialSpawner.State.class);
    }

    @Override
    public void setTrialSpawnerState(final TrialSpawner.State state) {
        this.set(STATE, state);
    }
}
