package duke.common;

/**
 * Exceptions that are specific to the Duke ChatBot.
 * Inherits from Exception.
 */
public class DukeException extends Exception {

    /**
     * Constructor to create a DukeException.
     *
     * @param errorMessage message describing the error.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
