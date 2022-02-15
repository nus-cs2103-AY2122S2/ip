package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents an abstract class of command.
 */
public abstract class Command {

    /**
     * Returns a various command.
     *
     * @param tasks   the entire TaskList.
     * @param ui      the ui interface and messages.
     * @param storage the storage operations.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Returns command exit status is false.
     */
    public boolean isExit() {
        return false;
    }
}
