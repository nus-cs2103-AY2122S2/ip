package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Represents a command for exiting Duke.
 */
public class ByeCommand implements Command {

    /**
     * Constructor for a ByeCommand object.
     */
    public ByeCommand() {}

    /**
     * Executes the command.
     *
     * @param taskList the list of the tasks a user has.
     * @param ui an instance of a user interface.
     * @param storage a storage used to save the user's tasks.
     * @return a boolean indicating whether it is an exit command.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return Ui.BYE_MSG;
    }
}
