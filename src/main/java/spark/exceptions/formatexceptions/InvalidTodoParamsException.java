package spark.exceptions.formatexceptions;

/**
 * This is an exception thrown when the user
 * attempts to add a Todo with an incorrect format.
 */
public class InvalidTodoParamsException extends FormatException {
    /**
     * Creates an Exception containing the
     * error message to be displayed to the user on the GUI.
     */
    public InvalidTodoParamsException() {
        super(String.format("%s\n%s\n%s",
                "Seems like you added an invalid Todo!",
                "    correct format: \"todo <TASK TITLE>\"",
                "        example: todo buy milk"));
    }
}
