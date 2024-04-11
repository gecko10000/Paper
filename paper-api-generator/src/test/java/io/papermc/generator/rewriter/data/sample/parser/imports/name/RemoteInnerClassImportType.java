package io.papermc.generator.rewriter.data.sample.parser.imports.name;

import io.papermc.generator.rewriter.data.sample.parser.imports.name.one.OneDepthClass.NonStaticClass;
import static io.papermc.generator.rewriter.data.sample.parser.imports.name.one.OneDepthClass.StaticClass;

public class RemoteInnerClassImportType {
    {
        var b = NonStaticClass.class;
        var c = StaticClass.class;
    }
}
