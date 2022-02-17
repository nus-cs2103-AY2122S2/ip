package duke;

/**
 * Represents an exception where the date provided is invalid
 */
public class InvalidDateException extends DukeException {
    InvalidDateException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "InvalidDateException: " + this.getMessage();
    }
}
