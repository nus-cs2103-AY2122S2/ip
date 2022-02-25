package jarvis;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Entry point for the launcher.
     * @param args arguments for the application
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
