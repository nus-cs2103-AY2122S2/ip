package alfred.exceptions;


/**
 * Encapsulates the exception thrown when there is an illegal character in the input.
 */
public class DuplicationException extends AlfredException {

    private static String ERROR_MESSAGE =
            "Sorry sir, you already have this task.";

    public DuplicationException() {
        super(DuplicationException.ERROR_MESSAGE);
    }
}

