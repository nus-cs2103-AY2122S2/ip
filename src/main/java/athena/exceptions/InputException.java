package athena.exceptions;

/**
 * Represents a specific error arising from incorrect or invalid user input given to Athena.
 */
public class InputException extends Exception {
    private final InputErrorCode inputErrorCode;

    /**
     * Constructs an InputException with the given InputErrorCode.
     *
     * @param inputErrorCode The type of input error made by the user.
     */
    public InputException(InputErrorCode inputErrorCode) {
        this.inputErrorCode = inputErrorCode;
    }

    /**
     * Returns the String response made by Athena corresponding to the user's input error,
     * to be displayed to the user.
     *
     * @return Specific response String to user input error.
     */
    @Override
    public String getMessage() {
        switch (inputErrorCode) {
        case INVALID_COMMAND:
            return "That's an invalid command. Please try again.";
            // No fallthrough
        case MISSING_TASK_NUMBER:
            return "Error. Please provide a valid task number.";
            // No fallthrough
        case INVALID_TASK_NUMBER:
            return "Error. There is no such task. Please try again.";
            // No fallthrough
        case MISSING_TASK_NAME:
            return "Error. Please provide a task name.";
            // No fallthrough
        case MISSING_TASK_DATETIME:
            return "Error. Please provide a date and time.";
            // No fallthrough
        case INVALID_TASK_DATETIME:
            return "Error. Please enter the date and time in the following format: dd/mm/yyyy hhmm";
            // No fallthrough
        case MISSING_SEARCH_PHRASE:
            return "Error. Please provide a search phrase.";
            // No fallthrough
        case INVALID_REMINDER_PHRASE:
            return "I do not understand that phrase. Try asking me for reminders "
                + "for 'today', 'tomorrow', 'this week' or 'next week'";
            // No fallthrough
        default:
            assert false;
            return "";
            // No fallthrough
        }
    }
}
