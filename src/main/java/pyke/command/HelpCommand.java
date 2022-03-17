package pyke.command;

import pyke.ui.Ui;
import pyke.util.Storage;
import pyke.util.TaskList;

public class HelpCommand extends Command {
    /**
     * Constructs the default HelpCommand.
     *
     * @param taskList the class store the tasks info.
     * @param ui the interface for output information.
     * @param storage in charge of file IO.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.outputHelpInfo();
    }

    @Override
    public String executeUi(TaskList taskList, Ui ui, Storage storage) {
        return ui.outputUiHelp();
    }

    /**
     * Knows if this command will exit the program.
     *
     * @return true if this method will exit the program.
     */
    public boolean isExit() {
        return false;
    }
}
