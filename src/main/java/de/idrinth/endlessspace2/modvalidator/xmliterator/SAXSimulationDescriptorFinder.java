package de.idrinth.endlessspace2.modvalidator.xmliterator;

import de.idrinth.endlessspace2.modvalidator.SimulationDescriptor;
import de.idrinth.endlessspace2.modvalidator.SimulationDescriptors;
import java.io.File;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

class SAXSimulationDescriptorFinder extends DefaultHandler {

    private SimulationDescriptor sd;
    private final SimulationDescriptors map;
    private String type;
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
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        switch (localName) {
            case "SimulationDescriptor":
                type = atts.getValue("Type");
                if ("Class".equals(type)) {
                    type = atts.getValue("Name");
                }
                sd = new SimulationDescriptor(atts.getValue("Name"), atts.getValue("Type"), source, map, validate);
                return;
            case "Property":
                sd.addProperty(atts.getValue("Name"));
                return;
            case "Modifier":
            case "BinaryModifier":
                var path = type;
                if (null != atts.getValue("Path")) {
                    path = atts.getValue("Path");
                }
                path = path.replaceAll("!", "");
                while (path.contains(",")) {
                    var position = path.lastIndexOf(",");
                    var path2 = path.substring(position + 1).trim();
                    sd.addReference(path2, atts.getValue("TargetProperty"));
                    path = path.substring(0, position);
                }
                sd.addReference(path, atts.getValue("TargetProperty"));
                return;
            default:
        }
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
        if ("SimulationDescriptor".equals(localName)) {
            map.put(sd.id(), sd);
            sd = null;
        }
    }
}
