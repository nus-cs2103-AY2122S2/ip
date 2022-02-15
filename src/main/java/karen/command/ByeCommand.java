package karen.command;

import karen.Storage;
import karen.Ui;

/**
 * To feedback Karen to exit and close the application.
 */
public class ByeCommand extends Command {
    /**
     * Returns Goodbye message.
     *
     * @param ui To control outputs related to execution
     * @param storage To access and modify Tasks stored in Storage
     * @return String result of output from successful execution of Command
     */
    @Override
    public String execute(Ui ui, Storage storage) {
        return Ui.GOODBYE;
    }

}
