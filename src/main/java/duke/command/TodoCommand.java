package duke.command;

import duke.Ui;
import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

public class TodoCommand extends Command {

    private final String description;

    /**
     * Constructs a {@code TodoCommand} object.
     * @param description the description of the todo task
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Adds a todo task into the list of tasks.
     * @param tasks current list of tasks
     * @param ui the UI used
     * @param storage the storage used
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task t = new Todo(description);
        tasks.add(t);
        ui.showMessage("Got it. I've added this task:\n" + t
                    + "\nNow you have " + tasks.size() + " tasks in your list.");
    }

}
