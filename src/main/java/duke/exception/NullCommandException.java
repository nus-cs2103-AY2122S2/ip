package duke.exception;

/**
 * CLass for handling the null command input
 */
public class NullCommandException extends DukeException {

    public NullCommandException(String e) {
        super(e);
    }

    @Override
    public String getMessage() {
        return "Please type some valid command!";
    }

}