package duke.exceptions;

/**
 * DukeException for operations that cannot be carried out.
 */
public class DukeUnsupportedOperationException extends DukeException{
    
    /**
     * Constructor for DukeUnsupportedOperationException.
     *
     * @param message Contains information of the exception.
     */
    public DukeUnsupportedOperationException(String message) {
        super(message);
    }
}
