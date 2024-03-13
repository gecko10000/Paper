package org.bukkit.craftbukkit.block.impl;

import com.google.common.collect.ImmutableSet;
import io.papermc.paper.generated.GeneratedFrom;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Fire;
import org.bukkit.craftbukkit.block.data.CraftBlockData;

@GeneratedFrom("1.20.4")
@SuppressWarnings("unused")
public class CraftFire extends CraftBlockData implements Fire {
    private static final IntegerProperty AGE = FireBlock.AGE;

    private static final Map<BlockFace, BooleanProperty> PROPERTY_BY_DIRECTION = Map.of(
        BlockFace.EAST, FireBlock.EAST,
        BlockFace.NORTH, FireBlock.NORTH,
        BlockFace.SOUTH, FireBlock.SOUTH,
        BlockFace.UP, FireBlock.UP,
        BlockFace.WEST, FireBlock.WEST
    );

    public CraftFire(BlockState state) {
        super(state);
    }

    @Override
    public int getAge() {
        return this.get(AGE);
    }

    @Override
    public void setAge(final int age) {
        this.set(AGE, age);
    }

    @Override
    public int getMaximumAge() {
        return AGE.max;
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
