package Duke.Commands;

import Duke.DukeException.DukeException;
import Duke.System.Storage;
import Duke.System.TaskList;
import Duke.System.Ui;

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
     * @throws DukeException - thrown if error saving data
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.clear();
        storage.save(tasks);
        ui.showClear();
    }
}
