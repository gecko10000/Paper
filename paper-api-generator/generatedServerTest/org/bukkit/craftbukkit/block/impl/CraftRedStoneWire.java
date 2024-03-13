package org.bukkit.craftbukkit.block.impl;

import io.papermc.paper.generated.GeneratedFrom;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import net.minecraft.world.level.block.RedStoneWireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.RedstoneSide;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.RedstoneWire;
import org.bukkit.craftbukkit.block.CraftBlock;
import org.bukkit.craftbukkit.block.data.CraftBlockData;

@GeneratedFrom("1.20.4")
@SuppressWarnings("unused")
public class CraftRedStoneWire extends CraftBlockData implements RedstoneWire {
    private static final IntegerProperty POWER = RedStoneWireBlock.POWER;

    private static final Map<BlockFace, EnumProperty<RedstoneSide>> PROPERTY_BY_DIRECTION = RedStoneWireBlock.PROPERTY_BY_DIRECTION.entrySet().stream()
            .collect(Collectors.toMap(entry -> CraftBlock.notchToBlockFace(entry.getKey()), entry -> entry.getValue()));

    public CraftRedStoneWire(BlockState state) {
        super(state);
    }

    @Override
    public int getPower() {
        return this.get(POWER);
    }

    @Override
    public void setPower(final int power) {
        this.set(POWER, power);
    }

    @Override
    public int getMaximumPower() {
        return POWER.max;
    }

    @Override
    public RedstoneWire.Connection getFace(final BlockFace blockFace) {
        return this.get(PROPERTY_BY_DIRECTION.get(blockFace), RedstoneWire.Connection.class);
    }

    @Override
    public void setFace(final BlockFace blockFace, final RedstoneWire.Connection connection) {
        this.set(PROPERTY_BY_DIRECTION.get(blockFace), connection);
    }

    @Override
    public Set<BlockFace> getAllowedFaces() {
        return Collections.unmodifiableSet(PROPERTY_BY_DIRECTION.keySet());
    }
}
