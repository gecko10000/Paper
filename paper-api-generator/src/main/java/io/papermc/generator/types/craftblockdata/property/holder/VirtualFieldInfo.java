package io.papermc.generator.types.craftblockdata.property.holder;

public class VirtualFieldInfo {

    private final String name;
    private final Class<?> valueClass;
    private final DataHolderType holderType;
    private final String baseName;
    private final Class<?> keyClass;

    private VirtualFieldInfo(String name, Class<?> valueClass, DataHolderType type, String baseName, Class<?> keyClass) {
        this.name = name;
        this.valueClass = valueClass;
        this.holderType = type;
        this.baseName = baseName;
        this.keyClass = keyClass;
    }

    public static VirtualFieldInfo createCollection(String name, Class<?> valueClass, boolean isArray, String baseName) {
        return new VirtualFieldInfo(name, valueClass, isArray ? DataHolderType.ARRAY : DataHolderType.LIST, baseName, null);
    }

    public static VirtualFieldInfo createMap(String name, Class<?> keyClass, Class<?> valueClass, String baseName) {
        return new VirtualFieldInfo(name, valueClass, DataHolderType.MAP, baseName, keyClass);
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
