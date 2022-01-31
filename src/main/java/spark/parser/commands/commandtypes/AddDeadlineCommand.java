package spark.parser.commands.commandtypes;

import spark.exceptions.SparkException;
import spark.parser.params.AddDeadlineParams;
import spark.storage.Storage;
import spark.tasks.TaskList;
import spark.Ui;

import java.time.LocalDateTime;

/**
 * Represents a command to add a new deadline to the task list.
 */
public class AddDeadlineCommand extends Command {
    private String title;
    private LocalDateTime by;

    /**
     * Creates a new Deadline with the specified title and date.
     *
     * @param params contains the title and date the deadline should have
     */
    public AddDeadlineCommand(AddDeadlineParams params) {
        this.title = params.getTitle();
        this.by = params.getBy();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.addDeadline(title, by);
            storage.writeTasksFile(tasks.encodeTasks());
            ui.printMessageWithDivider(getAddTaskSuccessMessage(tasks));
        } catch (SparkException e) {
            ui.printException(e);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    private String getAddTaskSuccessMessage(TaskList tasks) {
        return String.format("Okay! I've added this task:\n   %s", tasks.getLastAddedTask());
    }
}
