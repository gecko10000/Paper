package io.papermc.generator.rewriter.types.simple;

import com.google.common.base.Suppliers;
import com.google.gson.internal.Primitives;
import io.papermc.generator.rewriter.replace.SearchMetadata;
import io.papermc.generator.rewriter.replace.SearchReplaceRewriter;
import io.papermc.generator.rewriter.utils.Annotations;
import io.papermc.generator.utils.ClassHelper;
import io.papermc.generator.utils.Formatting;
import io.papermc.generator.utils.RegistryUtils;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Unit;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.behavior.PositionTracker;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.NearestVisibleLivingEntities;
import net.minecraft.world.entity.ai.memory.WalkTarget;
import net.minecraft.world.flag.FeatureElement;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.memory.MemoryKey;
import org.checkerframework.checker.nullness.qual.Nullable;

import static io.papermc.generator.utils.Formatting.quoted;
import static javax.lang.model.element.Modifier.FINAL;
import static javax.lang.model.element.Modifier.PUBLIC;
import static javax.lang.model.element.Modifier.STATIC;

public class MemoryKeyRewriter extends SearchReplaceRewriter {

    private static final Map<MemoryModuleType<?>, Class<?>> MEMORY_GENERIC_TYPES;
    static {
        final Map<MemoryModuleType<?>, Class<?>> map = new IdentityHashMap<>();
        try {
            for (final Field field : MemoryModuleType.class.getDeclaredFields()) {
                if (!MemoryModuleType.class.isAssignableFrom(field.getType())) {
                    continue;
                }

                int mod = field.getModifiers();
                if (Modifier.isPublic(mod) & Modifier.isStatic(mod) & Modifier.isFinal(mod)) {
                    if (field.getGenericType() instanceof ParameterizedType complexType && complexType.getActualTypeArguments().length == 1) {
                        map.put((MemoryModuleType<?>) field.get(null), ClassHelper.eraseType(complexType.getActualTypeArguments()[0]));
                    }
                }
            }
        } catch (ReflectiveOperationException ex) {
            throw new RuntimeException(ex);
        }
        MEMORY_GENERIC_TYPES = Map.copyOf(map);
    }

    private final Registry<MemoryModuleType<?>> registry;
    private final Supplier<List<ResourceKey<MemoryModuleType<?>>>> experimentalKeys;
    private final boolean isFilteredRegistry;

    public MemoryKeyRewriter(final String pattern) {
        super(MemoryKey.class, pattern, false);
        this.registry = BuiltInRegistries.MEMORY_MODULE_TYPE;
        this.experimentalKeys = Suppliers.memoize(() -> RegistryUtils.collectExperimentalKeys(this.registry));
        this.isFilteredRegistry = FeatureElement.FILTERED_REGISTRIES.contains(Registries.MEMORY_MODULE_TYPE);
    }

    // this api is not implemented and is not backed by a proper registry
    private static final Set<Class<?>> IGNORED_TYPES = Set.of(
        NearestVisibleLivingEntities.class,
        WalkTarget.class,
        PositionTracker.class,
        Path.class,
        DamageSource.class,
        Vec3.class,
        BlockPos.class,
        Unit.class,
        Void.class
    );

    private static final Set<Class<?>> IGNORED_SUB_TYPES = Set.of(
        Iterable.class,
        Map.class,
        Entity.class
    );

    private static final Map<Class<?>, Class<?>> API_BRIDGE = Map.of(
        GlobalPos.class, Location.class
    );

    private static final Map<String, String> FIELD_RENAMES = Map.of(
        "LIKED_NOTEBLOCK", "LIKED_NOTEBLOCK_POSITION"
    );

    @Override
    protected void insert(final SearchMetadata metadata, final StringBuilder builder) {
        Iterator<Holder.Reference<MemoryModuleType<?>>> referenceIterator = this.registry.holders().sorted(Formatting.alphabeticKeyOrder(reference -> reference.key().location().getPath())).iterator();

    outerLoop:
        while (referenceIterator.hasNext()) {
            Holder.Reference<MemoryModuleType<?>> reference = referenceIterator.next();
            Class<?> memoryType = MEMORY_GENERIC_TYPES.get(reference.value());
            if (IGNORED_TYPES.contains(memoryType)) {
                continue;
            }
            for (Class<?> subType : IGNORED_SUB_TYPES) {
                if (subType.isAssignableFrom(memoryType)) {
                    continue outerLoop;
                }
            }

            ResourceKey<MemoryModuleType<?>> resourceKey = reference.key();
            String pathKey = resourceKey.location().getPath();

            String experimentalValue = this.getExperimentalValue(reference);
            if (experimentalValue != null) {
                Annotations.experimentalAnnotations(builder, metadata, experimentalValue);
            }

            final Class<?> apiMemoryType;
            if (!Primitives.isWrapperType(memoryType) && API_BRIDGE.containsKey(memoryType)) {
                apiMemoryType = API_BRIDGE.get(memoryType);
            } else {
                apiMemoryType = memoryType;
            }

            builder.append(metadata.indent());
            builder.append("%s %s %s ".formatted(PUBLIC, STATIC, FINAL));
            builder.append("%s<%s>".formatted(this.rewriteClass.simpleName(), apiMemoryType.getSimpleName())).append(' ').append(this.rewriteFieldName(reference));
            builder.append(" = ");
            builder.append("new %s<>(%s.minecraft(%s), %s.class)".formatted(this.rewriteClass.simpleName(), NamespacedKey.class.getSimpleName(), quoted(pathKey), apiMemoryType.getSimpleName()));
            builder.append(';');

            builder.append('\n');
            if (referenceIterator.hasNext()) {
                builder.append('\n'); // this print an extra new line even at the end caused by the skipped ones
            }
        }
    }

    protected String rewriteFieldName(Holder.Reference<MemoryModuleType<?>> reference) {
        String internalName = Formatting.formatPathAsField(reference.key().location().getPath());
        return FIELD_RENAMES.getOrDefault(internalName, internalName);
    }

    @Nullable
    protected String getExperimentalValue(Holder.Reference<MemoryModuleType<?>> reference) {
        if (this.isFilteredRegistry && reference.value() instanceof FeatureElement element && FeatureFlags.isExperimental(element.requiredFeatures())) {
            return Formatting.formatFeatureFlagSet(element.requiredFeatures());
        }
        if (this.experimentalKeys.get().contains(reference.key())) {
            return Formatting.formatFeatureFlag(FeatureFlags.UPDATE_1_21);
        }
        return null;
    }
}
