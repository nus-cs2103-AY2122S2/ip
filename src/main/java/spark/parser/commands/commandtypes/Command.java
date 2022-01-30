package spark.parser.commands.commandtypes;

import spark.Ui;
import spark.storage.Storage;
import spark.tasks.TaskList;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public abstract boolean isExit();
}
