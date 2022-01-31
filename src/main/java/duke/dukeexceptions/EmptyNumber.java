package duke.dukeexceptions;

/**
 * The exception when no index number is entered when the command needs one.
 */
public final class EmptyNumber extends DukeExceptions{
    private EmptyNumber(String s) {
        super(s);
    }

    /**
     * Creates an Empty Number exception when there is no index number for commands that requires one.
     *
     * @param cmd The type of command.
     * @return The Empty Number exception.
     * @throws EmptyNumber The Exception when the user did not enter the index number when the command requires it.
     */
    public static EmptyNumber createEmptyNumber(String cmd) throws EmptyNumber {
        throw new EmptyNumber(cmd.substring(0, 1).toUpperCase() + cmd.substring(1) + " needs a number");
    }
}
