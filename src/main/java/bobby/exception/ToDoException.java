package bobby.exception;

public class ToDoException extends BobbyException {
    private String errtype;

    public ToDoException(String message) {
        super(message);
        errtype = message;
    }

    @Override
    public String toString() {
        return "\tThere is no description after the todo command :(";
    }
}
