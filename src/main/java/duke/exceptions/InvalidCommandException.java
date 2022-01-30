package duke.exceptions;

/**
 * This exception is thrown when no valid commands are given by the user
 */
public class InvalidCommandException extends DukeException{

    /**
     * Creates an InvalidCommandException
     */
    public InvalidCommandException(){
        super("Command is invalid!");
    }
}
