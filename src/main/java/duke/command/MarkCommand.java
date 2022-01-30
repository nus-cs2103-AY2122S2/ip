package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Encapsulates a command to mark a task as done.
 */
public class MarkCommand extends Command {

    private final int index;

    /**
     * Initialises a MarkCommand instance.
     *
     * @param index the index of the task to be marked (starting from 1).
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the task specified by this
     * MarkCommand's index as done.
     *
     * @param taskList the task list to execute this command on.
     * @param ui the user interface of Duke.
     * @param storage the storage of Duke.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = taskList.getTask(index);
        task.markAsDone();
        ui.showTaskMarked(task);
        storage.saveData(taskList, ui);
    }

    /**
     * Checks whether the Duke application should exit after this command.
     *
     * @return true iff this Command is a ByeCommand instance.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
