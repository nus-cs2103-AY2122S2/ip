package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents error command.
 * Inherits from Command.
 */
public class ErrorCommand extends Command {
    private final String err;

    /**
     * Returns an error command with error message.
     *
     * @param err index to be mark.
     */
    public ErrorCommand(String err) {
        super();
        this.err = err;
    }

    /**
     * Returns error Message.
     *
     * @param tasks   the entire TaskList.
     * @param ui      the ui interface and messages.
     * @param storage the storage operations.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("\uD83D\uDE15 " + err);
    }
}
