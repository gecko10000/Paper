package org.bukkit.craftbukkit.block.impl;

import com.google.common.collect.ImmutableSet;
import io.papermc.paper.generated.GeneratedFrom;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import net.minecraft.world.level.block.ChorusPlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.MultipleFacing;
import org.bukkit.craftbukkit.block.CraftBlock;
import org.bukkit.craftbukkit.block.data.CraftBlockData;

@GeneratedFrom("1.20.4")
@SuppressWarnings("unused")
public class CraftChorusPlant extends CraftBlockData implements MultipleFacing {
    private static final Map<BlockFace, BooleanProperty> PROPERTY_BY_DIRECTION = ChorusPlantBlock.PROPERTY_BY_DIRECTION.entrySet().stream()
            .collect(Collectors.toMap(entry -> CraftBlock.notchToBlockFace(entry.getKey()), entry -> entry.getValue()));

    public CraftChorusPlant(BlockState state) {
        super(state);
    }

    @Override
    public boolean hasFace(final BlockFace blockFace) {
        return this.get(PROPERTY_BY_DIRECTION.get(blockFace));
    }

    @Override
    public void setFace(final BlockFace blockFace, final boolean face) {
        this.set(PROPERTY_BY_DIRECTION.get(blockFace), face);
    }

    @Override
    public Set<BlockFace> getFaces() {
        ImmutableSet.Builder<BlockFace> faces = ImmutableSet.builder();
        for (BlockFace blockFace : PROPERTY_BY_DIRECTION.keySet()) {
            if (this.get(PROPERTY_BY_DIRECTION.get(blockFace))) {
                faces.add(blockFace);
            }
        }
        return faces.build();
    }

    @Override
    public Set<BlockFace> getAllowedFaces() {
        return Collections.unmodifiableSet(PROPERTY_BY_DIRECTION.keySet());
    }
}
