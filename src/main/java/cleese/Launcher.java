package cleese;

import javafx.application.Application;

/**
 * Launcher Class which fires up the GUI for the application
 */
public class Launcher {
    /**
     * Launches Cleese class
     * Opens the GUI for the application.
     * @param args
     */
    public static void main(String[] args) {
        Cleese.initialize();
        Application.launch(Cleese.class, args);
    }
}
