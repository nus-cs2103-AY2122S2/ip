package duke.exceptions;

/**
 * This exception is thrown when the command is missing arguments
 */
public class MissingArgumentException extends DukeException{

    /**
     * Creates a MissingArgumentException
     */
    public MissingArgumentException(){
        super("Command has no body! Add details after using command");
    }
}
