package taskmaster.commands;

import taskmaster.exception.TaskmasterExceptions;
import taskmaster.userinterface.UserInterface;
import taskmaster.util.Storage;
import taskmaster.util.TaskList;

/**
 * This class inherits from the Command class.
 * It encapsulates the bye command which the
 * user sees before the program exits.
 */
public class ByeCommands extends Commands {
    /**
     * Constructor for ByeCommands.
     */
    public ByeCommands() {
        super("");
    }
    /**
     * Execute the command.
     *
     * @param tasklist The task list that contains the task.
     *
     * @param ui The User interface.
     *
     * @param storage The file that is storing the task information.
     *
     * @return Returns a string confirmation that the task has been executed.
     *
     * @throws TaskmasterExceptions Throws an exception if execution fails.
     */
    @Override
    public String execute(TaskList tasklist, UserInterface ui, Storage storage) throws TaskmasterExceptions {
        storage.updateList(tasklist);
        return ui.getByeMessage();
    }
}
