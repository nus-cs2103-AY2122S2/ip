public class MarkCommand extends Command{
    private int taskNumber;

    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidTaskNumberException{
        if (taskNumber > taskList.numOfTasks()) {
            throw new InvalidTaskNumberException();
        }
        taskList.markTask(taskNumber);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
