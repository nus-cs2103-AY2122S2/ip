package spark.parser.commands.commandtypes;

import java.util.List;

import spark.Ui;
import spark.commandresponse.CommandResponse;
import spark.storage.Storage;
import spark.tasks.TaskList;

/**
 * Represents a command that can be executed by Spark.
 * All valid commands are sub-classes of this class.
 */
public abstract class Command {
    public abstract List<CommandResponse> execute(TaskList tasks, Ui ui, Storage storage);
}
