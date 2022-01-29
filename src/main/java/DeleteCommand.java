public class DeleteCommand extends Command {
    private final int taskNumber;

    // Assume that provided taskNumber has been checked for validity.
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

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
