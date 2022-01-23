public class ToDoException extends Exception {

    @Override
    public String getMessage() {
        return "\t ☹ OOPS!!! The description of a todo cannot be empty.";
    }
}
