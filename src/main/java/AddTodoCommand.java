public class AddTodoCommand extends Command {
    private static final boolean IS_EXIT = false;
    private String description;

    public AddTodoCommand(String description) {
        super(IS_EXIT);
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ToDo newTodo = new ToDo(description);
        tasks.add(newTodo);
        String message = String.format("%s\n  %s\nThere are %d tasks in the burning list.",
                Ui.ADD_MESSAGE, newTodo, tasks.getSize());
        ui.appendMessage(message);
    }
}
