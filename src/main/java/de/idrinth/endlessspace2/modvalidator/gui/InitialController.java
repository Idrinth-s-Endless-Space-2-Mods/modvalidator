package de.idrinth.endlessspace2.modvalidator.gui;

import de.idrinth.endlessspace2.modvalidator.logger.TextOutputLogger;
import de.idrinth.endlessspace2.modvalidator.runner.Initial;
import de.idrinth.endlessspace2.modvalidator.runner.SwitchToPrimary;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class InitialController extends ThreaddedController {
    @FXML
    private TextField endlessSpaceFolder;
    @FXML
    private void start() {
        var logger = new TextOutputLogger(endlessSpaceFolder.getText(), new TAWrapper(output));
        execute(new Initial(endlessSpaceFolder.getText(), logger));
        execute(new SwitchToPrimary(logger));
    }
}
