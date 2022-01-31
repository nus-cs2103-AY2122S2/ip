package spark.parser.commands.commandtypes;

import spark.Ui;
import spark.exceptions.SparkException;
import spark.storage.Storage;
import spark.tasks.TaskList;

/**
 * Represents a command to delete a Task.
 */
public class DeleteTaskCommand extends Command {
    private int index;
    private String responseMessage;

    /**
     * Creates a command with the index of the Task to be deleted.
     *
     * @param index a positive integer identifying the Task to
     *              be deleted.
     */
    public DeleteTaskCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.deleteTask(index);
            storage.writeTasksFile(tasks.encodeTasks());
            responseMessage = getDeleteTaskSuccessMessage(tasks);
            ui.printMessageWithDivider(responseMessage);
            return responseMessage;
        } catch (SparkException e) {
            ui.printException(e);
            return e.getMessage();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    private String getDeleteTaskSuccessMessage(TaskList tasks) {
        return String.format("Okay! I've removed this task:\n   %s", tasks.getLastDeletedTask());
    }
}
