package duke;

/**
 * Represents an exception where the index given is out of bounds
 */
public class OutOfBoundsException extends DukeException {

    OutOfBoundsException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "OutOfBoundsException: " + this.getMessage();
    }
}