package io.papermc.generator.rewriter;

import io.papermc.generator.utils.ClassHelper;
import org.jetbrains.annotations.Nullable;
import java.util.Objects;

public record ClassNamed(String packageName, String simpleName, String dottedNestedName, @Nullable Class<?> knownClass) {

    public ClassNamed(Class<?> knownClass) {
        this(knownClass.getPackageName(), knownClass.getSimpleName(), ClassHelper.retrieveFullNestedName(knownClass), knownClass);
    }

    // the class name shouldn't have any '$' char in it
    // otherwise it will be interpreted as a nested class
    public static ClassNamed of(String packageName, String name) {
        int nestedIndex = name.lastIndexOf('$');
        final String simpleName;
        final String nestedName;
        if (nestedIndex != -1) {
            simpleName = name.substring(nestedIndex + 1);
            nestedName = name.replace('$', '.');
        } else {
            simpleName = name;
            nestedName = name;
        }
        return new ClassNamed(packageName, simpleName, nestedName, null);
    }

    public ClassNamed root() {
        if (this.knownClass != null) {
            Class<?> rootClass = ClassHelper.getRootClass(this.knownClass);
            if (rootClass == this.knownClass) {
                return this;
            }
            return new ClassNamed(rootClass);
        }

        int dotIndex = this.dottedNestedName.indexOf('.');
        if (dotIndex != -1) {
            String name = this.dottedNestedName.substring(0, dotIndex);
            return new ClassNamed(this.packageName, name, name, null);
        }
        return this;
    }

    public boolean isRoot() {
        return this.root() == this;
    }

    public ClassNamed parent() {
        if (this.knownClass != null) {
            Class<?> parentClass = this.knownClass.getEnclosingClass();
            if (parentClass == null) {
                return this;
            }
            return new ClassNamed(parentClass);
        }

        int dotIndex = this.dottedNestedName.lastIndexOf('.');
        if (dotIndex != -1) {
            String name = this.dottedNestedName.substring(0, dotIndex);

            int lastDotIndex = name.lastIndexOf('.');
            final String simpleName;
            if (lastDotIndex != -1) {
                simpleName = name.substring(lastDotIndex + 1);
            } else {
                simpleName = name; // root
            }
            return new ClassNamed(this.packageName, simpleName, name, null);
        }
        return this;
    }

    public String canonicalName() {
        if (this.knownClass != null) {
            return this.knownClass.getCanonicalName();
        }

        return this.packageName + '.' + this.dottedNestedName;
    }

    @Override
    public int hashCode() {
        if (this.knownClass != null) {
            return this.knownClass.hashCode();
        }
        return Objects.hash(this.packageName, this.dottedNestedName);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        ClassNamed other = (ClassNamed) o;
        if (this.knownClass != null && other.knownClass != null) {
            return this.knownClass == other.knownClass;
        }
        return this.packageName.equals(other.packageName) &&
               this.dottedNestedName.equals(other.dottedNestedName);
    }

}
