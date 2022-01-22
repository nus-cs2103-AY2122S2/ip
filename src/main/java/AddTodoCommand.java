import java.io.IOException;

public class AddTodoCommand extends Command {
    protected String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = new Todo(description);
        tasks.addTask(task);
        ui.showAddTask(task, tasks.getTasks());
        storage.save(tasks.getTasks());
    }
}
