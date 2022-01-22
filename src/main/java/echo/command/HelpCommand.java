package echo.command;

import echo.storage.Storage;
import echo.task.TaskList;
import echo.ui.Ui;

public class HelpCommand extends Command {

    public static final String COMMAND = "help";

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showHelp();
    }
}
