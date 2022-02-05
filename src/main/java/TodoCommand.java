public class TodoCommand extends Command {
    private final Todo todo;

    public TodoCommand(String description) {
        this.todo = new Todo(description);
    }

    public void execute(Storage storage, TaskList tasks, Ui ui) {
        tasks.add(todo);
        ui.showTodo(this.todo, tasks);
    }

    public boolean isExit() {
        return false;
    }
}