package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Represents a command when the user's input is invalid.
 */
public class InvalidCommand implements Command {

    /**
     * Constructor for an InvalidCommand object.
     */
    public InvalidCommand() {}

    /**
     * Executes the command.
     *
     * @param taskList the list of the tasks a user has.
     * @param ui an instance of a user interface.
     * @param storage a storage used to save the user's tasks.
     * @return a boolean indicating whether it is an exit command.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return Ui.INVALID_MSG;
    }
}
