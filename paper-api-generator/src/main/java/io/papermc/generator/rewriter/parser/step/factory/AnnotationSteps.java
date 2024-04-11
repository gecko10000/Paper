package io.papermc.generator.rewriter.parser.step.factory;

import io.papermc.generator.rewriter.parser.ClosureType;
import io.papermc.generator.rewriter.parser.LineParser;
import io.papermc.generator.rewriter.parser.ProtoTypeName;
import io.papermc.generator.rewriter.parser.step.IterativeStep;
import io.papermc.generator.rewriter.parser.step.StepHolder;
import io.papermc.generator.rewriter.parser.StringReader;
import io.papermc.generator.utils.NamingManager;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;

// start once "@" is detected unless commented
// order is: skipAtSign -> skipPartName -> checkOpenParenthesis (-> skipParentheses)
public final class AnnotationSteps implements StepHolder {

    public static boolean canStart(char currentChar) {
        return currentChar == '@';
    }

    private final IterativeStep skipParenthesesStep = IterativeStep.createUntil(this::skipParentheses);

    private @MonotonicNonNull ProtoTypeName name;

    public void skipAtSign(StringReader line, LineParser parser) {
        line.skip(); // skip @
    }

    public boolean skipPartName(StringReader line, LineParser parser) {
        boolean checkStartId = this.name == null || this.name.shouldCheckStartIdentifier();

        if (!checkStartId) { // this part is not in the import steps since import always need a semicolon at their end so it's easier to parse them
            if (!parser.trySkipCommentOrWhitespaceUntil(line, '.')) { // expect a dot for multi line annotation when the previous line doesn't end by a dot itself
                return false;
            }
        } else {
            parser.skipCommentOrWhitespace(line);
        }
        if (!line.canRead()) {
            return true;
        }

        String name = line.getPartNameUntil('(', parser::skipCommentOrWhitespaceInName, false,
            () -> checkStartId);

        if (line.canRead() && parser.nextSingleLineComment(line)) {
            // ignore single line comment at the end and allow the name to continue
            line.setCursor(line.getTotalLength());
        }

        if (this.name == null) {
            this.name = ProtoTypeName.create(name);
        } else {
            this.name.append(name);
        }
        return !line.canRead();
    }

    public boolean skipParentheses(StringReader line, LineParser parser) {
        while (!parser.advanceEnclosure(ClosureType.PARENTHESIS, line)) { // closed parenthesis?
            if (!line.canRead()) { // parenthesis on another line?
                return true;
            }
            line.skip();
        }
        return false;
    }

    // filter out @interface
    public void checkAnnotationName(StringReader line, LineParser parser) {
        String name = this.name.getName();
        if (name.isEmpty() || NamingManager.hasIllegalKeyword(name)) { // keyword are checked after to simplify things
            parser.clearRemainingSteps();
        }
    }

    public boolean checkOpenParenthesis(StringReader line, LineParser parser) {
        parser.skipCommentOrWhitespace(line); // since skipPartName fail fast this is needed for space between the typeName and the parenthesis
        if (!line.canRead()) {
            return true;
        }

        if (parser.advanceEnclosure(ClosureType.PARENTHESIS, line)) { // open parenthesis?
            parser.addPriorityStep(this.skipParenthesesStep);
        }
        return false;
    }

    @Override
    public IterativeStep[] initialSteps() {
        return new IterativeStep[] {
            IterativeStep.create(this::skipAtSign),
            IterativeStep.createUntil(this::skipPartName),
            IterativeStep.create(this::checkAnnotationName),
            IterativeStep.createUntil(this::checkOpenParenthesis),
        };
    }
}
