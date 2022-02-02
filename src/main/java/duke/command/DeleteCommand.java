package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;

/**
 * Command to delete a task from a task list.
 */
public class DeleteCommand extends Command {
    private static final String NO_INPUT = "You need to put the task number you want to delete!";
    private static final String INVALID_INPUT = "Invalid input, you need to give a number/integer";

    /**
     * Delete Task Command constructor.
     *
     * @param key Keyword to call this command.
     */
    public DeleteCommand(String key) {
        super(key);
    }

    /**
     * Execution behavior of the delete command.
     *
     * <p>Delete existing task in the task list base on the task number
     * given by user.</>
     *
     * @param input User input
     * @param taskList User tasklist.
     * @param storage Storage to store the updated tasklist.
     * @param ui Duke UI to print what the command wants.
     * @throws DukeException If no task description or invalid input (User must write the task number in int) or task index does not exist.
     */
    @Override
    public void execute(String input, TaskList taskList, Storage storage, Ui ui) throws DukeException {
        int taskIndex = 0;
        String taskDesc = getTaskDescription(input, NO_INPUT);

        try {
            taskIndex = Integer.parseInt(taskDesc) - 1;
        } catch (NumberFormatException error) {
            throw new DukeException(INVALID_INPUT);
        }

        Task task = taskList.removeTask(taskIndex);
        storage.saveList(taskList.getTaskList());
        ui.printResponse(
                "Got it, task has been removed: \n"
                + task.toString() + "\nNow you have " + taskList.getTaskListSize()
                + " tasks in your list.");
    }
}
