package duke.exceptions;

/**
 * Exception that occurs when users does not give a description when adding a task.
 */
public class RequiredInformationMissingException extends DukeException {
    public RequiredInformationMissingException(String message) {
        super(message);
    }
}
