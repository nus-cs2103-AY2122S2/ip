package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * An instance of HelpCommand
 */
public class HelpCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showHelp();
    }
}
