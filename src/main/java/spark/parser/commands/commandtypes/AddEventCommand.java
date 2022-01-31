package spark.parser.commands.commandtypes;

import spark.exceptions.SparkException;
import spark.parser.params.AddEventParams;
import spark.storage.Storage;
import spark.tasks.TaskList;
import spark.Ui;

import java.time.LocalDateTime;

/**
 * Represents a command to add a new event to the task list.
 */
public class AddEventCommand extends Command {
    private String title;
    private LocalDateTime at;

    /**
     * Creates a new Event with the specified title and date.
     *
     * @param params contains the title and date the event should have
     */
    public AddEventCommand(AddEventParams params) {
        this.title = params.getTitle();
        this.at = params.getAt();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.addEvent(title, at);
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
