package duke.dukeexceptions;

/**
 * The exception when no task name is entered when the command needs it.
 */
public final class EmptyTask extends DukeExceptions {
    private EmptyTask(String s) {
        super(s);
    }

    /**
     * Creates an empty task exception.
     *
     * @param taskCmd The type of command that did not receive the task name.
     * @return The EmptyTask Exception.
     * @throws EmptyTask The exception when there is no task name when the command needs it.
     */
    public static EmptyTask createEmptyTask(String taskCmd) throws EmptyTask {
        throw new EmptyTask("Are you tryng to add something to " + taskCmd.toLowerCase());
    }
}
