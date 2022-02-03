package duke.exception;

/**
 * Exception class for handling exceptions
 */
public class DukeException extends Exception{

    /**
     * constructor for DukeException
     * @param errMessage
     */
    public DukeException(String errMessage){
        super(errMessage);
    }
}
