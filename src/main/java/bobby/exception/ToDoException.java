package bobby.exception;

public class ToDoException extends BobbyException {

    public ToDoException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "There is no description after the todo command :(";
    }
}
