package duke.exceptions;

/**
 * This exception is thrown when there is a Duplicate task in the list
 */
public class DuplicateTaskException extends DukeException {
    public DuplicateTaskException(int index) {
        super("Duplicate task detected! See Task " + index);
    }
}
