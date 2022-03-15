package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    @Override
    public String execute(TaskList taskList, Storage storage) {
        return Ui.showHelpPage();
    }
}
