package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Abstract class that represents a command given by the user, which is performed upon calling execute().
 */
abstract public class Command {
    /**
     * Executes the command
     * @param taskList The list of tasks
     * @param ui The UI object responsible for user interaction
     * @param storage The Storage object responsible for saving the change
     * @throws DukeException If an error is encountered during execution
     */
    abstract public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    /**
     * Indicates whether the command is an exit command or not.
     * @return True if the command is an exit command and false otherwise
     */
    public boolean isExit() {
        return false;
    }
}
