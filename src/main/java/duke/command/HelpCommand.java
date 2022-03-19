package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Prints the help message.
 */
public class HelpCommand extends Command {

    /**
     * Prints the help message for the user.
     *
     * @param tasks TaskList that is maintained in Ducky.
     * @param ui Ui that is maintained in Ducky.
     * @param storage Storage that is maintained in Ducky.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.printHelp();
    }
}
