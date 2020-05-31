package de.idrinth.endlessspace2.modvalidator;

import java.io.File;
import java.util.Collection;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SimulationDescriptorsTest {

    /**
     * Test of put method, of class SimulationDescriptors.
     */
    @Test
    public void testPut() {
        System.out.println("put");
        var name = "";
        var sd = new SimulationDescriptor(name, new File("."));
        var instance = new SimulationDescriptors();
        assertEquals(null, instance.get(name));
        instance.put(name, sd);
        assertEquals(sd, instance.get(name));
    }

    /**
     * Test of get method, of class SimulationDescriptors.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        var name = "";
        var sd = new SimulationDescriptor(name, new File("."));
        var instance = new SimulationDescriptors();
        assertEquals(null, instance.get(name));
        instance.put(name, sd);
        assertEquals(sd, instance.get(name));
    }

    @Test
    public void testValues() {
        System.out.println("values");
        var instance = new SimulationDescriptors();
        assertEquals(true, instance.values().isEmpty());
        instance.put("", new SimulationDescriptor("", new File(".")));
        assertEquals(false, instance.values().isEmpty());
        assertEquals(1, instance.values().size());
        assertEquals(1, instance.values().size());
    }

    @Test
    public void testHas() {
        System.out.println("has");
        var name = "";
        var instance = new SimulationDescriptors();
        assertEquals(false, instance.has(name));
        instance.put(name, new SimulationDescriptor(name, new File(".")));
        assertEquals(true, instance.has(name));
    }

    @Test
    public void testClone() {
        System.out.println("clone");
        var instance = new SimulationDescriptors();
        var expResult = new SimulationDescriptors();
        var result = instance.clone();
        assertNotEquals(null, result);
        assertEquals(expResult, result);
        assertEquals(instance, result);
    }
}
