package de.idrinth.endlessspace2.modvalidator.xmliterator;

import de.idrinth.endlessspace2.modvalidator.ExternalReferences;
import de.idrinth.endlessspace2.modvalidator.SimulationDescriptorReference;
import de.idrinth.endlessspace2.modvalidator.SimulationDescriptors;
import de.idrinth.endlessspace2.modvalidator.TextOutputLogger;
import java.io.File;
import java.util.HashSet;
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
public class ValidatingXMLIteratorTest {
    @Mock
    public TextOutputLogger logger;
    @Mock
    public SimulationDescriptors simulationDescriptors;
    @Mock
    public ExternalReferences externals;
    @Mock
    public HashSet<SimulationDescriptorReference> references;
    public final static String ROOT = "src/test/resources/de/idrinth/endlessspace2/modvalidator";
    @Test
    public void testRunWithCase1() {
        System.out.println("run");
        var folder = new File(ROOT + "/xml-case-1");
        var instance = new ValidatingXMLIterator(folder, references);
        var file = new File(ROOT + "/xml-case-1/example.xml");
        instance.run(folder, logger, simulationDescriptors, externals);
        verify(logger, times(1)).debug(file, "starting");
        verify(logger, times(1)).debug(file, "schema Schemas/example.xsd");
        verify(logger, times(1)).error(eq(file), isA(SAXException.class));
        verify(logger, times(1)).debug(file, "done");
        verifyNoMoreInteractions(logger);
    }
    @Test
    public void testRunWithCase2() {
        System.out.println("run");
        var folder = new File(ROOT + "/xml-case-2");
        var instance = new ValidatingXMLIterator(folder, references);
        var file1 = new File(ROOT + "/xml-case-2/example.xml");
        var file2 = new File(ROOT + "/xml-case-2/subfolder/example.xml");
        instance.run(folder, logger, simulationDescriptors, externals);
        verify(logger, times(1)).debug(file1, "starting");
        verify(logger, times(1)).debug(file1, "schema Schemas/example.xsd");
        verify(logger, times(1)).error(eq(file1), isA(SAXException.class));
        verify(logger, times(1)).debug(file1, "done");
        verify(logger, times(1)).debug(file2, "starting");
        verify(logger, times(1)).debug(file2, "schema Schemas/example.xsd");
        verify(logger, times(1)).warn(file2, "schema starts with a none-existing path: Documentation/Schemas/example.xsd");
        verify(logger, times(1)).error(eq(file2), isA(SAXException.class));
        verify(logger, times(1)).debug(file2, "done");
        verifyNoMoreInteractions(logger);
    }
    @Test
    public void testRunWithCase3() {
        System.out.println("run");
        var folder = new File(ROOT + "/xml-case-3");
        var instance = new ValidatingXMLIterator(folder, references);
        var file = new File(ROOT + "/xml-case-3/SimulationDescriptors[Idrinth][Garrison].xml");
        instance.run(folder, logger, simulationDescriptors, externals);
        verify(logger, times(1)).debug(file, "starting");
        verify(logger, times(1)).debug(file, "schema Schemas/Amplitude.Unity.Simulation.SimulationDescriptor.xsd");
        verify(logger, times(1)).error(eq(file), isA(SAXException.class));
        verify(logger, times(1)).debug(file, "done");
        verifyNoMoreInteractions(logger);
    }

    @Test
    public void testRunWithCase1Directory() {
        System.out.println("run");
        var folder = new File(ROOT + "/xml-case-1");
        var instance = new ValidatingXMLIterator(folder, references);
        var file = new File(ROOT + "/xml-case-1/example.xml");
        instance.run(folder, logger, simulationDescriptors, externals);
        verify(logger, times(1)).debug(file, "starting");
        verify(logger, times(1)).debug(file, "schema Schemas/example.xsd");
        verify(logger, times(1)).error(eq(file), isA(SAXException.class));
        verify(logger, times(1)).debug(file, "done");
        verifyNoMoreInteractions(logger);
    }
    @Test
    public void testRunWithCase2Directory() {
        System.out.println("run");
        var folder = new File(ROOT + "/xml-case-2");
        var instance = new ValidatingXMLIterator(folder, references);
        var file1 = new File(ROOT + "/xml-case-2/example.xml");
        var file2 = new File(ROOT + "/xml-case-2/subfolder/example.xml");
        instance.run(folder, logger, simulationDescriptors, externals);
        verify(logger, times(1)).debug(file1, "starting");
        verify(logger, times(1)).debug(file1, "schema Schemas/example.xsd");
        verify(logger, times(1)).error(eq(file1), isA(SAXException.class));
        verify(logger, times(1)).debug(file1, "done");
        verify(logger, times(1)).debug(file2, "starting");
        verify(logger, times(1)).warn(file2, "schema starts with a none-existing path: Documentation/Schemas/example.xsd");
        verify(logger, times(1)).debug(file2, "schema Schemas/example.xsd");
        verify(logger, times(1)).error(eq(file2), isA(SAXException.class));
        verify(logger, times(1)).debug(file2, "done");
        verifyNoMoreInteractions(logger);
    }
    @Test
    public void testRunWithANoneDirectory() {
        System.out.println("run");
        var file = new File("src/test/resources/de/idrinth/endlessspace2/modvalidator/example.xml");
        var instance = new ValidatingXMLIterator(file, references);
        instance.run(file, logger, simulationDescriptors, externals);
        verify(logger, times(1)).error(file, "Failed to get children");
        verifyNoMoreInteractions(logger);
    }
}
