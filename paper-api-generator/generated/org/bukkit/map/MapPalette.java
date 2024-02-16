package org.bukkit.map;

import com.google.common.base.Preconditions;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents the palette that map items use.
 * <p>
 * These fields are hee base color ranges. Each entry corresponds to four
 * colors of varying shades with values entry to entry + 3.
 */
public final class MapPalette {
    // Internal mechanisms
    private MapPalette() {}

    @NotNull
    private static Color c(int r, int g, int b) {
        return new Color(r, g, b);
    }

    @NotNull
    private static Color c(int r, int g, int b, int a) {
        return new Color(r, g, b, a);
    }

    private static double getDistance(@NotNull Color c1, @NotNull Color c2) {
        double rmean = (c1.getRed() + c2.getRed()) / 2.0;
        double r = c1.getRed() - c2.getRed();
        double g = c1.getGreen() - c2.getGreen();
        int b = c1.getBlue() - c2.getBlue();
        double weightR = 2 + rmean / 256.0;
        double weightG = 4.0;
        double weightB = 2 + (255 - rmean) / 256.0;
        return weightR * r * r + weightG * g * g + weightB * b * b;
    }

    @NotNull
    static final Color[] colors = {
        // Paper start - Generated/MapPalette#colors
        // @GeneratedFrom 1.20.4
        new Color(0, 0, 0, 0),
        new Color(0, 0, 0, 0),
        new Color(0, 0, 0, 0),
        new Color(0, 0, 0, 0),
        new Color(89, 125, 39),
        new Color(109, 153, 48),
        new Color(127, 178, 56),
        new Color(67, 94, 29),
        new Color(174, 164, 115),
        new Color(213, 201, 140),
        new Color(247, 233, 163),
        new Color(130, 123, 86),
        new Color(140, 140, 140),
        new Color(171, 171, 171),
        new Color(199, 199, 199),
        new Color(105, 105, 105),
        new Color(180, 0, 0),
        new Color(220, 0, 0),
        new Color(255, 0, 0),
        new Color(135, 0, 0),
        new Color(112, 112, 180),
        new Color(138, 138, 220),
        new Color(160, 160, 255),
        new Color(84, 84, 135),
        new Color(117, 117, 117),
        new Color(144, 144, 144),
        new Color(167, 167, 167),
        new Color(88, 88, 88),
        new Color(0, 87, 0),
        new Color(0, 106, 0),
        new Color(0, 124, 0),
        new Color(0, 65, 0),
        new Color(180, 180, 180),
        new Color(220, 220, 220),
        new Color(255, 255, 255),
        new Color(135, 135, 135),
        new Color(115, 118, 129),
        new Color(141, 144, 158),
        new Color(164, 168, 184),
        new Color(86, 88, 97),
        new Color(106, 76, 54),
        new Color(130, 94, 66),
        new Color(151, 109, 77),
        new Color(79, 57, 40),
        new Color(79, 79, 79),
        new Color(96, 96, 96),
        new Color(112, 112, 112),
        new Color(59, 59, 59),
        new Color(45, 45, 180),
        new Color(55, 55, 220),
        new Color(64, 64, 255),
        new Color(33, 33, 135),
        new Color(100, 84, 50),
        new Color(123, 102, 62),
        new Color(143, 119, 72),
        new Color(75, 63, 38),
        new Color(180, 177, 172),
        new Color(220, 217, 211),
        new Color(255, 252, 245),
        new Color(135, 133, 129),
        new Color(152, 89, 36),
        new Color(186, 109, 44),
        new Color(216, 127, 51),
        new Color(114, 67, 27),
        new Color(125, 53, 152),
        new Color(153, 65, 186),
        new Color(178, 76, 216),
        new Color(94, 40, 114),
        new Color(72, 108, 152),
        new Color(88, 132, 186),
        new Color(102, 153, 216),
        new Color(54, 81, 114),
        new Color(161, 161, 36),
        new Color(197, 197, 44),
        new Color(229, 229, 51),
        new Color(121, 121, 27),
        new Color(89, 144, 17),
        new Color(109, 176, 21),
        new Color(127, 204, 25),
        new Color(67, 108, 13),
        new Color(170, 89, 116),
        new Color(208, 109, 142),
        new Color(242, 127, 165),
        new Color(128, 67, 87),
        new Color(53, 53, 53),
        new Color(65, 65, 65),
        new Color(76, 76, 76),
        new Color(40, 40, 40),
        new Color(108, 108, 108),
        new Color(132, 132, 132),
        new Color(153, 153, 153),
        new Color(81, 81, 81),
        new Color(53, 89, 108),
        new Color(65, 109, 132),
        new Color(76, 127, 153),
        new Color(40, 67, 81),
        new Color(89, 44, 125),
        new Color(109, 54, 153),
        new Color(127, 63, 178),
        new Color(67, 33, 94),
        new Color(36, 53, 125),
        new Color(44, 65, 153),
        new Color(51, 76, 178),
        new Color(27, 40, 94),
        new Color(72, 53, 36),
        new Color(88, 65, 44),
        new Color(102, 76, 51),
        new Color(54, 40, 27),
        new Color(72, 89, 36),
        new Color(88, 109, 44),
        new Color(102, 127, 51),
        new Color(54, 67, 27),
        new Color(108, 36, 36),
        new Color(132, 44, 44),
        new Color(153, 51, 51),
        new Color(81, 27, 27),
        new Color(17, 17, 17),
        new Color(21, 21, 21),
        new Color(25, 25, 25),
        new Color(13, 13, 13),
        new Color(176, 168, 54),
        new Color(215, 205, 66),
        new Color(250, 238, 77),
        new Color(132, 126, 40),
        new Color(64, 154, 150),
        new Color(79, 188, 183),
        new Color(92, 219, 213),
        new Color(48, 115, 112),
        new Color(52, 90, 180),
        new Color(63, 110, 220),
        new Color(74, 128, 255),
        new Color(39, 67, 135),
        new Color(0, 153, 40),
        new Color(0, 187, 50),
        new Color(0, 217, 58),
        new Color(0, 114, 30),
        new Color(91, 60, 34),
        new Color(111, 74, 42),
        new Color(129, 86, 49),
        new Color(68, 45, 25),
        new Color(79, 1, 0),
        new Color(96, 1, 0),
        new Color(112, 2, 0),
        new Color(59, 1, 0),
        new Color(147, 124, 113),
        new Color(180, 152, 138),
        new Color(209, 177, 161),
        new Color(110, 93, 85),
        new Color(112, 57, 25),
        new Color(137, 70, 31),
        new Color(159, 82, 36),
        new Color(84, 43, 19),
        new Color(105, 61, 76),
        new Color(128, 75, 93),
        new Color(149, 87, 108),
        new Color(78, 46, 57),
        new Color(79, 76, 97),
        new Color(96, 93, 119),
        new Color(112, 108, 138),
        new Color(59, 57, 73),
        new Color(131, 93, 25),
        new Color(160, 114, 31),
        new Color(186, 133, 36),
        new Color(98, 70, 19),
        new Color(72, 82, 37),
        new Color(88, 100, 45),
        new Color(103, 117, 53),
        new Color(54, 61, 28),
        new Color(112, 54, 55),
        new Color(138, 66, 67),
        new Color(160, 77, 78),
        new Color(84, 40, 41),
        new Color(40, 28, 24),
        new Color(49, 35, 30),
        new Color(57, 41, 35),
        new Color(30, 21, 18),
        new Color(95, 75, 69),
        new Color(116, 92, 84),
        new Color(135, 107, 98),
        new Color(71, 56, 51),
        new Color(61, 64, 64),
        new Color(75, 79, 79),
        new Color(87, 92, 92),
        new Color(46, 48, 48),
        new Color(86, 51, 62),
        new Color(105, 62, 75),
        new Color(122, 73, 88),
        new Color(64, 38, 46),
        new Color(53, 43, 64),
        new Color(65, 53, 79),
        new Color(76, 62, 92),
        new Color(40, 32, 48),
        new Color(53, 35, 24),
        new Color(65, 43, 30),
        new Color(76, 50, 35),
        new Color(40, 26, 18),
        new Color(53, 57, 29),
        new Color(65, 70, 36),
        new Color(76, 82, 42),
        new Color(40, 43, 22),
        new Color(100, 42, 32),
        new Color(122, 51, 39),
        new Color(142, 60, 46),
        new Color(75, 31, 24),
        new Color(26, 15, 11),
        new Color(31, 18, 13),
        new Color(37, 22, 16),
        new Color(19, 11, 8),
        new Color(133, 33, 34),
        new Color(163, 41, 42),
        new Color(189, 48, 49),
        new Color(100, 25, 25),
        new Color(104, 44, 68),
        new Color(127, 54, 83),
        new Color(148, 63, 97),
        new Color(78, 33, 51),
        new Color(64, 17, 20),
        new Color(79, 21, 25),
        new Color(92, 25, 29),
        new Color(48, 13, 15),
        new Color(15, 88, 94),
        new Color(18, 108, 115),
        new Color(22, 126, 134),
        new Color(11, 66, 70),
        new Color(40, 100, 98),
        new Color(50, 122, 120),
        new Color(58, 142, 140),
        new Color(30, 75, 74),
        new Color(60, 31, 43),
        new Color(74, 37, 53),
        new Color(86, 44, 62),
        new Color(45, 23, 32),
        new Color(14, 127, 93),
        new Color(17, 155, 114),
        new Color(20, 180, 133),
        new Color(10, 95, 70),
        new Color(70, 70, 70),
        new Color(86, 86, 86),
        new Color(100, 100, 100),
        new Color(52, 52, 52),
        new Color(152, 123, 103),
        new Color(186, 150, 126),
        new Color(216, 175, 147),
        new Color(114, 92, 77),
        new Color(89, 117, 105),
        new Color(109, 144, 129),
        new Color(127, 167, 150),
        new Color(67, 88, 79),
        // Paper end - Generated/MapPalette#colors
    };

