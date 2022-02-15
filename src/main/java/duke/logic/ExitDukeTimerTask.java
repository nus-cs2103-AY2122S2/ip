package duke.logic;

import java.util.TimerTask;

import javafx.application.Platform;

/**
 * Represents a timer task that exits Duke GUI.
 *
 * @author Peter
 */
public class ExitDukeTimerTask extends TimerTask {
    /**
     * Closes the window and kills the process that runs Duke GUI.
     */
    @Override
    public void run() {
        Platform.exit();
        System.exit(0);
    }
}
