package echo.command;

import echo.storage.Storage;
import echo.task.TaskList;
import echo.ui.Ui;
import echo.main.EchoException;

import java.io.IOException;

public class DeleteCommand extends Command {

    public static final String COMMAND = "delete";

    private final int TASK_INDEX;

    public DeleteCommand(int taskIndex) {
        this.TASK_INDEX = taskIndex;
    }

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
