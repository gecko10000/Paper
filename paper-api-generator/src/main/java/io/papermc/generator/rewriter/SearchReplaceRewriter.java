package io.papermc.generator.rewriter;

import io.papermc.generator.Main;
import io.papermc.generator.rewriter.utils.Annotations;
import io.papermc.generator.rewriter.utils.ImportCollector;
import io.papermc.generator.utils.Formatting;
import io.papermc.paper.generated.GeneratedFrom;
import net.minecraft.SharedConstants;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class SearchReplaceRewriter implements SourceRewriter {

    private static final String INDENT_UNIT = "    ";
    private static final String PAPER_START_FORMAT = "Paper start";
    private static final String PAPER_END_FORMAT = "Paper end";

    protected final Class<?> rewriteClass;
    private final String pattern;
    private final boolean equalsSize;
    private final ImportCollector importCollector;

    public SearchReplaceRewriter(Class<?> rewriteClass, String pattern, boolean equalsSize) {
        this.rewriteClass = rewriteClass;
        this.pattern = pattern;
        this.equalsSize = equalsSize;
        this.importCollector = new ImportCollector(rewriteClass);
    }

    // only when equalsSize = false
    protected void insert(SearchMetadata metadata, StringBuilder builder) {}

    // only when equalsSize = true
    protected void replaceLine(SearchMetadata metadata, StringBuilder builder) {}

    protected void checkFileState() {}

    private boolean framed;

    @Override
    public void writeToFile(Path parent) throws IOException {
        this.checkFileState();

        String indent = Formatting.incrementalIndent(INDENT_UNIT, this.rewriteClass);
        String startPattern = String.format("// %s - Generated/%s", PAPER_START_FORMAT, this.pattern);
        String endPattern = String.format("// %s - Generated/%s", PAPER_END_FORMAT, this.pattern);

        String filePath = "%s/%s.java".formatted(
            this.rewriteClass.getPackageName().replace('.', '/'),
            Formatting.retrieveFileName(this.rewriteClass)
        );

        Path path = parent.resolve(filePath);
        StringBuilder content = new StringBuilder();
        StringBuilder strippedContent = new StringBuilder();

        // todo support multiple passes
        // strip the replaced content first or apply directly the change when the replaced content size is equals to the new content size
        {
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            boolean replace = false;

            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);

                // collect import to avoid fqn when not needed
                if (line.startsWith("import ") && line.endsWith(";")) {
                    this.importCollector.consume(line);
                }

                if (line.equals(indent + endPattern)) {
                    if (this.framed) {
                        this.framed = false;
                        if (this.equalsSize) {
                            replace = false;
                        }
                    } else {
                        throw new IllegalStateException("Start generated comment missing for " + this.rewriteClass.getSimpleName() + " at line " + (i + 1));
                    }
                }

                if (!this.framed) {
                    content.append(line);
                    content.append('\n');
                } else {
                    strippedContent.append(line);
                    strippedContent.append('\n');
                    if (replace) {
                        // todo generated version comment
                        this.replaceLine(new SearchMetadata(this.importCollector, indent, line, i), content);
                    }
                }

                int startPatternIndex = line.indexOf(startPattern);
                if (startPatternIndex != -1) {
                    if (!this.framed) {
                        this.framed = true;
                        indent = " ".repeat(startPatternIndex); // update indent based on the comments for flexibility
                        if (indent.length() % INDENT_UNIT.length() != 0) {
                            throw new IllegalStateException("Start generated comment is not properly indented at line " + (i + 1));
                        }
                        if (this.equalsSize) {
                            replace = true;
                        }
                    } else {
                        throw new IllegalStateException("Nested generated comments are not allowed for " + this.rewriteClass.getSimpleName() + " at line " + (i + 1));
                    }
                }
            }
        }

        if (this.framed) {
            throw new IllegalStateException("End generated comment missing for " + this.rewriteClass.getSimpleName());
        }

        // often content doesn't match because of javadoc or by design for some rewriter so insert manually later
        if (!this.equalsSize) {
            StringBuilder replacedContent = new StringBuilder();
            String[] stripLines = content.toString().split("\n");
            boolean replace = false;
            for (int i = 0; i < stripLines.length; i++) {
                String line = stripLines[i];
                if (replace) {
                    replacedContent.append(indent).append("// %s %s".formatted(
                        Annotations.annotationStyle(GeneratedFrom.class),
                        SharedConstants.getCurrentVersion().getName()
                    ));
                    replacedContent.append('\n');

                    this.insert(new SearchMetadata(this.importCollector, indent, strippedContent.toString(), i), replacedContent);
                    replace = false;
                }
                replacedContent.append(line);
                replacedContent.append('\n');
                if (line.equals(indent + startPattern)) {
                    replace = true;
                }
            }
            content = replacedContent;
        }

        // Files.writeString(path, content.toString(), StandardCharsets.UTF_8); // todo
        Path createdPath = Main.generatedPath.resolve(filePath);
        Files.createDirectories(createdPath.getParent());
        Files.writeString(createdPath, content.toString(), StandardCharsets.UTF_8);
    }
}
