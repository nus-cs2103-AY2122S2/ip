package duke.commands;

import duke.admin.Storage;
import duke.admin.TaskList;
import duke.admin.Ui;
import duke.exceptions.DukeException;
import duke.tasks.Task;

/**
 * DeleteCommand is a Command that deletes the task at the index specified from
 * the program.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructor for DeleteCommand that takes in the index of the task to be
     * deleted from the program.
     * @param index index of task to be deleted
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes the indexed task from task list and storage file and updates the user
     * when the task is deleted.
     * @param tasks   task list local to user
     * @param storage storage instance local to user
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        assert tasks.getNumberOfTasks() >= this.index;

        Task deletedTask = tasks.delete(index);
        storage.updateAfterDelete(index);

        return Ui.showDeletedMessage(deletedTask, tasks);
    }

    /**
     * Checks if this is an exit command, and only returns true for an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
