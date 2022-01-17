/**
 * An exception thrown when the user attempts to create a new
 * Deadline or Event with no name.
 */
public class EmptyDateException extends Exception {
    public EmptyDateException() {
        super("OOPS!!! You have to give your task a date!");
    }
}
