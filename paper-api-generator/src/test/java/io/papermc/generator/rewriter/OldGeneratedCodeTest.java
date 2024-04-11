package io.papermc.generator.rewriter;

import io.papermc.generator.Generators;
import io.papermc.generator.rewriter.parser.StringReader;
import io.papermc.generator.rewriter.replace.CommentMarker;
import io.papermc.generator.rewriter.replace.SearchReplaceRewriter;
import io.papermc.generator.rewriter.utils.Annotations;
import io.papermc.paper.generated.GeneratedFrom;
import net.minecraft.SharedConstants;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static io.papermc.generator.rewriter.replace.CommentMarker.EMPTY_MARKER;
import static io.papermc.generator.rewriter.replace.SearchReplaceRewriter.INDENT_CHAR;
import static io.papermc.generator.rewriter.replace.SearchReplaceRewriter.INDENT_SIZE;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Disabled
public class OldGeneratedCodeTest {

    private static final String API_CONTAINER = System.getProperty("paper.generator.rewriter.container.api");
    private static final String SERVER_CONTAINER = System.getProperty("paper.generator.rewriter.container.server");

    private static String CURRENT_VERSION;

    @BeforeAll
    public static void initializeVersion() {
        SharedConstants.tryDetectVersion();
        CURRENT_VERSION = SharedConstants.getCurrentVersion().getName();
    }

    private void checkOutdated(String container, SourceRewriter[] rewriters) throws IOException {
        for (SourceRewriter rewriter : rewriters) {
            if (!(rewriter instanceof SearchReplaceRewriter srt) || !srt.isVersionDependant()) {
                continue;
            }

            Path path = Path.of(container, srt.getRelativeFilePath());
            if (Files.notExists(path)) { // todo remove after the repo change
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
                    if (line.isEmpty()) {
                        continue;
                    }

                    StringReader lineIterator = new StringReader(line);
                    CommentMarker marker = srt.searchMarker(lineIterator, null, null);
                    if (marker != EMPTY_MARKER) {
                        if (!marker.start()) {
                            continue;
                        }
                        int startIndentSize = marker.indent();
                        if (startIndentSize % INDENT_SIZE != 0) {
                            continue;
                        }

                        String nextLine = reader.readLine();
                        if (nextLine == null) {
                            break;
                        }
                        lineCount++;
                        if (nextLine.isEmpty()) {
                            continue;
                        }

                        StringReader nextLineIterator = new StringReader(nextLine);
                        int indentSize = nextLineIterator.skipChars(INDENT_CHAR);
                        if (indentSize != startIndentSize) {
                            continue;
                        }

                        String generatedComment = "// %s ".formatted(Annotations.annotationStyle(GeneratedFrom.class));
                        if (nextLineIterator.trySkipString(generatedComment) && nextLineIterator.canRead()) {
                            String generatedVersion = nextLineIterator.readRemainingString();
                            assertEquals(CURRENT_VERSION, generatedVersion,
                                "Code at line %s in %s is marked as being generated in version %s when the current version is %s".formatted(
                                    lineCount, srt.getRewrittenClass().canonicalName(),
                                    generatedVersion, CURRENT_VERSION));
                        }
                    }
                }
            }
        }
    }

    @Test
    public void testOutdatedCode() throws IOException {
        checkOutdated(API_CONTAINER, Generators.API_REWRITE);
        checkOutdated(SERVER_CONTAINER, Generators.SERVER_REWRITE);
    }
}
