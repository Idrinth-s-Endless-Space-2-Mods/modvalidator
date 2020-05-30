package de.idrinth.endlessspace2.modvalidator;

import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class SimulationDescriptor {
    private final String name;
    private final File source;
    private final boolean validate;
    private final Set<String> properties = new HashSet<>();
    private final Set<Reference> references = new HashSet<>();

    public SimulationDescriptor(String name, File source) {
        this(name, source, true);
    }
    public SimulationDescriptor(String name, File source, boolean validate) {
        this.name = name;
        this.source = source;
        this.validate = validate;
    }
    public String id() {
        return name;
    }
    public void addProperty(String name) {
        properties.add(name);
    }
    public boolean hasProperty(String name) {
        return properties.contains(name);
    }
    public void addReference(String path, String name) {
        var last = path.lastIndexOf("/");
        var cls = path.substring(last < 0 ? 0 : last+1);
        if (!cls.startsWith("Class")) {
            return;
        }
        references.add(new Reference(cls, name));
    }
    @Override
    public SimulationDescriptor clone() {
        var result = new SimulationDescriptor(name, source);
        result.properties.addAll(properties);
        result.references.addAll(references);
        return result;
    }
    public void addMissingProperties(SimulationDescriptor other) {
        if (other == null) {
            return;
        }
        other.properties.forEach((property) -> {
            properties.add(property);
        });
    }
    public void check(TextOutputLogger logger, SimulationDescriptors map) {
        if (!validate) {
            return;
        }
        logger.debug(source, "Checking "+references.size()+" References");
        references.forEach((reference) -> {
            if (!map.has(reference.cls)) {
                logger.error(source, "Missing Type "+reference.cls);
            } else if (!map.get(reference.cls).hasProperty(reference.property)) {
                logger.error(source, "Missing Property "+reference.property+" of Type "+reference.cls);
            }
        });
        logger.debug(source, "done");
    }

    @Override
    public int hashCode() {
        return 53 * 7 + Objects.hashCode(this.name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final SimulationDescriptor other = (SimulationDescriptor) obj;
        return Objects.equals(this.name, other.name);
    }
    private class Reference {
        public final String cls;
        public final String property;

        public Reference(String cls, String property) {
            this.cls = cls;
            this.property = property;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 17 * hash + Objects.hashCode(this.cls);
            hash = 17 * hash + Objects.hashCode(this.property);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            final Reference other = (Reference) obj;
            return Objects.equals(this.cls, other.cls) && Objects.equals(this.property, other.property);
        }
    }
}
