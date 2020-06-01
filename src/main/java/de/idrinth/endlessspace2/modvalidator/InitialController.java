package de.idrinth.endlessspace2.modvalidator;

import java.io.File;
import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class InitialController extends ThreaddedController {
    @FXML
    private TextField endlessSpaceFolder;
    @FXML
    private void start() {
        execute();
    }

    @Override
    public void run() {
        var logger = new TextOutputLogger(endlessSpaceFolder.getText(), new TAWrapper(output));
        logger.info("checking requirements");
        if (null == endlessSpaceFolder.getText() || endlessSpaceFolder.getText().isEmpty()) {
            logger.info("You need to provide the folder of endless space 2.");
            return;
        }
        var list = new SimulationDescriptors();
        var iterator = new XMLIterator();
        var simFolder = new File(endlessSpaceFolder.getText()+"/Public/Simulation");
        var schemaFolder = new File(endlessSpaceFolder.getText()+"/Public/Schemas/");
        if (!simFolder.isDirectory()) {
            logger.info("Can't find Simulation folder at the place you pointed to.");
            return;
        }
        if (!schemaFolder.isDirectory()) {
            logger.info("Can't find Schema folder at the place you pointed to.");
            return;
        }
        logger.info("reading in game dir");
        iterator.run(simFolder, logger, list);
        logger.info("done");
        var gameDir = new File(endlessSpaceFolder.getText()+"/Public");
        DataTransferHelper.create(
            new XMLIterator(schemaFolder.getParentFile()),
            list,
            new File(endlessSpaceFolder.getText()+"/../../workshop/content/392110"),
            gameDir
        );
        Platform.runLater(new SwitchToPrimary(logger, gameDir));
    }
    private class SwitchToPrimary implements Runnable {
        private final TextOutputLogger logger;
        private final File gameDir;
        public SwitchToPrimary(TextOutputLogger logger, File gameDir) {
            this.logger = logger;
            this.gameDir = gameDir;
        }
        public void run() {
            try {
                App.toPrimary();
            } catch (IOException ex) {
                logger.error(DataTransferHelper.instance().gameDir(), ex);
            }
        }
    }
}
