package duke;

public class MissingDescriptionException extends Exception {
    /**
     * An exception to indicate that the user has not input all information required for a command.
     */
    public MissingDescriptionException() {
        super("Missing description!");
    }
}
