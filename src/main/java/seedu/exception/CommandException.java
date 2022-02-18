package seedu.exception;

/**
 * Inherits from the Java Exception class.
 * Creates CommandException object when error related to a Command Class or subclass
 * is caught to print out error message.
 */
public class CommandException extends Exception {
    public CommandException(String error) {
        super(error);
    }
}