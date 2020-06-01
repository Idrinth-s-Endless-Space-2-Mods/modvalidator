package de.idrinth.endlessspace2.modvalidator.xmliterator;

import de.idrinth.endlessspace2.modvalidator.ExternalReferences;
import de.idrinth.endlessspace2.modvalidator.SimulationDescriptors;
import de.idrinth.endlessspace2.modvalidator.TextOutputLogger;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

abstract class BaseXMLIterator {
    private final boolean validate;
    public BaseXMLIterator(boolean validate) {
        this.validate = validate;
    }
    protected void parseXMLForSimluationDescriptors(File xmlPath, TextOutputLogger logger, SimulationDescriptors simulationDescriptors) {
        try(var input = new FileInputStream(xmlPath)) {
            var spf = SAXParserFactory.newInstance();
            spf.setNamespaceAware(true);
            var handler = new SAXSimulationDescriptorFinder(simulationDescriptors, xmlPath, validate);
            var saxParser = spf.newSAXParser();
            var xmlReader = saxParser.getXMLReader();
            xmlReader.setContentHandler(handler);
            xmlReader.parse(new InputSource(input));
        } catch (IOException|ParserConfigurationException|SAXException ex) {
            logger.error(xmlPath, ex);
        }
    }
    protected String parseXMLForSchema(File xmlPath, TextOutputLogger logger) {
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
    protected abstract void handleFile(File xml, File source, TextOutputLogger logger, SimulationDescriptors simulationDescriptors, ExternalReferences externals);
    public void run(File source, TextOutputLogger logger, SimulationDescriptors simulationDescriptors, ExternalReferences externals)
    {
        var files = source.listFiles();
        if (null == files) {
            logger.error(source, "Failed to get children");
            return;
        }
        for(File file : source.listFiles()) {
            if (file.isDirectory()) {
                run(file, logger, simulationDescriptors, externals);
            } else if (file.isFile() && file.getName().endsWith(".xml")) {
                logger.debug(file, "starting");
                handleFile(file, source, logger, simulationDescriptors, externals);
                logger.debug(file, "done");
            }
        }
    }
}
