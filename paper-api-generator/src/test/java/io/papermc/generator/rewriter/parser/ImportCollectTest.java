package io.papermc.generator.rewriter.parser;

import io.papermc.generator.rewriter.ClassNamed;
import io.papermc.generator.rewriter.context.ImportTypeCollector;
import io.papermc.generator.rewriter.data.sample.parser.imports.FancyInlinedImportType;
import io.papermc.generator.rewriter.data.yml.ImportMapping;
import io.papermc.generator.rewriter.data.yml.ImportSet;
import io.papermc.generator.rewriter.data.sample.parser.imports.FancyCommentImportType;
import io.papermc.generator.rewriter.data.sample.parser.imports.FancyNewlineImportType;
import io.papermc.generator.rewriter.data.sample.parser.imports.FancySpaceImportType;
import io.papermc.generator.rewriter.data.sample.parser.imports.MixedCommentImportType;
import io.papermc.generator.rewriter.data.sample.parser.imports.StandardImportType;
import io.papermc.generator.rewriter.data.yml.YmlMappingConverter;
import it.unimi.dsi.fastutil.Pair;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ImportCollectTest extends ParserTest {

    private static Arguments fileToArgs(Class<?> sampleClass) {
        return Arguments.of(
            CONTAINER.resolve(sampleClass.getCanonicalName().replace('.', '/') + ".java"),
            sampleClass,
            "/parser/expected/imports/%s.yml".formatted(sampleClass.getSimpleName())
        );
    }

    private static Stream<Arguments> fileProvider() {
        return Stream.of(
            StandardImportType.class,
            FancySpaceImportType.class,
            FancyCommentImportType.class,
            FancyNewlineImportType.class,
            FancyInlinedImportType.class,
            MixedCommentImportType.class
        ).map(ImportCollectTest::fileToArgs);
    }

    @ParameterizedTest
    @MethodSource("fileProvider")
    public void testImports(Path path,
                            Class<?> sampleClass,
                            @ConvertWith(ImportMappingConverter.class) ImportMapping expected) throws IOException {
        final ImportTypeCollector importCollector = new ImportTypeCollector(new ClassNamed(sampleClass));
        parseFile(path, importCollector);

        String name = sampleClass.getSimpleName();

        Pair<Set<String>, Set<String>> imports = importCollector.getImports();
        ImportSet expectedImports = expected.getImports();
        assertEquals(expectedImports.single(), imports.left(), "Regular imports doesn't match for " + name);
        assertEquals(expectedImports.global(), imports.right(), "Regular global imports doesn't match for " + name);

        Pair<Set<String>, Set<String>> staticImports = importCollector.getStaticImports();
        ImportSet expectedStaticImports = expected.getStaticImports();
        assertEquals(expectedStaticImports.single(), staticImports.left(), "Static imports doesn't match for " + name);
        assertEquals(expectedStaticImports.global(), staticImports.right(), "Static global imports doesn't match for " + name);
    }

    private static class ImportMappingConverter extends YmlMappingConverter<ImportMapping> {

        protected ImportMappingConverter() {
            super(ImportMapping.class, null);
        }
    }
}
