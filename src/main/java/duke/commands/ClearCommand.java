package duke.commands;

import duke.exceptions.DukeException;
import duke.system.Storage;
import duke.system.TaskList;
import duke.system.Ui;

/**
 * The ClearCommand class contains the basic
 * behaviour of a clear command.
 *
 * @author  Melvin Chan Zijun
 */
public class ClearCommand extends Command {
    /**
     * Overrides method in parent class.
     * Executes the clear command, saves the data and returns
     * a message to let user know that execution was
     * successful.
     *
     * @param tasks duke's task list
     * @param ui duke's ui
     * @param storage duke's storage
     * @return String message of either successful or
     *                unsuccessful execution
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.clear();
            storage.save(tasks);
            return ui.showClear();
        } catch (DukeException e) {
            return ui.showException(e);
        }
    }
}
