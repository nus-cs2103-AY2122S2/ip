package Duke.Commands;

import Duke.System.Storage;
import Duke.System.TaskList;
import Duke.System.Ui;

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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTutorial();
    }
}
