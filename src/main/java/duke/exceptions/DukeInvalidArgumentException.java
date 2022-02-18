package duke.exceptions;

/**
 * DukeException for invalid arguments inputted.
 */
public class DukeInvalidArgumentException extends DukeException{
    
    /**
     * Constructor for DukeInvalidArgumentException.
     *
     * @param message Contains information of the exception.
     */
    public DukeInvalidArgumentException(String message) {
        super(message);
    }
}
