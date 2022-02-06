package duke.exception;

/**
 * Represents custom exception handler for chatbot
 */
public class DukeException extends Exception{

    public DukeException(String errorMessage) {
        super(errorMessage);
    }

}