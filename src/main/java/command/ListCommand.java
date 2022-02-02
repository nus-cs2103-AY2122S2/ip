package command;

import main.Storage;
import main.TaskList;
import main.TessUi;

/**
 * Represent a command to list all tasks.
 * @author Fan Jue
 * @version 0.1.0
 * @since 0.1.0
 */
public class ListCommand extends Command {

    ListCommand(String[] cmdArr) {
        super(cmdArr[0]);
    }

    /**
     * Execute the command on the system to list all current tasks.
     *
     * @param taskList The list of all current tasks.
     * @param ui The user interface.
     * @param storage The memory storage.
     */
    @Override
    public void execute(TaskList taskList, TessUi ui, Storage storage) {
        ui.listTasks(taskList.toString());
    };
}
