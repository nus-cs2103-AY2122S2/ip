package duke;

import duke.Gui.DukeGui;
import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Main {
    public static void main(String[] args) {
        Application.launch(DukeGui.class, args);
    }
}