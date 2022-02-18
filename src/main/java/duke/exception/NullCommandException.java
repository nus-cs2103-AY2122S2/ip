package duke.exception;

public class NullCommandException extends DukeException {

    public NullCommandException(String e) {
        super(e);
    }

    @Override
    public String getMessage() {
        return "Please type some valid command!";
    }

}