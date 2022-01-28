package duke.dukeexceptions;

public final class EmptyNumber extends DukeExceptions{

    private EmptyNumber(String s) {
        super(s);
    }

    public static EmptyNumber createEmptyNumber(String cmd) throws EmptyNumber {
        throw new EmptyNumber(cmd.substring(0, 1).toUpperCase() + cmd.substring(1) + " needs a number");
    }

}
