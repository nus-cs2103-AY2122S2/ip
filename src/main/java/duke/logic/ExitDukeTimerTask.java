package duke.logic;

import java.util.TimerTask;

import javafx.application.Platform;

public class ExitDukeTimerTask extends TimerTask {
    @Override
    public void run() {
        Platform.exit();
        System.exit(0);
    }
}
