package duke;

/**
 * EmptyDescriptionException is a class for handling unknown commands
 *
 * @author Rosa Kang
 */
class UnknownCmdException extends Exception {
    /**
     * Constructor for UnknownCmdException
     */
    public UnknownCmdException() {
        super("I'm sorry, but I don't know what that means...");
    }
}
