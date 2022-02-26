package butler.command;

import butler.TaskList;
import butler.util.Storage;
import butler.util.ui.Ui;

public class ByeCommand extends Command {

    /**
     * Executes command behavior.
     * In this case it prints a farewell message
     *
     * @param taskList A managed list of tasks.
     * @param storage A handler class that handles file I/O, storing taskList details in a file.
     * @param ui A handler class that handles user input and output.
     */
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        ui.setMessage("Farewell Master. Glad to be of service. Closing in 2s.");
        ui.setExitStatus();
    }
}
