package athena.commands;

import athena.exceptions.InputErrorCode;
import athena.exceptions.InputException;
import athena.tasks.TaskList;
import athena.ui.Ui;

/**
 * Represents an unmark command given to Athena by the user. When executed, marks
 * the task corresponding to the given task number as not done.
 */
public class UnmarkCommand extends Command {
    private final int taskNumber;

    /**
     * Constructs a new UnmarkCommand instance with the given task number.
     *
     * @param taskNumber Number of the task to be marked as not done.
     */
    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Marks the task corresponding to the given task number as not done on the given
     * TaskList and display outputs to the given Ui.
     *
     * @param ui Ui instance to display outputs through.
     * @param taskList TaskList instance to mark the task as not done on.
     * @throws InputException If given task number is invalid/out of range.
     */
    @Override
    public void execute(Ui ui, TaskList taskList) throws InputException {
        if (taskList.isValidTaskNumber(taskNumber)) {
            taskList.markTaskAsNotDone(taskNumber);
            ui.sayText("Alright, I've marked the following task as not done:");
            ui.showTask(taskNumber);
        } else {
            throw new InputException(InputErrorCode.INVALID_TASK_NUMBER);
        }
    }
}
