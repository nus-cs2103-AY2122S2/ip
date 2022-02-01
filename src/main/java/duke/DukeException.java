package duke;

public class DukeException extends RuntimeException {
    /**
     * Constructs a DukeException instance.
     *
     * @param message The error message.
     */
    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "OOPS!!! " + this.getMessage();
    }
}
