package de.idrinth.endlessspace2.modvalidator.logger;

import java.io.File;
import java.io.IOException;

public class CmdOutputLogger implements Logger {
    private boolean hasErrors = false;
    protected static String path(File file) {
        try {
            return file.getCanonicalPath();
        } catch (IOException ex) {
            return file.getAbsolutePath();
        }
    }
    protected final String root;
    protected final String FORMAT = "[%s] %s: %s\n";

    public CmdOutputLogger(String root) {
        this.root = root;
    }
    public CmdOutputLogger(File root) {
        this.root = path(root);
    }

    public boolean errored()
    {
        return hasErrors;
    }
    protected void error(String context, String msg) {
        hasErrors = true;
        log(System.Logger.Level.ERROR, "ERROR", context.replace(root, ""), msg);
    }

    @Override
    public void warn(File context, String msg) {
        log(System.Logger.Level.WARNING, "WARNING", path(context).replace(root, ""), msg);
    }

    @Override
    public void error(File context, String msg) {
        error(path(context).replace(root, ""), msg);
    }

    @Override
    public void error(File context, Throwable thrown) {
        error(context, thrown.getMessage());
    }

    protected void debug(String context, String msg) {
        log(System.Logger.Level.DEBUG, "DEBUG", context, msg);
    }

    @Override
    public void debug(File context, String msg) {
        debug(path(context).replace(root, ""), msg);
    }

    @Override
    public void info(String msg) {
        log(System.Logger.Level.INFO, "INFO", "PROCESS", msg);
    }

    private void log(System.Logger.Level level, String format, Object... params) {
        log(level, String.format(FORMAT, format, params[0], params[1]));
    }

    protected void log(System.Logger.Level level, String out) {
        System.out.printf(out);
    }
}
