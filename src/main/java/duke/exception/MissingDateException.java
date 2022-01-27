package duke.exception;

public class MissingDateException extends RonException {
    public static final String message = "Please add a date.";

    @Override
    public String toString() {
        return super.toString() + message;
    }
}
