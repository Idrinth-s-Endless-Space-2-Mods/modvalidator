package de.idrinth.endlessspace2.modvalidator.xmliterator;

import de.idrinth.endlessspace2.modvalidator.ExternalReference;
import de.idrinth.endlessspace2.modvalidator.ExternalReferences;
import de.idrinth.endlessspace2.modvalidator.SimulationDescriptorReference;
import de.idrinth.endlessspace2.modvalidator.SimulationDescriptors;
import de.idrinth.endlessspace2.modvalidator.TextOutputLogger;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Stack;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ValidatingXMLIterator extends BaseXMLIterator {
    private final File root;
    private final HashSet<SimulationDescriptorReference> references;
    public ValidatingXMLIterator(File root, HashSet<SimulationDescriptorReference> references) {
        super(true);
        this.root = root;
        this.references = references;
    }
    @Override
    protected void handleFile(File file, File source, TextOutputLogger logger, SimulationDescriptors simulationDescriptors, ExternalReferences externals)
    {
        var schema = parseXMLForSchema(file, logger);
        if (null != schema) {
            if (schema.startsWith("Documentation")) {
                logger.warn(file, "schema starts with a none-existing path: "+schema);
                schema = schema.substring(13);
            }
            logger.debug(file, "schema "+schema);
            validateXMLSchema(schema, file, logger);
            parseXMLForReferences(file, logger, externals);
            if ("Schemas/Amplitude.Unity.Simulation.SimulationDescriptor.xsd".equals(schema)) {
                parseXMLForSimluationDescriptors(file, logger, simulationDescriptors);
            }
        }
    }
    private void parseXMLForReferences(File file, TextOutputLogger logger, ExternalReferences externals) {
        
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
    private class SAXSimulationDescriptorReferenceFinder extends DefaultHandler {
        private String schema;
        private Stack<String> tag;
        private String name;
        private HashSet<SimulationDescriptorReference> references;
        private ExternalReferences externals;
        private HashSet<ExternalReference> list = new HashSet<>();
        private File source;
        @Override
        public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
            for (int i=0;i<atts.getLength();i++) {
                if ("noNamespaceSchemaLocation".equals(atts.getLocalName(i))) {
                    schema = atts.getValue(i).substring(atts.getValue(i).lastIndexOf("/")+1);
                    return;
                }
            }
            if (null != atts.getValue("Name")) {
                name = atts.getValue("Name");
            }
            tag.push(localName);
            for (var reference : references) {
                if (reference.schema.equals(schema) && reference.tag.equals(localName)) {
                    for (int i=0;i<atts.getLength();i++) {
                        if (null != atts.getValue(reference.attribute)) {
                            list.add(new ExternalReference(source, localName, atts.getValue(reference.attribute)));
                        }
                    }
                }
            }
        }
        @Override
        public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
            var type = tag.pop();
            if (tag.isEmpty()) {
                externals.add(name, type, list);
                list = new HashSet<>();
            }
        }
    }
}
