public class AddCommand extends Command{
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.add(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
