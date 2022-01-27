package duke.exception;

public class WrongDateSyntaxException extends RonException {
    public static final String message = "Please add prefix '/' to date.";

    @Override
    public String toString() {
        return super.toString() + message;
    }
}
