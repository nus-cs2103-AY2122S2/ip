package karen.command;

import karen.Storage;
import karen.Ui;

/**
 * To feedback Karen to exit and close the application.
 */
public class ByeCommand extends Command {
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String execute(Ui ui, Storage storage) {
        return Ui.GOODBYE;
    }

}
