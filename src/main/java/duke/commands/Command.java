package duke.commands;

import duke.system.Storage;
import duke.system.TaskList;
import duke.system.Ui;

/**
 * The abstract Command class contains basic
 * behaviours of a command.
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
     * @return String message informing user about the
     *                success of execution
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);
}
