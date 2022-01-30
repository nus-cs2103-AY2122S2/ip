package athena.commands;

import athena.exceptions.InputErrorCode;
import athena.exceptions.InputException;
import athena.tasks.TaskList;
import athena.ui.Ui;

public class UnmarkCommand extends Command {
    private final int taskNumber;

    // Assume that provided taskNumber has been checked for validity.
    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

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
