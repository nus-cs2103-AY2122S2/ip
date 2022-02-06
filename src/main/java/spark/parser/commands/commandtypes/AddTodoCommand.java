package spark.parser.commands.commandtypes;

import java.util.ArrayList;
import java.util.List;

import spark.Ui;
import spark.commandresponse.CommandResponse;
import spark.commandresponse.ErrorResponse;
import spark.commandresponse.SuccessResponse;
import spark.exceptions.SparkException;
import spark.parser.params.AddTodoParams;
import spark.storage.Storage;
import spark.tasks.TaskList;

/**
 * Represents a command to add a new deadline to the task list.
 */
public class AddTodoCommand extends Command {
    private String title;
    private String responseMessage;

    /**
     * Creates a new Todo with the specified title.
     *
     * @param params contains the title the Todo should have.
     */
    public AddTodoCommand(AddTodoParams params) {
        this.title = params.getTitle();
    }

    @Override
    public List<CommandResponse> execute(TaskList tasks, Ui ui, Storage storage) {
        List<CommandResponse> responses = new ArrayList<>();

        try {
            tasks.addTodo(title);
            storage.writeTasksFile(tasks.encodeTasks());
            responseMessage = getAddTaskSuccessMessage(tasks);

            responses.add(new SuccessResponse(responseMessage));
        } catch (SparkException e) {
            responses.add(new ErrorResponse(e));
        }

        return responses;
    }

    private String getAddTaskSuccessMessage(TaskList tasks) {
        return String.format("Okay! I've added this task:\n   %s", tasks.getLastAddedTask());
    }
}


