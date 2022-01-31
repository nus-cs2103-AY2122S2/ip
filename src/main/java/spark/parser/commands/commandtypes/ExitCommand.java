package spark.parser.commands.commandtypes;

import spark.Ui;
import spark.storage.Storage;
import spark.tasks.TaskList;

/**
 * Represents a command for Spark to stop running.
 */
public class ExitCommand extends Command {
    private String responseMessage;

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        responseMessage = "Cool, see you around!";
        ui.printMessageWithDivider(responseMessage);
        return responseMessage;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
