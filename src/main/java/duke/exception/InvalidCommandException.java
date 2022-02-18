package duke.exception;

public class InvalidCommandException extends DukeException {
    public InvalidCommandException(String err) {
        super(err);
    }

    @Override
    public String getMessage() {
        return "I cannot find the command! Please type \"help\" for assistance";
    }
}
