package duke;

public class DukeException extends Exception {
    /**
     * An exception to indicate that the user has entered an invalid input.
     */
    public DukeException() {
        super("Invalid command!");
    }
}
