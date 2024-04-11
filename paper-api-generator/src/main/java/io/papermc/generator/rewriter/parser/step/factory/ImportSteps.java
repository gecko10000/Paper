package io.papermc.generator.rewriter.parser.step.factory;

import io.papermc.generator.rewriter.context.ImportCollector;
import io.papermc.generator.rewriter.parser.LineParser;
import io.papermc.generator.rewriter.parser.ProtoTypeName;
import io.papermc.generator.rewriter.parser.step.IterativeStep;
import io.papermc.generator.rewriter.parser.step.StepHolder;
import io.papermc.generator.rewriter.parser.StringReader;
import io.papermc.generator.utils.NamingManager;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;

// start once "import" is detected unless commented
// order is: enforceSpace -> checkStatic (-> enforceSpace) -> getPartName -> collectImport
public final class ImportSteps implements StepHolder {

    public static boolean canStart(StringReader line) {
        return line.trySkipString("import");
    }

    private final IterativeStep enforceSpaceStep = IterativeStep.create(this::enforceSpace);

    private final ImportCollector collector;
    private boolean isStatic;
    private @MonotonicNonNull ProtoTypeName name;

    public ImportSteps(ImportCollector collector) {
        this.collector = collector;
    }

    public void enforceSpace(StringReader line, LineParser parser) {
        if (line.canRead() && parser.nextSingleLineComment(line)) {
            // ignore single line comment at the end of import/static
            line.setCursor(line.getTotalLength());
            return;
        }

        if (!parser.skipComment(line)) {
            if (line.skipWhitespace() == 0) { // expect at least one space between import, static and type name unless a multi comment is here to fill the gap
                parser.clearRemainingSteps();
            }
        }
    }

    public boolean checkStatic(StringReader line, LineParser parser) {
        parser.skipCommentOrWhitespace(line);
        if (!line.canRead()) {
            return true;
        }

        if (line.trySkipString("static")) {
            parser.addPriorityStep(this.enforceSpaceStep);
            this.isStatic = true;
        }
        return false;
    }

    public void collectImport(StringReader line, LineParser parser) {
        String name = this.name.getName();
        if (name.isEmpty() || NamingManager.hasIllegalKeyword(name)) { // keyword are checked after to simplify things
            return;
        }

        if (this.isStatic) {
            this.collector.addStaticImport(name);
        } else {
            this.collector.addImport(name);
        }
    }

    public boolean getPartName(StringReader line, LineParser parser) {
        parser.skipCommentOrWhitespace(line);
        if (!line.canRead()) {
            return true;
        }

        String name = line.getPartNameUntil(';', parser::skipCommentOrWhitespaceInName, true,
            () -> this.name == null || this.name.shouldCheckStartIdentifier());

        if (line.canRead() && parser.nextSingleLineComment(line)) {
            // ignore single line comment at the end of the name
            line.setCursor(line.getTotalLength());
        }

        if (this.name == null) {
            this.name = ProtoTypeName.create(name);
        } else {
            this.name.append(name);
        }
        return !line.canRead();
    }

    @Override
    public IterativeStep[] initialSteps() {
        return new IterativeStep[] {
            this.enforceSpaceStep,
            IterativeStep.createUntil(this::checkStatic),
            IterativeStep.createUntil(this::getPartName),
            IterativeStep.create(this::collectImport)
        };
    }
}
