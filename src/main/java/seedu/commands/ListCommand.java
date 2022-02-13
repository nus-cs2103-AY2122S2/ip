package seedu.commands;

import seedu.duke.DukeException;
import seedu.storage.TaskList;

/**
 * The List Command
 */
public class ListCommand extends Command {

    @Override
    public void input(String input) throws DukeException {
        if (!input.equals("")) {
            throw new DukeException("You have added unnecessary instructions in your list command.");
        }
    }

    /**
     * Gets the all the tasks from the task list
     *
     * @param tasks The task list in question
     * @return The task list in String form
     */
    @Override
    public String execute(TaskList tasks) {
        return tasks.toString();
    }
}
