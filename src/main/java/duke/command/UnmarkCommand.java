package duke.command;

import duke.exception.DukeException;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

import duke.task.Task;

/**
 * Command to mark a task in the task list as incomplete.
 */
public class UnmarkCommand extends Command {

    int index;

    /**
     * Constructor to mark a task as incomplete.
     * @param index the index of the task to be marked as incomplete.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /** {@inheritDoc} */
    @Override
    public boolean exec(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task unmarkedTask = taskList.unmark(index);
        storage.saveUpdatedTask(index, unmarkedTask);
        return true;
    }
}
