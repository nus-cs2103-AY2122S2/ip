package alfred.exceptions;

/**
 * Encapsulates the exception thrown when only the keyword is passed
 * to Alfred, without any arguments.
 */
public class MissingInputException extends AlfredException {

    static String ERROR_MESSAGE =
            "Missing input, sir. "
                    + "No valid input found after keyword arguments '(un)mark', 'todo', 'event' or 'deadline'.";

    public MissingInputException() {
        super(MissingInputException.ERROR_MESSAGE);
    }
}
