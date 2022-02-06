package karen.command;

import karen.KarenException;
import karen.Storage;
import karen.Ui;

/**
 * Allows for execution of functionalities related to Ui, Storage objects
 */
public abstract class Command {
    public Command() {
    }

    /**
     * Gets flag marker to indicate if user wishes to exit from application
     *
     * @return Flag marker to indicate if program should end
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes functionalities in implementations of Commands
     *
     * @param ui To control outputs related to execution
     * @param storage To access and modify Tasks stored in Storage
     * @throws KarenException If there are runtime exceptions during execute
     */
    public abstract String execute(Ui ui, Storage storage) throws KarenException;
}
