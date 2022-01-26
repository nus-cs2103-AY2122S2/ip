package duke.exception;

/**
 * Prints error message when user input the wrong command
 */
public class BotException {

    /**
     * Prints error when the bot command does not follow with description
     *
     * @param description The input command type
     */
    public void printEmptyDescriptionError(String description) {
        System.out.println("____________________________________________________________");
        System.out.println("  ☹ OOPS!!! The description of a " + description + " cannot be empty.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints error when the input command does not follow with a number
     * eg: 'todo abc' instead of 'todo 2'
     *
     * @param description The input command type
     */
    public void printNotNumericError(String description) {
        System.out.println("____________________________________________________________");
        System.out.println("  ☹ OOPS!!! The description of a " + description + " must be a number.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints error when the input command does not belong to any known command
     */
    public void printWrongSyntaxError() {
        System.out.println("____________________________________________________________");
        System.out.println("  ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints error when the input date does not appear in the database
     */
    public void printDateNotFoundError() {
        System.out.println("____________________________________________________________");
        System.out.println("  ☹ OOPS!!! You don't have any deadlines/events on this day :-(");
        System.out.println("____________________________________________________________");
    }
}
