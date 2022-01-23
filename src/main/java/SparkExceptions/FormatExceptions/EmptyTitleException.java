package SparkExceptions.FormatExceptions;

/**
 * An exception thrown when the user attempts to create a new
 * Tasks.TaskTypes.Task with no name.
 */
public class EmptyTitleException extends FormatException {
    public EmptyTitleException() {
        super("You have to give your task a title!");
    }
}
