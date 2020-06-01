package de.idrinth.endlessspace2.modvalidator;

import java.io.File;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import org.mockito.junit.jupiter.MockitoExtension;
import org.xml.sax.SAXException;

@ExtendWith(MockitoExtension.class)
public class XMLIteratorTest {
    @Mock
    public TextOutputLogger logger;
    @Mock
    public SimulationDescriptors simulationDescriptors;
    @Test
    public void testRun() {
        System.out.println("run");
        var folder = new File("src/test/resources/de/idrinth/endlessspace2/modvalidator");
        var instance = new XMLIterator(folder);
        var file = new File("src/test/resources/de/idrinth/endlessspace2/modvalidator/example.xml");
        instance.run(folder, logger, simulationDescriptors);
        verify(logger, times(1)).debug(file, "starting");
        verify(logger, times(1)).debug(file, "schema example.xsd");
        verify(logger, times(1)).error(eq(file), isA(SAXException.class));
        verify(logger, times(1)).debug(file, "done");
        verifyNoMoreInteractions(logger);
    }

    @Test
    public void testRunWithADirectory() {
        System.out.println("run");
        var instance = new XMLIterator();
        var folder = new File("src/test/resources/de/idrinth/endlessspace2/modvalidator");
        var file = new File("src/test/resources/de/idrinth/endlessspace2/modvalidator/example.xml");
        instance.run(folder, logger, simulationDescriptors);
        verify(logger, times(1)).debug(file, "starting");
        verify(logger, times(1)).debug(file, "schema example.xsd");
        verify(logger, times(1)).debug(file, "done");
        verifyNoMoreInteractions(logger);
    }
    @Test
    public void testRunWithANoneDirectory() {
        System.out.println("run");
        var instance = new XMLIterator();
        var file = new File("src/test/resources/de/idrinth/endlessspace2/modvalidator/example.xml");
        instance.run(file, logger, simulationDescriptors);
        verify(logger, times(1)).error(file, "Failed to get children");
        verifyNoMoreInteractions(logger);
    }
}
