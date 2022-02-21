package seedu.command;

import seedu.duke.Storage;
import seedu.duke.TaskList;
import seedu.exception.DukeException;

/**
 * Finds and displays tasks containing the keyword provided by the user.
 */
public class FindTasksCommand extends Command {
    private final String keyword;

    public FindTasksCommand(String keyword) {
        assert keyword != null : "FindTasksCommand->FindTasksCommand: Keyword cannot be null.";
        assert keyword.length() > 0 : "FindTasksCommand->FindTasksCommand: Keyword cannot be empty.";

        this.keyword = keyword;
    }

    /**
     * Executes the find tasks command to find all tasks containing the given keyword.
     * Checks that the task list and storage passed in are valid not null.
     *
     * @param taskList Current list of tasks.
     * @param storage Storage object to write tasks back to.
     * @return List of tasks that have been found containing the keyword (if any).
     * @throws DukeException If task list of found tasks to be returned and displayed is not created successfully.
     */
    @Override
    public String run(TaskList taskList, Storage storage) throws DukeException {
        assert taskList != null : "FindTasksCommand->run: Tasks list cannot be null.";
        assert storage != null : "FindTasksCommand->run: Storage cannot be null.";

        return "Here are the matching tasks in your list:\n" + taskList.findTasks(keyword).toString();
    }
}
