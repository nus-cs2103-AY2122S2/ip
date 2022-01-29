package duke.command;

import duke.helptool.Storage;
import duke.helptool.TaskList;
import duke.helptool.Ui;

/**
 * The type Command.
 */
public abstract class Command {
    /**
     * Is exit boolean.
     *
     * @return the boolean
     */
    public abstract boolean isExit();

    /**
     * Execute.
     *
     * @param tasks   the tasks
     * @param ui      the ui
     * @param storage the storage
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);
}
