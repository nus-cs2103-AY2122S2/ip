package SparkExceptions.FormatExceptions;

/**
 * An exception thrown when the user attempts to create a new
 * Tasks.Deadline or Tasks.Event with no name.
 */
public class EmptyDateException extends FormatException {
    public EmptyDateException() {
        super("OOPS!!! You have to give your task a date!");
    }
}
