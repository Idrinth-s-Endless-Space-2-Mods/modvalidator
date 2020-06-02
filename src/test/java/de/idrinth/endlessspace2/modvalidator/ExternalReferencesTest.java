package de.idrinth.endlessspace2.modvalidator;

import java.io.File;
import java.util.HashSet;
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
    public TextOutputLogger logger;
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
}
