package io.papermc.generator.rewriter.parser;

import io.papermc.generator.rewriter.ClassNamed;
import io.papermc.generator.rewriter.context.ImportTypeCollector;
import io.papermc.generator.rewriter.data.sample.parser.imports.name.GlobalImportType;
import io.papermc.generator.rewriter.data.sample.parser.imports.name.PackageClassImportType;
import io.papermc.generator.rewriter.data.sample.parser.imports.name.RegularImportType;
import io.papermc.generator.rewriter.data.sample.parser.imports.name.RemoteGlobalInnerClassImportType;
import io.papermc.generator.rewriter.data.sample.parser.imports.name.RemoteInnerClassImportType;
import io.papermc.generator.rewriter.data.sample.parser.imports.name.RemoteStaticGlobalInnerClassImportType;
import io.papermc.generator.rewriter.data.sample.parser.imports.name.SamePackageClass;
import io.papermc.generator.rewriter.data.sample.parser.imports.name.SelfInnerClassImportType;
import io.papermc.generator.rewriter.data.yml.ImportShortNameMapping;
import io.papermc.generator.rewriter.data.yml.YmlMappingConverter;
import io.papermc.generator.utils.ClassHelper;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TypeShortNameTest extends ParserTest {

    private static Arguments rootClass(Class<?> sampleClass) {
        return innerClass(sampleClass, sampleClass);
    }

    private static Arguments innerClass(Class<?> sampleClass, Class<?> sampleInnerClass) {
        String name = sampleClass.getSimpleName();
        return Arguments.of(
            CONTAINER.resolve(sampleClass.getCanonicalName().replace('.', '/') + ".java"),
            sampleInnerClass,
            name,
            "/parser/expected/imports/name/%s.yml".formatted(sampleInnerClass.getName().substring(sampleInnerClass.getPackageName().length() + 1))
        );
    }

    private static Stream<Arguments> fileProvider() {
        return Stream.of(
            rootClass(RegularImportType.class),
            rootClass(GlobalImportType.class),
            rootClass(PackageClassImportType.class),
            rootClass(RemoteGlobalInnerClassImportType.class),
            rootClass(RemoteStaticGlobalInnerClassImportType.class),
            rootClass(RemoteInnerClassImportType.class),
            rootClass(SelfInnerClassImportType.class),
            innerClass(SelfInnerClassImportType.class, SelfInnerClassImportType.A.B.C.class)
        );
    }

    @ParameterizedTest
    @MethodSource("fileProvider")
    public void testTypeName(Path path,
                            Class<?> sampleClass,
                            String name,
                            @ConvertWith(ImportShortNameMappingConverter.class) ImportShortNameMapping mapping) throws IOException {
        final ImportTypeCollector importCollector = new ImportTypeCollector(new ClassNamed(sampleClass));
        parseFile(path, importCollector);

        assertFalse(mapping.getImports() == null && mapping.getStaticImports() == null, "Empty expected import mapping!");

        if (mapping.getImports() != null) {
            for (Map.Entry<String, String> expect : mapping.getImports().entrySet()) {
                Class<?> runtimeClass = ClassHelper.classOr(expect.getKey(), null);
                assertNotNull(runtimeClass, "Runtime class cannot be null for import " + expect.getKey());
                assertEquals(expect.getValue(), importCollector.getShortName(runtimeClass),
                    () -> "Short name of " + expect.getKey() + " doesn't match with collected imports for " + name + "! Import found: " + importCollector.getImports());
            }
        }

        if (mapping.getStaticImports() != null) {
            for (Map.Entry<String, String> expect : mapping.getStaticImports().entrySet()) {
                assertEquals(expect.getValue(), importCollector.getStaticMemberShortName(expect.getKey()),
                    () -> "Short name of static member/class " + expect.getKey() + " doesn't match with collected imports for " + name + "! Static imports found: " + importCollector.getStaticImports());
            }
        }
    }

    private static class ImportShortNameMappingConverter extends YmlMappingConverter<ImportShortNameMapping> {

        protected ImportShortNameMappingConverter() {
            super(ImportShortNameMapping.class, SamePackageClass.class.getPackageName());
        }
    }
}
