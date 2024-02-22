package io.papermc.generator.rewriter;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import io.papermc.generator.Main;
import io.papermc.generator.rewriter.utils.Annotations;
import io.papermc.generator.rewriter.utils.ClassHelper;
import io.papermc.generator.rewriter.utils.ImportCollector;
import io.papermc.generator.utils.Formatting;
import io.papermc.paper.generated.GeneratedFrom;
import net.minecraft.SharedConstants;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.jetbrains.annotations.ApiStatus;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class SearchReplaceRewriter implements SourceRewriter {

    protected static final String INDENT_UNIT = "    ";
    private static final String PAPER_START_FORMAT = "Paper start";
    private static final String PAPER_END_FORMAT = "Paper end";
    private static final String GENERATED_COMMENT_FORMAT = "// %s - Generated/%s"; // {0} = PAPER_START_FORMAT|PAPER_END_FORMAT {1} = pattern

    protected final Class<?> rewriteClass;
    protected final String pattern;
    private final boolean equalsSize;

    public SearchReplaceRewriter(Class<?> rewriteClass, String pattern, boolean equalsSize) {
        this.rewriteClass = rewriteClass;
        this.pattern = pattern;
        this.equalsSize = equalsSize;
    }

    // only when equalsSize = false
    @ApiStatus.OverrideOnly
    protected void insert(SearchMetadata metadata, StringBuilder builder) {}

    // only when equalsSize = true
    @ApiStatus.OverrideOnly
    protected void replaceLine(SearchMetadata metadata, StringBuilder builder) {}

    protected void beginSearch() {}

    private SearchReplaceRewriter foundRewriter;

    private void searchAndReplace(BufferedReader reader, StringBuilder content) throws IOException {
        searchAndReplace(reader, content, this.pattern == null ? new HashMap<>() : Map.of(this.pattern, this));
    }

    protected void searchAndReplace(BufferedReader reader, StringBuilder content, Map<String, SearchReplaceRewriter> patternInfo) throws IOException {
        Preconditions.checkState(!patternInfo.isEmpty());
        this.beginSearch();

        Set<String> patterns = patternInfo.keySet();
        Set<String> foundPatterns = new HashSet<>();
        StringBuilder strippedContent = null;

        Class<?> rootClass = ClassHelper.getRootClass(this.rewriteClass);
        ImportCollector importCollector = new ImportCollector(rootClass);

        String indent = Formatting.incrementalIndent(INDENT_UNIT, rootClass);
        String rootClassDeclaration = "%s %s".formatted(ClassHelper.getDeclaredType(rootClass), rootClass.getSimpleName());
        boolean inBody = false;
        int i = 0;
        while (true) {
            String line = reader.readLine();
            if (line == null) {
                break;
            }

            // collect import to avoid fqn when not needed
            if (!inBody) {
                if (line.startsWith("import ") && line.endsWith(";")) {
                    importCollector.consume(line);
                }
                if (line.contains(rootClassDeclaration)) { // might fail on comments but good enough
                    inBody = true;
                }
            }

            Optional<String> endPattern = this.searchPattern(line, indent, PAPER_END_FORMAT, patterns);
            if (endPattern.isPresent()) {
                if (this.foundRewriter == null) {
                    throw new IllegalStateException("Start generated comment missing for pattern " + endPattern.get() + " in class " + this.rewriteClass.getSimpleName() + " at line " + (i + 1));
                }
                if (this.foundRewriter.pattern.equals(endPattern.get())) {
                    if (!this.foundRewriter.equalsSize) {
                        appendGeneratedComment(content, indent);

                        this.foundRewriter.insert(new SearchMetadata(importCollector, indent, strippedContent.toString(), i), content);
                        strippedContent = null;
                    }
                    this.foundRewriter = null;
                } else {
                    throw new IllegalStateException("End generated comment doesn't match for pattern " + this.foundRewriter.pattern +  " in " + this.rewriteClass.getSimpleName() + " at line " + (i + 1));
                }
            }

            if (this.foundRewriter == null) {
                content.append(line);
                content.append('\n');
            } else {
                if (this.foundRewriter.equalsSize) {
                    // there's no generated comment here since when the size is equals the replaced content doesn't depend on the game content
                    // if it does that means the replaced content might not be equals during MC update because of adding/removed content
                    this.foundRewriter.replaceLine(new SearchMetadata(importCollector, indent, line, i), content);
                } else {
                    strippedContent.append(line);
                    strippedContent.append('\n');
                }
            }

            Optional<String> startPattern = this.searchPattern(line, null, PAPER_START_FORMAT, patterns);
            if (startPattern.isPresent()) {
                if (this.foundRewriter != null) {
                    throw new IllegalStateException("Nested generated comments are not allowed for " + this.rewriteClass.getSimpleName() + " at line " + (i + 1));
                }
                int startPatternIndex = line.indexOf(GENERATED_COMMENT_FORMAT.formatted(PAPER_START_FORMAT, startPattern.get()));
                indent = " ".repeat(startPatternIndex); // update indent based on the comments for flexibility
                if (indent.length() % INDENT_UNIT.length() != 0) {
                    throw new IllegalStateException("Start generated comment is not properly indented at line " + (i + 1));
                }

                this.foundRewriter = patternInfo.get(startPattern.get());
                foundPatterns.add(this.foundRewriter.pattern);
                if (!this.foundRewriter.equalsSize) {
                    strippedContent = new StringBuilder();
                }
            }
            i++;
        }

        if (this.foundRewriter != null) {
            throw new IllegalStateException("End generated comment " + this.foundRewriter.pattern + " missing for " + this.rewriteClass.getSimpleName());
        }

        Set<String> diff = Sets.difference(patterns, foundPatterns);
        if (!diff.isEmpty()) {
            throw new IllegalStateException("SRT didn't found some expected generated comments: " + diff.toString());
        }
    }

    @Override
    public void writeToFile(Path parent) throws IOException {
        String filePath = "%s/%s.java".formatted(
            this.rewriteClass.getPackageName().replace('.', '/'),
            ClassHelper.getRootClass(this.rewriteClass).getSimpleName()
        );

        Path path = parent.resolve(filePath);
        StringBuilder content = new StringBuilder();
        try (BufferedReader buffer = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            this.searchAndReplace(buffer, content);
        }

        // Files.writeString(path, content.toString(), StandardCharsets.UTF_8); // todo
        Path createdPath = Main.generatedPath.resolve(filePath);
        Files.createDirectories(createdPath.getParent());
        Files.writeString(createdPath, content.toString(), StandardCharsets.UTF_8);
    }

    private void appendGeneratedComment(StringBuilder builder, String indent) {
        builder.append(indent).append("// %s %s".formatted(
            Annotations.annotationStyle(GeneratedFrom.class),
            SharedConstants.getCurrentVersion().getName()
        ));
        builder.append('\n');
    }

    private Optional<String> searchPattern(String rawLine, @Nullable String indent, String prefix, Set<String> patterns) {
        boolean strict = indent != null;
        String line = strict ? rawLine : rawLine.stripLeading();

        for (String pattern : patterns) {
            String comment = GENERATED_COMMENT_FORMAT.formatted(prefix, pattern);
            if (strict ? line.equals(indent + comment) : line.equals(comment)) {
                return Optional.of(pattern);
            }
        }
        return Optional.empty();
    }
}
