package io.papermc.generator.rewriter.types;

import io.papermc.generator.Main;
import io.papermc.generator.rewriter.SearchMetadata;
import io.papermc.generator.rewriter.SearchReplaceRewriter;
import io.papermc.generator.rewriter.utils.Annotations;
import io.papermc.generator.utils.Formatting;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import io.papermc.generator.utils.TagRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import org.bukkit.Bukkit;
import org.bukkit.Fluid;
import org.bukkit.GameEvent;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;

import static io.papermc.generator.utils.Formatting.quoted;
import static io.papermc.generator.utils.TagRegistry.registry;

public class TagRewriter extends SearchReplaceRewriter {

    private static final List<TagRegistry> TAG_REGISTRIES = List.of(
        registry("blocks", Material.class, Registries.BLOCK),
        registry("items", Material.class, Registries.ITEM),
        registry("fluids", Fluid.class, Registries.FLUID),
        registry("entity_types", EntityType.class, Registries.ENTITY_TYPE),
        registry("game_events", GameEvent.class, Registries.GAME_EVENT)
    );


    public TagRewriter(final Class<?> rewriteClass, final String pattern) {
        super(rewriteClass, pattern, false);
    }

    @Override
    protected void insert(final SearchMetadata metadata, final StringBuilder builder) {
        for (int i = 0, len = TAG_REGISTRIES.size(); i < len; i++) {
            final TagRegistry tagRegistry = TAG_REGISTRIES.get(i);

            final ResourceKey<? extends Registry<?>> registryKey = tagRegistry.registryKey();
            final Registry<?> registry = Main.REGISTRY_ACCESS.registryOrThrow(registryKey);
            final Collection<String> experimentalTags = Main.EXPERIMENTAL_TAGS.perRegistry().get(registryKey);

            final String fieldPrefix = Formatting.formatTagFieldPrefix(tagRegistry.name(), registryKey);
            final String registryFieldName = "REGISTRY_" + tagRegistry.name().toUpperCase(Locale.ENGLISH);

            // registry name field
            builder.append(metadata.indent());
            builder.append("%s %s = %s;".formatted(String.class.getSimpleName(), registryFieldName, quoted(tagRegistry.name())));

            builder.append('\n');
            builder.append('\n');

            Iterator<? extends TagKey<?>> keyIterator = registry.getTagNames().sorted(Formatting.alphabeticOrder(tagKey -> tagKey.location().getPath())).iterator();

            while (keyIterator.hasNext()) {
                TagKey<?> tagKey = keyIterator.next();
                final String keyPath = tagKey.location().getPath();
                final String fieldName = fieldPrefix + Formatting.formatPathAsField(keyPath);

                // tag field
                if (experimentalTags.contains(keyPath)) {
                    Annotations.experimentalAnnotations(builder, metadata, Formatting.formatFeatureFlagName(Main.EXPERIMENTAL_TAGS.perFeatureFlag().get(tagKey)));
                }

                builder.append(metadata.indent());
                builder.append("%s<%s>".formatted(this.rewriteClass.getSimpleName(), tagRegistry.apiType().getSimpleName())).append(' ').append(fieldName);
                builder.append(" = ");
                builder.append("%s.getTag(%s, %s.minecraft(%s), %s.class)".formatted(Bukkit.class.getSimpleName(), registryFieldName, NamespacedKey.class.getSimpleName(), quoted(keyPath), tagRegistry.apiType().getSimpleName()));
                builder.append(';');

                builder.append('\n');
                if (keyIterator.hasNext()) {
                    builder.append('\n');
                }
            }

            if (i != len - 1) {
                builder.append('\n'); // extra line before the registry field
            }
        }
    }
}
