package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TextUi;
import tasks.Task;
import tasks.TaskList;
import tasks.Todo;

/**
 * Represents a command that allows user to add a todo task
 * to the task list
 */
public class AddToDo extends Command {
    private final String taskDetails;

    /**
     * Initialize the AddEvent Command
     * @param taskDetails description of task
     */
    public AddToDo(String taskDetails) {
        this.taskDetails = taskDetails;
    }

    /**
     * Method that executes a command to add a todo to the task list.
     * @param taskList a taskList containing all existing tasks
     * @param ui a ui object
     * @param storage a storage object that is able to read and write to storage file
     * @return message after a todo has successfully been added to the task list
     */
    @Override
    public String execute(TaskList taskList, TextUi ui, Storage storage) {
        try {
            if (taskDetails.equals("")) {
                throw new DukeException("Todo command is invalid!");
            }
            Task todo = new Todo(taskDetails);
            return TaskList.addTask(todo);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
