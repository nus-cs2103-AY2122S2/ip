package duke.command;

import duke.exception.DukeException;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    int index;

    /**
     * Constructor for the delete command.
     * @param index the index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /** {@inheritDoc} */
    @Override
    public boolean exec(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.deleteTask(index);
        storage.saveUpdatedTask(index, null);
        return true;
    }
}
