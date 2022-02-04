package duke.command;

import java.util.Map;

import duke.exception.DukeException;
import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;

/**
 * Command to mark a task in the task list as complete.
 */
public class MarkCommand extends Command {

    private int index;

    /**
     * Constructor to mark a task as complete in the task list.
     * @param index the index of the task to be marked as complete.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /** {@inheritDoc} */
    @Override
    public String exec(TaskList taskList, Storage storage) throws DukeException {
        Map.Entry<Task, String> markedTask = taskList.mark(index);
        storage.saveUpdatedTask(index, markedTask.getKey());
        return markedTask.getValue();
    }
}
