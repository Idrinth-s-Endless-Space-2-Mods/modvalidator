package de.idrinth.endlessspace2.modvalidator;

import java.util.Objects;

public class SimulationDescriptorReference {
    public final String schema;
    public final String tag;
    public final String attribute;

    public SimulationDescriptorReference(String schema, String tag, String attribute) {
        this.schema = schema;
        this.tag = tag;
        this.attribute = attribute;
    }

    @Override
    public int hashCode() {
        int hash = 67 * 7 + Objects.hashCode(this.schema);
        hash = 67 * hash + Objects.hashCode(this.tag);
        return 67 * hash + Objects.hashCode(this.attribute);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final SimulationDescriptorReference other = (SimulationDescriptorReference) obj;
        return Objects.equals(this.schema, other.schema) && Objects.equals(this.tag, other.tag) && Objects.equals(this.attribute, other.attribute);
    }
}
