package io.papermc.generator.types.craftblockdata.converter;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.RotationSegment;
import org.bukkit.block.BlockFace;
import org.bukkit.util.Vector;

public class RotationConverter extends Converter<Integer, BlockFace> {
    @Override
    public Property<Integer> getProperty() {
        return BlockStateProperties.ROTATION_16;
    }

    @Override
    public Class<BlockFace> getApiType() {
        return BlockFace.class;
    }

    @Override
    public void convertSetter(final MethodSpec.Builder method, final FieldSpec field, final ParameterSpec parameter) {
        method.addCode("""
               $T vec = $N.getDirection();
               $T angle = ($T) -$T.toDegrees($T.atan2(vec.getX(), vec.getZ()));
               this.set($N, $T.convertToSegment(angle));
               """, Vector.class, parameter,
                    Float.TYPE, Float.TYPE, Math.class, Math.class,
                    field, RotationSegment.class);
    }

    @Override
    public void convertGetter(final MethodSpec.Builder method, final FieldSpec field) {
        method.addCode("return CraftBlockData.ROTATION_CYCLE[this.get($N)];", field);
    }
}
