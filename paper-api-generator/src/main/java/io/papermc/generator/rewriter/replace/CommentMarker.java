package io.papermc.generator.rewriter.replace;

public record CommentMarker(String pattern, boolean start, int indent) {

    private CommentMarker() {
        this("", false, 0);
    }

    public static final CommentMarker EMPTY_MARKER = new CommentMarker();
}
