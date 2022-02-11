package athena.commands;

import athena.exceptions.InputErrorCode;
import athena.exceptions.InputException;
import athena.tasks.TaskList;
import athena.ui.Messages;

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
     * TaskList and returns the outputs.
     *
     * @param taskList TaskList instance to mark the task as not done on.
     * @return Command output.
     * @throws InputException If given task number is invalid/out of range.
     */
    @Override
    public String execute(TaskList taskList) throws InputException {
        if (!taskList.isValidTaskNumber(taskNumber)) {
            throw new InputException(InputErrorCode.INVALID_TASK_NUMBER);
        }
        taskList.setTaskAsNotDone(taskNumber);
        return Messages.getUnmarkTaskDialog(taskList, taskNumber);
    }
}
