package de.idrinth.endlessspace2.modvalidator.xmliterator;

import de.idrinth.endlessspace2.modvalidator.ExternalReferences;
import de.idrinth.endlessspace2.modvalidator.SimulationDescriptors;
import de.idrinth.endlessspace2.modvalidator.logger.Logger;
import java.io.File;

public class InitialLoadXMLIterator extends BaseXMLIterator {
    @Override
    protected void handleFile(File file, File source, Logger logger, SimulationDescriptors simulationDescriptors, ExternalReferences externals) {
        parseXML(
            file,
            logger,
            new SAXSimulationDescriptorFinder(simulationDescriptors, file, false)
        );
    }
    public void run(File source, Logger logger, SimulationDescriptors simulationDescriptors) {
        run(source, logger, simulationDescriptors, null);
    }
}
