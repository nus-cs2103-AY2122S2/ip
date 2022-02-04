package duke.command;

import java.util.Map;

import duke.exception.DukeException;
import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;

/**
 * Command to mark a task in the task list as incomplete.
 */
public class UnmarkCommand extends Command {

    private int index;

    /**
     * Constructor to mark a task as incomplete.
     * @param index the index of the task to be marked as incomplete.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /** {@inheritDoc} */
    @Override
    public String exec(TaskList taskList, Storage storage) throws DukeException {
        Map.Entry<Task, String> unmarkedTask = taskList.unmark(index);
        storage.saveUpdatedTask(index, unmarkedTask.getKey());
        return unmarkedTask.getValue();
    }
}
