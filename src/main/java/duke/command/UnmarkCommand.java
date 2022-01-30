package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Encapsulates a command that marks a task as not done.
 */
public class UnmarkCommand extends Command {

    private final int index;

    /**
     * Initialises am UnmarkCommand instance.
     *
     * @param index the index of the task to be unmarked (starting from 1).
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the task specified by this
     * UnmarkCommand's index as not done.
     *
     * @param taskList the task list to execute this command on.
     * @param ui the user interface of Duke.
     * @param storage the storage of Duke.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = taskList.getTask(index);
        task.markAsNotDone();
        ui.showTaskUnmarked(task);
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
