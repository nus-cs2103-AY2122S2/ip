package duke;

import javafx.application.Application;

/**
 * Main entry point of application
 */
public class Main {

    /**
     * The main entry point to the application
     *
     * Creates an instance of Duke and starts the application
     */
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("--cmd")) { // Command Line mode
            new Duke().run_cmd();
        } else { // GUI mode
            Application.launch(duke.ui.GraphicsUi.class, args);
        }
    }
}
