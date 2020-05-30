package de.idrinth.endlessspace2.modvalidator;

import java.io.File;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class InitialController {
    @FXML
    private TextArea output;
    @FXML
    private TextField endlessSpaceFolder;
    @FXML
    private void start() throws IOException {
        var logger = new TextOutputLogger(endlessSpaceFolder.getText(), output);
        if (null == endlessSpaceFolder.getText() || endlessSpaceFolder.getText().isEmpty()) {
            logger.info("You need to provide the folder of endless space 2.");
            return;
        }
        var list = new SimulationDescriptors();
        var iterator = new SimpleXMLIterator();
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
        iterator.run(simFolder, logger, list);
        logger.info("done");
        Data.setIterator(new XMLIterator(schemaFolder.getParentFile()));
        Data.setRootList(list);
        Data.setWorkshopDir(new File(endlessSpaceFolder.getText()+"/../../workshop/content/392110"));
        App.toPrimary();
    }
}
