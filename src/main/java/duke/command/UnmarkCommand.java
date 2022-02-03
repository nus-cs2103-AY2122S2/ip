package src.main.java.duke.command;

import src.main.java.duke.DukeException;
import src.main.java.duke.Storage;
import src.main.java.duke.TaskList;
import src.main.java.duke.Ui;
import src.main.java.duke.task.Task;

/**
 * UnmarkCommand is a Command that marks the indexed task as not yet done.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Constructor for UnmarkCommand takes in the index of the task to be marked as
     * not yet done.
     * 
     * @param index the index of the task to be marked as not yet done
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the indexed task as not yet done, stores the changes in the storage
     * list and updates the user when completed.
     * 
     * @param tasks   task list local to user
     * @param ui      ui instance local to user
     * @param storage storage instance local to user
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task unmarkedTask = tasks.unmark(index);
        storage.updateAfterUnmark(index);
        ui.unmarkMessage(unmarkedTask);
    }

    /**
     * Checks if this is an exit command, and only returns true for an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
