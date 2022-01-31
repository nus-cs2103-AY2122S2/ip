package spark.parser.commands.commandtypes;

import java.time.LocalDateTime;

import spark.Ui;
import spark.exceptions.SparkException;
import spark.parser.params.AddDeadlineParams;
import spark.storage.Storage;
import spark.tasks.TaskList;

/**
 * Represents a command to add a new deadline to the task list.
 */
public class AddDeadlineCommand extends Command {
    private String title;
    private LocalDateTime by;
    private String responseMessage;

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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.addDeadline(title, by);
            storage.writeTasksFile(tasks.encodeTasks());
            responseMessage = getAddTaskSuccessMessage(tasks);
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

    private String getAddTaskSuccessMessage(TaskList tasks) {
        return String.format("Okay! I've added this task:\n   %s", tasks.getLastAddedTask());
    }
}
