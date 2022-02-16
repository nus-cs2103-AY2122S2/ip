package dazz.exception;

/**
 * Represents all the possible error message from Dazz.
 */
public enum ErrorType {
    EMPTY_DATE("OOPS!!! Your date is empty! Please check!"),
    INCOMPLETE_COMMAND("OOPS!!! This command is incomplete! Please check!"),
    INVALID_COMMAND("OOPS!!! I'm sorry, but I don't know what that means :-("),
    INVALID_INDEX("OOPS!!! The index you have provided is invalid!"),
    INVALID_DATE("OOPS!!! The date you have provided is invalid!"
            + "\n\tPlease input in dd/mm/yyyy or dd-mm-yyyy and \n\tfollowed by time in 24hr format.\"");
    private final String taskType;

    ErrorType(String s) {
        taskType = s;
    }

    /**
     * Gets the error message.
     * @return The error message.
     */
    public String getErrorMessage() {
        return taskType;
    }
}
