package io.papermc.generator.rewriter.data.sample.parser.imports.name;

public class SelfInnerClassImportType {
    public class A {
        public class B {
            public class C {
                {
                    var a = A.class;
                    var b = B.class;
                    var c = C.class;
                }
            }
        }
    }

    {
        var a = A.class;
        var b = A.B.class;
        var c = A.B.C.class;
    }
}
