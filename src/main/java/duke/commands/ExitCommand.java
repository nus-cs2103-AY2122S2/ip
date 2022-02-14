package duke.commands;

import duke.system.Storage;
import duke.system.TaskList;
import duke.system.Ui;

/**
 * The ExitCommand class contains the basic
 * behaviours of an exit command.
 *
 * @author  Melvin Chan Zijun
 */
public class ExitCommand extends Command {
    /**
     * Overrides method in parent class.
     * Executes the exit command, and returns message to
     * let user know that execution was successful.
     *
     * @param tasks duke's task list
     * @param ui duke's ui
     * @param storage duke's storage
     * @return String message of successful execution
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showExit();
    }
}
