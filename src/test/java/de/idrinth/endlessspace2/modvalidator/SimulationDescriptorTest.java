package de.idrinth.endlessspace2.modvalidator;

import java.io.File;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SimulationDescriptorTest {
    @Mock
    public SimulationDescriptors map;
    @Mock
    public TextOutputLogger logger;
    @Mock
    public File file;
    @Test
    public void testId() {
        System.out.println("id");
        var instance = new SimulationDescriptor("", "", file, map);
        assertEquals("", instance.id());
        instance = new SimulationDescriptor("2", "", file, map);
        assertEquals("2", instance.id());
    }

    @Test
    public void testAddProperty() {
        System.out.println("addProperty");
        var name = "example";
        var instance = new SimulationDescriptor("", "", file, map);
        assertEquals(false, instance.hasProperty(name));
        instance.addProperty(name);
        assertEquals(true, instance.hasProperty(name));
    }

    @Test
    public void testHasProperty() {
        System.out.println("hasProperty");
        var name = "";
        var instance = new SimulationDescriptor("", "", file, map);
        assertEquals(false, instance.hasProperty(name));
        instance.addProperty(name);
        assertEquals(true, instance.hasProperty(name));
    }

    @Test
    public void testAddReference() {
        System.out.println("addReference");
        var instance = new SimulationDescriptor("", "", file, map);
        assertEquals(true, instance.addReference("ClassExample", "property"));
        assertEquals(false, instance.addReference("Example", "property"));

    }

    @Test
    public void testClone() {
        System.out.println("clone");
        var instance = new SimulationDescriptor("", "", file, map);
        var expResult = new SimulationDescriptor("", "", file, map);
        var name = "cloned";
        instance.addProperty(name);
        expResult.addProperty(name);
        SimulationDescriptor result = instance.clone();
        assertNotEquals(null, result);
        assertEquals(expResult, result);
        assertEquals(instance, result);
    }

    @Test
    public void testAddMissingProperties() {
        System.out.println("addMissingProperties");
        var name = "property";
        var other = new SimulationDescriptor("", "", file, map);
        var instance = new SimulationDescriptor("", "", file, map);
        other.addProperty(name);
        assertEquals(true, other.hasProperty(name));
        assertEquals(false, instance.hasProperty(name));
        instance.addMissingProperties(other);
        assertEquals(true, instance.hasProperty(name));
    }

    @Test
    public void testCheck() {
        System.out.println("check");
        var instance0 = new SimulationDescriptor("example", "ClassExample", file, map);
        var instance1 = new SimulationDescriptor("ClassExample", "ClassExample", file, map);
        when(map.has(anyString())).thenAnswer((InvocationOnMock invocation) -> invocation.getArgument(0).equals("example") || invocation.getArgument(0).equals("ClassExample"));
        when(map.get("ClassExample")).thenReturn(instance1);
        instance0.addProperty("prop1");
        instance1.addProperty("prop2");
        instance1.addProperty("prop3");
        instance0.addReference("", "prop0");
        instance0.addReference("", "prop1");
        instance0.addReference("", "prop2");
        instance0.addReference("ClassExample", "prop3");
        instance0.addReference("ClassExample", "prop4");
        instance0.addReference("ClassNoExample", "prop4");
        instance0.check(logger);
        verify(logger, times(1)).debug(file, "Checking 6 References");
        verify(logger, times(1)).error(file, "Missing Property prop4 of Type ClassExample");
        verify(logger, times(1)).error(file, "Missing Property prop0 of SimulationDescriptor example");
        verify(logger, times(1)).error(file, "Missing Type ClassNoExample");
        verify(logger, times(1)).debug(file, "done");
        verifyNoMoreInteractions(logger);
    }
}
