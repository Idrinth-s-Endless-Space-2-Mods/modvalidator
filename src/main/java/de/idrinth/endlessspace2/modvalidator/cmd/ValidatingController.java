package de.idrinth.endlessspace2.modvalidator.cmd;

import de.idrinth.endlessspace2.modvalidator.DataTransferHelper;
import de.idrinth.endlessspace2.modvalidator.logger.CmdOutputLogger;
import de.idrinth.endlessspace2.modvalidator.runner.Initial;
import de.idrinth.endlessspace2.modvalidator.runner.Validation;
import java.io.File;

public class ValidatingController {
    private final File modfolder;
    private final String gamefolder;

    public ValidatingController(String gamefolder, File modfolder) {
        this.modfolder = modfolder;
        this.gamefolder = gamefolder;
    }
    public boolean isValid() {
        var logger = new CmdOutputLogger(modfolder);
        var initial = new Initial(gamefolder, new CmdOutputLogger(modfolder));
        initial.run();
        if (logger.errored()) {
            return false;
        }
        var helper = DataTransferHelper.instance();
        var validator = new Validation(modfolder, helper.iterator(), helper.rootList(), logger);
        validator.run();
        return logger.errored();
    }
}
