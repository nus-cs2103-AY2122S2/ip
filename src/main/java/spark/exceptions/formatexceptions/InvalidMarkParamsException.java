package spark.exceptions.formatexceptions;

public class InvalidMarkParamsException extends FormatException {
    public InvalidMarkParamsException() {
        super(String.format("%s\n%s\n%s",
                "Seems like you gave me an invalid mark task command!",
                "    correct format: \"mark <TASK_ID>\"",
                "    example: mark 2"));
    }
}
