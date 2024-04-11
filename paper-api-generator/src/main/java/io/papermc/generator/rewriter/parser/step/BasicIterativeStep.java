package io.papermc.generator.rewriter.parser.step;

import io.papermc.generator.rewriter.parser.LineParser;
import io.papermc.generator.rewriter.parser.StringReader;
import java.util.function.BiConsumer;

public class BasicIterativeStep implements IterativeStep {

    private final BiConsumer<StringReader, LineParser> runner;

    protected BasicIterativeStep(BiConsumer<StringReader, LineParser> runner) {
        this.runner = runner;
    }

    @Override
    public void run(StringReader line, LineParser parser) {
        this.runner.accept(line, parser);
    }
}
