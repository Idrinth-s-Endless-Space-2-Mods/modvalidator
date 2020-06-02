package de.idrinth.endlessspace2.modvalidator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SimulationDescriptorReferenceTest {
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        SimulationDescriptorReference instance = new SimulationDescriptorReference("1", "2", "3");
        assertEquals(2328703, instance.hashCode());
    }
    @Test
    public void testEquals() {
        System.out.println("equals");
        assertEquals(new SimulationDescriptorReference("1", "2", "3"), new SimulationDescriptorReference("1", "2", "3"));
        assertNotEquals(new SimulationDescriptorReference("4", "2", "3"), new SimulationDescriptorReference("1", "2", "3"));
        assertNotEquals(new SimulationDescriptorReference("1", "4", "3"), new SimulationDescriptorReference("1", "2", "3"));
        assertNotEquals(new SimulationDescriptorReference("1", "2", "4"), new SimulationDescriptorReference("1", "2", "3"));
        assertNotEquals(null, new SimulationDescriptorReference("1", "2", "3"));
    }
}
