package de.idrinth.endlessspace2.modvalidator;

import java.io.File;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SimpleXMLIteratorTest {
    @Mock
    public TextOutputLogger logger;
    @Mock
    public SimulationDescriptors simulationDescriptors;

    @Test
    public void testRun() {
        System.out.println("run");
        SimpleXMLIterator instance = new SimpleXMLIterator();
        var folder = new File("src/test/resources/de/idrinth/endlessspace2/modvalidator");
        var file = new File("src/test/resources/de/idrinth/endlessspace2/modvalidator/example.xml");
        instance.run(folder, logger, simulationDescriptors);
        verify(logger, times(1)).debug(file, "starting");
        verify(logger, times(1)).debug(file, "schema example.xsd");
        verify(logger, times(1)).debug(file, "done");
        verifyNoMoreInteractions(logger);
    }
    @Test
    public void testRun_NotDirectory() {
        System.out.println("run");
        SimpleXMLIterator instance = new SimpleXMLIterator();
        var file = new File("src/test/resources/de/idrinth/endlessspace2/modvalidator/example.xml");
        instance.run(file, logger, simulationDescriptors);
        verify(logger, times(1)).error(file, "Failed to get children");
        verifyNoMoreInteractions(logger);
    }
}
