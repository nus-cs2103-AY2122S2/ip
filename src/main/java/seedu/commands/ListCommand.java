package seedu.commands;

import seedu.duke.DukeException;
import seedu.storage.TaskList;

/**
 * The List Command
 */
public class ListCommand extends Command {

    private boolean priority;

    @Override
    public void input(String input) throws DukeException {
        switch (input) {
        case "":
            priority = false;
            break;
        case "priority":
            priority = true;
            break;
        default:
            throw new DukeException("I think you misspelled 'priority'.");
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
        if (priority) {
            tasks.sort();
        }
        return tasks.toString();
    }
}
