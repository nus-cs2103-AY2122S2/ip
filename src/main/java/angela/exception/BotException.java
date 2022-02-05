package angela.exception;

/**
 * Prints error message when user input the wrong command
 */
public class BotException {

    /**
     * Prints error when the bot command does not follow with description
     *
     * @param description The input command type
     *
     * @return The reply string of Angela
     */
    public String printEmptyDescriptionError(String description) {
        return " OOPS!!! The description of a " + description + " cannot be empty.";
    }

    /**
     * Prints error when the input command does not follow with a number
     * eg: 'todo abc' instead of 'todo 2'
     *
     * @param description The input command type
     *
     * @return The reply string of Angela
     */
    public String printNotNumericError(String description) {
        return " OOPS!!! The description of a " + description + " must be a number.";
    }

    /**
     * Prints error when the input command does not belong to any known command
     * @return The reply string of Angela
     */
    public String printWrongSyntaxError() {
        return " OOPS!!! I'm sorry, but I don't know what that means :-(";
    }

    /**
     * Prints error when the input date does not appear in the database
     * @return The reply string of Angela
     */
    public String printDateNotFoundError() {
        return " OOPS!!! You don't have any deadlines/events on this day :-(";
    }
}
