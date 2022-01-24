package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents exit command.
 * Changes state of isExit().
 */
public class ExitCommand extends Command {
    public ExitCommand() {
        super();
    }

    /**
     * Returns a exit command.
     *
     * @param tasks   the entire TaskList.
     * @param ui      the ui interface and messages.
     * @param storage the storage operations.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExitMessage();
    }

    /**
     * Returns command exit status is true.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
