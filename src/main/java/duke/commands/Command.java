package duke.commands;

import duke.exceptions.DukeException;
import duke.system.Storage;
import duke.system.TaskList;
import duke.system.Ui;

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
