package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command that user intends to use.
 */
public abstract class Command {

    /**
     * Executes the Command.
     *
     * @param tasks TaskList that command is executed on.
     * @param ui User interface that interacts with the user.
     * @param storage Storage that saves and loads tasks after Command is executed.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public boolean isExit() {
        return false;
    }
}
