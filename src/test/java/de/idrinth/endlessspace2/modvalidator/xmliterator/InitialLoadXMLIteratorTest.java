package de.idrinth.endlessspace2.modvalidator.xmliterator;

import de.idrinth.endlessspace2.modvalidator.SimulationDescriptors;
import de.idrinth.endlessspace2.modvalidator.TextOutputLogger;
import java.io.File;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class InitialLoadXMLIteratorTest {
    @Mock
    public TextOutputLogger logger;
    @Mock
    public SimulationDescriptors simulationDescriptors;
    public final static String ROOT = "src/test/resources/de/idrinth/endlessspace2/modvalidator";
    @Test
    public void testRunWithCase1() {
        System.out.println("run");
        var folder = new File(ROOT + "/xml-case-1");
        var instance = new InitialLoadXMLIterator();
        var file = new File(ROOT + "/xml-case-1/example.xml");
        instance.run(folder, logger, simulationDescriptors);
        verify(logger, times(1)).debug(file, "starting");
        verify(logger, times(1)).debug(file, "done");
        verifyNoMoreInteractions(logger);
    }
    @Test
    public void testRunWithCase2() {
        System.out.println("run");
        var folder = new File(ROOT + "/xml-case-2");
        var instance = new InitialLoadXMLIterator();
        var file1 = new File(ROOT + "/xml-case-2/example.xml");
        var file2 = new File(ROOT + "/xml-case-2/subfolder/example.xml");
        instance.run(folder, logger, simulationDescriptors);
        verify(logger, times(1)).debug(file1, "starting");
        verify(logger, times(1)).debug(file1, "done");
        verify(logger, times(1)).debug(file2, "starting");
        verify(logger, times(1)).debug(file2, "done");
        verifyNoMoreInteractions(logger);
    }
    @Test
    public void testRunWithCase3() {
        System.out.println("run");
        var folder = new File(ROOT + "/xml-case-3");
        var instance = new InitialLoadXMLIterator();
        var file = new File(ROOT + "/xml-case-3/SimulationDescriptors[Idrinth][Garrison].xml");
        instance.run(folder, logger, simulationDescriptors);
        verify(logger, times(1)).debug(file, "starting");
        verify(logger, times(1)).debug(file, "done");
        verifyNoMoreInteractions(logger);
    }
    @Test
    public void testRunWithANoneDirectory() {
        System.out.println("run");
        var instance = new InitialLoadXMLIterator();
        var file = new File("src/test/resources/de/idrinth/endlessspace2/modvalidator/example.xml");
        instance.run(file, logger, simulationDescriptors);
        verify(logger, times(1)).error(file, "Failed to get children");
        verifyNoMoreInteractions(logger);
    }
}
