package echo.command;

import java.io.IOException;

import echo.main.EchoException;
import echo.storage.Storage;
import echo.task.TaskList;
import echo.ui.Ui;

/**
 * This class inherits from the Command class and encapsulates the delete command.
 */
public class DeleteCommand extends Command {

    /** String that represents the delete command. */
    public static final String COMMAND = "delete";

    /** Task index to delete. */
    private final int TASK_INDEX;

    /**
     * Constructor for DeleteCommand.
     *
     * @param taskIndex Task index to delete.
     */
    public DeleteCommand(int taskIndex) {
        this.TASK_INDEX = taskIndex;
    }

    /**
     * Executes command.
     *
     * @param tasks TaskList containing list of tasks.
     * @param ui Ui that deals with user interactions.
     * @param storage Storage deals with loading tasks from the file and saving tasks in the file.
     *
     * @throws EchoException If input is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EchoException {
        try {
            String taskStatus = tasks.taskStatus(TASK_INDEX);
            tasks.delete(TASK_INDEX);
            ui.showDelete(taskStatus, tasks.size());
            storage.save(tasks);
        } catch (IndexOutOfBoundsException e) {
            // Task number provided by user does not exist in tasks.
            throw new EchoException(String.format("Task %d does not exist!", TASK_INDEX + 1));
        } catch (IOException e) {
            throw new EchoException("Unable to access folder: " + storage.filePath());
        }
    }
}
