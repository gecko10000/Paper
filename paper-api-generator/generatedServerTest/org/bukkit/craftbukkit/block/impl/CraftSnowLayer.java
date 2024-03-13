package org.bukkit.craftbukkit.block.impl;

import io.papermc.paper.generated.GeneratedFrom;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.bukkit.block.data.type.Snow;
import org.bukkit.craftbukkit.block.data.CraftBlockData;
import org.jetbrains.annotations.Range;

@GeneratedFrom("1.20.4")
@SuppressWarnings("unused")
public class CraftSnowLayer extends CraftBlockData implements Snow {
    private static final IntegerProperty LAYERS = SnowLayerBlock.LAYERS;

    public CraftSnowLayer(BlockState state) {
        super(state);
    }

    @Override
    @Range(
            from = 1,
            to = 8
    )
    public int getLayers() {
        return this.get(LAYERS);
    }

    @Override
    public void setLayers(@Range(from = 1, to = 8) final int layers) {
        this.set(LAYERS, layers);
    }

    @Override
    public int getMinimumLayers() {
        return LAYERS.min;
    }

    @Override
    public int getMaximumLayers() {
        return LAYERS.max;
    }
}
