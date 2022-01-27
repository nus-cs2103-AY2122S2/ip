package alfred.exceptions;

/**
 * Encapsulates the exception thrown when a valid command is identifiable
 * in the input, but the format of arguments is not as expected.
 */
public class InvalidInputException extends AlfredException {
    static String ERROR_MESSAGE =
            "Sorry sir, that's a valid command but invalid input."
                    +
                    " Likely due to wrong format after '(un)mark', 'delete', 'todo', 'deadline' or 'event'.";

    public InvalidInputException() {
        super(InvalidInputException.ERROR_MESSAGE);
    }
}
