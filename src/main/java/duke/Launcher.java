package duke;

import duke.ui.Main;
import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Entry point of the application
     *
     * @param args Arguments to be passed into the program, if any.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
