package spark.parser.commands.commandtypes;

import spark.Ui;
import spark.exceptions.SparkException;
import spark.storage.Storage;
import spark.tasks.TaskList;

/**
 * Represents a command to mark a Task as complete.
 */
public class MarkCommand extends Command {
    private int index;
    private String responseMessage;

    /**
     * Creates a command with the index of the Task to be marked as complete.
     *
     * @param index a positive integer identifying the Task to
     *              be marked as complete.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.markTask(index);
            storage.writeTasksFile(tasks.encodeTasks());
            responseMessage = getModifyTaskSuccessMessage(tasks);
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

    private String getModifyTaskSuccessMessage(TaskList tasks) {
        return String.format("Okay! I've marked this task:\n   %s", tasks.getLastModifiedTask());
    }
}
