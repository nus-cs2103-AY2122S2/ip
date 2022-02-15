package aeromon.command;

import aeromon.AeromonException;
import aeromon.Storage;
import aeromon.TaskArrayList;

/**
 * ByeCommand class handles the exit commands.
 */
public class ByeCommand extends Command {

    private static final String FAREWELL_MESSAGE = "Buai Buai! Ciao for now!";

    @Override
    public String execute(TaskArrayList taskArrayList, Storage storage) throws AeromonException {
        return FAREWELL_MESSAGE;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof ByeCommand;
    }
}
