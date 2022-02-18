package duke.main;

/**
 * An abstract clas that represents the exceptions related to main.Duke.
 */
public abstract class DukeException extends Exception {
    private final String message;

    /**
     * Constructs an exception used by Duke, with the specified message.
     * This constructor is called by the subclasses only.
     *
     * @param message The message to be shown when the exception is thrown.
     */
    protected DukeException(String message) {
        this.message = message;
    }

    /**
     * Returns the message associated to the exception.
     *
     * @return The message.
     */
    @Override
    public String getMessage() {
        return message;
    }
}
