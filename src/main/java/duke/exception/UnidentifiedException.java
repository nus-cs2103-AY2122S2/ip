package duke.exception;

public class UnidentifiedException extends RonException {
    public static final String message = "Unable to find event defined, please try again.";

    @Override
    public String toString() {
        return super.toString() + message;
    }
}
