package de.idrinth.endlessspace2.modvalidator.xmliterator;

import de.idrinth.endlessspace2.modvalidator.ExternalReferences;
import de.idrinth.endlessspace2.modvalidator.SimulationDescriptors;
import de.idrinth.endlessspace2.modvalidator.TextOutputLogger;
import java.io.File;

public class InitialLoadXMLIterator extends BaseXMLIterator {
    public InitialLoadXMLIterator() {
        super(false);
    }
    @Override
    protected void handleFile(File file, File source, TextOutputLogger logger, SimulationDescriptors simulationDescriptors, ExternalReferences externals) {
        var schema = parseXMLForSchema(file, logger);
        if (null != schema) {
            if (schema.startsWith("Documentation")) {
                schema = schema.substring(13);
            }
            logger.debug(file, "schema "+schema);
            if ("Schemas/Amplitude.Unity.Simulation.SimulationDescriptor.xsd".equals(schema)) {
                parseXMLForSimluationDescriptors(file, logger, simulationDescriptors);
            }
        }
    }
    public void run(File source, TextOutputLogger logger, SimulationDescriptors simulationDescriptors) {
        run(source, logger, simulationDescriptors, null);
    }
}
