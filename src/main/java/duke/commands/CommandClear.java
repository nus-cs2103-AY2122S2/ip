package duke.commands;

import duke.exceptions.DukeException;
import duke.system.Storage;
import duke.system.TaskList;
import duke.system.Ui;

/**
 * The CommandUnmark class contains the basic
 * behaviour of a Clear Command.
 *
 * @author  Melvin Chan Zijun
 */
public class CommandClear extends Command {
    /**
     * Overrides method in parent class.
     * This method models the execution of an Clear Command.
     * The TaskList empties itself out, the Storage saves it
     * and the Ui lets the user know that the task was
     * executed successfully.
     *
     * @param tasks - for TaskList empty out
     * @param ui - to let user know that execution was successful
     * @param storage - to save updated TaskList
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
