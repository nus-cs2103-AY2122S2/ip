package duke.dukeexceptions;

public final class EmptyDate extends DukeExceptions{

    private EmptyDate(String s) {
        super(s);
    }

    public static EmptyDate createEmptyDate(String taskCmd) throws EmptyDate {
        throw new EmptyDate(taskCmd.substring(0, 1).toUpperCase() + taskCmd.substring(1) + " needs a date");
    }

}
