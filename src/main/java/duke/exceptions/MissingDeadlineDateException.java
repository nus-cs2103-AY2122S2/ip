package duke.exceptions;

import duke.ui.Ui;

public class MissingDeadlineDateException extends Exception {
    public MissingDeadlineDateException(String message) {
        super(message);
    }

    /**
     * Prints custom error message based on user input
     * @param userInput
     * @return a String containing the custom error message
     */
    public static String printErrorMessage(String userInput) {
        String errorMsg = Ui.createLine()
                + "       You have entered \"" + userInput + "\".\n"
                + "       You have to include deadline after command!\n"
                + "       Follow format [deadline <name>/by <date>]\n"
                + Ui.createLine();
        return errorMsg;
    }
}
