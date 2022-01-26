public class ToDoException extends BobbyException {
    public String errtype;

    public ToDoException(String message) {
        super(message);
        errtype = message;
    }

    @Override
    public String toString() {
        return "\tThere is no description after the todo command :(";
    }
}
