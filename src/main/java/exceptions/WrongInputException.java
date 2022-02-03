package exceptions;

/**
 * A class that belongs to the Exceptions Package.
 * This class encapsulates the message that should be displayed when users parsed a wrong input for the
 * commands in {@link component.Command} class.
 */
public class WrongInputException extends TaskException {
    public WrongInputException() {
        super();
    }

    /**
     * Message to be displayed when a WrongInputException happens.
     * @return Message as a string.
     */
    @Override
    public String getMessage() {
        return super.getMessage() + "The input is wrong.";
    }
}
