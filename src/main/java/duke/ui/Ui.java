package duke.ui;

import duke.util.Constants;

/**
 * Represents User Interface,
 * deals with interactions with the user
 */
public class Ui {

    /**
     * Log a greeting message into the console
     */
    public void greetings() {
        log(Constants.GREETINGS);
    }

    /**
     * Log a goodbye message to the console when
     * use input bye
     */
    public void bye() {
        log(Constants.BYE);
    }

    /**
     * Shows error message caught to the console.
     *
     * @param e Exception caught.
     */
    public void showLoadingError(Exception e) {
        log("An error has occurred:\n" + e.getMessage());
    }

    /**
     * Main method of Ui class, directly interact with the console.
     * Format the output with horizontal lines.
     *
     * @param args String to be printed into the console.
     */
    public void log(String args) {
        System.out.println(Constants.HORIZONTAL_LINE);
        System.out.println(args);
        System.out.println(Constants.HORIZONTAL_LINE);
    }
}
