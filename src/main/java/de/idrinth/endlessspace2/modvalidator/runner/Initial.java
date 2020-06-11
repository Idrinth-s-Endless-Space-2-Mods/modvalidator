package de.idrinth.endlessspace2.modvalidator.runner;

import de.idrinth.endlessspace2.modvalidator.DataTransferHelper;
import de.idrinth.endlessspace2.modvalidator.SimulationDescriptors;
import de.idrinth.endlessspace2.modvalidator.data.SimulationReferenceListHelper;
import de.idrinth.endlessspace2.modvalidator.logger.Logger;
import de.idrinth.endlessspace2.modvalidator.xmliterator.InitialLoadXMLIterator;
import de.idrinth.endlessspace2.modvalidator.xmliterator.ValidatingXMLIterator;
import java.io.File;

public class Initial implements Runnable {
    private final String endlessSpaceFolder;
    private final Logger logger;

    public Initial(String endlessSpaceFolder, Logger logger) {
        this.endlessSpaceFolder = endlessSpaceFolder;
        this.logger = logger;
    }

    @Override
    public void run() {
        logger.info("checking requirements");
        if (null == endlessSpaceFolder || endlessSpaceFolder.isEmpty()) {
            logger.info("You need to provide the folder of endless space 2.");
            return;
        }
        var list = new SimulationDescriptors();
        var iterator = new InitialLoadXMLIterator();
        var simFolder = new File(endlessSpaceFolder+"/Public/Simulation");
        if (!simFolder.isDirectory()) {
            logger.info("Can't find Simulation folder at the place you pointed to.");
            return;
        }
        var schemaFolder = new File(endlessSpaceFolder+"/Public/Schemas/");
        if (!schemaFolder.isDirectory()) {
            logger.info("Can't find Schema folder at the place you pointed to.");
            return;
        }
        logger.info("reading in game dir");
        iterator.run(simFolder, logger, list);
        logger.info("done");
        DataTransferHelper.create(
            new ValidatingXMLIterator(schemaFolder.getParentFile(), SimulationReferenceListHelper.provide()),
            list,
            new File(endlessSpaceFolder+"/../../workshop/content/392110"),
            new File(endlessSpaceFolder+"/Public")
        );
    }
}
