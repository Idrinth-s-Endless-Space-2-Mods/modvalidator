package de.idrinth.endlessspace2.modvalidator;

import java.io.File;
import java.util.Objects;

public class ExternalReference {

    private final File file;
    private final String tag;
    private final String target;

    public ExternalReference(File file, String tag, String target) {
        this.file = file;
        this.tag = tag;
        this.target = target;
    }

    public File file() {
        return file;
    }

    public String tag() {
        return tag;
    }

    public String target() {
        return target;
    }

    @Override
    public int hashCode() {
        int hash = 89 * 3 + Objects.hashCode(this.tag);
        return 89 * hash + Objects.hashCode(this.target);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final ExternalReference other = (ExternalReference) obj;
        return Objects.equals(this.tag, other.tag) && Objects.equals(this.target, other.target);
    }
}
