package io.papermc.generator.rewriter;

import io.papermc.generator.Main;
import io.papermc.generator.rewriter.utils.Annotations;
import io.papermc.paper.generated.GeneratedFrom;
import net.minecraft.SharedConstants;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class SearchReplaceRewriter implements SourceRewriter {

    private static final String INDENT = "    ";
    private static final String PAPER_START_FORMAT = "Paper start";
    private static final String PAPER_END_FORMAT = "Paper end";

    private final Class<?> rewriteClass;
    private final String pattern;
    private final boolean equalsSize;

    public SearchReplaceRewriter(Class<?> rewriteClass, String pattern, boolean equalsSize) {
        this.rewriteClass = rewriteClass;
        this.pattern = pattern;
        this.equalsSize = equalsSize;
    }

    // only when equalsSize = false
    public void insert(SearchMetadata metadata, StringBuilder builder) {}

    // only when equalsSize = true
    public void replaceLine(SearchMetadata metadata, StringBuilder builder) {}

    private String getIndent(String unit, Class<?> clazz) { // todo move in formatting
        Class<?> parent = clazz.getEnclosingClass();
        StringBuilder indentBuilder = new StringBuilder(unit);
        while (parent != null) {
            indentBuilder.append(unit);
            parent = parent.getEnclosingClass();
        }
        return indentBuilder.toString();
    }

    private boolean framed;

    @Override
    public void writeToFile(Path parent) throws IOException {
        String indent = getIndent(INDENT, this.rewriteClass);
        String startPattern = String.format("%s// %s - Generated/%s", indent, PAPER_START_FORMAT, this.pattern);
        String endPattern = String.format("%s// %s - Generated/%s", indent, PAPER_END_FORMAT, this.pattern);

        Path path = parent.resolve(this.rewriteClass.getCanonicalName().replace('.', '/') + ".java");
        StringBuilder content = new StringBuilder();
        StringBuilder strippedContent = new StringBuilder();

        // strip the replaced content first or apply directly the change when the replaced content size is equals to the new content size
        {
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            boolean replace = false;

            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);

                if (line.equals(endPattern)) {
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
                        this.replaceLine(new SearchMetadata(indent, line, i), content);
                    }
                }

                if (line.equals(startPattern)) {
                    if (!this.framed) {
                        this.framed = true;
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
                        Annotations.annotation(GeneratedFrom.class, true),
                        SharedConstants.getCurrentVersion().getName()
                    ));
                    replacedContent.append('\n');

                    this.insert(new SearchMetadata(indent, strippedContent.toString(), i), replacedContent);
                    replace = false;
                }
                replacedContent.append(line);
                replacedContent.append('\n');
                if (line.equals(startPattern)) {
                    replace = true;
                }
            }
            content = replacedContent;
        }

        // Files.writeString(path, content.toString(), StandardCharsets.UTF_8); // todo
        Path createdPath = Main.generatedPath.resolve(this.rewriteClass.getCanonicalName().replace('.', '/') + ".java");
        Files.createDirectories(createdPath.getParent());
        Files.writeString(createdPath, content.toString(), StandardCharsets.UTF_8);
    }
}
