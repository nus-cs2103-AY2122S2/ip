package echo.command;

import java.io.IOException;

import echo.storage.Storage;
import echo.task.TaskList;
import echo.ui.Ui;
import echo.utils.EchoException;

/**
 * This class inherits from the Command class and encapsulates the unmark command.
 */
public class UnmarkCommand extends Command {

    /** String that represents the unmark command. */
    public static final String COMMAND = "unmark";

    /** Task index to unmark. */
    private final int TASK_INDEX;

    /**
     * Constructor for UnmarkCommand.
     *
     * @param taskIndex Task index to Unmark.
     */
    public UnmarkCommand(int taskIndex) {
        this.TASK_INDEX = taskIndex;
    }

    /**
     * Executes command.
     *
     * @param tasks TaskList containing list of tasks.
     * @param ui Ui that deals with user interactions.
     * @param storage Storage deals with loading tasks from the file and saving tasks in the file.
     *
     * @return String message representing command successful execution.
     *
     * @throws EchoException If input is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws EchoException {
        try {
            tasks.unmark(TASK_INDEX);
            storage.save(tasks);
            return ui.getUnmarkMessage(tasks.taskStatus(TASK_INDEX));
        } catch (IndexOutOfBoundsException e) {
            // Task number provided by user does not exist in tasks.
            throw new EchoException(String.format("Task %d does not exist!", TASK_INDEX + 1));
        } catch (IOException e) {
            throw new EchoException("Unable to access folder: " + storage.filePath());
        }
    }
}
