package de.idrinth.endlessspace2.modvalidator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public abstract class ThreaddedController implements Runnable {
    @FXML
    protected TextArea output;
    private final ExecutorService te = Executors.newSingleThreadExecutor();
    protected final void execute() {
        te.submit(this);
    }
}
