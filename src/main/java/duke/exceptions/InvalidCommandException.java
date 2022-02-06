package duke.exceptions;

/**
 * Represents an exception that will be thrown when the user keys in an invalid command
 **/
public class InvalidCommandException extends DukeException {
    /**
     * Initializes an InvalidCommandException
     */
    public InvalidCommandException() {
        super("Sumimasen! I don't recognize this command. Please try again!");
    }
}
