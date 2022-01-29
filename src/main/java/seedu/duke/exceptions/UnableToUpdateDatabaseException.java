package seedu.duke.exceptions;

/**
 * Thrown when {@link seedu.duke.chatbot.Storage} is unable to update database.
 */
public class UnableToUpdateDatabaseException extends DukeException {
    /**
     * Creates a UnableToStoreLineException() to update user about database error.
     */
    public UnableToUpdateDatabaseException() {
        super("Can't seem to store the line");
    }
}