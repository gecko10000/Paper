package io.papermc.generator.rewriter.parser;

import io.papermc.generator.rewriter.ClassNamed;
import io.papermc.generator.rewriter.context.ImportTypeCollector;
import io.papermc.generator.rewriter.data.sample.parser.area.AnnotationClass;
import io.papermc.generator.rewriter.data.sample.parser.area.AnnotationPresentClass;
import io.papermc.generator.rewriter.data.sample.parser.area.FancyNewlineAnnotationPresentClass;
import io.papermc.generator.rewriter.data.sample.parser.area.FancyScopeClass;
import io.papermc.generator.rewriter.data.sample.parser.area.FancyScopeClass2;
import io.papermc.generator.rewriter.data.sample.parser.area.MixedAnnotationPresentClass;
import io.papermc.generator.rewriter.data.sample.parser.area.NearScopeClass;
import io.papermc.generator.rewriter.data.sample.parser.area.NewlineScopedClass;
import io.papermc.generator.rewriter.data.sample.parser.area.SimpleTrapClass;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserMetadataAreaTest extends ParserTest {

    private static final Path CONTAINER = Path.of(System.getProperty("user.dir"), "src/test/java");

    private static Arguments file(Class<?> sampleClass, String expectedLastLine) {
        String name = sampleClass.getSimpleName();
        return Arguments.of(
            CONTAINER.resolve(sampleClass.getCanonicalName().replace('.', '/') + ".java"),
            sampleClass,
            name,
            expectedLastLine
        );
    }

    private static Stream<Arguments> fileProvider() {
        return Stream.of(
            file(
                SimpleTrapClass.class,
                "public class SimpleTrapClass {"
            ),
            file(
                AnnotationClass.class,
                "public @interface AnnotationClass {"
            ),
            file(
                AnnotationPresentClass.class,
                "public class AnnotationPresentClass {"
            ),
            file(
                FancyNewlineAnnotationPresentClass.class,
                "public class FancyNewlineAnnotationPresentClass {"
            ),
            file(
                MixedAnnotationPresentClass.class,
                "public class MixedAnnotationPresentClass {"
            ),
            file(
                NewlineScopedClass.class,
                "{"
            ),
            file(
                NearScopeClass.class,
                "public class NearScopeClass{"
            ),
            file(
                FancyScopeClass.class,
                "    /* d */{//d"
            ),
            file(
                FancyScopeClass2.class,
                "public/* d */class/* d */FancyScopeClass2/* d */ /* d */{//d"
            )
        );
    }

    @ParameterizedTest
    @MethodSource("fileProvider")
    public void testAreaEnd(Path path,
                            Class<?> sampleClass,
                            String name,
                            String expectedLastLine) throws IOException {
        final ImportTypeCollector importCollector = new ImportTypeCollector(new ClassNamed(sampleClass));
        parseFile(path, importCollector, line -> {
                assertEquals(expectedLastLine, line, "Parser didn't stop at the expected line for " + name);
            },
            () -> {
                fail("File is empty or doesn't contains the required top scope needed for this test to run");
            }
        );
    }
}
