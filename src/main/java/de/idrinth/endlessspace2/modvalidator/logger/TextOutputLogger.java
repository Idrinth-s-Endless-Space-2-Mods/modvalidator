package de.idrinth.endlessspace2.modvalidator.logger;

import de.idrinth.endlessspace2.modvalidator.gui.TAWrapper;
import java.io.File;

public class TextOutputLogger extends CmdOutputLogger {
    private final TAWrapper output;
    public TextOutputLogger(File root, TAWrapper output) {
        super(root);
        output.clear();
        this.output = output;
    }
    public TextOutputLogger(String root, TAWrapper output) {
        super(root);
        output.clear();
        this.output = output;
    }
    @Override
    protected void log(System.Logger.Level level, String out) {
        super.log(level, out);
        if (level == System.Logger.Level.DEBUG) {
            return;
        }
        output.append(out);
    }
}
