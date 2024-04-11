package io.papermc.generator.rewriter.data.sample.parser.imports;

import org.bukkit.NamespacedKey; // root class import
import org.bukkit.Statistic.Type; // inner class import (directly referenced)
import org.bukkit.*; // star import (whole package + one level)
import static org.bukkit.Statistic.ANIMALS_BRED; // static import
import static org.bukkit.Material.*; // static star import

public class StandardImportType {

}
