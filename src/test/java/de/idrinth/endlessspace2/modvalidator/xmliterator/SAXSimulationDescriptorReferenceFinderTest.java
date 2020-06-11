package de.idrinth.endlessspace2.modvalidator.xmliterator;

import de.idrinth.endlessspace2.modvalidator.ExternalReferences;
import de.idrinth.endlessspace2.modvalidator.SimulationDescriptorReference;
import java.util.HashSet;
import java.util.Iterator;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.xml.sax.Attributes;

@ExtendWith(MockitoExtension.class)
public class SAXSimulationDescriptorReferenceFinderTest {
    @Mock
    public HashSet<SimulationDescriptorReference> references;
    @Mock
    public ExternalReferences externals;
    @Mock
    public Attributes attributes1;
    @Mock
    public Attributes attributes2;
    @Mock
    public Iterator<SimulationDescriptorReference> refIterator;
    @Mock
    public Stream<SimulationDescriptorReference> refStream;
    @Test
    public void testStartElement() throws Exception {
        System.out.println("startElement");
        var instance = new SAXSimulationDescriptorReferenceFinder(references, externals, null);
        when(attributes1.getLength()).thenReturn(1);
        when(attributes1.getLocalName(0)).thenReturn("noNamespaceSchemaLocation");
        when(attributes1.getValue(0)).thenReturn("example.xsd");
        when(attributes1.getValue("noNamespaceSchemaLocation")).thenReturn(null);
        when(attributes2.getValue("Name")).thenReturn("example");
//        when(attributes2.getValue("attribute")).thenReturn("an attribute");
//        when(references.iterator()).thenReturn(refIterator);
//        when(refIterator.hasNext()).thenReturn(Boolean.TRUE, Boolean.FALSE);
 //       when(refIterator.next()).thenReturn(new SimulationDescriptorReference("example.xsd","local","attribute"));
        when(references.stream()).thenReturn(refStream);
        when(refStream.filter(any())).thenReturn(refStream);
        instance.startElement("ns", "wrapper", "ns:wrapper", attributes1);
        instance.startElement("ns", "local", "ns:local", attributes2);
        verify(refStream).forEachOrdered(any());
        verifyNoMoreInteractions(attributes1);
        verifyNoMoreInteractions(references);
        verifyNoMoreInteractions(externals);
        verifyNoMoreInteractions(attributes2);
        verifyNoMoreInteractions(refStream);
        verifyNoMoreInteractions(refIterator);
    }

    @Test
    public void testEndElement() throws Exception {
        System.out.println("endElement");
        var instance = new SAXSimulationDescriptorReferenceFinder(references, externals, null);
        instance.endElement("ns", "local", "ns:local");
        verifyNoMoreInteractions(references);
        verifyNoMoreInteractions(externals);
    }
}
