package exceptions;

public class EventException extends TaskException {
    public EventException() {
        super();
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "The description of an event cannot be empty.";
    }
}
