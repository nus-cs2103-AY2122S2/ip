package seedu.commands;

import seedu.duke.DukeException;
import seedu.storage.TaskList;

/**
 * The List Command
 */
public class ListCommand extends Command {

    /**
     * Checks if the string follows the given format
     *
     * @param input The command the user entered
     * @throws DukeException The command contains extra letters
     */
    @Override
    public void validate(String input) throws DukeException {
        if (!input.equals("")) {
            throw new DukeException("You have added unnecessary instructions in your list command.");
        }
    }

    /**
     * Returns the list of tasks
     *
     * @param tasks The task list
     * @return The list of tasks as a string
     */
    @Override
    public String execute(TaskList tasks) {
        return tasks.toString();
    }
}
