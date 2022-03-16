package duke.commands;

import duke.exceptions.OutOfRangeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Class which handles deletion of tasks in list
 */
public class DeleteCommand extends Command {
    protected Task deleted;
    private final int deleteIndex;
    private TaskList tasks;

    /**
     * Constructor for delete command
     * deletes a task based using index provided by user
     *
     * @param deleteIndex Non zeroed index of item to be deleted
     */
    public DeleteCommand(int deleteIndex) {
        this.deleteIndex = deleteIndex - 1;

    }

    /**
     * returns the modified task list after command execution
     *
     * @return TaskList
     */
    @Override
    public TaskList getList() {
        return tasks;
    }

    /**
     * returns true boolean if command execution ends program
     * @return true if it ends main program
     */
    @Override
    public boolean endsProgram() {
        return false;
    }

    /**
     * executes the delete command
     * Deletes the task from the input index
     *
     * @param tasks   tasks list to be modified
     * @param ui      to help with printing of messages
     * @param storage To deal with saving of task list
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws OutOfRangeException {
        assert tasks != null;
        this.tasks = tasks;
        if (deleteIndex >= tasks.size()) {
            throw new OutOfRangeException();
        }
        deleted = tasks.get(deleteIndex);
        this.tasks.remove(deleteIndex);
        storage.saveFile(this.tasks);
        return ui.deleteMessage(deleted, this.tasks.size());
    }
}
