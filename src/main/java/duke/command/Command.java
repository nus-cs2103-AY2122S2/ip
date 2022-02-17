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
     * This is an abstract method, all children will have corresponding implementations.
     *
     * @param tasks the TaskList tasks
     * @param ui       the ui
     * @param storage  the storage
     * @throws CortanaException the cortana exception
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws CortanaException;
}
