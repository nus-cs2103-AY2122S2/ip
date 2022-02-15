package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents delete command.
 * Inherits from Command.
 */
public class DeleteCommand extends Command {
    private final int deleteIndex;

    /**
     * Returns a delete command with index.
     *
     * @param deleteIndex index to be delete.
     */
    public DeleteCommand(int deleteIndex) {
        super();
        this.deleteIndex = deleteIndex;
    }

    /**
     * Changes tasks after delete.
     * Updates the storage.
     *
     * @param tasks   the entire TaskList.
     * @param ui      the ui interface and messages.
     * @param storage the storage operations.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task delete = tasks.getByIndex(deleteIndex);
        tasks = tasks.remove(deleteIndex);
        storage.saveTaskList(tasks);
        ui.showMessage("Noted. I've removed this task: \n        "
                + delete + "\n    Now you have " + tasks.getSize() + " tasks in the list.");
    }
}
