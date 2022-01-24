package duke.command;

import duke.helpTool.Storage;
import duke.helpTool.TaskList;
import duke.helpTool.Ui;

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
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
}
