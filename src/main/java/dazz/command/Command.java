package dazz.command;

import dazz.Storage;
import dazz.TaskList;
import dazz.Ui;
import dazz.exception.InvalidTaskIndexException;

/**
 * Represents various <code>Command</code> to be executed.
 */
public abstract class Command {

    /**
     *
     * @param taskList The <code>TaskList</code>.
     * @param ui The <code>Ui</code> of <code>Dazz</code>.
     * @param storage The <code>Storage</code>.
     * @return The execution message.
     * @throws InvalidTaskIndexException If index is out of bound.
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws InvalidTaskIndexException;

    /**
     * Gets whether the <code>Command</code> is exit.
     * @return true if exit.
     */
    public boolean isExit() {
        return false;
    }
}
