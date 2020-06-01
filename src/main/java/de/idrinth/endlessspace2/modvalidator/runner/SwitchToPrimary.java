package de.idrinth.endlessspace2.modvalidator.runner;

import de.idrinth.endlessspace2.modvalidator.App;
import de.idrinth.endlessspace2.modvalidator.DataTransferHelper;
import de.idrinth.endlessspace2.modvalidator.TextOutputLogger;
import java.io.IOException;
import javafx.application.Platform;

public class SwitchToPrimary implements Runnable {

    private final TextOutputLogger logger;

    public SwitchToPrimary(TextOutputLogger logger) {
        this.logger = logger;
    }

    @Override
    public void run() {
        Platform.runLater(() -> {
            try {
                App.toPrimary();
            } catch (IOException ex) {
                logger.error(DataTransferHelper.instance().gameDir(), ex);
            }
        });
    }
}
