package io.papermc.generator.rewriter.parser.step;

import io.papermc.generator.rewriter.parser.LineParser;
import io.papermc.generator.rewriter.parser.StringReader;
import java.util.function.BiPredicate;

public class RepeatIterativeStep implements IterativeStep {

    private final BiPredicate<StringReader, LineParser> runner;

    protected RepeatIterativeStep(BiPredicate<StringReader, LineParser> runner) {
        this.runner = runner;
    }

    @Override
    public void run(StringReader line, LineParser parser) {
        if (this.runner.test(line, parser)) {
            parser.addPriorityStep(this);
        }
    }
}
