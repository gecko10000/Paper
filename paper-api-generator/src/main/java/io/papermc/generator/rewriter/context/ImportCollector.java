package io.papermc.generator.rewriter.context;

import io.papermc.generator.rewriter.ClassNamed;

public interface ImportCollector {

    ImportCollector NO_OP = new ImportCollector() {
        @Override
        public void addImport(final String typeName) {

        }

        @Override
        public void addStaticImport(final String typeName) {

        }

        @Override
        public String getStaticMemberShortName(final String fullName) {
            return fullName;
        }

        @Override
        public String getShortName(final ClassNamed type) {
            return type.canonicalName();
        }

    };

    void addImport(String typeName);

    void addStaticImport(String typeName);

    String getStaticMemberShortName(String fullName);

    default String getShortName(Class<?> type) {
        return this.getShortName(new ClassNamed(type));
    }

    String getShortName(ClassNamed type);

}
