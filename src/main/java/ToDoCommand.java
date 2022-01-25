public class ToDoCommand extends AddCommand {
    public ToDoCommand(String taskName) {
        super(taskName);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage store) throws BobException {
        Task task = new Todo(getTaskName());
        addAndUpdate(task, tasks, ui, store);
    }
}
