package duke.commands;

import duke.exceptions.DukeException;
import duke.Storage;
import duke.TaskManager;
import duke.Ui;

/**
 * Represents a command to mark a task.
 */
public abstract class MarkCommand extends Command {
    public MarkCommand(String userInput) {
        super(userInput);
    }

    /**
     * Obtains an instance of a Command from a string.
     * This returns a command to mark a task as done or not done.
     *
     * @param userInput User input to mark a specified task as done or not done.
     * @return Returns a MarkDoneCommand or MarkUndoneCommand.
     * @see MarkDoneCommand
     * @see MarkUndoneCommand
     */
    public static MarkCommand of(String userInput) {
        if (userInput.startsWith("mark")) {
            return new MarkDoneCommand(userInput);
        } else {
            return new MarkUndoneCommand(userInput);
        }
    }

    public abstract String execute(Storage storage, Ui ui, TaskManager taskManager) throws DukeException;
}
