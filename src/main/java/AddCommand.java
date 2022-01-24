public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        storage.append(task.getAppendData());
        return "Task added:\n" + ui.tab(task.toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
