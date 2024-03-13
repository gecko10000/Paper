package io.papermc.generator.types.craftblockdata.property.converter;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.RotationSegment;
import org.bukkit.block.BlockFace;
import org.bukkit.util.Vector;

public class RotationConverter implements Converter<Integer, BlockFace> {

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
        method.addStatement("$T dir = $N.getDirection()", Vector.class, parameter);
        method.addStatement("$1T angle = ($1T) -$2T.toDegrees($2T.atan2(dir.getX(), dir.getZ()))", Float.TYPE, Math.class);
        method.addStatement(this.rawSetExprent().formatted("$N"), field, RotationSegment.class);
    }

    @Override
    public String rawSetExprent() {
        return "this.set(%s, $T.convertToSegment(angle))";
    }

    @Override
    public String rawGetExprent() {
        return "CraftBlockData.ROTATION_CYCLE[this.get(%s)]";
    }
}
