package duke;

/**
 * Duke specific exception that allows for custom error messages to be thrown.
 */
public class DukeException extends Exception {
    String message;

    /**
     * Constructor method for DukeException.
     *
     * @param errorMessage Custom message to be thrown.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
        message = errorMessage;
    }

    /**
     * Returns the error message from the specific DukeException thrown.
     *
     * @return Returns the error message specified.
     */
    @Override
    public String toString() {
        return message;
    }
}