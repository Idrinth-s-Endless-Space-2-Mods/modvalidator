package de.idrinth.endlessspace2.modvalidator.logger;

import java.io.File;

public interface Logger {

    void debug(File context, String msg);

    void error(File context, String msg);

    void error(File context, Throwable thrown);

    void info(String msg);

    void warn(File context, String msg);
    
}
