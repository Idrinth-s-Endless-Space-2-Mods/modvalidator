package de.idrinth.endlessspace2.modvalidator.xmliterator;

import de.idrinth.endlessspace2.modvalidator.ExternalReference;
import de.idrinth.endlessspace2.modvalidator.ExternalReferences;
import de.idrinth.endlessspace2.modvalidator.SimulationDescriptorReference;
import java.io.File;
import java.util.HashSet;
import java.util.Stack;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class SAXSimulationDescriptorReferenceFinder extends SAXFinder {

    private String schema;
    private final Stack<String> tag = new Stack<>();
    private String name;
    private final HashSet<SimulationDescriptorReference> references;
    private final HashSet<SimulationDescriptorReference> current = new HashSet<>();
    private final ExternalReferences externals;
    private final HashSet<ExternalReference> list = new HashSet<>();
    private final File source;

    public SAXSimulationDescriptorReferenceFinder(HashSet<SimulationDescriptorReference> references, ExternalReferences externals, File source) {
        this.references = references;
        this.externals = externals;
        this.source = source;
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        if (schema == null) {
            schema = byLocalName(atts, "noNamespaceSchemaLocation");
            for (var reference : references) {
                if (schema.endsWith(reference.schema)) {
                    current.add(reference);
                }
            }
            return;
        }
        if (tag.isEmpty() && null != byLocalName(atts, "Name")) {
            name = byLocalName(atts, "Name");
        }
        tag.push(localName);
        for (var reference : current) {
            if (reference.tag.equals(localName)) {
                var value = byLocalName(atts, reference.attribute);
                if (!value.isEmpty()) {
                    list.add(new ExternalReference(source, localName, value));
                }
            }
        }
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
        if (tag.isEmpty()) {
            return;
        }
        var type = tag.pop();
        if (tag.isEmpty()) {
            var l = new HashSet<>(list);
            externals.add(name, type, l);
            list.clear();
            current.clear();
        }
    }
}
