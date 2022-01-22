package exceptions;

/**
 * Exception that occurs when users does not give a description when adding a task.
 */
public class RequiredInformationMissingException1 extends DukeException {
    public RequiredInformationMissingException1(String message) {
        super(message);
    }
}
