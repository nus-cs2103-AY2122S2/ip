package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.util.Storage;

/**
 * Command to delete a task from a task list.
 */
public class DeleteCommand extends Command {
    public static final String DELETE_COMMAND = "delete";

    private static final String NO_INPUT = "You need to put the task number you want to delete!";
    private static final String INVALID_INPUT = "Invalid input, you need to give a number/integer";

    /**
     * Delete Task Command constructor.
     */
    public DeleteCommand() {
        super(DELETE_COMMAND);
    }

    /**
     * Execution behavior of the delete command.
     *
     * <p>Delete existing task in the task list base on the task number
     * given by user.</p>
     *
     * @param input User input
     * @param taskList User tasklist.
     * @param storage Storage to store the updated tasklist.
     * @return Delete task response.
     * @throws DukeException If:
     * - No task description.
     * - Invalid input (User must write the task number in int).
     * - Task index does not exist.
     */
    @Override
    public String execute(String input, TaskList taskList, Storage storage) throws DukeException {
        int taskIndex = 0;
        String taskDesc = getTaskDescription(input, NO_INPUT);

        try {
            taskIndex = Integer.parseInt(taskDesc) - 1;
        } catch (NumberFormatException error) {
            throw new DukeException(INVALID_INPUT);
        }

        Task task = taskList.removeTask(taskIndex);
        storage.saveList(taskList.getTaskList());
        return "Got it, task has been removed: \n"
                + task.toString() + "\nNow you have " + taskList.getTaskListSize()
                + " tasks in your list.";
    }
}
