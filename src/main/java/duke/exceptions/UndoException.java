package duke.exceptions;

/**
 * Represents an exception that is thrown when there are errors when processing
 * the undo command.
 */
public class UndoException extends DukeException {
    private final String errorCode;

    /**
     * Instantiates an UndoException
     */
    public UndoException(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * Returns the detail message of the error that has occurred when undoing
     * a command that the user has keyed in.
     * @return Detail message of an UndoException.
     */
    @Override
    public String getMessage() {
        String message = "";
        switch (errorCode) {
        case "NOTHING":
            message = "Nothing to undo here!";
            break;
        case "EMPTY_PREVIOUS":
            message = "No previous commands can be found!";
            break;
        default:
            assert false : errorCode;
        }
        return message;
    }
}
