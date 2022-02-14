package duke.commands;

import duke.system.Storage;
import duke.system.TaskList;
import duke.system.Ui;

/**
 * The HelpCommand class contains the basic
 * behaviour of a help command.
 *
 * @author  Melvin Chan Zijun
 */
public class HelpCommand extends Command {
    /**
     * Overrides method in parent class.
     * Executes the help command, and returns list of
     * commands and their usages.
     *
     * @param tasks duke's task list
     * @param ui duke's ui
     * @param storage duke's storage
     * @return String command list
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showTutorial();
    }
}
