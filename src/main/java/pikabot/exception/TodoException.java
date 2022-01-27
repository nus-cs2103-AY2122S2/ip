package pikabot.exception;

public class TodoException extends PikaBotException {

    public TodoException() {
        super("☹ OOPS!!! The description of a todo cannot be empty.");
    }
}
