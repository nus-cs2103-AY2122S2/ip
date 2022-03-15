package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Todo;

/**
 * Represents a command that creates a new Todo task.
 */
public class TodoCommand extends Command {

    public static final String COMMAND_WORD = "todo";
    private String description;

    /**
     * Creates a new TodoCommand.
     *
     * @param description Description of todo.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Creates a new todo, adds it into the user's task list, updates the storage and notifies user upon successful
     * completion.
     *
     * @param tasks List of user's tasks.
     * @param storage Handles storage of tasks in the user's memory.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        Todo todo = new Todo(description);
        tasks.addTask(todo);
        storage.save(tasks);
        return Ui.showAddTask(todo, tasks.count());
    }
}
