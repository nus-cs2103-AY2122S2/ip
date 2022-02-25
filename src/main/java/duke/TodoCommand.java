package duke;

/**
 * Command to add todo
 */
public class TodoCommand extends Command {
    private final Todo todo;

    /**
     * Constructor for todo command
     * @param description todo description
     */
    public TodoCommand(String description) {
        this.todo = new Todo(description);
    }

    /**
     * Executes command to add todo
     * @param storage storage
     * @param tasks list of tasks
     * @param ui User interface
     */
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        tasks.add(todo);
        return ui.showTodo(this.todo, tasks);
    }

    /**
     * Boolean indicative of whether program should end
     * @return boolean indicative of whether program should end
     */
    public boolean isExit() {
        return false;
    }
}