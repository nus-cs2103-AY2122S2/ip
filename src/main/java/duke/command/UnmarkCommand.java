package duke.command;

import duke.exception.DukeException;
import duke.exception.ErrorMessage;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * A class that represents a command to mark a task as not done yet.
 */
public class UnmarkCommand implements Command {
    private final int taskNum;

    /**
     * Constructor to initialize an instance of UnmarkCommand class with
     * task number.
     *
     * @param taskNum Task number
     */
    public UnmarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Checks if the command is an Exit command.
     *
     * @return False as the command is a Done command
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command of marking the task in the task list as not
     * done yet and saving the updated list to the data file.
     *
     * @param taskList Task list
     * @param ui An object to handle I/O operations
     * @param storage An object to handle file operations
     * @throws DukeException If the task is not found
     * @throws IOException If the tasks cannot be saved to the data file
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        Task taskNotDone = !taskList.hasFilter()
                ? markTaskNotDoneBasedOnAllTasks(taskList)
                : markTaskNotDoneBasedOnFilteredTasks(taskList);

        String response = ui.taskNotDoneMessage(taskNotDone);
        ui.displayResponse(response);

        storage.saveTasksToFile(taskList);
    }

    /**
     * Marks the task as not done yet based on the corresponding task number
     * in the List of tasks and then returns the task.
     *
     * @param taskList Task list
     * @return The task that was marked as not done yet
     * @throws DukeException If the task is not found
     */
    public Task markTaskNotDoneBasedOnAllTasks(TaskList taskList) throws DukeException {
        if (taskNum > 0 && taskNum <= taskList.getNumOfTasks()) {
            return taskList.markNotDone(taskNum);
        } else {
            throw new DukeException(ErrorMessage.ERROR_TASK_NOT_FOUND.toString());
        }
    }

    /**
     * Marks the task as not done yet based on the corresponding task number
     * in the List of filtered tasks and then returns the task.
     *
     * @param taskList Task list
     * @return The task that was marked as not done yet
     * @throws DukeException If the task is not found
     */
    public Task markTaskNotDoneBasedOnFilteredTasks(TaskList taskList) throws DukeException {
        if (taskNum > 0 && taskNum <= taskList.getNumOfFilteredTasks()) {
            return taskList.markNotDone(taskNum);
        } else {
            throw new DukeException(ErrorMessage.ERROR_TASK_NOT_FOUND.toString());
        }
    }
}
