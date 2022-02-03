package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.util.Storage;

/**
 * Add todo command.
 *
 * <p>Extends from TaskCommand, as this class adds new todo task
 * to the task list</>
 */
public class TodoCommand extends TaskCommand {

    /**
     * Constructor for todo command to init values.
     *
     * <p>Calls superclass TaskCommand constructor.</>
     *
     * @param key
     */
    public TodoCommand(String key) {
        super(key);
    }

    /**
     * Execution behavior of the add todo command.
     *
     * <p>Create and add new todo task into the task list base on what the user inputs.
     * Will also print the new todo task created through the UI.</>
     *
     * @param input User input
     * @param taskList User tasklist.
     * @param storage Storage to store the updated tasklist.
     * @return New todo task added string description.
     * @throws DukeException If no task descriptor
     */
    @Override
    public String execute(String input, TaskList taskList, Storage storage) throws DukeException {
        Task newTask = null;
        String taskDescription = getTaskDescription(input);

        newTask = new Todo(taskDescription);
        return updateTaskList(newTask, taskList, storage);
    }
}
