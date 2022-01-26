public class ToDoException extends DukeException {

    @Override
    public String getMessage() {
        return "\t ☹ OOPS!!! The description of a todo cannot be empty.";
    }
}
