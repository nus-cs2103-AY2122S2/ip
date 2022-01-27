package pikabot.exception;

public class DeadlineException extends PikaBotException {

    public DeadlineException(String message) {
        super("☹ OOPS!!! " + message);
    }
}

