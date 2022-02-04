package duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    private Duke duke = new Duke("duke.txt");

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}