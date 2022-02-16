package aeromon.command;

import aeromon.AeromonException;
import aeromon.Storage;
import aeromon.TaskArrayList;

import java.util.Timer;
import java.util.TimerTask;

/**
 * ByeCommand class handles the bye commands that closes Aeromon.
 */
public class ByeCommand extends Command {

    private static final String FAREWELL_MESSAGE = "Buai Buai! Ciao for now!";

    @Override
    public String execute(TaskArrayList taskArrayList, Storage storage) throws AeromonException {

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.exit(0);
            }
        }, 500);

        return FAREWELL_MESSAGE;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof ByeCommand;
    }
}
