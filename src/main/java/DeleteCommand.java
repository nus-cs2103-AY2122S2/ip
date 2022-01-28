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
        if (taskNum > 0 && taskNum <= taskList.getNumOfTasks()) {
            Task taskDeleted = taskList.deleteTask(taskNum);

            String response = ui.taskDeletedMessage(taskDeleted)
                    + System.lineSeparator()
                    + ui.numOfTasksInListMessage(taskList);
            ui.displayResponse(response);

            storage.saveTasksToFile(taskList);
        } else {
            throw new DukeException("Task not found. Please try again!");
        }
    }
}
