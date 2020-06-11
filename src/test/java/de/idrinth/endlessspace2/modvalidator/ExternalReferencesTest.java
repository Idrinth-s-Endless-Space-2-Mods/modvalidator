package de.idrinth.endlessspace2.modvalidator;

import de.idrinth.endlessspace2.modvalidator.logger.Logger;
import java.io.File;
import java.util.HashSet;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ExternalReferencesTest {
    @Mock
    public SimulationDescriptors list;
    @Mock
    public Logger logger;
    @Test
    public void testCheck() {
        System.out.println("check");
        when(list.has("3")).thenReturn(false);
        ExternalReferences instance = new ExternalReferences();
        var references = new HashSet<ExternalReference>();
        references.add(new ExternalReference(new File("."), "2", "3"));
        instance.add("example", "ClassExample", references);
        instance.check(logger, list);
        verify(logger).error(
            new File("."),
            "Unable to find SimulationDescriptor 3 referenced by example of type ClassExample in 2"
        );
        verifyNoMoreInteractions(logger);
        verify(list).has("3");
        verifyNoMoreInteractions(list);
    }
    @Test
    public void testEquals() {
        ExternalReferences instance = new ExternalReferences();
        assertNotEquals(null, instance);
        assertEquals(new ExternalReferences(), instance);
        var set = new HashSet<ExternalReference>();
        set.add(new ExternalReference(new File("."), "k", "q"));
        instance.add("name", "type", set);
        assertNotEquals(new ExternalReferences(), instance);
        instance.add("name", "type", new HashSet<>());
        assertEquals(new ExternalReferences(), instance);
    }
    @Test
    public void testHashCode() {
        ExternalReferences instance = new ExternalReferences();
        assertEquals(553, instance.hashCode());
    }
}
