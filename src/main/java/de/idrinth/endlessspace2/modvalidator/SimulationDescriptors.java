package de.idrinth.endlessspace2.modvalidator;

import de.idrinth.endlessspace2.modvalidator.data.SimulationDescriptorHierarchyHelper;
import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;
import java.util.Set;

public class SimulationDescriptors
{
    private final HashMap<String, SimulationDescriptor> data;
    private final SimulationDescriptorAdjuster adjuster;
    public SimulationDescriptors() {
        data = new HashMap<>();
        adjuster = new SimulationDescriptorAdjuster();
    }
    public void put(String name, SimulationDescriptor sd) {
        data.put(name, sd);
    }
    public SimulationDescriptor get(String name) {
        return adjuster.get(name, data);
    }
    public Collection<SimulationDescriptor> values() {
        return data.values();
    }
    public boolean has(String name) {
        return data.containsKey(name);
    }

    @Override
    public int hashCode() {
        return 59 * 7 + Objects.hashCode(this.data);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final SimulationDescriptors other = (SimulationDescriptors) obj;
        return Objects.equals(this.data, other.data);
    }
    @Override
    public SimulationDescriptors clone()
    {
        var result = new SimulationDescriptors();
        result.data.putAll(data);
        result.values().forEach((sd) -> {
            sd.setMap(result);
        });
        return result;
    }
    private class SimulationDescriptorAdjuster {
        private final Set<Pair> keysToCopy;
        public SimulationDescriptorAdjuster() {
            keysToCopy = SimulationDescriptorHierarchyHelper.provide();
        }
        public SimulationDescriptor get(String name, HashMap<String, SimulationDescriptor> data) {
            var result = data.get(name);
            if (result != null) {
                result = result.clone();
                System.out.println(result);
                for (Pair pair : keysToCopy) {
                    if (name.equals(pair.to)) {
                        result.addMissingProperties(get(pair.from, data));
                    }
                }
            }
            return result;
        }
    }
}
