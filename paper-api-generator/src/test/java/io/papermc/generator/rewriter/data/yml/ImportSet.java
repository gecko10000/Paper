package io.papermc.generator.rewriter.data.yml;

import java.util.Collections;
import java.util.Set;

public class ImportSet {

    public Set<String> single;
    public Set<String> global;

    public Set<String> single() {
        return this.single == null ? Collections.emptySet() : this.single;
    }

    public Set<String> global() {
        return this.global == null ? Collections.emptySet() : this.global;
    }
}
