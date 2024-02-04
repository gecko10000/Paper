package io.papermc.generator.types.registry;

import com.squareup.javapoet.TypeSpec;
import io.papermc.generator.utils.Javadocs;
import io.papermc.paper.registry.RegistryKey;
import net.minecraft.core.registries.Registries;
import org.bukkit.generator.structure.StructureType;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;

@DefaultQualifier(NonNull.class)
public class StructureTypeGenerator extends RegistryGenerator<net.minecraft.world.level.levelgen.structure.StructureType<?>, StructureType> {

    private static final String CLASS_HEADER = Javadocs.getVersionDependentClassHeader("StructureTypes");

    public StructureTypeGenerator(final String className, final String pkg) {
        super(className, pkg, Registries.STRUCTURE_TYPE, RegistryKey.STRUCTURE_TYPE, false);
    }

    @Override
    public void addExtras(final TypeSpec.Builder builder) {
        builder.addJavadoc(CLASS_HEADER);
    }

}
