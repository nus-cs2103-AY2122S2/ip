package spark.parser.commands.commandtypes;

import spark.storage.Storage;
import spark.tasks.TaskList;
import spark.Ui;

/**
 * Represents a command for Spark to list all Tasks in the task-list.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printMessage(tasks.getTaskList());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
