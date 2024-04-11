package io.papermc.generator.rewriter.data.yml;

import org.junit.jupiter.params.converter.TypedArgumentConverter;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.ScalarNode;
import org.yaml.snakeyaml.nodes.Tag;
import java.io.IOException;
import java.io.InputStream;

public class YmlMappingConverter<T> extends TypedArgumentConverter<String, T> {

    private static final LoaderOptions OPTIONS;
    static {
        OPTIONS = new LoaderOptions();
        OPTIONS.setNestingDepthLimit(3);
    }

    private final Constructor yamlConstructor;

    protected YmlMappingConverter(Class<T> clazz, String relativePackage) {
        super(String.class, clazz);
        if (relativePackage == null) {
            this.yamlConstructor = new Constructor(clazz, OPTIONS);
        } else {
            this.yamlConstructor = new Constructor(clazz, OPTIONS) {

                @Override
                public String constructScalar(ScalarNode node) {
                    if (node.getTag() == Tag.STR && node.getValue().startsWith("(_).")) {
                        return node.getValue().replaceFirst("\\(_\\)", relativePackage);
                    }

                    return super.constructScalar(node);
                }
            };
        }
    }

    @Override
    protected T convert(String path) {
        try (InputStream input = this.getClass().getResourceAsStream(path)) {
            return new Yaml(this.yamlConstructor).load(input);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
