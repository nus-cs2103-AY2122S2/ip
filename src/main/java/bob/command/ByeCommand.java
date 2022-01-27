package bob.command;

import bob.exception.BobException;
import bob.Storage;
import bob.TaskList;
import bob.Ui;

public class ByeCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage store) throws BobException {
        ui.sayBye();
    }
}
