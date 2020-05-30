package de.idrinth.endlessspace2.modvalidator;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class SimulationDescriptors
{
    private static Set<Pair> keysToCopy;
    private final HashMap<String, SimulationDescriptor> data;
    public SimulationDescriptors() {
        data = new HashMap<>();
        if (null != keysToCopy) {
            return;
        }
        //Stuff that is explained nowhere and still works
        keysToCopy = new HashSet<>();
        keysToCopy.add(new Pair("ClassStarSystem", "ClassColonizedStarSystem"));
        keysToCopy.add(new Pair("ClassColonizedStarSystem", "ClassExploitedStarSystem"));
        keysToCopy.add(new Pair("ClassPlanet", "ClassColonizedPlanet"));
        keysToCopy.add(new Pair("ClassGarrison", "ClassGarrisonFleet"));
    }
    public void put(String name, SimulationDescriptor sd) {
        data.put(name, sd);
    }
    public SimulationDescriptor get(String name) {
        var result = data.get(name);
        if (result != null) {
            result = result.clone();
            for (Pair pair : keysToCopy) {
                if (name.equals(pair.to)) {
                    result.addMissingProperties(get(pair.from));
                }
            }
        }
        return result;
    }
    public Collection<SimulationDescriptor> values() {
        return data.values();
    }
    public boolean has(String name) {
        return data.containsKey(name);
    }
    @Override
    public SimulationDescriptors clone()
    {
        var result = new SimulationDescriptors();
        result.data.putAll(data);
        return result;
    }
    private class Pair {
        public final String from;
        public final String to;

        public Pair(String from, String to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public int hashCode() {
            int hash = 73 * 3 + Objects.hashCode(this.from);
            return 73 * hash + Objects.hashCode(this.to);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            final Pair other = (Pair) obj;
            return Objects.equals(this.from, other.from) && Objects.equals(this.to, other.to);
        }
    }
}
