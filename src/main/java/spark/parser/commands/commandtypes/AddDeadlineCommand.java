package spark.parser.commands.commandtypes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import spark.commandresponse.CommandResponse;
import spark.commandresponse.ErrorResponse;
import spark.commandresponse.SuccessResponse;
import spark.commandresponse.WarningResponse;
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
    public List<CommandResponse> execute(TaskList tasks, Storage storage) {
        List<CommandResponse> responses = new ArrayList<>();

        boolean isDuplicate = tasks.alreadyHasTask(title);

        try {
            tasks.addDeadline(title, by);
            storage.writeTasksFile(tasks.encodeTasks());
            responses.add(new SuccessResponse(getAddTaskSuccessMessage(tasks)));
            responses.add(new SuccessResponse(getTasksRemainingMessage(tasks)));
        } catch (SparkException e) {
            responses.add(new ErrorResponse(e));
        }

        checkAndWarnUserOfDuplicateTask(responses, isDuplicate);

        return responses;
    }

    private String getAddTaskSuccessMessage(TaskList tasks) {
        return String.format("Okay! I've added this task:\n   %s", tasks.getLastAddedTask());
    }
    private String getTasksRemainingMessage(TaskList tasks) {
        return String.format("You have %d tasks remaining", tasks.getNumberOfTasks());
    }


    private void checkAndWarnUserOfDuplicateTask(List<CommandResponse> responses, boolean isDuplicate) {
        if (isDuplicate) {
            responses.add(new WarningResponse("You've previously added a Task with the same name!" + "\n"
                    + "(have you mistakenly added it twice?)"));
        }
    }
}
