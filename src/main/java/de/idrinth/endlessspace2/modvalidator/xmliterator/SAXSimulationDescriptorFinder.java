package de.idrinth.endlessspace2.modvalidator.xmliterator;

import de.idrinth.endlessspace2.modvalidator.SimulationDescriptor;
import de.idrinth.endlessspace2.modvalidator.SimulationDescriptors;
import java.io.File;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

class SAXSimulationDescriptorFinder extends SAXFinder {

    private SimulationDescriptor sd;
    private final SimulationDescriptors map;
    private String type;
    private String schema;
    private final File source;
    private final boolean validate;

    public SAXSimulationDescriptorFinder(SimulationDescriptors map, File source, boolean validate) {
        this.map = map;
        this.validate = validate;
        this.source = source;
    }

    @Override
    public void startDocument() throws SAXException {
        sd = null;
        type = null;
        schema = null;
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        if (schema == null) {
            schema = byLocalName(atts, "noNamespaceSchemaLocation");
            return;
        }
        if (!schema.endsWith("Amplitude.Unity.Simulation.SimulationDescriptor.xsd")) {
            return;
        }
        switch (localName) {
            case "SimulationDescriptor":
                type = byLocalName(atts, "Type");
                if ("Class".equals(type)) {
                    type = byLocalName(atts, "Name");
                }
                sd = new SimulationDescriptor(atts.getValue("Name"), atts.getValue("Type"), source, map, validate);
                return;
            case "Property":
                sd.addProperty(byLocalName(atts, "Name"));
                return;
            case "Modifier":
            case "BinaryModifier":
                var path = type;
                if (null != byLocalName(atts, "Path")) {
                    path = byLocalName(atts, "Path");
                }
                path = path.replaceAll("!", "");
                while (path.contains(",")) {
                    var position = path.lastIndexOf(",");
                    var path2 = path.substring(position + 1).trim();
                    sd.addReference(path2, byLocalName(atts, "Targetproperty"));
                    path = path.substring(0, position);
                }
                sd.addReference(path, byLocalName(atts, "TargetProperty"));
                return;
            default:
        }
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
        if (sd != null && "SimulationDescriptor".equals(localName)) {
            map.put(sd.id(), sd);
            sd = null;
        }
    }
}
