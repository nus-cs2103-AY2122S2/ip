package bob.command;

import bob.exception.BobException;
import bob.Storage;
import bob.TaskList;
import bob.Ui;

/**
 * {@inheritDoc}
 */
public class ByeCommand extends Command {
    /**
     * {@inheritDoc}
     * Triggers the ui to print the goodbye message.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage store) throws BobException {
        ui.sayBye();
    }
}
