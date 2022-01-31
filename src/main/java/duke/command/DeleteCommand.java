package duke.command;

import duke.exception.DukeException;
import duke.exception.ErrorMessage;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * A class that represents a command to delete a task.
 */
public class DeleteCommand implements Command {
    private final int taskNum;

    /**
     * Constructor to initialize an instance of DeleteCommand class with
     * task number.
     *
     * @param taskNum Task number
     */
    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Checks if the command is an Exit command.
     *
     * @return False as the command is a Delete command
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command of deleting the task from the task list and
     * saving the updated list to the data file.
     *
     * @param taskList Task list
     * @param ui An object to handle I/O operations
     * @param storage An object to handle file operations
     * @throws DukeException If the task is not found
     * @throws IOException If the tasks cannot be saved to the data file
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        Task taskDeleted = !taskList.hasFilter()
                ? deleteTaskBasedOnAllTasks(taskList)
                : deleteTaskBasedOnFilteredTasks(taskList);

        if (taskList.getNumOfFilteredTasks() == 0) {
            // Reset the List of filteredTasks when the List is empty
            // This will clear the List of filteredTasks
            taskList.resetFilteredTasks();
        }

        String response = ui.taskDeletedMessage(taskDeleted)
                + System.lineSeparator()
                + ui.numOfTasksInListMessage(taskList);
        ui.displayResponse(response);

        storage.saveTasksToFile(taskList);
    }

    /**
     * Deletes the task based on the corresponding task number in the
     * List of tasks and then returns the deleted task.
     *
     * @param taskList Task list
     * @return The task that was deleted
     * @throws DukeException If the task is not found
     */
    public Task deleteTaskBasedOnAllTasks(TaskList taskList) throws DukeException {
        if (taskNum > 0 && taskNum <= taskList.getNumOfTasks()) {
            return taskList.deleteTask(taskNum);
        } else {
            throw new DukeException(ErrorMessage.ERROR_TASK_NOT_FOUND.toString());
        }
    }

    /**
     * Deletes the task based on the corresponding task number in the
     * List of filtered tasks and then returns the deleted task.
     *
     * @param taskList Task list
     * @return The task that was deleted
     * @throws DukeException If the task is not found
     */
    public Task deleteTaskBasedOnFilteredTasks(TaskList taskList) throws DukeException {
        if (taskNum > 0 && taskNum <= taskList.getNumOfFilteredTasks()) {
            return taskList.deleteTask(taskNum);
        } else {
            throw new DukeException(ErrorMessage.ERROR_TASK_NOT_FOUND.toString());
        }
    }
}
