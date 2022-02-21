package seedu.view;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Launches the Duke application.
     *
     * @param args Serves as the inputs for launching.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
