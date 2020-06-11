package de.idrinth.endlessspace2.modvalidator.xmliterator;

import de.idrinth.endlessspace2.modvalidator.ExternalReferences;
import de.idrinth.endlessspace2.modvalidator.SimulationDescriptors;
import de.idrinth.endlessspace2.modvalidator.logger.Logger;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

abstract class BaseXMLIterator {
    protected void parseXML(File xmlPath, Logger logger, ContentHandler ...handlers) {
        try(var input = new FileInputStream(xmlPath)) {
            var spf = SAXParserFactory.newInstance();
            spf.setNamespaceAware(true);
            var xmlReader = spf.newSAXParser().getXMLReader();
            xmlReader.setContentHandler(new MultiEventHandler(handlers));
            xmlReader.parse(new InputSource(input));
        } catch (IOException|ParserConfigurationException|SAXException ex) {
            logger.error(xmlPath, ex);
        }
    }
    protected abstract void handleFile(File xml, File source, Logger logger, SimulationDescriptors simulationDescriptors, ExternalReferences externals);
    public void run(File source, Logger logger, SimulationDescriptors simulationDescriptors, ExternalReferences externals)
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
