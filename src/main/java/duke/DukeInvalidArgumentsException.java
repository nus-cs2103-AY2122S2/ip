package duke;

public class DukeInvalidArgumentsException extends DukeException {
    @Override
    public String toString() {
        return "The arguments supplied are invalid for this command.";
    }
}
