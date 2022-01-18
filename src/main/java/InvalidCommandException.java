/**
 * Exception that occurs when an invalid command is provided.
 */
public class InvalidCommandException extends DukeException {
    /**
     * Constructs an invalid command exception.
     */
    public InvalidCommandException() {
        super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
