package io.papermc.generator.types.craftblockdata.property.holder;

import com.google.common.base.Preconditions;
import org.jetbrains.annotations.Contract;

public class VirtualFieldInfo {

    private final String name;
    private final Class<?> valueClass;
    private final DataHolderType holderType;
    private final String baseName;
    private Class<?> keyClass;

    private VirtualFieldInfo(String name, Class<?> valueClass, DataHolderType type, String baseName) {
        this.name = name;
        this.valueClass = valueClass;
        this.holderType = type;
        this.baseName = baseName;
    }

    public static VirtualFieldInfo create(String name, Class<?> valueClass, DataHolderType type, String baseName) {
        return new VirtualFieldInfo(name, valueClass, type, baseName);
    }

    @Contract(value = "_ -> this", mutates = "this")
    public VirtualFieldInfo keyClass(Class<?> keyClass) {
        Preconditions.checkState(this.holderType == DataHolderType.MAP, "Custom key class is only relevant for map structure");
        this.keyClass = keyClass;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public Class<?> getValueClass() {
        return this.valueClass;
    }

    public String getBaseName() {
        return this.baseName;
    }

    public DataHolderType getHolderType() {
        return this.holderType;
    }

    public Class<?> getKeyClass() {
        return this.keyClass;
    }
}
