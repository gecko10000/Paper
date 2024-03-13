package io.papermc.generator.types.craftblockdata;

import io.papermc.generator.utils.BlockStateMapping;
import net.minecraft.world.level.block.Block;
import org.bukkit.block.data.BlockData;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public final class CraftBlockDataGenerators {

    public static void generate(Path container) throws IOException {
        for (Map.Entry<Class<? extends Block>, BlockStateMapping.BlockData> entry : BlockStateMapping.MAPPING.entrySet()) {
            Class<? extends BlockData> api = BlockStateMapping.getBestSuitedApiClass(entry.getKey());
            if (api == null) {
                continue;
            }

            new CraftBlockDataGenerator<>(entry.getKey(), entry.getValue(), api).writeToFile(container);
        }
    }

}
