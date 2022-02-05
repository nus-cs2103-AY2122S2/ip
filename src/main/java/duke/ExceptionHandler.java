package duke;

/**
 * Exceptions for duke errors
 */
public class ExceptionHandler extends Exception {
    public ExceptionHandler(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return " ☹ OOPS!!!" + getMessage();
    }
}
