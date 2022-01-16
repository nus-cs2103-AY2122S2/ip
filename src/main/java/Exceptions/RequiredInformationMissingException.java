package Exceptions;

/**
 * Exception that occurs when users does not give a description when adding a task.
 */
public class DescriptionMissingException extends DukeException {
    public DescriptionMissingException(String message) {
        super(message);
    }
}
