public class AddTodoCommand extends Command {
    private static final boolean IS_EXIT = false;
    private final String title;

    public AddTodoCommand(String title) {
        super(IS_EXIT);
        this.title = title;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new Todo(title);
        tasks.addTask(task);
        ui.showExecutionMessage(Messages.MESSAGE_ADD_TODO, task.toString(), tasks.getSize());
    }
}
