
public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task, String[] commandArray) {
        super(commandArray);
        this.task = task;
    }
    @Override
    public void executeCommand(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(task);
        storage.save(taskList);
    }
}
