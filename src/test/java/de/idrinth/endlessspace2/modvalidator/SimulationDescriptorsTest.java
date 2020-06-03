package de.idrinth.endlessspace2.modvalidator;

import java.io.File;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SimulationDescriptorsTest {
    @Mock
    public SimulationDescriptor sd;
    @Test
    public void testPut() {
        System.out.println("put");
        when(sd.clone()).thenReturn(sd);
        var name = "n";
        var instance = new SimulationDescriptors();
        assertEquals(null, instance.get(name));
        instance.put(name, sd);
        assertEquals(sd, instance.get(name));
    }

    @Test
    public void testGet() {
        System.out.println("get");
        when(sd.clone()).thenReturn(sd);
        var name = "name";
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
        instance.put("", sd);
        assertEquals(false, instance.values().isEmpty());
        assertEquals(1, instance.values().size());
    }

    @Test
    public void testHas() {
        System.out.println("has");
        var name = "";
        var instance = new SimulationDescriptors();
        assertEquals(false, instance.has(name));
        instance.put(name, sd);
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

    @Test
    public void testCloneWithData() {
        System.out.println("clone");
        var instance = new SimulationDescriptors();
        instance.put("example", new SimulationDescriptor("", "", new File("."), instance));
        var result = instance.clone();
        assertNotEquals(null, result);
        assertEquals(1, result.values().size());
        assertEquals(true, result.values().contains(new SimulationDescriptor("", "", new File("."), instance)));
    }

    @Test
    public void testEquals() {
        System.out.println("equals");
        var instance = new SimulationDescriptors();
        var expResult = new SimulationDescriptors();
        assertNotEquals(null, instance);
        assertEquals(expResult, instance);
        assertNotEquals(instance, new SimulationDescriptor("", "", new File("."), instance));
    }

    @Test
    public void testHashCode() {
        System.out.println("equals");
        var instance = new SimulationDescriptors();
        assertEquals(413, instance.hashCode());
    }
}
