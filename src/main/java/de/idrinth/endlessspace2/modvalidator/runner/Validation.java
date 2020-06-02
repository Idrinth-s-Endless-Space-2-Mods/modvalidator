package de.idrinth.endlessspace2.modvalidator.runner;

import de.idrinth.endlessspace2.modvalidator.ExternalReferences;
import de.idrinth.endlessspace2.modvalidator.SimulationDescriptors;
import de.idrinth.endlessspace2.modvalidator.TextOutputLogger;
import de.idrinth.endlessspace2.modvalidator.xmliterator.ValidatingXMLIterator;
import java.io.File;

public class Validation implements Runnable {
    private final File modfolder;
    private final ValidatingXMLIterator iterator;
    private final SimulationDescriptors rootList;
    private final TextOutputLogger logger;
    public Validation(File modfolder, ValidatingXMLIterator iterator, SimulationDescriptors rootList, TextOutputLogger logger) {
        this.modfolder = modfolder;
        this.iterator = iterator;
        this.rootList = rootList;
        this.logger = logger;
    }
    @Override
    public void run() {
        if (null == modfolder) {
            logger.info("You need to choose a mod to check.");
            return;
        }
        logger.info("xsd validation");
        var list = rootList.clone();
        var externals = new ExternalReferences();
        iterator.run(modfolder, logger, list, externals);
        logger.info("logic validation");
        list.values().forEach((sd) -> {
            sd.check(logger);
        });
        logger.info("reference validation");
        externals.check(logger, list);
        logger.info("done");
    }
}
