package io.papermc.generator.rewriter.parser;

import com.mojang.brigadier.ImmutableStringReader;
import org.jetbrains.annotations.Nullable;
import java.util.function.BiPredicate;
import java.util.function.BooleanSupplier;

// based on brigadier string reader with some extra/removed features for rewriter
public class StringReader implements ImmutableStringReader {

    private final String string;
    private int cursor;

    public StringReader(final StringReader other) {
        this.string = other.string;
        this.cursor = other.cursor;
    }

    public StringReader(final String string) {
        this.string = string;
    }

    @Override
    public String getString() {
        return string;
    }

    public void setCursor(final int cursor) {
        this.cursor = cursor;
    }

    @Override
    public int getRemainingLength() {
        return string.length() - cursor;
    }

    @Override
    public int getTotalLength() {
        return string.length();
    }

    @Override
    public int getCursor() {
        return cursor;
    }

    @Override
    public String getRead() {
        return string.substring(0, cursor);
    }

    @Override
    public String getRemaining() {
        return string.substring(cursor);
    }

    @Override
    public boolean canRead(final int length) {
        return cursor + length <= string.length();
    }

    @Override
    public boolean canRead() {
        return canRead(1);
    }

    @Override
    public char peek() {
        return string.charAt(cursor);
    }

    @Override
    public char peek(final int offset) {
        return string.charAt(cursor + offset);
    }

    public char read() {
        return string.charAt(cursor++);
    }

    public void skip() {
        cursor++;
    }

    // new features

    public int peekPoint() {
        return this.string.codePointAt(this.cursor);
    }

    public int skipWhitespace() {
        int i = 0;
        while (canRead() && Character.isWhitespace(peek())) {
            this.skip();
            i++;
        }
        return i;
    }

    public int skipChars(final char value) {
        int i = 0;
        while (this.canRead() && this.peek() == value) {
            this.skip();
            i++;
        }
        return i;
    }

    public void skipString(final char terminator) {
        while (this.canRead() && this.peek() != terminator) {
            this.skip();
        }
    }

    public boolean trySkipWhitespace(final int size) {
        int delta = 0;
        int previousCursor = this.cursor;
        while (delta < size && this.canRead() && Character.isWhitespace(this.peek())) {
            this.skip();
            delta++;
        }
        if (delta == size) {
            return true;
        }

        this.setCursor(previousCursor);
        return false;
    }

    public boolean trySkipChars(final int size, final char value) {
        int delta = 0;
        int previousCursor = this.cursor;
        while (delta < size && this.canRead() && this.peek() == value) {
            this.skip();
            delta++;
        }
        if (delta == size) {
            return true;
        }

        this.setCursor(previousCursor);
        return false;
    }

    public boolean trySkipString(final String value) {
        char[] chars = value.toCharArray();
        int delta = 0;
        int previousCursor = this.cursor;
        while (this.canRead() && delta < chars.length && chars[delta] == this.peek()) {
            this.skip();
            delta++;
        }
        if (delta == chars.length) {
            return true;
        }

        this.setCursor(previousCursor);
        return false;
    }

    public String readRemainingString() {
        final int start = this.cursor;
        this.cursor = this.getTotalLength();
        return this.string.substring(start);
    }

    public String readStringUntil(final char terminator) {
        final int start = this.cursor;
        this.skipString(terminator);
        return this.string.substring(start, this.cursor);
    }

    @Nullable
    public String getStringUntil(final char terminator) {
        final int start = this.cursor;
        this.skipString(terminator);
        if (!this.canRead()) {
            return null;
        }
        return this.string.substring(start, this.cursor);
    }

    // cleaner is used to skip stuff like : net/* hi */./**/kyori.adventure.translation/**/.Translatable within the type name
    public String getPartNameUntil(final char terminator, final BiPredicate<StringReader, NameCursorState> cleaner, final boolean forImport, final BooleanSupplier checkStartGetter) { // this break the concept of this a class a bit but it's not worth making a code point equivalent for only this method
        boolean hasCleaner = cleaner != null;
        boolean checkStart = checkStartGetter.getAsBoolean();
        StringBuilder name = new StringBuilder();
        while (this.canRead()) {
            int c = this.peekPoint();
            if (c == terminator) {
                break;
            }

            if (checkStart) { // had a dot before
                if (hasCleaner && cleaner.test(this, NameCursorState.AFTER_DOT)) {
                    continue;
                }
            }

            boolean isJavaIdChar = checkStart ? Character.isJavaIdentifierStart(c) : Character.isJavaIdentifierPart(c);
            if (!isJavaIdChar && (checkStart || c != '.') && !(c == '*' && forImport)) { // star should be allowed only at the end for import todo
                if (hasCleaner && cleaner.test(this, NameCursorState.INVALID_CHAR)) {
                    continue;
                } else {
                    break;
                }
            }

            char[] chars = Character.toChars(c);
            name.append(chars);
            this.cursor += chars.length;
            checkStart = c == '.';
        }
        return name.toString();
    }
}
