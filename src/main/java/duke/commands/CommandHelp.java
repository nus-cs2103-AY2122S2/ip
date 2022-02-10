package duke.commands;

import duke.system.Storage;
import duke.system.TaskList;
import duke.system.Ui;

/**
 * The CommandUnmark class contains the basic
 * behaviour of a Help Command.
 *
 * @author  Melvin Chan Zijun
 */
public class CommandHelp extends Command {
    /**
     * Overrides method in parent class.
     * This method models the execution of a Help Command.
     * The Ui prints the tutorial.
     *
     * @param tasks - not used
     * @param ui - to print the tutorial
     * @param storage - not used
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showTutorial();
    }
}
