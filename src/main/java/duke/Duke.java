package duke;


import duke.storage.Storage;
import duke.ui.Display;
import javafx.application.Application;
/**
 * A launcher class to workaround classpath issues.
 */
public class Duke {
    public static void main(String[] args) {
        Storage.initialise();
        Application.launch(Display.class, args);
    }
}