package exceptions;

/**
 * A class that belongs to the Exceptions Package.
 * This class encapsulates the message that should be displayed when users parsed an invalid input
 * for the {@link tasks.Events} class.
 */
public class EventException extends TaskException {

    /**
     * Constructor for EventException class.
     */
    public EventException() {
        super();
    }

    /**
     * Message to be displayed when an EventException happens.
     * @return Message as a string.
     */
    @Override
    public String getMessage() {
        return super.getMessage() + "The description of an event cannot be empty.";
    }
}
