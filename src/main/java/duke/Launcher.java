package duke;

import javafx.application.Application;

public class Launcher {
    /**
     * Launches the Duke application
     * @param args User inserted arguments to change the application behaviour
     */
    public static void main(String[] args) {
        new Duke();
        Application.launch(Duke.class, args);
    }
}
