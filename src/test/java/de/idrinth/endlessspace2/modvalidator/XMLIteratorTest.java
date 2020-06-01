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
    public final static String ROOT = "src/test/resources/de/idrinth/endlessspace2/modvalidator";
    @Test
    public void testRunWithCase1() {
        System.out.println("run");
        var folder = new File(ROOT + "/xml-case-1");
        var instance = new XMLIterator(folder);
        var file = new File(ROOT + "/xml-case-1/example.xml");
        instance.run(folder, logger, simulationDescriptors);
        verify(logger, times(1)).debug(file, "starting");
        verify(logger, times(1)).debug(file, "schema example.xsd");
        verify(logger, times(1)).error(eq(file), isA(SAXException.class));
        verify(logger, times(1)).debug(file, "done");
        verifyNoMoreInteractions(logger);
    }
    @Test
    public void testRunWithCase2() {
        System.out.println("run");
        var folder = new File(ROOT + "/xml-case-2");
        var instance = new XMLIterator(folder);
        var file1 = new File(ROOT + "/xml-case-2/example.xml");
        var file2 = new File(ROOT + "/xml-case-2/subfolder/example.xml");
        instance.run(folder, logger, simulationDescriptors);
        verify(logger, times(1)).debug(file1, "starting");
        verify(logger, times(1)).debug(file1, "schema example.xsd");
        verify(logger, times(1)).error(eq(file1), isA(SAXException.class));
        verify(logger, times(1)).debug(file1, "done");
        verify(logger, times(1)).debug(file2, "starting");
        verify(logger, times(1)).debug(file2, "schema example.xsd");
        verify(logger, times(1)).error(eq(file2), isA(SAXException.class));
        verify(logger, times(1)).debug(file2, "done");
        verifyNoMoreInteractions(logger);
    }

    @Test
    public void testRunWithCase1Directory() {
        System.out.println("run");
        var instance = new XMLIterator();
        var folder = new File(ROOT + "/xml-case-1");
        var file = new File(ROOT + "/xml-case-1/example.xml");
        instance.run(folder, logger, simulationDescriptors);
        verify(logger, times(1)).debug(file, "starting");
        verify(logger, times(1)).debug(file, "schema example.xsd");
        verify(logger, times(1)).debug(file, "done");
        verifyNoMoreInteractions(logger);
    }
    @Test
    public void testRunWithADirectory() {
        System.out.println("run");
        var instance = new XMLIterator();
        var folder = new File(ROOT + "/xml-case-2");
        var file1 = new File(ROOT + "/xml-case-2/example.xml");
        var file2 = new File(ROOT + "/xml-case-2/subfolder/example.xml");
        instance.run(folder, logger, simulationDescriptors);
        verify(logger, times(1)).debug(file1, "starting");
        verify(logger, times(1)).debug(file1, "schema example.xsd");
        verify(logger, times(1)).debug(file1, "done");
        verify(logger, times(1)).debug(file2, "starting");
        verify(logger, times(1)).debug(file2, "schema example.xsd");
        verify(logger, times(1)).debug(file2, "done");
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
