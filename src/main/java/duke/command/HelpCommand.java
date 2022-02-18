package duke.command;

import duke.ui.Ui;
import duke.util.Storage;
import duke.util.TaskList;

/**
 * Represents the command to list all commands.
 */
public class HelpCommand extends Command {
    /**
     * Executes the command.
     *
     * @param storage  Storage of Duke.
     * @param tasks Task list of Duke.
     * @param ui User interface of Duke.
     */
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        return ui.help();
    }

    /**
     * Indicates whether the program should stop after this command.
     *
     * @return Boolean indicating whether the program should stop after this command.
     */
    public boolean isExit() {
        return false;
    }
}
