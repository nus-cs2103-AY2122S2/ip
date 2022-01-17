/**
 * An exception thrown when the user attempts to create a new
 * Task with no name.
 */
public class EmptyTitleException extends Exception {
    public EmptyTitleException() {
        super("OOPS!!! You have to give your task a title!");
    }
}
