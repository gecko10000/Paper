package io.papermc.generator.rewriter.parser.step;

import io.papermc.generator.rewriter.parser.LineParser;
import io.papermc.generator.rewriter.parser.StringReader;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;

public interface IterativeStep {

    void run(StringReader line, LineParser parser);

    static IterativeStep create(BiConsumer<StringReader, LineParser> runner) {
        return new BasicIterativeStep(runner);
    }

    static IterativeStep createUntil(BiPredicate<StringReader, LineParser> runner) {
        return new RepeatIterativeStep(runner);
    }

}
