package duke.exception;

/**
 * Represents custom exception handler for chatbot
 */
public class DukeException extends Exception {

    /**
     * @param errorMessage Custom error message to pass on to object
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

}
