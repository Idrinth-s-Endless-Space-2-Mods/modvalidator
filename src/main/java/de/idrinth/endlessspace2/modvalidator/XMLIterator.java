package de.idrinth.endlessspace2.modvalidator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

class XMLIterator {
    private final boolean validate;
    private final File root;
    public XMLIterator(File root, boolean validate) {
        this.validate = validate && root != null;
        this.root = root;
    }
    public XMLIterator(File root) {
        this(root, true);
    }
    public XMLIterator() {
        this(null, false);
    }
    public void run(File source, TextOutputLogger logger, SimulationDescriptors simulationDescriptors)
    {
        var files = source.listFiles();
        if (null == files) {
            logger.error(source, "Failed to get children");
            return;
        }
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
                    if (validate) {
                        validateXMLSchema(schema, file, logger);
                    }
                    if ("Schemas/Amplitude.Unity.Simulation.SimulationDescriptor.xsd".equals(schema)) {
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
    protected void validateXMLSchema(String xsdPath, File xmlPath, TextOutputLogger logger){
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new StreamSource(root + "/" + xsdPath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(xmlPath));
        } catch (IOException | SAXException ex) {
            logger.error(xmlPath, ex);
        }
    }
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
                if ("noNamespaceSchemaLocation".equals(atts.getLocalName(i))) {
                    schema = atts.getValue(i).replace("../", "");
                }
            }
        }
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
                        var path2 = path.substring(position+1).trim();
                        sd.addReference(path2, atts.getValue("TargetProperty"));
                        path = path.substring(0, position);
                    }
                    sd.addReference(path, atts.getValue("TargetProperty"));
                    return;
            }
        }
        @Override
        public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
            if ("SimulationDescriptor".equals(localName)) {
                map.put(sd.id(), sd);
                sd = null;
            }
        }
        @Override
        public void endDocument() throws SAXException {}
    }
}
