package duke.exception;

/**
 * RonException type: Write
 * Exception thrown when error occurs while saving backup file on exit
 */

public class WriteException extends RonException {
    public static final String message = "Error while saving backup.";

    @Override
    public String toString() {
        return super.toString() + message;
    }
}
