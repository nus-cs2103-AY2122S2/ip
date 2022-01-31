package duke.command;

import duke.exception.DukeException;
import duke.exception.ErrorMessage;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A class that represents a command to find tasks by searching
 * for a keyword in the description.
 */
public class FindCommand implements Command {
    private final String keyword;

    /**
     * Constructor to initialize an instance of PrintCommand class
     * with keyword.
     *
     * @param keyword Keyword
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Checks if the command is an Exit command.
     *
     * @return False as the command is a Find command
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command of finding tasks in the task list that
     * contains the keyword in the description.
     *
     * @param taskList Task list
     * @param ui An object to handle I/O operations
     * @param storage An object to handle file operations
     * @throws DukeException If the List of tasks in the task list
     * is empty or if there are no tasks that contains the keyword
     * in the description
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (taskList.getNumOfTasks() == 0) {
            throw new DukeException(ErrorMessage.ERROR_NO_TASKS_IN_LIST.toString());
        }

        taskList.setFilteredTasksByKeyword(keyword);

        if (taskList.getNumOfFilteredTasks() == 0) {
            // Reset the List of filteredTasks when FindCommand is executed
            // and there are no matching tasks
            // This will clear the List of filteredTasks
            taskList.resetFilteredTasks();

            throw new DukeException(ErrorMessage.ERROR_NO_MATCHING_TASKS_IN_LIST.toString());
        }

        String response = ui.tasksWithKeywordMessage(taskList, keyword);
        ui.displayResponse(response);
    }
}
