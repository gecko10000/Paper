package io.papermc.generator.types.craftblockdata;

import com.squareup.javapoet.ClassName;

public final class Types {

    public static final String BASE_PACKAGE = "org.bukkit.craftbukkit";

    public static final ClassName CRAFT_BLOCKDATA = ClassName.get(BASE_PACKAGE + ".block.data", "CraftBlockData");

    public static final ClassName CRAFT_BLOCK = ClassName.get(BASE_PACKAGE + ".block", "CraftBlock");

    public static final String INDEX_VARIABLE = "index";
    public static final String ENTRY_VARIABLE = "entry";
}
