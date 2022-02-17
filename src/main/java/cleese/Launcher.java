package cleese;

import javafx.application.Application;

/**
 * Launcher Class which fires up the GUI for the application
 */
public class Launcher {
    /**
     * Main method which initializes the Cleese class, and launches the GUI for the application.
     * @param args
     */
    public static void main(String[] args) {
        Cleese.initialize();
        Application.launch(Cleese.class, args);
    }
}
