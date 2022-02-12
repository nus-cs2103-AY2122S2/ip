package duke;

import javafx.application.Application;

/**
 * Serves as the main entry point for the application.
 * Launches the Duke JavaFX lifecycle by calling {@link Application#launch(Class, String...)}.
 */
public class Launcher {
    /**
     * Launches the JavaFX user interface for the application.
     *
     * @param args The arguments passed in during application launch.
     */
    public static void main(String ... args) {
        new Duke().initializeTaskList();
        Application.launch(Duke.class, args);
    }
}
