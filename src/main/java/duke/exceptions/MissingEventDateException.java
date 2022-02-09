package duke.exceptions;

import duke.ui.Ui;

public class MissingEventDateException extends Exception{
    public MissingEventDateException(String message) {
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
                + "       You have to include date after command!\n"
                + "       Please follow format [event <name>/at <date>]\n"
                + Ui.createLine();
        return errorMsg;
    }
}