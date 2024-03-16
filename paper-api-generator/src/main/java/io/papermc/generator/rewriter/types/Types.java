package io.papermc.generator.rewriter.types;

import io.papermc.generator.rewriter.ClassNamed;

public final class Types {

    public static final String BASE_PACKAGE = "org.bukkit.craftbukkit";
    public static final String BLOCKDATA_IMPL_PACKAGE = BASE_PACKAGE + ".block.impl";

    public static final ClassNamed CRAFT_BLOCKDATA = ClassNamed.of(BASE_PACKAGE + ".block.data", "CraftBlockData");

    public static final ClassNamed CRAFT_BLOCK = ClassNamed.of(BASE_PACKAGE + ".block", "CraftBlock");

}
