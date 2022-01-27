package command;

import exception.DukeException;
import storage.Storage;
import task.Task;
import task.TaskList;
import task.Todo;
import ui.Ui;

/**
 * Represents a type of Command - Todo.
 * Processes, stores and saves the todo task.
 */
public class TodoCommand extends Command {

    protected Task todo;

    /**
     * Class constructor.
     * Instantiates a new instance of todo.
     *
     * @param description Description of todo
     */
    public TodoCommand(String description) {
        this.todo = new Todo(description);
    }

    /**
     * Adds the todo task to the TaskList instance and saves the new list of tasks.
     * Displays the necessary bot response.
     *
     * @param tasks TaskList which stores the list of tasks
     * @param ui Ui to display necessary responses
     * @param storage Storage to perform caching features
     * @throws DukeException Throws exception related commands
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.add(todo);
            storage.store(tasks);
            ui.showResponseMessage("todo");
            ui.showTaskMessage(todo);
            ui.printTasksCountMessage(tasks);
        } catch (DukeException e) {
            // e.printStackTrace();
        }
    }

}
