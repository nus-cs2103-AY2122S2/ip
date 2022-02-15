package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents mark command.
 * Inherits from Command.
 */
public class MarkCommand extends Command {
    private final int markIndex;

    /**
     * Returns a mark command with index.
     *
     * @param markIndex index to be mark.
     */
    public MarkCommand(int markIndex) {
        super();
        this.markIndex = markIndex;
    }

    /**
     * Changes one task of tasks after mark.
     * Updates the storage.
     *
     * @param tasks   the entire TaskList.
     * @param ui      the ui interface and messages.
     * @param storage the storage operations.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks = tasks.set(markIndex, tasks.getByIndex(markIndex).mark());
            storage.saveTaskList(tasks);
            ui.showMessage("Nice! I've marked this task as done:\n        "
                    + tasks.getByIndex(markIndex));
        } catch (IndexOutOfBoundsException | NumberFormatException ex) {
            ui.showInvalidIndex();
        }
    }
}
