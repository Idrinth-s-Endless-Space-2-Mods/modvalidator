package de.idrinth.endlessspace2.modvalidator;

import java.io.File;
import java.io.IOException;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.SAXException;

final class XMLIterator extends SimpleXMLIterator {
    private final File root;
    public XMLIterator(File root) {
        super(true);
        this.root = root;
    }
    @Override
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
}
