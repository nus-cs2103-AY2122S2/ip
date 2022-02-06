package spark.parser.commands.commandtypes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import spark.Ui;
import spark.commandresponse.CommandResponse;
import spark.commandresponse.ErrorResponse;
import spark.commandresponse.SuccessResponse;
import spark.commandresponse.WarningResponse;
import spark.exceptions.SparkException;
import spark.parser.params.AddEventParams;
import spark.storage.Storage;
import spark.tasks.TaskList;

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
    public List<CommandResponse> execute(TaskList tasks, Ui ui, Storage storage) {
        List<CommandResponse> responses = new ArrayList<>();

        boolean isDuplicate = tasks.alreadyHasTask(title);

        try {
            tasks.addEvent(title, at);
            storage.writeTasksFile(tasks.encodeTasks());
            responses.add(new SuccessResponse(getAddTaskSuccessMessage(tasks)));
        } catch (SparkException e) {
            responses.add(new ErrorResponse(e));
        }

        checkAndWarnUserOfDuplicateTask(responses, isDuplicate);

        return responses;
    }

    private String getAddTaskSuccessMessage(TaskList tasks) {
        return String.format("Okay! I've added this task:\n   %s", tasks.getLastAddedTask());
    }

    private void checkAndWarnUserOfDuplicateTask(List<CommandResponse> responses, boolean isDuplicate) {
        if (isDuplicate) {
            responses.add(new WarningResponse("You've previously added a Task with the same name!" + "\n"
                    + "(have you mistakenly added it twice?)"));
        }
    }
}
