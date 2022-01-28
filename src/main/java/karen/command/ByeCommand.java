package karen.command;

import karen.Storage;
import karen.Ui;

/**
 * To feedback Karen to exit and close the application.
 */
public class ByeCommand extends Command {
    public ByeCommand() {}

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(Ui ui, Storage storage) {
        ui.showGoodbye();
    }

}
