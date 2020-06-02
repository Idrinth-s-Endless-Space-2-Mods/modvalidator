package de.idrinth.endlessspace2.modvalidator;

import java.io.File;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExternalReferenceTest {
    @Test
    public void testFile() {
        System.out.println("file");
        ExternalReference instance = new ExternalReference(new File("."), "1", "2");
        assertEquals(new File("."), instance.file());
    }
    @Test
    public void testTag() {
        System.out.println("tag");
        ExternalReference instance = new ExternalReference(new File("."), "1", "2");
        assertEquals("1", instance.tag());
    }
    @Test
    public void testTarget() {
        System.out.println("target");
        ExternalReference instance = new ExternalReference(new File("."), "1", "2");
        assertEquals("2", instance.target());
    }
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        ExternalReference instance = new ExternalReference(new File("."), "1", "2");
        assertEquals(28174, instance.hashCode());
    }
    @Test
    public void testEquals() {
        System.out.println("equals");
        ExternalReference instance = new ExternalReference(new File("."), "1", "2");
        assertNotEquals(null, instance);
        assertEquals(new ExternalReference(new File(".."), "1", "2"), instance);
        assertNotEquals(new ExternalReference(new File("."), "1a", "2"), instance);
        assertNotEquals(new ExternalReference(new File("."), "1", "2q"), instance);
        assertEquals(new ExternalReference(new File("."), "1", "2"), instance);
    }
    
}
