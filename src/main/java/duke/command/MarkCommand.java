package duke.command;

import java.util.Map;

import duke.exception.DukeException;
import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

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
    public boolean exec(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Map.Entry<Task, String> markedTask = taskList.mark(index);
        ui.print(markedTask.getValue());
        storage.saveUpdatedTask(index, markedTask.getKey());
        return true;
    }
}
