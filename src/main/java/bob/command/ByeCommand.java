package bob.command;

import bob.Storage;
import bob.TaskList;
import bob.Ui;
import bob.exception.BobException;

/**
 * {@inheritDoc}
 */
public class ByeCommand extends Command {
    /**
     * {@inheritDoc}
     * Triggers the ui to print the goodbye message.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage store) throws BobException {
        return ui.sayBye();
    }
}
