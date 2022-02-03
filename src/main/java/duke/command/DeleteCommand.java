package src.main.java.duke.command;

import src.main.java.duke.DukeException;
import src.main.java.duke.Storage;
import src.main.java.duke.Ui;
import src.main.java.duke.task.Task;
import src.main.java.duke.TaskList;

/**
 * DeleteCommand is a Command that deletes the task at the index specified from
 * the program.
 */
public class DeleteCommand extends Command {
    private int idx;

    /**
     * Constructor for DeleteCommand that takes in the index of the task to be
     * deleted from the program.
     * 
     * @param idx index of task to be deleted
     */
    public DeleteCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Execute function for DeleteCommand deletes the indexed task from task list,
     * storage file and updates the user when the task is deleted.
     * 
     * @param tasks   task list local to user
     * @param ui      ui instance local to user
     * @param storage storage instance local to user
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task temp = tasks.delete(idx);
        storage.updateAfterDelete(idx);
        ui.deleteMessage(temp, tasks.length());
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
