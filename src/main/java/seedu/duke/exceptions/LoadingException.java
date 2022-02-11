package seedu.duke.exceptions;

/**
 * Thrown when {@link seedu.duke.chatbot.Storage} is unable to access or use database effectively.
 */
public class LoadingException extends DukeException {
    /**
     * Creates a LoadingException with a message telling user that old tasks cannot be retrieved.
     */
    public LoadingException() {
        super("Looks like there is a problem with creating or retrieving the files for tasks");
    }
}