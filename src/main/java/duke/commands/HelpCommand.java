package duke.commands;

import duke.tasklist.TaskList;
import duke.ui.Ui;

public class HelpCommand extends Command {

    /**
     * Display the commands available for the user
     *
     */
    public HelpCommand() {}

    @Override
    public String execute(TaskList tasks, Ui ui) {
        return Ui.printHelpCommands();
    }
}
