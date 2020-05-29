package de.idrinth.endlessspace2.modvalidator;

import java.io.File;
import java.lang.System.Logger;
import java.util.Enumeration;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.scene.control.TextArea;

public final class TextOutputLogger implements Logger {

    private final File root;
    private final TextArea output;
    private final String FORMAT = "[%s] %s: %s\n";

    public TextOutputLogger(File root, TextArea output) {
        this.root = root;
        this.debug(root.getAbsolutePath(), "Root set");
        this.output = output;
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public boolean isLoggable(Level level) {
        return true;
    }

    private void error(String context, String msg) {
        log(Level.ERROR, new RB(), "ERROR", context, msg);
    }

    public void error(File context, String msg) {
        error(context.getAbsolutePath().replace(root.getAbsolutePath(), ""), msg);
    }

    public void error(File context, Throwable thrown) {
        error(context, thrown.getMessage());
    }

    private void debug(String context, String msg) {
        log(Level.DEBUG, new RB(), "DEBUG", context, msg);
    }

    public void debug(File context, String msg) {
        debug(context.getAbsolutePath().replace(root.getAbsolutePath(), ""), msg);
    }

    @Override
    public void log(Level level, ResourceBundle bundle, String msg, Throwable thrown) {
    }
    public void destruct() {
        
    }

    @Override
    public void log(Level level, ResourceBundle bundle, String format, Object... params) {
        var out = String.format(FORMAT, format, params[0], params[1]);
        System.out.printf(out);
        if (!format.equals("ERROR")) {
            return;
        }
        Platform.runLater(new LogEntry(out, output));
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

    private class RB extends ResourceBundle {

        @Override
        protected Object handleGetObject(String key) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Enumeration<String> getKeys() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }
}
