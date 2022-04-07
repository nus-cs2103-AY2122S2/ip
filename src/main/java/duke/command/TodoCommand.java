package duke.command;

import duke.exception.DukeException;
import duke.exception.EmptyDescriptionException;
import duke.task.Task;
import duke.task.Todo;
import duke.tasklist.TaskList;

/**
 * Represents a command to add todo task.
 */
public class TodoCommand extends Command {
    private Task task;

    /**
     * Creates a TodoCommand with todo task.
     *
     * @param description Description of todo task.
     * @throws DukeException If description is empty.
     */
    public TodoCommand(String description) throws DukeException {
        super(description);
        if (description == "") {
            throw new EmptyDescriptionException("OOPS!!! The description of a todo cannot be empty.");
        } else {
            task = new Todo(description);
        }
    }

    /**
     * Executes TodoCommand to add todo task to a TaskList.
     *
     * @param taskList TaskList for command to add Task to.
     * @return a String when command is called.
     */
    @Override
    public String callCommand(TaskList taskList) {
        return taskList.addTask(this.task);
    }
}
