package duke.dukeexceptions;

/**
 * The exception when no index number is entered when the command needs one.
 */
public final class EmptyNumberException extends DukeExceptions {
    private EmptyNumberException(String s) {
        super(s);
    }

    /**
     * Creates an Empty Number exception when there is no index number for commands that requires one.
     *
     * @param cmd The type of command.
     * @return The Empty Number exception.
     * @throws EmptyNumberException The Exception when the user did not enter the index number when the command requires it.
     */
    public static EmptyNumberException createEmptyNumber(String cmd) throws EmptyNumberException {
        throw new EmptyNumberException(cmd.substring(0, 1).toUpperCase() + cmd.substring(1) + " needs a number");
    }
}
