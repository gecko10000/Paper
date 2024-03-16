package io.papermc.generator.rewriter;

import com.google.common.base.Preconditions;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CompositeRewriter extends SearchReplaceRewriter {

    private final Map<String, SearchReplaceRewriter> patternsInfo;

    private CompositeRewriter(ClassNamed rewriteClass, List<SearchReplaceRewriter> rewriters) {
        super(rewriteClass, null, false);
        this.patternsInfo = rewriters.stream().collect(Collectors.toMap(rewriter -> rewriter.pattern, rewriter -> rewriter));
    }

    @Override
    protected void beginSearch() {
        for (SearchReplaceRewriter rewriter : this.getRewriters()) {
            rewriter.beginSearch();
        }
    }

    public static CompositeRewriter bind(SearchReplaceRewriter... rewriters) {
        return bind(Arrays.asList(rewriters));
    }

    public static CompositeRewriter bind(List<SearchReplaceRewriter> rewriters) {
        Preconditions.checkArgument(!rewriters.isEmpty(), "Rewriter list cannot be empty!");
        ClassNamed rewriteClass = rewriters.get(0).rewriteClass;
        String rootClassName = rewriteClass.rootClassCanonicalName();

        for (SearchReplaceRewriter rewriter : rewriters) {
            Preconditions.checkState(!(rewriter instanceof CompositeRewriter), "Nested composite rewriters are not allowed!");
            Preconditions.checkArgument(rewriter.pattern != null, "Rewriter pattern cannot be null!");
            Preconditions.checkState(rewriteClass.packageName().equals(rewriter.rewriteClass.packageName()) &&
                rootClassName.equals(rewriter.rewriteClass.rootClassCanonicalName()), "Composite rewriter only works for one file!");
        }

        return new CompositeRewriter(rewriteClass, rewriters);
    }

    @Override
    protected void searchAndReplace(BufferedReader reader, StringBuilder content, Map<String, SearchReplaceRewriter> patternInfo) throws IOException {
        Preconditions.checkState(patternInfo.isEmpty());
        super.searchAndReplace(reader, content, this.patternsInfo);
    }

    public Collection<SearchReplaceRewriter> getRewriters() {
        return this.patternsInfo.values();
    }
}
