package duke;

import javafx.application.Application;

public class Launcher {
    public static void main(String[] args) {
        new Duke().initializeTaskList();
        Application.launch(Duke.class, args);
    }
}
