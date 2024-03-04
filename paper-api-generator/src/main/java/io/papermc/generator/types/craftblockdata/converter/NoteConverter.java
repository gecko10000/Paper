package io.papermc.generator.types.craftblockdata.converter;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.RotationSegment;
import org.bukkit.Note;
import org.bukkit.util.Vector;

public class NoteConverter extends Converter<Integer, Note> {
    @Override
    public Property<Integer> getProperty() {
        return BlockStateProperties.NOTE;
    }

    @Override
    public Class<Note> getApiType() {
        return Note.class;
    }

    @Override
    public void convertSetter(final MethodSpec.Builder method, final FieldSpec field, final ParameterSpec parameter) {
        method.addCode("this.set($N, (int) $N.getId());", field, parameter);
    }

    @Override
    public void convertGetter(final MethodSpec.Builder method, final FieldSpec field) {
        method.addCode("return new $T(this.get($N));", Note.class, field);
    }
}
