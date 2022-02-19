package saitama.exceptions;

/**
 * An exception to be thrown when a valid command is given to Saitama.
 * but it is in the wrong format.
 */
public class InvalidFormatException extends SaitamaException {
    public InvalidFormatException(String message) {
        super(message);
    }
}
