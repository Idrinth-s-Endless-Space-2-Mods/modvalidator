package de.idrinth.endlessspace2.modvalidator.gui;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

public class TAWrapper {
    private final TextArea output;
    public TAWrapper(TextArea output) {
        this.output = output;
    }
    public void append(String string) {
        Platform.runLater(new LogEntry(string, output));
    }
    public void clear() {
        Platform.runLater(new LogClear(output));
    }
    private class LogEntry implements Runnable {
        private final String msg;
        private final TextArea output;
        public LogEntry(String msg, TextArea output) {
            this.msg = msg;
            this.output = output;
        }
        @Override
        public void run() {
            output.appendText(msg);
        }
    }
    private class LogClear implements Runnable {
        private final TextArea output;
        public LogClear(TextArea output) {
            this.output = output;
        }
        @Override
        public void run() {
            output.setText("");
        }
    }
}
