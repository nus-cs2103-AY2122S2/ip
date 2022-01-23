public class UnmarkCommand extends Command{
    private int taskNumber;

    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidTaskNumberException{
        if (taskNumber > taskList.numOfTasks()) {
            throw new InvalidTaskNumberException();
        }
        taskList.unmarkTask(taskNumber);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}