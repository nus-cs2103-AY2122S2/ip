package spark.exceptions.formatexceptions;

public class InvalidToDoParamsException extends FormatException {
    public InvalidToDoParamsException() {
        super(String.format("%s\n%s\n%s",
                "Seems like you added an invalid Todo!",
                "    correct format: \"todo <TASK TITLE>\"",
                "        example: todo buy milk"));
    }
}
