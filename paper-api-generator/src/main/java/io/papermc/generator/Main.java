package io.papermc.generator;

import com.google.common.util.concurrent.MoreExecutors;
import com.mojang.logging.LogUtils;
import io.papermc.generator.rewriter.SourceRewriter;
import io.papermc.generator.types.SourceGenerator;
import io.papermc.generator.utils.TagCollector;
import io.papermc.generator.utils.TagResult;
import java.nio.file.Files;
import java.nio.file.Path;
import net.minecraft.SharedConstants;
import net.minecraft.commands.Commands;
import net.minecraft.core.LayeredRegistryAccess;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.RegistryDataLoader;
import net.minecraft.server.Bootstrap;
import net.minecraft.server.RegistryLayer;
import net.minecraft.server.ReloadableServerResources;
import net.minecraft.server.WorldLoader;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackRepository;
import net.minecraft.server.packs.repository.ServerPacksSource;
import net.minecraft.server.packs.resources.MultiPackResourceManager;
import net.minecraft.world.flag.FeatureFlags;
import org.apache.commons.io.file.PathUtils;
import org.slf4j.Logger;

public final class Main {

    private static final Logger LOGGER = LogUtils.getLogger();
    public static final RegistryAccess.Frozen REGISTRY_ACCESS;
    public static final TagResult EXPERIMENTAL_TAGS;
    public static Path generatedPath;

    static {
        SharedConstants.tryDetectVersion();
        Bootstrap.bootStrap();
        final PackRepository resourceRepository = ServerPacksSource.createVanillaTrustedRepository();
        resourceRepository.reload();
        final MultiPackResourceManager resourceManager = new MultiPackResourceManager(PackType.SERVER_DATA, resourceRepository.getAvailablePacks().stream().map(Pack::open).toList());
        LayeredRegistryAccess<RegistryLayer> layers = RegistryLayer.createRegistryAccess();
        layers = WorldLoader.loadAndReplaceLayer(resourceManager, layers, RegistryLayer.WORLDGEN, RegistryDataLoader.WORLDGEN_REGISTRIES);
        REGISTRY_ACCESS = layers.compositeAccess().freeze();
        final ReloadableServerResources datapack = ReloadableServerResources.loadResources(resourceManager, REGISTRY_ACCESS, FeatureFlags.REGISTRY.allFlags(), Commands.CommandSelection.DEDICATED, 0, MoreExecutors.directExecutor(), MoreExecutors.directExecutor()).join();
        datapack.updateRegistryTags(REGISTRY_ACCESS);
        EXPERIMENTAL_TAGS = TagCollector.grabExperimental(resourceManager);
    }

    private Main() {
    }

    public static void main(final String[] args) {
        LOGGER.info("Running API generators...");
        Main.generatedPath = Path.of(args[0]); // todo remove
        generate(Main.generatedPath, Generators.API);
        apply(Path.of(args[1]), Generators.API_REWRITE);
    }

    private static void generate(Path output, SourceGenerator[] generators) {
        try {
            if (Files.exists(output)) {
                PathUtils.deleteDirectory(output);
            }
            Files.createDirectories(output);

            for (final SourceGenerator generator : generators) {
                generator.writeToFile(output);
            }

            LOGGER.info("Files written to {}", output.toAbsolutePath());
        } catch (final Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private static void apply(Path output, SourceRewriter[] generators) {
        try {
            for (final SourceRewriter generator : generators) {
                generator.writeToFile(output);
            }

            LOGGER.info("Files written to {}", output.toAbsolutePath());
        } catch (final RuntimeException ex) {
            throw ex;
        } catch (final Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
