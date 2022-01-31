package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

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
     * @param ui the text UI of Duke.
     * @param storage the storage of Duke.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = taskList.getTask(index);
        task.markAsDone();
        return ui.showTaskMarked(task) + this.saveData(taskList, ui, storage);
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
