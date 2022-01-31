package spark.parser.commands.commandtypes;

import spark.Ui;
import spark.exceptions.formatexceptions.UnrecognisedCommandException;
import spark.storage.Storage;
import spark.tasks.TaskList;

/**
 * Represents a command to tell Spark to alert the user
 * that Spark does not know how to process the given input command.
 */
public class UnrecognisedCommand extends Command {
    private String responseMessage;

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        UnrecognisedCommandException e = new UnrecognisedCommandException();
        responseMessage = e.getMessage();
        ui.printException(e);
        return responseMessage;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
