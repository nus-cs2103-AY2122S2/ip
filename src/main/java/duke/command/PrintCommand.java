package duke.command;

import duke.exception.DukeException;
import duke.exception.ErrorMessage;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A class that represents a command to print deadlines/events
 * occurring on a specific date.
 */
public class PrintCommand implements Command {
    private final String dateStr;

    /**
     * Constructor to initialize an instance of PrintCommand class
     * with specified date.
     *
     * @param dateStr Specified date
     */
    public PrintCommand(String dateStr) {
        this.dateStr = dateStr;
    }

    /**
     * Checks if the command is an Exit command.
     *
     * @return False as the command is a Print command
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command of printing deadlines/events in the task
     * list that occurs on the specified date.
     *
     * @param taskList Task list
     * @param ui An object to handle I/O operations
     * @param storage An object to handle file operations
     * @throws DukeException If the List of tasks in the task list
     * is empty or if there are no deadlines/events that occurs on
     * the specified date
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (taskList.getNumOfTasks() == 0) {
            throw new DukeException(ErrorMessage.ERROR_NO_TASKS_IN_LIST.toString());
        }

        taskList.setFilteredTasksByDate(dateStr);

        if (taskList.getNumOfFilteredTasks() == 0) {
            // Reset the List of filteredTasks when PrintCommand is executed
            // and there are no deadlines/events on the specified date
            // This will clear the List of filteredTasks
            taskList.resetFilteredTasks();

            throw new DukeException(ErrorMessage.ERROR_NO_TASKS_ON_DATE.toString());
        }

        String response = ui.tasksOnDateMessage(taskList, dateStr);
        ui.displayResponse(response);
    }
}
