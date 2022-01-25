public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws FileSaveException {
        taskList.addTask(task);
        ui.displayAddedTask(task);
        ui.displayNumberOfTasks(taskList.getTasks());
        storage.write(taskList.getTasks());
    }
}
