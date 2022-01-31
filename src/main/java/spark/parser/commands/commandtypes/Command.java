package spark.parser.commands.commandtypes;

import spark.Ui;
import spark.storage.Storage;
import spark.tasks.TaskList;

/**
 * Represents a command that can be executed by Spark.
 * All valid commands are sub-classes of this class.
 */
public abstract class Command {
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);

    public abstract boolean isExit();
}
