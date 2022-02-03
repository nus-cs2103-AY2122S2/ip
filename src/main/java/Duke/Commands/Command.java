package Duke.Commands;

import Duke.DukeException.DukeException;
import Duke.System.Storage;
import Duke.System.TaskList;
import Duke.System.Ui;

/**
 * The abstract Command class contains basic
 * behaviours of a Command.
 *
 * @author  Melvin Chan Zijun
 */
public abstract class Command {
    /**
     * Abstract method that models the execution
     * of this Command
     *
     * @param tasks - TaskList for tasks related methods
     * @param ui - Ui for I/O related methods
     * @param storage - Storage for data saving method
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage)
            throws DukeException;

    /**
     * Method that returns false for all Command objects,
     * except of the CommandExit object.
     *
     * @return boolean - false unless this is a
     *                   CommandExit object
     */
    public boolean isExit() {
        return false;
    }
}
