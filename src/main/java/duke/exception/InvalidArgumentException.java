package duke.exception;

/**
 * A specialised exception for invalid argument.
 */
public class InvalidArgumentException extends DukeException {

    /**
     * Constructs the InvalidArgumentException class.
     * This exception occurs when the user input format is invalid but with some known parts.
     */
    public InvalidArgumentException() {
        super("Hmm, I recognized your command but it seems like the arguments are not complete / invalid.");
    }
}
