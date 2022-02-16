package duke.command;

import duke.DukeException;
import duke.common.TaskType;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.TextUi;

/**
 * A class that find tasks object from a task list.
 */
public class SortTaskCommand extends Command {
    private static final boolean IS_EXIT = false;
    private final TaskType taskType;

    /**
     * Creates a SortTaskCommand instance with a task type for sorting.
     *
     * @param taskType The task type used for sorting.
     */
    public SortTaskCommand(TaskType taskType) {
        super(IS_EXIT);
        this.taskType = taskType;
    }

    /**
     * Sorts the task object from the task list based on task type
     * and show the sorted task list.
     *
     * @param tasks The current task list.
     * @param storage The current storage of Duke.
     * @return All the filtered sortable tasks objects formatted for the GUI message.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        try {
            TaskList filteredTasks = tasks.sortTasks(taskType);
            return TextUi.showTasks(filteredTasks);
        } catch (DukeException e) {
            return TextUi.showError(e.getMessage());
        }
    }
}
