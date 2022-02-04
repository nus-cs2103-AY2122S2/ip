package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;

/**
 * Command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructor for the delete command.
     * @param index the index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /** {@inheritDoc} */
    @Override
    public String exec(TaskList taskList, Storage storage) throws DukeException {
        String printStr = taskList.deleteTask(index);
        storage.saveUpdatedTask(index, null);
        return printStr;
    }

    @Override
    public boolean shouldAbort() {
        return false;
    }
}
