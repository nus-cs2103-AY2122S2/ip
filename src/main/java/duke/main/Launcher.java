package duke.main;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Launches the main application.
     *
     * @param args the arguments input by user.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
