package duke.exception;

/**
 * A specialised exception for invalid index.
 * Triggered if index < 0 or >= size.
 */
public class InvalidIndexException extends DukeException {

    /**
     * Constructs the InvalidIndexException class.
     * This exception occurs when user input index is out of bounds.
     */
    public InvalidIndexException() {
        super("Sorry, your argument has invalid Index. Hint: Use \"list\" command to help you");
    }
}
