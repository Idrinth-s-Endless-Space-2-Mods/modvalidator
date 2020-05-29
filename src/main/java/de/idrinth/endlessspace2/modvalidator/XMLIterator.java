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

public class XMLIterator {
    public void run(File source, TextOutputLogger logger) {
        for(File file : source.listFiles()) {
            if (file.isDirectory()) {
                run(file, logger);
            } else if (file.isFile() && file.getName().endsWith(".xml")) {
                logger.debug(file, "starting");
                var schema = parseXMLForSchema(file, logger);
                if (null != schema) {
                    logger.debug(file, "schema "+schema);
                    validateXMLSchema(schema, file, logger);
                }
                logger.debug(file, "done");
            }
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
    private void validateXMLSchema(String xsdPath, File xmlPath, TextOutputLogger logger){
        
        try(var schemaSource = XMLIterator.class.getResourceAsStream(xsdPath)) {
            if (null == schemaSource) {
                logger.error(xmlPath, "failed to load xsd "+xsdPath);
                return;
            }
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new StreamSource(schemaSource));
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
                if (atts.getLocalName(i).equals("noNamespaceSchemaLocation")) {
                    schema = "/de/idrinth/endlessspace2/modvalidator/"+atts.getValue(i).replace("../", "");
                }
            }
        }
        @Override
        public void endDocument() throws SAXException {}
    }
}
