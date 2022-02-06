package command;

import exception.DukeException;
import storage.Storage;
import task.Task;
import task.TaskList;
import task.ToDo;
import ui.Response;
import ui.Ui;

/**
 * Represents a type of Command - Todo.
 * Processes, stores and saves the todo task.
 */
public class TodoCommand extends Command {

    protected Task toDo;

    /**
     * Class constructor.
     * Instantiates a new instance of todo.
     *
     * @param description Description of todo
     */
    public TodoCommand(String description) {
        this.toDo = new ToDo(description);
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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            tasks.add(toDo);
            storage.store(tasks);
            this.response = new Response(ui.getResponseMessage("todo"),
                    ui.getTaskMessage(toDo),
                    ui.getTasksCountMessage(tasks));
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
    }

}
