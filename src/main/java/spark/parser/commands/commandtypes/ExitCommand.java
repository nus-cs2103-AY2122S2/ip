package spark.parser.commands.commandtypes;

import java.util.ArrayList;
import java.util.List;

import spark.commandresponse.CommandResponse;
import spark.commandresponse.ExitResponse;
import spark.storage.Storage;
import spark.tasks.TaskList;

/**
 * Represents a command for Spark to stop running.
 */
public class ExitCommand extends Command {
    private String responseMessage;

    @Override
    public List<CommandResponse> execute(TaskList tasks, Storage storage) {
        List<CommandResponse> responses = new ArrayList<>();

        responses.add(new ExitResponse());

        return responses;
    }
}
