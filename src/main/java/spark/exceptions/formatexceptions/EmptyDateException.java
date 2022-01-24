package spark.exceptions.formatexceptions;

/**
 * An exception thrown when the user attempts to create a new
 * Tasks.TaskTypes.Deadline or Tasks.TaskTypes.Event with no name.
 */
public class EmptyDateException extends FormatException {
    public EmptyDateException() {
        super("You have to give your task a date!");
    }
}
