package spark.parser.commands.commandtypes;

import java.util.ArrayList;
import java.util.List;

import spark.Ui;
import spark.commandresponse.CommandResponse;
import spark.commandresponse.ErrorResponse;
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
    public List<CommandResponse> execute(TaskList tasks, Ui ui, Storage storage) {
        List<CommandResponse> responses = new ArrayList<>();

        UnrecognisedCommandException e = new UnrecognisedCommandException();

        responses.add(new ErrorResponse(e));

        return responses;
    }
}
