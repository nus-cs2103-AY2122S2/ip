package duke.command;

import duke.exception.DukeException;
import duke.manager.Storage;
import duke.manager.TaskList;
import duke.task.ToDo;

/**
 * Represents a command that will add a ToDo Task to the TaskList upon execution.
 */
public class AddToDoCommand extends Command {
    private String task;

    /**
     * A constructor to store the name of the Task.
     *
     * @param task The name of the task.
     */
    public AddToDoCommand(String task) {
        super();
        this.task = task;
    }

    /**
     * Executes the command by adding a ToDo task into the TaskList.
     *
     * @param taskList A TaskList that stores the tasks.
     * @param storage A Storage object to handle saving of data.
     * @return A String which is Duke's response.
     * @throws DukeException If there is an issue saving the tasks.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        ToDo todo = new ToDo(task);
        taskList.add(todo);
        String response = "Got it. I've added this task:" + "\n";
        response += todo.toString() + "\n";
        response += "Now you have " + taskList.numOfTasks() + " tasks in the list." + "\n";
        try {
            storage.save(taskList);
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
        return response;
    }

    /**
     * Returns true if it is an exit command and false otherwise.
     * @return a boolean.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
