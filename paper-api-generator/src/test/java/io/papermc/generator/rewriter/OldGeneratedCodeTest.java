package io.papermc.generator.rewriter;

import io.papermc.generator.Generators;
import io.papermc.generator.rewriter.utils.Annotations;
import io.papermc.generator.utils.ClassHelper;
import io.papermc.paper.generated.GeneratedFrom;
import net.minecraft.SharedConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class OldGeneratedCodeTest {

    private static final String CONTAINER = System.getProperty("paper.generator.rewriter.container");

    private static String CURRENT_VERSION;

    @BeforeAll
    public static void initializeVersion() {
        SharedConstants.tryDetectVersion();
        CURRENT_VERSION = SharedConstants.getCurrentVersion().getName();
    }

    @Test
    public void testOutdatedCode() throws IOException {
        for (SourceRewriter rewriter : Generators.API_REWRITE) {
            if (!(rewriter instanceof SearchReplaceRewriter srt)) {
                continue;
            }

            String filePath = "%s/%s.java".formatted(
                srt.rewriteClass.getPackageName().replace('.', '/'),
                ClassHelper.getRootClass(srt.rewriteClass).getSimpleName()
            );

            Path path = Path.of(CONTAINER, filePath);
            if (false && !Files.exists(path)) {
                continue;
            }
            try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
                int lineCount = 0;
                while (true) {
                    String line = reader.readLine();
                    if (line == null) {
                        break;
                    }
                    lineCount++;

                    int startPatternIndex = line.indexOf(SearchReplaceRewriter.GENERATED_COMMENT_FORMAT.formatted(SearchReplaceRewriter.PAPER_START_FORMAT, ""));
                    if (startPatternIndex != -1 && (startPatternIndex % SearchReplaceRewriter.INDENT_UNIT.length()) == 0 && line.stripTrailing().equals(line)) {
                        String nextLine = reader.readLine();
                        if (nextLine == null) {
                            break;
                        }
                        lineCount++;
                        String generatedComment = "// %s ".formatted(Annotations.annotationStyle(GeneratedFrom.class));
                        int generatedIndex = nextLine.indexOf(generatedComment);
                        if (generatedIndex != -1 && (generatedIndex % SearchReplaceRewriter.INDENT_UNIT.length()) == 0 && nextLine.stripTrailing().equals(nextLine)) {
                            String generatedVersion = nextLine.substring(generatedIndex + generatedComment.length());
                            Assertions.assertEquals(CURRENT_VERSION, generatedVersion,
                                "Code at line %s in %s is marked as being generated in version %s when the current version is %s".formatted(
                                    lineCount, srt.rewriteClass.getCanonicalName(),
                                    generatedVersion, CURRENT_VERSION));
                        }
                    }
                }
            }
        }
    }
}
