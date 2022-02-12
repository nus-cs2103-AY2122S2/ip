package exceptions;

/**
 * A class that belongs to the Exceptions Package.
 * This class encapsulates the message that should be displayed when users parsed an invalid input.
 */
public class IncorrectInputException extends TaskException {

    /**
     * Constructs IncorrectInputException.
     */
    public IncorrectInputException() {
        super();
    }

    /**
     * Creates message to be displayed when a IncorrectInputException happens.
     * @return Message as a string.
     */
    @Override
    public String getMessage() {
        return super.getMessage() + " I'm sorry, but I don't know what that means :-(";
    }
}
