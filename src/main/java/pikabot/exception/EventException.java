package pikabot.exception;

public class EventException extends PikaBotException {

    public EventException(String message) {
        super("☹ OOPS!!! " + message);
    }
}


