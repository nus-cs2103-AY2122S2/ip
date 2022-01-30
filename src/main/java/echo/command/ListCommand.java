package echo.command;

import echo.storage.Storage;
import echo.task.TaskList;
import echo.ui.Ui;

/**
 * This class inherits from the Command class and encapsulates the list command.
 */
public class ListCommand extends Command {

    /** String that represents the list command. */
    public static final String COMMAND = "list";

    /**
     * Executes command.
     *
     * @param tasks TaskList containing list of tasks.
     * @param ui Ui that deals with user interactions.
     * @param storage Storage deals with loading tasks from the file and saving tasks in the file.
     *
     * @return String message representing command successful execution.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            return ui.showEmptyList();
        } else {
            return ui.showList(tasks.toString());
        }
    }
}
