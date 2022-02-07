package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

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
    public boolean execute(TaskList taskList, Ui ui, Storage storage) {
        System.out.println("I don't understand your query! Please try again.");
        ui.showLine();
        return false;
    }
}
