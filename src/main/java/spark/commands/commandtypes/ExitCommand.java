package spark.commands.commandtypes;

import spark.Ui;
import spark.storage.Storage;
import spark.tasks.TaskList;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printMessage("Cool, see you around!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
