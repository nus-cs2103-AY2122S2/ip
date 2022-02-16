package duke.command;

import duke.DukeException;
import duke.common.TaskType;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.TextUi;

public class FilterTaskCommand extends Command {
    private static final boolean IS_EXIT = false;
    private final TaskType taskType;

    /**
     * Creates a FilterTaskCommand instance with a task type for filtering.
     *
     * @param taskType The task type used for filtering.
     */
    public FilterTaskCommand(TaskType taskType) {
        super(IS_EXIT);
        this.taskType = taskType;
    }

    /**
     * Filters the task object from the task list based on task type
     * and show the filtered task list.
     *
     * @param tasks The current task list.
     * @param storage The current storage of Duke.
     * @return All the filtered tasks objects formatted for the GUI message.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        try {
            TaskList filteredTasks = tasks.filterTasks(taskType);
            return TextUi.showTasks(filteredTasks);
        } catch (DukeException e) {
            return TextUi.showError(e.getMessage());
        }
    }

}
