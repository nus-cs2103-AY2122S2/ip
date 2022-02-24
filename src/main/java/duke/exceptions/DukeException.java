package duke.exceptions;

/**
 * Parent class of all duke exceptions.
 */
public class DukeException extends Exception {
    
    /**
     * Constructor for DukeException.
     *
     * @param message Contains information of the exception.
     */
    public DukeException(String message) {
        super(message);
    }
}
