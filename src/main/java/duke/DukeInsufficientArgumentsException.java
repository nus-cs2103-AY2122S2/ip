package duke;

public class DukeInsufficientArgumentsException extends DukeException {
    @Override
    public String toString() {
        return "The command you've typed in is missing a few arguments.";
    }
}
