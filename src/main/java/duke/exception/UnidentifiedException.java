package duke.exception;

/**
 * RonException type: Unidentified
 * Exception thrown when command is unidentified
 */
public class UnidentifiedException extends RonException {
    public static final String MESSAGE = "Unable to find event defined, please try again.";

    @Override
    public String toString() {
        return super.toString() + MESSAGE;
    }
}
