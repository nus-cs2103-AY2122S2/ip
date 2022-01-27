package alfred.exceptions;

/**
 * Encapsulates the exception thrown when an invalid date time format
 * (non-ISO) is passed to Alfred for the "event" and "deadline" commands.
 */
public class InvalidDateTimeException extends AlfredException {
    static String ERROR_MESSAGE =
            "Invalid date time format, sir."
                    + " Check that it follows ISO local format, of <YYYY>-<MM>-<DD>T<HH>:<mm>:<ss>";

    public InvalidDateTimeException() {
        super(InvalidDateTimeException.ERROR_MESSAGE);
    }
}