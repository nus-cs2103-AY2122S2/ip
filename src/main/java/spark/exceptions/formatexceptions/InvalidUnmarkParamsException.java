package spark.exceptions.formatexceptions;

/**
 * This is an exception thrown when a user fails to input
 * an integer that would identify the Task to be marked as incomplete;
 * such as inputting an alphabet instead of an integer.
 */
public class InvalidUnmarkParamsException extends FormatException {
    public InvalidUnmarkParamsException() {
        super(String.format("%s\n%s\n%s",
                "Seems like you gave me an invalid unmark task command!",
                "    correct format: \"unmark <TASK_ID>\"",
                "    example: unmark 2"));
    }
}
