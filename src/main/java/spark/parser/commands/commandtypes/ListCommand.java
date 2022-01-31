package spark.parser.commands.commandtypes;

import spark.Ui;
import spark.storage.Storage;
import spark.tasks.TaskList;

/**
 * Represents a command for Spark to list all Tasks in the task-list.
 */
public class ListCommand extends Command {
    private String responseMessage;

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        responseMessage = tasks.getTaskList();
        ui.printMessage(responseMessage);
        return responseMessage;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
