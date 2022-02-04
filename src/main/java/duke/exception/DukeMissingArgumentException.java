package duke.exception;

/**
 * This exception occurs when a necessary argument is missing.
 */
public class DukeMissingArgumentException extends DukeException {
    /**
     * Instantiates this exception with an input error message, using parent constructor.
     *
     * @param message String Error message.
     */
    public DukeMissingArgumentException(String message) {
        super(message);
    }

    /**
     * Returns a String representation of this exception.
     *
     * @return String String representation of DukeMissingArgumentException.
     */
    @Override
    public String toString() {
        String result = "We are missing the following argument: " + this.getMessage() + "\n";
        return result;
    }
}
