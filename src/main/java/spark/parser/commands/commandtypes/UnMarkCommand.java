package spark.parser.commands.commandtypes;

import spark.Ui;
import spark.exceptions.SparkException;
import spark.storage.Storage;
import spark.tasks.TaskList;

/**
 * Represents a command to mark a Task as incomplete.
 */
public class UnMarkCommand extends Command {
    private int index;

    /**
     * Creates a command with the index of the Task to be marked as incomplete.
     *
     * @param index a positive integer identifying the Task to
     *              be marked as incomplete.
     */
    public UnMarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.unMarkTask(index);
            storage.writeTasksFile(tasks.encodeTasks());
            ui.printMessageWithDivider(getModifyTaskSuccessMessage(tasks));
        } catch (SparkException e) {
            ui.printException(e);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    private String getModifyTaskSuccessMessage(TaskList tasks) {
        return String.format("Okay! I've unmarked this task:\n   %s", tasks.getLastModifiedTask());
    }
}
