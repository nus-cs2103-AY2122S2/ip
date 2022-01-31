package spark.parser.commands.commandtypes;

import spark.Ui;
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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.addTodo(title);
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


