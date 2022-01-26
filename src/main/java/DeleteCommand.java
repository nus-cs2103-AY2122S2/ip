public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index, String[] commandArray) {
        super(commandArray);
        this.index = index;
    }

    @Override
    public void executeCommand(TaskList taskList, Ui ui, Storage storage) {
        taskList.deleteTask(index);
        storage.save(taskList);
    }
}
