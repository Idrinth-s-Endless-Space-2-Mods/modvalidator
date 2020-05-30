package de.idrinth.endlessspace2.modvalidator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

class SimpleXMLIterator {
    private final boolean validate;

    protected SimpleXMLIterator(boolean validate) {
        this.validate = validate;
    }
    public SimpleXMLIterator() {
        this(false);
    }
    
    public void run(File source, TextOutputLogger logger, SimulationDescriptors simulationDescriptors)
    {
        for(File file : source.listFiles()) {
            if (file.isDirectory()) {
                run(file, logger, simulationDescriptors);
            } else if (file.isFile() && file.getName().endsWith(".xml")) {
                logger.debug(file, "starting");
                var schema = parseXMLForSchema(file, logger);
                if (null != schema) {
                    if (schema.startsWith("Documentation")) {
                        logger.warn(file, "schema starts with a none-existing path: "+schema);
                        schema = schema.substring(13);
                    }
                    logger.debug(file, "schema "+schema);
                    validateXMLSchema(schema, file, logger);
                    if (schema.equals("Schemas/Amplitude.Unity.Simulation.SimulationDescriptor.xsd")) {
                        parseXMLForSimluationDescriptors(file, logger, simulationDescriptors);
                    }
                }
                logger.debug(file, "done");
            }
        }
    }
    private void parseXMLForSimluationDescriptors(File xmlPath, TextOutputLogger logger, SimulationDescriptors simulationDescriptors) {
        try(var input = new FileInputStream(xmlPath)) {
            var spf = SAXParserFactory.newInstance();
            spf.setNamespaceAware(true);
            var schema = new SAXSimulationDescriptorFinder(simulationDescriptors, xmlPath, validate);
            var saxParser = spf.newSAXParser();
            var xmlReader = saxParser.getXMLReader();
            xmlReader.setContentHandler(schema);
            xmlReader.parse(new InputSource(input));
        } catch (IOException|ParserConfigurationException|SAXException ex) {
            logger.error(xmlPath, ex);
        }
    }
    private String parseXMLForSchema(File xmlPath, TextOutputLogger logger) {
        try(var input = new FileInputStream(xmlPath)) {
            var spf = SAXParserFactory.newInstance();
            spf.setNamespaceAware(true);
            var schema = new SAXSchemaFinder();
            var saxParser = spf.newSAXParser();
            var xmlReader = saxParser.getXMLReader();
            xmlReader.setContentHandler(schema);
            xmlReader.parse(new InputSource(input));
            return schema.value();
        } catch (IOException|ParserConfigurationException|SAXException ex) {
            logger.error(xmlPath, ex);
        }
        return null;
    }
    protected void validateXMLSchema(String xsdPath, File xmlPath, TextOutputLogger logger){}
    private class SAXSchemaFinder extends DefaultHandler {
        private String schema;
        public String value() {
            return schema;
        }
        @Override
        public void startDocument() throws SAXException {
            schema = null;
        }
        @Override
        public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
            if (schema != null) {
                return;
            }
            for (int i=0;i<atts.getLength();i++) {
                if (atts.getLocalName(i).equals("noNamespaceSchemaLocation")) {
                    schema = atts.getValue(i).replace("../", "");
                }
            }
        }
        @Override
        public void endDocument() throws SAXException {}
    }
    private class SAXSimulationDescriptorFinder extends DefaultHandler {
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
                    sd = new SimulationDescriptor(atts.getValue("Name"), source, validate);
                    type = atts.getValue("Type");
                    if (type.equals("Class")) {
                        type = atts.getValue("Name");
                    }
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
                        var path2 = path.substring(path.indexOf(",")+1).trim();
                        sd.addReference(path2, atts.getValue("TargetProperty"));
                        path = path.substring(0, path.lastIndexOf(","));
                    }
                    sd.addReference(path, atts.getValue("TargetProperty"));
            }
        }
        @Override
        public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
            if (localName.equals("SimulationDescriptor")) {
                map.put(sd.id(), sd);
                sd = null;
            }
        }
        @Override
        public void endDocument() throws SAXException {}
    }
}
