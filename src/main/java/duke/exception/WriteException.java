package duke.exception;

public class WriteException extends RonException {
    public static final String message = "Error while saving backup.";

    @Override
    public String toString() {
        return super.toString() + message;
    }
}
