package seedu.commands;

import seedu.storage.TaskList;

/**
 * The List Command
 */
public class ListCommand extends Command {

    @Override
    public void input(String input) {
        // not in use
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
