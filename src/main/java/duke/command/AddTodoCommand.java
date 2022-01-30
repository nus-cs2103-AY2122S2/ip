package duke.command;

import duke.main.Storage;
import duke.task.TaskList;
import duke.main.Ui;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represents add todo task command
 */
public class AddTodoCommand extends Command {
    private String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

    /**
     * Adds todo task to list
     *
     * @param tasks contains list of tasks
     * @param ui interact with user
     * @param storage save tasks to file
     * @throws DukeException if I0Exception occurs
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = new Todo(description);
        tasks.addTask(task);
        ui.showAddTask(task, tasks.getTasks());
        storage.save(tasks.getTasks());
    }
}