    // Interface
    /**
     * @deprecated Magic value
     */
    @Deprecated
    public static final byte TRANSPARENT = 0;
    /**
     * @deprecated Magic value
     */
    @Deprecated
    public static final byte LIGHT_GREEN = 4;
    /**
     * @deprecated Magic value
     */
    @Deprecated
    public static final byte LIGHT_BROWN = 8;
    /**
     * @deprecated Magic value
     */
    @Deprecated
    public static final byte GRAY_1 = 12;
    /**
     * @deprecated Magic value
     */
    @Deprecated
    public static final byte RED = 16;
    /**
     * @deprecated Magic value
     */
    @Deprecated
    public static final byte PALE_BLUE = 20;
    /**
     * @deprecated Magic value
     */
    @Deprecated
    public static final byte GRAY_2 = 24;
    /**
     * @deprecated Magic value
     */
    @Deprecated
    public static final byte DARK_GREEN = 28;
    /**
     * @deprecated Magic value
     */
    @Deprecated
    public static final byte WHITE = 32;
    /**
     * @deprecated Magic value
     */
    @Deprecated
    public static final byte LIGHT_GRAY = 36;
    /**
     * @deprecated Magic value
     */
    @Deprecated
    public static final byte BROWN = 40;
    /**
     * @deprecated Magic value
     */
    @Deprecated
    public static final byte DARK_GRAY = 44;
    /**
     * @deprecated Magic value
     */
    @Deprecated
    public static final byte BLUE = 48;
    /**
     * @deprecated Magic value
     */
    @Deprecated
    public static final byte DARK_BROWN = 52;

