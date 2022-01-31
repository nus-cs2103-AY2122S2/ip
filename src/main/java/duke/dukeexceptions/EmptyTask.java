package duke.dukeexceptions;

public final class EmptyTask extends DukeExceptions{
    private EmptyTask(String s) {
        super(s);
    }

    public static EmptyTask createEmptyTask(String taskCmd) throws EmptyTask {
        throw new EmptyTask("Are you tryng to add something to " + taskCmd.toLowerCase());
    }
}
