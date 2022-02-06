package duke;

import javafx.application.Application;

// --module-path C:\JavaFx\javafx-sdk-11\bin --add-modules javafx.controls,javafx.fxml

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
