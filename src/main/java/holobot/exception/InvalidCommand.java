package holobot.exception;

/**
 * Represents an exception that will be thrown when the user enters an invalid command.
 *
 * @author Terng Yan Long
 */
public class InvalidCommand extends HoloBotException {
    private final String errorMessage;

    /**
     * Instantiates a new instance of holobot.exception.InvalidCommand.
     *
     * @param errorMessage The error message that is related to the exception.
     */
    public InvalidCommand(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    /**
     * Creates an error message to be displayed to the user.
     *
     * @return String containing the output message.
     */
    @Override
    public String toString() {
        return "EHHHHHHH~ " + errorMessage;
    }
}
