package duke.exceptions;

/**
 * Represents an exception which is thrown upon no description given for tasks.
 */
public class EmptyDescriptionException extends DukeException {
    /**
     * Constructs an EmptyDescriptionException.
     */
    public EmptyDescriptionException(String command) {
        super("OOPS!!! The description of a " + command + " cannot be empty");
    }
}
