package duke.command;

import duke.exception.DukeException;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

import duke.task.Task;

/**
 * Command to mark a task in the task list as complete.
 */
public class MarkCommand extends Command {

    int index;

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
        Task markedTask = taskList.mark(index);
        storage.saveUpdatedTask(index, markedTask);
        return true;
    }
}
