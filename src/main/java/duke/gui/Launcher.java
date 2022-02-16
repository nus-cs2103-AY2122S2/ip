package duke.gui;

import duke.modules.Duke;
import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(duke.modules.Main.class, args);
    }
}