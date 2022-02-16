package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

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
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = taskList.getTask(index);
        assert task != null : "task retrieved from taskList cannot be null";

        task.markAsNotDone();
        return ui.showTaskUnmarked(task)
                + this.saveData(taskList, ui, storage);
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
