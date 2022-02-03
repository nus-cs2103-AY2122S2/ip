package Duke.Exceptions;

public class WrongCommandException extends DukeException {
    public WrongCommandException(String exceptionMessage) {
        super("Sorry! I'm afraid I didnt understand that command...");
    }
}
