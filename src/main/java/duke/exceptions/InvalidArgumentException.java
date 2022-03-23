package duke.exceptions;

public class InvalidArgumentException extends DukeException {
    private static final String PREFIX = "Please enter a valid ";
    private static final String END = "!";

    /**
     * Constructor for the exception.
     * @param argument the argument that is invalid in the command
     */
    public InvalidArgumentException(String argument) {
        super(PREFIX + argument + END);
    }
}
