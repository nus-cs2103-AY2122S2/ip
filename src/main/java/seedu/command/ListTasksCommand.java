package seedu.command;

import seedu.duke.Storage;
import seedu.duke.TaskList;
import seedu.exception.DukeException;

/**
 * Retrieves and lists all tasks in existing task list for user.
 */
public class ListTasksCommand extends Command {

    public ListTasksCommand() {
    }

    /**
     * Executes the list tasks command to display all tasks in existing task list.
     * Checks that the task list and storage passed in are valid not null.
     *
     * @param taskList Current list of tasks.
     * @param storage Storage object to write tasks back to.
     * @return List of all tasks in existing task list.
     */
    @Override
    public String run(TaskList taskList, Storage storage) throws DukeException {
        assert taskList != null : "ListTasksCommand->run: Task list cannot be null.";
        assert storage != null : "ListTasksCommand->run: Storage cannot be null.";

        return "Here are the tasks in your list:\n" + taskList;
    }
}
