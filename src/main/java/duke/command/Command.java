package duke.command;

import duke.utils.CortanaException;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

/**
 * The type Command.
 */
public abstract class Command {
    /**
     * Execute the corresponding command.
     *
     * @param tasks the TaskList tasks
     * @param ui       the ui
     * @param storage  the storage
     * @throws CortanaException the cortana exception
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws CortanaException;

    /**
     * Is user exited.
     *
     * @return whether the user inputs an exit command
     */
    public abstract boolean isExit();
}
