package de.idrinth.endlessspace2.modvalidator;

import de.idrinth.endlessspace2.modvalidator.logger.Logger;
import java.util.HashMap;
import java.util.Objects;
import java.util.Set;

public class ExternalReferences {
    private final HashMap<ExternalReferenceId, Set<ExternalReference>> externals = new HashMap<>();
    public void add(String name, String type, Set<ExternalReference> references) {
        var key = new ExternalReferenceId(name, type);
        if (references.isEmpty()) {
            if (externals.containsKey(key)) {
                externals.remove(key);
            }
            return;
        }
        externals.put(key, references);
    }
    public void check(Logger logger, SimulationDescriptors list) {
        for (var id : externals.keySet()) {
            var references = externals.get(id);
            for (var reference : references) {
                if (!list.has(reference.target())) {
                    logger.error(
                        reference.file(),
                        "Unable to find SimulationDescriptor "+reference.target()+" referenced by "+id.name+" of type "+id.type+" in "+reference.tag()
                    );
                }
            }
        }
    }

    @Override
    public int hashCode() {
        return 79 * 7 + Objects.hashCode(this.externals);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final ExternalReferences other = (ExternalReferences) obj;
        return Objects.equals(this.externals, other.externals);
    }
    private class ExternalReferenceId {
        private final String name;
        private final String type;
        public ExternalReferenceId(String name, String type) {
            this.name = name;
            this.type = type;
        }
        @Override
        public int hashCode() {
            int hash = 67 * 5 + Objects.hashCode(this.name);
            return 67 * hash + Objects.hashCode(this.type);
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (null == obj || getClass() != obj.getClass()) {
                return false;
            }
            final ExternalReferenceId other = (ExternalReferenceId) obj;
            return Objects.equals(this.name, other.name) &&Objects.equals(this.type, other.type);
        }
    }
}
