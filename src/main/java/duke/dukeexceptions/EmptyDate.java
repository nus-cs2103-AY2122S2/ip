package duke.dukeexceptions;

/**
 * The exception for when no date is entered for commands that requires dates.
 */
public final class EmptyDate extends DukeExceptions {
    private EmptyDate(String s) {
        super(s);
    }

    /**
     * Creates the Empty Date exception when no date is entered for commands that requires dates.
     *
     * @param taskCmd Type of command entered.
     * @return The empty date exception.
     * @throws EmptyDate The empty date exception.
     */
    public static EmptyDate createEmptyDate(String taskCmd) throws EmptyDate {
        throw new EmptyDate(taskCmd.substring(0, 1).toUpperCase() + taskCmd.substring(1) + " needs a date");
    }
}
