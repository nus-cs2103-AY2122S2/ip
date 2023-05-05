package spark.parser.commands.commandtypes;

import java.util.ArrayList;
import java.util.List;

import spark.commandresponse.CommandResponse;
import spark.commandresponse.SuccessResponse;
import spark.commandresponse.WarningResponse;
import spark.storage.Storage;
import spark.tasks.TaskList;

/**
 * Represents a command for Spark to list all Tasks in the task-list.
 */
public class ListCommand extends Command {
    @Override
    public List<CommandResponse> execute(TaskList tasks, Storage storage) {
        List<CommandResponse> responses = new ArrayList<>();
        String noTasksMessage = "No tasks found! (trust me, I've looked everywhere)";

        String allTaskTitles = tasks.getTaskList();

        if (allTaskTitles.isBlank()) {
            responses.add(new WarningResponse(noTasksMessage));
        } else {
            responses.add(new SuccessResponse(allTaskTitles));
        }

        return responses;
    }
}
