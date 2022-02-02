package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Represents an abstract class for the various commands that Ekud supports.
 */

public interface Command {

    /**
     * Execute the function of the command.
     *
     * @param tasks   Task object.
     * @param storage Storage object.
     * @param ui      Ui object.
     * @throws Exception If directory or file cannot be found.
     */
    public abstract void execute(TaskList tasks, Storage storage, Ui ui) throws Exception;

    /**
     * Provides the boolean value whether to exit the programme.
     *
     * @return Boolean value to exit programme.
     */

    public abstract boolean isExit();
}
