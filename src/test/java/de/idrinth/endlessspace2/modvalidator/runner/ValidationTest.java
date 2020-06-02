package de.idrinth.endlessspace2.modvalidator.runner;

import de.idrinth.endlessspace2.modvalidator.ExternalReferences;
import de.idrinth.endlessspace2.modvalidator.SimulationDescriptor;
import de.idrinth.endlessspace2.modvalidator.SimulationDescriptors;
import de.idrinth.endlessspace2.modvalidator.TextOutputLogger;
import de.idrinth.endlessspace2.modvalidator.xmliterator.ValidatingXMLIterator;
import java.io.File;
import java.util.Collection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ValidationTest {
    @Mock
    public File modfolder;
    @Mock
    public ValidatingXMLIterator iterator;
    @Mock
    public SimulationDescriptors rootList;
    @Mock
    public TextOutputLogger logger;
    @Mock
    public Collection<SimulationDescriptor> values;
    @Test
    public void testRun() {
        System.out.println("run");
        Validation instance = new Validation(modfolder, iterator, rootList, logger);
        when(rootList.clone()).thenReturn(rootList);
        when(rootList.values()).thenReturn(values);
        instance.run();
        verify(logger).info("xsd validation");
        verify(logger).info("logic validation");
        verify(logger).info("reference validation");
        verify(logger).info("done");
        verifyNoMoreInteractions(logger);
        verify(iterator).run(eq(modfolder), eq(logger), eq(rootList), isA(ExternalReferences.class));
        verifyNoMoreInteractions(iterator);
        verify(rootList).clone();
        verify(rootList).values();
        verifyNoMoreInteractions(rootList);
    }
    
}
