package duke.dukeexceptions;

/**
 * An exception when the user enters an invalid number when the command requires one.
 */
public final class InvalidNumberException extends DukeExceptions {
    /**
     * Creates a new InvalidNumberException exception.
     */
    public InvalidNumberException() {
        super("You have entered an invalid number!");
    }
}
