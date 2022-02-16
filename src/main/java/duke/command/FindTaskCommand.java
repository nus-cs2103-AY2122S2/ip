package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.TextUi;

/**
 * A class that find tasks object from a task list.
 */
public class FindTaskCommand extends Command {
    private static final boolean IS_EXIT = false;
    private final String keywords;

    /**
     * Creates a FindTaskCommand instance with a search keyword.
     *
     * @param keyword The search keyword.
     */
    public FindTaskCommand(String keyword) {
        super(IS_EXIT);
        this.keywords = keyword;
    }

    /**
     * Searches the task object from the task list and show the filtered task list.
     *
     * @param tasks The current task list.
     * @param storage The current storage of Duke.
     * @return All the filtered tasks objects formatted for the GUI message.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        try {
            TaskList filteredTasks = tasks.filterTasks(keywords);
            return TextUi.showTasks(filteredTasks);
        } catch (DukeException e) {
            return TextUi.showError(e.getMessage());
        }
    }
}
