package duke.command;

import duke.storage.Storage;
import duke.task.Priority;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents mark command.
 * Inherits from Command.
 */
public class PriorityCommand extends Command {
    private final int priorityIndex;
    private final Priority priority;

    /**
     * Returns a priority command with index.
     *
     * @param priorityIndex index to be priority.
     */
    public PriorityCommand(int priorityIndex, Priority priority) {
        super();
        this.priorityIndex = priorityIndex;
        this.priority = priority;
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
            tasks = tasks.set(priorityIndex, tasks.getByIndex(priorityIndex).setPriority(priority));
            storage.saveTaskList(tasks);
            ui.showMessage("Priority of this task change to:\n        "
                    + tasks.getByIndex(priorityIndex));
        } catch (IndexOutOfBoundsException | NumberFormatException ex) {
            ui.showInvalidIndex();
        }
    }
}
