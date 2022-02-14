package duke;

import duke.gui.Main;
import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues. *
 */
//@@author goel-a-reused
// This class has been adapted from https://se-education.org/guides/tutorials/javaFx.html
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
//@@author goel-a
