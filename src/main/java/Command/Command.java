package Command;

import DukeUtils.*;

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
    abstract public void execute(TaskList tasks, Ui ui, Storage storage) throws CortanaException;

    /**
     * Is user exited.
     *
     * @return whether the user inputs an exit command
     */
    abstract public boolean isExit();
}
