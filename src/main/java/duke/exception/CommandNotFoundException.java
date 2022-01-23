package duke.exception;

public class CommandNotFoundException extends DukeException {
    public CommandNotFoundException() {
        super("Oops! try another command!");
    }
}
