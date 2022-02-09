package duke.exceptions;

import duke.ui.Ui;

public class InvalidTypeException extends Exception {
    public InvalidTypeException(String message) {
        super(message);
    }

    /**
     * Prints custom error message based on user input
     * @param userInput
     * @return a String containing the custom error message
     */
    public static String printErrorMessage(String userInput) {
        String errorMsg = Ui.createLine()
                + "       Opps, the command \"" + userInput + "\" is not supported :(\n"
                + Ui.createLine();
        return errorMsg;
    }
}