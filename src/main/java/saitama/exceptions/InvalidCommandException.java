package saitama.exceptions;

/**
 * An exception to be thrown when an invalid command is given to Saitama.
 */
public class InvalidCommandException extends SaitamaException {
    public InvalidCommandException() {
        super("Please enter a valid command!");
    }
}
