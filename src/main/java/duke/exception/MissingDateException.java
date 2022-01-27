package duke.exception;

/**
 * RonException type: Missing date
 * Exception thrown when user input does not contain a date
 */

public class MissingDateException extends RonException {
    public static final String message = "Please add a date.";

    @Override
    public String toString() {
        return super.toString() + message;
    }
}
