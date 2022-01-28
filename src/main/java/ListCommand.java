/**
 * A class that represents a command to list all the tasks.
 */
public class ListCommand implements Command {
    /**
     * Constructor to initialize an instance of ListCommand class.
     */
    public ListCommand() {
    }

    /**
     * Checks if the command is an Exit command.
     *
     * @return False as the command is a List command
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command of listing all the tasks in the task list.
     *
     * @param taskList Task list
     * @param ui An object to handle I/O operations
     * @param storage An object to handle file operations
     * @throws DukeException If the task list is empty
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (taskList.getNumOfTasks() != 0) {
            String response = ui.tasksInListMessage(taskList);
            ui.displayResponse(response);
        } else {
            throw new DukeException("There are no tasks in your list!");
        }
    }
}
