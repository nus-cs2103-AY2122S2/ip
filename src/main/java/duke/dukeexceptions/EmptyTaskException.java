package duke.dukeexceptions;

/**
 * The exception when no task name is entered when the command needs it.
 */
public final class EmptyTaskException extends DukeExceptions {
    private EmptyTaskException(String s) {
        super(s);
    }

    /**
     * Creates an empty task exception.
     *
     * @param taskCmd The type of command that did not receive the task name.
     * @return The EmptyTaskException Exception.
     * @throws EmptyTaskException The exception when there is no task name when the command needs it.
     */
    public static EmptyTaskException createEmptyTask(String taskCmd) throws EmptyTaskException {
        throw new EmptyTaskException("Are you tryng to add something to " + taskCmd.toLowerCase());
    }
}
