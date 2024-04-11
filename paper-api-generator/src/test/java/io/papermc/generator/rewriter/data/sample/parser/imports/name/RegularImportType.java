package io.papermc.generator.rewriter.data.sample.parser.imports.name;

import org.bukkit.Material;
import static org.bukkit.Statistic.ARMOR_CLEANED;
import static org.bukkit.Statistic.valueOf;

public class RegularImportType {
    {
        var a = Material.VINE;
        var b = ARMOR_CLEANED;
        valueOf(b.name());
    }
}
