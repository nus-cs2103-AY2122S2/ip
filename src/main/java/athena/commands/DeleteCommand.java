package athena.commands;

import athena.exceptions.InputErrorCode;
import athena.exceptions.InputException;
import athena.tasks.TaskList;
import athena.ui.Ui;

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
     * Deletes the specified task from the given taskList and displays the results on the ui.
     *
     * @param ui Ui instance to display outputs through.
     * @param taskList TaskList instance to delete task from.
     * @throws InputException If the task number is out of range/invalid.
     */
    @Override
    public void execute(Ui ui, TaskList taskList) throws InputException {
        if (taskList.isValidTaskNumber(taskNumber)) {
            ui.sayText("Alright, I will delete the following task from the list:");
            ui.showTask(taskNumber);
            taskList.deleteTask(taskNumber);
            ui.showCurrentNumberOfTasks();
        } else {
            throw new InputException(InputErrorCode.INVALID_TASK_NUMBER);
        }
    }
}
