package seedu.commands;

import seedu.duke.DukeException;
import seedu.storage.TaskList;

/**
 * The Bye Command
 */
public class ByeCommand extends Command {

    /**
     * Checks if the string follows the given format
     *
     * @param input The command the user entered
     * @throws DukeException The command contains extra letters
     */
    @Override
    public void validate(String input) throws DukeException {
        if (!input.equals("")) {
            throw new DukeException("Your 'bye' command is a bit too long.");
        }
    }

    /**
     * Prepares the application to be closed
     *
     * @param tasks The task list
     * @return An exit string
     */
    @Override
    public String execute(TaskList tasks) {
        // Task list is not in use in this scenario
        isExit = true;
        return "Good Bye!";
    }
}
