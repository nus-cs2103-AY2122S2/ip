package duke.exception;

/**
 * RonException type: Wrong date syntax
 * Exception thrown when users input date without "/"
 */
public class WrongDateSyntaxException extends RonException {
    public static final String MESSAGE = "Please add prefix '/' to date.";

    @Override
    public String toString() {
        return super.toString() + MESSAGE;
    }
}
