package spark.exceptions.formatexceptions;

public class InvalidDeadlineParamsException extends FormatException {
    public InvalidDeadlineParamsException() {
        super(String.format("%s\n%s\n%s",
                "Seems like you added an invalid Deadline!",
                        "    correct-format: \"deadline <TASK TITLE> /by <MM-DD-YYYY HHMM>\"",
                        "        example: deadline buy milk /by 02-22-2022 1800"));
    }
}
