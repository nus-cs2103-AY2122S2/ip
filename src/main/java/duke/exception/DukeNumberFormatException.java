package duke.exception;

public class DukeNumberFormatException extends DukeException {
    /**
     * Instantiates DukeException using the parent constructor from Exception.
     */
    public DukeNumberFormatException() {
        super("Index must be a number.");
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
