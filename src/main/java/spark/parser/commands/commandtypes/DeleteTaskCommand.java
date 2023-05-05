package spark.parser.commands.commandtypes;

import java.util.ArrayList;
import java.util.List;

import spark.commandresponse.CommandResponse;
import spark.commandresponse.ErrorResponse;
import spark.commandresponse.SuccessResponse;
import spark.exceptions.SparkException;
import spark.storage.Storage;
import spark.tasks.TaskList;

/**
 * Represents a command to delete a Task.
 */
public class DeleteTaskCommand extends Command {
    private int index;

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
    public List<CommandResponse> execute(TaskList tasks, Storage storage) {
        List<CommandResponse> responses = new ArrayList<>();

        try {
            tasks.deleteTask(index);
            storage.writeTasksFile(tasks.encodeTasks());
            responses.add(new SuccessResponse(getDeleteTaskSuccessMessage(tasks)));
            responses.add(new SuccessResponse(getTasksRemainingMessage(tasks)));
        } catch (SparkException e) {
            responses.add(new ErrorResponse(e));
        }

        return responses;
    }

    private String getDeleteTaskSuccessMessage(TaskList tasks) {
        return String.format("Okay! I've removed this task:\n   %s", tasks.getLastDeletedTask());
    }

    private String getTasksRemainingMessage(TaskList tasks) {
        return String.format("You have %d tasks remaining", tasks.getNumberOfTasks());
    }
}
