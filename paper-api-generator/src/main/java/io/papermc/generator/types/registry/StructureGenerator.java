package io.papermc.generator.types.registry;

import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import io.papermc.generator.Main;
import io.papermc.generator.utils.Javadocs;
import io.papermc.paper.registry.RegistryKey;
import net.minecraft.core.registries.Registries;
import org.bukkit.generator.structure.Structure;
import org.bukkit.generator.structure.StructureType;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;

import static io.papermc.generator.utils.Annotations.NOT_NULL;
import static javax.lang.model.element.Modifier.ABSTRACT;
import static javax.lang.model.element.Modifier.PUBLIC;

@DefaultQualifier(NonNull.class)
public class StructureGenerator extends RegistryGenerator<net.minecraft.world.level.levelgen.structure.Structure, Structure> {

    private static final String CLASS_HEADER = Javadocs.getVersionDependentClassHeader("Structures");

    private static final String GET_STRUCTURE_TYPE_JAVADOC = """
        Returns the type of the structure.

        @return the type of structure
        """;

    public StructureGenerator(final String className, final String pkg) {
        super(className, Structure.class, pkg, RegistryKey.STRUCTURE, false);
    }

    @Override
    public void addExtras(final TypeSpec.Builder builder) {
        builder.addJavadoc(CLASS_HEADER);

        builder.addMethod(MethodSpec.methodBuilder("getStructureType")
            .addModifiers(PUBLIC, ABSTRACT)
            .returns(StructureType.class)
            .addJavadoc(GET_STRUCTURE_TYPE_JAVADOC)
            .addAnnotation(NOT_NULL).build());
    }

    @Override
    public net.minecraft.core.Registry<net.minecraft.world.level.levelgen.structure.Structure> getRegistry() {
        return Main.REGISTRY_ACCESS.registryOrThrow(Registries.STRUCTURE);
    }

}
