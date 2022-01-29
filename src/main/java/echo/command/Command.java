package echo.command;

import echo.main.EchoException;
import echo.storage.Storage;
import echo.task.TaskList;
import echo.ui.Ui;


/**
 * This class encapsulates a command to be executed.
 */
public abstract class Command {

    /**
     * Executes command.
     *
     * @param tasks TaskList containing list of tasks.
     * @param ui Ui that deals with user interactions.
     * @param storage Storage deals with loading tasks from the file and saving tasks in the file.
     *
     * @throws EchoException If input is invalid.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws EchoException;

}
