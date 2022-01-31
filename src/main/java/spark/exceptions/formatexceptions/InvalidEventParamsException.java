package spark.exceptions.formatexceptions;

/**
 * This is an exception thrown when the user
 * attempts to add an Event with an incorrect format.
 */
public class InvalidEventParamsException extends FormatException {
    public InvalidEventParamsException() {
        super(String.format("%s\n%s\n%s",
                "Seems like you added an invalid Event!",
                "    correct-format: \"event <TASK TITLE> /at <MM-DD-YYYY HHMM>\"",
                "        example: event biology class /at 02-22-2022 1800"));
    }
}
