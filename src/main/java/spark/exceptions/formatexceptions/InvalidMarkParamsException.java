package spark.exceptions.formatexceptions;

/**
 * This is an exception thrown when a user fails to input
 * an integer that would identify the Task to be marked as complete;
 * such as inputting an alphabet instead of an integer.
 */
public class InvalidMarkParamsException extends FormatException {
    public InvalidMarkParamsException() {
        super(String.format("%s\n%s\n%s",
                "Seems like you gave me an invalid mark task command!",
                "    correct format: \"mark <TASK_ID>\"",
                "    example: mark 2"));
    }
}
