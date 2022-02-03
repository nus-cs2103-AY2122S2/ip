package src.main.java.duke.command;

import src.main.java.duke.DukeException;
import src.main.java.duke.Storage;
import src.main.java.duke.Ui;
import src.main.java.duke.task.Task;
import src.main.java.duke.TaskList;

/**
 * MarkCommand is a Command that marks the indexed task as done.
 */
public class MarkCommand extends Command {
    private int idx;

    /**
     * Constructor for MarkCommand takes in the index of the task to be marked as
     * done.
     * 
     * @param idx the index of the task to be marked as done
     */
    public MarkCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Execute method for MarkCommand marks the indexed task as done, stores the
     * changes in the storage list and updates the user when completed.
     * 
     * @param tasks   task list local to user
     * @param ui      ui instance local to user
     * @param storage storage instance local to user
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task temp = tasks.mark(idx);
        storage.updateAfterMark(idx);
        ui.markMessage(temp);
    }

    /**
     * isExit method checks if this is an exit command, and only returns yes for an
     * exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
