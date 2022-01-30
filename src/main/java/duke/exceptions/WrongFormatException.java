package duke.exceptions;

/**
 * This exception is thrown when the command arguments are in the wrong format
 */
public class WrongFormatException extends DukeException{

    /**
     * Creates a WrongFormatException
     */
    public WrongFormatException(){
        super("Command is in the wrong format!");
    }
}
