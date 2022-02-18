package duke.commands;

import duke.Storage;
import duke.TextUi;
import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.Todo;

/**
 * Represents a command that allows user to add a todo task
 * to the task list.
 */
public class AddToDo extends Command {
    private final String taskDetails;

    /**
     * Initialize the AddToDo Command.
     *
     * @param taskDetails Description of task.
     */
    public AddToDo(String taskDetails) {
        this.taskDetails = taskDetails;
    }


    /**
     * Returns a success string after a successful execution of an add to-do command.
     *
     * @param taskList A taskList containing all existing tasks in Duke.
     * @param ui A UI object that is used to print the System's response.
     * @param storage A storage object that is able to read and write to storage file.
     * @return Message after a to-do has successfully been added to the task list.
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

    /**
     * Returns a success message after a successful undo of an AddTodo Command.
     * Throws a DukeException when there errors undoing the command.
     *
     * @param taskList A taskList containing all existing tasks in Duke.
     * @return Success message after an AddTodo command has been undone.
     * @throws DukeException if the program is unable to undo the command.
     */
    @Override
    public String undo(TaskList taskList) throws DukeException {
        return TaskList.deleteLastTask();
    }
}
