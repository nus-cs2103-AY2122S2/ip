package spark.parser.commands.commandtypes;

import java.util.ArrayList;
import java.util.List;

import spark.Ui;
import spark.commandresponse.CommandResponse;
import spark.commandresponse.ErrorResponse;
import spark.commandresponse.SuccessResponse;
import spark.exceptions.SparkException;
import spark.storage.Storage;
import spark.tasks.TaskList;

/**
 * Represents a command to mark a Task as incomplete.
 */
public class UnMarkCommand extends Command {
    private int index;
    private String responseMessage;

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
    public List<CommandResponse> execute(TaskList tasks, Ui ui, Storage storage) {
        List<CommandResponse> responses = new ArrayList<>();

        try {
            tasks.unMarkTask(index);
            storage.writeTasksFile(tasks.encodeTasks());
            responseMessage = getModifyTaskSuccessMessage(tasks);

            responses.add(new SuccessResponse(responseMessage));
        } catch (SparkException e) {
            responses.add(new ErrorResponse(e));
        }

        return responses;
    }

    private String getModifyTaskSuccessMessage(TaskList tasks) {
        return String.format("Okay! I've unmarked this task:\n   %s", tasks.getLastModifiedTask());
    }
}
