package spark.parser.commands.commandtypes;

import java.util.ArrayList;
import java.util.List;

import spark.Ui;
import spark.commandresponse.CommandResponse;
import spark.commandresponse.ErrorResponse;
import spark.commandresponse.SuccessResponse;
import spark.commandresponse.WarningResponse;
import spark.exceptions.SparkException;
import spark.parser.params.AddTodoParams;
import spark.storage.Storage;
import spark.tasks.TaskList;

/**
 * Represents a command to add a new deadline to the task list.
 */
public class AddTodoCommand extends Command {
    private String title;

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

        boolean isDuplicate = tasks.alreadyHasTask(title);

        try {
            tasks.addTodo(title);
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


