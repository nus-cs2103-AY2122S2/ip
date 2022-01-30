package athena.commands;

import athena.exceptions.InputErrorCode;
import athena.exceptions.InputException;
import athena.tasks.TaskList;
import athena.ui.Ui;

public class MarkCommand extends Command {
    private final int taskNumber;

    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(Ui ui, TaskList taskList) throws InputException {
        if (taskList.isValidTaskNumber(taskNumber)) {
            taskList.setTaskAsDone(taskNumber);
            ui.sayText("Alright, I've marked the following task as done:");
            ui.showTask(taskNumber);
        } else {
            throw new InputException(InputErrorCode.INVALID_TASK_NUMBER);
        }
    }
}
