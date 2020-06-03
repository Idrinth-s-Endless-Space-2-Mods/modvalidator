package de.idrinth.endlessspace2.modvalidator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PairTest {
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        assertEquals(23166, new Pair("a", "b").hashCode());
        assertEquals(24782, new Pair("c", "11").hashCode());
    }
    @Test
    public void testEquals() {
        System.out.println("equals");
        Pair instance = new Pair("a", "b");
        assertEquals(new Pair("a", "b"), instance);
        assertNotEquals(new Pair("a", "c"), instance);
        assertNotEquals(null, instance);
        assertNotEquals(new SimulationDescriptor("a", "b", null, null), instance);
    }
    
}
