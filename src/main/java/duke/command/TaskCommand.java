package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.util.Storage;

/**
 * Abstract class for add task commands.
 *
 * <p>All future commands about adding new tasks to the list should inherit from this class</p>
 */
public abstract class TaskCommand extends Command {
    private static final String TASK_DESC_EMPTY = "The description of your task cannot be empty.";

    /**
     * Constructor for command to init values.
     *
     * <p>Calls superclass Command constructor.</p>
     *
     * @param key Keyword to call this command.
     */
    public TaskCommand(String key) {
        super(key);
    }

    /**
     * Get description of the task after the command keyword.
     *
     * <p>Get task description without the command keyword.
     * Calls the super class, Command, getTaskDescriptor, but this
     * function passes the default task description empty statement.</p>
     *
     * @param input The full user input.
     * @return User input without the command's keyword.
     * @throws DukeException If task description is empty.
     */
    protected String getTaskDescription(String input) throws DukeException {
        return super.getTaskDescription(input, TASK_DESC_EMPTY);
    }

    /**
     * Add new task into task list, update save and print.
     *
     * <p>Updates the task list and output the added task through
     * the UI</p>
     *
     * @param newTask The new task to be added into the list.
     * @param taskList User tasklist.
     * @param storage Storage to store the updated tasklist.
     * @return New task added statement.
     * @throws DukeException If there are issues with saving the updated
     * list into the file.
     */
    protected String updateTaskList(Task newTask, TaskList taskList, Storage storage) throws DukeException {
        taskList.addTask(newTask);

        try {
            storage.saveList(taskList.getTaskList());
        } catch (DukeException exception) {
            return exception.getMessage() + "\n" + getUpdatedTaskListStr(newTask, taskList);
        }

        return getUpdatedTaskListStr(newTask, taskList);
    }

    private String getUpdatedTaskListStr(Task newTask, TaskList taskList) {
        return "Gotcha. Added the task: \n" + newTask.toString() + "\nNow you have "
                + taskList.getTaskListSize() + " tasks in your list.";
    }
}
