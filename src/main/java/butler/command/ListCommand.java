package butler.command;

import butler.TaskList;
import butler.util.Storage;
import butler.util.ui.Ui;

public class ListCommand extends Command {

    /**
     * Executes command behavior.
     * In this case it lists all tasks currently in the task list in a pre-defined format.
     *
     * @param taskList A managed list of tasks.
     * @param storage A handler class that handles file I/O, storing taskList details in a file.
     * @param ui A handler class that handles user input and output.
     */
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        ui.setMessage(taskList.viewList());
    }
}
