package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents unmark command.
 * Inherits from Command.
 */
public class UnmarkCommand extends Command {
    private final int unmarkIndex;

    /**
     * Returns an unmark command with index.
     *
     * @param unmarkIndex index to be mark.
     */
    public UnmarkCommand(int unmarkIndex) {
        super();
        this.unmarkIndex = unmarkIndex;
    }

    /**
     * Changes one task of tasks after unmark.
     * Updates the storage.
     *
     * @param tasks   the entire TaskList.
     * @param ui      the ui interface and messages.
     * @param storage the storage operations.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks = tasks.set(unmarkIndex, tasks.getByIndex(unmarkIndex).unmark());
            storage.saveTaskList(tasks);
            ui.showMessage("Nice! I've marked this task as not done yet:\n        "
                    + tasks.getByIndex(unmarkIndex));
        } catch (IndexOutOfBoundsException | NumberFormatException ex) {
            ui.showInvalidIndex();
        }
    }
}
