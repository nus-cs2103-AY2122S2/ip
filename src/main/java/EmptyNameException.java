/**
 * An exception thrown when the user attempts to create a new
 * Task with no name.
 */
public class EmptyNameException extends Exception {
    public EmptyNameException() {
        super("OOPS!!! You have to give your task a title!");
    }
}
