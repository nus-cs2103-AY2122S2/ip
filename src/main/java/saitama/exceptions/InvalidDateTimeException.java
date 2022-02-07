package saitama.exceptions;

/**
 * An exception to be thrown when the date or time specified is invalid.
 */
public class InvalidDateTimeException extends SaitamaException {
    public InvalidDateTimeException() {
        super("The specified date and/or time does not exist!");
    }
}
