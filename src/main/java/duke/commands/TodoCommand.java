package duke.commands;

import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.Todo;
import duke.ui.Ui;

/**
 * Encapsulates command to create a todo task.
 */
public class TodoCommand implements Command {
    /**
     * The todo task description
     */
    private final String description;

    /**
     * Instantiates a new Todo command.
     *
     * @param description the todo task description
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList<Task> tasks, Ui ui, Storage storage) {
        Todo todo = new Todo(this.description);
        tasks.add(todo);
        ui.showMessage("Got it. I've added the to-do task:");
        ui.showTask(todo.toString());
        ui.showMessage("Now you have " + tasks.size() + " tasks in your list.");
    }
}
