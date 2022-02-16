package aeromon.command;

import aeromon.AeromonException;
import aeromon.Storage;
import aeromon.TaskArrayList;

/**
 * Abstract class that executes the commands.
 */
public abstract class Command {

    /**
     * Executes the command itself.
     *
     * @param taskArrayList the TaskArrayList of Aeromon.
     * @param storage       the Storage of Aeromon.
     * @throws AeromonException when an error occurs during execution.
     */
    public abstract String execute(TaskArrayList taskArrayList, Storage storage) throws AeromonException;
}
