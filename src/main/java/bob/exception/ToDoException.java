package bob.exception;
/**
 * {@inheritDoc}
 */
public class ToDoException extends BobException {
    public ToDoException() {
        super("Adding a Todo requires a task name! (ノಠ益ಠ)ノ彡┻━┻\n" +
                "\t[e.g. \"todo Write a proper ToDo\"]");
    }
}
