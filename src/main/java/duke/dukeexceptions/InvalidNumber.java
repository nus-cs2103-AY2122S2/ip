package duke.dukeexceptions;

/**
 * An exception when the user enters an invalid number when the command requires one.
 */
public final class InvalidNumber extends DukeExceptions {
    /**
     * Creates a new InvalidNumber exception.
     */
    public InvalidNumber() {
        super("You have entered an invalid number!");
    }
}
