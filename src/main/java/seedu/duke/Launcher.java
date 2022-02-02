package seedu.duke;

import javafx.application.Application;
import seedu.duke.chatbot.Duke;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Duke.class, args);
    }
}
