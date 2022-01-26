public class AddCommand extends Command {
    Task task;
    AddCommand(Task task) {
        this.task = task;
    }
    @Override
    public boolean exec(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(this.task);
        storage.saveAddedTask(this.task);
        return true;
    }
}