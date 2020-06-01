package de.idrinth.endlessspace2.modvalidator;

import java.io.File;
import java.io.IOException;
import java.lang.System.Logger;
import java.util.Enumeration;
import java.util.ResourceBundle;

class TextOutputLogger implements Logger {

    private final String root;
    private final TAWrapper output;
    private final String FORMAT = "[%s] %s: %s\n";

    public TextOutputLogger(File root, TAWrapper output) {
        this(path(root), output);
    }
    public TextOutputLogger(String root, TAWrapper output) {
        this.root = root == null ? "" :  root;
        output.clear();
        this.debug(root, "Root set");
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

    private static final String path(File file)
    {
        try {
            return file.getCanonicalPath();
        } catch (IOException ex) {
            return file.getAbsolutePath();
        }
    }
    private void error(String context, String msg) {
        log(Level.ERROR, new RB(), "ERROR", context.replace(root, ""), msg);
    }

    public void warn(File context, String msg) {
        log(Level.WARNING, new RB(), "WARNING", path(context).replace(root, ""), msg);
    }

    public void error(File context, String msg) {
        error(path(context).replace(root, ""), msg);
    }

    public void error(File context, Throwable thrown) {
        error(context, thrown.getMessage());
    }

    private void debug(String context, String msg) {
        log(Level.DEBUG, new RB(), "DEBUG", context, msg);
    }

    public void debug(File context, String msg) {
        debug(path(context).replace(root, ""), msg);
    }

    public void info(String msg) {
        log(Level.INFO, new RB(), "INFO", "PROCESS", msg);
    }

    @Override
    public void log(Level level, ResourceBundle bundle, String msg, Throwable thrown) {
        log(level, bundle, FORMAT, "", msg);
        log(level, bundle, FORMAT, "", thrown.getMessage());
    }

    @Override
    public void log(Level level, ResourceBundle bundle, String format, Object... params) {
        var out = String.format(FORMAT, format, params[0], params[1]);
        System.out.printf(out);
        if (level == Level.DEBUG) {
            return;
        }
        output.append(out);
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
