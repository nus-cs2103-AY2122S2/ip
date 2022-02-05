package duke.dukeexceptions;

/**
 * The exception for when no date is entered for commands that requires dates.
 */
public final class EmptyDateException extends DukeExceptions {
    private EmptyDateException(String s) {
        super(s);
    }

    /**
     * Creates the Empty Date exception when no date is entered for commands that requires dates.
     *
     * @param taskCmd Type of command entered.
     * @return The empty date exception.
     * @throws EmptyDateException The empty date exception.
     */
    public static EmptyDateException createEmptyDate(String taskCmd) throws EmptyDateException {
        throw new EmptyDateException(taskCmd.substring(0, 1).toUpperCase() + taskCmd.substring(1) + " needs a date");
    }
}