    /**
     * Resize an image to 128x128.
     *
     * @param image The image to resize.
     * @return The resized image.
     */
    @NotNull
    public static BufferedImage resizeImage(@Nullable Image image) {
        BufferedImage result = new BufferedImage(128, 128, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = result.createGraphics();
        graphics.drawImage(image, 0, 0, 128, 128, null);
        graphics.dispose();
        return result;
    }

    /**
     * Convert an Image to a byte[] using the palette.
     *
     * @param image The image to convert.
     * @return A byte[] containing the pixels of the image.
     * @deprecated use color-related methods
     */
    @Deprecated(forRemoval = true, since = "1.20.2") // Paper
    @NotNull
    public static byte[] imageToBytes(@NotNull Image image) {
        BufferedImage temp = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = temp.createGraphics();
        graphics.drawImage(image, 0, 0, null);
        graphics.dispose();

        int[] pixels = new int[temp.getWidth() * temp.getHeight()];
        temp.getRGB(0, 0, temp.getWidth(), temp.getHeight(), pixels, 0, temp.getWidth());

        byte[] result = new byte[temp.getWidth() * temp.getHeight()];
        for (int i = 0; i < pixels.length; i++) {
            result[i] = matchColor(new Color(pixels[i], true));
        }
        return result;
    }

    /**
     * Get the index of the closest matching color in the palette to the given
     * color.
     *
     * @param r The red component of the color.
     * @param b The blue component of the color.
     * @param g The green component of the color.
     * @return The index in the palette.
     * @deprecated use color-related methods
     */
    @Deprecated(forRemoval = true, since = "1.20.2") // Paper
    public static byte matchColor(int r, int g, int b) {
        return matchColor(new Color(r, g, b));
    }

    /**
     * Get the index of the closest matching color in the palette to the given
     * color.
     *
     * @param color The Color to match.
     * @return The index in the palette.
     * @deprecated use color-related methods
     */
    @Deprecated(forRemoval = true, since = "1.20.2") // Paper
    public static byte matchColor(@NotNull Color color) {
        if (color.getAlpha() < 128) return 0;

        if (mapColorCache != null && mapColorCache.isCached()) {
            return mapColorCache.matchColor(color);
        }

        int index = 0;
        double best = -1;

        for (int i = 4; i < colors.length; i++) {
            double distance = getDistance(color, colors[i]);
            if (distance < best || best == -1) {
                best = distance;
                index = i;
            }
        }

        // Minecraft has 143 colors, some of which have negative byte representations
        return (byte) (index < 128 ? index : -129 + (index - 127));
    }

    /**
     * Get the value of the given color in the palette.
     *
     * @param index The index in the palette.
     * @return The Color of the palette entry.
     * @deprecated use color directly
     */
    @Deprecated(forRemoval = true, since = "1.20.2") // Paper
    @NotNull
    public static Color getColor(byte index) {
        // Minecraft has 143 colors, some of which have negative byte representations
        return colors[index >= 0 ? index : index + 256];
    }

    private static MapColorCache mapColorCache;

    /**
     * Sets the given MapColorCache.
     *
     * @param mapColorCache The map color cache to set
     */
    public static void setMapColorCache(@NotNull MapColorCache mapColorCache) {
        Preconditions.checkState(MapPalette.mapColorCache == null, "Map color cache already set");

        MapPalette.mapColorCache = mapColorCache;
    }

    /**
     * Holds cached information for matching map colors of a given RBG color.
     */
    public interface MapColorCache {

        /**
         * Returns true if the MapColorCache has values cached, if not it will
         * return false.
         * A case where it might return false is when the cache is not build jet.
         *
         * @return true if this MapColorCache has values cached otherwise false
         */
        boolean isCached();

        /**
         * Get the cached index of the closest matching color in the palette to the given
         * color.
         *
         * @param color The Color to match.
         * @return The index in the palette.
         * @throws IllegalStateException if {@link #isCached()} returns false
         * @apiNote Internal Use Only
         */
        @org.jetbrains.annotations.ApiStatus.Internal // Paper
        byte matchColor(@NotNull Color color);
    }
}
