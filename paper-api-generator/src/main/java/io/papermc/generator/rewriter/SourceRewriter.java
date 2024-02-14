package io.papermc.generator.rewriter;

import java.io.IOException;
import java.nio.file.Path;

public interface SourceRewriter {

    void writeToFile(Path parent) throws IOException;

}
