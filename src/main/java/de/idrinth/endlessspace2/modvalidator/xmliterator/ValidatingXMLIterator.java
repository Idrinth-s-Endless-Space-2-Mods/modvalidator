package de.idrinth.endlessspace2.modvalidator.xmliterator;

import de.idrinth.endlessspace2.modvalidator.ExternalReferences;
import de.idrinth.endlessspace2.modvalidator.SimulationDescriptorReference;
import de.idrinth.endlessspace2.modvalidator.SimulationDescriptors;
import de.idrinth.endlessspace2.modvalidator.TextOutputLogger;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.SAXException;

public class ValidatingXMLIterator extends BaseXMLIterator {
    private final File root;
    private final HashSet<SimulationDescriptorReference> references;
    public ValidatingXMLIterator(File root, HashSet<SimulationDescriptorReference> references) {
        this.root = root;
        this.references = references;
    }
    @Override
    protected void handleFile(File file, File source, TextOutputLogger logger, SimulationDescriptors simulationDescriptors, ExternalReferences externals)
    {
        var finder = new SAXSchemaFinder();
        parseXML(
            file,
            logger,
            finder,
            new SAXSimulationDescriptorReferenceFinder(references, externals, file),
            new SAXSimulationDescriptorFinder(simulationDescriptors, file, true)
        );
        validateXMLSchema(finder.value(), file, logger);
    }
    private void validateXMLSchema(String xsdPath, File xmlPath, TextOutputLogger logger){
        if (null == xsdPath || xsdPath.isEmpty()) {
            logger.error(xmlPath, "schema can't be found");
            return;
        }
        var xsd = cleanPath(xsdPath, xmlPath, logger);
        logger.debug(xmlPath, "schema "+xsd);
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new StreamSource(root + "/" + xsd));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(xmlPath));
        } catch (IOException | SAXException ex) {
            logger.error(xmlPath, ex);
        }
    }
    private String cleanPath(String xsdPath, File xmlPath, TextOutputLogger logger) {
        if (xsdPath.startsWith("Schemas")) {
            return xsdPath;
        }
        logger.warn(xmlPath, "schema starts with a none-existing path: "+xsdPath);
        var pos = xsdPath.indexOf("Schemas");
        return xsdPath.substring(pos > 0 ? pos : 0);
    }
}
