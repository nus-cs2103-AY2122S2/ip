package spark.exceptions.formatexceptions;

public class InvalidUnmarkParamsException extends FormatException {
    public InvalidUnmarkParamsException() {
        super(String.format("%s\n%s\n%s",
                "Seems like you gave me an invalid unmark task command!",
                "    correct format: \"unmark <TASK_ID>\"",
                "    example: unmark 2"));
    }
}
