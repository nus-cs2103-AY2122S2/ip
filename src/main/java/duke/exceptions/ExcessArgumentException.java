package duke.exceptions;

/**
 * Error when too many arguments are specified in the command.
 */
public class ExcessArgumentException extends DukeException {
    private static final String PREFIX = "Please input ";
    private static final String END = " at a time!";

    /**
     * Constructor for the exception.
     * @param argument the argument that is in excess
     */
    public ExcessArgumentException(String argument) {
        super(PREFIX + argument + END);
    }
}
