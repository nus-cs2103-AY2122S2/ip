package aeromon.command;

import aeromon.AeromonException;
import aeromon.Storage;
import aeromon.Ui;
import aeromon.TaskArrayList;

/**
 * Abstract class that executes the commands.
 */
public abstract class Command {

    /**
     * Executes the command itself.
     * @param taskArrayList  the TaskArrayList of Aeromon.
     * @param ui the UI of Aeromon.
     * @param storage the Storage of Aeromon.
     * @throws AeromonException when an error occurs during execution.
     */
    public abstract String execute(TaskArrayList taskArrayList, Ui ui, Storage storage) throws AeromonException;

    /**
     * Checks if the command is a ByeCommand.
     * @return if the command is an instance of ByeCommand.
     */
    public boolean isExit() {
        return this instanceof ByeCommand;
    }
}
