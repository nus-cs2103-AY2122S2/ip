package athena.commands;

import athena.exceptions.InputErrorCode;
import athena.exceptions.InputException;
import athena.tasks.TaskList;
import athena.ui.Messages;

/**
 * Represents a delete command given to Athena by the user. When executed, deletes
 * the task with the given task number.
 */
public class DeleteCommand extends Command {
    private final int taskNumber;

    /**
     * Constructs a new DeleteCommand instance according to the given parameters.
     *
     * @param taskNumber The number of the task to be deleted.
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Deletes the specified task from the given taskList and returns output.
     *
     * @param taskList TaskList instance to delete task from.
     * @return Command output.
     * @throws InputException If the task number is out of range/invalid.
     */
    @Override
    public String execute(TaskList taskList) throws InputException {
        if (!taskList.isValidTaskNumber(taskNumber)) {
            throw new InputException(InputErrorCode.INVALID_TASK_NUMBER);
        }
        String preDialog = Messages.getPreDeletionDialog(taskList, taskNumber);
        taskList.deleteTask(taskNumber);
        String postDialog = Messages.getCurrentNumberOfTasksDialog(taskList);
        return Messages.getMultiLineString(preDialog, postDialog);
    }
}
